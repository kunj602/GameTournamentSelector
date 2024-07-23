package com.example.gametournamentselector

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var spinnerGame: Spinner
    private lateinit var spinnerTournament: Spinner
    private lateinit var buttonShowWinner: Button
    private lateinit var textViewWinner: TextView
    private var selectedGame: String = ""
    private var selectedTournament: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        spinnerGame = findViewById(R.id.spinnerGame)
        spinnerTournament = findViewById(R.id.spinnerTournament)
        buttonShowWinner = findViewById(R.id.buttonShowWinner)
        textViewWinner = findViewById(R.id.textViewWinner)

        // Populate the spinners
        setupSpinner(spinnerGame, R.array.games_array)
        setupSpinner(spinnerTournament, R.array.tournaments_array)

        // Set listeners for the spinners
        spinnerGame.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedGame = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        spinnerTournament.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
                selectedTournament = parent.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>) {
                // Do nothing
            }
        }

        // Set the onClickListener for the button
        buttonShowWinner.setOnClickListener {
            val winner = getWinner(selectedGame, selectedTournament)
            textViewWinner.text = "Winner of $selectedTournament in $selectedGame is: $winner"
        }
    }

    private fun setupSpinner(spinner: Spinner, arrayResId: Int) {
        ArrayAdapter.createFromResource(
            this,
            arrayResId,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
    }

    private fun getWinner(game: String, tournament: String): String {
        return when (game) {
            "Soccer" -> when (tournament) {
                "World Cup" -> "Argentina"
                "Champions League" -> "Real Madrid"
                else -> "TBD"
            }
            "Basketball" -> when (tournament) {
                "Olympics" -> "USA"
                "Champions League" -> "CSKA Moscow"
                else -> "TBD"
            }
            "Baseball" -> when (tournament) {
                "World Cup" -> "Japan"
                "Champions League" -> "New York Yankees"
                else -> "TBD"
            }
            "Tennis" -> when (tournament) {
                "Grand Slam" -> "Roger Federer"
                else -> "TBD"
            }
            "Hockey" -> when (tournament) {
                "Stanley Cup" -> "Tampa Bay Lightning"
                else -> "TBD"
            }
            else -> "TBD"
        }
    }
}
