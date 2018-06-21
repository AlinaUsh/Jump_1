package com.example.harrix.jump


import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_lvl1.*

class Lvl1 : AppCompatActivity() , Scoreable, Shotable {
    override fun updateScore(score: Int) {
        passive_text.text="score: ${score / 10}"
    }
    var a:Int=0
    var b:Int=0
    override fun updatePos(x: Int, y: Int) {
        a=x
        b=y
    }

    val relief : ArrayList<ObjectRelief> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lvl1)

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
        relief.add(Triangle(2860,-1,-1))
        relief.add(Triangle(3000,2,1))
        relief.add(Triangle(3060,2,1))

        val lay = findViewById<LinearLayout>(R.id.layout_1)
        var l:Int=0
        val draw = Draw(this,relief,Circle(1,20))
        var k:Int=-1
        lay.addView(draw)
        but1_1.setOnClickListener{
            if(a>=l+100) {
                k = -1
                for (i in 0..this.relief.size - 1) {
                    if ((this.relief[i].x > a)&&((i<k)||(k==-1))){
                        k=i
                    }
                    if(k!=-1){
                        this.relief[k].x=0
                    }
                }
            }
        }
        but2_1.setOnClickListener{draw.touch = true}
    }
}