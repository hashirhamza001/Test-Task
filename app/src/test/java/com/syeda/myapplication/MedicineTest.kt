package com.syeda.myapplication
import com.syeda.myapplication.model.Medicine
import org.junit.Assert.*
import org.junit.Test
class MedicineTest {
    @Test
    fun testMedicineInstantiation() {
        val medicine = Medicine(id = 1, name = "Paracetamol", dose = "500mg", strength = "Mild")

        assertEquals(1, medicine.id)
        assertEquals("Paracetamol", medicine.name)
        assertEquals("500mg", medicine.dose)
        assertEquals("Mild", medicine.strength)
    }
}