package com.spector.template.data.room

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_100_101 = object : Migration(100, 101) {
    override fun migrate(database: SupportSQLiteDatabase) {

//        // Begin SQL transaction
//        database.execSQL("BEGIN TRANSACTION;")
//
//        //creating a new table "docOrderStatus"
//        database.execSQL(
//            "CREATE TABLE IF NOT EXISTS docOrderStatus (" +
//                    " accountId INTEGER NOT NULL," +
//                    " docDate INTEGER NOT NULL," +
//                    " status TEXT NOT NULL," +
//                    " sum REAL NOT NULL," +
//                    " PRIMARY KEY (accountId, docDate)," +
//                    " FOREIGN KEY (accountId, docDate)" +
//                    " REFERENCES docOrder (accountId, docDate)" +
//                    " ON UPDATE NO ACTION ON DELETE CASCADE);"
//        )
//
//        // Commit transaction
//        database.execSQL("COMMIT;")
    }
}

