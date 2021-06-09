package com.catarsi.catosrs.presentation.old.itemdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.catarsi.catosrs.R
import com.catarsi.catosrs.databinding.FragmentItemDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ItemDetailFragment : Fragment() {

    private lateinit var fragmentBinding: FragmentItemDetailBinding
    private val viewModel: ItemDetailViewModel by viewModels()
    private val args: ItemDetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.oldItem = args.oldItem
        viewModel.getTimeseries()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_item_detail, container, false)
        fragmentBinding.itemDetailViewModel = viewModel
        (activity as AppCompatActivity).supportActionBar?.title = viewModel.oldItem.name

        return fragmentBinding.root
    }


}