package com.spector.template.domain.repository.room

import com.spector.template.domain.dto.sql.RefAccountsDto
import kotlinx.coroutines.flow.Flow

interface RefAccountsRepository {

    suspend fun getFlow(): Flow<List<RefAccountsDto>>

    suspend fun deleteOne(id: Int)

    suspend fun saveOne(refAccountsDto: RefAccountsDto)

    suspend fun getDto(id: Int): RefAccountsDto

}