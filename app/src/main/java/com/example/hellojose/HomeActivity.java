package com.example.hellojose;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class HomeActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView txvUserWelcome;
    ImageView imvClosew;
    Button btnGet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        sharedPreferences = getSharedPreferences("myPreference", Context.MODE_PRIVATE);
        txvUserWelcome = findViewById(R.id.txvUserWelcome);
        imvClosew = findViewById(R.id.imvClosew);
        btnGet = findViewById(R.id.btnGet);

        String email = sharedPreferences.getString("email", "There isn't email");
        txvUserWelcome.setText(email);

        txvUserWelcome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, ScrollActivity.class);
                startActivity(intent);
            }
        });

        imvClosew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(HomeActivity.this);
                //title
                builder.setTitle(R.string.close_session);
                //message
                builder.setMessage("if you log out , you'll loose all dates.");
                //buttons
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

//              with this editor I can do changes in the shared Preferences
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.remove("email");
                        editor.remove("password");
//                then I have to confirm the changes with commit
                        editor.commit();

                        Intent intent = new Intent(HomeActivity.this,LoginTodosalaUActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setPositiveButtonIcon(getResources().getDrawable(R.drawable.baseline_favorite_24));

                builder.setCancelable(false);

                AlertDialog alertDialog = builder.create();
                alertDialog.show();


            }
        });

        btnGet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this,VideosActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_actionbar, menu);

        for (int i = 0; i < menu.size(); i++){
            MenuItem menuItem = menu.getItem(i);
            SpannableString title = new SpannableString(menuItem.getTitle());
            title.setSpan(new ForegroundColorSpan(Color.WHITE),0, title.length(), 0);
            menuItem.setTitle(title);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if (item.getItemId() == R.id.ite_contact){
            Toast.makeText(this, "You've selected contact", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.ite_information){
            Toast.makeText(this, "You've selected information", Toast.LENGTH_SHORT).show();
        }

        if (item.getItemId() == R.id.ite_support){
            Toast.makeText(this, "You've selected support", Toast.LENGTH_SHORT).show();
        }

        return true;
    }
}