package com.valentinerutto.roomdbtutorial.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.roomdbtutorial.data.LineRepository
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class LineViewModel(private val repository: LineRepository) : ViewModel() {

    private val _lineList = MutableStateFlow(emptyList<PickuplineEntity>())
    val lineList = _lineList.asStateFlow()

    fun getSavedLine() {
        viewModelScope.launch(IO) {
            repository.getAllLines().collectLatest {
                _lineList.tryEmit(it)
            }
        }
    }

    suspend fun getRandomLine() {

        when (val response = repository.getRandomLine()) {

            is Resource.Error -> {

            }

            is Resource.Loading -> {

            }

            is Resource.Success -> {
                insertLine(response.data)
            }

        }
    }

    fun insertLine(entity: PickuplineEntity) {
        viewModelScope.launch(IO) {
            repository.saveRandomLine(entity)
        }
    }


//    data class UiState(
//        val loading: Boolean = false,
//        val line: PickuplineEntity  ,
//        val error: String = ""
//    )
}