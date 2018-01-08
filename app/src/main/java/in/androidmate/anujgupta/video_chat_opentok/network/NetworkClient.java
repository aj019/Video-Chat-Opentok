package in.androidmate.anujgupta.video_chat_opentok.network;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by anujgupta on 08/01/18.
 */

public class NetworkClient {

    public static Retrofit retrofit;

    public NetworkClient() {
    }

    public static Retrofit getRetrofit(){

        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();


        retrofit = new Retrofit.Builder()
                    .baseUrl("http://www.androidmate.in/video_chat/opentok/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        return retrofit;
    }
}
