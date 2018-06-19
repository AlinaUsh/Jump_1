package com.example.harrix.jump

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_start.*

class Start : AppCompatActivity() {


    val relief : ArrayList<Triangle> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        relief.add(Triangle(150,0,1))
        relief.add(Triangle(700,0,1))
        relief.add(Triangle(210,0,1))
        relief.add(Triangle(270,-1,-1))
        relief.add(Triangle(2100,0,1))
        relief.add(Triangle(1200,0,1))
        relief.add(Triangle(850,-1,-1))
        relief.add(Triangle(1020,-1,-1))
        relief.add(Triangle(1700,-1,-1))
        relief.add(Triangle(1760,-1,-1))
        relief.add(Triangle(2300,-1,-1))
        relief.add(Triangle(2360,-1,-1))
        relief.add(Triangle(2420,-1,-1))


        val lay = findViewById<LinearLayout>(R.id.layout)

        val draw = Draw(this, relief)

        lay.addView(draw)

        but2.setOnClickListener { draw.touch = true}
    }
}