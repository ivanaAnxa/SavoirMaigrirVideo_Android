package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aprilanxa on 29/08/2017.
 */

public class ShoppingListContract {
    @SerializedName("Quantity")
    public float Quantity;
    @SerializedName("ItemName")
    public String ItemName;
    @SerializedName("Category")
    public int Category;
    @SerializedName("ProgId")
    public int ProgId;
    @SerializedName("Ctid")
    public int ctid;
    @SerializedName("Title")
    public String Title;
    @SerializedName("Servings")
    public int Servings;
    @SerializedName("Wnum")
    public int Wnum;
}
