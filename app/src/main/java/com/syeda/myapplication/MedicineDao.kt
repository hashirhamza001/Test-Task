package com.syeda.myapplication

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.syeda.myapplication.model.Medicine
import kotlinx.coroutines.flow.Flow

@Dao
interface MedicineDao {
    @Insert(onConflict = OnConflictStrategy.Companion.REPLACE)
    suspend fun insertMedicine(medicine: Medicine)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedicines(medicines: List<Medicine>)

    @Query("SELECT * FROM medicine_table")
    fun getAllMedicines(): Flow<List<Medicine>>

    @Delete
    suspend fun deleteMedicine(medicine: Medicine)

    @Query("DELETE FROM medicine_table")
    suspend fun clearMedicines()
}
