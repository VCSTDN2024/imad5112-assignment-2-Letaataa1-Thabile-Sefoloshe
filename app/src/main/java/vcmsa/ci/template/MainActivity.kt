package vcmsa.ci.template

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {

    private lateinit var startBtn: Button
    private lateinit var exitBtn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        startBtn = findViewById(R.id.startBtn)
        exitBtn1 = findViewById(R.id.exitBtn1)

        startBtn.setOnClickListener{
            val intent = Intent (this, Quiz::class.java)
            startActivity(intent)
        }

        exitBtn1.setOnClickListener{
            finishAffinity()
            exitProcess(0)
        }
    }
}