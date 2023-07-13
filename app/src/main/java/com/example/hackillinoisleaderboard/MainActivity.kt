package com.example.hackillinoisleaderboard

import android.os.Bundle
import android.os.StrictMode
import android.os.StrictMode.ThreadPolicy
import android.text.InputType
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import org.json.JSONObject
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val policy = ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        val adapter = RecyclerViewAdapter(getLeaderboard())

        setContentView(R.layout.activity_main)
        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(applicationContext)


        val limitSearchView: SearchView = findViewById(R.id.limit)
        limitSearchView.setInputType(InputType.TYPE_CLASS_NUMBER)
        limitSearchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrBlank()) {
                        adapter.updateList(getLeaderboard())
                    } else {
                        adapter.updateList(getLeaderboard(query))
                    }

                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank()) {
                        adapter.updateList(getLeaderboard())
                    }
                    else {
                        adapter.updateList(getLeaderboard(newText))
                    }
                    return false
                }
            })

    }

    fun getLeaderboard() : List<Person> {
        val items = JSONObject(URL("https://api.hackillinois.org/profile/leaderboard/?limit=20").readText()).optJSONArray("profiles")
        val people: MutableList<Person> = mutableListOf()
        for (i in 0 until items.length()) {
            val currentPerson = items.getJSONObject(i)
            val id = currentPerson.optString("id")
            val ranking : Int = i + 1
            val discord = currentPerson.optString("discord")
            val score = currentPerson.optInt("points")
            people.add(Person(id,discord,ranking.toString(), score))
            print(id)
            print(ranking)
            print(discord)
            print(score)
        }
        
        return people
    }

    fun getLeaderboard(limit: String?) : List<Person> {
        val items = JSONObject(URL("https://api.hackillinois.org/profile/leaderboard/?limit=${limit}").readText()).optJSONArray("profiles")
        val people: MutableList<Person> = mutableListOf()
        for (i in 0 until items.length()) {
            val currentPerson = items.getJSONObject(i)
            val id = currentPerson.optString("id")
            val ranking : Int = i + 1
            val discord = currentPerson.optString("discord")
            val score = currentPerson.optInt("points")
            people.add(Person(id,discord,ranking.toString(), score))
            print(id)
            print(ranking)
            print(discord)
            print(score)
        }

        return people
    }
}