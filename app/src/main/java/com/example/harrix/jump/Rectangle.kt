package com.example.harrix.jump

import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Point
import kotlin.math.abs
import kotlin.math.sqrt

class Rectangle(forma : Int,
                c : Int, //color
                var a : Int) : Player(6, c) {//a - половина стороны
    override fun drawobject(canvas: Canvas, x: Int, y: Int) {
        super.drawobject(canvas, x, y)
        var paint = Paint()
        canvas.drawRect((x - a).toFloat(), (y - 2 * a).toFloat(), (x + a).toFloat(), y.toFloat(), paint)
    }

    fun area(p1: Point, p2 : Point, p3 : Point) : Int{
        var x1 : Int = p1.x - p2.x
        var y1 : Int = p1.y - p2.y
        var x2 : Int = p3.x - p2.x
        var y2 : Int = p3.y - p2.y

        return abs(x1 * y2 - x2 * y1)
    }

    fun inTriangle(p1 : Point, p2 : Point, p3 : Point, p : Point) : Boolean{//p1, p2, p3 - треугольник; p4 - вершина квадрата
        if (abs(area(p1, p2, p3) - area(p1, p2, p) - area(p1, p3, p) - area(p2, p3, p)) <= 0.0001)
            return true
        return false
    }

    override fun check(triangle: Triangle, x: Int, y: Int){//: Boolean {
        super.check(triangle, x, y)

        var p1 = Point(triangle.x - triangle.w, triangle.y)
        var p2 = Point(triangle.x + triangle.w, triangle.y)
        var p3 = Point(triangle.x, triangle.y + triangle.orient * triangle.h)

        var pr1 = Point(x - a, y)
        var pr2 = Point(x + a, y)
        var pr3 = Point(x - a, y - 2 * a)
        var pr4 = Point(x + a, y - 2 * a)

        if (inTriangle(p1, p2, p3, pr1) || inTriangle(p1, p2, p3, pr2) ||
                inTriangle(p1, p2, p3, pr3) || inTriangle(p1, p2, p3, pr4)){
            alive = false//return false
            return
        }
        alive = true//return true
    }
}