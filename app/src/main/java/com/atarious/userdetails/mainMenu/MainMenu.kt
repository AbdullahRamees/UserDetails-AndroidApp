package com.atarious.userdetails.mainMenu

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
import com.atarious.userdetails.databinding.FragmentMainMenuBinding
import com.atarious.userdetails.mainMenu.Adapters.MenuAdapter
import com.atarious.userdetails.mainMenu.model.MainMenuItems
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass.
 * Use the [MainMenu.newInstance] factory method to
 * create an instance of this fragment.
 */
class MainMenu : Fragment() {

    private var _binding: FragmentMainMenuBinding? = null
    private val binding get() = _binding!!



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("Main Memu")
        _binding = FragmentMainMenuBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.Home.setOnClickListener {
            findNavController().navigate(R.id.action_MainMenu_to_HomeFragment)
        }
        binding.recyclerview.layoutManager = LinearLayoutManager(view.context)

                val Menu:List<MainMenuItems> = listOf(
                    MainMenuItems(1,"Assignment 01"),
                    MainMenuItems(2,"Assignment 02"),
                    MainMenuItems(3,"Practice Demo")
                )
                val adapter = MenuAdapter(Menu)
                binding.recyclerview.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}