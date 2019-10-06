package com.preqel.zadapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.preqel.adapterz.CommonAdadpter
import com.preqel.adapterz.base.ViewHolder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recyclerView =  findViewById<RecyclerView>(R.id.recyclerview)
        val datas = listOf("hell ", "dfdf" ,"ddd")
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = object: CommonAdadpter<String>(this, R.layout.item_recye,datas) {
            override fun convert(holder: ViewHolder, t: String, position: Int) {
                val textview = holder.getView<TextView>(R.id.tv_text)
                textview.text = t
            }
        }

    }
}
