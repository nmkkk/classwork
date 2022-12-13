package com.example.myapplication

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class MyViewModel(private val itemDao: ItemDao): ViewModel() {
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()
    fun addNewItem(itemName: String, itemAge: String) {
        val newItem = getNewItemEntry(itemName, itemAge)
        insertItem(newItem)
    }
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }
    private fun getNewItemEntry(itemName: String, itemAge: String): Item{
        return Item(
            itemName = itemName,
            itemAge = itemAge.toInt()
        )
    }
    fun isEntryValid(itemName: String, itemAge: String): Boolean{
        if (itemName.isBlank() || itemAge.isBlank() ) {
            return false
        }
        return true
    }

}
class MyViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return MyViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}