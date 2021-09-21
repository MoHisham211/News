package mo.zain.news.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.io.Externalizable;
import java.io.Serializable;
import java.util.List;

import mo.zain.news.R;
import mo.zain.news.model.NewsModel;
import mo.zain.news.ui.DetailsActivity;
import mo.zain.news.ui.MainActivity;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> implements Serializable{

    private NewsModel newsModels;
    private Context context;

    public NewsAdapter(NewsModel newsModels, Context context) {
        this.newsModels = newsModels;
        this.context = context;
    }

    @NonNull
    @Override
    public NewsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.news_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ViewHolder holder, int position) {
       NewsModel newsModel=newsModels;
        Glide.with(context)
                .load(newsModel.getArticles()
                        .get(position).getUrlToImage())
        .into(holder.Image);
        holder.sourceName.setText(newsModel.getArticles().get(position)
        .getSource().getName());
        holder.time.setText(newsModel.getArticles().get(position)
        .getPublishedAt());
        holder.title.setText(newsModel.getArticles().get(position)
        .getTitle());
    }

    @Override
    public int getItemCount() {
        return newsModels.getArticles().size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements Serializable {
        ImageView Image;
        TextView sourceName,time,title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Image=itemView.findViewById(R.id.Image);
            sourceName=itemView.findViewById(R.id.sourceName);
            time=itemView.findViewById(R.id.time);
            title=itemView.findViewById(R.id.title);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent=new Intent(context, DetailsActivity.class);
                    //Bundle mBundle = new Bundle();
                    intent.putExtra("CHOOSE",
                            newsModels.getArticles().get(getAdapterPosition()));
                    //mIntent.putExtras(mBundle);
                    context.startActivity(intent);
                }
            });
        }
    }
}
