package com.atarious.userdetails.Practices.Adapters




import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.atarious.userdetails.Practices.DemoFragment
import com.atarious.userdetails.Practices.ListFragement
import com.atarious.userdetails.Practices.Model.userList_Practices
import com.atarious.userdetails.R
import java.util.concurrent.Executors


class UserAdapter_Practice(private val mListPractices: List<userList_Practices>) :
    RecyclerView.Adapter<UserAdapter_Practice.ViewHolder>() {


    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.card_view_design, parent, false)
        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemsViewModel = mListPractices[position]
        // sets the image to the imageview from our itemHolder class
        ImageAdd(itemsViewModel.Image,holder.imageView)
        // sets the text to the textview from our itemHolder class
        holder.textViewName.text = "${itemsViewModel.first_name} ${itemsViewModel.last_name}"
        holder.textViewID.text ="ID : ${itemsViewModel.id}"
        holder.Search.setOnClickListener{
            val navController = Navigation.findNavController(it)
            navController!!.navigate(R.id.action_userFragment_to_demoFragment)
        }
    }


    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mListPractices.size
    }

    // Holds the views for adding it to image and text

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageview)
        val textViewName: TextView = itemView.findViewById(R.id.textView_Name)
        val textViewID = itemView.findViewById<TextView>(R.id.textview_ID)
        val Search = itemView.findViewById<ImageButton>(R.id.search_button)
    }


    fun ImageAdd(URL:String, imageView:ImageView){
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