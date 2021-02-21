package com.sliide.philip.arnold.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.sliide.philip.arnold.R
import com.sliide.philip.arnold.databinding.AddUserDialogBinding

class AddUserDialog : DialogFragment() {
    companion object {
        const val TAG = "SimpleDialog"

        fun newInstance(): AddUserDialog {
            return AddUserDialog()
        }
    }

    private lateinit var binding: AddUserDialogBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_user_dialog, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = AddUserDialogBinding.bind(view)

        setupClickListeners()
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.WRAP_CONTENT
        )
    }

    private fun setupClickListeners() {
        binding.btnPositive.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
        binding.btnNegative.setOnClickListener {
            // TODO: Do some task here
            dismiss()
        }
    }
}