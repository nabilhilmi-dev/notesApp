package com.nabil.notes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.nabil.notes.model.ToDoData
import com.nabil.notes.databinding.RowLayoutBinding

class ListAdapter: RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    var datalist = emptyList<ToDoData>()

    class MyViewHolder(private val binding: RowLayoutBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(toDoData: ToDoData){
            binding.toDoData = toDoData
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ListAdapter.MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = RowLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder.from(parent)

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
       val currentItem = datalist[position]
        holder.bind(currentItem)
    }

    override fun getItemCount(): Int = datalist.size

    fun setData(toDoData: List<ToDoData>){
        val toDoDiffUtil = ToDoDiffUtil(datalist, toDoData)
        val toDoDiffUtilResult  = DiffUtil.calculateDiff(toDoDiffUtil)
        this.datalist = toDoData
        toDoDiffUtilResult.dispatchUpdatesTo(this)
    }
}