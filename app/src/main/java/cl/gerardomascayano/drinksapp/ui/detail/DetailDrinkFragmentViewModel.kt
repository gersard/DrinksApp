package cl.gerardomascayano.drinksapp.ui.detail

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.domain.detail.DetailDrinkUseCase
import cl.gerardomascayano.drinksapp.domain.model.Drink
import kotlinx.coroutines.launch

class DetailDrinkFragmentViewModel @ViewModelInject constructor(private val useCase: DetailDrinkUseCase) : ViewModel() {

    private var _detailDrinkEvent = MutableLiveData<Resource<Drink>>()
    val detailDrinkEvent: LiveData<Resource<Drink>>
        get() = _detailDrinkEvent

    fun getDrink(drinkId: Int) {
        viewModelScope.launch {
            val drink = useCase.getDrink(drinkId)
            _detailDrinkEvent.value = Resource.Success(drink)
        }
    }


}