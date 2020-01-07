package com.example.campusinfo



class Tables() {

    var table= Array(9) {IntArray(5)}



    fun show(){
        for(i in table ){
            for(j in i){
                print("$j ")
            }
            println("")
        }
    }


}