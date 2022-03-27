package com.atarious.userdetails.Practices

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Gravity
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.atarious.userdetails.Practices.Model.User
import com.atarious.userdetails.Practices.api.UserApiServices_Practices
import com.atarious.userdetails.R
import com.atarious.userdetails.databinding.FragmentDemoBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DemoFragment : Fragment() {


    private var _binding: FragmentDemoBinding? = null
    private var UserID :Number = 0
    private val binding get() = _binding!!
    private val userApiServices = UserApiServices_Practices.create()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.findViewById<TextView>(R.id.custom_title)?.setText("Demo")
        _binding = FragmentDemoBinding.inflate(inflater, container, false)
      return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.layoutsWrongID.setVisibility(View.INVISIBLE)
        binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
        binding.layouts.setVisibility(View.INVISIBLE)
        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_demoFragment_to_userFragment)
        }
        binding.Search.setOnClickListener{
            PrintDetails()
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

    fun PrintDetails(){
        if(binding.TextFieldForID.getText().toString().trim().length <= 0){

            binding.layoutsWrongID.setVisibility(View.VISIBLE)
            binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
            binding.layouts.setVisibility(View.INVISIBLE)

        }else{
            UserID=binding.TextFieldForID.getText().toString().toInt()-1

            if((UserID as Int) <0|| UserID as Int >100){

                binding.layoutSIDNOTFOUND.setVisibility(View.VISIBLE)
                binding.layouts.setVisibility(View.INVISIBLE)
                binding.layoutsWrongID.setVisibility(View.INVISIBLE)

            }else{

                val user = userApiServices.GetUser(UserID.toString())
                user.enqueue(object : Callback<User>{
                    override fun onResponse(call: Call<User>, response: Response<User>) {
                        val body = response.body()
                        body?.let {
                            binding.IDForUSer.text = "ID : ${it.id}"
                            binding.UserName.text = "First Name :${it.first_name}"
                            binding.UserUserName.text = "Last Name : ${it.last_name}"
                            binding.UserEmail.text = "Email : ${it.email}"
                            binding.address.text ="Address : ${it.Address.Suite},${it.Address.Street} Street,${it.Address.City}"
                            binding.gender.text = "Gender : ${it.gender}"
                            binding.dateofbirth.text = "Date of Birth: ${it.date_of_birth}"
                            binding.ipaddress.text = "Ip Address : ${it.ip_address}"
                            binding.company.text = "Company : ${it.Company}"
                            ImageAdd("${it.Image}")
                        }
                    }

                    override fun onFailure(call: Call<User>, t: Throwable) {
                        Log.i("DemoFragment ",t.message!!)
                    }

                })
                binding.layoutsWrongID.setVisibility(View.INVISIBLE)
                binding.layoutSIDNOTFOUND.setVisibility(View.INVISIBLE)
                binding.layouts.setVisibility(View.VISIBLE)

            }
        }
    }

    fun ImageAdd(URL:String){
        // Declaring and initializing the ImageView
        val imageView = binding.Pic
        // Declaring executor to parse the URL
        val executor = Executors.newSingleThreadExecutor()
        // Once the executor parses the URL
        // and receives the image, handler will load it
        // in the ImageView
        val handler = Handler(Looper.getMainLooper())

        // Initializing the image
        var image: Bitmap? = null

        // Only for Background process (can take time depending on the Internet speed)
        executor.execute {
            // Image URL
            val imageURL = "$URL"
            // Tries to get the image and post it in the ImageView
            // with the help of Handler
            try {
                val `in` = java.net.URL(imageURL).openStream()
                image = BitmapFactory.decodeStream(`in`)
                // Only for making changes in UI
                handler.post {
                    imageView.setImageBitmap(image)
                }
            }
            // If the URL doesnot point to
            // image or any other kind of failure
            catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }



}






