package com.mohan.prepare

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.content.Context
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.mohan.prepare.Delegates.DataManagerDelegate
import com.mohan.prepare.exceptions.NoInternetException
import com.mohan.prepare.extensions.await
import com.mohan.prepare.model.News
import com.mohan.prepare.model.Responce
import com.mohan.prepare.service.ApiService
import com.mohan.prepare.service.DataService
import com.mohan.prepare.util.Constants
import com.mohan.prepare.viewmodel.NewsViewModel
import junit.framework.Assert
import junit.framework.Assert.fail
import kotlinx.coroutines.experimental.runBlocking
import org.json.JSONArray
import org.json.JSONObject
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import retrofit2.Call

typealias NO_INTERNET = String
typealias EMPTY_DATA = String

class NewsViewModelTest {


    private lateinit var newsViewModel: NewsViewModel
    private lateinit var dataManager: DataManager


    @get:Rule
    val looperRule: LooperRule = LooperRule()
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    internal lateinit var context: Context

    @Mock
    internal lateinit var dataPref: DataPref

    @Mock
    internal lateinit var dataService: DataService


    @Mock
    lateinit var datamanagerDel: DataManagerDelegate

    val noInterNet: NO_INTERNET = "Please check internet connection"
    val empty: EMPTY_DATA = "No news found"
    val newsList: List<News>? = listOf<News>(News("1", "title", desc = "lorem ipsum ans sksk djdj"))
    val responce = Responce(newsList)

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        runBlocking {

            datamanagerDel = Mockito.mock(DataManagerDelegate::class.java)

            dataManager = spy(DataManager(dataPref, dataService))
            newsViewModel = NewsViewModel(this@NewsViewModelTest.context, datamanagerDel)
            Mockito.`when`(this@NewsViewModelTest.context.getString(ArgumentMatchers.eq(R.string.check_internet))).thenReturn(noInterNet)
            Mockito.`when`(this@NewsViewModelTest.context.getString(ArgumentMatchers.eq(R.string.no_data_found))).thenReturn(empty)
        }
    }

    @Test
    fun `if pref is empty or null make network call`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(null)
            `when`(datamanagerDel.fetchDataFromNetwork()).thenReturn(responce)
            newsViewModel.fetchData()
            verify(datamanagerDel).fetchDataFromNetwork()
        }
    }

    @Test
    fun `if pref has data never make network call`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(newsList)
            newsViewModel.fetchData()
            verify(datamanagerDel, never()).fetchDataFromNetwork()
        }
    }

    @Test
    fun `if no internet show internet message`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(null)
            `when`(datamanagerDel.fetchDataFromNetwork()).thenThrow(NoInternetException())
            newsViewModel.fetchData()
            Assert.assertEquals(noInterNet, newsViewModel.errorMessage.value)
        }
    }

    @Test
    fun `if list is empty show empty message`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(null)
            `when`(datamanagerDel.fetchDataFromNetwork()).thenReturn(Responce(emptyList()))
            newsViewModel.fetchData()
            Assert.assertEquals(empty, newsViewModel.errorMessage.value)
        }
    }

    @Test
    fun `when network call is made save data to preference`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(emptyList())
            `when`(datamanagerDel.fetchDataFromNetwork()).thenReturn(responce)
            newsViewModel.fetchData()
            verify(datamanagerDel).saveDataToPref(ArgumentMatchers.eq(responce))
        }
    }

    @Test
    fun `when network call is made update newslist object`() {
        runBlocking {
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(emptyList())
            `when`(datamanagerDel.fetchDataFromNetwork()).thenReturn(responce)
            newsViewModel=spy(newsViewModel)
            newsViewModel.fetchData()
            verify(newsViewModel).updateList(ArgumentMatchers.eq(responce.newsList))
            Assert.assertEquals(ArgumentMatchers.eq(responce.newsList),newsViewModel.newsList.value)
        }
    }

    @Test
    fun `progressbar visibility set to gone after fetching data`() {
        runBlocking {
            Assert.assertEquals(true, newsViewModel.showProgressBar.get())
            `when`(datamanagerDel.fetchDataFromPref()).thenReturn(emptyList())
            `when`(datamanagerDel.fetchDataFromNetwork()).thenReturn(responce)
            newsViewModel.fetchData()
            Assert.assertEquals(false, newsViewModel.showProgressBar.get())
        }
    }


}