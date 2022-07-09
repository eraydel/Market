package com.dev.eraydel.market.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.dev.eraydel.market.databinding.FoodMenuItemBinding
import com.dev.eraydel.market.model.MenuModel
import com.squareup.picasso.Picasso

class MenuAdapter ( context: Context , listMenu: ArrayList<MenuModel>) : BaseAdapter() {

    private val listMenu = listMenu
    private val layoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int {
        return listMenu.size
    }

    override fun getItem(p0: Int): Any {
        return listMenu[p0]
    }

    override fun getItemId(p0: Int): Long {
        return listMenu[p0].id.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val binding = FoodMenuItemBinding.inflate(layoutInflater)
        with(binding)
        {
            binding.tvItemTitle.text = listMenu[p0].title
            binding.tvItemDescription.text = listMenu[p0].description
            Picasso.get().load(listMenu[p0].image).into(binding.ivItem)
            binding.tvIPrice.text = "$" + listMenu[p0].price.toString()
        }

        return binding.root
    }

}