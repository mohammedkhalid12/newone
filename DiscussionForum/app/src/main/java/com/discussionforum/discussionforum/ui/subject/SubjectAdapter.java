package com.discussionforum.discussionforum.ui.subject;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.discussionforum.discussionforum.R;
import com.discussionforum.discussionforum.DiscussionSubject;
import com.discussionforum.discussionforum.SqliteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SubjectAdapter extends RecyclerView.Adapter<SubjectAdapter.MyViewHolder> {

    private Context context;
    private List<SubjectModel> subjectModels;
    SubjectModel subjectModel;

    public SubjectAdapter(Context context, List<SubjectModel> list) {
        this.context = context;
        this.subjectModels = list;
    }

    @NonNull
    @Override
    public  SubjectAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subject_layout,parent,false);
        return new MyViewHolder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        subjectModel = subjectModels.get(position);
        holder.subject_name.setText("Title : "+subjectModel.getTitle());
        holder.subjcet_author.setText("Author : "+subjectModel.getOwner());
        holder.subject_bio.setText("Subject : "+subjectModel.getBio());
        holder.subject_time.setText("Time : "+subjectModel.getTime());
       // holder.imageView.setVisibility(View.VISIBLE);
        holder.read_subject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readSubject();
            }
        });
    }


    @Override
    public int getItemCount() {
       return subjectModels.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView subject_name,subjcet_author,subject_time,subject_bio;
        Button read_subject;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = (ImageView)itemView.findViewById(R.id._owner_image);
            subject_name = (TextView)itemView.findViewById(R.id.subject_name);
            subjcet_author = (TextView)itemView.findViewById(R.id.subject_author);
            subject_bio = (TextView)itemView.findViewById(R.id.subject_bio);
            subject_time = (TextView)itemView.findViewById(R.id.subject_date);
            read_subject = (Button)itemView.findViewById(R.id.read_subject);
        }
    }

    public void readSubject(){
        Intent intent = new Intent(context, DiscussionSubject.class);
        intent.putExtra("subject_name",subjectModel.getTitle());
        intent.putExtra("subject_author",subjectModel.getOwner());
        intent.putExtra("subject_bio",subjectModel.getBio());
        intent.putExtra("subject_time",subjectModel.getTime());
        context.startActivity(intent);
    }
}


