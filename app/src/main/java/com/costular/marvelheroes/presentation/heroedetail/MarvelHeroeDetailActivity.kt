package com.costular.marvelheroes.presentation.heroedetail

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.app.AppCompatDelegate
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.costular.marvelheroes.R
import com.costular.marvelheroes.data.model.MarvelHero
import com.costular.marvelheroes.data.repository.datasource.LocalDataSource
import com.costular.marvelheroes.domain.model.MarvelHeroEntity
import com.costular.marvelheroes.presentation.MainApp
import kotlinx.android.synthetic.main.activity_hero_detail.*
import javax.inject.Inject

/**
 * Created by costular on 18/03/2018.
 */
class MarvelHeroeDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var marvelHeroeDetailViewModel: MarvelHeroeDetailViewModel

    companion object {
        const val PARAM_HEROE = "heroe"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()
        marvelHeroeDetailViewModel = ViewModelProviders.of(this, viewModelFactory).get(MarvelHeroeDetailViewModel::class.java)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hero_detail)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
        }
        supportPostponeEnterTransition() // Wait for image load and then draw the animation



        val hero: MarvelHeroEntity? = intent?.extras?.getParcelable(PARAM_HEROE)
        hero?.let { fillHeroData(it) }

        val btnShow = findViewById<Button>(R.id.btnSave)
        btnShow?.setOnClickListener {

            if (hero!=null) {saveReg(hero, hero.id)
                showText()
            }}


    }
    fun inject() {
        (application as MainApp).component.injectDetail(this)

    }


private fun saveReg(hero: MarvelHeroEntity, userId: Int){
    val editText = findViewById<EditText>(R.id.heroEditText).text.toString()
    val hero4: List<MarvelHeroEntity> = listOf(
            MarvelHeroEntity(hero.id, hero.name, hero.photoUrl,hero.realName,hero.height,hero.power,hero.abilities,editText,hero.groups))

    marvelHeroeDetailViewModel.deleteHeroes(userId)

    marvelHeroeDetailViewModel.updateHeroe(hero4)

}

private fun showText() {
    val editText = findViewById<EditText>(R.id.heroEditText).text
    if (editText != null) {

        Toast.makeText(this, "${editText}, SAVE!!", Toast.LENGTH_LONG).show()

        onBackPressed()
    }
}





private fun fillHeroData(hero: MarvelHeroEntity) {
        Glide.with(this)
                .load(hero.photoUrl)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(e: GlideException?, model: Any?, target: Target<Drawable>?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }

                    override fun onResourceReady(resource: Drawable?, model: Any?, target: Target<Drawable>?, dataSource: DataSource?, isFirstResource: Boolean): Boolean {
                        supportStartPostponedEnterTransition()
                        return false
                    }
                })
                .into(heroDetailImage)

        heroDetailName.text = hero.name
        heroDetailRealName.text = hero.realName
        heroDetailHeight.text = hero.height
        heroDetailPower.text = hero.power
        heroDetailAbilities.text = hero.abilities
        heroEditText.setText(hero.favourite)


    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        return when(item?.itemId) {
            android.R.id.home -> {
                onBackPressed()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}