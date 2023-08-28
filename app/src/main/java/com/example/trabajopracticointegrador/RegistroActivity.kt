package com.example.trabajopracticointegrador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast

class RegistroActivity : AppCompatActivity() {

    lateinit var etUsuarioR: EditText
    lateinit var etContraseniaR: EditText
    lateinit var chTerminos: CheckBox
    lateinit var btnRegistroR:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_registro)

        etUsuarioR=findViewById(R.id.etUsuarioRegistro)
        etContraseniaR=findViewById(R.id.etContraseñaRegistro)
        chTerminos=findViewById(R.id.cbTerminos)
        btnRegistroR=findViewById(R.id.btnRegistrar)

        btnRegistroR.setOnClickListener {
            var usuario= etUsuarioR.text.toString()

            if(usuario.isEmpty() || etContraseniaR.text.toString().isEmpty()){
                Toast.makeText(this,"Faltan datos",Toast.LENGTH_SHORT).show()
            }else {
                if (!chTerminos.isChecked){
                    Toast.makeText(this,"Debe aceptar los terminos y condiciones para continuar",Toast.LENGTH_SHORT).show()
                }else{
                    val intentMain = Intent(this, MainActivity::class.java)
                    intentMain.putExtra("usuario",usuario)
                    startActivity(intentMain)
                    finish()
                }
            }


        }
    }



}
