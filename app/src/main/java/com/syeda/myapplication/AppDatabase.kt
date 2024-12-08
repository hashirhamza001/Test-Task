package com.syeda.myapplication

import androidx.room.Database
import androidx.room.RoomDatabase
import com.syeda.myapplication.model.Medicine

@Database(entities = [Medicine::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun medicineDao(): MedicineDao
}
