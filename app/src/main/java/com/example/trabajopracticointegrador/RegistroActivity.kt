package com.example.trabajopracticointegrador

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class RegistroActivity : AppCompatActivity() {

    private lateinit var etUsuarioR: EditText
    private lateinit var etContraseniaR: EditText
    private lateinit var chTerminos: CheckBox
    private lateinit var btnRegistroR:Button

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etUsuarioR=findViewById(R.id.etUsuarioRegistro)
        etContraseniaR=findViewById(R.id.etContraseñaRegistro)
        chTerminos=findViewById(R.id.cbTerminos)
        btnRegistroR=findViewById(R.id.btnRegistrar)

        btnRegistroR.setOnClickListener {
            var usuario= etUsuarioR.text.toString()
            var contrasenia= etContraseniaR.text.toString()
            if(usuario.isEmpty() || contrasenia.isEmpty()){
                Toast.makeText(this,"Faltan datos",Toast.LENGTH_SHORT).show()
            }else {
                if (!chTerminos.isChecked){
                    Toast.makeText(this,"Debe aceptar los terminos y condiciones para continuar",Toast.LENGTH_SHORT).show()
                }else{
                    var nuevoUsuario= Usuario(usuario,contrasenia)
                    UserDatabase.getDatabase(this).userDao().insertUsuario(nuevoUsuario)

                    val intentLogin = Intent(this, LoginActivity::class.java)
                    startActivity(intentLogin)
                    finish()
                }
            }


        }
    }



}
