package com.example.wmhanaasri.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;
import android.widget.EditText;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;

import com.example.wmhanaasri.Karyawan.KaryawanMainActivity;
import com.example.wmhanaasri.R;

public class LoginActivity extends AppCompatActivity {
    Button buttonLogin;
    TextView textView;
    EditText etEmail, etPassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        etEmail = findViewById(R.id.etPassword);
        etPassword = findViewById(R.id.etPassword);



        //text garis bawah & button lupa password
        textView = (TextView)findViewById(R.id.lupaPassword);
        String content = "Lupa Password";
        SpannableString spannableString = new SpannableString(content);
        spannableString.setSpan(new UnderlineSpan(), 0, content.length(), 0);
        textView.setText(spannableString);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, LupaPasswordActivity.class);
                startActivity(intent);
            }
        });

            buttonLogin = (Button) findViewById(R.id.btnLogin);
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String email = etEmail.getText().toString();
                    String password = etPassword.getText().toString();
                    Intent intent = new Intent(LoginActivity.this, KaryawanMainActivity.class);
                    startActivity(intent);
                }
            });
    }

}