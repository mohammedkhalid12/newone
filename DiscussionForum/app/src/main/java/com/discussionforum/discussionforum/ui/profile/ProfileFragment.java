package com.discussionforum.discussionforum.ui.profile;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.discussionforum.discussionforum.R;
import com.discussionforum.discussionforum.SqliteDatabase;

public class ProfileFragment extends Fragment {

    private ProfileViewModel profileViewModel;
    SqliteDatabase sqliteDatabase;
    Cursor cursor;
    String name, list_name,address,age,gender;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        profileViewModel =
                ViewModelProviders.of(this).get(ProfileViewModel.class);
        View root = inflater.inflate(R.layout.fragment_profile, container, false);
        final  TextView profile_name = root.findViewById(R.id.profile_name);
        final  TextView profile_last_name = root.findViewById(R.id.profile_last_name);
        final  TextView profile_address = root.findViewById(R.id.profile_address);
        final  TextView profile_age = root.findViewById(R.id.profile_age);
        final  TextView profile_gender = root.findViewById(R.id.profile_gender);


        sqliteDatabase = new SqliteDatabase(getContext());
        cursor = sqliteDatabase.getData();
        if (cursor.getCount() > 0){
            if (cursor.moveToFirst()){
                do {
                    name = cursor.getString(1);
                    list_name = cursor.getString(2);
                    address = cursor.getString(3);
                    age = cursor.getString(4);
                    gender = cursor.getString(5);
                }while (cursor.moveToNext());{}
            }
        }

        profile_name.setText(name);
        profile_last_name.setText(" Name : "+ name +" "+ list_name);
        profile_address.setText("Location : "+address);
        profile_age.setText("Age : "+age);
        profile_gender.setText("Gander : "+gender);
        return root;
    }

}
