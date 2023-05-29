package com.example.diceroller

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

/**
 * This activity allows the user to roll a dice and view the result
 * on the screen.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDice() }
        //roll dice on app start up
        rollDice()

    }
    /**
     * Roll the dice and update the screen with the result.
     */
    private fun rollDice() {
        //creates a dice object and roll it
        val dice1Result = Dice().roll()
        val dice2Result = Dice().roll()
        //get the ImageView in the layout
        val dice1Image: ImageView = findViewById(R.id.imageView)
        val dice2Image: ImageView = findViewById(R.id.imageView2)
        //get the drawable resource ids of the dice images based on the rolled number (1..6)
        val dice1ImageResourceId: Int  = getDiceImageResource(dice1Result)
        val dice2ImageResourceId: Int = getDiceImageResource(dice2Result)
        //updates the ImageView base on the resource id
        dice1Image.setImageResource(dice1ImageResourceId)
        dice2Image.setImageResource(dice2ImageResourceId)
        //set the content description of the image
        dice1Image.contentDescription = "$dice1Result"
        dice2Image.contentDescription = "$dice2Result"

    }

    private  fun getDiceImageResource(diceResult: Int): Int{
       return when (diceResult){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
    }
}

class Dice(private val numOfSides: Int = 6) {
    fun roll(): Int {
        val range: IntRange = 1..numOfSides
        return range.random()
    }
}