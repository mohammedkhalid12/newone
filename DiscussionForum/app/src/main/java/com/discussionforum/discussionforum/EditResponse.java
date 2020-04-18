package com.discussionforum.discussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.discussionforum.discussionforum.ui.subject.SubjectModel;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import www.sanju.motiontoast.MotionToast;

public class EditResponse extends AppCompatActivity {

    ImageView discussion_subject_profile_image;
    EditText edit_response_data;
    Button image,audio,video,send;
    String author,time;
    SubjectModel subjectModel;
    DiscussionSubject discussionSubject;
     SqliteDatabase sqliteDatabase;
     Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_response);

        discussion_subject_profile_image = (ImageView)findViewById(R.id.discussion_subject_profile_image);
        edit_response_data = (EditText)findViewById(R.id.edit_response_data);
        image = (Button)findViewById(R.id.image);
        audio = (Button)findViewById(R.id.audio);
        video = (Button)findViewById(R.id.video);
        send = (Button)findViewById(R.id.send);
        author = getAuthor();
        time = getTime();

        Intent intent = getIntent();
        String discussion_subject_title = intent.getStringExtra("subject_name");
        String  discussion_subject_author = "Author :"+intent.getStringExtra("subject_author");
        String discussion_subject = intent.getStringExtra("subject_bio");


        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendContent();
            }
        });
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                saveImage(view);
            }
        });
        audio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // saveAudio();
            }
        });
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //saveVideo(view);
            }
        });
    }

    public void sendContent(){
        if (TextUtils.isEmpty(edit_response_data.getText())){
            edit_response_data.setError("Please enter Content");
            edit_response_data.requestFocus();
            return;
        }
       sqliteDatabase = new SqliteDatabase(this);
       sqliteDatabase.InsertResponse(author,time,edit_response_data.getText().toString());
        MotionToast.Companion.createToast(this,"Response Dane",
                MotionToast.Companion.getTOAST_SUCCESS(),
                MotionToast.Companion.getGRAVITY_BOTTOM(),
                MotionToast.Companion.getLONG_DURATION(),
                ResourcesCompat.getFont(this,R.font.tajawal_tegular));
        startActivity(new Intent(getApplicationContext(),Home.class));
        finish();
    }

    public String getAuthor(){

        sqliteDatabase = new SqliteDatabase(this);
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

//    public void getImage(View view){
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("image/");
//        startActivityForResult(Intent.createChooser(intent, "select picture"), 1);
//
//    }

    //    public void getVideo(View view){
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_GET_CONTENT);
//        intent.setType("video/");
//        startActivityForResult(Intent.createChooser(intent, "select picture"), 1);
//
//    }
//
//    public void Result(int requestCode, int resultCode, Intent data){
//        if(resultCode == RESULT_OK){
//            Uri imageUri = data.getData();
//            String imagePath = getPath(imageUri);
//
//            discussion_subject_profile_image.setVisibility(View.VISIBLE);
//
//            discussion_subject_profile_image.setImageURI(imageUri);
//            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
//            Toast.makeText(getApplicationContext(), ""+bitmap, Toast.LENGTH_SHORT).show();
//            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
//             = byteArrayOutputStream.toByteArray();
//        }
//    }
//
//    public String getPath(Uri uri) {
//        String[] projection = { MediaStore.Images.Media.DATA };
//        Cursor cursor = managedQuery(uri, projection, null, null, null);
//        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        return cursor.getString(column_index);
//    }
//
//    public void saveImage(View v){
//        sqliteDatabase = new SqliteDatabase(this);
//        sqliteDatabase.InsertResponse(author,time,img.toString());
//        MotionToast.Companion.createToast(this,"Response Dane",
//                MotionToast.Companion.getTOAST_SUCCESS(),
//                MotionToast.Companion.getGRAVITY_BOTTOM(),
//                MotionToast.Companion.getLONG_DURATION(),
//                ResourcesCompat.getFont(this,R.font.tajawal_tegular));
//        startActivity(new Intent(getApplicationContext(),Home.class));
//        finish();
//    }

    //    public void saveVideo(View v){
//        sqliteDatabase = new SqliteDatabase(this);
//        sqliteDatabase.InsertResponse(author,time,video.toString());
//        MotionToast.Companion.createToast(this,"Response Dane",
//                MotionToast.Companion.getTOAST_SUCCESS(),
//                MotionToast.Companion.getGRAVITY_BOTTOM(),
//                MotionToast.Companion.getLONG_DURATION(),
//                ResourcesCompat.getFont(this,R.font.tajawal_tegular));
//        startActivity(new Intent(getApplicationContext(),Home.class));
//        finish();
//    }
//
//    public void saveAudio(){
//        File[] myFiles = new File[0];
//        File pathToMySongs = new File(
//                Environment.getExternalStorageDirectory() + "/MySongs");
//        if(pathToMySongs.exists()) {
//            myFiles = pathToMySongs.listFiles();
//        }
//        else {
//            Toast.makeText(getBaseContext(), "No files found.", Toast.LENGTH_SHORT).show();
//        }
//        for(File file : myFiles)
//        {
//            String path = file.getAbsolutePath();
//            sqliteDatabase = new SqliteDatabase(this);
//        sqliteDatabase.InsertResponse(author,time,path);
//        MotionToast.Companion.createToast(this,"Response Dane",
//                MotionToast.Companion.getTOAST_SUCCESS(),
//                MotionToast.Companion.getGRAVITY_BOTTOM(),
//                MotionToast.Companion.getLONG_DURATION(),
//                ResourcesCompat.getFont(this,R.font.tajawal_tegular));
//        startActivity(new Intent(getApplicationContext(),Home.class));
//        finish();
//        }
//    }
}
