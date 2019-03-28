package com.skaffman.napoleonit.api;

import com.skaffman.napoleonit.model.Banner;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("banners.json")
    Call<List<Banner>> getBanners();

}
