package my.test.testmap.MVP;

import my.test.testmap.Activities.SubRegionActivity;

public class PresenterSubRegionActivity {

    private SubRegionActivity view;

    public void attachView(SubRegionActivity activity){

        view = activity;

    }

    public void detachView(){
        view = null;
    }
}
