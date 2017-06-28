package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by allenanxa on 6/16/2017.
 */

public class BMContract extends BaseContract {
    @SerializedName("version")
    public int Version;

    @SerializedName("userId")
    public int UserId;

    @SerializedName("email")
    public String Email;

    @SerializedName("firstName")
    public String FirstName;

    @SerializedName("lastName")
    public String LastName;

    @SerializedName("phone")
    public String Phone;

    @SerializedName("gender")
    public String Gender;

    @SerializedName("heightInCm")
    public Double HeightInCm;

    @SerializedName("currentWeightKg")
    public Double CurrentWeightKg;

    @SerializedName("targetWeightKg")
    public Double TargetWeightKg;

    @SerializedName("startWeightKg")
    public Double StartWeightKg;

    @SerializedName("answers")
    public List<Integer> Answers;

}
