package com.gucarsoft.saferpassword.Views.Fragments.add;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputLayout;
import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.R;
import com.gucarsoft.saferpassword.Service.PasswordService;

import java.security.SecureRandom;

public class AddFragment extends Fragment {

    private AddViewModel addViewModel;
    private TextInputLayout title;
    private TextInputLayout userName;
    private TextInputLayout mail;
    private TextInputLayout passwordText;
    private TextInputLayout note;
    private Button saveBtn;
    private Button generateButton;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        addViewModel = ViewModelProviders.of(this).get(AddViewModel.class);
        View root = inflater.inflate(R.layout.fragment_add, container, false);
        title = root.findViewById(R.id.addTitle);
        passwordText = root.findViewById(R.id.addPassword);
        userName = root.findViewById(R.id.addUserName);
        note = root.findViewById(R.id.addNote);
        mail = root.findViewById(R.id.addMail);
        saveBtn = root.findViewById(R.id.saveBtn);
        generateButton = root.findViewById(R.id.generatePassword);
        Toast.makeText(getContext(),"Hello!", Toast.LENGTH_LONG);
        addViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                saveBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (title.getEditText().getText().toString().trim().length() == 0 || note.getEditText().getText().toString().trim().length() == 0 || passwordText.getEditText().getText().toString().trim().length() == 0 || mail.getEditText().getText().toString().trim().length() == 0 || userName.getEditText().getText().toString().trim().length() == 0) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                            builder.setTitle("ERROR !");
                            builder.setMessage("Please fill the boxes");
                            builder.setNegativeButton("Ok", null);
                            builder.show();
                        } else {
                            PasswordService passwordService = new PasswordService(getContext());
                            Password password = new Password();
                            password.setTitle(title.getEditText().getText().toString());
                            password.setUserName(userName.getEditText().getText().toString());
                            password.setMail(mail.getEditText().getText().toString());
                            password.setPassword(passwordText.getEditText().getText().toString());
                            password.setNote(note.getEditText().getText().toString());
                            passwordService.savePassword(password);
                            System.out.println(password.toString());
                            Toast.makeText(getContext(), "Password saved!", Toast.LENGTH_LONG).show();

                            title.getEditText().setText("");
                            userName.getEditText().setText("");
                            mail.getEditText().setText("");
                            passwordText.getEditText().setText("");
                            note.getEditText().setText("");
                        }
                    }
                });

                generateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                         char[] letters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n', 'o', 'p', 'r', 's', 't', 'u', 'v', 'y', 'z'};
                         char[] numbers = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
                         char[] chars = {'#','.','*','+','!','%','=','?','_','&'};

                        StringBuilder sb = new StringBuilder();
                        SecureRandom random = new SecureRandom();
                        while (sb.length()<25)
                        {
                            switch (random.nextInt(3))
                            {
                                case 0: sb.append(letters[random.nextInt(letters.length)]); break;
                                case 1: sb.append(numbers[random.nextInt(numbers.length)]); break;
                                case 2: sb.append(chars[random.nextInt(chars.length)]); break;
                            }
                        }
                        passwordText.getEditText().setText(sb.toString());
                    }
                });
            }
        });
        return root;
    }
}