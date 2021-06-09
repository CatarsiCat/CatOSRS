package com.catarsi.catosrs.presentation.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.FragmentOldBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class OldFragment : Fragment() {

    private lateinit var fragmentOldBinding: FragmentOldBinding
    private val viewModel: OldViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.loadRepo()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentOldBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_old, container, false)
        fragmentOldBinding.oldViewModel = viewModel


        setupRecyclerView();

        return fragmentOldBinding.root
    }

    private fun setupRecyclerView() {
        viewModel.listItem.observe(viewLifecycleOwner, { items ->
            items?.let {
                fragmentOldBinding.rvOldItems.adapter!!.notifyDataSetChanged()
                fragmentOldBinding.shimmerFrameLayout.visibility = View.GONE
                fragmentOldBinding.rvOldItems.visibility = View.VISIBLE
            }
        })

        fragmentOldBinding.rvOldItems.layoutManager = LinearLayoutManager(context)
        fragmentOldBinding.rvOldItems.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        fragmentOldBinding.rvOldItems.adapter = object : RecyclerView.Adapter<OldItemHolder>() {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OldItemHolder {
                return OldItemHolder(
                    LayoutInflater.from(parent.context)
                        .inflate(android.R.layout.simple_list_item_1, parent, false)
                )
            }

            override fun onBindViewHolder(holder: OldItemHolder, position: Int) {
                val oldItem = viewModel.listItem.value!![position]
                holder.textField.text = oldItem.name
                holder.textField.setOnClickListener {
                    Toast.makeText(context, "Clicked ${oldItem.id}", Toast.LENGTH_SHORT).show()
                }
            }

            override fun getItemCount(): Int {
                return viewModel.listItem.value!!.size
            }

        }
    }

    class OldItemHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textField: TextView = view.findViewById(android.R.id.text1) as TextView
    }

}