package com.dev.eraydel.market.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.FoodElementBinding
import com.dev.eraydel.market.model.Food

class FoodAdapter (private val context: Context , val food: ArrayList<Food>, val onItemListener: OnItemListener) : RecyclerView.Adapter<FoodAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FoodElementBinding.inflate(layoutInflater)
        return ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: FoodAdapter.ViewHolder, position: Int) {
        holder.bindData(food[position])
    }

    override fun getItemCount(): Int {
        return food.size
    }

    interface OnItemListener{
        fun miClick(food: Food)
    }

    class ViewHolder(binding: FoodElementBinding, onItemListener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding = binding
        private val onItemListener = onItemListener
        private lateinit var food: Food

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.miClick(food)
        }

        fun bindData(item: Food){
            with(binding){

                when(item.id){
                    1 -> { ivFood.setImageResource(R.drawable.hamburguesa) }
                    2 -> { ivFood.setImageResource(R.drawable.tacos) }
                    3 -> { ivFood.setImageResource(R.drawable.pizza) }
                    4 -> { ivFood.setImageResource(R.drawable.sushi) }
                    5 -> { ivFood.setImageResource(R.drawable.hamburguesa) }
                    6 -> { ivFood.setImageResource(R.drawable.tacos) }
                    7 -> { ivFood.setImageResource(R.drawable.pizza) }
                }

                tvTitle.text = item.title
                //tvDescription.text = item.description
            }
            food = item
        }
    }
}