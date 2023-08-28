package com.example.trabajopracticointegrador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasenia: EditText
    private lateinit var btnRegistro:Button
    private lateinit var btnLogin: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario=findViewById(R.id.etUsuario)
        etContrasenia=findViewById(R.id.etContraseña)
        btnRegistro=findViewById(R.id.btnRegistro)
        btnLogin=findViewById(R.id.btnLogin)

        btnRegistro.setOnClickListener {
            val intentRegistro = Intent(this, RegistroActivity::class.java)
            startActivity(intentRegistro)
            finish()

        }


        btnLogin.setOnClickListener {
                var usuario= etUsuario.text.toString()

                if(usuario.isEmpty() || etContrasenia.text.toString().isEmpty()){
                    Toast.makeText(this,"Faltan datos", Toast.LENGTH_SHORT).show()
                }else {
                        val intentMain = Intent(this, MainActivity::class.java)
                        intentMain.putExtra("usuario",usuario)
                        startActivity(intentMain)
                        finish()
                }
        }


    }


}
