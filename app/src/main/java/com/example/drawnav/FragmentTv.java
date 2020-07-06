package com.example.drawnav;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class FragmentTv extends Fragment {
    View view;
    RecyclerView recyclerView;
    AdapterTv adapterTv;
    ProgressBar progressBar;

    public FragmentTv() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tv,container,false);
        recyclerView = view.findViewById(R.id.rv);
        progressBar = view.findViewById(R.id.simpleProgressBar);
        getLoading();
        return view;
    }

    int progressStatus;
    private void getLoading(){
        progressBar.setVisibility(View.VISIBLE);
        final Handler handler = new Handler();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 10;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        getDatabasePath();
                    }
                });
            }
        }).start();
    }

    private void getDatabasePath() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(InterfaceTv.JSONURL)
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();

        InterfaceTv interfaceTv = retrofit.create(InterfaceTv.class);

        Call<String> stringCall = interfaceTv.getString();

        stringCall.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("Responsestring", response.body().toString());
                //Toast.makeText()
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        Log.i("onSuccess", response.body().toString());

                        String jsonresponse = response.body().toString();
                        writeRecycler(jsonresponse);

                    } else {
                        Log.i("onEmptyResponse", "Returned empty response");//Toast.makeText(getContext(),"Nothing returned",Toast.LENGTH_LONG).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeRecycler(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            if(jsonObject.optString("page").equals("1")){
                ArrayList<Tv> tvArrayList = new ArrayList<>();
                JSONArray jsonArray = jsonObject.getJSONArray("results");
                for (int i = 0; i < jsonArray.length(); i++){
                    Tv tv = new Tv();
                    JSONObject object = jsonArray.getJSONObject(i);

                    tv.setName(object.getString("name"));
                    tv.setPoster_path(object.getString("poster_path"));
                    tv.setOriginal_name(object.getString("original_name"));
                    tv.setFirst_air_date(object.getString("first_air_date"));
                    tv.setOriginal_language(object.getString("original_language"));
                    tv.setOverview(object.getString("overview"));
                    tvArrayList.add(tv);
                }
                adapterTv = new AdapterTv(getActivity(),tvArrayList);
                recyclerView.setAdapter(adapterTv);
                //recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
                recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                progressBar.setVisibility(View.INVISIBLE);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
