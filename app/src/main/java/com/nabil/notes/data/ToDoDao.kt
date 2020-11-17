package com.nabil.notes.data

import androidx.lifecycle.LiveData
import androidx.room.*
import com.nabil.notes.model.ToDoData

@Dao
interface ToDoDao {
    @Query("SELECT * FROM todo_table ORDER BY id ASC")
    fun getAllData(): LiveData<List<ToDoData>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertData(toDoData: ToDoData)

    @Update
    suspend fun updateData(toDoData: ToDoData)

    @Delete
    suspend fun deleteData(toDoData: ToDoData)

    @Query("DELETE FROM todo_table ")
    suspend fun deleteAll()

    @Query("SELECT * FROM todo_table WHERE title LIKE :searchQuery")
     fun searchDataBase(searchQuery : String): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'H%' THEN 1 WHEN priority Like 'M%' THEN 2 WHEN priority Like 'L%' THEN 3 END")
    fun sortByHighPriority(): LiveData<List<ToDoData>>

    @Query("SELECT * FROM todo_table ORDER BY CASE WHEN priority LIKE 'L%' THEN 1 WHEN priority Like 'M%' THEN 2 WHEN priority Like 'H%' THEN 3 END")
    fun sortByLowPriority(): LiveData<List<ToDoData>>

}