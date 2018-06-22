package com.example.harrix.jump

import android.content.Context
import android.graphics.*
import android.graphics.Color
import android.view.View
import java.util.*
import kotlin.collections.ArrayList

class Draw (context : Context,var relief : ArrayList<ObjectRelief>, var player: Player, var coins: ArrayList<Coins>) : View(context) {
    companion object {
        var color = Color.BLUE
    }


    var touch = false
    var deleteRelief = false

    var dy: Int = 3//разница в высоте между перерисовками
    var dh: Int = 0//изменение высоты
    var hOfJump: Int = 300//высоты прыжка

    var x = -2
    val dx = 50
    var y = -1
    var l = 0
    var y0 = -1

    var something : Int = 0
    var lastRectXR : Int = -1//правый край последнего прямоугольника платформы
    var nextOnTheSameH : Boolean = false//последний ли прямоугольник платформы
    var sinceLastShot : Int = 0

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
        for (i in 0..this.coins.size - 1)
            this.coins[i].x -= this.coins[i].speed

        lastRectXR -= relief[0].speed
        sinceLastShot += relief[0].speed

        //x -= dx


        if (touch) {
            //dh += dy;
            if (player.y + dy >= player.highbottom + hOfJump)
                dy *= -1
            player.y += dy
            dh += dy
            paint.color = Draw.color
            player.drawobject(canvas,canvas.width/4 + player.x, canvas.height - player.y - player.r)
           // canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - dh - 10).toFloat(), 20f, paint)
            //отрисовка объекта
            //проверка препядствий
            if (player.y <= 0) {
                touch = false
                dy = 3
                player.highbottom = 0
                player.y = 0//player.r//player.highbottom
            }
        } else {
            paint.color = Draw.color
            dy = 3
            player.drawobject(canvas,canvas.width/4 + player.x, canvas.height - player.y - player.r)
           // canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - 10).toFloat(), 20f, paint)
            //отрисовка объекта
        }

        if ((deleteRelief) && (sinceLastShot >= 100))
        {
            y0 = y
            for (i in 0..this.relief.size - 1)
                if ((relief[i].y >= y0) && (relief[i].y - relief[i].h <= y0) &&
                        (relief[i].x > x)){
                    //удалить элемент
                    relief[i].x = -1000
                    sinceLastShot = 0
                    break
                }
            deleteRelief = false
        }

        if ((player.x == lastRectXR) && (touch == false) &&
                (nextOnTheSameH == false) && (0 != player.y)){//доехали до клнца платформы
            dy = -3
        }else if(player.x == lastRectXR)
            nextOnTheSameH = false

        for (i in 0..this.coins.size - 1)
            player.getCoin(coins[i].x, coins[i].y)

        for (i in 0..this.relief.size-1){
            if (relief[i].forma == 1){
                player.check((relief[i] as Triangle),player.x + canvas.width/4,canvas.height - player.y - player.r)
            }
            else{
                player.checkRect((relief[i] as ReliefRect), player.x + canvas.width/4,canvas.height - player.y - player.r)
            }
            if(player.jumpOnRect){
                something = i
                lastRectXR = relief[i].x + 15
            }
            if ((lastRectXR > player.x) && (relief[i].forma == 2) && (relief[i].x - 15 == lastRectXR))
                nextOnTheSameH = true
        }

        if(player.jumpOnRect){
            touch = false
            player.highbottom = canvas.height - relief[something].y + relief[something].h + 1
            player.y = player.highbottom// + player.r
            player.jumpOnRect = false
            dy = 3
            dh = 0
            touch = false
        }

        if(player.alive)
            invalidate()
        l++

        if(context is Scoreable) {
            (context as Scoreable).updateScore(l)
        }
    }
}