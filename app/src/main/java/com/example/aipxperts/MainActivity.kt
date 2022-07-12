package com.example.aipxperts

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.aipxperts.room.AppDatabase
import com.example.aipxperts.room.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity :   AppCompatActivity() {
    lateinit var rec_response:RecyclerView
    lateinit var res:List<response_model>
    lateinit var adapter:responseAdapter
    lateinit var db:AppDatabase
    var temp:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rec_response = findViewById(R.id.rec_response)
        val mLayoutManager = LinearLayoutManager(applicationContext)
        rec_response.setLayoutManager(mLayoutManager)
        db = Room.databaseBuilder(this,AppDatabase::class.java,"User").allowMainThreadQueries().build()
        addAdaptertoRecyclerView()

        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val api:ApiInterface  = retrofit.create(ApiInterface::class.java)


        api.GetResponse().enqueue(object :Callback<List<response_model>>{
            override fun onResponse(
                call: Call<List<response_model>>,
                response: Response<List<response_model>>
            ) {
                res = response.body()!!

                for(temp in res){
                    db.userDao().insert(User(null,temp.postid,temp.id,temp.name,temp.email,temp.body))
                }
                addAdaptertoRecyclerView()

            }

            override fun onFailure(call: Call<List<response_model>>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun addAdaptertoRecyclerView() {
        val test = db.userDao().getAllUsers()
        adapter = responseAdapter(this@MainActivity,test)
        rec_response.adapter = adapter

        findViewById<SearchView>(R.id.search).setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val test = db.userDao().getAllUsers().filter { it.name.contains(newText.toString(),true) }
                adapter = responseAdapter(this@MainActivity,test)
                rec_response.adapter = adapter
                return false
            }
        })
    }
}