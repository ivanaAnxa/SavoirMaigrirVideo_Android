package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by allenanxa on 6/16/2017.
 */

public class BMResultsResponseContract extends BaseContract {
    @SerializedName("data")
    public ResultsResponseDataContract Data;

}
