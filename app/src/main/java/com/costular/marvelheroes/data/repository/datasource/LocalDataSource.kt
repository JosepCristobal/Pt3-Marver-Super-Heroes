package com.costular.marvelheroes.data.repository.datasource

import com.costular.marvelheroes.data.db.MarvelHeroDatabase
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.model.mapper.MarvelHeroMapper
import com.costular.marvelheroes.data.repository.MarvelHeroesRepository
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers


class LocalDataSource (val marvelHeroDatabase: MarvelHeroDatabase
                         ): MarvelHeroesRepository {


    override fun getMarvelHeroesList(): Observable<List<MarvelHeroEntity>> =
                    marvelHeroDatabase
                            .getMarvelHeroDao()
                            .getAllUsers()
                            .toObservable()

   override fun saveHeroes(users: List<MarvelHeroEntity>) {
      Observable.fromCallable {
         marvelHeroDatabase.getMarvelHeroDao().removeAndInsertUsers(users)
      }
              .subscribeOn(Schedulers.io())
              .subscribe()
   }

    override fun deleteMarvelHeroesList() {
        marvelHeroDatabase
                .getMarvelHeroDao()
                .deleteAllUsers()
    }

    override fun updateHeroes(users: List<MarvelHeroEntity>) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().updateUser(users)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()    }

    override fun deleteHeroes(userId: String) {
        Observable.fromCallable {
            marvelHeroDatabase.getMarvelHeroDao().deleteUser(userId)
        }
                .subscribeOn(Schedulers.io())
                .subscribe()    }

}
