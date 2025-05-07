package vcmsa.ci.template

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Quiz : AppCompatActivity() {

    private lateinit var questionTextView: TextView
    private lateinit var trueBtn: Button
    private lateinit var falseBtn: Button
    private lateinit var answerTextView: TextView
    private lateinit var nextBtn: Button

    companion object {

        val questions = arrayOf(
            "South Africa has 11 official provinces",//F
            "The judicial capital of South Africa is Johannesburg",//F
            "The world's largest diamond was found in South Africa",//T
            "The first world war began on 1 September 1914",//F
            "Nelson Mandela natively spoke Xhosa",//T
            "South Africa is home to the largest wine route"//T
        )
        val answers = booleanArrayOf(false, false, true, false, true, true)
    }

    private var currentQuestionIndex = 0
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)


        questionTextView = findViewById(R.id.questionTextView)
        trueBtn = findViewById(R.id.trueBtn)
        falseBtn = findViewById(R.id.falseBtn)
        answerTextView = findViewById(R.id.answerTextView)
        nextBtn = findViewById(R.id.nextBtn)

        displayQuestions()

        nextBtn.setOnClickListener {

            currentQuestionIndex++ // Ensure questions follow chronological sequence

            if (currentQuestionIndex < questions.size) {
                displayQuestions()
                answerTextView.text = ""
                trueBtn.isEnabled = true  // Enable functionality of buttons
                falseBtn.isEnabled = true
            } else {
                val intent = Intent(this, score_review::class.java)
                intent.putExtra("score", score) //Pushing
                startActivity(intent)
                finish() //Ensure the user cannot go back
            }
        }
        nextBtn.isEnabled = false //Disable the next button without attempt initially

        trueBtn.setOnClickListener { CheckAnswer(true) }
        falseBtn.setOnClickListener { CheckAnswer(false) }
    }

    private fun displayQuestions() {
        questionTextView.text = questions[currentQuestionIndex]
    }

    private fun CheckAnswer(userInput: Boolean) {
        val correctAnswer = answers[currentQuestionIndex]

        if (userInput == correctAnswer) {
            answerTextView.text = "CORRECT"  //Display green text for correct answer
            answerTextView.setTextColor(Color.GREEN)
            score++
        }
        else {
            answerTextView.text = "INCORRECT" //Display red text for wrong answer
            answerTextView.setTextColor(Color.RED)
        }
        trueBtn.isEnabled = false  //  disable the true and false button
        falseBtn.isEnabled = false
        nextBtn.isEnabled = true
    }
}