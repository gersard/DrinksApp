package cl.gerardomascayano.drinksapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gerardomascayano.drinksapp.domain.DrinkUseCase
import kotlinx.coroutines.launch

class ListDrinksFragmentViewModel @ViewModelInject constructor(private val useCase: DrinkUseCase) : ViewModel() {

    fun loadData(){
        viewModelScope.launch {
            useCase.getAllFavoriteDrinks()

            useCase.getAllUnFavoriteDrinks()
        }
    }

}