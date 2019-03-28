package com.skaffman.napoleonit.banners;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.skaffman.napoleonit.R;
import com.skaffman.napoleonit.App;
import com.skaffman.napoleonit.model.Banner;

import java.util.ArrayList;
import java.util.List;

public class BannersFragment extends Fragment {

    private RecyclerView rvBanners;
    private CardView cvBanners;
    private ImageView imageBanner;
    private TextView tvTitle, tvDesc;
    public List<Banner> banners;
    private BannersAdapter bannersAdapter;

    public BannersFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_banners, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        rvBanners = view.findViewById(R.id.rvBanners);
        cvBanners = view.findViewById(R.id.cvBanner);
        imageBanner = view.findViewById(R.id.imageBanner);
        tvTitle = view.findViewById(R.id.tvTitle);
        tvDesc = view.findViewById(R.id.tvDesc);
        banners = new ArrayList<>();
        loadBanners(banners);
        bannersAdapter = new BannersAdapter();
        bannersAdapter.setBanners(banners);
        rvBanners = view.findViewById(R.id.rvBanners);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rvBanners.setLayoutManager(layoutManager);
        rvBanners.setAdapter(bannersAdapter);
    }

    public void loadBanners(final List<Banner> list) {

        Call<List<Banner>> call = App.api.getBanners();

        call.enqueue(new Callback<List<Banner>>() {
            @Override
            public void onResponse(Call<List<Banner>> call, Response<List<Banner>> response) {
                if (response.body() != null) {
                    list.addAll(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<Banner>> call, Throwable t) {
                Toast.makeText(getContext(), "An error occurred during networking", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
