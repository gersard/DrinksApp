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

    var drink: Drink? = null

    private var _detailDrinkEvent = MutableLiveData<Resource<Drink>>()
    val detailDrinkEvent: LiveData<Resource<Drink>>
        get() = _detailDrinkEvent

    fun getDrink(drinkId: Int) {
        viewModelScope.launch {
            drink = useCase.getDrink(drinkId).also { _detailDrinkEvent.value = Resource.Success(it) }
        }
    }


    fun updateDrink(){
        viewModelScope.launch {
            val success = useCase.updateDrink(drink!!)
            if (success){

            }else{

            }
        }
    }
}