package anxa.com.smvideo.contracts;

import com.google.gson.annotations.SerializedName;

/**
 * Created by angelaanxa on 10/19/2017.
 */

public class PaymentOrderDataContract extends BaseContract {
    @SerializedName("paymentId")
    public int PaymentId;
}
