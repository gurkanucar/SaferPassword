package com.gucarsoft.saferpassword.Views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.R;
import com.gucarsoft.saferpassword.Service.PasswordService;
import com.gucarsoft.saferpassword.Views.Fragments.passwords.PasswordsFragment;

import java.util.List;

public class ViewDialog {

    Context context;
    ImageView closeBtn;
    ImageView saveBtn;
    private TextInputLayout title;
    private TextInputLayout userName;
    private TextInputLayout mail;
    private TextInputLayout passwordText;
    private TextInputLayout note;
    private Button generateButton;
    private static Password existPassword;
    static List<Password> passwordList;

    public void showDialog(Context activity, Password password, List<Password> _passwordList){

        passwordList=_passwordList;
        existPassword=password;
        context=activity;
        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.update_dialog);
        title = dialog.findViewById(R.id.updateTitle);
        passwordText = dialog.findViewById(R.id.updatePassword);
        userName = dialog.findViewById(R.id.updateUserName);
        note = dialog.findViewById(R.id.updateNote);
        mail = dialog.findViewById(R.id.updateMail);
        saveBtn = dialog.findViewById(R.id.updateSaveBtn);
        closeBtn = dialog.findViewById(R.id.updateCancelBtn);
        generateButton = dialog.findViewById(R.id.updateGeneratePassword);


        title.getEditText().setText(existPassword.getTitle());
        userName.getEditText().setText(existPassword.getUserName());
        mail.getEditText().setText(existPassword.getMail());
        passwordText.getEditText().setText(existPassword.getPassword());
        note.getEditText().setText(existPassword.getNote());

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordList.remove(existPassword);
                PasswordService passwordService = new PasswordService(context);
                existPassword.setTitle(title.getEditText().getText().toString());
                existPassword.setUserName(userName.getEditText().getText().toString());
                existPassword.setMail(mail.getEditText().getText().toString());
                existPassword.setPassword(passwordText.getEditText().getText().toString());
                existPassword.setNote(note.getEditText().getText().toString());
                passwordService.update(existPassword);
                passwordList.add(existPassword);
                Toast.makeText(context, "Password saved!", Toast.LENGTH_LONG).show();
                dialog.dismiss();
                PasswordsFragment.updateItems(passwordList);

            }
        });

        dialog.show();

    }
}