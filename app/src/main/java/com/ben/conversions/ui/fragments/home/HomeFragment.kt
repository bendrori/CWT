package com.ben.conversions.ui.fragments.home

import android.graphics.Color
import android.os.Bundle
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.ben.conversions.databinding.HomeFragmentBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import androidx.recyclerview.widget.DividerItemDecoration


@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: HomeFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = HomeFragmentBinding.inflate(layoutInflater)
        .also {
            binding = it
            it.lifecycleOwner = viewLifecycleOwner
            it.viewModel = viewModel
        }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()
        setObserves()
    }

    private fun setObserves() {
        viewModel.errorEvent.observe(viewLifecycleOwner) {
            showSnackbar(it)
        }
    }

    private fun showSnackbar(message: String) {
        Snackbar.make(
            requireView(), message,
            Snackbar.LENGTH_LONG
        ).setBackgroundTint(Color.RED).show()
    }

    private fun initViews() {
        binding.conversionRecyclerViewId.apply {
            adapter = HomeListAdapter()
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}