package com.example.harrix.jump

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.view.View

class Draw (context : Context) : View(context){

    var touch = false
    /*var dy : Int = 2//разница в высоте между перерисовками
    var dh : Int = 0//изменение высоты*/


    var x = 50
    val dx = 2

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)

        if (canvas == null) return

        var paint = Paint()


        fun drawTriangle(triangle: Triangle){
            val p1 : Point = Point(triangle.x-triangle.w,triangle.y)
            val p2 = Point(triangle.x + triangle.w,triangle.y)
            val p3 = Point(triangle.x,triangle.y + triangle.orient*triangle.h)
            canvas.drawLine(p1.x.toFloat(),p1.y.toFloat(),p2.x.toFloat(),p2.y.toFloat(),paint)
            canvas.drawLine(p1.x.toFloat(),p1.y.toFloat(),p3.x.toFloat(),p3.y.toFloat(),paint)
            canvas.drawLine(p2.x.toFloat(),p2.y.toFloat(),p3.x.toFloat(),p3.y.toFloat(),paint)
        }

        x += dx

        if(touch) {
            drawTriangle(Triangle(x, x, 1))
            touch = false
        }else {
            drawTriangle(Triangle(x, 0, 1))

        }
        /*if (touch) {
            y += dy;
        }*/
        invalidate()

    }

}