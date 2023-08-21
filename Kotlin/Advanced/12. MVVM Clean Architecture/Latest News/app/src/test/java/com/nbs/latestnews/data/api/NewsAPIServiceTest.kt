package com.nbs.latestnews.data.api

import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class NewsAPIServiceTest {

    private lateinit var newsAPIService: NewsAPIService
    private lateinit var mockWebServer: MockWebServer

    @Before
    fun setUp() {
        mockWebServer = MockWebServer()
        newsAPIService = Retrofit.Builder()
            .baseUrl(mockWebServer.url(""))
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewsAPIService::class.java)
    }

    private fun enqueueMockResponse(fileName: String) {
        val inputStream = javaClass.classLoader!!.getResourceAsStream(fileName)!!
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        mockResponse.setBody(source.readString(Charsets.UTF_8))
        mockWebServer.enqueue(mockResponse)
    }

    @Test
    fun getTopHeadlines_sentRequest_receivedExpected() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("in", 1).body()
            val request = mockWebServer.takeRequest()

            Truth.assertThat(request.path)
                .isEqualTo("/top-headlines?country=in&page=1&apiKey=8f3fddf391094922b79027f42014140d")
            Truth.assertThat(responseBody).isNotNull()
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctPageSie() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("in", 1).body()
            val articleList = responseBody!!.articles
            Truth.assertThat(articleList.size).isEqualTo(20)
        }
    }

    @Test
    fun getTopHeadlines_receivedResponse_correctContent() {
        runBlocking {
            enqueueMockResponse("newsresponse.json")
            val responseBody = newsAPIService.getTopHeadlines("in", 1).body()
            val articleList = responseBody!!.articles
            val article = articleList[0]
            Truth.assertThat(article.author).isEqualTo("Sportstar")
            Truth.assertThat(article.publishedAt).isEqualTo("2023-08-20T10:30:00Z")
            Truth.assertThat(article.url)
                .isEqualTo("https://sportstar.thehindu.com/athletics/world-athletics-championships-2023-live-updates-day-two-indians-in-action-results-schedule-timings-live-streaming-info-budapest/article67215806.ece")
        }
    }

    @After
    fun tearDown() {
        mockWebServer.shutdown()
    }
}
