package com.example.harrix.jump

import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_lvl2.*

class Lvl2 : AppCompatActivity() , Scoreable {
    override fun updateScore(score: Int) {
        passive_text_2.text="score: ${score / 10}"
    }

    val coins : ArrayList<Coins> = ArrayList()
    val relief : ArrayList<ObjectRelief> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl2)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

        /*relief.add(ReliefRect(700, -1, 100, 2))
        relief.add(ReliefRect(760, -1, 100,2))
        relief.add(ReliefRect(820, -1, 100,2))
        relief.add(ReliefRect(940, 2, 100,2))
        relief.add(ReliefRect(1000, 2, 100,2))
        relief.add(ReliefRect(1060, 2, 100,2))
        relief.add(ReliefRect(1180, 1, 100, 2))
        relief.add(ReliefRect(1240, 1, 100,2))
        relief.add(ReliefRect(1300, 1, 100,2))
        relief.add(ReliefRect(1420, 3, 100,2))
        relief.add(ReliefRect(1480, 3, 100,2))
        relief.add(ReliefRect(1540, 3, 100,2))
        relief.add(Triangle(880,-1,-1))
        relief.add(Triangle(940,-1,-1))
        relief.add(Triangle(1000,-1,-1))
        relief.add(Triangle(1060,-1,-1))
        relief.add(Triangle(1120,-1,-1))
        relief.add(Triangle(1180,-1,-1))
        relief.add(Triangle(1240,-1,-1))
        relief.add(Triangle(1300,-1,-1))
        relief.add(Triangle(1360,-1,-1))
        relief.add(Triangle(1420,-1,-1))
        relief.add(Triangle(1480,-1,-1))
        relief.add(Triangle(1540,-1,-1))
        relief.add(ReliefRect(1660, 3, 100,2))
        relief.add(ReliefRect(1720, 3, 100,2))
        relief.add(ReliefRect(1960, 3, 100,2))
        relief.add(ReliefRect(2020, 3, 100,2))
        relief.add(ReliefRect(2260, 3, 100,2))
        relief.add(ReliefRect(2320, 3, 100,2))
        relief.add(Triangle(1600,-1,-1))
        relief.add(Triangle(1660,-1,-1))
        relief.add(Triangle(1720,-1,-1))
        relief.add(Triangle(1780,-1,-1))
        relief.add(Triangle(1840,-1,-1))
        relief.add(Triangle(1900,-1,-1))
        relief.add(Triangle(1960,-1,-1))
        relief.add(Triangle(2020,-1,-1))
        relief.add(Triangle(2080,-1,-1))
        relief.add(Triangle(2140,-1,-1))
        relief.add(Triangle(2200,-1,-1))
        relief.add(Triangle(2260,-1,-1))
        relief.add(Triangle(2320,-1,-1))
        relief.add(Triangle(2380,-1,-1))
        relief.add(ReliefRect(2440, 1, 100, 2))
        relief.add(ReliefRect(2500, 1, 100,2))
        relief.add(ReliefRect(2560, 1, 100,2))
        relief.add(ReliefRect(2800, -1, 100, 2))
        relief.add(ReliefRect(2860, -1, 100,2))
        relief.add(ReliefRect(2920, -1, 100,2))
        relief.add(Triangle(2440,-1,-1))
        relief.add(Triangle(2500,-1,-1))
        relief.add(Triangle(2560,-1,-1))
        relief.add(Triangle(2620,-1,-1))
        relief.add(Triangle(2680,-1,-1))
        relief.add(Triangle(2740,-1,-1))
        relief.add(Triangle(2980,-1,-1))
        relief.add(Triangle(3280,1,1))
        relief.add(Triangle(3380,-1,-1))
        relief.add(Triangle(3580,-1,-1))
        relief.add(Triangle(3780,-1,-1))*/
        relief.add(ReliefRect(3960, 4, 100, 2))
        relief.add(ReliefRect(4020, 4, 100,2))
        relief.add(ReliefRect(4080, 4, 100,2))
        relief.add(ReliefRect(4140, 4, 100, 2))
        relief.add(ReliefRect(4200, 4, 100,2))
        relief.add(ReliefRect(4260, 4, 100,2))
        relief.add(Triangle(4370,-1,-1))
        relief.add(Triangle(4430,-1,-1))
        relief.add(Triangle(4730,-1,-1))
        relief.add(Triangle(4930,-1,-1))
        relief.add(ReliefRect(5230, -1, 100,2))
        relief.add(ReliefRect(5290, -1, 100,2))
        relief.add(ReliefRect(5350, -1, 100,2))
        relief.add(Triangle(5410,-1,-1))
        relief.add(Triangle(5470,-1,-1))
        relief.add(ReliefRect(5530, -1, 100,2))
        relief.add(ReliefRect(5590, -1, 100,2))
        relief.add(ReliefRect(5650, -1, 100,2))
        relief.add(Triangle(5710,-1,-1))
        relief.add(Triangle(5770,-1,-1))
        relief.add(Triangle(5970,-1,-1))
        relief.add(Triangle(6170,1,1))
        relief.add(Triangle(6370,-1,-1))








        val lay = findViewById<LinearLayout>(R.id.layout_2)

        val draw = Draw(this,relief,Circle(1,20), coins )

        lay.addView(draw)

        but2_2.setOnClickListener{draw.touch = true}
    }
}
