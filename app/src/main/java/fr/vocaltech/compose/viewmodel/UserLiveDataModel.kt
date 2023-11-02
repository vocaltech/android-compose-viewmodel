package fr.vocaltech.compose.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class UserLiveDataModel: ViewModel() {
    var currentUser = MutableLiveData(User("", ""))

    fun setUser(user: User) {
        currentUser.value = user
    }

}