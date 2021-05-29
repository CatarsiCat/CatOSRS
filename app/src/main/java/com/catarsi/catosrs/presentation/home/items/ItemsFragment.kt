package com.catarsi.catosrs.presentation.home.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.ItemsFragmentBinding
import com.catarsi.catosrs.domain.model.Item
import com.catarsi.catosrs.presentation.loadImage
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment() {

    private var items: List<Item> = arrayListOf()
    private lateinit var fragmentBinding: ItemsFragmentBinding
    private val viewModel: ItemsViewModel by viewModels()
    private val args: ItemsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.category = args.category
        viewModel.loadCategory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        fragmentBinding = DataBindingUtil.inflate(inflater, R.layout.items_fragment, container, false)
        fragmentBinding.itemsViewModel = viewModel
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.category.name

        setupRecyclerView()

        return fragmentBinding.root
    }

    private fun setupRecyclerView() {
        viewModel.items.observe(viewLifecycleOwner, Observer { items->
            items?.let {
                this.items = it
                fragmentBinding.rvItems.adapter!!.notifyDataSetChanged()
            }
        })

        fragmentBinding.rvItems.layoutManager = LinearLayoutManager(context)
        fragmentBinding.rvItems.adapter = object : RecyclerView.Adapter<ItemHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
                return ItemHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_holder, parent, false))
            }

            override fun onBindViewHolder(holder: ItemHolder, position: Int) {
                var item = items[position]
                holder.title.text = item.name
                holder.subtitle.text = item.description
                holder.price.text = item.current.price
                holder.pbIcon.visibility = View.VISIBLE
                holder.icon.loadImage(item.icon_large, holder.pbIcon)
                holder.itemView.setOnClickListener{
                    Toast.makeText(context, "Click!", Toast.LENGTH_SHORT).show()
                }
            }

            override fun getItemCount(): Int {
               return items.size
            }

        }


    }

    class ItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView = view.findViewById(R.id.tvTitle)
        var subtitle: TextView = view.findViewById(R.id.tvSubtitle)
        var price: TextView = view.findViewById(R.id.tvPrice)
        var icon: ImageView = view.findViewById(R.id.ivIcon)
        var pbIcon: ProgressBar = view.findViewById(R.id.pbIcon)
    }


}