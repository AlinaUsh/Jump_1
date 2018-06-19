package com.example.harrix.jump

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_menu.*

class Menu : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)
        start.setOnClickListener {
            val intent = Intent(this, Start::class.java)
            startActivity(intent)
        }
        set.setOnClickListener {
            val intent = Intent(this, Settings::class.java)
            startActivity(intent)
        }
        lvl.setOnClickListener {
            val intent = Intent(this, Lvl::class.java)
            startActivity(intent)
        }
    }
}
