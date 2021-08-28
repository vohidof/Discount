package Utils

import Model.MyModel
import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDbHelper(context: Context) :
    SQLiteOpenHelper(context, ConstantDb.DB_NAME, null, ConstantDb.TABLE_VERSION),
    DbServiceInterface {
    override fun onCreate(db: SQLiteDatabase?) {
        val query =
            "create table " + ConstantDb.TABLE_NAME + " (" + ConstantDb.ID + " integer not null primary key autoincrement unique, " + ConstantDb.NAME + " text not null unique, " + ConstantDb.NUMBER + " text not null)"
        db?.execSQL(query)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ${ConstantDb.TABLE_NAME}")
        onCreate(db)
    }

    override fun addNotes(myModel: MyModel) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ConstantDb.NAME, myModel.name)
        contentValue.put(ConstantDb.NUMBER, myModel.number)
        database.insert(ConstantDb.TABLE_NAME, null, contentValue)
        database.close()
    }

    override fun addFavourite(myModel: MyModel) {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ConstantDb.NAME, myModel.name)
        contentValue.put(ConstantDb.NUMBER, myModel.number)
        database.insert(ConstantDb.TABLE_NAME, null, contentValue)
        database.close()
    }

    override fun deleteNotes(myModel: MyModel) {
        val database = this.writableDatabase
        database.delete(ConstantDb.TABLE_NAME, "${ConstantDb.ID} = ?", arrayOf("${myModel.id}"))
        database.close()
    }

    override fun updateNotes(myModel: MyModel): Int {
        val database = this.writableDatabase
        val contentValue = ContentValues()
        contentValue.put(ConstantDb.ID, myModel.id)
        contentValue.put(ConstantDb.NAME, myModel.name)
        contentValue.put(ConstantDb.NUMBER, myModel.number)
        return database.update(
            ConstantDb.TABLE_NAME,
            contentValue,
            "${ConstantDb.ID} = ?",
            arrayOf("${myModel.id}")
        )
    }

    override fun getAllNotes(): List<MyModel> {
        val list = ArrayList<MyModel>()
        val query = "select * from ${ConstantDb.TABLE_NAME}"
        val database = this.readableDatabase
        val cursor = database.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(0)
                val name = cursor.getString(1)
                val number = cursor.getString(2)

                val user = MyModel(id, name, number)
                list.add(user)

            } while (cursor.moveToNext())

        }
        return list
    }

}