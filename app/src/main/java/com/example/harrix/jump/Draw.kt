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
    var dy: Int = 5//разница в высоте между перерисовками
    var deleteRelief = false

    var dh: Int = 0//изменение высоты
    var hOfJump: Int = 260//высоты прыжка

    var x = -2
    val dx = 50
    var y = -1
    var l = 0
    var y0 = -1

    var something : Int = 0
    var lastRectXR : Int = -1//правый край последнего прямоугольника платформы
    var nextOnTheSameH : Boolean = false//последний ли прямоугольник платформы
    var sinceLastShot : Int = 0
    var firstJumpOn = true

    //обьявляем игрока

    override fun onDraw(canvas: Canvas?) {

        super.onDraw(canvas)
        if (canvas == null) return
        var y0 = canvas.height
        if (x == -2) x = canvas.width
        if (y == -1) y = canvas.height
        if (player.y == -100){
            player.y = canvas.height - player.r
            player.highbottom = canvas.height
        }
        if (player.x == 0) player.x = canvas.width/4


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
                    relief[i].y = canvas.height/5*4
                if (relief[i].y == 4)
                    relief[i].y = canvas.height/7*6
                drawTriangle(relief[i] as Triangle)
            }
            if(relief[i].forma == 2){
                if (relief[i].y == -1)
                    relief[i].y = canvas.height
                if (relief[i].y == 1)
                    relief[i].y = canvas.height/3*2
                if (relief[i].y == 2)
                    relief[i].y = canvas.height/5*4
                if (relief[i].y == 3)
                    relief[i].y = canvas.height/2
                if (relief[i].y == 4)
                    relief[i].y = canvas.height/7*6
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
            if (player.y + player.speedy + player.r <= player.highbottom - hOfJump)
                player.speedy *= -1
            player.y += player.speedy
           // dh += dy
           // paint.color = Draw.color
           // player.drawobject(canvas,canvas.width/4 + player.x, canvas.height - player.y - player.r)
           // canvas.drawCircle((canvas.width / 2).toFloat(), (y0 - dh - 10).toFloat(), 20f, paint)
            //отрисовка объекта
            //проверка препядствий
            if (player.y + player.r >= canvas.height) {
                touch = false
                player.speedy = -5//dy = 3
                player.highbottom = canvas.height
                player.y = canvas.height - player.r//player.highbottom
            }
        } else {
           // paint.color = Draw.color
            //dy = 3
           // player.drawobject(canvas,canvas.width/4 + player.x, canvas.height - player.y - player.r)
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
                    deleteRelief = false
                    break
                }
            deleteRelief = false
        }

        if((player.highbottom < canvas.height) && (player.x >= lastRectXR) && (!touch)){
            touch = true
            player.speedy = player.speedyabs
        }

      /*  if ((player.x >= lastRectXR) && (touch == false) &&
                (nextOnTheSameH == false) && (0 != player.y) && (player.highbottom < canvas.height)){//доехали до клнца платформы
            player.speedy = player.speedyabs//dy = -3
        }else if(player.x >= lastRectXR) {
              nextOnTheSameH = false

        }*/

        for (i in 0..this.coins.size - 1)
            player.getCoin(coins[i].x, coins[i].y)

        for (i in 0..this.relief.size-1){
            if (relief[i].forma == 1){
                player.check((relief[i] as Triangle),player.x , player.y)
            }
            else{
                if ((lastRectXR > relief[i].x - relief[i].w)&&(lastRectXR < relief[i].x + relief[i].w)&&
                        (relief[i].y == player.highbottom))
                    lastRectXR = relief[i].x + relief[i].w
                player.checkRect((relief[i] as ReliefRect), player.x , player.y)
            }
            if(player.jumpOnRect && firstJumpOn){
                something = i
                lastRectXR = relief[i].x + relief[i].w
                firstJumpOn = false
            }
          //  if ((lastRectXR > player.x) && (relief[i].forma == 2) && (relief[i].x - 15 == lastRectXR))
          //      nextOnTheSameH = true
        }

        if(player.jumpOnRect){
            touch = false
            player.highbottom = relief[something].y - relief[something].h
            player.y = player.highbottom - player.r
            player.jumpOnRect = false
            player.speedy = -player.speedyabs
            firstJumpOn = true
         //   dh = 0
            touch = false
        }

        paint.color = Draw.color
        player.drawobject(canvas,player.x , player.y)

        if(player.alive)
            invalidate()
        l++

        if(context is Scoreable) {
            (context as Scoreable).updateScore(l)
        }
    }
}