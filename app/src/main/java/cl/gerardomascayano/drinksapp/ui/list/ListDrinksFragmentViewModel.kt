package cl.gerardomascayano.drinksapp.ui.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import cl.gerardomascayano.drinksapp.core.Resource
import cl.gerardomascayano.drinksapp.domain.DrinkUseCase
import cl.gerardomascayano.drinksapp.domain.model.Drink
import cl.gerardomascayano.drinksapp.domain.model.DrinkSearch
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import timber.log.Timber

class ListDrinksFragmentViewModel @ViewModelInject constructor(private val useCase: DrinkUseCase) : ViewModel() {

    private var _listDrinksFavoriteEvent = MutableLiveData<Resource<List<Drink>>>()
    val listDrinksFavoriteEvent: LiveData<Resource<List<Drink>>>
        get() = _listDrinksFavoriteEvent

    private var _listDrinksUnFavoriteEvent = MutableLiveData<Resource<List<Drink>>>()
    val listDrinksUnFavoriteEvent: LiveData<Resource<List<Drink>>>
        get() = _listDrinksUnFavoriteEvent

    private var _listDrinksSearched = MutableLiveData<Resource<List<DrinkSearch>>>()
    val listDrinksSearched: LiveData<Resource<List<DrinkSearch>>>
        get() = _listDrinksSearched

    var searchDrinkFlow: StateFlow<String>? = null
    set(value) {
        viewModelScope.launch {
            field = value!!
            searchDrinkFlow!!
                .debounce(300)
                .distinctUntilChanged()
                .filter { it.isNotEmpty() }
                .flowOn(Dispatchers.Default)
                .collect { drinkName -> searchDrinksByName(drinkName)}
        }
    }

    fun loadData() {
        viewModelScope.launch {
            _listDrinksFavoriteEvent.value = Resource.Loading(true)
            _listDrinksUnFavoriteEvent.value = Resource.Loading(true)

            val favoriteDrinks = useCase.getAllFavoriteDrinks()
            _listDrinksFavoriteEvent.value = Resource.Loading(false)

            withContext(Dispatchers.Main) {
                if (favoriteDrinks.isNotEmpty()) {
                    _listDrinksFavoriteEvent.value = Resource.Success(favoriteDrinks)
                } else {
                    _listDrinksFavoriteEvent.value = Resource.Empty()
                }

            }

            val unFavoriteDrinks = useCase.getAllUnFavoriteDrinks()
            _listDrinksUnFavoriteEvent.value = Resource.Loading(false)
            withContext(Dispatchers.Main) {
                if (unFavoriteDrinks.isNotEmpty()) {
                    _listDrinksUnFavoriteEvent.value = Resource.Success(unFavoriteDrinks)
                } else {
                    _listDrinksUnFavoriteEvent.value = Resource.Empty()
                }

            }
        }
    }

    private fun searchDrinksByName(name: String) {
        viewModelScope.launch {
            val drinksNames = useCase.getDrinksByName(name)
            _listDrinksSearched.value = if (drinksNames.isNotEmpty()) Resource.Success(drinksNames) else Resource.Empty()
        }
    }

}