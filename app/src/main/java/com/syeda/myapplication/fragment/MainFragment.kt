package com.syeda.myapplication.fragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.syeda.myapplication.R
import com.syeda.myapplication.activity.MedicineDetailActivity
import com.syeda.myapplication.adapter.MedicineAdapter
import com.syeda.myapplication.databinding.FragmentMainBinding
import com.syeda.myapplication.viewmodel.MedicineViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {
    private val viewModel: MedicineViewModel by viewModels()

    private val args: MainFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMainBinding.inflate(inflater, container, false)
        binding.tvGreeting.text = getGreetingMessage(args.username)
        val adapter = MedicineAdapter { medicine ->
            var intent = Intent(requireContext(), MedicineDetailActivity::class.java)
            intent.putExtra("medicine", Gson().toJson(medicine))
            startActivity(intent)

        }

        binding.rvMedicines.adapter = adapter
        binding.rvMedicines.layoutManager = LinearLayoutManager(context)

        viewModel.medicines.observe(viewLifecycleOwner) { medicines ->
            if(medicines.isEmpty()){
                binding.refreshBtn.text = getString(R.string.press_to_load)
            }
            else {
                binding.refreshBtn.text = getString(R.string.press_to_refresh)
            }
             adapter.submitList(medicines)

        }

        binding.refreshBtn.setOnClickListener {
            viewModel.refreshMedicines()
        }
        return binding.root
    }
    private fun getGreetingMessage(username: String?): String {
        val hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
        val greeting = when {
            hour < 12 -> "Good Morning"
            hour < 18 -> "Good Afternoon"
            else -> "Good Evening"
        }
        return "$greeting, $username!"
    }

}
