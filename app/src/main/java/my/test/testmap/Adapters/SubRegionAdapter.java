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

public class SubRegionAdapter extends RecyclerView.Adapter<SubRegionAdapter.SubRegionHolder>{

    private List<Region> subRegions;
    private OnSubRegionClickListener onSubRegionClickListener;

    public SubRegionAdapter(OnSubRegionClickListener onSubRegionClickListener) {
        this.onSubRegionClickListener = onSubRegionClickListener;
    }

    @NonNull
    @Override
    public SubRegionHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.region_item, viewGroup, false);
        return new SubRegionHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SubRegionHolder subRegionHolder, int i) {
        subRegionHolder.bind(subRegions.get(i));
    }

    @Override
    public int getItemCount() {
        return subRegions.size();
    }

    public void refreshRecyclerView(List<Region> subRegions) {
        this.subRegions = subRegions;
        notifyDataSetChanged();
    }

    class SubRegionHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_region_item)
        TextView tv_region_item;

        @BindView(R.id.iv_download)
        ImageView iv_download;

        public SubRegionHolder(@NonNull View itemView) {
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
            onSubRegionClickListener.onSubRegionClick(subRegions.get(getLayoutPosition()));
        }
    }

    public interface OnSubRegionClickListener{
        void onSubRegionClick(Region region);
    }
}
