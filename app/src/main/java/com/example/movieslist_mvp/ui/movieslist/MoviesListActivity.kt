package com.example.movieslist_mvp.ui.movieslist

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.data.networking.response.similarmovies.SimilarMoviesResponse
import com.example.data.repository.MoviesRepository
import com.example.movieslist_mvp.databinding.ActivityMoviesListBinding
import com.example.movieslist_mvp.dialogerror.DialogErrorException
import com.example.utils.AppConstants
import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch

class MoviesListActivity : AppCompatActivity(), MoviesListContract.View {
    lateinit var binding: ActivityMoviesListBinding
    lateinit var presenter: MoviesListPresenter
    private lateinit var repository: MoviesRepository
    private lateinit var adapter: MoviesListAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoviesListBinding.inflate(layoutInflater)
        val root = binding.root
        setContentView(root)
        repository = MoviesRepository()
        presenter = MoviesListPresenter(this, repository)
        initView()

    }

    private fun initView() {
        lifecycleScope.launch {
            try {
                val responseMainMovie = presenter.getMainMovie()
                val responseSimilarMovies = presenter.getSimilarMovies()
                Picasso.get().load(AppConstants.BASE_URL_PHOTO + responseMainMovie?.backdrop_path).into(binding.mainMovieLogo)
                binding.likesMainMovieTv.text = responseMainMovie?.vote_count.toString()
                binding.popularutyTv.text = responseMainMovie?.popularity.toString()
                binding.mainMovieTitle.text = responseMainMovie?.title
                adapter = MoviesListAdapter(responseSimilarMovies!!)

                configRecyclerView(adapter)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun configRecyclerView(similarMoviesResponse: MoviesListAdapter) {
        binding.recyclerView.apply {
            adapter = similarMoviesResponse
            layoutManager = LinearLayoutManager(applicationContext)
        }
    }

    override fun showLoading(success: Boolean) {
        if (success) binding.movieScreenPb.visibility = View.GONE

    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_LONG).show()
    }

    override fun showErrorException(error: String) {
        DialogErrorException(context = this, titleError = error, mensageErro = error, listener = {
            initView()
        }).show()
    }
}