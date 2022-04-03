package com.dev.eraydel.market.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.FoodCategoryItemBinding
import com.dev.eraydel.market.model.FoodCategoryItems

class FoodCategoryItemsAdapter( private val context: Context, val foodItems: ArrayList<FoodCategoryItems>, val onItemListener: OnItemListener) : RecyclerView.Adapter<FoodCategoryItemsAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodCategoryItemsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = FoodCategoryItemBinding.inflate(layoutInflater)
        return ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: FoodCategoryItemsAdapter.ViewHolder, position: Int) {
        holder.bindData(foodItems[position])
    }

    override fun getItemCount(): Int {
        return foodItems.size
    }

    interface OnItemListener{
        fun clickFoodItem(foodItems: FoodCategoryItems)
    }

    class ViewHolder(binding: FoodCategoryItemBinding, onItemListener: OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        private val binding = binding
        private val onItemListener = onItemListener
        private lateinit var foodItems: FoodCategoryItems

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.clickFoodItem(foodItems)
        }

        fun bindData(item: FoodCategoryItems){
            with(binding){
                ivItem.setImageResource(R.drawable.hamburgueseria)
                tvItemTitle.text = item.itemTitle
                tvItemDescription.text = item.itemDescription
                tvItemPrice.text = "$" + item.itemPrice.toString()
            }
            foodItems = item
        }

    }
}