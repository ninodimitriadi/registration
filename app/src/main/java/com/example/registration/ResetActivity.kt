package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class ResetActivity : AppCompatActivity() {

    private lateinit var resetEmailInput: EditText
    private lateinit var resetSendEmailButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset)

        mAuth = FirebaseAuth.getInstance()

        resetEmailInput = findViewById(R.id.resetEmailEditText)
        resetSendEmailButton = findViewById(R.id.resetSendButton)

        resetSendEmailButton.setOnClickListener {
            val email = resetEmailInput.text.toString()
            if (email.isEmpty()){
                Toast.makeText(this, "Empty", Toast.LENGTH_LONG).show()
            }else{
                mAuth.sendPasswordResetEmail(email).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        startActivity(Intent(this, MainActivity::class.java))
                        finish()
                    }else{
                        Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

    }
}