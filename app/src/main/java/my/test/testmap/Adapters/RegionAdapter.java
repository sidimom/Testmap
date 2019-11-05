package my.test.testmap.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import my.test.testmap.R;
import my.test.testmap.Region;

public class RegionAdapter extends RecyclerView.Adapter<RegionAdapter.RegionHolder>{

    private List<Region> regions;
    private OnRegionClickListener onRegionClickListener;

    public RegionAdapter(OnRegionClickListener onRegionClickListener) {
        this.onRegionClickListener = onRegionClickListener;
    }

    @NonNull
    @Override
    public RegionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.region_item, viewGroup, false);
        return new RegionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RegionHolder regionHolder, int i) {
        regionHolder.bind(regions.get(i));
    }

    @Override
    public int getItemCount() {
        return regions.size();
    }

    public void refreshRecyclerView(List<Region> regions) {
        this.regions = regions;
        notifyDataSetChanged();
    }

    class RegionHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_region_item)
        TextView tv_region_item;

        @BindView(R.id.iv_download)
        ImageView iv_download;

        public RegionHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Region region) {
            tv_region_item.setText(region.name);
            if (!region.isDownloaded){

                iv_download.setVisibility(View.INVISIBLE);
            }
        }

        @OnClick
        void onClick(){
            onRegionClickListener.onRegionClick(regions.get(getLayoutPosition()));
        }
    }

    public interface OnRegionClickListener{
        void onRegionClick(Region region);
    }
}
