package mo.zain.news.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mo.zain.news.R;
import mo.zain.news.model.Article;
import mo.zain.news.model.NewsModel;

public class DetailsActivity extends AppCompatActivity {

    ImageView newsImageView,backImage;
    TextView source,titletxt,destxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Intent i = getIntent();
        Article article = (Article) i.getSerializableExtra("CHOOSE");
        newsImageView=findViewById(R.id.newsImageView);
        Glide.with(this)
                .load(article.getUrlToImage())
                .into(newsImageView);
        source=findViewById(R.id.source);
        source.setText(article.getSource().getName());
        titletxt=findViewById(R.id.titletxt);
        titletxt.setText(article.getTitle());
        destxt=findViewById(R.id.destxt);
        destxt.setText(article.getDescription());
        backImage=findViewById(R.id.backImage);
        backImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}