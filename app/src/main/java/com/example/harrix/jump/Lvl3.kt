package com.example.harrix.jump

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_lvl3.*

class Lvl3 : AppCompatActivity() , Scoreable {
    override fun updateScore(score: Int) {
        passive_text_3.text="score: ${score / 10}"
    }

    val coins : ArrayList<Coins> = ArrayList()
    val relief : ArrayList<ObjectRelief> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl3)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

        relief.add(ReliefRect(700, -1, 100, 2))
        relief.add(ReliefRect(760, -1, 100,2))
        relief.add(ReliefRect(820, -1, 100,2))
        relief.add(Triangle(900,2,1))
        relief.add(Triangle(960,2,1))
        relief.add(Triangle(1020,2,1))
        relief.add(ReliefRect(1240, 4, 50, 2))
        relief.add(ReliefRect(1300, 4, 50,2))
        relief.add(ReliefRect(1360, 4, 50,2))
        relief.add(Triangle(1210,-1,-1))
        relief.add(Triangle(1270,-1,-1))
        relief.add(Triangle(1330,-1,-1))
        relief.add(Triangle(1390,-1,-1))
        relief.add(Triangle(1240,4,1))
        relief.add(Triangle(1300,4,1))
        relief.add(Triangle(1360,4,1))
        relief.add(ReliefRect(1480, 4, 50, 2))
        relief.add(ReliefRect(1540, 4, 50,2))
        relief.add(ReliefRect(1600, 4, 50,2))
        relief.add(Triangle(1450,-1,-1))
        relief.add(Triangle(1710,-1,-1))
        relief.add(Triangle(1970,-1,-1))
        relief.add(ReliefRect(2270, -1, 70, 2))
        relief.add(ReliefRect(2330, -1, 70,2))
        relief.add(ReliefRect(2390, -1, 70,2))
        relief.add(Triangle(2460,2,1))
        relief.add(ReliefRect(2590, -1, 70, 2))
        relief.add(ReliefRect(2650, -1, 70,2))
        relief.add(ReliefRect(2710, -1, 70,2))
        relief.add(Triangle(2790,-1,-1))
        relief.add(Triangle(2910,2,1))
        relief.add(Triangle(3030,-1,-1))
        relief.add(Triangle(3150,2,1))
        relief.add(Triangle(3270,-1,-1))
        relief.add(ReliefRect(3570, -1, 120, 2))
        relief.add(ReliefRect(3630, -1, 120,2))
        relief.add(ReliefRect(3690, -1, 120,2))
        relief.add(ReliefRect(3790, 2, 100, 2))
        relief.add(ReliefRect(3850, 2, 100,2))
        relief.add(ReliefRect(3910, 2, 100,2))
        relief.add(ReliefRect(4010, 1, 100, 2))
        relief.add(ReliefRect(4070, 1, 100,2))
        relief.add(ReliefRect(4130, 1, 100,2))
        relief.add(Triangle(3750,-1,-1))
        relief.add(Triangle(3810,-1,-1))
        relief.add(Triangle(3870,-1,-1))
        relief.add(Triangle(3930,-1,-1))
        relief.add(Triangle(3990,-1,-1))
        relief.add(Triangle(4050,-1,-1))
        relief.add(Triangle(4110,-1,-1))
        relief.add(Triangle(4170,-1,-1))
        relief.add(Triangle(4230,-1,-1))
        relief.add(Triangle(4290,-1,-1))






        val lay = findViewById<LinearLayout>(R.id.layout_3)

        val draw = Draw(this,relief,Circle(1,20), coins )

        lay.addView(draw)

        but2_3.setOnClickListener{draw.touch = true}
    }
}
