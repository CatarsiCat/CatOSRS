package com.catarsi.catosrs.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private lateinit var fragmentHomeBinding: FragmentHomeBinding
    private val viewModel: HomeViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //viewModel.loadCategory()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        fragmentHomeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        fragmentHomeBinding.homeViewModel = viewModel

        val categories = viewModel.categoryList
        fragmentHomeBinding.rvCategory.layoutManager = LinearLayoutManager(context)
        fragmentHomeBinding.rvCategory.adapter = object : RecyclerView.Adapter<CategoryHolder>() {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryHolder {
                return CategoryHolder(LayoutInflater.from(parent.context).inflate(android.R.layout.simple_list_item_1, parent, false))
            }

            override fun getItemCount(): Int {
                return categories.size
            }

            override fun onBindViewHolder(holder: CategoryHolder, position: Int) {
                val name = categories[position].name
                holder.textField.text = name
                holder.textField.setOnClickListener {
                    findNavController().navigate(HomeFragmentDirections.actionNavHomeToItemsFragment(categories[position]))
                    Toast.makeText(context, "Clicked $name ($position)", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return fragmentHomeBinding.root
    }

    inner class CategoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        var textField: TextView = view.findViewById(android.R.id.text1) as TextView
    }

}
