package my.test.testmap.API;

import io.reactivex.Single;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RegionDownloadAPI {

    @GET("/download.php?standard=yes")
    Single<ResponseBody> downloadRegion(@Query("file") String filename);
}
