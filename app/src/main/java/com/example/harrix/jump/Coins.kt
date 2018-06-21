package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Paint

class Coins (x : Int = 0,                         //середина основания
             y : Int = 0,
             h : Int = 100,
             speed : Int = 1,
             forma : Int = 3) : ObjectRelief(3,x, y, speed, h){
    fun drawCoin(x : Int, y : Int, canvas: Canvas){
        var paint = Paint()
        canvas.drawCircle(x.toFloat(), y.toFloat(), 5.toFloat(), paint)
    }
}