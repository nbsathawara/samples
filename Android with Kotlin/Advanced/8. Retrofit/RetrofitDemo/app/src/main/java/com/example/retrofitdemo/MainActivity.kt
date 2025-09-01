package com.example.retrofitdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.liveData
import com.example.retrofitdemo.databinding.ActivityMainBinding
import com.example.retrofitdemo.model.AlbumItem
import com.example.retrofitdemo.model.Albums
import com.example.retrofitdemo.network.AlbumService
import com.example.retrofitdemo.network.RetrofitInstance
import kotlinx.coroutines.delay
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var albumService: AlbumService
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        albumService = RetrofitInstance.getRetrofitInstance()
            .create(AlbumService::class.java)

        //getRequestWithPathParameters()
        getRequestWithQueryParameters()
        //uploadAlbum()
    }

    private fun uploadAlbum() {
        val album = AlbumItem(0, "Sample Album 1", 1)
        val postResLiveData: LiveData<Response<AlbumItem>> = liveData {
            val response = albumService.uploadAlbum(album)
            delay(2000)
            emit(response)
        }
        postResLiveData.observe(this, Observer { response ->
            binding.tvAlbums.text = response.body()?.toString()
        })
    }

    private fun getRequestWithQueryParameters() {

        val resLiveData: LiveData<Response<Albums>> = liveData {
            val response =
                //albumService.getAlbumsByUser(7)
                albumService.getAlbums()
            emit(response)
        }

        resLiveData.observe(this, Observer {
            val albums = it.body()?.listIterator()
            if (albums != null)
                while (albums.hasNext()) {
                    val album = albums.next()
                    binding.tvAlbums.append("\n" + album.toString() + "\n")
                }
        })
    }

    private fun getRequestWithPathParameters() {

        val pathResLiveData: LiveData<Response<AlbumItem>> = liveData {
            val response = albumService.getAlbum(31)
            emit(response)
        }

        pathResLiveData.observe(this, Observer {
            Toast.makeText(this, "Album - ${it.body()?.toString()}", Toast.LENGTH_SHORT).show()
        })
    }
}