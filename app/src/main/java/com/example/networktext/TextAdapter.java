package com.example.networktext;

import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TextAdapter extends RecyclerView.Adapter<TextAdapter.ViewHolder> {
    private List<Text> mtextList;

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textName;
        TextView textContent;
        Button textWeb;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textName = itemView.findViewById(R.id.text_name);
            textContent = itemView.findViewById(R.id.text_content);
            textWeb = itemView.findViewById(R.id.button_web);
        }
    }

    public TextAdapter(List<Text> textList) {
        mtextList = textList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.text_item,parent,false);
        final ViewHolder holder = new ViewHolder(view);
        holder.textWeb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Text text = mtextList.get(position);
//                Toast.makeText(v.getContext(),""+text.getWeb(),Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(v.getContext(),WebActivity.class);
                intent.putExtra("extra_data",text.getWeb());
                v.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Text text = mtextList.get(position);
        holder.textName.setText(text.getName());
        holder.textContent.setText(text.getContent());
        holder.textWeb.setText(text.getWeb());

    }

    @Override
    public int getItemCount() {
        Log.d("Main",""+mtextList.size()+"hhhh");
        return mtextList.size();

    }



}
