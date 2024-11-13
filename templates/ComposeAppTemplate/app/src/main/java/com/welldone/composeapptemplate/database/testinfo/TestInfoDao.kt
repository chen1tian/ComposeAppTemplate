package com.welldone.composeapptemplate.database.testinfo

import androidx.room.Dao
import androidx.room.Query
import com.welldone.composeapptemplate.database.IDaoBase
import com.welldone.composeapptemplate.database.testinfo.TestInfo

@Dao
interface TestInfoDao : IDaoBase<TestInfo> {
    @Query("SELECT * FROM TestInfo WHERE testId = :testId")
    suspend fun getyId(testId: String): TestInfo?

    @Query("SELECT * FROM TestInfo")
    suspend fun getAll(): List<TestInfo>
}