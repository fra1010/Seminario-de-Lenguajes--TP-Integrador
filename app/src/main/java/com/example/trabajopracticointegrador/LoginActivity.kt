package com.example.trabajopracticointegrador

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.TaskStackBuilder
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class LoginActivity : AppCompatActivity() {
    val CHANNEL_ID = "NotifApi"
    val CHANNEL_NAME= "Notificacion recordar usuario"
    val NOTIF_ID=0

    private lateinit var etUsuario: EditText
    private lateinit var etContrasenia: EditText
    private lateinit var btnRegistro:Button
    private lateinit var btnLogin: Button
    private lateinit var cbRecordar: CheckBox


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        createNotifChannel()
        val intent=Intent(this, MainActivity::class.java)
        val pendingIntent = TaskStackBuilder.create(this).run {
            addNextIntentWithParentStack(intent)
            getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT)
        }
        val notif = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Se activó la opcion recordar usuario")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setSmallIcon(R.drawable.baseline_account_circle_24)
            .setContentIntent(pendingIntent)
            .build()

        val notifMan = NotificationManagerCompat.from(this)



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
                val baseDatos=UserDatabase.getDatabase(this)

                if(nUsuario.isEmpty() || pUsuario.isEmpty()){
                    Toast.makeText(this,"Faltan datos", Toast.LENGTH_SHORT).show()
                }else {
                    val verificarUsuario = baseDatos.userDao().getNombreUsuario(nUsuario)
                    if(verificarUsuario== null) {
                        Toast.makeText(this, "Usuario no registrado", Toast.LENGTH_SHORT).show()

                    }
                    else{
                        if(!verificarUsuario.usuarioContrasenia.equals(pUsuario)) {
                            Toast.makeText(this, "Contraseña incorrecta", Toast.LENGTH_SHORT).show()
                        }
                        else{
                            if (cbRecordar.isChecked) {
                                var preferenciasL= getSharedPreferences(resources.getString((R.string.sp_credenciales)), MODE_PRIVATE)
                                preferenciasL.edit().putString(resources.getString(R.string.usuario), nUsuario).apply()
                                preferenciasL.edit().putString(resources.getString(R.string.contrasenia), pUsuario).apply()

                                if (ActivityCompat.checkSelfPermission(
                                        this,
                                        Manifest.permission.POST_NOTIFICATIONS
                                    ) != PackageManager.PERMISSION_GRANTED
                                ) {
                                    // TODO: Consider calling
                                    //    ActivityCompat#requestPermissions
                                    // here to request the missing permissions, and then overriding
                                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                                    //                                          int[] grantResults)
                                    // to handle the case where the user grants the permission. See the documentation
                                    // for ActivityCompat#requestPermissions for more details.

                                }
                                notifMan.notify(NOTIF_ID,notif)
                            }

                            starMainActivity(nUsuario)

                        }
                    }



                }
        }
    }

    private fun createNotifChannel() {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            val channel = NotificationChannel(CHANNEL_ID,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT).apply {
                lightColor= Color.BLUE
                enableLights(true)
            }
            val manager= getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            manager.createNotificationChannel(channel)
        }
    }



    /*private fun getUsuario(): MutableList<Usuario> {

        var usuarioLista: MutableList<Usuario> = ArrayList()
        var ubdd= UserDatabase.getDatabase(this)
        usuarioLista.addAll(ubdd.userDao().getAllU())

        usuarioLista.add(Usuario("asdf","1234"))

        return usuarioLista
    }*/



    private fun starMainActivity(usuarioGuardado: String) {
        val intentMain = Intent(this, MainActivity::class.java)
        intentMain.putExtra(resources.getString(R.string.usuario),usuarioGuardado)
        startActivity(intentMain)
        finish()
    }
}
