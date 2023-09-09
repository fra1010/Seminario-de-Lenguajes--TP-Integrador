package com.example.trabajopracticointegrador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    private lateinit var etUsuario: EditText
    private lateinit var etContrasenia: EditText
    private lateinit var btnRegistro:Button
    private lateinit var btnLogin: Button
    private lateinit var cbRecordar: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        etUsuario=findViewById(R.id.etUsuario)
        etContrasenia=findViewById(R.id.etContraseña)
        btnRegistro=findViewById(R.id.btnRegistro)
        btnLogin=findViewById(R.id.btnLogin)
        cbRecordar=findViewById(R.id.cbLogin)

        var preferencias = getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
        var usuarioGuardado = preferencias.getString(resources.getString(R.string.usuario), "")
        var contraseniaGuardada= preferencias.getString(resources.getString(R.string.contrasenia), "")


        if(usuarioGuardado!= null && contraseniaGuardada != ""){
            starMainActivity(usuarioGuardado)
        }


        btnRegistro.setOnClickListener {
            val intentRegistro = Intent(this, RegistroActivity::class.java)
            startActivity(intentRegistro)
            finish()
        }


        btnLogin.setOnClickListener {
                var nUsuario= etUsuario.text.toString()
                var pUsuario= etContrasenia.text.toString()

                if(nUsuario.isEmpty() || pUsuario.isEmpty()){
                    Toast.makeText(this,"Faltan datos", Toast.LENGTH_SHORT).show()
                }else {
                    if (cbRecordar.isChecked) {
                        var preferenciasL= getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                        preferenciasL.edit().putString(resources.getString(R.string.usuario), nUsuario).apply()
                        preferenciasL.edit().putString(resources.getString(R.string.contrasenia), pUsuario).apply()
                    }
                    starMainActivity(nUsuario)
                }
        }
    }

    private fun starMainActivity(usuarioGuardado: String) {
        val intentMain = Intent(this, MainActivity::class.java)
        intentMain.putExtra(resources.getString(R.string.usuario),usuarioGuardado)
        startActivity(intentMain)
        finish()
    }
}
