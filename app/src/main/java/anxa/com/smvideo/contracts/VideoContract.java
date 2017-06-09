package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by angelaanxa on 5/23/2017.
 */

public class VideoContract {
    @SerializedName("id")
    public int Id;

    @SerializedName("index")
    public int Index;

    @SerializedName("weekNumber")
    public int WeekNumber;

    @SerializedName("dayNumber")
    public int DayNumber;

    @SerializedName("category")
    public String Category;

    @SerializedName("title")
    public String Title;

    @SerializedName("duration")
    public String Duration;

    @SerializedName("description")
    public String Description;

    @SerializedName("videoId")
    public String VideoId;

    @SerializedName("videoSource")
    public String VideoSource;

    @SerializedName("videoUrl")
    public String VideoUrl;

    @SerializedName("thumbnailUrl")
    public String ThumbnailUrl;

    public boolean IsSelected;
}
