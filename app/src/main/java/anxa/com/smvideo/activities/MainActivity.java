package anxa.com.smvideo.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import anxa.com.smvideo.ApplicationData;
import anxa.com.smvideo.R;
import anxa.com.smvideo.models.NavItem;
import anxa.com.smvideo.ui.DrawerListAdapter;


public class MainActivity extends BaseVideoActivity implements View.OnClickListener {

    private static String TAG = MainActivity.class.getSimpleName();

    ListView mDrawerList;
    RelativeLayout mDrawerPane;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    ArrayList<NavItem> mNavItems = new ArrayList<NavItem>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavItems.add(new NavItem(getString(R.string.menu_decouvrir), R.drawable.decouvrez_ico));
        mNavItems.add(new NavItem(getString(R.string.menu_bilan), R.drawable.bilanminceur_ico));
        mNavItems.add(new NavItem(getString(R.string.menu_temoignages), R.drawable.temoignage_ico));
        mNavItems.add(new NavItem(getString(R.string.menu_recettes), R.drawable.recettes_ico));
        mNavItems.add(new NavItem(getString(R.string.menu_mon_compte), R.drawable.compte_ico));

        // DrawerLayout
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

        // Populate the Navigation Drawer with options
        mDrawerPane = (RelativeLayout) findViewById(R.id.drawerPane);
        mDrawerList = (ListView) findViewById(R.id.navList);
        DrawerListAdapter adapter = new DrawerListAdapter(this, mNavItems);
        mDrawerList.setAdapter(adapter);

        // Drawer Item click listeners
        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItemFromDrawer(position);
            }
        });

        //landing page on the first launch
        if (ApplicationData.getInstance().showLandingPage) {
            launchActivity(LandingPageActivity.class);
        }
    }

    @Override
    public void onResume() {
        super.onResume();

        selectItemFromDrawer(ApplicationData.getInstance().selectedFragment.getNumVal());
    }

    /*
 * Called when a particular item from the navigation drawer is selected.*/
    private void selectItemFromDrawer(int position) {
        Fragment fragment = new RecipesActivity();

        switch (position) {
            case 0: //decouvir

                fragment = new DiscoverActivity();

                break;
            case 1: //bilan
                fragment = new BilanMinceurActivity();
                break;
            case 2: //temoignages
                fragment = new TemoignagesActivity();
                break;
            case 3: //recetters
                fragment = new RecipesActivity();

                break;
            case 4: //mon compte
                fragment = new MonCompteActivity();
                break;
            default:
                fragment = new RecipesActivity();


        }

        FragmentManager fragmentManager = getFragmentManager();
        if(getFragmentManager().findFragmentByTag("CURRENT_FRAGMENT") != null){
            fragmentManager.beginTransaction().remove(getFragmentManager().findFragmentByTag("CURRENT_FRAGMENT")).commit();
        }


        fragmentManager.beginTransaction().replace(R.id.mainContent, fragment, "CURRENT_FRAGMENT")
                .commit();




        mDrawerList.setItemChecked(position, true);
        setTitle(mNavItems.get(position).mTitle);

        // Close the drawer
        mDrawerLayout.closeDrawer(mDrawerPane);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.header_menu_iv) {
            //burger menu
            mDrawerLayout.openDrawer(Gravity.LEFT);
        }
    }

    public void launchActivity(Class obj) {
        Intent intent = new Intent(this, obj);
        startActivity(intent);
    }

    public void onBackPressed(View view) {

        getFragmentManager().popBackStack();

    }
}
