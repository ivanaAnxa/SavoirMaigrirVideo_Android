package anxa.com.smvideo.contracts;

/**
 * Created by angelaanxa on 10/17/2017.
 */

public class PaymentOrderGoogleContract {

    private String productId;
    private String description;
    private String orderId;
    private long purchaseDateMs;
    private String purchaseToken;
    private int status;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public long getPurchaseDateMs() {
        return purchaseDateMs;
    }

    public void setPurchaseDateMs(long purchaseDateMs) {
        this.purchaseDateMs = purchaseDateMs;
    }

    public String getPurchaseToken() {
        return purchaseToken;
    }

    public void setPurchaseToken(String purchaseToken) {
        this.purchaseToken = purchaseToken;
    }
}