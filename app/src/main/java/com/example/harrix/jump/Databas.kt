package com.example.harrix.jump


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.support.v4.content.res.FontResourcesParserCompat
import android.util.Log


class totalfig{
    val totalId:Int
    val total_fig:String
    val total_x:Int
    val total_y:Int
    val total_h:Int
    val total_dx:Int
    val total_time:Int
    constructor(fig: String,x: Int,y: Int,h: Int,dx: Int, time:Int,id:Int=0,levelId: Int){
        totalId=id
        total_fig=fig
        total_x=x
        total_y=y
        total_h=h
        total_dx=dx
        total_time=time
    }
}
class TotalDatabaseHandler: SQLiteOpenHelper {
    companion object {
        val Tag= "DatabaseHandler"
        val DBName="Total"
        val DBVersion=1
        val totalId="id"
        val tableName="tMap"
        val totalfig="tfig"
        val totalx="tx"
        val totaly="ty"
        val totalh="th"
        val totaldx="tdx"
        val totaltime="ttime"
        val totallId="lid"
    }

    var context: Context?=null
    lateinit var sqlObj:SQLiteDatabase
    constructor(context: Context):super(context,DBName,null, DBVersion){
        this.context=context
        sqlObj=this.writableDatabase
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0!!.execSQL("Drop table IF EXISTS " + tableName)
        onCreate(p0)
    }

    override fun onCreate(p0: SQLiteDatabase?) {
        Log.d("DB", "created")
        var sql1:String="CREATE TABLE IF NOT EXISTS "+ tableName+" "+
                "(" + totallId+", "+totalId+" INTEGER PRIMARY KEY, "+totalfig+" TEXT, "+
                totalx+" INTEGER, "+ totaly+" INTEGER, "+totalh+" INTEGER, "+totaldx+" INTEGER );"
        p0!!.execSQL(sql1)
    }

    fun AddFig(fig:String,x:Int,y:Int,h:Int,dx:Int,levelId:Int): String {
        val pr=ContentValues()

        pr.put(TotalDatabaseHandler.totalfig,fig)
        pr.put(TotalDatabaseHandler.totalx,x)
        pr.put(TotalDatabaseHandler.totaly,y)
        pr.put(TotalDatabaseHandler.totalh,h)
        pr.put(TotalDatabaseHandler.totaldx,dx)
        pr.put(TotalDatabaseHandler.totallId,levelId)

        var Msg: String = "error";
        val ID = sqlObj!!.insert(tableName, "", pr)
        if (ID > 0) {
            Msg = "ok"
        }
        return Msg
    }
    fun FetchFigs(keyword:String, isAllFigs:Boolean):ArrayList<totalfig> {
        var arraylist = ArrayList<totalfig>()
        val sqb = SQLiteQueryBuilder()
        sqb.tables = tableName
        val cols = arrayOf(totalId, totallId, totalfig, totalx, totaly, totalh, totaldx, totaltime)
        val rowSelArg = arrayOf(keyword)
        val cur: Cursor

        if (isAllFigs) {
            cur = sqb.query(sqlObj, cols, null, null, null, null, "ttime")
        } else {
            cur = sqb.query(sqlObj, cols, "tname like ?", rowSelArg, null, null, "ttime")
        }

        if (cur.moveToFirst()) {
            do {
                val id = cur.getInt(cur.getColumnIndex(totalId))
                val fig: String = cur.getString(cur.getColumnIndex(totalfig))
                val x = cur.getInt(cur.getColumnIndex(totalx))
                val y = cur.getInt(cur.getColumnIndex(totaly))
                val h = cur.getInt(cur.getColumnIndex(totalh))
                val dx = cur.getInt(cur.getColumnIndex(totaldx))
                val time = cur.getInt(cur.getColumnIndex(totaltime))
                val lId = cur.getInt(cur.getColumnIndex(totallId))

                arraylist.add(totalfig(fig, x, y, h, dx, time, id, lId))
            } while (cur.moveToNext())

            var count: Int = arraylist.size
        }
        return arraylist
    }

        fun UpdateFig(values: ContentValues,id:Int):String{
            var selectionArs= arrayOf(id.toString())
            var i=sqlObj!!.update(tableName,values,"id=?",selectionArs)
            if(i>0){
                return "ok"
            }else{
                return "error"
            }
        }

        fun RemoveFig(id:Int):String{
            var selectionArs=arrayOf(id.toString())
            val i=sqlObj!!.delete(tableName,"id=?",selectionArs)
            if(i>0){
                return "ok"
            }else{
                return "error"
            }
        }


}