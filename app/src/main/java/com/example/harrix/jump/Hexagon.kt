package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import kotlin.math.sqrt

class Hexagon(forma : Int,
              c : Int,
              var r : Int) : Player(3, c) {//r - длина стороны
    override fun drawobject(canvas: Canvas, x: Int, y: Int) {
        super.drawobject(canvas, x, y)
        var paint = Paint()
        //...
    }

    fun check_line(x : Int, y : Int, k : Float, b : Float) : Int{
        if (y.toFloat() > k * x + b) return 1
        return -1
    }


    override fun check(triangle: Triangle, x: Int, y: Int): Boolean {
        super.check(triangle, x, y)

        var p1 = Point(triangle.x - triangle.w, triangle.y)
        var p2 = Point(triangle.x + triangle.w, triangle.y)
        var p3 = Point(triangle.x, triangle.y + triangle.orient * triangle.h)

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

        if(x < triangle.x)
        {
            if (check_line(x, y, k12_, b121) + check_line(x, y, k12_, b122) == 0)
            {
                var d : Float = (k12 * x - y.toFloat() + b12) / sqrt(k12 * k12 + 1)
                if (d <= r.toFloat())
                    return false
                return true
            }
            else
            {
                if(((x.toFloat() - p2.x.toFloat()) * (x.toFloat() - p2.x.toFloat()) +
                                (y.toFloat() - p2.y.toFloat()) * (y.toFloat() - p2.y.toFloat()) <= r * r) ||
                        ((x.toFloat() - p1.x.toFloat()) * (x.toFloat() - p1.x.toFloat()) +
                                (y.toFloat() - p1.y.toFloat()) * (y.toFloat() - p1.y.toFloat()) <= r * r))
                    return false
                return true
            }
        }
        else
        {
            if(check_line(x, y, k23_, b231) + check_line(x, y, k23_, b232) == 0)
            {
                var d : Float = (k23 * x - y.toFloat() + b23) / sqrt(k23 * k23 + 1)
                if (d <= r.toFloat())
                    return false
                return true
            }
            else
            {
                if(((x.toFloat() - p2.x.toFloat()) * (x.toFloat() - p2.x.toFloat()) +
                                (y.toFloat() - p2.y.toFloat()) * (y.toFloat() - p2.y.toFloat()) <= r * r) ||
                        ((x.toFloat() - p3.x.toFloat()) * (x.toFloat() - p3.x.toFloat()) +
                                (y.toFloat() - p3.y.toFloat()) * (y.toFloat() - p3.y.toFloat()) <= r * r))
                    return false
                return true
            }
        }
    }
}