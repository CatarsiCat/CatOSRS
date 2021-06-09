package com.catarsi.catosrs.presentation.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.catarsi.catosrs.R
import com.catarsi.catosrs.data.source.remote.model.OldItem
import com.catarsi.catosrs.databinding.FragmentOldBinding
import com.catarsi.catosrs.presentation.old.adapter.OldItemsListAdapter
import dagger.hilt.android.AndroidEntryPoint
import java.util.*
import kotlin.collections.ArrayList

@AndroidEntryPoint
class OldFragment : Fragment() {

    private lateinit var fragmentOldBinding: FragmentOldBinding
    private val viewModel: OldViewModel by viewModels()
    private lateinit var adapter: OldItemsListAdapter

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


        setupRecyclerView()
        setupSearchBar()

        return fragmentOldBinding.root
    }

    private fun setupSearchBar() {
        fragmentOldBinding.oldItemsSearch.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(text: String?): Boolean {
                adapter.filter.filter(text)
                return false
            }

        })
    }

    private fun setupRecyclerView() {
        viewModel.listItem.observe(viewLifecycleOwner, { items ->
            items?.let {
                adapter = OldItemsListAdapter(viewModel.listItem.value!! as ArrayList<OldItem>)
                fragmentOldBinding.rvOldItems.adapter = adapter
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
    }


}