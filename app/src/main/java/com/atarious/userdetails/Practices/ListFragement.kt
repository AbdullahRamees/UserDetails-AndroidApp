package com.atarious.userdetails.Practices


import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle

import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.atarious.userdetails.Practices.Adapters.UserAdapter_Practice
import com.atarious.userdetails.Practices.Model.userList_Practices
import com.atarious.userdetails.Practices.api.UserApiServices_Practices
import com.atarious.userdetails.R
import com.atarious.userdetails.databinding.FragmentListBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ListFragement : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private val userApiServices = UserApiServices_Practices.create()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("User List")
        _binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Home.setOnClickListener {
            findNavController().navigate(R.id.action_userFragment_to_HomeFragment)
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(view.context)
        val users = userApiServices.getUsers()
        users.enqueue(object : Callback<List<userList_Practices>>{
            override fun onResponse(
                call: Call<List<userList_Practices>>,
                response: Response<List<userList_Practices>>
            ) {
                val UsersBody = response.body()
                val adapter = UserAdapter_Practice(UsersBody!!)
                binding.recyclerview.adapter = adapter
            }

            override fun onFailure(call: Call<List<userList_Practices>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}











