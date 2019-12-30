package com.example.campusinfo.emptyLT


import android.util.Log
import androidx.lifecycle.ViewModel


class EmptyLtViewModel : ViewModel() {   //NEW PROBLEM how to use file *** Solved by converting CSV file to a string resource

    var arrTable = mutableListOf<Array<IntArray>>()

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

   } // adds a table of 9*5 matrix in above arrTable

    fun showOutput(TimeSlot:Int,Day: Int): String{
        var opStr="Empty LT\n"

        var checkarr = Array (8) {1}

        for(t in arrTable){

            if(t[TimeSlot][Day]!=0)
                checkarr[ t[TimeSlot][Day] - 1 ] = 0

        }


        for(k in checkarr.indices){
            if(checkarr[k]!=0){
                opStr+="LT${k+1}\n"
            }
        }



        return opStr
    }  // function which sets output textView

}


