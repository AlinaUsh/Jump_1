package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Rect

open class Player (var forma : Int,
              var c : Int) //color
{
   // var speedx : Int = 0
   // var speedy : Int = 0
    var sum : Int = 0
    var x : Int = 10
    var y : Int = 10
    var alive : Boolean = true
    var jumpOnRect : Int = 1

    open fun drawobject(canvas: Canvas, x: Int, y : Int){}//прорисовка

    open fun check(triangle: Triangle, x : Int, y : Int){//: Boolean {
     //return true
    }// для 1 треугольника
    // true - прошли
    // false - убились

   open fun checkRect(rect: ReliefRect, x : Int, y : Int){//: Int {
    //return 1
    }// для 1 прямоугольника
    // 1 - прошли
    // -1 - убились
    // 0 - запрыгнули

    fun getCoin(x1 : Int, y1 : Int){//координаты монетки
        if ((x == x1) && (y == y1))
            this.sum += 10//удаляем монетку из массива рельефа
    }
}
