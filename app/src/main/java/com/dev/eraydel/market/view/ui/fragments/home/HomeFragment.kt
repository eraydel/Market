package com.dev.eraydel.market.view.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.dev.eraydel.market.R
import com.dev.eraydel.market.databinding.FragmentHomeBinding
import com.dev.eraydel.market.db.Home
import com.dev.eraydel.market.model.Food
import com.dev.eraydel.market.model.Products
import com.dev.eraydel.market.model.Services
import com.dev.eraydel.market.view.adapter.FoodAdapter
import com.dev.eraydel.market.view.adapter.ProductsAdapter
import com.dev.eraydel.market.view.adapter.ServiceAdapter
import com.dev.eraydel.market.view.ui.activities.ItemFoodDetails
import com.dev.eraydel.market.view.ui.activities.stories.StoryTell1


class HomeFragment : Fragment() , FoodAdapter.OnItemListener, ProductsAdapter.OnItemListener, ServiceAdapter.OnItemListener {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        // return inflater.inflate(R.layout.fragment_home, container, false)
        binding = FragmentHomeBinding.inflate(inflater , container ,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //Top food
        val data = Home(requireContext()).getTopFood()
        val adapter = FoodAdapter(requireContext(), data, this)
        with(binding){
            //RecyclerView require a LayoutManager
            val lm = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvFood.layoutManager = lm
            rvFood.adapter = adapter
        }
        //top products
        val dataProducts = Home(requireContext()).getTopProducts()
        val adapterProducts = ProductsAdapter(requireContext(), dataProducts, this)
        with(binding){
            //RecyclerView require a LayoutManager
            val lmp = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvProducts.layoutManager = lmp
            rvProducts.adapter = adapterProducts
        }

        // top services
        val dataServices = Home(requireContext()).getTopServices()
        val adapterServices = ServiceAdapter(requireContext(), dataServices, this)
        with(binding){
            //RecyclerView require a LayoutManager
            val lms = LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL,false)
            rvServices.layoutManager = lms
            rvServices.adapter = adapterServices
        }
    }

    override fun miClick(food: Food) {
        Toast.makeText(requireContext(), "Title: ${food.title}", Toast.LENGTH_SHORT).show()
        val intent =  Intent(activity, ItemFoodDetails::class.java)
        intent.putExtra("id" , food.id)
        startActivity(intent)

    //parentFragmentManager.setFragmentResult("datos",bundle)
    }

    override fun clickProduct(products: Products) {
        Toast.makeText(requireContext(), "Title: ${products.name}", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("dato1", "${products.name}")
        parentFragmentManager.setFragmentResult("datos",bundle)
    }

    override fun clickService(services: Services) {
        Toast.makeText(requireContext(), "Title: ${services.name}", Toast.LENGTH_SHORT).show()
        val bundle = Bundle()
        bundle.putString("dato1", "${services.name}")
        parentFragmentManager.setFragmentResult("datos",bundle)
    }
}