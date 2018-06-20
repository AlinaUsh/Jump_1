package com.example.harrix.jump


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.database.sqlite.SQLiteQueryBuilder
import android.os.Bundle
import android.provider.ContactsContract
import android.util.Log
import java.util.*


class totalresult{
    val totalId:Int
    val total_res:Int
    val total_lId:Int
    val total_time:Int
    constructor(res:Int, time:Int,id:Int=0,levelId: Int){
        totalId=id
        total_time=time
        total_res=res
        total_lId=levelId
    }
}
class TotalDatabaseHandler2: SQLiteOpenHelper {
    companion object {
        val Tag= "DatabaseHandler2"
        val DBName="Total"
        val DBVersion=1
        val totalId="id"
        val totalres="tres"
        val tableName="tRes"
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
                "(" +totalId+" INTEGER PRIMARY KEY, "+totalres+" INTEGER, "+ totallId+" INTEGER );"
        p0!!.execSQL(sql1)
    }

    fun AddFig(levelId:Int,res: Int): String {
        val pr=ContentValues()

        pr.put(TotalDatabaseHandler2.totalres,res)
        pr.put(TotalDatabaseHandler2.totallId,levelId)

        var Msg: String = "error";
        val ID = sqlObj!!.insert(tableName, "", pr)
        if (ID > 0) {
            Msg = "ok"
        }
        return Msg
    }
    fun FetchFigs(keyword:String, isAllFigs:Boolean): ArrayList<totalresult> {
        var arraylist = ArrayList<totalresult>()
        val sqb = SQLiteQueryBuilder()
        sqb.tables = tableName
        val cols = arrayOf(totalId, totallId, totalres, totaltime)
        val rowSelArg = arrayOf(keyword)
        val cur: Cursor

        if (isAllFigs) {
            cur = sqb.query(sqlObj, cols, null, null, null, null, "ttime")
        } else {
            cur = sqb.query(sqlObj, cols, "lId like ?", rowSelArg, null, null, "ttime")
        }

        if (cur.moveToFirst()) {
            do {
                val id = cur.getInt(cur.getColumnIndex(totalId))
                val res = cur.getInt(cur.getColumnIndex(totalres))
                val time = cur.getInt(cur.getColumnIndex(totaltime))
                val lId = cur.getInt(cur.getColumnIndex(totallId))

                arraylist.add(totalresult( res, time, id, lId))
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