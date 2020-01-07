package com.example.campusinfo.emptyLT

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.lifecycle.ViewModel
import com.example.campusinfo.R
import kotlinx.android.synthetic.main.empty_lt_fragment.*

class
emptyLT : Fragment() {

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


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val t=inflater.inflate(R.layout.empty_lt_fragment, container, false)
        val spinner_day = t.findViewById<Spinner>(R.id.spinner_branch)
        val spinner_time_slot = t.findViewById<Spinner>(R.id.spinner_time_slot)
        val button_current = t.findViewById<Button>(R.id.button_current)
        val textView_output = t.findViewById<TextView>(R.id.textView_output)

        textView_output?.movementMethod = ScrollingMovementMethod()

        viewModel = ViewModelProviders.of(this).get(EmptyLtViewModel::class.java)



        // work Here
        fillTable()                   //filling Tables

        spinner_day?.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            day
        )

        spinner_time_slot?.adapter = ArrayAdapter(
            activity!!.applicationContext,
            R.layout.support_simple_spinner_dropdown_item,
            normalDay
        )


        // NEW PROBLEM how TO get day and compare it to given input ***solved(by using local variables)

        spinner_day?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
            override fun onItemSelected(parent: AdapterView<*>?, p1: View?, pos: Int, p3: Long) {

               // defDay = if(pos!=0) pos-1 else 0
                 defDay=pos
         //       Log.i("DaySelect","$pos")
                if(parent!!.getItemAtPosition(pos).toString() == "   Wednesday") {
                    spinner_time_slot?.adapter = ArrayAdapter(
                        activity!!.applicationContext,
                        R.layout.support_simple_spinner_dropdown_item,
                        wednesday
                    )
                }
                    else{
                    spinner_time_slot?.adapter = ArrayAdapter(
                        activity!!.applicationContext,
                        R.layout.support_simple_spinner_dropdown_item,
                        normalDay
                    )
                }
                if(defDay!=0 && defTimeSlot!=0){
                        if( (viewModel as EmptyLtViewModel).showOutput(defTimeSlot-1,defDay-1) =="Empty LT(s)\n\n" ){
                            textView_output.text ="Empty LT(s)\n\nNo Empty LT"
                        }
                        else{
                            textView_output.text = (viewModel as EmptyLtViewModel).showOutput(defTimeSlot-1,defDay-1)
                        }
                }
            }
        }      //Onclick Handler of day spinner

        spinner_time_slot.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {
            }
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                defTimeSlot = p2
            //    Log.i("TimeSelect","$p2")
                if(defDay!=0 && defTimeSlot!=0){
                    if( (viewModel as EmptyLtViewModel).showOutput(defTimeSlot-1,defDay-1) == "Empty LT(s)\n\n" ){
                        textView_output.text ="Empty LT(s)\n\nNo Empty LT"
                    }
                    else{
                        textView_output.text = (viewModel as EmptyLtViewModel).showOutput(defTimeSlot-1,defDay-1)
                    }
                }

            }
        } //Onclick Handler of timeSlot spinner

        button_current.setOnClickListener {
            var temp = (viewModel as EmptyLtViewModel).setCurrent()
            if(temp!=""){
                if(temp!= "Empty LT(s)\n\n")
                    textView_output.text = temp
                else
                    textView_output.text = "Empty LT(s)\n\nNo Empty LT"
            }
            else
                Toast.makeText(activity!!.applicationContext,"College Closed" , Toast.LENGTH_SHORT ) .show()
        }                                                     //Onclick Handler of current Button



        return t

    }

    private fun fillTable() {
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_cse1) )    //Create All tables
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_cse2) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_cse3) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_cse4) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_ece1) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_ece2) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_ece3) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_ece4) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_eee1) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_eee2) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_eee3) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.b_eee4) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.m_ece) )
        (viewModel as EmptyLtViewModel).createTable( getString(R.string.m_me) )
    }      //Filling Tables

    private fun fillDays(){

            spinner_branch?.adapter = ArrayAdapter(
                activity!!.applicationContext,
                R.layout.support_simple_spinner_dropdown_item,
                day
            )


    }        // Fill spinner_days with days

    private fun fillNormalDays(){

            spinner_time_slot?.adapter = ArrayAdapter(
                activity!!.applicationContext,
                R.layout.support_simple_spinner_dropdown_item,
                normalDay
            )



    }  // Fill spinner_time_slot with normal time

    private fun fillWednesday(){

             spinner_time_slot?.adapter = ArrayAdapter(
                activity!!.applicationContext,
                R.layout.support_simple_spinner_dropdown_item,
                wednesday
            )

    }   // Fill spinner_time_slot with wednesday time

}










