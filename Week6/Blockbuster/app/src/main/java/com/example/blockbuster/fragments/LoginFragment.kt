package com.example.blockbuster.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.blockbuster.R
import com.example.blockbuster.data.DataManager
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private  lateinit var  model : DataManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        model = activity?.let { ViewModelProvider(it).get(DataManager::class.java) }!!
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // -------------------------------------Hide toolbar
        val activity = activity as AppCompatActivity
        activity.supportActionBar?.hide()
        // -------------------------------------------------
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (model.getLoggedStatus()){
            view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }

        bt_logIn.setOnClickListener {
            model.setLoggedStatus(true)
            view.findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
    }
}