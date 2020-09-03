package com.gucarsoft.saferpassword.Views.Fragments.passwords;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
    private PasswordsViewModel passwordsViewModel;
    private static List<Password> passwordList;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        passwordsViewModel =
                ViewModelProviders.of(this).get(PasswordsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_passwords, container, false);

        PasswordService passwordService = new PasswordService(getContext(), "selam");

        rcview = root.findViewById(R.id.recyclerview);

        passwordList = passwordService.list();

        System.out.println("PASSWORD LIST LENGTH: " + passwordList.size());
        rcAdapter = new RecyclerViewAdapter(passwordList, getContext());
        rcview.setAdapter(rcAdapter);
        rcview.setLayoutManager(new LinearLayoutManager(getContext()));

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