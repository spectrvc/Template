package com.spector.template.domain.useCases

import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.domain.repository.room.RefAccountsRepository
import kotlinx.coroutines.flow.Flow

class RefAccountsUseCase(private val refAccountsRepository: RefAccountsRepository) {

    suspend fun getFlow(): Flow<List<RefAccountsDto>> {
        return refAccountsRepository.getFlow()
    }

    suspend fun deleteOne(id: Int) {
        refAccountsRepository.deleteOne(id)
    }

    suspend fun saveOne(refAccountsDto: RefAccountsDto) {
        refAccountsRepository.saveOne(refAccountsDto)
    }

    suspend fun getDto(id: Int): RefAccountsDto {
        return refAccountsRepository.getDto(id)
    }
}