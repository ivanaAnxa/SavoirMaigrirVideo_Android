package anxa.com.smvideo.activities.account;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.GlideBitmapDrawable;

import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.contracts.RecipeContract;
import anxa.com.smvideo.util.RecipeHelper;
import anxa.com.smvideo.util.UITagHandler;

/**
 * Created by aprilanxa on 04/08/2017.
 */

public class RecipeAccountActivity extends Activity implements View.OnClickListener {
    private List<RecipeContract> recipesList;
    private ImageView backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.recipe);

        ((TextView) ((RelativeLayout) findViewById(R.id.headermenu)).findViewById(R.id.header_title_tv)).setText(getString(R.string.menu_recette));
        ((ImageView) ((RelativeLayout) findViewById(R.id.headermenu)).findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);
        ((TextView) ((RelativeLayout) findViewById(R.id.headermenu)).findViewById(R.id.header_right_tv)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);

        backButton = (ImageView) ((RelativeLayout) findViewById(R.id.headermenu)).findViewById(R.id.header_menu_back);
        backButton.setVisibility(View.VISIBLE);
        backButton.setOnClickListener(this);

        updateUI(ApplicationData.getInstance().selectedRelatedRecipe);
    }

    private void updateUI(RecipeContract recipeContract) {
        ((TextView)findViewById(R.id.recipeTitle)).setText(recipeContract.Title);
        Bitmap avatar = null;
        avatar = RecipeHelper.GetRecipeImage(recipeContract.Id);
        ImageView img = (ImageView) findViewById(R.id.recipeImage);
        //img.setTag(recipeContract.Id);
        if (avatar == null) {

            Glide.with(this).load(recipeContract.ImageUrl).diskCacheStrategy(DiskCacheStrategy.RESULT).into(img);
            try {
                if (!ApplicationData.getInstance().recipePhotoList.containsKey(String.valueOf(recipeContract.Id)) && img.getDrawable() != null) {

                    ApplicationData.getInstance().recipePhotoList.put(String.valueOf(recipeContract.Id), ((GlideBitmapDrawable) img.getDrawable()).getBitmap());
                }

            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            findViewById(R.id.recipeImageProgress).setVisibility(View.GONE);
            //new RecipeDownloadImageAsync(img, (ProgressBar) mView.findViewById(R.id.recipeImageProgress), recipeContract.Id).execute(recipeContract.ImageUrl);
        } else {
            ((ImageView) findViewById(R.id.recipeImage)).setImageBitmap(avatar);
            ((ProgressBar) findViewById(R.id.recipeImageProgress)).setVisibility(View.GONE);
        }
        ((TextView) findViewById(R.id.recipeIngredientsTitle)).setText((recipeContract.IngredientsTitle));
        ((TextView) findViewById(R.id.recipeIngredients)).setText(Html.fromHtml(recipeContract.IngredientsHtml, null, new UITagHandler()));
        ((TextView) findViewById(R.id.recipePreparation)).setText(Html.fromHtml(recipeContract.PreparationHtml, null, new UITagHandler()));
    }


    private Bitmap getAvatar(int recipeId) {
        Bitmap avatarBMP = null;
        if (recipeId > 0) {
            avatarBMP = ApplicationData.getInstance().recipePhotoList.get(String.valueOf(recipeId));

            return avatarBMP;
        }
        return avatarBMP;
    }

    @Override
    public void onClick(final View v) {
        if (v==backButton){
            finish();
        }
    }

}