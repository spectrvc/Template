package com.spector.template.data.room.refAccounts

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "refAccounts")
class RefAccounts(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var name: String = "",

    var login: String = "",

)

