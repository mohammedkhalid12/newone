package com.discussionforum.discussionforum;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import www.sanju.motiontoast.MotionToast;

public class Subscribe extends AppCompatActivity {

    EditText user_name,list_name,address,age;
    RadioButton male,female;
    Button subscribe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscribe);

        user_name = (EditText)findViewById(R.id.user_name);
        list_name = (EditText)findViewById(R.id.list_name);
        address = (EditText)findViewById(R.id.address);
        age = (EditText)findViewById(R.id.user_age);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

        subscribe = (Button)findViewById(R.id.subscribe_button);

        subscribe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insert();
            }
        });
    }


    public void insert(){

        if (TextUtils.isEmpty(user_name.getText())){
            user_name.requestFocus();
            user_name.setError("Please enter your name ");
            return;
        }
        if (TextUtils.isEmpty(list_name.getText())){
            list_name.requestFocus();
            list_name.setError("Please enter your list name ");
            return;
        }
        if (TextUtils.isEmpty(address.getText())){
            address.requestFocus();
            address.setError("Please enter your address ");
            return;
        }
        if (TextUtils.isEmpty(age.getText())){
            age.requestFocus();
            age.setError("Please enter your age ");
            return;
        }
        if (male.isClickable()){
            SqliteDatabase sqliteDatabase = new SqliteDatabase(this);
            sqliteDatabase.insert(user_name.getText().toString(),list_name.getText().toString(),
                    address.getText().toString(),age.getText().toString(),male.getText().toString());
            MotionToast.Companion.createToast(this,"Subscribe!",
                    MotionToast.Companion.getTOAST_SUCCESS(),
                    MotionToast.Companion.getGRAVITY_BOTTOM(),
                    MotionToast.Companion.getLONG_DURATION(),
                    ResourcesCompat.getFont(this,R.font.tajawal_tegular));
            startActivity(new Intent(this,Home.class));
            finish();
        }else{
            SqliteDatabase sqliteDatabase = new SqliteDatabase(this);
            sqliteDatabase.insert(user_name.getText().toString(),list_name.getText().toString(),
                    address.getText().toString(),age.getText().toString(),female.getText().toString());
            MotionToast.Companion.createToast(this,"Subscribe!",
                    MotionToast.Companion.getTOAST_SUCCESS(),
                    MotionToast.Companion.getGRAVITY_BOTTOM(),
                    MotionToast.Companion.getLONG_DURATION(),
                    ResourcesCompat.getFont(this,R.font.tajawal_tegular));
            startActivity(new Intent(this,Home.class));
            finish();
        }
    }
}
