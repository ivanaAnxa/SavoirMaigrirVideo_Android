package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by allenanxa on 6/21/2017.
 */

public class BMVideoResponseContract extends BaseContract {
    @SerializedName("data")
    public BMVideoDataContract Data;
}
