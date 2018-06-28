package com.mohan.prepare

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.mohan.prepare.adapter.ListAdapter
import com.mohan.prepare.databinding.ActivityMainBinding
import com.mohan.prepare.injections.ViewModelProviderFactory
import com.mohan.prepare.model.News
import com.mohan.prepare.viewmodel.NewsViewModel
import kotlinx.coroutines.experimental.android.UI
import kotlinx.coroutines.experimental.launch

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var model: NewsViewModel
    lateinit var listAdapter: ListAdapter<News>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        model = ViewModelProviders.of(this, ViewModelProviderFactory.getInstance()).get(NewsViewModel::class.java)
        binding.model = model
        initializeAdapter()
        bindListWithData()
        launch(UI) {
            model.fetchData()
        }
        model.errorMessage.observe(this, Observer {
            Toast.makeText(this@MainActivity,it,Toast.LENGTH_SHORT).show()
        })

    }

    private fun bindListWithData() {
        model.newsList.observe(this, Observer {
            it?.let {
                listAdapter.updateList(it)
            }
        })
    }

    private fun initializeAdapter() {

        listAdapter = object : ListAdapter<News>() {
            override fun getViewType(position: Int): Int {
                return R.layout.adapter_news_item
            }
        }
        (binding.newsList).apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            setHasFixedSize(true)
            adapter = listAdapter
        }
    }


}
