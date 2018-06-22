package com.example.harrix.jump

import android.content.Intent
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

        relief.add(Triangle(150,0,1))
        relief.add(Triangle(210,0,1))
        relief.add(ReliefRect(500,200,100))
        relief.add(ReliefRect(560,200,100))
        relief.add(ReliefRect(620,200,100))
        relief.add(Triangle(700,0,1))
        relief.add(Triangle(850,-1,-1))
        relief.add(Triangle(1020,-1,-1))
        relief.add(Triangle(1200,0,1))
        relief.add(ReliefRect(1400,500,50))
        relief.add(ReliefRect(1460,500,50))
        relief.add(ReliefRect(1400,800,50))
        relief.add(ReliefRect(1460,800,50))
        relief.add(Triangle(1600,480,-1))
        relief.add(Triangle(1700,-1,-1))
        relief.add(Triangle(1760,-1,-1))
        relief.add(Triangle(2100,0,1))
        relief.add(Triangle(2300,-1,-1))
        relief.add(Triangle(2360,-1,-1))
        relief.add(Triangle(2420,-1,-1))

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