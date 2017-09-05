package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aprilanxa on 29/08/2017.
 */

public class ShoppingListDataContract {
    @SerializedName("ShoppingList")
    public List<ShoppingListContract> ShoppingList;
    @SerializedName("ShoppingListRecipe")
    public List<ShoppingListContract> ShoppingListRecipe;
}
