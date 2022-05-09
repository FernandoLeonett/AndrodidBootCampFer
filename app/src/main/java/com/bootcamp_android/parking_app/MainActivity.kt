package com.bootcamp_android.parking_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.adapter.LotAdapter
import com.bootcamp_android.parking_app.adapter.Provider

class MainActivity : AppCompatActivity() {






    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initRecyclerView()

    }

    fun initRecyclerView() {
        val recycleView = findViewById<RecyclerView>(R.id.recycler_id_list)
        recycleView.layoutManager = LinearLayoutManager(this)

        recycleView.adapter = LotAdapter(Provider.lots)
    }
}