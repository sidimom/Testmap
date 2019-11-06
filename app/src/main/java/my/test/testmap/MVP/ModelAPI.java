package my.test.testmap.MVP;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import my.test.testmap.API.RegionDownloadAPI;
import my.test.testmap.Constants;
import my.test.testmap.Region;
import my.test.testmap.RestCallback;
import okhttp3.ResponseBody;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

class ModelAPI {

    private RegionDownloadAPI regionDownloadAPI;

    ModelAPI() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.DOWNLOADED_LINK)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        regionDownloadAPI = retrofit.create(RegionDownloadAPI.class);
    }


    void downloadRegion(final RestCallback callback, final Region region) {
        regionDownloadAPI.downloadRegion("Denmark_europe_2.obf.zip")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<ResponseBody>() {
                    @Override
                    public void onSuccess(ResponseBody responseBody) {
                        callback.downloadResponse();
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.error(e.toString());
                    }
                });
    }
}
