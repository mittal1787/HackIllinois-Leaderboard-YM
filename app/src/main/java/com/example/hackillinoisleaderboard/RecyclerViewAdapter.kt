package com.example.hackillinoisleaderboard

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyclerViewAdapter(private var peopleList: List<Person>) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val rankText: TextView
        val discordIdText: TextView
        val scoreView: TextView

        init {
            // Define click listener for the ViewHolder's View
            rankText = view.findViewById(R.id.rank)
            discordIdText = view.findViewById(R.id.discordId)
            scoreView = view.findViewById(R.id.score)
        }
    }

    fun updateList(newList: List<Person>) {
        peopleList = newList
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.leaderboard_item, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.rankText.text = peopleList[position].rank
        viewHolder.discordIdText.text = peopleList[position].discordId
        viewHolder.scoreView.text = peopleList[position].score.toString()
        if (peopleList[position].rank.equals("1")) {
            viewHolder.rankText.setTextColor(Color.rgb(255,215,0))
        }
        else if (peopleList[position].rank.equals("2")) {
            viewHolder.rankText.setTextColor(Color.rgb(192,192,192))
        }
        else if (peopleList[position].rank.equals("3")) {
            viewHolder.rankText.setTextColor(Color.rgb(205, 127, 50))
        } else {
            viewHolder.rankText.setTextColor(Color.BLACK)
        }

    }

    override fun getItemCount() = peopleList.size
}