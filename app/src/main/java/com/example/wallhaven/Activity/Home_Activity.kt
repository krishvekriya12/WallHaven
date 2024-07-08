package com.example.wallhaven.Activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.wallhaven.Adapter.WallpaperAdapter
import com.example.wallhaven.Model.WallModel
import com.example.wallhaven.Retrofit.ApiClient
import com.example.wallhaven.Retrofit.ApiInterface
import com.example.wallhaven.databinding.ActivityHomeBinding
import com.google.android.material.search.SearchBar
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Home_Activity : AppCompatActivity() {

    lateinit var binding: ActivityHomeBinding
    lateinit var wallrvdata:RecyclerView
    private var wallModel :WallModel?=null
    lateinit var search:SearchBar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getSearchWallApi("all")
        binding.imgSearch.setOnClickListener {
            val searchData = binding.edtSearch.text.toString()
            getSearchWallApi(searchData)
        }
    }
    fun setRv(){
        val adapter = WallpaperAdapter(this@Home_Activity,wallModel!!.hits)
        val lm = GridLayoutManager(this,2,)
        binding.wallrvdata.layoutManager = lm
        binding.wallrvdata.adapter = adapter


    }
    fun getSearchWallApi(q:String){
        var apiInterface = ApiClient.getApiClient()?.create(ApiInterface::class.java)
        apiInterface?.searchWallPaper("41626448-dc177bdab9c7d049689854042",q,"all")?.enqueue(object : Callback<WallModel>{
            override fun onResponse(call: Call<WallModel>, response: Response<WallModel>) {
                if (response!!.code()==200)
                {
                    wallModel = response.body()
                    setRv()
                }

            }

            override fun onFailure(call: Call<WallModel>, t: Throwable) {
                Log.e("TAG","onFailure: ${t!!.message}")

            }

        })
    }

}