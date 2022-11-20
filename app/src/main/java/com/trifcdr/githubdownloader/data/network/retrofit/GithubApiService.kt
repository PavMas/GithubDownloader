package com.trifcdr.githubdownloader.data.network.retrofit

import com.trifcdr.githubdownloader.data.network.model.ReposListResponse
import com.trifcdr.githubdownloader.data.network.model.UsersListResponse
import com.trifcdr.githubdownloader.data.rest.BASE_URL
import com.trifcdr.githubdownloader.domain.model.GitHubRepo
import com.trifcdr.githubdownloader.domain.model.GitHubUser
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Created by trifcdr.
 */
interface GithubApiService {

    @GET("/search/users")
    fun getUsers(@Query("q") username: String) : Call<UsersListResponse>

    @GET("/users/{username}/repos")
    fun getRepos(@Path("username") name: String) : Call<MutableList<GitHubRepo>>

    companion object {
        var githubApiService: GithubApiService? = null

        fun getInstance() : GithubApiService{
            if(githubApiService == null){
                val logging = HttpLoggingInterceptor()
                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
                val client = OkHttpClient.Builder()
                    .addInterceptor(logging)
                    .build()
                val retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                githubApiService = retrofit.create(GithubApiService::class.java)
            }
            return githubApiService!!
        }
    }
}