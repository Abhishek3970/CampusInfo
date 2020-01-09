package com.example.campusinfo.emptyLT


import android.util.Log
import androidx.lifecycle.ViewModel
import java.util.*
import kotlin.collections.ArrayList


class EmptyLtViewModel : ViewModel() {   //NEW PROBLEM how to use file *** Solved by converting CSV file to a string resource

    var arrTable = mutableListOf<Array<IntArray>>()            // elements:cs1,cs2,cs3,cs4,ec1,ec2,ec3,ec4,ee1,ee2,ee3,ee4,mec,mme

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

   }                   // adds a table of 9*5 matrix in above arrTable

    fun showOutput(TimeSlot:Int,Day: Int): String{
        var opStr="Empty LT(s)\n\n"
        var ct=0
        var checkarr = Array (8) {1}
        for(t in arrTable){
            if(t[TimeSlot][Day]!=0)
                checkarr[ t[TimeSlot][Day] - 1 ] = 0

            if(ct == 2){                                   // special case for cs3 as g1 and g2 in diff LT
                if(Day == 1 && TimeSlot == 1)
                    checkarr[2] = 0
                if(Day == 3 && TimeSlot == 2)
                    checkarr[7] = 0
                if(Day == 4 && TimeSlot == 2)
                    checkarr[6] = 0
            }
            if(ct == 4){                                    // special case for ec1 as g1 and g2 in diff LT
                if(Day == 2 && TimeSlot == 4)
                    checkarr[5] = 0
                if(Day == 2 && TimeSlot == 7)
                    checkarr[6] = 0
            }
            if(ct==8){
                if(Day == 3 && TimeSlot == 1)
                    checkarr[5] = 0
            }

            ct++
        }
        for(k in checkarr.indices){
            if(checkarr[k]!=0){
                opStr+="LT${k+1}\n"
            }
        }
        return opStr
    }         // function which returns values to be set in output textView

    fun setCurrent():String{                                    //CONTINUE WORKING HERE use return to call show output function
        var res = setkeys()
        return if(res[0]==-1 && res[1]==-1) "" else showOutput(res[1],res[0])   //1 - timeSlot , 0 - day
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


