package br.com.meiadois.decole.presentation.user.account.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import br.com.meiadois.decole.data.repository.CepRepository
import br.com.meiadois.decole.data.repository.SegmentRepository
import br.com.meiadois.decole.data.repository.UserRepository

class AccountViewModelFactory(
    private val segmentRepository: SegmentRepository,
    private val userRepository: UserRepository,
    private val cepRepository: CepRepository
): ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AccountViewModel(segmentRepository, userRepository, cepRepository) as T
    }
}