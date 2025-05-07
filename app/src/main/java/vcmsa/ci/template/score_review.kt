package vcmsa.ci.template

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.SeekBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class score_review : AppCompatActivity() {
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_score_review)

         val resultsTextView = findViewById<TextView>(R.id.resultsTextView)
         val verdictTextView = findViewById<TextView>(R.id.verdictTextView)
         val restartBtn = findViewById<Button>(R.id.restartBtn)
         val exitBtn2 = findViewById<Button>(R.id.exitBtn2)
         val reviewTextView = findViewById<TextView>(R.id.reviewTextView)

        // Pushing questions and answers
        intent.putExtra("questions", Quiz.questions)
        intent.putExtra("answers", Quiz.answers)
        // Get questions and answers
        val questions = intent.getStringArrayExtra("questions")
        val answers = intent.getBooleanArrayExtra("answers")

        val score = intent.getIntExtra("score", 0)
        resultsTextView.text = "Your score is: $score/6" // Display score to the user

        // Feedback to the user based on performance
        val feedback = if (score >= 3) {
            "Excellent job!"
        } else {
            "Almost there!Keep practicing"
        }
        // Feedback to user based on performance
        verdictTextView.text = feedback

        val reviewText = StringBuilder()
        if (questions != null && answers != null && questions.size == answers.size) {
            for (i in questions.indices) {
                reviewText.append("${i + 1}. ${questions[i]}\n")
                reviewText.append(" Answer: ${if (answers[i]) "True" else "False"}\n\n")
            }
            reviewTextView.text = reviewText.toString()
        }
        else {
            reviewTextView.text = "Error occured loading review data."
        }

        // Take the user back to the home screen
        restartBtn.setOnClickListener{
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        // Exit the application
        exitBtn2.setOnClickListener {
            finishAffinity()
            exitProcess(0)
        }
    }
}