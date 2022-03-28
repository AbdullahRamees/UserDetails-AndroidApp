package com.atarious.userdetails.assignment02

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Gravity.TOP
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.atarious.userdetails.R
import com.atarious.userdetails.databinding.FragmentSharedProferencesBinding



/**
 * A simple [Fragment] subclass.
 * Use the [SharedPreferences.newInstance] factory method to
 * create an instance of this fragment.
 */
class SharedPreferences : Fragment() {
    private var _binding:FragmentSharedProferencesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("Shared Preferences Demo")
        _binding = FragmentSharedProferencesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.DataLayout.visibility = View.INVISIBLE
        binding.NOdataLayout2.visibility = View.INVISIBLE

        binding.Save.setOnClickListener{

            if(binding.TextEditerForName.getText().toString().trim().isEmpty() ||binding.textEditerForNumber.getText().toString().trim().isEmpty()){
                val toast = Toast.makeText(context,"Please Enter Data",Toast.LENGTH_LONG)
                toast.setGravity(TOP,0,140)
                toast.show()
            }else{
                val perf: SharedPreferences =requireContext().getSharedPreferences("demo",Context.MODE_PRIVATE)
                val editor = perf.edit()

                editor.putString("Name",binding.TextEditerForName.text.toString())
                editor.putInt("Phone",binding.textEditerForNumber.text.toString().toInt())
                editor.putBoolean("Whatsapp",binding.WhatsApp.isChecked)
                editor.commit()

                binding.DataLayout.visibility = View.INVISIBLE
                binding.NOdataLayout2.visibility = View.INVISIBLE

                val toast = Toast.makeText(context,"saved",Toast.LENGTH_LONG)
                toast.setGravity(TOP,0,140)
                toast.show()
            }

        }
        binding.Return.setOnClickListener {
            val perf: SharedPreferences =requireContext().getSharedPreferences("demo",Context.MODE_PRIVATE)
            val name = perf.getString("Name","")
            val number = perf.getInt("Phone",0)
            val WA = perf.getBoolean("Whatsapp",false)

            if(number == null||name ==""){
                binding.NOdataLayout2.visibility = View.VISIBLE
                binding.DataLayout.visibility = View.INVISIBLE
            }else{
                Log.i("Get",name!!)
                binding.NAmeGet.text ="Name : $name"
                binding.PhoneGet.text="Phone : $number"
                binding.IhaveWA.text = "Have whatsapp: $WA"
                binding.DataLayout.visibility = View.VISIBLE
                binding.NOdataLayout2.visibility = View.INVISIBLE
            }
        }

        binding.buttonClear.setOnClickListener {
            val perf: SharedPreferences =requireContext().getSharedPreferences("demo",Context.MODE_PRIVATE)
            val editor = perf.edit()
            editor.clear()
            editor.commit()
            binding.DataLayout.visibility = View.INVISIBLE
            binding.NOdataLayout2.visibility = View.INVISIBLE
        }
        binding.buttonBAck.setOnClickListener {
            findNavController().navigate(R.id.action_Assignment02_to_MainMenu)
        }
    }


}

