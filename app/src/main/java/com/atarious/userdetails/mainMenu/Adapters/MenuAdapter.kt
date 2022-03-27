package com.atarious.userdetails.mainMenu.Adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.atarious.userdetails.Practices.Adapters.UserAdapter_Practice
import com.atarious.userdetails.R
import com.atarious.userdetails.mainMenu.model.MainMenuItems

class MenuAdapter (private val MenuList: List<MainMenuItems>) :
    RecyclerView.Adapter<MenuAdapter.ViewHolder>() {
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design_mainmenu, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = MenuList[position]

        // sets the image to the imageview from our itemHolder class
        // sets the text to the textview from our itemHolder class
        holder.MenuName.text = itemsViewModel.Menuname
        holder.go.setOnClickListener {
            val navController = Navigation.findNavController(it)
            if(itemsViewModel.Menuid == 1){
                navController!!.navigate(R.id.action_MainMenu_to_Assignment01)
            }else if(itemsViewModel.Menuid == 2){
                navController!!.navigate(R.id.action_MainMenu_to_Assignment02)
            }else if(itemsViewModel.Menuid == 3){
                navController!!.navigate(R.id.action_MainMenu_to_demoFragment)
            }
        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return MenuList.size
    }

    // Holds the views for adding it to image and text

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val MenuName: TextView = itemView.findViewById(R.id.MenuName)
        val go = itemView.findViewById<ImageButton>(R.id.Go)
    }
}
