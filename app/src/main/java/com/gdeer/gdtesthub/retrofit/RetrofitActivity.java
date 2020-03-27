package com.gdeer.gdtesthub.retrofit;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;

import com.gdeer.gdtesthub.R;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitActivity extends AppCompatActivity {

    private static final String USERNAME = "gdeeer";

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit);

        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        System.out.println("ConverterFactories: " + retrofit.converterFactories());
        System.out.println("CallAdapterFactories: " + retrofit.callAdapterFactories());
        GithubService githubService = retrofit.create(GithubService.class);

        Call<List<Repo>> repos = githubService.listRepos(USERNAME);
        System.out.println("zhangjl");
        System.out.println(repos);
        repos.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                Log.d("zhangjl", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {

            }
        });

        Observable<List<Repo>> reposRx = githubService.listReposRx(USERNAME);
        reposRx
            .subscribeOn(Schedulers.io())
            .timeout(3, TimeUnit.SECONDS)
            .subscribe(new Observer<List<Repo>>() {
                @Override
                public void onSubscribe(Disposable d) {
                    Log.d("zhangjl", "onSubscribe() called with: d = [" + d + "]");
                }

                @Override
                public void onNext(List<Repo> repos) {
                    Log.d("zhangjl", "onNext() called with: repos = [" + repos + "]");
                }

                @Override
                public void onError(Throwable e) {
                    Log.d("zhangjl", "onError() called with: e = [" + e + "]");
                }

                @Override
                public void onComplete() {
                    Log.d("zhangjl", "onComplete() called");
                }
            });
//        repos.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                try {
//                    System.out.println(response.body().string());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                System.out.println("onFailure");
//            }
//        });
    }
}
