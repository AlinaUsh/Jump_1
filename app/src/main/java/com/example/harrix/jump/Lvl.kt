package com.example.harrix.jump

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lvl.*


class Lvl : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl)
        lvl1.setOnClickListener {
            val intent = Intent(this, Lvl1::class.java)
            startActivity(intent)
        }
        lvl2.setOnClickListener {
            val intent = Intent(this, Lvl2::class.java)
            startActivity(intent)
        }
        lvl3.setOnClickListener {
            val intent = Intent(this, Lvl3::class.java)
            startActivity(intent)
        }
    }
}
