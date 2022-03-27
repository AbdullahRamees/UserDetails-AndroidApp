package com.atarious.userdetails.assignment01

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.atarious.userdetails.R
import com.atarious.userdetails.assignment01.api.UserApiServices
import com.atarious.userdetails.assignment01.model.User
import com.atarious.userdetails.databinding.FragmentAssassinment01Binding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * A simple [Fragment] subclass.
 * Use the [Assassinment01Fragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class Assassinment01Fragment : Fragment() {
    private var _binding: FragmentAssassinment01Binding? = null
    private var UserID :Number = 0
    private val binding get() = _binding!!
    private val userApiServices = UserApiServices.create()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("Assignment 01")
        _binding = FragmentAssassinment01Binding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.layoutsWrongID.setVisibility(View.INVISIBLE)
        binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
        binding.layouts.setVisibility(View.INVISIBLE)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_Assignment01_to_MainMenu)
        }
        binding.Search.setOnClickListener{
            if(binding.TextFieldForID.getText().toString().trim().length <= 0){

                binding.layoutsWrongID.setVisibility(View.VISIBLE)
                binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
                binding.layouts.setVisibility(View.INVISIBLE)

            }else{
                UserID=binding.TextFieldForID.getText().toString().toInt()

                if((UserID as Int) <=0|| UserID as Int >10){

                    binding.layoutSIDNOTFOUND.setVisibility(View.VISIBLE)
                    binding.layouts.setVisibility(View.INVISIBLE)
                    binding.layoutsWrongID.setVisibility(View.INVISIBLE)

                }else{

                    val user = userApiServices.GetUser(UserID.toString())
                    user.enqueue(object : Callback<User> {
                        override fun onResponse(call: Call<User>, response: Response<User>) {
                            val body = response.body()
                            body?.let {
                                binding.IDForUSer.text = "ID : ${it.id}"
                                binding.UserName.text = "Name :${it.name}"
                                binding.UserUserName.text = "UserName : ${it.username}"
                                binding.UserEmail.text = "Email : ${it.email}"
                                binding.StreetAddress.text = "Street : ${it.address.street}"
                                binding.SuitAddress.text = "Suite : ${it.address.suite}"
                                binding.city.text = "City : ${it.address.city}"
                                binding.zipCode.text = "ZipCode : ${it.address.zipcode}"
                                binding.Phone.text = "Phone: ${it.phone}"
                                binding.Website.text = "WebSite : ${it.website}"
                                binding.company.text = "Company : ${it.company.name}"
                            }
                        }

                        override fun onFailure(call: Call<User>, t: Throwable) {
                            Log.i("DemoFragment ",t.message!!)
                        }

                    })
                    binding.IDForUSer.setText("ID : $UserID")
                    binding.layoutsWrongID.setVisibility(View.INVISIBLE)
                    binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
                    binding.layouts.setVisibility(View.VISIBLE)

                }
            }


        }
        binding.Clear.setOnClickListener{
            binding.layoutsWrongID.setVisibility(View.INVISIBLE)
            binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
            binding.layouts.setVisibility(View.INVISIBLE)
            binding.TextFieldForID.text = null
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

