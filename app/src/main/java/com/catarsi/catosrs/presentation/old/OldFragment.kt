package com.catarsi.catosrs.presentation.old

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
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


        return fragmentOldBinding.root
    }

}