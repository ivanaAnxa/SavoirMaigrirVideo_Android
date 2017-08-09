package anxa.com.smvideo.activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.compat.BuildConfig;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.webkit.CookieManager;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.common.WebkitURL;
import anxa.com.smvideo.customview.VideoEnabledWebChromeClient;
import anxa.com.smvideo.customview.VideoEnabledWebView;
import anxa.com.smvideo.util.AppUtil;

/**
 * Created by aprilanxa on 07/08/2017.
 */

public class RegistrationActivity extends Activity {

    ImageButton forwardBrowserButton, backBrowserButton, refreshBrowserButton;
    String URLPath = "";
    public String contentString = "";

    ProgressBar myProgressBar;

    private VideoEnabledWebView mainContentWebView;
    private VideoEnabledWebChromeClient webChromeClient;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        URLPath = WebkitURL.domainURL + WebkitURL.registrationURL;

        setContentView(R.layout.registration);

        // Save the web view
        mainContentWebView = (VideoEnabledWebView) findViewById(R.id.maincontentWebView);

        forwardBrowserButton = (ImageButton) findViewById(R.id.forward);
        forwardBrowserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainContentWebView.goForward();
            }

        });
        backBrowserButton = (ImageButton) findViewById(R.id.back);
        backBrowserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainContentWebView.goBack();
            }
        });
        refreshBrowserButton = (ImageButton) findViewById(R.id.refresh);
        refreshBrowserButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                mainContentWebView.reload();
            }
        });

        // Initialized progress bar
        myProgressBar = (ProgressBar) findViewById(R.id.progressbar);

        // Save the web view
        mainContentWebView = (VideoEnabledWebView) findViewById(R.id.maincontentWebView);

        mainContentWebView.getSettings().setJavaScriptEnabled(true);

//        mainContentWebView.addJavascriptInterface(new MyJavaScriptInterface(), "HTMLOUT");
        //prevent horizontal scrolling

        mainContentWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);


        // Initialize the VideoEnabledWebChromeClient and set event handlers
        View loadingView = myProgressBar; // Your own view, read class comments
        View nonVideoLayout = findViewById(R.id.registration_rl); // Your own view, read class comments
        ViewGroup videoLayout = (ViewGroup) findViewById(R.id.videoLayout); // Your own view, read class comments

        webChromeClient = new VideoEnabledWebChromeClient(nonVideoLayout, videoLayout, loadingView, mainContentWebView) // See all available constructors...
        {
            // Subscribe to standard events, such as onProgressChanged()...
            @Override
            public void onProgressChanged(WebView view, int progress) {
            }
        };

        webChromeClient.setOnToggledFullscreen(new VideoEnabledWebChromeClient.ToggledFullscreenCallback() {
            public void toggledFullscreen(boolean fullscreen) {
                // Your code to handle the full-screen change, for example showing and hiding the title bar. Example:
                if (fullscreen) {

                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags |= WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE);
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

                } else {
                    WindowManager.LayoutParams attrs = getWindow().getAttributes();
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_FULLSCREEN;
                    attrs.flags &= ~WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
                    getWindow().setAttributes(attrs);
                    if (android.os.Build.VERSION.SDK_INT >= 14) {
                        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                }
            }
        });

        //prevent horizontal scrolling
        mainContentWebView.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        mainContentWebView.getSettings().setJavaScriptEnabled(true);

        String defaultagent = mainContentWebView.getSettings().getUserAgentString();

        if (defaultagent == null)
            defaultagent = AppUtil.getDefaultUserAgent();

        mainContentWebView.getSettings().setUserAgentString(ApplicationData.getInstance().customAgent + " " + BuildConfig.VERSION_NAME + " " + defaultagent);

        CookieManager.getInstance().setCookie("http://savoir-maigrir.aujourdhui.com", "produit=sid=221");

        mainContentWebView.loadUrl(URLPath);
        mainContentWebView.setWebChromeClient(webChromeClient);

        mainContentWebView.setWebViewClient(new WebViewClient() {
            private boolean testPayment = false;

            @Override
            public void onPageFinished(WebView view, String url) {
                // TODO Auto-generated method stub
                super.onPageFinished(view, url);
                if (myProgressBar != null)
                    myProgressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                System.out.println("FullBrowserActivity " + url);
                if (url.contains(WebkitURL.domainURL.replace("http://", ""))) {
                    return false;
                }

                view.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
                return true;
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //registration for JMC

                System.out.println("Registration onPageStarted url: " + url);
                System.out.println("Registration onPageStarted this: " + WebkitURL.domainURL + WebkitURL.registrationDoneURL);
                if (url.equalsIgnoreCase(WebkitURL.domainURL + WebkitURL.registrationDoneURL)) {
                    Intent returnIntent = new Intent();
                    returnIntent.putExtra("TO_LOGIN", true);
                    setResult(RESULT_OK, returnIntent);

                    finish();
                }
//                if (testPayment && url.equalsIgnoreCase(WebkitURL.domainURL + WebkitURL.freeDashboardURL + "payment/premium")) {
//                    view.loadUrl(WebkitURL.domainURL + WebkitURL.freeDashboardURL + "payment/premium?v=99");
//                }
//                if (url.equalsIgnoreCase(WebkitURL.domainURL + "/payment-sub.asp")) {
//                    ClearCookies();
//                    UserProfile up = ApplicationData.getInstance().userProfile;
//                    if (up != null) {
//                        Bundle extras = getIntent().getExtras();
//                        if (extras != null) {
//                            int offer = extras.getInt("OfferSelected", 0);
//                            if (offer > 0) {
//
//                                String postdata = null;
//                                postdata = "regId=" + up.getReg_id() + "&productType=" + offer + "&sid=221";
//                                if (testPayment) {
//                                    postdata = postdata + "&v=99";
//
//                                } else {
//                                    postdata = postdata + "&v=0";
//                                }
//                                view.postUrl(WebkitURL.domainURL + "/payment-resubapp.asp", postdata.getBytes());
//
//                            }
//                        }
//                    }
//                }
//                if (url.equalsIgnoreCase(WebkitURL.domainURL + "/5minparjour?") || url.equalsIgnoreCase(WebkitURL.domainURL + "/5minparjour")) {
//                    Intent returnIntent = new Intent();
//                    returnIntent.putExtra("TO_MAINPAGE", true);
//                    setResult(RESULT_OK, returnIntent);
//
//                    finish();
//                }

            }
        });


        WebSettings webSettings = mainContentWebView.getSettings();
        webSettings.setUseWideViewPort(true);
        webSettings.setDomStorageEnabled(true);
        webSettings.setSaveFormData(true);
        webSettings.setSavePassword(false);
        webSettings.setJavaScriptEnabled(true);
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setLoadsImagesAutomatically(true);
        webSettings.setBlockNetworkImage(false);
        webSettings.setGeolocationEnabled(true);
        webSettings.setLoadWithOverviewMode(true);

    }
}


