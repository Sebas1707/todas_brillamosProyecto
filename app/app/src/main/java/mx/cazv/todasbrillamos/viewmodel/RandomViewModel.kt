package mx.cazv.todasbrillamos.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import mx.cazv.todasbrillamos.model.services.ProductsService
import mx.cazv.todasbrillamos.model.states.RandomState

class RandomViewModel : ViewModel() {
    private val randomService = ProductsService()

    private val _state = MutableStateFlow(RandomState())
    val state: StateFlow<RandomState> = _state.asStateFlow()

    fun loadRandomInfo(token: String) {
        viewModelScope.launch {
            try {
                val products = randomService.random(token)
                _state.value = RandomState(products)
            } catch (e: Exception) {
                _state.value = RandomState()
            }
        }
    }
}