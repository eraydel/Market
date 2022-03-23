package com.dev.eraydel.market.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.ProductElementBinding
import com.dev.eraydel.market.model.Products

class ProductsAdapter( private val context: Context , val products: ArrayList<Products>, val onItemListener: OnItemListener) : RecyclerView.Adapter<ProductsAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdapter.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ProductElementBinding.inflate(layoutInflater)
        return ViewHolder(binding, onItemListener)
    }

    override fun onBindViewHolder(holder: ProductsAdapter.ViewHolder, position: Int) {
        holder.bindData(products[position])
    }

    override fun getItemCount(): Int {
        return products.size
    }

    interface OnItemListener{
        fun clickProduct(products: Products)
    }

    class ViewHolder(binding: ProductElementBinding, onItemListener: ProductsAdapter.OnItemListener): RecyclerView.ViewHolder(binding.root), View.OnClickListener{

        private val binding = binding
        private val onItemListener = onItemListener
        private lateinit var products: Products

        init{
            binding.root.setOnClickListener(this)
        }

        override fun onClick(p0: View?) {
            onItemListener.clickProduct(products)
        }

        fun bindData(item: Products){
            with(binding){

                when(item.id){
                    1 -> { ivProduct.setImageResource(R.drawable.ropa) }
                    2 -> { ivProduct.setImageResource(R.drawable.calzado) }
                    3 -> { ivProduct.setImageResource(R.drawable.deportiva) }
                    4 -> { ivProduct.setImageResource(R.drawable.tenis) }
                    5 -> { ivProduct.setImageResource(R.drawable.tazas) }
                    6 -> { ivProduct.setImageResource(R.drawable.blusa) }
                }

                tvName.text = item.name
                tvPrice.text = "$" + item.price.toString()
            }
            products = item
        }
    }
}