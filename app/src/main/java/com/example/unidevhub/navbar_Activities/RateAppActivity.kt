package com.example.unidevhub.navbar_Activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RatingBar
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unidevhub.R

class RateAppActivity : AppCompatActivity() {

    private lateinit var ratingBar: RatingBar
    private lateinit var editTextFeedback: EditText
    private lateinit var buttonSubmit: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_rate_app)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        ratingBar = findViewById(R.id.ratingBar)
        editTextFeedback = findViewById(R.id.editTextFeedback)
        buttonSubmit = findViewById(R.id.buttonSubmit)

        buttonSubmit.setOnClickListener {
            val rating = ratingBar.rating
            val feedback = editTextFeedback.text.toString()

            // You can perform any necessary actions with the rating and feedback here
            // For example, you can send the rating and feedback to a server, save it locally, etc.

            // Show a toast message to indicate that the feedback has been submitted
            Toast.makeText(this, "Feedback submitted!", Toast.LENGTH_SHORT).show()

            // Finish the activity
            finish()
        }

    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_right_out)
    }

}