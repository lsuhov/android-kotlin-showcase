package com.test.showcase.masterdetailkotlintechnology.articleList

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.test.showcase.masterdetail_kotlintechnology.R
import com.test.showcase.masterdetail_kotlintechnology.databinding.ArticlesListActivityBinding
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class ArticlesListActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ArticlesListActivityBinding
    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: ArticleListViewModel
    private lateinit var adapter: ArticlesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.articles_list_activity)
        binding.lifecycleOwner = this

        viewModel = ViewModelProviders.of(this, viewModelFactory)
                .get(ArticleListViewModel::class.java)

        binding.viewModel = viewModel

        prepareRecyclerView()
        prepareSectionsSpinner()

        viewModel.articleList.observe(this, Observer { it?.let { adapter.replaceData(it) } })
    }

    private fun prepareRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ArticlesListAdapter(viewModel.articleList.value!!, object : IArticleListItemClicked {
            override fun onArticleClicked(url: String) {
                goToArticle(url)
            }
        })
        binding.recyclerView.adapter = adapter
    }

    private fun prepareSectionsSpinner() {

        val sectionsAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, viewModel.articleSections)
        sectionsAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        binding.articleSectionsSpinner.adapter = sectionsAdapter
        binding.articleSectionsSpinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {}

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                viewModel.setArticleSectionPosition(position)
            }
        }
    }

    private fun goToArticle(url: String) {
        if (TextUtils.isEmpty(url)) {
            return
        }

        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(url)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item == null) {
            return true
        }

        return when (item.itemId) {
            R.id.filterMenuItem -> {
                viewModel.toggleFilterZoneVisibility()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
