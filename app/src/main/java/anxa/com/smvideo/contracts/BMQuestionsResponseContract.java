package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;
/**
 * Created by allenanxa on 6/9/2017.
 */

public class BMQuestionsResponseContract extends BaseContract {
    @SerializedName("data")
    public QuestionsDataContract Data;
}
