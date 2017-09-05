package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by aprilanxa on 29/08/2017.
 */

public class ShoppingListResponseContract extends BaseContract {
    @SerializedName("data")
    public ShoppingListDataContract Data;
}
