package my.test.testmap.MVP;

import android.os.Environment;
import android.os.StatFs;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.util.List;

import my.test.testmap.Activities.MainActivity;
import my.test.testmap.R;
import my.test.testmap.Region;
import my.test.testmap.RestCallback;

public class PresenterMainActivity implements RestCallback {
    private MainActivity view;
    private StatFs statFs;
    private ModelReadingXML modelXML;
    private ModelAPI modelAPI;

    public void attachView(MainActivity activity){

        view = activity;
        statFs = new StatFs(Environment.getExternalStorageDirectory().getPath());
        XmlPullParser parser = view.getResources().getXml(R.xml.regions);
        modelXML = new ModelReadingXML(parser);
        modelAPI = new ModelAPI();

    }

    public void detachView(){
        view = null;
        modelXML = null;
    }

    public void refreshMemoryInfo() {
        long totalMemory = statFs.getTotalBytes() / 1024 / 1024;
        long freeMemory = statFs.getFreeBytes() / 1024 / 1024;
        view.refreshPBMemory((int) freeMemory, (int) totalMemory);
    }

    public void setRegions() {
        try {
            List<Region> regions = modelXML.getRegions();
            if (regions != null) {
                view.setAdapter(regions);
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
            view.showToast(e.toString());
        } catch (IOException e) {
            e.printStackTrace();
            view.showToast(e.toString());
        }
    }

    public void downloadedRegion(Region region) {
        modelAPI.downloadRegion(this, region);
    }

    @Override
    public void downloadResponse() {
        view.showToast("downloaded");
        refreshMemoryInfo();
    }

    @Override
    public void error(String textError) {
        view.showToast(textError);
    }
}
