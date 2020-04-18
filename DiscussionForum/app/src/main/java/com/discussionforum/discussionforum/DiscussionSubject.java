package com.discussionforum.discussionforum;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.discussionforum.discussionforum.ui.subject.SubjectModel;

import java.util.ArrayList;
import java.util.List;

public class DiscussionSubject extends AppCompatActivity {

    ImageView discussion_subject_profile_image;
    TextView discussion_subject_title,discussion_subject_author,discussion_subject;
    Button add_response;
    RecyclerView response_recycler;
    List<ResponsModel> models = new ArrayList<>();
    SqliteDatabase sqliteDatabase;
    ResponseAdapter responseAdapter;
    Cursor cursor;
    String author,time,content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion_subject);

        discussion_subject_profile_image = (ImageView)findViewById(R.id.discussion_subject_profile_image);
        discussion_subject_title = (TextView)findViewById(R.id.discussion_subject_title);
        discussion_subject_author = (TextView)findViewById(R.id.discussion_subject_author);
        discussion_subject = (TextView)findViewById(R.id.discussion_subject);
        add_response = (Button)findViewById(R.id.add_response);

        Intent intent = getIntent();
        discussion_subject_title.setText(intent.getStringExtra("subject_name"));
        discussion_subject_author.setText(intent.getStringExtra("subject_author"));
        discussion_subject.setText(intent.getStringExtra("subject_bio"));

        response_recycler = (RecyclerView)findViewById(R.id.response_recycler);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        response_recycler.setLayoutManager(layoutManager);
        response_recycler.setHasFixedSize(true);

        sqliteDatabase = new SqliteDatabase(this);
        cursor = sqliteDatabase.getResponse();
        if (cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    author = cursor.getString(1);
                    time = cursor.getString(2);
                    content = cursor.getString(3);
                    ResponsModel responsModel = new ResponsModel(author,time,content);
                    models.add(responsModel);
                } while (cursor.moveToNext());
            }
        }
        responseAdapter = new ResponseAdapter(this,models);
        response_recycler.setAdapter(responseAdapter);
        add_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),EditResponse.class));
                finish();
            }
        });
    }



}
