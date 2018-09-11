package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.model.MarvelHero
import io.reactivex.Observable

/**
 * Created by costular on 05/06/2018.
 */
class FakeMarvelHeroesDataSource : MarvelHeroesDataSource {

    override fun getMarvelHeroesList(): Observable<List<MarvelHero>> {
        return Observable.just(
                arrayListOf(
                        MarvelHero(1, "Fake01","https://i.blogs.es/30cb7a/blackpanther5/450_1000.jpg"),
                        MarvelHero(2, "Fake02","https://thefreakchoice.com/2833-home_default/cojin-arrow-flechas-verde-.jpg")
                )
        )
    }

}