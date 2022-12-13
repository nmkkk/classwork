package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel:MyViewModel by viewModels {
        MyViewModelFactory(
            (application as MyApplication).database.itemDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = ItemListAdapter {}
        binding.recyclerView.adapter = adapter
        viewModel.allItems.observe(this) {
            adapter.submitList(it)
        }
        binding.button.setOnClickListener {
            addNewItem()
            val inputMethodManager = this.getSystemService(INPUT_METHOD_SERVICE) as
                    InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(this.currentFocus?.windowToken, 0)

        }
    }
        private fun addNewItem() {
            // check input
            if (isEntryValid()) {
                viewModel.addNewItem(
                    binding.itemName.text.toString(),
                    binding.itemAge.text.toString()
                )
            }
        }

        private fun isEntryValid(): Boolean {
            return viewModel.isEntryValid(
                binding.itemName.text.toString(),
                binding.itemAge.text.toString()
            )
        }


}