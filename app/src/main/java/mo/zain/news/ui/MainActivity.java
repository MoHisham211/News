package mo.zain.news.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import mo.zain.news.R;
import mo.zain.news.adapter.NewsAdapter;
import mo.zain.news.model.Article;
import mo.zain.news.model.NewsModel;
import mo.zain.news.network.RetrofitInstance;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private List<Article> articleList=new ArrayList<>();
    private List<Article> articleListFilter=new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView rv;
    private EditText searchET;
    private NewsModel newsModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        searchET=findViewById(R.id.searchET);

        searchET.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                articleListFilter.clear();
                if (s.toString().isEmpty()){
                    getAllData();
                }else
                {
                    FilterData(s.toString());
                }

            }
        });

        getAllData();


    }

    private void FilterData (String text)
    {
        for (Article articless:articleList)
        {
            if (articless.getTitle().contains(text)){
                articleListFilter.add(articless);
                newsModel.setArticles(articleListFilter);
            }
        }
        adapter=new NewsAdapter(newsModel,MainActivity.this);
        rv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void getAllData(){
        Call<NewsModel> newsModelCall= RetrofitInstance.getNewsApi().getAllData();
        newsModelCall.enqueue(new Callback<NewsModel>() {
            @Override
            public void onResponse(Call<NewsModel> call, Response<NewsModel> response) {
                newsModel=response.body();
                articleList=newsModel.getArticles();
                adapter=new NewsAdapter(newsModel,MainActivity.this);
                rv.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<NewsModel> call, Throwable t) {

            }
        });
    }
}
