package presentation.login

import presentation.base.BaseInteractionListener

interface LoginScreenInteractionListener: BaseInteractionListener {
    fun onUserNameChanged(userName: String)
    fun onPasswordChanged(password: String)
    fun onKeepLoggedInClicked()
    fun onClickLogin(userName: String, password: String, isKeepMeLoggedInChecked: Boolean)

    //region permission
    fun onOwnerEmailChanged(ownerEmail: String)
    fun onRestaurantNameChanged(restaurantName: String)
    fun onDescriptionChanged(description: String)
    fun onRequestPermissionClicked()
    fun onSubmitClicked(restaurantName: String, ownerEmail: String, description: String)
    fun onCancelClicked()
    fun onSheetBackgroundClicked()
    //endregion
}