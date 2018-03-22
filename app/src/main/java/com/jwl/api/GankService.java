package com.jwl.api;

import com.jwl.entity.GankData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Administrator on 2018/3/22/0022.
 */

public interface GankService {
    @GET("data/{category}/{number}/{page}")
    Call<GankData> getGankData(@Path("category") String category,@Path("number") int number,@Path("page") int page);
}
