package com.example.harrix.jump

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Point
import android.view.View
import java.util.*

class Draw (context : Context, relief : ArrayList<Triangle>) : View(context) {
    companion object {
        var color = Color.BLUE
    }

    var touch = false
    var dy: Int = 2//разница в высоте между перерисовками
    var dh: Int = 0//изменение высоты
    var hOfJump: Int = 150//высоты прыжка

    var x = -2
    val dx = 2
    var y = -1

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        if (canvas == null) return
        var y0 = canvas.height
        if (x == -2) x = canvas.width
        if (y == -1) y = canvas.height


        var paint = Paint()


        fun drawTriangle(triangle: Triangle) {
            val p1: Point = Point(triangle.x - triangle.w, triangle.y)
            val p2 = Point(triangle.x + triangle.w, triangle.y)
            val p3 = Point(triangle.x, triangle.y + triangle.orient * triangle.h)
            canvas.drawLine(p1.x.toFloat(), p1.y.toFloat(), p2.x.toFloat(), p2.y.toFloat(), paint)
            canvas.drawLine(p1.x.toFloat(), p1.y.toFloat(), p3.x.toFloat(), p3.y.toFloat(), paint)
            canvas.drawLine(p2.x.toFloat(), p2.y.toFloat(), p3.x.toFloat(), p3.y.toFloat(), paint)

        }
        x -= dx
        drawTriangle(Triangle(x, y, -1))

        if (touch) {
            dh += dy;
            if (dh >= hOfJump)
                dy *= -1
            paint.color = Draw.color
            canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - dh - 10).toFloat(), 20f, paint)
            //отрисовка объекта
            //проверка препядствий
            if (dh == 0) {
                //dy *= -1
                touch = false
            }
        } else {
            paint.color = Draw.color
            dy = 2
            canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - 10).toFloat(), 20f, paint)
            //отрисовка объекта
        }
        invalidate()
    }
}
