package com.dev.eraydel.market.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.ServiceElementBinding
import com.dev.eraydel.market.model.Services


class ServiceAdapter(private val context: Context, val services: ArrayList<Services>, val onItemListener: ServiceAdapter.OnItemListener ) : RecyclerView.Adapter<ServiceAdapter.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ServiceElementBinding.inflate(layoutInflater)
        return ServiceAdapter.ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: ServiceAdapter.ViewHolder, position: Int) {
        holder.bindData(services[position])
    }

    override fun getItemCount(): Int {
        return services.size
    }

    interface OnItemListener{
        fun clickService(services: Services)
    }

    class ViewHolder(binding: ServiceElementBinding, onItemListener: ServiceAdapter.OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding = binding
        private val onItemListener = onItemListener
        private lateinit var services: Services

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.clickService(services)
        }

        fun bindData(item: Services){
            with(binding){

                when(item.id){
                    1 -> { ivService.setImageResource(R.drawable.pediatra) }
                    2 -> { ivService.setImageResource(R.drawable.electricista) }
                    3 -> { ivService.setImageResource(R.drawable.albanil) }
                    4 -> { ivService.setImageResource(R.drawable.jardinero) }
                    5 -> { ivService.setImageResource(R.drawable.tazas) }
                    6 -> { ivService.setImageResource(R.drawable.blusa) }
                }

                tvName.text = item.name
                tvDescription.text = item.description
            }
            services = item
        }
    }
}