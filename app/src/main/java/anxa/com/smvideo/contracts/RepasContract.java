package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aprilanxa on 13/07/2017.
 */

public class RepasContract {
    @SerializedName("ProgId")
    public int progId;
    @SerializedName("Dnum")
    public int dnum;
    @SerializedName("Wnum")
    public int wnum;
    @SerializedName("MealType")
    public int mealType;
    @SerializedName("Ctid")
    public int ctid;
    @SerializedName("ItemName")
    public String itemName;
    @SerializedName("LinkCtid")
    public int linkCtid;
    @SerializedName("OriginalCtid")
    public int originalCtid;
    @SerializedName("BootcampId")
    public int bootcampId;
}
