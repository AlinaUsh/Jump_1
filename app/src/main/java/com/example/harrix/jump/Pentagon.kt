package com.example.harrix.jump

import android.content.Intent
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import android.support.v4.content.ContextCompat.startActivity
import kotlin.math.sqrt
import kotlin.system.exitProcess

class Pentagon (forma : Int,
              c : Int, //color
              var a : Int) :  Player (2 , c){//a - половина основания

    override fun drawobject(canvas: Canvas, x: Int, y: Int) {
        super.drawobject(canvas, x, y)
        var paint = Paint()
        //paint.color =
        //как несколько треугольников
    }

    fun check_line(x : Int, y : Int, k : Float, b : Float) : Int{
        if (y.toFloat() > k * x + b) return 1
        return -1
    }

    override fun check(triangle: Triangle, x: Int, y: Int): Boolean {
        super.check(triangle, x, y)

        var r : Float = 1f
        //считать из стороны

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

        if (x < triangle.x)
        {
            if (check_line(x, y, k12_, b121) + check_line(x, y, k12_, b122) == 0)
            {
                var d : Float = (k12 * x - y.toFloat() + b12) * (k12 * x - y.toFloat() + b12) / (k12 * k12 + 1)
                if (d * d <= r.toFloat() * r.toFloat())
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
                var d : Float = (k23 * x - y.toFloat() + b23) * (k23 * x - y.toFloat() + b23) / (k23 * k23 + 1)
                if (d * d <= r.toFloat() * r.toFloat())
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