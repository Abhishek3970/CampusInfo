package com.example.campusinfo.freeBatches

import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList

class FreeBatchesViewModel : ViewModel() {

    var arrTable = mutableListOf<Array<IntArray>>()            // elements:cs1,cs2,cs3,cs4,ec1,ec2,ec3,ec4,ee1,ee2,ee3,ee4

    fun createTable(tableData : String){

        var table= Array(9) {IntArray(5)}

        Log.i("ViewM","$tableData" )   //Data coming as list

        var k=0
        for(i in 0..8){
            for(j in 0..4){
                table[i][j]=tableData[k]-'0'
                k+=2
                //  Log.i("table","table[$i][$j]=${table[i][j]}")
            }
        }
        arrTable.add(table)

    }                 //fills Table

    fun giveBatches(no: Int): String{
        return when(no){
            0->"CSE1"
            1->"CSE2"
            2->"CSE3"
            3->"CSE4"
            4->"ECE1"
            5->"ECE2"
            6->"ECE3"
            7->"ECE4"
            8->"EEE1"
            9->"EEE2"
            10->"EEE3"
            11 ->"EEE4"
            else ->""
        }
    }                 //Returns branch name relative to its position in arrTable

    fun showOutputBatches(TimeSlot:Int,Day: Int) : String{
        var ct=0
        var opStr = "Free Batches\n\n"
        for(t in arrTable){
            if(t[TimeSlot][Day] == 0){
                opStr +=  (giveBatches(ct) + "\n")
            }
            ct++
        }
        return opStr
    }    //gives output

    fun setCurrent():String{                                    //CONTINUE WORKING HERE use return to call show output function
        var res = setkeys()
   //     Log.i("fbv"," ${res[0]} ${res[1]}")
        return if(res[0]==-1 && res[1]==-1) "" else showOutputBatches(res[1],res[0])   //1 - timeSlot , 0 - day
    }                               // used to set current time and day

    private fun setkeys(): MutableList<Int> {
        var arr: MutableList<Int>  = ArrayList()

        var cal = Calendar.getInstance().time.toString()
        //   Log.i("time","${cal} ${cal[11]-'0'} ${cal[12]-'0'}")
        var flag = true

        var day=cal[0].toString()+cal[1].toString()
        if(day == "Su" || day == "Sa")
            flag = false

        var time = ( (cal[11]-'0')*10 + (cal[12]-'0') )*60 + (cal[14]-'0')*10 + (cal[15]-'0')                                    //starts at 11th index
        if( (time < 510 || time > 1050) && day!="We"  )
            flag = false

        if( (time < 510 ||time > 930 ) && day=="We" )
            flag = false

        //   Log.i("values","$day $time")

        if(!flag){                       // -1 indicate COLLEGE OVER
            arr.add(-1)
            arr.add(-1)
        }
        else{
            when(day){
                "Mo"-> arr.add(0)
                "Tu"-> arr.add(1)
                "We"-> arr.add(2)
                "Th"-> arr.add(3)
                "Fr"-> arr.add(4)
            }

            if(arr[0]==2){           //this is for Wednesday           //Continue by setting time
                var ct=0
                var start = 510
                start+=60
                if (time < start)
                    arr.add(ct)
                else{
                    ct++
                    start+=45
                    while(time > start){
                        ct++
                        start+=45
                    }
                    arr.add(ct)
                }
            }
            else{
                var ct=0
                var start = 510+60
                while(time > start){
                    ct++
                    start+=60
                }
                arr.add(ct)
            }
        }

        //       Log.i("date" ,"${arr[0]} ${arr[1]}")

        return arr
    }              // used to map current day and time to correct keys



}
