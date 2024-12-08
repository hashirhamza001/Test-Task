package com.syeda.myapplication.activity

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.syeda.myapplication.model.Medicine
import com.syeda.myapplication.viewmodel.MedicineViewModel
import com.syeda.myapplication.R
import com.syeda.myapplication.databinding.ActivityMedicineDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MedicineDetailActivity : AppCompatActivity() {
    private lateinit var viewModel: MedicineViewModel
    private lateinit var binding : ActivityMedicineDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(MedicineViewModel::class.java)
        binding = ActivityMedicineDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tvName = findViewById<TextView>(R.id.tvName)
        val tvDose = findViewById<TextView>(R.id.tvDose)
        val tvStrength = findViewById<TextView>(R.id.tvStrength)

        val medicine =Gson().fromJson( intent.getStringExtra("medicine"), Medicine::class.java)
        medicine?.let {
            tvName.text = "Name: ${it.name}"
            tvDose.text = "Dose: ${it.dose}"
            tvStrength.text = "Strength: ${it.strength}"
        }
        binding.btnDelete.setOnClickListener {
            medicine?.let {
                viewModel.deleteMedicine(it)
                finish()
        }
    }
}
}
