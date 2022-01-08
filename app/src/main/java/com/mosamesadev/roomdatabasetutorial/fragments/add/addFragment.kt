package com.mosamesadev.roomdatabasetutorial.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.mosamesadev.roomdatabasetutorial.R
import com.mosamesadev.roomdatabasetutorial.data.User
import com.mosamesadev.roomdatabasetutorial.data.UserViewModel
import kotlinx.android.synthetic.main.fragment_add.*
import kotlinx.android.synthetic.main.fragment_add.view.*

class addFragment : Fragment() {

    private lateinit var mUserViewModel: UserViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.add_btn.setOnClickListener {
            insertDataToDatabase()
        }


        return view
    }

    private fun insertDataToDatabase() {

        val firstName = addFirstName.text.toString()
        val lastName = addLastName.text.toString()
        val age = addAge.text.toString()

        if(inputCheck(firstName,lastName,age)){
            val user = User(0, firstName,lastName,age)
            mUserViewModel.addUser(user)
            Toast.makeText(requireContext(),"Succesfully added", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.listFragment)
        }else Toast.makeText(requireContext(),"please fill out all fields", Toast.LENGTH_LONG).show()
    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean{
        return !(TextUtils.isEmpty(firstName)) && !TextUtils.isEmpty(lastName) && !TextUtils.isEmpty(age)
    }
}