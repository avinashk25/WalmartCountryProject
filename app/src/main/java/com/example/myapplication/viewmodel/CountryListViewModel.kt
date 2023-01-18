package com.example.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.data.CountryAPIService
import com.example.myapplication.model.CountryItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.example.myapplication.SingleLiveEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CountryListViewModel @Inject constructor(private val service : CountryAPIService) : ViewModel(),  SwipeRefreshLayout.OnRefreshListener  {

    companion object {
        private const val TAG = "CountryListViewModel"
    }

    // List of countries to be displayed on UI
    private val _items = MutableLiveData<List<CountryItem>>()
    val items : LiveData<List<CountryItem>> = _items

    // Refresh status update
    private val _canRefresh = SingleLiveEvent<Boolean>()
    val canRefresh : SingleLiveEvent<Boolean> = _canRefresh

    init {
        // load country list
        onRefresh()
    }

    /**
     * In case, there is no data received from the server. request user to refresh and try fetching the dataset again.
     */

    override fun onRefresh() {
        Log.d(TAG,"onRefresh")
        viewModelScope.launch(Dispatchers.IO) {
            kotlin.runCatching {
                service.getCountries()
            }.onFailure {
                _items.postValue(emptyList())
            }.onSuccess {
                _items.postValue(it)
            }
            _canRefresh.postValue(true)
        }
    }

}