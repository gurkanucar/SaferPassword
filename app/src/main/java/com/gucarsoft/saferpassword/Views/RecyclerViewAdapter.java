package com.gucarsoft.saferpassword.Views;

import android.app.AlertDialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.gucarsoft.saferpassword.Model.Password;
import com.gucarsoft.saferpassword.R;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{
    private static final String TAG="RecyclerViewAdapter";

    //Define Lists From Here
    List<Password> passwordList = new ArrayList<>();
    Context context;

    public RecyclerViewAdapter(List<Password> _passwordList,Context _context) {
        passwordList = _passwordList;
        System.out.println("PASSWORD LIST LENGTH FROM ADAPTER: "+passwordList.size());
        context= _context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.passwordTxt.setText("***********");
        holder.title.setText(passwordList.get(position).getTitle().toUpperCase());
        holder.userName.setText(passwordList.get(position).getUserName());
        holder.mail.setText(passwordList.get(position).getMail());

        holder.copyPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Password Copied",Toast.LENGTH_SHORT).show();
                setClipboard(context,passwordList.get(position).getPassword());
            }
        });

        holder.copyMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"Mail Copied",Toast.LENGTH_SHORT).show();
                setClipboard(context,passwordList.get(position).getMail());
            }
        });

        holder.copyUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"User Name Copied",Toast.LENGTH_SHORT).show();
                setClipboard(context,passwordList.get(position).getUserName());
            }
        });

        holder.parentLayout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Password password = passwordList.get(position);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle(password.getTitle());
                builder.setMessage(password.getUserName()+"\n"+password.getMail()+"\n"+password.getPassword()+"\n"+password.getNote());
                builder.setNegativeButton("Delete", null);
                builder.setPositiveButton("Edit", null);
                builder.setNeutralButton("Ok", null);
                builder.show();
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        System.out.println("PASSWORD LIST LENGTH FROM GET ITEM COUNT: "+passwordList.size());

        return passwordList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        //View Objects
        TextView title;
        TextView userName;
        TextView mail;
        TextView passwordTxt;
        RelativeLayout parentLayout;
        LinearLayout copyUserName;
        LinearLayout copyMail;
        LinearLayout copyPassword;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parentLayout=itemView.findViewById(R.id.showRelativeLayout);
            title=itemView.findViewById(R.id.showTitle);
            userName=itemView.findViewById(R.id.showUserName);
            mail=itemView.findViewById(R.id.showMail);
            passwordTxt=itemView.findViewById(R.id.showPassword);
            copyUserName=itemView.findViewById(R.id.showCopyUserName);
            copyMail=itemView.findViewById(R.id.showCopyMail);
            copyPassword=itemView.findViewById(R.id.showCopyPassword);
            //Set View Objects
        }
    }

    private void setClipboard(Context context, String text) {
        if(android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.HONEYCOMB) {
            android.text.ClipboardManager clipboard = (android.text.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            clipboard.setText(text);
        } else {
            android.content.ClipboardManager clipboard = (android.content.ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
            android.content.ClipData clip = android.content.ClipData.newPlainText("Copied Text", text);
            clipboard.setPrimaryClip(clip);
        }
    }
}
