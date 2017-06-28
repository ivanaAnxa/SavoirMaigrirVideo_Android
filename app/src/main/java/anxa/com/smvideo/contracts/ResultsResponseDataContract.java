package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by allenanxa on 6/21/2017.
 */

public class ResultsResponseDataContract {

    @SerializedName("height")
    public String Height;

    @SerializedName("currentWeight")
    public String CurrentWeight;

    @SerializedName("targetWeight")
    public String TargetWeight;

    @SerializedName("targetWeightLoss")
    public String TargetWeightLoss;

    @SerializedName("currentIMC")
    public String CurrentIMC;

    @SerializedName("currentIMCPointer")
    public String CurrentIMCPointer;

    @SerializedName("targetIMC")
    public String TargetIMC;

    @SerializedName("targetIMCPointer")
    public String TargetIMCPointer;

    @SerializedName("averageIMC")
    public String AverageIMC;

    @SerializedName("normalWeightContent")
    public String NormalWeightContent;

    @SerializedName("mainVideo")
    public String MainVideo;

    @SerializedName("testimonialTitle1")
    public String TestimonialTitle1;

    @SerializedName("testimonialVideo1")
    public String TestimonialVideo1;

    @SerializedName("testimonialTitle2")
    public String TestimonialTitle2;

    @SerializedName("testimonialVideo2")
    public String TestimonialVideo2;

    @SerializedName("testimonialTitle3")
    public String TestimonialTitle3;

    @SerializedName("testimonialVideo3")
    public String TestimonialVideo3;

    @SerializedName("q3Result")
    public String Q3Result;

    @SerializedName("q4ResultTitle")
    public String Q4ResultTitle;

    @SerializedName("q4ResultContent")
    public String Q4ResultContent;

    @SerializedName("q6Result")
    public String Q6Result;
}
