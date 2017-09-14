package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by angelaanxa on 7/21/2016.
 */
public class PostAnxamatsContract extends BaseContract {
    @SerializedName("application_id")
    public int ApplicationId;
    @SerializedName("application_userid")
    public int ApplicationUserId;
    @SerializedName("event_id")
    public int EventId;
    @SerializedName("log_details")
    public String LogDetails;
    @SerializedName("event_value")
    public String EventValue;
}
