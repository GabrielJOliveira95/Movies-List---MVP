package com.example.movieslist_mvp.ui.movieslist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.example.data.repository.MoviesRepository
import com.example.movieslist_mvp.R
import com.example.movieslist_mvp.databinding.ActivityMoviesListBinding
import com.example.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class MoviesListActivity : AppCompatActivity(), MoviesListContract.View {
    lateinit var binding: ActivityMoviesListBinding
    lateinit var presenter: MoviesListPresenter
    private lateinit var repository: MoviesRepository
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        repository = MoviesRepository()
        presenter = MoviesListPresenter(this, repository)
        initView()

    }

    private fun initView(){
        lifecycleScope.launch{
          val responseMainMovie = presenter.getMainMovie()
          binding.likesMainMovieTv.text = responseMainMovie?.vote_count.toString()
          binding.popularutyTv.text = responseMainMovie?.popularity.toString()
          binding.mainMovieTitle.text = responseMainMovie?.title
          Picasso.get().load(AppConstants.BASE_URL_PHOTO + responseMainMovie?.backdrop_path).into(binding.mainMovieLogo)
        }
    }

    override fun showLoading(success: Boolean) {
        if (success) binding.movieScreenPb.visibility = View.GONE

    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }
}