package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Paint

class Circle ( forma : Int,
               c : Int, //color
               var r : Int) :  Player ( forma , c){

    override fun drawobject(canvas: Canvas, x: Int, y: Int) {
        super.drawobject(canvas, x, y)

        var paint = Paint()


        canvas.drawCircle(x.toFloat(),y.toFloat(),r.toFloat(),paint)
    }

}