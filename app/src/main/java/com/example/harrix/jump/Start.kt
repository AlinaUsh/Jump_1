package com.example.harrix.jump

import android.content.Intent
import android.graphics.Point
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_start.*

class Start : AppCompatActivity(), Scoreable {
    override fun updateScore(score: Int) {
        passive_text.text="score: ${score / 10}"
    }

    val coins : ArrayList<Coins> = ArrayList()
    val relief : ArrayList<ObjectRelief> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val display = windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width = size.x

        for (counter in 0..20) {
            relief.add(Triangle(550 + width * counter, -1, -1))
            relief.add(ReliefRect(1000 + width * counter, -1, 130, 2))
            relief.add(ReliefRect(1060 + width * counter, -1, 130,2))
            relief.add(ReliefRect(1120 + width * counter, -1, 130,2))
            relief.add(ReliefRect(1180 + width * counter, 2, 100,2))
            relief.add(ReliefRect(1240 + width * counter, 2, 100,2))
            relief.add(ReliefRect(1300 + width * counter, 2, 100,2))
            relief.add(Triangle(1420 + width * counter, -1, -1))
            relief.add(Triangle(1620 + width * counter, -1, -1))
            relief.add(Triangle(1840 + width * counter, -1, -1))
            relief.add(Triangle(1900 + width * counter, 1, 1))
            relief.add(Triangle(1960 + width * counter, 1, 1))
            relief.add(ReliefRect(2100 + width * counter, -1, 50,2))
            relief.add(ReliefRect(2350 + width * counter, -1, 50,2))
            relief.add(ReliefRect(2600 + width * counter, -1, 50,2))
            relief.add(ReliefRect(2660 + width * counter, -1, 50,2))
            relief.add(ReliefRect(2720 + width * counter, 2, 50,2))
            relief.add(ReliefRect(2780 + width * counter, 2, 50,2))
        }


        val lay = findViewById<LinearLayout>(R.id.layout)
        val player = Circle(1,20)

        val draw = Draw(this, relief, player, coins)

        lay.addView(draw)

        if(!player.alive){
            val intent = Intent ( this, Lose::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            startActivity(intent)
        }

        but2.setOnClickListener{draw.touch = true}
        //при нажатии на but1 deleteRelief = true
        but1.setOnClickListener{draw.deleteRelief = true}
    }

}