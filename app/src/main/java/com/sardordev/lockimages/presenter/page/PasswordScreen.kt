package com.sardordev.lockimages.presenter.page

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
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



        binding.btn0.setOnClickListener { appendToExpression("0") }
        binding.btn1.setOnClickListener { appendToExpression("1") }
        binding.btn2.setOnClickListener { appendToExpression("2") }
        binding.btn3.setOnClickListener { appendToExpression("3") }
        binding.btn4.setOnClickListener { appendToExpression("4") }
        binding.btn5.setOnClickListener { appendToExpression("5") }
        binding.btn6.setOnClickListener { appendToExpression("6") }
        binding.btn7.setOnClickListener { appendToExpression("7") }
        binding.btn8.setOnClickListener { appendToExpression("8") }
        binding.btn9.setOnClickListener { appendToExpression("9") }


        binding.btnBackSpace.setOnClickListener {
            backSpace()
        }
        binding.btnNext.setOnClickListener {
            checkNext()
        }
        return binding.root
    }
    private fun clear() {
        binding.tvSetPassword.text = ""
    }
    private fun appendToExpression(s: String) {
        if (expression.length < 4) {
            expression += s
            updateExpressionText()
        }
    }
    private fun updateExpressionText() {
        binding.tvSetPassword.text = expression
    }
    private fun backSpace() {
        if (expression.isNotEmpty()) {
            expression = expression.substring(0, expression.length - 1)// 10001
            updateExpressionText()
        }
    }
    private fun checkNext() {
        val enterUser = LocalStorage.getUserId()
        when (enterUser) {
            0 -> {
                toast("New User")
                if (expression.isNotEmpty() && expression.length == 4) {
                    LocalStorage.saveUserId(1)
                    LocalStorage.savePassword(expression)
                } else {
                    Toast.makeText(requireContext(), "Empty", Toast.LENGTH_SHORT).show()
                }
            }
            //
            1 -> {
                if (expression.contains(LocalStorage.getPassword())) toast("Second Screen")
                else if (expression.isNotEmpty()) toast("no user") else toast("Empty")
            }
        }
    }


    private fun toast(str: String) {
        Toast.makeText(
            requireContext(),
            str,
            Toast.LENGTH_SHORT
        ).show()
    }

}