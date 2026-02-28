package com.example.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class MainActivity : AppCompatActivity() {

    //Step 1: Declare Variables
    private lateinit var viewPager: ViewPager2
    private lateinit var pagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Step 6: Set Adapter
        viewPager = findViewById(R.id.viewpager)
        pagerAdapter = ViewPagerAdapter()
        viewPager.adapter = pagerAdapter
    }
}

//Step 2: Create ViewPagerAdapter class
class ViewPagerAdapter: RecyclerView.Adapter<ViewHolder>() {
    //Step 4: Create a List
    private val itemList = listOf("Summary",
            "This project demonstrates:",
            "\t•\tCreating Fragment classes",
            "\t•\tHosting them inside an Activity",
            "\t•\tDynamically switching fragments",
            "It serves as a clean starting point for building multi-screen Android applications using fragments.")

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.page_layout, parent, false)
        return com.example.viewpager.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = itemList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    //Step 5: Implement Members
}

//Step 3: Create Viewholder Class
class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    private val pagerText: TextView = itemView.findViewById(R.id.pagerText)
    fun bind(item: String) {
        pagerText.text = item
    }
}