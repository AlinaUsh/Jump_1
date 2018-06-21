package com.example.harrix.jump

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_start.*


class Start : AppCompatActivity() {



    val relief : ArrayList<ObjectRelief> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x
        //val height = size.y
        var counter = 0

        while (counter>-1){
            relief.add(Triangle(150 + width * counter,0,1))
            relief.add(Triangle(700 + width * counter,0,1))
            relief.add(Triangle(210 + width * counter,0,1))
            relief.add(Triangle(270 + width * counter,-1,-1))
            relief.add(Triangle(2100 + width * counter,0,1))
            relief.add(Triangle(1200 + width * counter,0,1))
            relief.add(Triangle(850 + width * counter,-1,-1))
            relief.add(Triangle(1020 + width * counter,-1,-1))
            relief.add(Triangle(1700 + width * counter,-1,-1))
            relief.add(Triangle(1760 + width * counter,-1,-1))
            relief.add(Triangle(2300 + width * counter,-1,-1))
            relief.add(Triangle(2360 + width * counter,-1,-1))
            relief.add(Triangle(2420 + width * counter,-1,-1))
            relief.add(ReliefRect(500 + width * counter,-1,100))
            relief.add(ReliefRect(560 + width * counter,-1,100))
            relief.add(ReliefRect(620 + width * counter,-1,100))
            relief.add(ReliefRect(1000 + width * counter,-1,50))
            relief.add(ReliefRect(1060 + width * counter,-1,100))







        val draw = Draw(this, relief)

        layout.addView(draw)

        but2.setOnClickListener { draw.touch = true}
            counter++
        }


    }
}