package com.ben.conversions.ui.fragments.home

import android.view.View
import androidx.lifecycle.*
import com.ben.conversions.data.local.entities.ConversionData
import com.ben.conversions.data.repositories.ConversionRepository
import com.ben.conversions.data.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    var conversionRepository: ConversionRepository
) : ViewModel() {

    val filterText = MutableLiveData<String?>(null)
    val errorEvent = MutableLiveData<String>()

    private var conversionsResource = Transformations.switchMap(filterText) {
        when (it) {
            null -> conversionRepository.fetchConversions()
            else -> conversionRepository.searchConversions(it)
        }
    }

    val conversions = Transformations.map(conversionsResource) {
        when (it.status) {
            Resource.Status.SUCCESS -> it.data
            Resource.Status.LOADING -> emptyList()
            Resource.Status.ERROR -> {
                it.message?.let { errorEvent.postValue(it) }
                emptyList()
            }
        }
    }

    fun onRefreshClicked(view: View) {
        filterText.postValue(null)
    }

}