package com.example.testewebsocket.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.testewebsocket.Modelss.EventModel
import com.example.testewebsocket.databinding.ItemSocketMessageBinding
import com.example.testewebsocket.databinding.ItemSocketTypeBinding

class AdapterMessage : RecyclerView.Adapter<AdapterMessage.TipoVH>() {

    private var eventos = listOf<EventModel>()

    fun postData(newData: List<EventModel>){
        eventos = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TipoVH {
        val binding = ItemSocketMessageBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TipoVH(binding)
    }

    override fun onBindViewHolder(holder: TipoVH, position: Int) {

        var event : EventModel = eventos[position]

        holder.binding.tvMessage.text = event.data?.message

    }

    override fun getItemCount(): Int {
        return eventos.size
    }

    class TipoVH(val binding: ItemSocketMessageBinding): RecyclerView.ViewHolder(binding.root)
}
