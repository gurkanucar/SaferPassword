package com.gucarsoft.saferpassword.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.gucarsoft.saferpassword.MainActivity;
import com.gucarsoft.saferpassword.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Login extends AppCompatActivity {

    TextInputLayout passwordText;
    Button button;
    String existPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        button=findViewById(R.id.loginBtn);
        passwordText=findViewById(R.id.loginPassword);

        SharedPreferences prefs = getSharedPreferences("pass", MODE_PRIVATE);
        existPass = prefs.getString("pass", md5("pass"));

        if (existPass.equals(md5("pass"))) {
            Intent intent = new Intent(Login.this, SetPassword.class);
            startActivity(intent);
        }



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(existPass.equals(md5(passwordText.getEditText().getText().toString()))){
                   Intent intent = new Intent(Login.this, MainActivity.class);
                   intent.putExtra("isLogin", 1);
                   startActivity(intent);
               }
               else{
                   alertDialog();
               }
            }
        });
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(Login.this);
        builder.setTitle("Error !");
        builder.setMessage("Try Again");
        builder.setNegativeButton("Ok", null);
        builder.show();
    }


    public String md5(String s) {
        String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

}
