package com.atarious.userdetails


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.atarious.userdetails.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity(){
     var ID : Number? = null

    fun SetID(id:Number){
        ID = id
    }
private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding.root)
    }


}