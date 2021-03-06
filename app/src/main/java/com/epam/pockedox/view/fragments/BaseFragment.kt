package com.epam.pockedox.view.fragments

import android.app.Dialog
import android.view.Window
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.epam.pockedox.R
import com.epam.pockedox.viewmodel.BaseViewModel

/**
 * Class made to generalize all the fragments of application
 * * Every activity has data loading and it has to show error
 */

abstract class BaseFragment<VM : BaseViewModel>(val layout : Int) : Fragment(layout) {
    protected abstract val viewModel: VM

    override fun onStart() {
        super.onStart()
        observeData()
    }

    private fun getLoader(): Dialog {
        val loadingDialog = Dialog(requireContext())
        loadingDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        loadingDialog.setCancelable(false)
        loadingDialog.setContentView(R.layout.dialog_loader)
        loadingDialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        return loadingDialog
    }

    private fun showError(message: String) {
        val errorDialog = Dialog(requireContext())
        errorDialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        errorDialog.setCancelable(false)
        errorDialog.setContentView(R.layout.dialog_error)
        errorDialog.findViewById<TextView>(R.id.tvErrorDialog).text = message
        errorDialog.findViewById<Button>(R.id.btnErrorDialog).setOnClickListener{
            errorDialog.dismiss()
        }
        errorDialog.show()
    }

    /**
     * Observes error and loading data, which is set in ViewModel
     */
    private fun observeData() {
        val loader = getLoader()

        viewModel.isLoading.observe(this, Observer { isLoading ->
            if (isLoading) {
                loader.show()
            } else {
                loader.dismiss()
            }
        })

        viewModel.errorMessageData.observe(this, Observer { message ->
            if (message != null) {
                showError(message)
            }
        })
    }
}
