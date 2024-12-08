package com.syeda.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.syeda.myapplication.model.Medicine
import com.syeda.myapplication.databinding.MedicineCardBinding

class MedicineAdapter(private val onItemClicked: (Medicine) -> Unit) :
    ListAdapter<Medicine, MedicineAdapter.MedicineViewHolder>(MedicineDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MedicineViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = MedicineCardBinding.inflate(inflater, parent, false)
        return MedicineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MedicineViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class MedicineViewHolder(private val binding: MedicineCardBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(medicine: Medicine) {
            binding.tvName.text = medicine.name
            binding.tvDose.text = medicine.dose
            binding.tvStrength.text = medicine.strength
            binding.root.setOnClickListener { onItemClicked(medicine) }
        }
    }
}

class MedicineDiffCallback : DiffUtil.ItemCallback<Medicine>() {
    override fun areItemsTheSame(oldItem: Medicine, newItem: Medicine) = oldItem.name == newItem.name
    override fun areContentsTheSame(oldItem: Medicine, newItem: Medicine) = oldItem == newItem
}
