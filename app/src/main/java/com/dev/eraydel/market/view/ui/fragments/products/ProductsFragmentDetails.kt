package com.dev.eraydel.market.view.ui.fragments.products

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.CatalogAdapter
import com.dev.eraydel.market.databinding.FragmentProductsBinding
import com.dev.eraydel.market.databinding.FragmentProductsDetailsBinding
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragment
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class ProductsFragmentDetails : Fragment() {

    private lateinit var binding: FragmentProductsDetailsBinding
    private lateinit var title: String
    private lateinit var owner: String
    private lateinit var telephone: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProductsDetailsBinding.inflate(inflater,container,false)

        // reading the arguments...
        val item = arguments?.getString("item")
        item?.let {
            var gson = Gson()
            var element = gson.fromJson(item, ProductsModel::class.java)

            binding.title.text = element.title.toString()
            binding.itemTitle.text = element.owner.toString()
            Picasso.get().load(element.image).into(binding.ivItemImage)
            binding.itemDescription.text = element.description
            binding.itemSchedule.text = element.schedule
            val catalogAdapter = CatalogAdapter(requireContext(),element.catalog)
            binding.lvCatalog.adapter = catalogAdapter

            //detail item
            binding.lvCatalog.setOnItemClickListener { adapterView, view, i, l ->

                showDetail(element , i)
            }

            this.title = element.title.toString()
            this.owner = element.owner.toString()
            this.telephone = element.telephone.toString()
        }

        binding.btnBack.setOnClickListener(){
            Log.i("btnBack" , "btn back")
            val food = ProductsFragment()
            binding.materialToolbar.isVisible = false
            binding.scroll.isVisible = false
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(binding.DetailsFragment.id, food).commit()
        }

        //share button
        binding.btnShare.setOnClickListener(){
            val intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hola, te recomiendo " + this.title + " " + this.owner + " " + this.telephone)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        //btnCard
        binding.btnInfo.setOnClickListener{
            //showDetail()
        }

        return binding.root
    }


    private fun showDetail(e:ProductsModel , p0: Int){

        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.activity_bottom_sheet, null)

        view.findViewById<TextView>(R.id.itemTitle).text = e.catalog[p0].title + " $" + e.catalog[p0].price
        view.findViewById<TextView>(R.id.itemDescription).text = e.catalog[p0].description
        Picasso.get().load(e.catalog[p0].image).into(view.findViewById<ImageView>(R.id.ivItemImage))

        // below line is use to set cancelable to avoid
        // closing of dialog box when clicking on the screen.
        dialog.setCancelable(true)

        // on below line we are setting
        // content view to our view.
        dialog.setContentView(view)

        // on below line we are calling
        // a show method to display a dialog.
        dialog.show()
    }
}