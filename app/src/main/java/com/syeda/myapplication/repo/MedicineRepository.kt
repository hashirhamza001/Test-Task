package com.syeda.myapplication.repo

import com.syeda.myapplication.model.Medicine
import com.syeda.myapplication.MedicineDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MedicineRepository @Inject constructor(
    private val api: MedicineApi,
    private val dao: MedicineDao
) {
    // Fetch medicines from API and update the Room DB
    suspend fun refreshMedicines() {
        try {
            val medicinesFromApi = api.getMedicines()
            dao.clearMedicines()
            dao.insertMedicines(medicinesFromApi)
        } catch (e: Exception) {
            // Handle API failure (e.g., log the error)
        }
    }

    // Get medicines from the database (offline support)
    fun getMedicines(): Flow<List<Medicine>> {
        return dao.getAllMedicines()
    }
    suspend fun deleteMedicine(medicine: Medicine) {
        dao.deleteMedicine(medicine)
    }
}
