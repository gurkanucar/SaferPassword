package com.gucarsoft.saferpassword.Views;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SetPassword extends AppCompatActivity {

    Button btn;
    TextInputLayout lastPass;
    TextInputLayout newPass;
    TextInputLayout newPassAgain;
    String existPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_password);

        btn = findViewById(R.id.setPasswordBtn);
        lastPass = findViewById(R.id.setPasswordLast);
        newPass = findViewById(R.id.setPasswordNew);
        newPassAgain = findViewById(R.id.setPasswordNewAgain);

        SharedPreferences prefs = getSharedPreferences("pass", MODE_PRIVATE);
        existPass = prefs.getString("pass", md5("pass"));

        if (existPass.equals(md5("pass"))) {
            lastPass.setVisibility(View.GONE);
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (existPass.equals(md5("pass"))) {
                    if (newPass.getEditText().getText().toString().equals(newPassAgain.getEditText().getText().toString())) {
                        if (newPass.getEditText().getText().toString().trim().length() != 0 && newPassAgain.getEditText().getText().toString().trim().length() != 0) {
                            String password = md5(newPass.getEditText().getText().toString());
                            SharedPreferences.Editor editor = getSharedPreferences("pass", MODE_PRIVATE).edit();
                            editor.putString("pass", password);
                            editor.apply();
                            Toast.makeText(SetPassword.this, "Password saved: " + password, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SetPassword.this, Login.class);
                            startActivity(intent);
                        } else {
                            alertDialog();
                        }
                    } else {
                        alertDialog();
                    }
                } else {
                    if (newPass.getEditText().getText().toString().equals(newPassAgain.getEditText().getText().toString()) && existPass.equals(md5(lastPass.getEditText().getText().toString()))) {
                        if (newPass.getEditText().getText().toString().trim().length() != 0 && newPassAgain.getEditText().getText().toString().trim().length() != 0) {
                            String password = md5(newPass.getEditText().getText().toString());
                            SharedPreferences.Editor editor = getSharedPreferences("pass", MODE_PRIVATE).edit();
                            editor.putString("pass", password);
                            editor.apply();
                            Toast.makeText(SetPassword.this, "Password saved: " + password, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SetPassword.this, Login.class);
                            startActivity(intent);
                        } else {
                            alertDialog();
                        }
                    } else {
                        alertDialog();
                    }
                }
            }
        });
    }

    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SetPassword.this);
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
