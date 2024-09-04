package com.example.hellojose;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Timer;
import java.util.TimerTask;

public class LoginTodosalaUActivity extends AppCompatActivity {

    Button btnLogin;
    EditText edtUser;
    EditText edtClave;

    String emailLogin = "josed.developer@gmail.com";
    String passwordLogin = "12345";

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_todosala_uactivity);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnLogin = findViewById(R.id.btnLogin);
        edtUser = findViewById(R.id.edtUser);
        edtClave = findViewById(R.id.edtClave);

        sharedPreferences = getSharedPreferences("myPreference", Context.MODE_PRIVATE);

//      This part of the code is to keep the session open if the credentials have already been entered.
        String emailLogged = sharedPreferences.getString("email","");
        if (!emailLogged.equals("")){
            Intent intent = new Intent(LoginTodosalaUActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }

        //code toast with timer
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                LoginTodosalaUActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LoginTodosalaUActivity.this,"Time's gone",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        };
        timer.schedule(task,9000);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("LoginTodosalaUActivity","On click of the button Sign In.");
                Log.e("LoginTodosalaUActivity","User: "+ edtUser.getText());
                Log.e("LoginTodosalaUActivity","Password: "+ edtClave.getText());

                String email = edtUser.getText().toString();
                String password = edtClave.getText().toString();

                if (email.equals(emailLogin) && password.equals(passwordLogin)){

                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("email",email);
                    editor.putString("password", password);
                    editor.commit();

                    Intent intent = new Intent(LoginTodosalaUActivity.this, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginTodosalaUActivity.this, "The email and/or password is incorrect", Toast.LENGTH_SHORT).show();
                }




            }
        });

        btnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Log.w("LoginTodosalaUActivity","onLongClick of the buttonn Sign In.");
                return false;
            }
        });
    }
}