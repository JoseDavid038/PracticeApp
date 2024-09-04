package com.example.hellojose;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class EcoAppLoginActivity extends AppCompatActivity {

    EditText edtEmail;
    EditText edtPassword;
    CheckBox chbTerms;
    Button btnSignIn;
    SwitchCompat swiSession;
    ImageView imvHeader;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_eco_app_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        chbTerms = findViewById(R.id.chbTerms);
        btnSignIn = findViewById(R.id.btnSignIn);
        swiSession = findViewById(R.id.swiSession);

        imvHeader = findViewById(R.id.imvHeader);



        chbTerms.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if (isChecked){
                        btnSignIn.setVisibility(View.VISIBLE);
                        Toast.makeText(EcoAppLoginActivity.this, "The Terms has been accepted", Toast.LENGTH_SHORT).show();
                    }else{
                        btnSignIn.setVisibility(View.INVISIBLE);
                        Toast.makeText(EcoAppLoginActivity.this, "The Terms hasn't been accepted", Toast.LENGTH_SHORT).show();
                    }
            }
        });

        swiSession.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(EcoAppLoginActivity.this, "The Session will keep actived", Toast.LENGTH_SHORT).show();
                    MyToast toast = new MyToast();
                    toast.show(EcoAppLoginActivity.this,"Switch activated", true);
                }else{
                    Toast.makeText(EcoAppLoginActivity.this, "The Session won't keep actived", Toast.LENGTH_SHORT).show();
                }
            }
        });

//        MyToast toast = new MyToast();
//        toast.show(this,"hello jose", true);


        AnimationSet animationSet = new AnimationSet(true);
        animationSet.addAnimation(new ScaleAnimation(0f, 1f, 0f, 1f));
        animationSet.addAnimation(new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF,0.5f, Animation.RELATIVE_TO_SELF,0.5f));

        imvHeader.startAnimation(animationSet);
        animationSet.setDuration(5000);

    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

//    public void signIn(View view){
//        Toast.makeText(this, "You are Logged", Toast.LENGTH_SHORT).show();
//    }

    public void signIn(View view) {
//        Toast.makeText(this, edtEmail.getText(), Toast.LENGTH_SHORT).show();
//
//        edtPassword.setText(edtEmail.getText());

        if (chbTerms.isChecked()) {
            Toast.makeText(this, "The Terms has been accepted", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "The Terms hasn't been accepted", Toast.LENGTH_SHORT).show();
        }

        if (swiSession.isChecked()){
            Toast.makeText(this, "The Session will keep actived", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "The Session won't keep actived", Toast.LENGTH_SHORT).show();
        }
    }

    public class MyToast {
        public void show(Context context, String text, boolean isLong) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View layout = inflater.inflate(R.layout.toast_layout, null);

            ImageView image = (ImageView) layout.findViewById(R.id.toast_image);
            image.setImageResource(R.drawable.hand_ok);

            TextView textV = (TextView) layout.findViewById(R.id.toast_text);
            textV.setText(text);

            Toast toast = new Toast(context);
            toast.setGravity(Gravity.TOP, 0, 0);
            toast.setDuration((isLong) ? Toast.LENGTH_LONG : Toast.LENGTH_SHORT);
            toast.setView(layout);
            toast.show();
        }
    }
}