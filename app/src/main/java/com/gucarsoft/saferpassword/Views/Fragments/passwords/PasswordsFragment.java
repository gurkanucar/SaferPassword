package com.gucarsoft.saferpassword.Views.Fragments.passwords;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.R;
import com.gucarsoft.saferpassword.Service.PasswordService;
import com.gucarsoft.saferpassword.Views.RecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

public class PasswordsFragment extends Fragment {

    static RecyclerView rcview;
    static RecyclerViewAdapter rcAdapter;
    EditText searchText;
    private PasswordsViewModel passwordsViewModel;
    private static List<Password> passwordList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        passwordsViewModel =
                ViewModelProviders.of(this).get(PasswordsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_passwords, container, false);

        final PasswordService passwordService = new PasswordService(getContext());

        rcview = root.findViewById(R.id.recyclerview);

        searchText=root.findViewById(R.id.searchTxt);

        passwordList = passwordService.list();

        System.out.println("PASSWORD LIST LENGTH: " + passwordList.size());
        rcAdapter = new RecyclerViewAdapter(passwordList, getContext());
        rcview.setAdapter(rcAdapter);
        rcview.setLayoutManager(new LinearLayoutManager(getContext()));

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                rcAdapter.setPasswordList(passwordService.list(searchText.getText().toString()));
                rcAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        passwordsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

            }
        });
        return root;

    }

    public static void removeItem(int position) {
        rcAdapter.notifyItemRemoved(position);
        passwordList.remove(position);
    }

    public static void updateItems(List<Password> passwords) {
        passwordList = passwords;
        rcAdapter.notifyDataSetChanged();
    }
}