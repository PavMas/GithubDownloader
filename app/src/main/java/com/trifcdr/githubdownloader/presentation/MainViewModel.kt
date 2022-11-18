package com.trifcdr.githubdownloader.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.trifcdr.githubdownloader.domain.model.UserParams
import com.trifcdr.githubdownloader.domain.model.UsersList
import com.trifcdr.githubdownloader.domain.usecase.GetUsersUseCase
import kotlinx.coroutines.launch

/**
 * Created by trifcdr.
 */
class MainViewModel(private val getUsersUseCase: GetUsersUseCase) : ViewModel() {

    private val resultLiveUsersMutable = MutableLiveData<UsersList>()
    val resultLive: LiveData<UsersList> = resultLiveUsersMutable
    fun searchUsers(userParams: UserParams) = viewModelScope.launch{
        resultLiveUsersMutable.value = getUsersUseCase.execute(userParams)
    }

}