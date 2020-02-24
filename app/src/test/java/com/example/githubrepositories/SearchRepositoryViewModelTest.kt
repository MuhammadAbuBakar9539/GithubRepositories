package com.example.githubrepositories

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.githubrepositories.model.SearchRepositoriesResponse
import com.example.githubrepositories.repository.Repository
import com.example.githubrepositories.view_model.SearchRepositoryViewModel
import io.reactivex.Observable
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import java.lang.NullPointerException

@RunWith(MockitoJUnitRunner::class)
class SearchRepositoryViewModelTest {
    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var repository: Repository

    private lateinit var viewModel: SearchRepositoryViewModel

    @Mock
    private lateinit var searchRepositoryObserver: Observer<SearchRepositoriesResponse>

    @Mock
    private lateinit var searchError: Observer<String>

    private lateinit var model: SearchRepositoriesResponse

    @Before
    fun setup() {
        val item = SearchRepositoriesResponse.Item("java")
        viewModel = SearchRepositoryViewModel(repository)
        viewModel.getRepository().observeForever(searchRepositoryObserver)
        viewModel.getError().observeForever(searchError)
        model = SearchRepositoriesResponse(items = listOf(item), totalCount = 2)
    }

    @Test
    fun `get Searched Repositories call successfully`() {

        val list = model
        val topic = "java"
        //Given
        `when`(repository.getSearchedRepositories(topic)).thenReturn(Observable.just(list))

        //When
        viewModel.getSearchedRepositories(topic)

        //Then
        Assert.assertEquals(model, viewModel.getRepository().value)
        verify(searchRepositoryObserver).onChanged(model)
    }

    @Test
    fun `get Searched Repositories call unsuccessful`() {
        //Given
        val topic = "java"
        val error = "error"
        `when`(repository.getSearchedRepositories(topic)).thenReturn(
            Observable.error(
                NullPointerException(error)
            )
        )

        //When
        viewModel.getSearchedRepositories(topic)

        //Then
        Assert.assertEquals(error, viewModel.getError().value)
        verify(searchError).onChanged(error)

    }
}