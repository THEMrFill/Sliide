package com.sliide.philip.arnold.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.android.viewmodel.ext.android.viewModel
import com.sliide.philip.arnold.R
import com.sliide.philip.arnold.databinding.MainFragmentBinding
import com.sliide.philip.arnold.utils.nonNullObserve

class MainFragment : Fragment() {
    companion object {
        fun newInstance() = MainFragment()
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()
    private val mainAdapter = MainAdapter(::rowClick)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = MainFragmentBinding.bind(view)

        with (binding) {
            with (recycler) {
                adapter = mainAdapter
                layoutManager = LinearLayoutManager(context)
            }
            addButton.setOnClickListener {
                showAddDialog()
            }
        }

        setupObservers()
    }

    private fun setupObservers() {
        with (viewModel) {
            users.nonNullObserve(viewLifecycleOwner) { users ->
                mainAdapter.setUsers(users.data)
            }
        }
    }

    private fun rowClick(id: Int) {

    }

    private fun showAddDialog() {
        AddUserDialog().show(requireFragmentManager(), AddUserDialog.TAG)
    }
}