package my.test.testmap.MVP;

import my.test.testmap.Activities.SubRegionActivity;
import my.test.testmap.Region;
import my.test.testmap.RestCallback;

public class PresenterSubRegionActivity implements RestCallback {

    private SubRegionActivity view;
    private ModelAPI modelAPI;

    public void attachView(SubRegionActivity activity){

        view = activity;
        modelAPI = new ModelAPI();

    }

    public void detachView(){
        view = null;
    }

    public void downloadedRegion(Region region) {
        modelAPI.downloadRegion(this, region);
    }

    @Override
    public void downloadResponse() {
        view.showToast("downloaded");
    }

    @Override
    public void error(String textError) {
        view.showToast(textError);
    }
}
