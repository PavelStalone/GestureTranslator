package com.ortin.gesturetranslator.data.db

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class RoomViewModel(
    private val database: RoomDB
) : ViewModel() {

    fun getAllItems() = viewModelScope.launch {
        database.dao.getAllItems()
    }

    fun insertItem(item: RoomEntity) = viewModelScope.launch {
        database.dao.insertItem(item)
    }

    fun updateItem(item: RoomEntity) = viewModelScope.launch {
        database.dao.updateItem(item)
    }

    fun deleteItem(item: RoomEntity) = viewModelScope.launch {
        database.dao.deleteItem(item)
    }
}
