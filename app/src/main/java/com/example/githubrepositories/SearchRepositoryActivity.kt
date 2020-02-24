package com.example.githubrepositories

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.githubrepositories.recycle_view.SearchRepositoryAdapter
import com.example.githubrepositories.repository.RepositoryImpl
import com.example.githubrepositories.view_model.SearchRepositoryViewModel
import com.example.githubrepositories.view_model_factory.SearchRepositoryViewModelFactory
import kotlinx.android.synthetic.main.activity_search_repository.*

class SearchRepositoryActivity : AppCompatActivity() {

    private lateinit var topic: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_repository)

        topic = et_search

        val repositories =
            ViewModelProvider(this, SearchRepositoryViewModelFactory(RepositoryImpl())).get(
                SearchRepositoryViewModel::class.java
            )

        et_search.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if(s?.length !=0) {
                    repositories.getSearchedRepositories(s.toString())
                }
                else{
                    tv_hits.visibility = View.GONE
                    rv_search_repository.visibility = View.GONE
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {

            }

        })
        if (topic.text != null) {
            repositories.getRepository().observe(this, Observer { searchedRepositories ->
                val hits = searchedRepositories.totalCount.toString() + getString(R.string.hits)
                tv_hits.visibility = View.VISIBLE
                tv_hits.text = hits
                rv_search_repository.visibility = View.VISIBLE
                rv_search_repository.layoutManager = LinearLayoutManager(this)
                val searchRepositoryAdapter = SearchRepositoryAdapter(searchedRepositories)
                rv_search_repository.adapter = searchRepositoryAdapter
            })

            repositories.getError().observe(this, Observer { searchError ->
                tv_hits.visibility = View.VISIBLE
                tv_hits.text = searchError
                rv_search_repository.visibility = View.GONE
            })
        } else {
            rv_search_repository.visibility = View.GONE
        }


    }
}
