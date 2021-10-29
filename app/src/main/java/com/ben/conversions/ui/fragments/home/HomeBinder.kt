package com.ben.conversions.ui.fragments.home

import android.os.Parcelable
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ben.conversions.data.local.entities.ConversionData

@BindingAdapter("data")
fun <T> setRecyclerViewProperties(recyclerView: RecyclerView, data: List<ConversionData>?) {
    if (recyclerView.adapter != null) {
        data?.let {
            val recyclerViewState: Parcelable? = recyclerView.layoutManager?.onSaveInstanceState()
            (recyclerView.adapter as? HomeListAdapter)?.submitList(it)
            recyclerView.layoutManager?.onRestoreInstanceState(recyclerViewState)
        }
    }
}