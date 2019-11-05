package my.test.testmap.Activities;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.test.testmap.Adapters.RegionAdapter;
import my.test.testmap.MVP.PresenterMainActivity;
import my.test.testmap.R;
import my.test.testmap.Region;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.tv_freeMemory)
    TextView tv_freeMemory;

    @BindView(R.id.pb_memory)
    ProgressBar pb_memory;

    @BindView(R.id.rv_regions)
    RecyclerView rv_regions;

    PresenterMainActivity presenter;
    RegionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void init(){

        ButterKnife.bind(this);

        rv_regions.setLayoutManager(new LinearLayoutManager(this));
        RegionAdapter.OnRegionClickListener onRegionClickListener = new RegionAdapter.OnRegionClickListener() {
            @Override
            public void onRegionClick(Region region) {
                if (region.inner_download_prefix != null
                        && !region.inner_download_prefix.isEmpty()){
                    openSubRegions(region);
                }else {
                    presenter.downloadedRegion(region);
                }
            }
        };
        adapter = new RegionAdapter(onRegionClickListener);
        rv_regions.setAdapter(adapter);


        presenter = new PresenterMainActivity();
        presenter.attachView(this);
        presenter.refreshMemoryInfo();

        presenter.setRegions();

    }

    private void openSubRegions(Region region) {
        if (region == null){
            showToast("Unknown region");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable("value", region);
        Intent intent = new Intent(this, SubRegionActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @SuppressLint("SetTextI18n")
    public void refreshPBMemory(int freeMemory, int totalMemory) {
        String freeMemoryString = new DecimalFormat("#.##").format((double) freeMemory  / 1024);
        tv_freeMemory.setText("Free " + freeMemoryString + " Gb");
        pb_memory.setMax(totalMemory);
        pb_memory.setProgress(totalMemory - freeMemory);
    }

    public void setAdapter(List<Region> regions) {
        adapter.refreshRecyclerView(regions);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
