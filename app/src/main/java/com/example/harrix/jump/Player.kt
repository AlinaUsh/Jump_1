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
     return true
    }// для 1 треугольника
    // true - прошли
    // false - убились
}