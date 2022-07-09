package com.dev.eraydel.market.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.holder.FoodViewHolder
import com.dev.eraydel.market.holder.ProductsViewHolder
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.view.ui.fragments.products.ProductsFragment

class ProductsAdapter (private var products: ArrayList<ProductsModel>, val onItemListener: ProductsFragment) : RecyclerView.Adapter<ProductsViewHolder>(), Filterable {

    var productsFilterList = ArrayList<ProductsModel>()

    init {
        productsFilterList = products
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ProductsViewHolder(layoutInflater.inflate(R.layout.product_item,parent,false), onItemListener)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        val item = productsFilterList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return productsFilterList.size
    }

    //filter ...
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    productsFilterList = products
                } else {
                    val resultList = ArrayList<ProductsModel>()
                    for (row in products) {
                        if ( row.title.lowercase().contains(charSearch.lowercase()) ) {
                            resultList.add(row)
                        }
                    }
                    productsFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = productsFilterList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                productsFilterList = results?.values as ArrayList<ProductsModel>
                notifyDataSetChanged()
            }

        }
    }
}