package cl.gerardomascayano.drinksapp.ui.list

import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentActivity
import cl.gerardomascayano.drinksapp.core.OnFragmentBackPressed

class ListDrinksBackPressedCallback(
    enabled: Boolean, private val activity: FragmentActivity?, private val onFragmentBackPressed: OnFragmentBackPressed,
    private val shouldHandleCallback: () -> Boolean
) :
    OnBackPressedCallback(enabled) {

    override fun handleOnBackPressed() {
        if (shouldHandleCallback()) {
            onFragmentBackPressed.onFragmentBackPressed()
        } else {
            isEnabled = false
            activity?.onBackPressed()
        }
    }

}