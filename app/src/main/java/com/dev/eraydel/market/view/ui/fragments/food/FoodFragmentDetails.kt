package com.dev.eraydel.market.view.ui.fragments.food

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.dev.eraydel.market.R
import com.dev.eraydel.market.adapter.MenuAdapter
import com.dev.eraydel.market.databinding.FragmentFoodDetailsBinding
import com.dev.eraydel.market.model.FoodModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.gson.Gson
import com.squareup.picasso.Picasso


class FoodFragmentDetails : Fragment()  {

    private lateinit var binding: FragmentFoodDetailsBinding
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
        binding = FragmentFoodDetailsBinding.inflate(inflater,container,false)

        // reading the arguments...
        val item = arguments?.getString("item")
        item?.let {
            var gson = Gson()
            var element = gson.fromJson(item, FoodModel::class.java)
            binding.title.text = element.title.toString()
            binding.itemTitle.text = element.owner.toString()
            Picasso.get().load(element.image).into(binding.ivItemImage)
            binding.itemDescription.text = element.description
            binding.itemSchedule.text = element.schedule
            val menuAdapter = MenuAdapter(requireContext(),element.menu)
            binding.lvMenu.adapter = menuAdapter
            //detail item
            binding.lvMenu.setOnItemClickListener { adapterView, view, i, l ->

                showDetail(element , i)
            }
            this.title = element.title.toString()
            this.owner = element.owner.toString()
            this.telephone = element.telephone.toString()
        }

        binding.btnBack.setOnClickListener(){
            Log.i("btnBack" , "btn back")
            val food = FoodFragment()
            binding.materialToolbar.isVisible = false
            binding.scroll.isVisible = false
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(binding.DetailsFragment.id, food).commit()
        }

        //share button
        binding.btnShare.setOnClickListener(){
            val intent= Intent()
            intent.action=Intent.ACTION_SEND
            intent.putExtra(Intent.EXTRA_TEXT,"Hola, te recomiendo " + this.title + " " + this.owner + " " + this.telephone)
            intent.type="text/plain"
            startActivity(Intent.createChooser(intent,"Share To:"))
        }

        //business card
        binding.btnInfo.setOnClickListener{
            showBusinessCard()
        }

        return binding.root
    }

    //business card
    private fun showBusinessCard(){
        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.business_card, null)

        //use SuppoprtMapFragment for using in fragment instead of activity  MapFragment = activity   SupportMapFragment = fragment
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?

        if ( mapFragment!! == null ) {


            mapFragment!!.getMapAsync { mMap ->

                mMap.mapType = GoogleMap.MAP_TYPE_NORMAL

                mMap.clear() //clear old markers

                val googlePlex = CameraPosition.builder()
                    .target(LatLng(37.4219999, -122.0862462))
                    .zoom(10f)
                    .bearing(0f)
                    .tilt(45f)
                    .build()

                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(googlePlex), 10000, null)

                mMap.addMarker(
                    MarkerOptions()
                        .position(LatLng(37.4629101, -122.2449094))
                        .title("Iron Man")
                        .snippet("His Talent : Plenty of money")
                )
            }
        }

        /*
        view.findViewById<TextView>(R.id.itemTitle).text = e.menu[p0].title + " $" + e.menu[p0].price
        view.findViewById<TextView>(R.id.itemDescription).text = e.menu[p0].description
        Picasso.get().load(e.menu[p0].image).into(view.findViewById<ImageView>(R.id.ivItemImage))
         */

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

    //share option
    private fun showDetail(e: FoodModel, p0: Int){

        // on below line we are creating a new bottom sheet dialog.
        val dialog = BottomSheetDialog(requireContext())

        // on below line we are inflating a layout file which we have created.
        val view = layoutInflater.inflate(R.layout.activity_bottom_sheet, null)

        view.findViewById<TextView>(R.id.itemTitle).text = e.menu[p0].title + " $" + e.menu[p0].price
        view.findViewById<TextView>(R.id.itemDescription).text = e.menu[p0].description
        Picasso.get().load(e.menu[p0].image).into(view.findViewById<ImageView>(R.id.ivItemImage))

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