package com.dev.eraydel.market.view.ui.fragments.services

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.airbnb.lottie.LottieAnimationView
import com.dev.eraydel.market.OnItemClickListener
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.ProductsAdapter
import com.dev.eraydel.market.adapter.ServicesAdapter
import com.dev.eraydel.market.api.APIService
import com.dev.eraydel.market.databinding.FragmentProductsBinding
import com.dev.eraydel.market.databinding.FragmentServicesBinding
import com.dev.eraydel.market.model.FoodModel
import com.dev.eraydel.market.model.ProductsModel
import com.dev.eraydel.market.model.ServicesModel
import com.dev.eraydel.market.response.ProductsResponse
import com.dev.eraydel.market.response.ServicesResponse
import com.dev.eraydel.market.view.ui.fragments.products.ProductsFragmentDetails
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ServicesFragment : Fragment() , OnItemClickListener {

    private lateinit var adapter: ServicesAdapter
    private lateinit var binding: FragmentServicesBinding
    private val servicesItems = ArrayList<ServicesModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentServicesBinding.inflate(inflater,container,false)
        initRecyclerView()
        getServices()
        setUpSearchView()
        binding.btnInfoServices.setOnClickListener{
            showInfo()
        }
        return binding.root
    }

    private fun showInfo(){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.show_animation, null)

        view.findViewById<LottieAnimationView>(R.id.idAnimation).setAnimation(R.raw.servicesorder)
        view.findViewById<TextView>(R.id.titleAnimation).text = "¿Necesitas a un experto?"
        view.findViewById<TextView>(R.id.descriptionAnimation).text = "Contacta fácilmente con oficios y profesiones"

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



    //getServices
    private fun getServices()
    {
        CoroutineScope(Dispatchers.IO).launch {
            val call: Response<ServicesResponse> = getRetrofit().create(APIService::class.java).getServicesItems("servicesList")
            val serResponse: ServicesResponse? =  call.body()
            requireActivity().runOnUiThread {
                if(call.isSuccessful)
                {
                    var items: ArrayList<ServicesModel> = (serResponse?.services ?: emptyArray<ServicesResponse>()) as ArrayList<ServicesModel>
                    servicesItems.clear()
                    servicesItems.addAll(items)
                    binding.progressBar.visibility = View.GONE
                    adapter.notifyDataSetChanged()
                }
            }
        }
    }

    private fun setUpSearchView() {
        binding.serviceSearch.queryHint = "¿Necesitas a un experto?"
        binding.serviceSearch.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return false
            }

        })
    }

    //initRecyclerView
    private fun initRecyclerView(){

        adapter = ServicesAdapter(servicesItems,this)

        binding.rvService.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false)

        //binding.rvFood.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        binding.rvService.adapter = adapter

    }

    //retrofit
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://demo7733613.mockable.io/api/v1/" )
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    override fun clickServicesItem(item: ServicesModel) {
        Log.i("Service" , item.title)
        val details = ServicesFragmentDetails()
        val args = Bundle()
        //sending the arguments...
        var gson = Gson()
        var jsonString = gson.toJson(item)
        args.putString("item" , jsonString)

        details.arguments = args
        binding.rvService.isVisible = false
        binding.materialToolbar2.isVisible = false
        binding.serviceSearch.isVisible = false
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(binding.FragmentContainerServices.id, details).commit()
    }

    override fun clickFoodItem(food: FoodModel) {

    }

    override fun clickProductsItem(products: ProductsModel) {

    }
}