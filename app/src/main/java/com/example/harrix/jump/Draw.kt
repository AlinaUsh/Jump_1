package com.example.harrix.jump

import android.content.Context
import android.graphics.*
import android.graphics.Color
import android.view.View
import java.util.*

class Draw (context : Context,var relief : ArrayList<ObjectRelief>) : View(context) {
    companion object {
        var color = Color.BLUE
    }

    var touch = false
    var dy: Int = 2//разница в высоте между перерисовками
    var dh: Int = 0//изменение высоты
    var hOfJump: Int = 275//высоты прыжка

    var x = -2
    val dx = 50
    var y = -1
    var l = 0

    //обьявляем игрока

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

        fun drawReliefRect(rect: ReliefRect){
            canvas.drawRect(Rect(rect.x-rect.w,rect.y - rect.h,rect.x + rect.w,rect.y),paint)
        }

        for (i in 0..this.relief.size-1){
            this.relief[i].x -= this.relief[i].speed
            if (relief[i].forma == 1) {
                if (relief[i].y == -1)
                    relief[i].y = canvas.height
                drawTriangle(relief[i] as Triangle)
            }
            if(relief[i].forma == 2){
                drawReliefRect(relief[i] as ReliefRect)
            }
        }
        //x -= dx

        if (touch) {
            dh += dy;
            if (dh >= hOfJump)
                dy *= -1
            paint.color = color
            canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - dh - 10).toFloat(), 20f, paint)
            //отрисовка объекта
            //проверка препядствий
            if (dh == 0) {
                touch = false
                dy = 3
            }
        } else {
            paint.color = color
            dy = 3
            canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - 10).toFloat(), 20f, paint)
            //отрисовка объекта
        }

        //
        /*for (i in 0..this.relief.size){
            if (relief[i].forma == 1){

            }
            else{

            }
        }*/
        //

        invalidate()
        l++

        if(context is Scoreable) {
            (context as Scoreable).updateScore(l)
        }


    }
}