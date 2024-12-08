package com.syeda.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.syeda.myapplication.model.Medicine
import com.syeda.myapplication.repo.MedicineRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(  private val repository: MedicineRepository) : ViewModel() {
    val medicines: LiveData<List<Medicine>> = repository.getMedicines().asLiveData()

    fun refreshMedicines() = viewModelScope.launch {
        repository.refreshMedicines()
    }
    fun deleteMedicine(medicine: Medicine) = viewModelScope.launch {
        repository.deleteMedicine(medicine)
    }
}
