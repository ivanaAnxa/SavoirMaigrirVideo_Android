package anxa.com.smvideo.activities;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.connection.http.RecipeDownloadImageAsync;
import anxa.com.smvideo.contracts.RecipeContract;
import anxa.com.smvideo.util.RecipeHelper;
import anxa.com.smvideo.util.UITagHandler;

/**
 * Created by angelaanxa on 5/29/2017.
 */

public class RecipeActivity extends Fragment {
    private List<RecipeContract> recipesList;
    View mView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.recipe, null);
        ((TextView)((RelativeLayout)mView.findViewById(R.id.headermenu)).findViewById(R.id.header_title_tv)).setText(getString(R.string.menu_recette));
        ((ImageView)((RelativeLayout)mView.findViewById(R.id.headermenu)).findViewById(R.id.header_menu_back)).setVisibility(View.VISIBLE);
        ((ImageView)((RelativeLayout)mView.findViewById(R.id.headermenu)).findViewById(R.id.header_menu_iv)).setVisibility(View.GONE);

        String myValue = this.getArguments().getString("message");
        int recipeId = Integer.parseInt(this.getArguments().getString("RECIPE_ID"));
        recipesList = ApplicationData.getInstance().recipeList;
        if(recipeId > 0){
            for(RecipeContract r : recipesList)
            {
               if(r.Id == recipeId)
               {
                   ((TextView)mView.findViewById(R.id.recipeTitle)).setText(r.Title);
                   Bitmap avatar = null;
                   avatar = RecipeHelper.GetRecipeImage(r.Id);
                   ImageView img = (ImageView)mView.findViewById(R.id.recipeImage);
                   img.setTag(r.Id);
                   if (avatar == null) {
                       new RecipeDownloadImageAsync( img,(ProgressBar) mView.findViewById(R.id.recipeImageProgress), r.Id).execute(r.ImageUrl);
                   } else {
                       ((ImageView)mView.findViewById(R.id.recipeImage)).setImageBitmap(avatar);
                       ((ProgressBar) mView.findViewById(R.id.recipeImageProgress)).setVisibility(View.GONE);
                   }
                   ((TextView)mView.findViewById(R.id.recipeIngredientsTitle)).setText((r.IngredientsTitle));
                   ((TextView)mView.findViewById(R.id.recipeIngredients)).setText(Html.fromHtml(r.IngredientsHtml, null, new UITagHandler()));
                   ((TextView)mView.findViewById(R.id.recipePreparation)).setText(Html.fromHtml(r.PreparationHtml, null, new UITagHandler()));
               }
            }
        }
        return mView;
    }


    private Bitmap getAvatar(int recipeId) {
        Bitmap avatarBMP = null;
        if (recipeId > 0) {
            avatarBMP = ApplicationData.getInstance().recipePhotoList.get(String.valueOf(recipeId));

            return avatarBMP;

        }

        return avatarBMP;

    }

}