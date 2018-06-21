package com.example.harrix.jump

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_lose.*
import kotlinx.android.synthetic.main.activity_menu.*

class Lose : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lose)
        start.setOnClickListener {
            val intent = Intent(this, Menu:: class.java)
            startActivity(intent)
        }
    }
}
