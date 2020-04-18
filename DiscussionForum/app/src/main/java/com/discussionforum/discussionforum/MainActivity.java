package com.discussionforum.discussionforum;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button button;
    TextView textView;
    public static final String MyPREFERENCES = "Prefs" ;
    public static final String key = "isLogin";
    SharedPreferences myPrefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myPrefs= this.getSharedPreferences(MyPREFERENCES, MODE_PRIVATE);
        String check = myPrefs.getString(key,null);
        if (check != null){
            startActivity(new Intent(this,Home.class));
            finish();
        }

        textView = (TextView)findViewById(R.id.subscribe);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //go to subscribe Activity
                startActivity(new Intent(getApplicationContext(), Subscribe.class));
                SharedPreferences.Editor editor = myPrefs.edit();
                editor.putString(key,"Login");
                editor.commit();
                finish();
            }
        });

        button = (Button)findViewById(R.id.start_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),Home.class));
                finish();
            }
        });
    }
}
