package com.example.myapplication.data

import com.example.myapplication.model.CountryItem
import retrofit2.http.GET

/**
 * Communicates with the backend to obtain country data.
 */

interface CountryAPIService {

    /**
     * @return [List<CountryItem>] : List of countries from the server dataset
     */

    @GET("countries.json")
    suspend fun getCountries(): List<CountryItem>

}