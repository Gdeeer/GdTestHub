package com.gdeer.gdtesthub.poplibrary.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubService {
    @GET("users/{user}/repos")
    Call<List<Repo>> listRepos(@Path("user") String user);


    @GET("users/{user}/repos")
    Observable<List<Repo>> listReposRx(@Path("user") String user);
}
