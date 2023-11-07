package com.example.wmhanaasri.Login;

import androidx.appcompat.app.AppCompatActivity;

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
    EditText txtemail, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        txtemail = findViewById(R.id.email);
        txtpassword = findViewById(R.id.password);
        txtemail.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_NEXT){
                    txtpassword.requestFocus();
                    return true;
                }
                return false;
            }
        });
        txtpassword.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                if(i == EditorInfo.IME_ACTION_DONE || i == EditorInfo.IME_NULL){
//                    btnlogin();
                    return true;
                }
                return false;
            }
        });

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
                    Intent intent = new Intent(LoginActivity.this, KaryawanMainActivity.class);
                    startActivity(intent);
                }
            });
//
    }
    //button login
//    public void btnlogin(){
//        buttonLogin = (Button) findViewById(R.id.buttonLogin);
//        buttonLogin.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(LoginActivity.this, HomeFragment.class);
//                startActivity(intent);
//            }
//        });
//    }
}