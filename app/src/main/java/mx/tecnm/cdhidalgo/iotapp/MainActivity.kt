package mx.tecnm.cdhidalgo.iotapp

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    private lateinit var etUserLogin: EditText
    private lateinit var etPassLogin:EditText
    private lateinit var btnStartLogin:Button
    private lateinit var sesion:SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        etUserLogin = findViewById(R.id.etUserLogin)
        etPassLogin = findViewById(R.id.etPassLogin)
        btnStartLogin = findViewById(R.id.btnStartLogin)
        sesion = getSharedPreferences("sesion",0)
        btnStartLogin.setOnClickListener {login()}

    }
    private fun login(){
        val url = Uri.parse(Config.URL + "login")
            .buildUpon()
            .build().toString()

        //val body = JSONObject()
       // body.put("username",etUserLogin.text.toString())
      //  body.put("password",etPassLogin.text.toString())

        val peticion = object:StringRequest(Request.Method.POST,url,{
         response->with(sesion.edit()){
             putString("jwt",response)
            putString("username",etUserLogin.text.toString())
            apply()
        }
            startActivity(Intent(this,MainActivity2::class.java))
        },{
            error -> Toast.makeText(this,error.toString(),Toast.LENGTH_LONG).show()
        }){
            override fun getParams():Map<String,String>{
                val params:MutableMap<String,String> = HashMap()
                params["username"] = etUserLogin.text.toString()
                params["password"] = etPassLogin.text.toString()
                return params
            }
        }
        MySingleton.getInstance(applicationContext).addToRequestQueue(peticion)
    }
}