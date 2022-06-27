package com.example.expandablerecyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserActivity extends AppCompatActivity {
    EditText editText, editText2;
    Button  b1, b2;
    TextView textView;
    ProgressBar progressBar;

    public static ArrayList<DataModal> arrayList = new ArrayList<>();
    public static ArrayList<UserModel> userModelArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);


        editText = findViewById(R.id.idEdtName);
        editText2 = findViewById(R.id.idEdtJob);
        b1 = findViewById(R.id.btnAsync);
        b2 = findViewById(R.id.expandedbtn);
        progressBar = findViewById(R.id.idLoadingPB);

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UserActivity.this, MainActivity.class);
                startActivity(i);

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty() && editText2.getText().toString().isEmpty()) {
                    Toast.makeText(UserActivity.this, "Please enter both the values", Toast.LENGTH_SHORT).show();
                    return;
                }
                dropDown(editText.getText().toString(), editText2.getText().toString());


            }
        });



    }

    private void postData() {
        progressBar.setVisibility(View.VISIBLE);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in/api/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);

        Call<DataModal> call = retrofitApi.createPost("morpheus", "leader");
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {


                System.out.println("code : " + response.code());
                System.out.println("name: " + response.body().getName());
                System.out.println("job : " + response.body().getJob());
                System.out.println("ID: " + response.body().getId());
                System.out.println("created: " + response.body().getCreatedAt());
                arrayList.add(response.body());
                DataModal user1 = response.body();


                Toast.makeText(getApplicationContext(),
                        user1.getName() + " " + user1.getJob() + " " +
                                user1.getId() + " " + user1.getCreatedAt(), Toast.LENGTH_SHORT).show();
                DataModal dataModal = response.body();
                Intent i = new Intent(UserActivity.this, MainActivity.class);
                startActivity(i);

                String responseString = "Response Code : " + response.code() + "\n Name : " +
                        dataModal.getName() + "\n" + " Job : " + dataModal.getJob() +
                        "\n" + " ID : " + dataModal.getId() + "\n" + " CreatedAt : " + dataModal.getCreatedAt();

                textView.setText(responseString + " ");

            }

            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                textView.setText("Error found is : " + t.getMessage());

            }
        });

    }


    private void dropDown(String name, String job) {

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://reqres.in")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        DataModal dataModal = new DataModal(name, job);

        Call<DataModal> call = retrofitApi.getdata(dataModal);
        call.enqueue(new Callback<DataModal>() {
            @Override
            public void onResponse(Call<DataModal> call, Response<DataModal> response) {
                DataModal user1 = response.body();
                arrayList.add(user1);
                Toast.makeText(getApplicationContext(), "Save data", Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(), user1.getName() + " " + user1.getJob() + " ", Toast.LENGTH_SHORT).show();

            }


            @Override
            public void onFailure(Call<DataModal> call, Throwable t) {
                textView.setText("Error found is : " + t.getMessage());

            }
        });

    }


}