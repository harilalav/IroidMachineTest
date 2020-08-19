package com.example.iroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView cat_name;
    RecyclerView recyclerView;
    List<Category> categoryList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cat_name = findViewById(R.id.categoryName);
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(),categoryList));
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://iroidtechnologies.in/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        JsonPlaceHolder jsonPlaceHolder = retrofit.create(JsonPlaceHolder.class);
        Call<JsonArray> loadCategories = jsonPlaceHolder.getCategory();
        loadCategories.enqueue(new Callback<JsonArray>() {
            @Override
            public void onResponse(Call<JsonArray> call, Response<JsonArray> response) {
                if (!response.isSuccessful()) {
                    try {
                        String catNamesStrings = response.body().toString();
                        Type listType = new TypeToken<List<Category>>() {
                        }.getType();
                        categoryList = getListFromJson(catNamesStrings, listType);
                        Toast.makeText(MainActivity.this, ""+categoryList, Toast.LENGTH_LONG).show();
                        recyclerView.setItemAnimator(new DefaultItemAnimator());
                        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(),categoryList));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<JsonArray> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private <T> List<T> getListFromJson(String catNamesStrings, Type listType) {
        if (!isValid(catNamesStrings)) {
            return null;
        }
        return new Gson().fromJson(catNamesStrings,listType);

    }

    private boolean isValid(String catNamesStrings) {
        try {
            new JsonParser().parse(catNamesStrings);
//
            return true;
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return false;
        }
    }
}