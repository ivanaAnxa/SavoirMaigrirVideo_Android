package anxa.com.smvideo.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.media.Image;
import android.net.Uri;
import android.opengl.Visibility;
import android.os.Bundle;
import android.os.Handler;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerFragment;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.common.SavoirMaigrirVideoConstants;
import anxa.com.smvideo.connection.ApiCaller;
import anxa.com.smvideo.connection.http.AsyncResponse;
import anxa.com.smvideo.contracts.AnswersContract;
import anxa.com.smvideo.contracts.BMContract;
import anxa.com.smvideo.contracts.BMQuestionsResponseContract;
import anxa.com.smvideo.contracts.BMResultsResponseContract;
import anxa.com.smvideo.contracts.BMVideoResponseContract;
import anxa.com.smvideo.contracts.QuestionsContract;
import anxa.com.smvideo.contracts.ResultsResponseDataContract;
import anxa.com.smvideo.contracts.VideoContract;
import anxa.com.smvideo.util.BilanMinceurUtil;
import anxa.com.smvideo.util.InputValidatorUtil;

/**
 * Created by angelaanxa on 5/24/2017.
 */

public class BilanMinceurActivity extends Fragment implements View.OnClickListener {
    private WebView webViewResultsVideo;
    private WebView webViewTestimonials1Video;
    private WebView webViewTestimonials2Video;
    private WebView webViewTestimonials3Video;

    private YouTubePlayerFragment playerFragment;
    private List<QuestionsContract> questionsList;
    private Integer questionIndex;
    private String gender;
    private Integer[] answers = new Integer[10];

    Context context;
    ApiCaller caller;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.context = getActivity();
        mView = inflater.inflate(R.layout.bilan, null);
        caller = new ApiCaller();

        //header change
        ((TextView)((RelativeLayout)mView.findViewById(R.id.headermenu)).findViewById(R.id.header_title_tv)).setText(getString(R.string.menu_bilan));

        /*
        String path1="http://ovh.belletoday.com/orange/Free/SM_Marketing/SM_Marketing04.mp4";
        Uri uri=Uri.parse(path1);
        VideoView videoDietprofile  = (VideoView) mView.findViewById(R.id.videoViewDietProfile);
        videoDietprofile.setVideoURI(uri);
        videoDietprofile.start();
        */
        //Initializing and adding YouTubePlayerFragment

        FragmentManager fm = getFragmentManager();
        String tag = YouTubePlayerFragment.class.getSimpleName();
        playerFragment = (YouTubePlayerFragment) fm.findFragmentByTag(tag);

        FragmentTransaction ft = fm.beginTransaction();
        playerFragment = YouTubePlayerFragment.newInstance();
        ft.replace(R.id.youtube_layout, playerFragment, tag);
        ft.commit();

        /*
        TO DO: http://stacktips.com/tutorials/android/youtubeplayerview-example-in-android-using-youtube-api
        Change YouTubePlayerFragment to YouTubePlayerView for main video. Webview for others.

        UI Fixes
        */
        //Testing
        webViewResultsVideo = (WebView) mView.findViewById(R.id.webViewResultsVideo);
        webViewResultsVideo.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings = webViewResultsVideo.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webViewTestimonials1Video = (WebView) mView.findViewById(R.id.webViewResultsTestimonialVideo1);
        webViewTestimonials1Video.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings1 = webViewTestimonials1Video.getSettings();
        webSettings1.setJavaScriptEnabled(true);

        webViewTestimonials2Video = (WebView) mView.findViewById(R.id.webViewResultsTestimonialVideo2);
        webViewTestimonials2Video.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings2 = webViewTestimonials2Video.getSettings();
        webSettings2.setJavaScriptEnabled(true);

