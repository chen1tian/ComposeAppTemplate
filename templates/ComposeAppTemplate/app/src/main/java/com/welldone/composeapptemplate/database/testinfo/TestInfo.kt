package com.welldone.composeapptemplate.database.testinfo

import androidx.room.Entity

@Entity(primaryKeys = ["testId"])
data class TestInfo(
    val name: String,
    val testId: String
)