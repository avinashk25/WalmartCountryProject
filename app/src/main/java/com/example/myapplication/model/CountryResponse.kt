package com.example.myapplication.model

import com.google.gson.annotations.SerializedName

/**
 * Structure of the Country items to be displayed as list.
 */

data class CountryItem(

    @field:SerializedName("name")
    var name : String,

    @field:SerializedName("region")
    var region : String,

    @field:SerializedName("code")
    var code : String,

    @field:SerializedName("capital")
    var capital : String
)