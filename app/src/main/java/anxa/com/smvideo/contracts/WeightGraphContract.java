package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aprilanxa on 29/06/2017.
 */

public class WeightGraphContract {

    @SerializedName("ActivityId")
    public int ActivityId;

    @SerializedName("UserId")
    public int UserId;

    @SerializedName("WeightKg")
    public float WeightKg;

    @SerializedName("Bmi")
    public float Bmi;

    @SerializedName("DeviceType")
    public String DeviceType;

    @SerializedName("Date")
    public String Date;

    @SerializedName("Deleted")
    public String Deleted;

    @SerializedName("ConfirmedData")
    public String ConfirmedData;
}
