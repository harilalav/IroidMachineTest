package com.example.iroid;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JsonPlaceHolder
{
    @GET("friday/index.php?route=api/common/getCategories")
    Call<JsonArray> getCategory();
}
