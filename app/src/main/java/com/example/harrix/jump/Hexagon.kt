package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import kotlin.math.sqrt

class Hexagon(forma : Int,
              c : Int,
              var a : Int) : Player(3, c) {//r - длина стороны, a - половина
override fun drawobject(canvas: Canvas, x: Int, y: Int) {
    super.drawobject(canvas, x, y)
    var paint = Paint()
    //...
    var y1 = y + (a * sqrt(3f)).toInt()

    var p1 = Point(x - a, y1)
    var p2 = Point(x + a, y1)
    var p3 = Point(x + 2 * a, y1 - (a * sqrt(3f)).toInt())
    var p4 = Point(x + a, y1 - 2 * (a * sqrt(3f)).toInt())
    var p5 = Point(x - a, y1 - 2 * (a * sqrt(3f)).toInt())
    var p6 = Point(x - 2 * a, y1 - (a * sqrt(3f)).toInt())

    canvas.drawLine(p1.x.toFloat(), p1.y.toFloat(), p2.x.toFloat(), p2.y.toFloat(), paint)
    canvas.drawLine(p2.x.toFloat(), p2.y.toFloat(), p3.x.toFloat(), p3.y.toFloat(), paint)
    canvas.drawLine(p3.x.toFloat(), p3.y.toFloat(), p4.x.toFloat(), p4.y.toFloat(), paint)
    canvas.drawLine(p4.x.toFloat(), p4.y.toFloat(), p5.x.toFloat(), p5.y.toFloat(), paint)
    canvas.drawLine(p5.x.toFloat(), p5.y.toFloat(), p6.x.toFloat(), p6.y.toFloat(), paint)
    canvas.drawLine(p6.x.toFloat(), p6.y.toFloat(), p1.x.toFloat(), p1.y.toFloat(), paint)
}

    fun check_line(x : Int, y : Int, k : Float, b : Float) : Int{
        if (y.toFloat() > k * x + b) return 1
        return -1
    }


    override fun check(triangle: Triangle, x: Int, y: Int){//: Boolean {
        super.check(triangle, x, y)

        var r : Int = 2 * a;

        var p1 = Point(triangle.x - triangle.w, triangle.y)
        var p3 = Point(triangle.x + triangle.w, triangle.y)
        var p2 = Point(triangle.x, triangle.y + triangle.orient * triangle.h)

        var k12 : Float = (p1.y - p2.y).toFloat() / (p1.x - p2.x).toFloat()//p1---p2
        var b12 : Float = (p1.x * p2.y - p2.x * p1.y).toFloat() /
                (p1.x - p2.x).toFloat()

        var k12_: Float = -1 / k12;

        var b121 : Float = p1.y.toFloat() - k12_ * p1.x
        var b122 : Float = p2.y.toFloat() - k12_ * p2.x

        var k23 : Float = (p3.y - p2.y).toFloat() / (p3.x - p2.x).toFloat()//p2---p3
        var b23 : Float = (p3.x * p2.y - p2.x * p3.y).toFloat() /
                (p3.x - p2.x).toFloat()

        var k23_ : Float = -1 /  k23;

        var b231 : Float = p1.y.toFloat() - k23_ * p1.x
        var b232 : Float = p2.y.toFloat() - k23_ * p2.x

        if (x < triangle.x)
        {
            if (check_line(x, y, k12_, b121) + check_line(x, y, k12_, b122) == 0)
            {
                var d : Float = (k12 * x - y.toFloat() + b12) * (k12 * x - y.toFloat() + b12) / (k12 * k12 + 1)
                if (d <= r.toFloat() * r.toFloat()){
                    alive = false//return false
                    return
                }
                alive = true//return true
                return
            }
            else
            {
                if(((x.toFloat() - p2.x.toFloat()) * (x.toFloat() - p2.x.toFloat()) +
                                (y.toFloat() - p2.y.toFloat()) * (y.toFloat() - p2.y.toFloat()) <= r * r) ||
                        ((x.toFloat() - p1.x.toFloat()) * (x.toFloat() - p1.x.toFloat()) +
                                (y.toFloat() - p1.y.toFloat()) * (y.toFloat() - p1.y.toFloat()) <= r * r)){
                    alive = false//return false
                    return
                }
                alive = true//return true
                return
            }
        }
        else
        {
            if(check_line(x, y, k23_, b231) + check_line(x, y, k23_, b232) == 0)
            {
                var d : Float = (k23 * x - y.toFloat() + b23) * (k23 * x - y.toFloat() + b23) / (k23 * k23 + 1)
                if (d <= r.toFloat() * r.toFloat()){
                    alive = false//return false
                    return
                }
                alive = true//return true
                return
            }
            else
            {
                if(((x.toFloat() - p2.x.toFloat()) * (x.toFloat() - p2.x.toFloat()) +
                                (y.toFloat() - p2.y.toFloat()) * (y.toFloat() - p2.y.toFloat()) <= r * r) ||
                        ((x.toFloat() - p3.x.toFloat()) * (x.toFloat() - p3.x.toFloat()) +
                                (y.toFloat() - p3.y.toFloat()) * (y.toFloat() - p3.y.toFloat()) <= r * r)){
                    alive = false//return false
                    return
                }
                alive = true//return true
                return
            }
        }
    }
    fun dist(x1 : Int, y1 : Int, x2 : Int, y2 : Int) : Boolean {
        var r : Int = 2 * a
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2) < r * r
    }

    override fun checkRect(rect: ReliefRect, x: Int, y: Int){//: Int {
        super.checkRect(rect, x, y)
        var r : Int = 2 * a
        if (((y >= rect.y - rect.h) && (y <= rect.y) && ((x + r) * 2 >= 2 * rect.x - rect.w) &&
                        ((x + r) * 2 <= 2 * rect.x + rect.w)) ||
                ((y - r <= rect.y) && (2 * (x + r) <= 2 * rect.x + rect.w) && (2 * (x + r) >= 2 * rect.x - rect.w) &&
                        (2 * (x + r) <= 2 * rect.x + rect.w)) ||
                dist(x, y, rect.x - (rect.w / 2).toInt(), rect.y) ||
                dist(x, y, rect.x + (rect.w / 2).toInt(), rect.y) ||
                dist(x, y, (rect.x - rect.w / 2).toInt(), rect.y - rect.h)
        ){
            jumpOnRect = -1//return -1
            return
        }
        if ((y > rect.y - rect.h - r) && (x * 2 > rect.x * 2 - rect.w) && (y < rect.y - rect.h + r) &&
                (x * 2 < rect.x * 2 + rect.w)){
            jumpOnRect = 0//return 0
            return
        }
        jumpOnRect = 1//return 1
        return
    }
}