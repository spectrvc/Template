package com.spector.template.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.spector.template.data.room.refAccounts.RefAccounts
import com.spector.template.data.room.refAccounts.RefAccountsDao

@Database(
    entities = [
        (RefAccounts::class),
    ],
    version = 101,
    exportSchema = true
)
abstract class TemplateDatabase : RoomDatabase() {

    abstract fun refAccountsDao(): RefAccountsDao

}