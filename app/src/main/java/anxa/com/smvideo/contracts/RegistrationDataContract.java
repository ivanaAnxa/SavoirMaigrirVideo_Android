package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by angelaanxa on 10/23/2017.
 */

public class RegistrationDataContract extends BaseContract {
    @SerializedName("reg_id")
    public int RegId ;
    @SerializedName("aj_regno")
    public int AJRegNo;
}
