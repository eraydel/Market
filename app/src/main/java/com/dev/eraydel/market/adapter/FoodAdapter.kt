package com.dev.eraydel.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.holder.FoodViewHolder
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragment
import java.util.*
import kotlin.collections.ArrayList

class FoodAdapter(private var food: ArrayList<FoodModel>, val onItemListener: FoodFragment) : RecyclerView.Adapter<FoodViewHolder>(), Filterable {

    var foodFilterList = ArrayList<FoodModel>()

    init {
        foodFilterList = food
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FoodViewHolder(layoutInflater.inflate(R.layout.food_item,parent,false),onItemListener)
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {
        val item = foodFilterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return foodFilterList.size
    }

    //filter ...
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    foodFilterList = food
                } else {
                    val resultList = ArrayList<FoodModel>()
                    for (row in food) {
                        if ( row.title.lowercase().contains(charSearch.lowercase()) ) {
                            resultList.add(row)
                        }
                    }
                    foodFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = foodFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                foodFilterList = results?.values as ArrayList<FoodModel>
                notifyDataSetChanged()
            }

        }
    }

}