package com.costular.marvelheroes.presentation.heroedetail

import com.costular.marvelheroes.data.repository.MarvelHeroesRepositoryImpl
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.util.mvvm.BaseViewModel
import io.reactivex.rxkotlin.addTo
import javax.inject.Inject

class MarvelHeroeDetailViewModel @Inject constructor(val marvelHeroesRepositoryImpl: MarvelHeroesRepositoryImpl): BaseViewModel() {

    fun updateHeroe(users: List<MarvelHeroEntity>){
        marvelHeroesRepositoryImpl.updateHeroes(users)

    }

    fun deleteHeroes(userId: Int){
        marvelHeroesRepositoryImpl.deleteHeroes(userId)

    }

}