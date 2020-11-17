package com.nabil.notes.adapter

import androidx.recyclerview.widget.DiffUtil
import com.nabil.notes.model.ToDoData

class ToDoDiffUtil(private val oldlist: List<ToDoData>, private val newList: List<ToDoData>): DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldlist.size
    }

    override fun getNewListSize(): Int {
      return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldlist[oldItemPosition] === newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
       return oldlist[oldItemPosition].id == newList[newItemPosition].id
               && oldlist[oldItemPosition].title == newList[newItemPosition].title
               && oldlist[oldItemPosition].description == newList[newItemPosition].description
               && oldlist[oldItemPosition].priority == newList[newItemPosition].priority

    }


}