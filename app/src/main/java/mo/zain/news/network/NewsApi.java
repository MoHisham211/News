package mo.zain.news.network;

import java.util.List;

import mo.zain.news.model.NewsModel;
import mo.zain.news.util.Constant;
import retrofit2.Call;
import retrofit2.http.GET;


public interface NewsApi {
    @GET(Constant.END_POINT)
    Call<NewsModel> getAllData();
}
