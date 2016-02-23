package com.christmas.citp.christmasinthepark;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    // Button Variables
    private ImageButton mFB;
    private ImageButton mInsta;
    private ImageButton mTwitter;
    private ImageButton mYT;
    private ImageButton mDonate;
    private ImageButton mCITP;

    // Ornament Button Variables
    private ImageButton mOrn1;
    private ImageButton mOrn2;
    private ImageButton mOrn3;
    private ImageButton mOrn4;
    private ImageButton mOrn5;
    private ImageButton mOrn6;
    private ImageButton mOrn7;
    private ImageButton mOrn8;
    private ImageButton mOrn9;

    // Navigation Drawer Variables
    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private String mActivityTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ACTION BAR MODIFICATIONS
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#1c2e44")));

        // DRAWER
        mDrawerList = (ListView) findViewById(R.id.navList);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mActivityTitle = getTitle().toString();
        addDrawerItems();
        setupDrawer();

        // BUTTONS

        mCITP = (ImageButton) findViewById(R.id.citp_logo);
        mCITP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                {
                    Intent site = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.christmasinthepark.com"));
                    startActivity(site);
                }
            }
        });

        mFB = (ImageButton) findViewById(R.id.fb_icon_final);
        mFB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // CHECK IF FB IS INSTALLED. If not, open page in Browser
                boolean installed = appInstalledOrNot("com.facebook.katana");
                if (installed) {
                    // Ravi's FB Chooser
                    Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("fb://page/183825781669765"));
                    Intent chooser = Intent.createChooser(fb, "facebook");
                    startActivity(chooser);
                } else {
                    Intent fb = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/ChristmasintheParkSJ"));
                    startActivity(fb);
                }
            }
        });

        mTwitter = (ImageButton) findViewById(R.id.twitter_icon_final);
        mTwitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent twitter = new Intent(Intent.ACTION_VIEW, Uri.parse("https://twitter.com/xmasintheparksj"));
                startActivity(twitter);
            }
        });

        mYT = (ImageButton) findViewById(R.id.yt_icon_final);
        mYT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent yt = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/user/xmasinthepark"));
                startActivity(yt);
            }
        });

        mInsta = (ImageButton) findViewById(R.id.insta_icon_final);
        mInsta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Ravi's Instagram Link
                Intent insta = new Intent(Intent.ACTION_VIEW, Uri.parse("https://instagram.com/christmasintheparksj"));
                startActivity(insta);
            }
        });

        mDonate = (ImageButton) findViewById(R.id.donate_icon); // Ravi's version cleaned up
        mDonate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent donate = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.giveffect.com/charities/704"));
                startActivity(donate);
            }
        });

        // ORNAMENTS

        // "FAQ"
        mOrn1 = (ImageButton) findViewById(R.id.ornament1);
        mOrn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent faq = new Intent(MainActivity.this, ContentViewActivity.class);
                faq.putExtra("Url", "http://christmasinthepark.com/faq.html?json=get_recent_posts");
                startActivity(faq);
            }
        });

        // "MAP" -- IMAGES WIP
        mOrn2 = (ImageButton) findViewById(R.id.ornament2);
        mOrn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent map = new Intent(MainActivity.this, WebViewActivity.class);
                map.putExtra("Url", "http://christmasinthepark.com/wp-content/uploads/2014/03/Map-Brochure-2014-1024x576.jpg");
                startActivity(map);
            }
        });

        // "PARKING" -- IMAGES WIP
        mOrn3 = (ImageButton) findViewById(R.id.ornament3);
        mOrn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent parking = new Intent(MainActivity.this, ContentViewActivity.class);
                parking.putExtra("Url", "http://christmasinthepark.com/directions.html?json=get_recent_posts");
                startActivity(parking);
            }
        });

        // "FOOD" -- IMAGES WIP
        mOrn4 = (ImageButton) findViewById(R.id.ornament4);
        mOrn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food = new Intent(MainActivity.this, ContentViewActivity.class);
                food.putExtra("Url", "http://christmasinthepark.com/vendors.html?json=get_recent_posts");
                startActivity(food);
            }
        });

        // "PARTNERS" -- HEAVY WIP
        mOrn5 = (ImageButton) findViewById(R.id.ornament5);
        mOrn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent partners = new Intent(MainActivity.this, ContentViewActivity.class);
                partners.putExtra("Url", "http://christmasinthepark.com/partners.html?json=get_recent_posts");
                startActivity(partners);
            }
        });

        // "CALENDAR" -- HEAVY WIP
        mOrn6 = (ImageButton) findViewById(R.id.ornament6);
        mOrn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent calendar = new Intent(MainActivity.this, ContentViewActivity.class);
                calendar.putExtra("Url", "http://christmasinthepark.com/calendar.html?json=get_recent_posts");
                startActivity(calendar);
            }
        });

        // "VOLUNTEER"
        mOrn7 = (ImageButton) findViewById(R.id.ornament7);
        mOrn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent vol = new Intent(MainActivity.this, ContentViewActivity.class);
                vol.putExtra("Url", "http://christmasinthepark.com/volunteer.html?json=get_recent_posts");
                startActivity(vol);
            }
        });

        // "FUN FACTS"
        mOrn8 = (ImageButton) findViewById(R.id.ornament8);
        mOrn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ff = new Intent(MainActivity.this, ContentViewActivity.class);
                ff.putExtra("Url", "http://christmasinthepark.com/did-you-know.html?json=get_recent_posts");
                startActivity(ff);
            }
        });

        // "HISTORY"
        mOrn9 = (ImageButton) findViewById(R.id.ornament9);
        mOrn9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent history = new Intent(MainActivity.this, ContentViewActivity.class);
                history.putExtra("Url", "http://christmasinthepark.com/history-community.html?json=get_recent_posts");
                startActivity(history);
            }
        });
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    // Keep objects in sync when rotating landscape/portrait
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        // Checking if the user clicked on the Navigation Drawer
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    // HELPER METHODS
    private void addDrawerItems() {
        String[] navArray = {"About Us", "Board of Directors", "Press Releases", "Contact Us", "Android App Team"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, navArray);
        mDrawerList.setAdapter(mAdapter);
        mDrawerList.setOnItemClickListener(this);
    }

    private void setupDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getSupportActionBar().setTitle("Navigation");
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getSupportActionBar().setTitle(mActivityTitle);
                invalidateOptionsMenu(); // creates call to onPrepareOptionsMenu()
            }
        };
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    private void selectItem(int position) {
        switch(position) {
            case 0:
                Intent a = new Intent(MainActivity.this, AboutActivity.class);
                startActivity(a);
                break;
            case 1:
                Intent b = new Intent(MainActivity.this, DirectorsActivity.class);
                startActivity(b);
                break;
            case 2:
                Intent c = new Intent(MainActivity.this, PRActivity.class);
                startActivity(c);
                break;
            case 3:
                Intent contact = new Intent(MainActivity.this, ContentViewActivity.class);
                contact.putExtra("Url", "http://christmasinthepark.com/contact.html?json=get_recent_posts");
                startActivity(contact);
                break;
            case 4:
                Intent e = new Intent(MainActivity.this, TeamActivity.class);
                startActivity(e);
                break;
            default:
        }
    }

    // Check if FB is installed on device.
    private boolean appInstalledOrNot(String uri) {
        PackageManager pm = getPackageManager();
        boolean app_installed = false;
        try {
            pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
            app_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            app_installed = false;
        }
        return app_installed;
    }
}