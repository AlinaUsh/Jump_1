package com.example.harrix.jump

import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.TableLayout
import kotlinx.android.synthetic.main.activity_start.*

class Start : AppCompatActivity() {


    val relief : ArrayList<Triangle> = ArrayList()

    fun drawscreen(){

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        relief.add(Triangle(50,0,1))
        relief.add(Triangle(70,0,1))
        relief.add(Triangle(140,0,1))


        val lay = findViewById<LinearLayout>(R.id.layout)

        val draw = Draw(this)

        lay.addView(draw)

        but2.setOnClickListener { draw.touch = true}
    }
}
