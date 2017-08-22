package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by aprilanxa on 29/06/2017.
 */

public class WeightHistoryDataContract {

    @SerializedName("InitialWeightModel")
    public WeightHistoryContract InitialWeightModel;

    @SerializedName("HistoryModel")
    public List<WeightHistoryContract> HistoryModel;

    @SerializedName("Page")
    public int Page;

    @SerializedName("TotalRecords")
    public int TotalRecords;

}
