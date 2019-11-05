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
import my.test.testmap.R;
import my.test.testmap.Region;

public class SubRegionActivity extends AppCompatActivity {

    @BindView(R.id.rv_subregions)
    RecyclerView rv_subregions;

    SubRegionAdapter adapter;
    Region mainRegion;

    public static final String EXTRA_REGION = "Region";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sub_regions);
        init();
    }

    private void init() {

        ButterKnife.bind(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        mainRegion = (Region) bundle.getSerializable("value");

        rv_subregions.setLayoutManager(new LinearLayoutManager(this));
        SubRegionAdapter.OnSubRegionClickListener onSubRegionClickListener = new SubRegionAdapter.OnSubRegionClickListener() {
            @Override
            public void onSubRegionClick(Region region) {
                Toast.makeText(getApplicationContext(),"download", Toast.LENGTH_SHORT).show();
            }
        };
        adapter = new SubRegionAdapter(onSubRegionClickListener);
        rv_subregions.setAdapter(adapter);
        adapter.refreshRecyclerView(mainRegion.subregions);

    }
}
