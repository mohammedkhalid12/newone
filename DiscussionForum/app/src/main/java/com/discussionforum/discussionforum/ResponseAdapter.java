package com.discussionforum.discussionforum;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ResponseAdapter extends RecyclerView.Adapter<ResponseAdapter.MyHOlder> {

    private Context context;
    private List<ResponsModel> models;
    private ResponsModel responsModel;

    public ResponseAdapter(Context context, List<ResponsModel> models) {
        this.context = context;
        this.models = models;
    }

    @NonNull
    @Override
    public MyHOlder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item_view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.response_layout,parent,false);
        return new MyHOlder(item_view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHOlder holder, int position) {
          responsModel = models.get(position);
          holder.author.setText("Author :"+responsModel.getAuthor());
          holder.time.setText("Time :"+responsModel.getTime());
          holder.content.setText(responsModel.getContent());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class MyHOlder extends RecyclerView.ViewHolder {

        TextView author,time,content;

        public MyHOlder(@NonNull View itemView) {
            super(itemView);
            author = (TextView)itemView.findViewById(R.id.response_author);
            time = (TextView)itemView.findViewById(R.id.response_time);
            content = (TextView)itemView.findViewById(R.id.response_content);
        }
    }
}
