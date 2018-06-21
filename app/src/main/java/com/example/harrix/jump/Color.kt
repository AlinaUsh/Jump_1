package com.example.harrix.jump

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_color.*

class Color : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_color)
        col1.setOnClickListener {
            Draw.color = android.graphics.Color.BLACK
            val intent = Intent(this, Settings::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        col2.setOnClickListener{
            Draw.color = android.graphics.Color.RED
            val intent = Intent(this, Settings::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)

        }
        col3.setOnClickListener{
            Draw.color = android.graphics.Color.YELLOW
            val intent = Intent(this, Settings::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
        col4.setOnClickListener{
            Draw.color = android.graphics.Color.GREEN
            val intent = Intent(this, Settings::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }
    }
}