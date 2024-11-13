package com.welldone.composeapptemplate.database.testinfo

import javax.inject.Inject

class TestInfoRepository @Inject constructor(
    private val testInfoDao: TestInfoDao
) {
    suspend fun insertTestInfo(testInfo: TestInfo) {
        testInfoDao.insert(testInfo)
    }

    suspend fun updateTestInfo(testInfo: TestInfo) {
        testInfoDao.update(testInfo)
    }

    suspend fun deleteTestInfo(testInfo: TestInfo) {
        testInfoDao.delete(testInfo)
    }

    suspend fun getTestInfoById(id: String): TestInfo? {
        return testInfoDao.getyId(id)
    }

    suspend fun getAllTestInfo(): List<TestInfo> {
        return testInfoDao.getAll()
    }
}