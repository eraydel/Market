package com.dev.eraydel.market.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dev.eraydel.market.databinding.FoodMenuItemBinding
import com.dev.eraydel.market.databinding.ProductCatalogItemBinding
import com.dev.eraydel.market.model.CatalogModel
import com.squareup.picasso.Picasso

class CatalogAdapter (context: Context, listCatalog: ArrayList<CatalogModel>) : BaseAdapter() {

    private val listCatalog = listCatalog
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listCatalog.size
    }

    override fun getItem(p0: Int): Any {
        return listCatalog[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listCatalog[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = ProductCatalogItemBinding.inflate(layoutInflater)
        with(binding)
        {
            binding.tvItemTitle.text = listCatalog[p0].title
            binding.tvItemDescription.text = listCatalog[p0].description
            Picasso.get().load(listCatalog[p0].image).into(binding.ivItem)
            binding.tvIPrice.text = "$" + listCatalog[p0].price.toString()
        }

        return binding.root
    }
}