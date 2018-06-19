package com.example.harrix.jump

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_settings.*

class Settings : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        col.setOnClickListener {
            val intent = Intent(this, Color::class.java)
            startActivity(intent)
        }

        shape.setOnClickListener {
            val intent = Intent(this, Shape::class.java)
            startActivity(intent)
        }
    }
}
