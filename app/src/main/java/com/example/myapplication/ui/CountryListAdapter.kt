package com.example.myapplication.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.databinding.CountryListItemBinding
import com.example.myapplication.model.CountryItem
import com.example.myapplication.viewmodel.CountryListViewModel

class CountryListAdapter(var items : List<CountryItem> = emptyList(), val viewModel: CountryListViewModel) : RecyclerView.Adapter<CountryItemViewHolder>() {

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryItemViewHolder {
        val binding = CountryListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryItemViewHolder(binding, binding.root)
    }

    override fun onBindViewHolder(holder: CountryItemViewHolder, position: Int) {
        holder.binding.item = items.elementAtOrNull(position)
    }

}


/**
 * Holds the view for the Country item.
 */
class CountryItemViewHolder(val binding: CountryListItemBinding, itemView: View) : RecyclerView.ViewHolder(itemView)