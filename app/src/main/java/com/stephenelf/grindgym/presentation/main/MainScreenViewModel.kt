package com.stephenelf.grindgym.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.stephenelf.grindgym.R
import com.stephenelf.grindgym.domain.repository.GymRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val gymRepository: GymRepository
) :
    ViewModel() {

    private val _userMessage: MutableStateFlow<Int?> = MutableStateFlow(null)
    private val _isLoading = MutableStateFlow(false)


    val uiState: StateFlow<MainScreenState> =
        combine(
            _isLoading,
            _userMessage,
            gymRepository.getGyms()
        ) { loading, message, remoteGymList ->
            MainScreenState(gymList = remoteGymList, userMessage = message, isLoading = loading)
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000L),
            initialValue = MainScreenState(emptyList(), isLoading = true)
        )

    fun refresh() {
        _isLoading.value = true
        viewModelScope.launch {
            gymRepository.getGyms()
            _isLoading.value = false
        }
    }

    fun handleLike() {
        showSnackbarMessage(R.string.like_gym)
        checkMatch()
    }

    fun handleDislike() {
        showSnackbarMessage(R.string.dislike_gym)
    }

    fun checkMatch() {
        //get user position as uistate flow
        // distance from current position to gym < Threshold
        // show message if match
    }

    fun snackbarMessageShown() {
        _userMessage.value = null
    }

    private fun showSnackbarMessage(message: Int) {
        _userMessage.value = message
    }

}