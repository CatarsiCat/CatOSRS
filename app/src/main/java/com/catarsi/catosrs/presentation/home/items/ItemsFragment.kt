package com.catarsi.catosrs.presentation.home.items

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.ItemsFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemsFragment : Fragment() {

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
        fragmentBinding.initText.text = viewModel.category.name

        return fragmentBinding.root
    }

}