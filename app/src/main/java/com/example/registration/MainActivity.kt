package com.example.registration

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import java.sql.RowId

class MainActivity : AppCompatActivity() {

    private lateinit var inputEmail: EditText
    private lateinit var inputPassword: EditText
    private lateinit var logInButton: Button
    private lateinit var registerButton: Button
    private lateinit var resetButton: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        inputEmail = findViewById(R.id.signInEmailEditText)
        inputPassword = findViewById(R.id.signInPasswordEditTextT)
        logInButton = findViewById(R.id.signInButton)
        registerButton = findViewById(R.id.goToRegistrationButton)
        resetButton = findViewById(R.id.goToPasswordResetButton)


        logInButton.setOnClickListener {
            val email = inputEmail.text.toString()
            val password = inputPassword.text.toString()
            if (email.isEmpty() || password.isEmpty()){
                Toast.makeText(this, "Empty!", Toast.LENGTH_LONG).show()
            }else{
                mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task->
                    if (task.isSuccessful){
                        startActivity(Intent(this, PersonActivity::class.java))
                        finish()
                    } else{
                        Toast.makeText(this, "ERROR", Toast.LENGTH_LONG).show()
                    }
                }
            }
        }

        registerButton.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }

        resetButton.setOnClickListener {
            startActivity(Intent(this, ResetActivity::class.java))
            finish()
        }

    }
}