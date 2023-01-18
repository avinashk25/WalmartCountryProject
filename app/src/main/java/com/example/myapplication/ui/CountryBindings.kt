package com.example.myapplication.ui

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.myapplication.model.CountryItem
import com.example.myapplication.viewmodel.CountryListViewModel

@BindingAdapter("items", "viewmodel", requireAll = true)
fun RecyclerView.addItems(items : List<CountryItem>?, viewmodel: CountryListViewModel) {

    if (this.adapter == null) {
        val listAdapter = CountryListAdapter(items ?: emptyList(), viewmodel)
        adapter = listAdapter
        addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
    } else {
        (adapter as? CountryListAdapter)?.let {
            if (items != null) {
                it.items = items
            }
            it.notifyDataSetChanged()
        }
    }
}

@BindingAdapter("onCancelRefresh")
fun SwipeRefreshLayout.onCancelRefresh(cancel: Boolean) {
    if (cancel) {
        isRefreshing = false
    }
}