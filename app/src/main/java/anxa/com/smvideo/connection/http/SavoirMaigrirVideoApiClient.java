package anxa.com.smvideo.connection.http;

import android.net.Uri;
import android.os.AsyncTask;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.concurrent.Executor;

import anxa.com.smvideo.common.SavoirMaigrirVideoConstants;
import anxa.com.smvideo.common.WebkitURL;
import anxa.com.smvideo.contracts.BaseContract;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by angelaanxa on 5/23/2017.
 */

public class SavoirMaigrirVideoApiClient {
    public  <T extends BaseContract> void GetAsync(AsyncResponse asyncResponse, String apiName, MasterCommand command, Class<T> classType) {
        AnxacoachingGetAsnyc client = new AnxacoachingGetAsnyc(asyncResponse, FormatUri(apiName, "get", command), classType);
        client.execute(new String()); //temporarily pass string since we already built the url
    }

    //to be used only for listview inside a fragment
    public  <T extends BaseContract> void GetAsync(AsyncResponse asyncResponse, String apiName, MasterCommand command, Class<T> classType, Executor executor) {
        AnxacoachingGetAsnyc client = new AnxacoachingGetAsnyc(asyncResponse, FormatUri(apiName, "get", command), classType);
        client.executeOnExecutor(executor, new String());
    }
    public String FormatUri(String apiName, String httpMethod,  MasterCommand command)  {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("http").encodedAuthority(WebkitURL.domainURL.replace("http://", ""));
        builder.appendPath(SavoirMaigrirVideoConstants.SM_VIDEO_API_PATH);
        if(apiName != null && !apiName.isEmpty())
        {
            builder.appendPath(apiName);
        }
        if(command.Command != null && !command.Command.isEmpty())
        {
            builder.appendPath(command.Command);
        }
        if(command.RegId > 0)
        {
            builder.appendQueryParameter("regId", String.valueOf(command.RegId));
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(httpMethod);
        stringBuilder.append(apiName);
        if(command.Command != null && command.Command.length() > 0){
            stringBuilder.append("/" + command.Command);
        }

        if(command.RegId > 0){
            stringBuilder.append(String.valueOf(command.RegId));
        }
        stringBuilder.append(SavoirMaigrirVideoConstants.SHARED_KEY);

        String toHash = stringBuilder.toString();
        String sig = null;
        try {
            sig = AppUtil.SHA1(toHash);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        builder.appendQueryParameter("sig", sig);

        return builder.build().toString();
    }
}
