package presentation.login

data class LoginScreenUIState(
    val userName: String = "",
    val password: String = "",
    val keepLoggedIn: Boolean = false,
    val isLoading: Boolean = false,
    val error: String = "",
    val usernameError: String = "",
    val isUsernameError: Boolean = false,
    val passwordError: String = "",
    val isPasswordError: Boolean = false,
    //permission will move
    val restaurantName: String = "",
    val description: String = "",
    val ownerEmail: String = "",
    val hasPermission: Boolean = false,

    )
