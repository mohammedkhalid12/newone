package com.discussionforum.discussionforum.ui.subject;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.discussionforum.discussionforum.R;
import com.discussionforum.discussionforum.SqliteDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {

    private SubjectViewModel subjectViewModel;
    DatabaseReference databaseReference;
    List<SubjectModel> list = new ArrayList<>();
    SubjectAdapter subjectAdapter;
    SqliteDatabase sqliteDatabase;
    Cursor cursor;
    String subj_title,subj_author,subj_bio,subj_time;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subjectViewModel =
                ViewModelProviders.of(this).get(SubjectViewModel.class);
        View root = inflater.inflate(R.layout.fragment_subjects, container, false);
        final RecyclerView recyclerView = root.findViewById(R.id.recycler_view);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        sqliteDatabase = new SqliteDatabase(getContext());
        cursor = sqliteDatabase.getSubject();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    subj_title = cursor.getString(1);
                    subj_author = cursor.getString(2);
                    subj_bio = cursor.getString(4);
                    subj_time = cursor.getString(3);
                   SubjectModel subjectModel = new SubjectModel(subj_title,subj_author,subj_bio,subj_time);
                    list.add(subjectModel);
                } while (cursor.moveToNext());
            }
        }
        subjectAdapter = new SubjectAdapter(getContext(),list);
        recyclerView.setAdapter(subjectAdapter);
//        databaseReference = FirebaseDatabase.getInstance().getReference("Discussion").child("Subjects");
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot snapshot) {
//                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
//                    SubjectModel subjectModel = dataSnapshot.getValue(SubjectModel.class);
//                    list.add(subjectModel);
//                }
//
//                subjectAdapter = new SubjectAdapter();
//                recyclerView.setAdapter(subjectAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        return root;
    }
}
