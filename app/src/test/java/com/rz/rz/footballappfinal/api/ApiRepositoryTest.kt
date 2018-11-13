package com.rz.rz.footballappfinal.api

import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify

class ApiRepositoryTest {

    @Test
    fun testPastEventsRequest() {
        val apiRepo = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getPastEventsResponse("4329")
        apiRepo.doRequest(url)
        verify(apiRepo).doRequest(url)
    }

    @Test
    fun testFutureEventsRequest() {
        val apiRepo = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getFutureEventsResponse("4329")
        apiRepo.doRequest(url)
        verify(apiRepo).doRequest(url)
    }

    @Test
    fun testTeamsRequest() {
        val apiRepo = mock(ApiRepository::class.java)
        val url = TheSportDBAPI.getTeamsResponse("Barcelona")
        apiRepo.doRequest(url)
        verify(apiRepo).doRequest(url)
    }
}
