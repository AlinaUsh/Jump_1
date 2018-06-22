package com.example.harrix.jump

import android.graphics.Point
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_lvl1.*

class Lvl1 : AppCompatActivity() , Scoreable {
    override fun updateScore(score: Int) {
        passive_text.text="score: ${score / 10}"
    }

    val relief : ArrayList<ObjectRelief> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl1)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

            relief.add(Triangle(550,-1,-1))
            relief.add(Triangle(650,1,1))
            relief.add(Triangle(1020,-1,-1))
            relief.add(Triangle(1400,-1,-1))
            relief.add(Triangle(1460,-1,-1))
            relief.add(Triangle(1560,1,1))
            relief.add(Triangle(1660,-1,-1))
            relief.add(Triangle(1760,1,1))
            relief.add(Triangle(1900,-1,-1))
            relief.add(Triangle(1960,-1,-1))
            relief.add(Triangle(2070,2,1))
            relief.add(Triangle(2130,2,1))
            relief.add(Triangle(2190,2,1))
            relief.add(Triangle(2290,-1,-1))
            relief.add(Triangle(2560,-1,-1))
            relief.add(Triangle(2860 ,-1,-1))
            relief.add(Triangle(3000,2,1))
            relief.add(Triangle(3060,2,1))
            relief.add(Triangle(3260,-1,-1))
            relief.add(Triangle(3320,-1,-1))
            relief.add(Triangle(3400,1,1))
            relief.add(Triangle(3560,-1,-1))
            relief.add(Triangle(3660,1,1))
            relief.add(Triangle(3760,-1,-1))
            relief.add(Triangle(3860,1,1))
            relief.add(Triangle(3960,-1,-1))
            relief.add(Triangle(4060,1,1))
            relief.add(Triangle(4105,2,1))
            relief.add(Triangle(4165,2,1))
            relief.add(Triangle(4225,2,1))
            relief.add(Triangle(4310,-1,-1))
            relief.add(Triangle(4370,-1,-1))
            relief.add(Triangle(4670,-1,-1))
            relief.add(Triangle(4870,-1,-1))
            relief.add(Triangle(5070,-1,-1))


        val lay = findViewById<LinearLayout>(R.id.layout_1)

        val draw = Draw(this,relief,Circle(1,20))

        lay.addView(draw)

        but2_1.setOnClickListener{draw.touch = true}
    }
}