package anxa.com.smvideo;

import android.app.Application;
import android.graphics.Bitmap;

import com.crashlytics.android.Crashlytics;

import anxa.com.smvideo.contracts.QuestionsContract;
import anxa.com.smvideo.contracts.ResultsResponseDataContract;
import anxa.com.smvideo.contracts.VideoContract;
import io.fabric.sdk.android.Fabric;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import anxa.com.smvideo.contracts.RecipeContract;

/**
 * Created by aprilanxa on 19/05/2017.
 */

public class ApplicationData extends Application {

    public enum SelectedFragment{
        Decouvir(0),
        Bilan(1),
        Temoignages(2),
        Recettes(3),
        MonCompte(4),
        Account_Coaching(5),
        Account_Repas(6),
        Account_Recettes(7),
        Account_Conseil(8),
        Account_Exercices(9),
        Account_Suivi(10),
        Account_MonCompte(11);


        private int numVal;

        SelectedFragment(int numVal) {
            this.numVal = numVal;
        }

        public int getNumVal() {
            return numVal;
        }
    }

    private static ApplicationData instance = null;
    public boolean showLandingPage = true;
    public String accountType = "free";
    public String userName = "User";

    public SelectedFragment selectedFragment = SelectedFragment.Decouvir;

    public Hashtable<String, Bitmap> recipePhotoList = new Hashtable<String, Bitmap>();
    public Hashtable<String, Bitmap> videoPhotoList = new Hashtable<String, Bitmap>();
    public List<RecipeContract> recipeList = new ArrayList<>();
    public List<QuestionsContract> questionsList = new ArrayList<>();
    public List<VideoContract> discoverVideoList = new ArrayList<>();
    public List<VideoContract> testimonialVideoList = new ArrayList<>();
    public ResultsResponseDataContract bilanminceurResults = new ResultsResponseDataContract();
    @Override
    public void onCreate() {
        super.onCreate();
        Fabric.with(this, new Crashlytics());
        instance = this;
    }


    public ApplicationData() {
        super();
    }


    public static ApplicationData getInstance() {
        return instance;
    }

}