        webViewTestimonials3Video = (WebView) mView.findViewById(R.id.webViewResultsTestimonialVideo3);
        webViewTestimonials3Video.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }
        });

        WebSettings webSettings3 = webViewTestimonials3Video.getSettings();
        webSettings3.setJavaScriptEnabled(true);

        caller = new ApiCaller();
        caller.GetBilanMinceurVideo(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                if (output != null) {
                    BMVideoResponseContract c = (BMVideoResponseContract) output;

                    if (c != null && c.Data != null) {
                        RefreshPlayer(mView, c.Data.DietProfileVideo);
                    }
                }
            }
        });

        questionIndex = 1;
        configureGenderButton();

        return mView;
    }

    @Override
    public void onClick(View v) {
        if (v.getTag() instanceof Integer)
        {
            int answerTag = (int)v.getTag();
            if (answerTag >= 1 && answerTag <= 10 && questionIndex <= 11)
            {
                answers[questionIndex-2] = answerTag;
                if (questionIndex <= 10)
                {
                    createQuestionsList();
                }
                else
                {
                    final LinearLayout qHeaderLayout = (LinearLayout) mView.findViewById(R.id.dietProfileQuestionsHeaderLayout);
                    final LinearLayout vsHeaderLayout = (LinearLayout) mView.findViewById(R.id.dietProfilVitalStatsHeaderLayout);
                    final LinearLayout dietProfileQuestionsListLayout = (LinearLayout) mView.findViewById(R.id.dietProfileQuestionsListLayout);
                    final LinearLayout lVitalStats = (LinearLayout) mView.findViewById(R.id.lVitalStats);

                    qHeaderLayout.setVisibility(View.GONE);
                    dietProfileQuestionsListLayout.setVisibility(View.GONE);
                    lVitalStats.setVisibility(View.VISIBLE);
                    vsHeaderLayout.setVisibility(View.VISIBLE);
                    Button btnVitalStatsProcess =  (Button) mView.findViewById(R.id.btnVitalStatsProcess);
                    btnVitalStatsProcess.setOnClickListener(this);
                }
            }
        }
        else
        {
            String buttonTag = (String)v.getTag();
            switch (buttonTag)
            {
                case "buttonReturnToResultsPage1":
                    viewResultPage(1);
                    break;
                case "buttonReturnToResultsPage2":
                case "buttonToResultsPage2":
                    viewResultPage(2);
                    break;
                case "buttonReturnToResultsPage3":
                case "buttonToResultsPage3":
                    viewResultPage(3);
                    break;
                case "buttonReturnToResultsPage4":
                case "buttonToResultsPage4":
                    viewResultPage(4);
                    break;
                case "buttonToResultsPage5":
                    viewResultPage(5);
                    break;
                case "buttonToResultsPage6":
                    viewResultPage(6);
                    break;
                case "btnVitalStatsProcess":
                    if (validateVitalStats()) {
                        final LinearLayout persoHeaderLayout = (LinearLayout) mView.findViewById(R.id.dietProfilPersonalInfoHeaderLayout);
                        final LinearLayout vsHeaderLayout = (LinearLayout) mView.findViewById(R.id.dietProfilVitalStatsHeaderLayout);
                        final LinearLayout lVitalStats = (LinearLayout) mView.findViewById(R.id.lVitalStats);
                        final ScrollView sPersonalInfo = (ScrollView) mView.findViewById(R.id.sPersonalInfo);

                        lVitalStats.setVisibility(View.GONE);
                        vsHeaderLayout.setVisibility(View.GONE);
                        persoHeaderLayout.setVisibility(View.VISIBLE);
                        sPersonalInfo.setVisibility(View.VISIBLE);
                        Button btnPersoInfoProcess = (Button) mView.findViewById(R.id.btnPersoInfoProcess);
                        btnPersoInfoProcess.setOnClickListener(this);
                    }
                    break;
                case "btnPersoInfoProcess":
                    if (validatePersonalInfo()) {
                        final LinearLayout dietProfileQuestionsLayout = (LinearLayout) mView.findViewById(R.id.dietProfileQuestionsLayout);
                        dietProfileQuestionsLayout.setVisibility(View.GONE);
                        postResults();
                    }
                    break;
                case "imgBtnFem":
                case "imgBtnMale":
                    if (buttonTag.equals("imgBtnFem")) {
                        ImageButton imgBtn2 = (ImageButton) mView.findViewById(R.id.imgBtnFem);
                        imgBtn2.setImageResource(R.drawable.gender_woman_active);
                        gender = "0";

                    }
                    else {
                        gender = "1";
                        ImageButton imgBtn1 = (ImageButton) mView.findViewById(R.id.imgBtnMale);
                        imgBtn1.setImageResource(R.drawable.gender_man_active);
                    }

                    final RelativeLayout genderLayout = (RelativeLayout) getView().findViewById(R.id.rlGenderQuestion);
                    final LinearLayout dietProfileQuestionsListLayout = (LinearLayout) getView().findViewById(R.id.dietProfileQuestionsListLayout);


                    final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            genderLayout.setVisibility(View.GONE);
                            dietProfileQuestionsListLayout.setVisibility(View.VISIBLE);
                            getQuestions(gender.toString());
                        }
                    }, 500);



                    break;
                default:
                    break;
            }
        }
        // default method for handling onClick Events..
    }

    private boolean validateVitalStats() {
        EditText editHeight = (EditText) mView.findViewById(R.id.editHeight);
        EditText editCurrentWeight = (EditText) mView.findViewById(R.id.editCurrentWeight);
        EditText editIdealWeight = (EditText) mView.findViewById(R.id.editIdealWeight);

        TextView textViewHeightError = (TextView) mView.findViewById(R.id.textViewHeightError);
        TextView textViewWeightError = (TextView) mView.findViewById(R.id.textViewWeightError);
        TextView textViewTargetWeightError = (TextView) mView.findViewById(R.id.textViewTargetWeightError);

        String errorHeight = InputValidatorUtil.isValidHeight(editHeight.getText().toString().trim());
        String errorCurrentWeight = InputValidatorUtil.isValidCurrentWeight(editCurrentWeight.getText().toString().trim(),editHeight.getText().toString().trim());
        String errorTargetWeight = InputValidatorUtil.isValidTargetWeight(editIdealWeight.getText().toString().trim(),editCurrentWeight.getText().toString().trim(),editHeight.getText().toString().trim());

        if (errorHeight.length() > 0)
        {
            textViewHeightError.setText(errorHeight);
            textViewHeightError.setVisibility(View.VISIBLE);
        }
        else
            textViewHeightError.setVisibility(View.GONE);

        if (errorCurrentWeight.length() > 0)
        {
            textViewWeightError.setText(errorCurrentWeight);
            textViewWeightError.setVisibility(View.VISIBLE);
        }
        else
            textViewWeightError.setVisibility(View.GONE);

        if (errorTargetWeight.length() > 0)
        {
            textViewTargetWeightError.setText(errorTargetWeight);
            textViewTargetWeightError.setVisibility(View.VISIBLE);
        }
        else
            textViewTargetWeightError.setVisibility(View.GONE);

        if (textViewHeightError.getVisibility() == View.VISIBLE || textViewWeightError.getVisibility() == View.VISIBLE || textViewTargetWeightError.getVisibility() == View.VISIBLE)
            return false;

        return true;
    }

    private boolean validatePersonalInfo(){
        EditText editEmailAddress = (EditText) mView.findViewById(R.id.editEmailAddress);
        EditText editFirstname = (EditText) mView.findViewById(R.id.editFirstname);
        EditText editSurname = (EditText) mView.findViewById(R.id.editSurname);
        EditText editPhone = (EditText) mView.findViewById(R.id.editPhone);
        CheckBox checkBoxPhone = (CheckBox) mView.findViewById(R.id.checkBoxPhone);

        TextView textViewErrorEmailAddress = (TextView) mView.findViewById(R.id.textViewErrorEmailAddress);
        TextView textViewErrorFirstname = (TextView) mView.findViewById(R.id.textViewErrorFirstname);
        TextView textViewErrorSurname = (TextView) mView.findViewById(R.id.textViewErrorSurname);
        TextView textViewErrorPhone = (TextView) mView.findViewById(R.id.textViewErrorPhone);

        if (!InputValidatorUtil.isValidEmail(editEmailAddress.getText().toString().trim()))
            textViewErrorEmailAddress.setVisibility(View.VISIBLE);
        else
            textViewErrorEmailAddress.setVisibility(View.GONE);

        if (editFirstname.getText().toString().isEmpty())
            textViewErrorFirstname.setVisibility(View.VISIBLE);
        else
            textViewErrorFirstname.setVisibility(View.GONE);

        if (editSurname.getText().toString().isEmpty())
            textViewErrorSurname.setVisibility(View.VISIBLE);
        else
            textViewErrorSurname.setVisibility(View.GONE);

        boolean boolcheckBoxPhone = checkBoxPhone.isChecked();

        if (!boolcheckBoxPhone && !InputValidatorUtil.isValidPhoneFormat(editPhone.getText().toString().trim()))
            textViewErrorPhone.setVisibility(View.VISIBLE);
        else
            textViewErrorPhone.setVisibility(View.GONE);

        if (textViewErrorEmailAddress.getVisibility() == View.VISIBLE || textViewErrorFirstname.getVisibility() == View.VISIBLE || textViewErrorSurname.getVisibility() == View.VISIBLE || textViewErrorPhone.getVisibility() == View.VISIBLE)
            return  false;

        return true;
    }

    private void configureGenderButton() {
        ImageButton imgBtnFem =  (ImageButton) mView.findViewById(R.id.imgBtnFem);
        ImageButton imgBtnMale =  (ImageButton) mView.findViewById(R.id.imgBtnMale);
        imgBtnFem.setOnClickListener(this);
        imgBtnMale.setOnClickListener(this);
    }

    private void getQuestions(String genderValue) {
        questionsList = new ArrayList<QuestionsContract>();

        caller = new ApiCaller();
        caller.GetBilanMinceurQuestions(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                if (output != null) {
                    BMQuestionsResponseContract c = (BMQuestionsResponseContract) output;

                    if (c != null && c.Data != null && c.Data.Questions != null) {
                        questionsList = (List< QuestionsContract>) c.Data.Questions;
                        ApplicationData.getInstance().questionsList = questionsList;
                        createQuestionsList();
                    }
                }
            }
        }, genderValue );
    }

    private void createQuestionsList() {
        final LinearLayout qLayout = (LinearLayout) mView.findViewById(R.id.lQuestionsList);
        List<AnswersContract> answersList = new ArrayList<AnswersContract>();
        String question;

        if (ApplicationData.getInstance().questionsList != null && ApplicationData.getInstance().questionsList.size() > 0) {
            questionsList = ApplicationData.getInstance().questionsList;
        }
        else {
            getQuestions(gender);
        }

        LinearLayout.LayoutParams paramsQuestionButton = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );
        paramsQuestionButton.setMargins(0, 0, 0, 8);

        for (QuestionsContract q : questionsList) {
            if (q.QuestionIndex == questionIndex)
            {
                question = q.Question;
                answersList = q.Answers;
                qLayout.removeAllViews();

                TextView textViewQuestion = (TextView) mView.findViewById(R.id.textViewQuestion);
                textViewQuestion.setTextColor(Color.parseColor("#323232"));
                textViewQuestion.setText(q.QuestionIndex.toString() + ". " + question);

                for (AnswersContract a : answersList) {
                    Button btn1 = new Button(context);
                    btn1.setTag(a.AnswerIndex);
                    btn1.setOnClickListener(this);
                    btn1.setTextSize(12);
                    btn1.setGravity(Gravity.LEFT);
                    btn1.setPadding(50,btn1.getPaddingTop()+5,btn1.getPaddingRight(),btn1.getPaddingBottom());

                    btn1.setLayoutParams(paramsQuestionButton);
                    btn1.setBackgroundResource(R.drawable.button_lightgray_roundedcorners);
                    btn1.setText(a.Answer);
                    qLayout.addView(btn1);
                }
                break;
            }
        }
        questionIndex += 1;
    }

    private void postResults() {
        EditText editHeight = (EditText) mView.findViewById(R.id.editHeight);
        EditText editCurrentWeight = (EditText) mView.findViewById(R.id.editCurrentWeight);
        EditText editIdealWeight = (EditText) mView.findViewById(R.id.editIdealWeight);
        EditText editEmailAddress = (EditText) mView.findViewById(R.id.editEmailAddress);
        EditText editFirstname = (EditText) mView.findViewById(R.id.editFirstname);
        EditText editSurname = (EditText) mView.findViewById(R.id.editSurname);
        EditText editPhone = (EditText) mView.findViewById(R.id.editPhone);

        BMContract bmContract = new BMContract();
        bmContract.Gender = gender;
        bmContract.Answers = Arrays.asList(answers);
        bmContract.HeightInCm = Double.parseDouble(editHeight.getText().toString());
        bmContract.CurrentWeightKg = Double.parseDouble(editCurrentWeight.getText().toString());
        bmContract.StartWeightKg = Double.parseDouble(editCurrentWeight.getText().toString());
        bmContract.TargetWeightKg = Double.parseDouble(editIdealWeight.getText().toString());
        bmContract.Email = editEmailAddress.getText().toString();
        bmContract.FirstName = editFirstname.getText().toString();
        bmContract.LastName = editSurname.getText().toString();
        bmContract.Phone = editPhone.getText().toString();

        BMResultsResponseContract responseContract = new BMResultsResponseContract();

        caller = new ApiCaller();

        caller.PostBilanMinceurQuestions(new AsyncResponse() {
            @Override
            public void processFinish(Object output) {
                if (output != null) {
                    BMResultsResponseContract c = (BMResultsResponseContract) output;

                    if (c !=  null && c.Data != null)
                    {
                        buildResultsPage(c.Data);
                    }
                }
            }
        }, bmContract);
    }

    private void buildResultsPage(ResultsResponseDataContract resultsData) {
        ApplicationData.getInstance().bilanminceurResults = resultsData;

        final LinearLayout dietProfileResultsLayout = (LinearLayout) mView.findViewById(R.id.dietProfileResultsLayout);
        dietProfileResultsLayout.setVisibility(View.VISIBLE);

        final ForegroundColorSpan orangeFont = new ForegroundColorSpan(Color.parseColor("#f2680d"));
        final StyleSpan boldFont = new StyleSpan(android.graphics.Typeface.BOLD);

        final TextView textViewGreeting = (TextView) mView.findViewById(R.id.textViewGreeting);
        final TextView textViewProgramme = (TextView) mView.findViewById(R.id.textViewProgramme);
        final TextView textViewResultPage2Content = (TextView) mView.findViewById(R.id.textViewResultPage2Content);
        final TextView textViewCurrentWeight = (TextView) mView.findViewById(R.id.textViewCurrentWeight);
        final TextView textViewTargetWeight = (TextView) mView.findViewById(R.id.textViewTargetWeight);
        final TextView textViewCurrentIMC     = (TextView) mView.findViewById(R.id.textViewCurrentIMC    );
        final TextView textViewAverageIMC = (TextView) mView.findViewById(R.id.textViewAverageIMC);
        final TextView textViewTargetIMC = (TextView) mView.findViewById(R.id.textViewTargetIMC);
        final TextView textViewMyIMC = (TextView) mView.findViewById(R.id.textViewMyIMC);
        final TextView textViewQ3Result = (TextView) mView.findViewById(R.id.textViewQ3Result);
        final TextView textViewTestimonialTitle1 = (TextView) mView.findViewById(R.id.textViewTestimonialTitle1);
        final TextView textViewTestimonialTitle2 = (TextView) mView.findViewById(R.id.textViewTestimonialTitle2);
        final TextView textViewTestimonialTitle3 = (TextView) mView.findViewById(R.id.textViewTestimonialTitle3);
        final TextView textViewQ4ResultTitle = (TextView) mView.findViewById(R.id.textViewQ4ResultTitle);
        final TextView textViewQ4ResultContent = (TextView) mView.findViewById(R.id.textViewQ4ResultContent);
        final TextView textViewResultPage6Content1 = (TextView) mView.findViewById(R.id.textViewResultPage6Content1);
        final TextView textViewResultPage6Content2 = (TextView) mView.findViewById(R.id.textViewResultPage6Content2);

        final TextView textViewCurrentIMCGraph = (TextView) mView.findViewById(R.id.textViewCurrentIMCGraph);
        final TextView textViewTargetIMCGraph = (TextView) mView.findViewById(R.id.textViewTargetIMCGraph);

        EditText editFirstname = (EditText) mView.findViewById(R.id.editFirstname);
        String firstName = editFirstname.getText().toString();

        ImageView imageViewGenderGraph= (ImageView) mView.findViewById(R.id.imageViewGenderGraph);

        String greeting = "Bravo, je vais vous aider à perdre ces "+  resultsData.TargetWeightLoss +" kilos et à " + resultsData.Q6Result;
        textViewGreeting.setText(resultsData.Q6Result);
        Spannable spannableGreeting = new SpannableString(greeting);
        spannableGreeting.setSpan(orangeFont, greeting.indexOf(resultsData.TargetWeightLoss), greeting.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewGreeting.setText(spannableGreeting, TextView.BufferType.SPANNABLE);
        textViewProgramme.setText("En suivant mon programme, vous allez bénéficier de tous les outils et conseils pour atteindre votre objectif de "+ resultsData.TargetWeight +" kilos. Cette méthode de rééquilibrage alimentaire sera bénéfique pour votre santé et complètement adaptée à votre profil.");

        String page2Content = "Vous m'avez indiqué que votre objectif était d'atteindre le poids de " + resultsData.TargetWeight + " kg.";
        String page2FullContent = page2Content + " " + resultsData.NormalWeightContent.replace("<strong>","").replace("</strong>","").replace("<strong class=\"darken\">","");
        Spannable spannablePage2Content = new SpannableString(page2FullContent);
        spannablePage2Content.setSpan(boldFont, page2FullContent.indexOf(resultsData.TargetWeight),page2Content.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannablePage2Content.setSpan(boldFont, page2FullContent.indexOf("compris entre") + 13,page2FullContent.indexOf(" kg. C'est donc"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewResultPage2Content.setText(spannablePage2Content, TextView.BufferType.SPANNABLE);

        textViewCurrentWeight.setText(resultsData.CurrentWeight+" kg");
        textViewTargetWeight.setText(resultsData.TargetWeight +" kg");
        textViewCurrentIMC.setText("IMC:" + resultsData.CurrentIMC);
        textViewAverageIMC.setText(resultsData.AverageIMC);
        textViewTargetIMC.setText(resultsData.TargetIMC);
        textViewMyIMC.setText("Votre IMC : Comme vous pesez "+ resultsData.CurrentWeight +" kg pour " + resultsData.Height +" cm, votre Indice de Masse Corporelle (IMC) est actuellement de "+ resultsData.CurrentIMC +".");
        textViewCurrentIMCGraph.setText(resultsData.CurrentIMC);
        textViewTargetIMCGraph.setText(resultsData.TargetIMC);

        textViewQ3Result.setText(resultsData.Q3Result);
        textViewTestimonialTitle1.setText(resultsData.TestimonialTitle1);
        textViewTestimonialTitle2.setText(resultsData.TestimonialTitle2);
        textViewTestimonialTitle3.setText(resultsData.TestimonialTitle3);
        textViewQ4ResultTitle.setText(resultsData.Q4ResultTitle);
        textViewQ4ResultContent.setText(resultsData.Q4ResultContent);

        String resultPage6Content1 = "Vous avez " + resultsData.TargetWeightLoss + " kilos à perdre et je vous recommande mon programme qui va vous permettre de perdre tranquillement ces kilos puis de profiter d'une période de stabilisation pour muscler votre corps ! Rappelez-vous que votre succès est important pour votre santé mais aussi pour votre bien être et votre confiance en vous. Savoir maigrir est bien plus efficace qu'un régime : c'est le rééquilibrage alimentaire qui vous servira tout au long de votre vie.";
        Spannable spannableresultPage6Content1 = new SpannableString(resultPage6Content1);
        spannableresultPage6Content1.setSpan(orangeFont, 0,resultPage6Content1.indexOf(" et je vous recommande"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableresultPage6Content1.setSpan(boldFont, 0,resultPage6Content1.indexOf(" et je vous recommande"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableresultPage6Content1.setSpan(orangeFont, resultPage6Content1.indexOf("mon programme"),resultPage6Content1.indexOf(" qui va vous permettre "), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableresultPage6Content1.setSpan(boldFont, resultPage6Content1.indexOf("mon programme"),resultPage6Content1.indexOf(" qui va vous permettre"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewResultPage6Content1.setText(spannableresultPage6Content1, TextView.BufferType.SPANNABLE);

        String resultPage6Content2 = "Vous pouvez avoir une confiance totale dans la méthode Savoir Maigrir, "+ firstName +", et nous vous le prouvons en prenant cet engagement : si vous n'avez pas pu atteindre votre juste poids à la fin du programme, nous nous engageons à vous le rembourser intégralement.";
        Spannable spannableresultPage6Content2 = new SpannableString(resultPage6Content2);
        spannableresultPage6Content2.setSpan(orangeFont, resultPage6Content2.indexOf(firstName),resultPage6Content2.indexOf(", et nous vous"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        spannableresultPage6Content2.setSpan(boldFont, resultPage6Content2.indexOf(firstName),resultPage6Content2.indexOf(", et nous vous"), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textViewResultPage6Content2.setText(spannableresultPage6Content2, TextView.BufferType.SPANNABLE);

        if (gender == "1")
        {
            imageViewGenderGraph.setImageResource(R.drawable.results02_male);
        }

        webViewResultsVideo.setWebChromeClient(new WebChromeClient());
        webViewResultsVideo.loadData("<html><body><iframe heigth=\"100%\" width=\"100%\" src=\"https://www.youtube.com/embed/"+ resultsData.MainVideo +"\" frameborder=\"0\" allowfullscreen></iframe></body></html>","text/html", "utf-8");

        webViewTestimonials1Video.setWebChromeClient(new WebChromeClient());
        webViewTestimonials1Video.loadData("<html><body><iframe heigth=\"100%\" width=\"100%\" src=\"https://www.youtube.com/embed/"+ resultsData.TestimonialVideo1 +"\" frameborder=\"0\" allowfullscreen></iframe></body></html>","text/html", "utf-8");

        webViewTestimonials2Video.setWebChromeClient(new WebChromeClient());
        webViewTestimonials2Video.loadData("<html><body><iframe heigth=\"100%\" width=\"100%\" src=\"https://www.youtube.com/embed/"+ resultsData.TestimonialVideo2 +"\" frameborder=\"0\" allowfullscreen></iframe></body></html>","text/html", "utf-8");

        webViewTestimonials3Video.setWebChromeClient(new WebChromeClient());
        webViewTestimonials3Video.loadData("<html><body><iframe heigth=\"100%\" width=\"100%\" src=\"https://www.youtube.com/embed/"+ resultsData.TestimonialVideo3 +"\" frameborder=\"0\" allowfullscreen></iframe></body></html>","text/html", "utf-8");

        //RefreshPlayer(mView, resultsData.MainVideo);

        Button buttonToResultsPage2 = (Button) mView.findViewById(R.id.buttonToResultsPage2);
        buttonToResultsPage2.setOnClickListener(this);

        Button buttonToResultsPage3 = (Button) mView.findViewById(R.id.buttonToResultsPage3);
        buttonToResultsPage3.setOnClickListener(this);

        Button buttonToResultsPage4 = (Button) mView.findViewById(R.id.buttonToResultsPage4);
        buttonToResultsPage4.setOnClickListener(this);

        Button buttonToResultsPage5 = (Button) mView.findViewById(R.id.buttonToResultsPage5);
        buttonToResultsPage5.setOnClickListener(this);

        Button buttonToResultsPage6 = (Button) mView.findViewById(R.id.buttonToResultsPage6);
        buttonToResultsPage6.setOnClickListener(this);

        Button buttonReturnToResultsPage1 = (Button) mView.findViewById(R.id.buttonReturnToResultsPage1);
        buttonReturnToResultsPage1.setOnClickListener(this);

        Button buttonReturnToResultsPage2 = (Button) mView.findViewById(R.id.buttonReturnToResultsPage2);
        buttonReturnToResultsPage2.setOnClickListener(this);

        Button buttonReturnToResultsPage3 = (Button) mView.findViewById(R.id.buttonReturnToResultsPage3);
        buttonReturnToResultsPage3.setOnClickListener(this);

        Button buttonReturnToResultsPage4 = (Button) mView.findViewById(R.id.buttonReturnToResultsPage4);
        buttonReturnToResultsPage4.setOnClickListener(this);

        //Button buttonReturnToResultsPage5 = (Button) mView.findViewById(R.id.buttonReturnToResultsPage5);
        //buttonReturnToResultsPage5.setOnClickListener(this);
    }

    private void viewResultPage(int pageNo) {
        final LinearLayout dietProfileResultsPage1 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage1);
        final LinearLayout dietProfileResultsPage2 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage2);
        final LinearLayout dietProfileResultsPage3 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage3);
        final LinearLayout dietProfileResultsPage4 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage4);
        final LinearLayout dietProfileResultsPage5 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage5);
        final LinearLayout dietProfileResultsPage6 = (LinearLayout) mView.findViewById(R.id.dietProfileResultsPage6);
        ResultsResponseDataContract resultsData = ApplicationData.getInstance().bilanminceurResults;

        ImageView imageViewProgressBar= (ImageView) mView.findViewById(R.id.imageViewProgressBar);

        dietProfileResultsPage1.setVisibility(View.GONE);
        dietProfileResultsPage2.setVisibility(View.GONE);
        dietProfileResultsPage3.setVisibility(View.GONE);
        dietProfileResultsPage4.setVisibility(View.GONE);
        dietProfileResultsPage5.setVisibility(View.GONE);
        dietProfileResultsPage6.setVisibility(View.GONE);

        switch (pageNo) {
            case 1:
                dietProfileResultsPage1.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress01);
                break;
            case 2:
                dietProfileResultsPage2.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress02);
                break;
            case 3:
                dietProfileResultsPage3.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress03);
                break;
            case 4:
                dietProfileResultsPage4.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress04);
                break;
            case 5:
                dietProfileResultsPage5.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress05);
                break;
            case 6:
                dietProfileResultsPage6.setVisibility(View.VISIBLE);
                imageViewProgressBar.setImageResource(R.drawable.results_progress06);
                break;
        }
    }

    private void RefreshPlayer(final View v, final String videoId) {

        playerFragment.initialize(SavoirMaigrirVideoConstants.YOUTUBE_API_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, final YouTubePlayer youTubePlayer, boolean b) {
                if (videoId != null) {

                    youTubePlayer.cueVideo(videoId);

                    youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                        @Override
                        public void onBuffering(boolean arg0) {
                        }

                        @Override
                        public void onPaused() {
                        }

                        @Override
                        public void onPlaying() {
                            //youTubePlayer.setFullscreen(true);
                        }

                        @Override
                        public void onSeekTo(int arg0) {
                        }

                        @Override
                        public void onStopped() {
                            //youTubePlayer.setFullscreen(false);
                        }
                    });

                    youTubePlayer.setOnFullscreenListener(new YouTubePlayer.OnFullscreenListener() {
                        @Override
                        public void onFullscreen(boolean b) {
                            if (!b){
                                getActivity().setRequestedOrientation(
                                        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                            }
                        }
                    });
                }

            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
                //Toast.makeText(YouTubePlayerFragmentActivity.this, "Error while initializing YouTubePlayer.", Toast.LENGTH_SHORT).show();
            }


        });

    }


}
