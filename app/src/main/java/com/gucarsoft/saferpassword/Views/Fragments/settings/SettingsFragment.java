package com.gucarsoft.saferpassword.Views.Fragments.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.gucarsoft.saferpassword.MainActivity;
import com.gucarsoft.saferpassword.R;
import com.gucarsoft.saferpassword.Views.Login;
import com.gucarsoft.saferpassword.Views.SetPassword;

public class SettingsFragment extends Fragment {

    private SettingsViewModel settingsViewModel;
    Button button;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        settingsViewModel =
                ViewModelProviders.of(this).get(SettingsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_settings, container, false);

        button=root.findViewById(R.id.settingsChangePass);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SetPassword.class);
                startActivity(intent);
            }
        });

        settingsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;
    }
}