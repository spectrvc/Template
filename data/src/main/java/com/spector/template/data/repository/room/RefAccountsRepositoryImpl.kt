package com.spector.template.data.repository.room

import com.spector.template.data.room.refAccounts.RefAccounts
import com.spector.template.data.room.refAccounts.RefAccountsDao
import com.spector.template.domain.dto.sql.RefAccountsDto
import com.spector.template.domain.repository.room.RefAccountsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RefAccountsRepositoryImpl @Inject constructor(private val refAccountsDao: RefAccountsDao) :
    RefAccountsRepository {

    override suspend fun getFlow(): Flow<List<RefAccountsDto>> {
        return refAccountsDao.getFlow()
            .map { list -> list.map { it.toRefAccountsDto() } }
    }

    override suspend fun deleteOne(id: Int) {
        refAccountsDao.deleteOne(id)
    }

    override suspend fun saveOne(refAccountsDto: RefAccountsDto) {
        refAccountsDao.saveOne(refAccountsDto.toRefAccounts())
    }

    override suspend fun getDto(id: Int): RefAccountsDto {
        var record = refAccountsDao.getDto(id)
        if (record == null)
            record = RefAccounts()
        return record.toRefAccountsDto()
    }

    private fun RefAccounts.toRefAccountsDto(): RefAccountsDto {
        return RefAccountsDto(
            id = this.id,
            name = this.name,
            login = this.login,
        )
    }

    private fun RefAccountsDto.toRefAccounts(): RefAccounts {
        return RefAccounts(
            id = this.id,
            name = this.name,
            login = this.login,
        )
    }

}