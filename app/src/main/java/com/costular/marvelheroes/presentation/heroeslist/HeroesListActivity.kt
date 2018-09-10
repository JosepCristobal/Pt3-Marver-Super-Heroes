package com.costular.marvelheroes.presentation.heroeslist

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.view.View
import android.widget.Toast
import com.costular.marvelheroes.R

import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.MainApp
import com.costular.marvelheroes.presentation.util.Navigator
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class HeroesListActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var navigator: Navigator

    lateinit var userListViewModel: HeroesListViewModel

    lateinit var adapter: HeroesListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUp()
    }

    fun inject() {
        (application as MainApp).component.inject(this)
    }

    private fun setUp() {
        setUpRecycler()
        userListViewModel = ViewModelProviders.of(this, viewModelFactory).get(HeroesListViewModel::class.java)
        bindEvents()
        userListViewModel.loadMarvelHeroesList()
    }

    private fun setUpRecycler() {
        adapter = HeroesListAdapter { hero, image -> goToHeroDetail(hero, image) }
        heroesListRecycler.layoutManager = GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false)
        heroesListRecycler.itemAnimator = DefaultItemAnimator()
        heroesListRecycler.adapter = adapter
    }

    private fun goToHeroDetail(hero: MarvelHeroEntity, image: View) {
        navigator.goToHeroDetail(this, hero, image)
    }
    private fun bindEvents() {
        userListViewModel.isLoadingState.observe(this, Observer { isLoading ->
            isLoading?.let {
                showLoading(it)
            }
        })

        userListViewModel.marvelHeroListState.observe(this, Observer { userList ->
            userList?.let {
                onMarvelListLoaded(it)
            }
        })
    }
    private fun onMarvelListLoaded(userList: List<MarvelHeroEntity>) {
        adapter.submitList(userList)
    }

    private fun showLoading(isLoading: Boolean) {
        heroesListLoading.visibility = if(isLoading) View.VISIBLE else View.GONE
    }

}
