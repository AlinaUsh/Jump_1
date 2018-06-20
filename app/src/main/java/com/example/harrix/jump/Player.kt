package com.example.harrix.jump

import android.graphics.Canvas

open class Player (var forma : Int,
              var c : Int) //color
{
   // var speedx : Int = 0
 //   var speedy : Int = 0
    var x : Int = 0
    var y : Int = 0

    open fun drawobject(canvas: Canvas, x: Int, y : Int){}//прорисовка

    open fun check(triangle: Triangle, x : Int, y : Int): Boolean {
        when (forma) {
            4 -> return (this as Circle).check(triangle,x,y)
            else -> return false
        }
    }// для 1 треугольника
    // true - прошли
    // false - убились

    open fun checkRect(rect: ReliefRect, x: Int, y : Int): Int{
        when (forma) {
            4 -> return (this as Circle).checkRect(rect, x, y)
            else -> return 0
        }
    }

    open fun check(objectRelief: ObjectRelief): Int{
        when (objectRelief.forma) {
            1 -> return if(check((objectRelief as Triangle), this.x, this.y)) 0 else 1
            2 -> return checkRect((objectRelief as ReliefRect), this.x, this.y)
            else -> return 0
        }
    }
}