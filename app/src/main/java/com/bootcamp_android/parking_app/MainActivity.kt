package com.bootcamp_android.parking_app

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp_android.parking_app.adapter.Adapter
import com.bootcamp_android.parking_app.adapter.Provider

class MainActivity : AppCompatActivity() {

    private val dataAdapter: Adapter by lazy {
        Adapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dataAdapter.setData(Provider.lots)

        findViewById<RecyclerView>(R.id.lot_list)
            .apply {
                layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this@MainActivity)
                hasFixedSize()
                this.adapter = dataAdapter
            }


    }


}