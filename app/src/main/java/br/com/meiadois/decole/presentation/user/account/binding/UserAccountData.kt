package br.com.meiadois.decole.presentation.user.account.binding

data class UserAccountData(
    var name : String = "",
    var email : String = "",
    var newPassword : String = "",
    var confirmPassword : String = ""
)