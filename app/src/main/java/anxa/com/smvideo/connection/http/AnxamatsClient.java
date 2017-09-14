package anxa.com.smvideo.connection.http;

import android.net.Uri;

import anxa.com.smvideo.common.WebkitURL;
import anxa.com.smvideo.contracts.BaseContract;

/**
 * Created by angelaanxa on 7/21/2016.
 * Use this for api calls to anxamats
 */
public class AnxamatsClient {

    private final String DEFAULT_ACTIVETIME_API_PATH = "logger";

    public <T extends BaseContract> void PostAsync(AsyncResponse asyncResponse, String json, Class<T> classType) {
        AnxacoachingPostAsync client = new AnxacoachingPostAsync(asyncResponse, GetUri(),classType );
        client.execute(json);
    }

    public String GetUri()  {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority(WebkitURL.ANXAMATS_URL.replace("http://", ""));
        builder.appendPath(DEFAULT_ACTIVETIME_API_PATH);
        return builder.build().toString();
    }
}
