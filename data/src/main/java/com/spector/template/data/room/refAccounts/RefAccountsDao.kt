package com.spector.template.data.room.refAccounts

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface RefAccountsDao {

    // we'll do this in a transaction,
    // since a replacement performs two different operations: a delete and an insert
    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveOne(settings: RefAccounts)

    @Query("SELECT * FROM refAccounts WHERE id = :id")
    fun getDto(id: Int): RefAccounts?

    @Query("DELETE FROM refAccounts WHERE id = :id")
    fun deleteOne(id: Int)

    @Query("SELECT * FROM refAccounts ORDER BY name")
    fun getFlow(): Flow<List<RefAccounts>>

}