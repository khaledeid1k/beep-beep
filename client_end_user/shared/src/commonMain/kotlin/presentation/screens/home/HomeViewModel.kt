package presentation.screens.home


import cafe.adriel.voyager.core.model.StateScreenModel
import kotlinx.coroutines.flow.update
import presentation.screens.home.HomeUiState

class HomeViewModel : StateScreenModel<HomeUiState>(HomeUiState()){

    init {
        initializeState()
    }

    private fun initializeState() {
        mutableState.update { it.copy(text = "aya") }
    }

}