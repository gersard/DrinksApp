package cl.gerardomascayano.drinksapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.domain.DrinkUseCase
import cl.gerardomascayano.drinksapp.domain.model.Drink
import kotlinx.coroutines.launch

class ListDrinksFragmentViewModel @ViewModelInject constructor(private val useCase: DrinkUseCase) : ViewModel() {

    private var _listDrinksFavoriteEvent = MutableLiveData<Resource<List<Drink>>>()
    val listDrinksFavoriteEvent: LiveData<Resource<List<Drink>>>
        get() = _listDrinksFavoriteEvent

    private var _listDrinksUnFavoriteEvent = MutableLiveData<Resource<List<Drink>>>()
    val listDrinksUnFavoriteEvent: LiveData<Resource<List<Drink>>>
        get() = _listDrinksUnFavoriteEvent

    fun loadData() {
        viewModelScope.launch {
            _listDrinksFavoriteEvent.value = Resource.Loading(true)
            _listDrinksUnFavoriteEvent.value = Resource.Loading(true)

            val favoriteDrinks = useCase.getAllFavoriteDrinks()
            _listDrinksFavoriteEvent.value = Resource.Loading(false)
            if (favoriteDrinks.isNotEmpty()) {
                _listDrinksFavoriteEvent.value = Resource.Success(favoriteDrinks)
            } else {
                _listDrinksFavoriteEvent.value = Resource.Empty()
            }


            val unFavoriteDrinks = useCase.getAllUnFavoriteDrinks()
            _listDrinksUnFavoriteEvent.value = Resource.Loading(false)
            if (unFavoriteDrinks.isNotEmpty()) {
                _listDrinksUnFavoriteEvent.value = Resource.Success(unFavoriteDrinks)
            } else {
                _listDrinksUnFavoriteEvent.value = Resource.Empty()
            }

        }
    }

}