package com.example.toDoList43017

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_item.view.*


class ListAdapter constructor(private val lista: MutableList<ModeloTarefa>) : RecyclerView.Adapter<ListAdapter.MainViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val itemView = LayoutInflater.from(parent.context)
        return MainViewHolder(itemView.inflate(R.layout.list_item,parent,false))
    }

    override fun getItemCount() = lista.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {

        val current = lista[position]

        // Vê se a checkbox está checked, muda a letra consoante
        holder.cb.setOnCheckedChangeListener{ _, _ ->
            if(holder.cb.isChecked){
                holder.cb.paintFlags = holder.cb.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            }
            else{
                holder.cb.paintFlags = holder.cb.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            }
        }

        holder.cb.text = current.texto
        holder.cb.isChecked = current.checked

        //lista[position] = ModeloTarefa(holder.cb.isChecked, holder.cb.text.toString())
    }


    class MainViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        val cb: CheckBox = itemView.todoCheckbox

    }


}