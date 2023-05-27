package com.sardordev.lockimages.presenter.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.navigation.fragment.findNavController
import androidx.navigation.navOptions
import com.sardordev.lockimages.R
import com.sardordev.lockimages.data.localstorage.LocalStorage
import com.sardordev.lockimages.databinding.FragmentPasswordScreenBinding

class PasswordScreen : Fragment() {
    private val binding by lazy { FragmentPasswordScreenBinding.inflate(layoutInflater) }
    private var expression = ""
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding.pinView.setOnCompletedListener = { pincode ->
            val userId = LocalStorage.getPin() //1
            when (userId) {
                0 -> {
                    LocalStorage.savePassword(pincode)//2144
                    toast(LocalStorage.getPassword())
                    binding.pinView.clearPin()
                    LocalStorage.firstPin(1)
                }
                1 -> {
                    if (LocalStorage.getPassword().contains(pincode)) {
                        binding.pinView.clearPin()
                        navigate()
                        toast("true")
                    } else {
                        binding.pinView.clearPin()
                        binding.pinView.showError(true)
                        toast("false")
                    }
                }
            }
        }




        return binding.root
    }

    //
//    private fun clear() {
//        binding.tvSetPassword.text = ""
//    }
//
//    private fun appendToExpression(s: String) {
//        if (expression.length < 4) {
//            expression += s
//            updateExpressionText()
//        }
//    }
//
//    private fun updateExpressionText() {
//        binding.tvSetPassword.text = expression
//    }
//
//    private fun backSpace() {
//        if (expression.isNotEmpty()) {
//            expression = expression.substring(0, expression.length - 1)// 10001
//            updateExpressionText()
//        }
//    }
//
//    private fun checkNext() {
//        val enterUser = LocalStorage.getUserId()
//        when (enterUser) {
//            0 -> {
//                toast("New User")
//                if (expression.isNotEmpty() && expression.length == 4) {
//                    LocalStorage.saveUserId(1)
//                    LocalStorage.savePassword(expression)
//                } else {
//                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
//                }
//            }
//            //
//            1 -> {
//                if (expression.contains(LocalStorage.getPassword())) {

//                } else if (expression.isNotEmpty()) toast("no user") else toast("Empty")
//            }
//        }
//    }

    private fun navigate() {
        findNavController().navigate(R.id.mainFragment, null, navOptions {
            launchSingleTop = true
            popUpTo(R.id.navigation) {
                inclusive = true
            }
        })
    }

    private fun toast(str: String) {
        Toast.makeText(
            requireContext(),
            str,
            Toast.LENGTH_SHORT
        ).show()
    }

}