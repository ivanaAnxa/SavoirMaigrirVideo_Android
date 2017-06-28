package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by allenanxa on 6/9/2017.
 */

public class QuestionsDataContract {
    @SerializedName("questions")
    public List<QuestionsContract> Questions;

}
