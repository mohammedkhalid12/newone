package com.discussionforum.discussionforum.ui.subscribe;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.discussionforum.discussionforum.R;
import com.discussionforum.discussionforum.SqliteDatabase;
import com.discussionforum.discussionforum.ui.subject.SubjectModel;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

import www.sanju.motiontoast.MotionToast;

public class SubscribeFragment extends Fragment {

    private SubscribeViewModel subscribeViewModel;
    private SqliteDatabase sqliteDatabase;
    DatabaseReference databaseReference;
    private Cursor cursor;
    private String author;
    private String time;
    EditText title,bio;
    Button add_subject;
    Context context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        subscribeViewModel =
                ViewModelProviders.of(this).get(SubscribeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_subscribe, container, false);
        title = (EditText)root.findViewById(R.id.subject_title);
        bio = (EditText)root.findViewById(R.id.suject_data);
        add_subject = (Button)root.findViewById(R.id.subscribe_button);
         try {
             time = getTime();
             author = getAuthor();
         }catch (Exception e){}

        add_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertOfline();
                //insetOnline();
            }
        });
        return root;
    }


    public void insetOnline(){
        SubjectModel subjectModel = new SubjectModel(title.getText().toString(),author,time,bio.getText().toString());
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Subjects");
        databaseReference.push().setValue(subjectModel);
        MotionToast.Companion.createToast((Activity) getContext(),"Online Subject",
                MotionToast.Companion.getTOAST_SUCCESS(),
                MotionToast.Companion.getGRAVITY_BOTTOM(),
                MotionToast.Companion.getLONG_DURATION(),
                ResourcesCompat.getFont(getContext(),R.font.tajawal_tegular));

    }

    public void insertOfline(){
        if (TextUtils.isEmpty(title.getText())){
            title.setError("Please enter your Title! ");
            title.findFocus();
            return;
        }
        if (TextUtils.isEmpty(bio.getText())){
            bio.findFocus();
            bio.setError("Please enter your Subject");
        }

        sqliteDatabase = new SqliteDatabase(getContext());
        sqliteDatabase.insertSubject(title.getText().toString(),author,bio.getText().toString(),time);
        MotionToast.Companion.createToast((Activity) getContext(),"Subject Dane",
                MotionToast.Companion.getTOAST_SUCCESS(),
                MotionToast.Companion.getGRAVITY_BOTTOM(),
                MotionToast.Companion.getLONG_DURATION(),
                ResourcesCompat.getFont(getContext(),R.font.tajawal_tegular));
    }

    public String getAuthor(){

        sqliteDatabase = new SqliteDatabase(getContext());
        cursor = sqliteDatabase.getData();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    author = cursor.getString(1);
                } while (cursor.moveToNext());
            }
        }
        return author;
    }

    public String getTime(){

        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyy:mm:dd - h:mm a");
        return simpleDateFormat.format(date);

    }
}
