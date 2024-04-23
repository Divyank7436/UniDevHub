package com.example.unidevhub.auth

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.unidevhub.MainActivity
import com.example.unidevhub.R
import com.example.unidevhub.databinding.ActivityLoginBinding
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class LoginActivity : AppCompatActivity() {


    companion object {
        private const val RC_SIGN_IN = 9001
    }

    private lateinit var binding : ActivityLoginBinding
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser

        if (currentUser != null) {
            // The user is already signed in, navigate to MainActivity
            Toast.makeText(this@LoginActivity, "Leaving Login Activity ", Toast.LENGTH_SHORT).show()

            val intent = Intent(this, MainActivity::class.java)
            Toast.makeText(this@LoginActivity, "Entering Main Activity ", Toast.LENGTH_SHORT).show()

            startActivity(intent)
            finish() // finish the current activity to prevent the user from coming back to the SignInActivity using the back button
        }

        val signUpBtn = findViewById<TextView>(R.id.signup)

        binding.button3.setOnClickListener {
            if(binding.email.text.toString().isNotEmpty() && binding.password.text.toString().isNotEmpty()){
                signInWithEmail(binding.email.text.toString(), binding.password.text.toString())
            }else{
                Toast.makeText(this, "Please fill above fields!", Toast.LENGTH_LONG).show()
            }
        }




        signUpBtn.setOnClickListener {
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.googleBtn.setOnClickListener {
            signIn()
        }

    }


    private fun signInWithEmail(email: String, password: String) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password).addOnCompleteListener {
                task ->
            run {
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
                else{
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun signIn() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()



        val googleSignInClient = GoogleSignIn.getClient(this, gso)
        val signInIntent = googleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val account = task.getResult(ApiException::class.java)
                firebaseAuthWithGoogle(account.idToken!!)
            } catch (e: ApiException) {
                Toast.makeText(this, "Google sign in failed: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun firebaseAuthWithGoogle(idToken: String) {
        val credential = GoogleAuthProvider.getCredential(idToken, null)
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {

//                    val user =  User(
//                        userId =  auth.currentUser!!.uid,
//                        username = auth.currentUser!!.displayName!!,
//                        email = auth.currentUser!!.email!!
//                    )
//                    FirebaseDatabase.getInstance().getReference("Users")
//                        .child(auth.currentUser!!.uid)
//                        .setValue(user)
//                        .addOnCompleteListener{
//                            if (it.isSuccessful){
//                                Toast.makeText(this, "Signed in as ${user.username}", Toast.LENGTH_SHORT).show()
//                                startActivity(Intent(this, MainActivity::class.java))
//                                finish()
//                            }
//                            else{
//                                Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
//                            }
//                        }

                    Toast.makeText(this, "Signed in as ${auth.currentUser!!.displayName}", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                } else {
                    Toast.makeText(this, "Authentication failed", Toast.LENGTH_SHORT).show()
                }
            }
    }



    }