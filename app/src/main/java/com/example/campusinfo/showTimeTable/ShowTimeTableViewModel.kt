package com.example.campusinfo.showTimeTable

import android.util.Log
import androidx.lifecycle.ViewModel

class ShowTimeTableViewModel : ViewModel() {

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

    fun showOutput( branch: Int , year:Int ):String{
        var ct = 1
        val empty ="  - "
        val space = " "
        var opStr="Slot  M    T     W       T       F\n\n"
        var opTable = arrTable[ 4*(branch -1) +(year-1) ]
        for(i in 0..8){
            var row = "   $ct    "
            for(j in 0..4){
                var next = if(opTable[i][j]==0)
                                    "$empty"
                                else
                                    "LT"+opTable[i][j].toString()
                if (next =="$empty")
                    row+="${next}$space$space$space$space"
                else
                    row+="${next}$space$space"
            }
            opStr+=(row+"\n")
            ct++
        }
        return opStr
    }
}
