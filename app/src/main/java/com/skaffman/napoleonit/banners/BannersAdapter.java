package com.skaffman.napoleonit.banners;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.skaffman.napoleonit.R;
import com.skaffman.napoleonit.model.Banner;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BannersAdapter extends RecyclerView.Adapter<BannersAdapter.ViewHolder> {

    private List<Banner> banners = new ArrayList<>();

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_banner, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(banners.get(position));
    }

    @Override
    public int getItemCount() {
        return banners.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageBanner;
        private TextView tvTitle, tvDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageBanner = itemView.findViewById(R.id.imageBanner);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDesc = itemView.findViewById(R.id.tvDesc);
        }

        public void bind(Banner banner) {
            new DownloadImageTask((ImageView) itemView.findViewById(R.id.imageBanner)).execute(banner.getImage());
            tvTitle.setText(banner.getTitle());
            tvDesc.setText(banner.getDesc());
        }
    }
}
