package com.example.campusinfo.freeBatches

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModel

import com.example.campusinfo.R
import kotlinx.android.synthetic.main.empty_lt_fragment.*
import kotlinx.android.synthetic.main.free_batches_fragment.*

class freeBatches : Fragment() {

    private lateinit var viewModel: ViewModel

    var defDay = 0                  // passed in show output function days as selected day
    var defTimeSlot = 0             // passed in show output function timeSlot as selected timeSlot

    val day = arrayOf(
        "   Chose Day:",
        "   Monday",
        "   Tuesday",
        "   Wednesday",
        "   Thursday",
        "   Friday"
    )          // used to compare user i/p with wednesday to set wednesday

    val normalDay = arrayOf(
        "   Chose Time Slot:",
        "   8:30-9:30",
        "   9:30-10:30",
        "   10:30-11:30",
        "   11:30-12:30",
        "   12:30-1:30",
        "   1:30-2:30",
        "   2:30-3:30",
        "   3:30-4:30",
        "   4:30-5:30"
    )

    val wednesday = arrayOf(
        "   Chose Time Slot:",
        "   8:30-9:30",
        "   9:30-10:15",
        "   10:15-11:00",
        "   11:00-11:45",
        "   11:45-12:30",
        "   12:30-1:15",
        "   1:15-2:00",
        "   2:00-2:45",
        "   2:45-3:30"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val t = inflater.inflate(R.layout.free_batches_fragment, container, false)
        viewModel = ViewModelProviders.of(this).get(FreeBatchesViewModel::class.java)
        val spinner_day_fb = t.findViewById<Spinner>(R.id.spinner_day_fb)
        val spinner_time_slot_fb = t.findViewById<Spinner>(R.id.spinner_time_slot_fb)
        val button_use_current_time = t.findViewById<Button>(R.id.button_use_current_time)
        val textView_show_free_batches = t.findViewById<TextView>(R.id.textView_show_free_batches)

        textView_show_free_batches?.movementMethod = ScrollingMovementMethod()
        // Work here
        fillTable()                                   //filling Tables

        spinner_day_fb.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            day
        )
        spinner_time_slot_fb.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            normalDay
        )

        spinner_day_fb.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }

            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                defDay=pos
                //       Log.i("DaySelect","$pos")
                if(parent!!.getItemAtPosition(pos).toString() == "   Wednesday") {
                    spinner_time_slot_fb?.adapter = ArrayAdapter(
                        activity!!.applicationContext,
                        R.layout.support_simple_spinner_dropdown_item,
                        wednesday
                    )
                }
                else{
                    spinner_time_slot_fb?.adapter = ArrayAdapter(
                        activity!!.applicationContext,
                        R.layout.support_simple_spinner_dropdown_item,
                        normalDay
                    )
                }
                // next conditions here
                if(defDay!=0 && defTimeSlot!=0){
                    if((viewModel as FreeBatchesViewModel).showOutputBatches(defTimeSlot-1,defDay-1) == "Free Batches\n\n")
                        textView_show_free_batches.text = "Free Batches\n\nNo Free Batches"
                    else{
                        textView_show_free_batches.text=""
                        textView_show_free_batches.append( (viewModel as FreeBatchesViewModel).showOutputBatches(defTimeSlot-1,defDay-1) )
                    }
                }
            }
        }

        spinner_time_slot_fb.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {
                defTimeSlot = pos
                //next Conditions here
                if(defDay!=0 && defTimeSlot!=0){
                    if((viewModel as FreeBatchesViewModel).showOutputBatches(defTimeSlot-1,defDay-1) == "Free Batches\n\n")
                        textView_show_free_batches.text = "Free Batches\n\nNo Free Batches"
                    else{
                        textView_show_free_batches.text=""
                        textView_show_free_batches.append( (viewModel as FreeBatchesViewModel).showOutputBatches(defTimeSlot-1,defDay-1) )
                    }
                }
            }
        }

        button_use_current_time.setOnClickListener {
            var temp = (viewModel as FreeBatchesViewModel).setCurrent()
            Log.i("fb" , "$temp")
            if(temp!=""){
                if(temp!= "Free Batches\n\n"){
                    textView_show_free_batches.text = temp
                }
                else
                    textView_show_free_batches.text = "Free Batches\n\nNo Free Batches"
            }
            else
                Toast.makeText(activity!!.applicationContext,"College Closed" , Toast.LENGTH_SHORT ) .show()
        }




        return t
    }

    private fun fillTable() {
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_cse1fb) )    //Create All tables
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_cse2fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_cse3fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_cse4fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_ece1fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_ece2fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_ece3fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_ece4fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_eee1fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_eee2fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_eee3fb) )
        (viewModel as FreeBatchesViewModel).createTable( getString(R.string.b_eee4fb) )
    }      //Filling Tables



}
