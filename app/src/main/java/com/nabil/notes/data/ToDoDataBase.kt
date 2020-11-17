package com.nabil.notes.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nabil.notes.model.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDataBase: RoomDatabase() {
    abstract fun toDoData(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDataBase? = null
        fun getDataBase(context: Context): ToDoDataBase{
            val tempInstance = INSTANCE
            if (tempInstance != null ){
                return tempInstance
            }
            synchronized(this){
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        ToDoDataBase::class.java,
                        "todo_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}