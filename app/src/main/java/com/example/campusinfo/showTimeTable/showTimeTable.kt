package com.example.campusinfo.showTimeTable

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

import com.example.campusinfo.R

class showTimeTable : Fragment() {

    private lateinit var viewModel: ShowTimeTableViewModel
    var branch = arrayOf(
        "   Chose Branch:",
        "   CSE",
        "   ECE",
        "   EEE"
    )
    var year = arrayOf(
        "   Chose Year:",
        "   I",
        "   II",
        "   III",
        "   IV"
    )

    var defBranch = 0
    var defYear = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var inf=inflater.inflate(R.layout.show_time_table_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(ShowTimeTableViewModel::class.java)

        //work here
        fillTable()                          //filling tables

        val spinner_year = inf.findViewById<Spinner>(R.id.spinner_year)
        val spinner_branch_tt = inf.findViewById<Spinner>(R.id.spinner_branch_tt)
        val textView_output_tt =inf.findViewById<TextView>(R.id.textView_output_tt)
        textView_output_tt?.movementMethod = ScrollingMovementMethod()

        spinner_year?.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            year
        )

        spinner_branch_tt?.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            branch
        )

        spinner_year?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(parient: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                defYear = pos
                if(defBranch!=0 && defYear != 0 )
                    textView_output_tt.text = viewModel.showOutput(defBranch ,defYear)
            }
        }
        spinner_branch_tt?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(parient: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                defBranch = pos
                if(defBranch!=0 && defYear != 0 )
                    textView_output_tt.text = viewModel.showOutput(defBranch ,defYear)
            }
        }


        return inf
    }

    private fun fillTable() {
        viewModel.createTable(getString(R.string.b_cse1))    //Create All tables
        viewModel.createTable(getString(R.string.b_cse2))
        viewModel.createTable(getString(R.string.b_cse3))
        viewModel.createTable(getString(R.string.b_cse4))
        viewModel.createTable(getString(R.string.b_ece1))
        viewModel.createTable(getString(R.string.b_ece2))
        viewModel.createTable(getString(R.string.b_ece3))
        viewModel.createTable(getString(R.string.b_ece4))
        viewModel.createTable(getString(R.string.b_eee1))
        viewModel.createTable(getString(R.string.b_eee2))
        viewModel.createTable(getString(R.string.b_eee3))
        viewModel.createTable(getString(R.string.b_eee4))
    }



}
