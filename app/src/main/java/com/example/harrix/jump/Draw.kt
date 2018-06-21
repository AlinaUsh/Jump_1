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
    var dy: Int = 5//разница в высоте между перерисовками
    var dh: Int = 0//изменение высоты
    var hOfJump: Int = 250//высоты прыжка

    var x = -1
    var y = -1

    //обьявляем игрока

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        if (canvas == null) return
        var y0 = canvas.height
        if (x == -1) x = canvas.width
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
                if (relief[i].y == 1)
                    relief[i].y = canvas.height/3*2
                if (relief[i].y == 2)
                    relief[i].y = canvas.height/6*5
                drawTriangle(relief[i] as Triangle)
            }
            if(relief[i].forma == 2){

                if (relief[i].y == -1)
                    relief[i].y = canvas.height
                drawReliefRect(relief[i] as ReliefRect)
            }
        }
        //x -= dx

        if (touch) {
            dh += dy;
            if (dh >= hOfJump)
                dy *= -1
            paint.color = Draw.color
            canvas.drawCircle((canvas.width / 4).toFloat(), (y0 - dh - 10).toFloat(), 20f, paint)
            //отрисовка объекта
            //проверка препядствий
            if (dh == 0) {
                touch = false
                dy = 5
            }
        } else {
            paint.color = Draw.color
            dy = 5
            canvas.drawCircle((canvas.width / 4).toFloat(), (y0 - 10).toFloat(), 20f, paint)
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
    }
}