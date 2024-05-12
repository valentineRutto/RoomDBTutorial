package com.valentinerutto.roomdbtutorial.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.valentinerutto.roomdbtutorial.data.LineRepository
import com.valentinerutto.roomdbtutorial.data.local.PickuplineEntity
import com.valentinerutto.roomdbtutorial.utils.Resource
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class LineViewModel(private val repository: LineRepository) : ViewModel() {

    private val _lineList = MutableStateFlow(emptyList<PickuplineEntity>())
    val lineList = _lineList.asStateFlow()

    private val _stateFlow: MutableStateFlow<UiState> =
        MutableStateFlow(UiState(lines = emptyList<PickuplineEntity>()))
    val stateFlow: StateFlow<UiState> = _stateFlow.asStateFlow()

    fun getSavedLine() {
        viewModelScope.launch(IO) {
            repository.getAllLines().collect {
                _lineList.value = it
            }
        }
    }

    suspend fun refreshData() {
        repository.refreshData()
    }

    suspend fun getListofPickUpLines() {
        repository.getandSaveListPickupLine().collect {
            when (it) {
                is Resource.Error -> setState { copy(loading = false, error = it.errorMessage) }
                is Resource.Loading -> setState { copy(loading = true) }
                is Resource.Success -> {
                    setState { copy(loading = false, lines = it.data) }
                }
            }
        }
    }

    fun getFilteredPickupLines(category: String) {
        _lineList.value.filter { it.category == category }
    }

    suspend fun getRandomLine() {

        when (val response = repository.getRandomLine()) {

            is Resource.Error -> {}

            is Resource.Loading -> {}

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

    fun deleteLine(entity: PickuplineEntity) {
        viewModelScope.launch(IO) {
            repository.deleteRandomLine(entity)
        }
    }

    private fun setState(stateReducer: UiState.() -> UiState) {
        viewModelScope.launch {
            _stateFlow.emit(stateReducer(stateFlow.value))
        }
    }

    data class UiState(
        val loading: Boolean = false,
        val lines: List<PickuplineEntity>? = emptyList(),
        val error: String = ""
    )
}