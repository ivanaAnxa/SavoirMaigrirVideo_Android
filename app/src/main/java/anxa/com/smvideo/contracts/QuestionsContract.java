package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by allenanxa on 6/9/2017.
 */

public class QuestionsContract {

    @SerializedName("questionIndex")
    public Integer QuestionIndex;

    @SerializedName("question")
    public String Question;

    @SerializedName("answers")
    public List<AnswersContract> Answers;

}
