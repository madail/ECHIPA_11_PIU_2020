package com.example.ustoyou.VisualAid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.RatingBar
import android.widget.TextView
import android.widget.Toast
import com.example.ustoyou.CategoryActivity
import com.example.ustoyou.R

class VisualAidRateActivity : AppCompatActivity() {
    private lateinit var virtualLessonAssistentRating: RatingBar
    private lateinit var virtualLessonInterfaceRating: RatingBar
    private lateinit var virtualLessonServiceRating: RatingBar

    private lateinit var assistantRatingLabel: TextView
    private lateinit var interfaceRatingLabel: TextView
    private lateinit var serviceRatingLabel: TextView

    private lateinit var visualAidSkipOrSaveRating: Button

    private var isInterfaceRatingShowing: Boolean = false
    private var isServiceRatingShowing: Boolean = false
    private var isSkipSure: Boolean = false
    private var isBackSure: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_visual_aid_rate)

        supportActionBar?.title = getString(R.string.us_to_you)

        bindItems()

        initLayout()

        Toast.makeText(this@VisualAidRateActivity, "Give a rating from 1 to 4 for the person who assisted you", Toast.LENGTH_SHORT).show()
    }

    private fun bindItems() {
        virtualLessonAssistentRating  = findViewById(R.id.providerRating)
        virtualLessonInterfaceRating  = findViewById(R.id.contentRating)
        virtualLessonServiceRating  = findViewById(R.id.serviceRating)
        assistantRatingLabel = findViewById(R.id.providerRatingLabel)
        interfaceRatingLabel = findViewById(R.id.contentRatingLabel)
        serviceRatingLabel = findViewById(R.id.serviceRatingLabel)
        visualAidSkipOrSaveRating = findViewById(R.id.visualAidSkipOrSaveRating)
    }

    private fun initLayout() {
        assistantRatingLabel.text = getString(R.string.assistant)
        interfaceRatingLabel.text = getString(R.string.interface_label)


        virtualLessonInterfaceRating.visibility = View.INVISIBLE
        virtualLessonServiceRating.visibility = View.INVISIBLE

        interfaceRatingLabel.visibility = View.INVISIBLE
        serviceRatingLabel.visibility = View.INVISIBLE

        visualAidSkipOrSaveRating.text = getString(R.string.skip)
    }

    fun onDefaultIconClick(view: View) {
        if (!isInterfaceRatingShowing) {
            if (virtualLessonAssistentRating.rating > 0.0F) {
                Toast.makeText(this@VisualAidRateActivity, "Give a rating from 1 to 4 for the application flow", Toast.LENGTH_SHORT).show()
                isInterfaceRatingShowing = true
                virtualLessonInterfaceRating.visibility = View.VISIBLE
                interfaceRatingLabel.visibility = View.VISIBLE
                isSkipSure = false
            } else {
                Toast.makeText(this@VisualAidRateActivity, "The rate for the assistent must be a number between 1 and 4", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (!isServiceRatingShowing) {
                if (virtualLessonInterfaceRating.rating > 0.0F) {
                    Toast.makeText(
                        this@VisualAidRateActivity,
                        "Give a rating from 1 to 4 for the entire service",
                        Toast.LENGTH_SHORT
                    ).show()
                    isServiceRatingShowing = true
                    isSkipSure = false

                    virtualLessonServiceRating.visibility = View.VISIBLE
                    serviceRatingLabel.visibility = View.VISIBLE

                    visualAidSkipOrSaveRating.text = getString(R.string.save)
                } else {
                    Toast.makeText(this@VisualAidRateActivity, "The rate for the interface must be a number between 1 and 4", Toast.LENGTH_SHORT).show()
                }
            } else {
                if (virtualLessonServiceRating.rating > 0.0F) {
                    onSaveRating()
                } else {
                    Toast.makeText(this@VisualAidRateActivity, "The rate for the overall service must be a number between 1 and 4", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onButtonClick(view: View) {
        if (!isServiceRatingShowing) {
            if (!isSkipSure) {
                Toast.makeText(this@VisualAidRateActivity, "Are you sure you want to skip the rating? You won't be able to come back.", Toast.LENGTH_SHORT).show()
                isSkipSure = true
            } else {
                Toast.makeText(this@VisualAidRateActivity, "Rating skipped. Have a good day", Toast.LENGTH_SHORT).show()
            }
        } else {
            if (virtualLessonServiceRating.rating > 0.0F) {
                onSaveRating()
            } else {
                Toast.makeText(this@VisualAidRateActivity, "The rate for the overall service must be a number between 1 and 4", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun onSaveRating() {
        Toast.makeText(
            this@VisualAidRateActivity,
            "Saving your ratings. Have a good day.",
            Toast.LENGTH_SHORT
        ).show()
        val intent = Intent(this, CategoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    override fun onBackPressed() {
        if (!isBackSure) {
            isBackSure = true
            Toast.makeText(this@VisualAidRateActivity, "Are you sure you want to skip the rating? You won't be able to come back.", Toast.LENGTH_SHORT).show()
        } else {
            onSaveRating()
        }
    }
}