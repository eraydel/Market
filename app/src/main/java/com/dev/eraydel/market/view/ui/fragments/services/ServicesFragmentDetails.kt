package com.dev.eraydel.market.view.ui.fragments.services

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.MenuAdapter
import com.dev.eraydel.market.databinding.FragmentFoodDetailsBinding
import com.dev.eraydel.market.databinding.FragmentServicesDetailsBinding
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.view.ui.fragments.food.FoodFragment
import com.google.gson.Gson
import com.squareup.picasso.Picasso

class ServicesFragmentDetails : Fragment() {

    private lateinit var binding: FragmentServicesDetailsBinding
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
        binding = FragmentServicesDetailsBinding.inflate(inflater,container,false)

        // reading the arguments...
        val item = arguments?.getString("item")
        item?.let {
            var gson = Gson()
            var element = gson.fromJson(item, ServicesModel::class.java)
            binding.title.text = element.title.toString()
            binding.itemTitle.text = element.owner.toString()
            Picasso.get().load(element.image).into(binding.ivItemImage)
            binding.itemDescription.text = element.description
            binding.itemSchedule.text = element.schedule
            binding.tvResusme.text = element.resume
            binding.tvExperience.text = element.experience
            //val menuAdapter = MenuAdapter(requireContext(),element.menu)
            //binding.lvMenu.adapter = menuAdapter
            this.title = element.title.toString()
            this.owner = element.owner.toString()
            this.telephone = element.telephone.toString()
        }

        binding.btnBack.setOnClickListener(){
            Log.i("btnBack" , "btn back")
            val services = ServicesFragment()
            binding.materialToolbar.isVisible = false
            binding.scroll.isVisible = false
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(binding.DetailsFragment.id, services).commit()
        }

        //share button
        binding.btnShare.setOnClickListener(){
            val intent= Intent()
            intent.action= Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hola, te recomiendo " + this.title + " " + this.owner + " " + this.telephone)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        return binding.root
    }


}