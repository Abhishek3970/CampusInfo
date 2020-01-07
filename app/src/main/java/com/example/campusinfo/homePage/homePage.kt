package com.example.campusinfo.homePage

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController

import com.example.campusinfo.R
import com.example.campusinfo.showTimeTable.showTimeTable

class homePage : Fragment() {

    private lateinit var viewModel: HomePageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var t=inflater.inflate(R.layout.home_page_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(HomePageViewModel::class.java)

        val button_empty_lt = t.findViewById<Button>(R.id.button_empty_lt)
        val button_find_free_batches = t.findViewById<Button>(R.id.button_find_free_batches)
        val button_show_time_table = t.findViewById<Button>(R.id.button_show_time_table)

        button_empty_lt.setOnClickListener {
            view?.findNavController()?.navigate(homePageDirections.actionHomePageToEmptyLT())
        }

        button_show_time_table.setOnClickListener {
            view?.findNavController()?.navigate(homePageDirections.actionHomePageToShowTimeTable())
        }
        button_find_free_batches.setOnClickListener {
            view?.findNavController()?.navigate(homePageDirections.actionHomePageToFreeBatches())
        }

        return t
    }


}
