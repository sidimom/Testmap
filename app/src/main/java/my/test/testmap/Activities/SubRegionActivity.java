package my.test.testmap.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import my.test.testmap.Adapters.SubRegionAdapter;
import my.test.testmap.MVP.PresenterSubRegionActivity;
import my.test.testmap.R;
import my.test.testmap.Region;

public class SubRegionActivity extends AppCompatActivity {

    @BindView(R.id.rv_subregions)
    RecyclerView rv_subregions;

    SubRegionAdapter adapter;
    Region mainRegion;
    PresenterSubRegionActivity presenter;

    public static final String EXTRA_REGION = "Region";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_regions);
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    private void init() {

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            mainRegion = (Region) bundle.getSerializable(EXTRA_REGION);
        }

        rv_subregions.setLayoutManager(new LinearLayoutManager(this));
        SubRegionAdapter.OnSubRegionClickListener onSubRegionClickListener = new SubRegionAdapter.OnSubRegionClickListener() {
            @Override
            public void onSubRegionClick(Region region) {
                if (region.subregions != null
                        && region.subregions.size() > 0){
                    openSubRegions(region);
                }
            }

            @Override
            public void onDownloadClick(Region region) {
                presenter.downloadedRegion(region);
            }
        };
        adapter = new SubRegionAdapter(onSubRegionClickListener);
        rv_subregions.setAdapter(adapter);
        if (mainRegion != null) {
            adapter.refreshRecyclerView(mainRegion.subregions);
        }

        presenter = new PresenterSubRegionActivity();
        presenter.attachView(this);

    }

    private void openSubRegions(Region region) {
        if (region == null){
            showToast("Unknown region");
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putSerializable(SubRegionActivity.EXTRA_REGION, region);
        Intent intent = new Intent(this, SubRegionActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void showToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
