package com.enchantedswords.dragger;

import android.animation.ValueAnimator;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import java.lang.reflect.Field;
import android.net.InetAddresses;
import java.net.Socket;
import java.net.InetAddress;
import java.io.OutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.net.SocketTimeoutException;
import java.net.ConnectException;
import android.os.NetworkOnMainThreadException;
import android.os.AsyncTask;
import android.animation.StateListAnimator;
import android.animation.AnimatorInflater;

public class main_menu extends Activity {
    public int level = 1;
    private int money = 0;
    // To retrieve the money value from SharedPreferences
    private static final String SHARED_PREFS_KEY = "my_shared_prefs";
    private static final String MONEY_KEY = "money";
    private static final String level_KEY = "level";
    private static final String SKIN_KEY = "skin";
    private static final String HASSKIN2_KEY = "hasskin2";
    private static final String HASSKIN3_KEY = "hasskin3";
    private int skin = 1;
    private boolean hasskin2 = false;
    private boolean hasskin3 = false;
    
    
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_menu);
        
        
        
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        money = prefs.getInt(MONEY_KEY, 0); // Default value is 0 if key not found

        prefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        hasskin2 = prefs.getBoolean(HASSKIN2_KEY, false); // Default value is false if key not found
        
        
        prefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        skin = prefs.getInt(SKIN_KEY, 1); // Default value is false if key not found
        
        
        prefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        hasskin3 = prefs.getBoolean(HASSKIN3_KEY, false); // Default value is false if key not found
        
        TextView pricetag3 = findViewById(R.id.boat3_price); 

        TextView pricetag2 = findViewById(R.id.boat2_price); 
        
        
        final Button settings_button = (Button) findViewById(R.id.settings);
        
        final FrameLayout settingslayout = (FrameLayout) findViewById(R.id.settingslayout);
        
        if(skin == 3 && hasskin3)
        {
            
            
            pricetag3.setText(null);
            pricetag3.setBackgroundResource(R.drawable.checkbox_checked);
            
            
        }
        else if(skin != 3 && hasskin3)
        {
            pricetag3.setText(null);
            pricetag3.setBackgroundResource(R.drawable.checkbox);
            
        }
        
        
        if(skin == 2 && hasskin2)
        {


            pricetag2.setText(null);
            pricetag2.setBackgroundResource(R.drawable.checkbox_checked);


        }
        else if(skin != 2 && hasskin2)
        {
            pricetag2.setText(null);
            pricetag2.setBackgroundResource(R.drawable.checkbox);

        }
        
        
        // Update the UI with the current value of money
        final TextView money_count = findViewById(R.id.moneyLabel);

        money_count.setZ(11);
        
        money_count.setText(Integer.toString(money));
        
        final FrameLayout shoplayout = (FrameLayout) findViewById(R.id.shoplayout);
        
        
        final Button play = (Button) findViewById(R.id.play);
        play.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                    editor.putInt(level_KEY, 1);
                    editor.apply();
                    
                    
                    editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                    editor.putInt(SKIN_KEY, skin);
                    editor.apply();
                    
                        Intent intent = new Intent(main_menu.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                  
                       
                        // Handle the exception, for example by showing an error message
                        
                    
                    

                }
			});
            
        final Button paintlevel = (Button) findViewById(R.id.level_paint);

        paintlevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                        Intent intent = new Intent(main_menu.this, level_paint.class);
                        startActivity(intent);
                        finish();
          
                }
			});
            

        final Button level_menu = findViewById(R.id.level_menu);
        final Button back = (Button) findViewById(R.id.back);
        final ScrollView levelselector = findViewById(R.id.levelselector);

        levelselector.setZ(10);
        
        level_menu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {

                        back.setZ(11);
                        back.setVisibility(View.VISIBLE);
                        
                        // Set the visibility of the scroll view to visible
                        if (levelselector.getVisibility() == View.INVISIBLE) {
                            levelselector.setVisibility(View.VISIBLE);

                            // Create a new LinearLayout to hold the buttons
                            LinearLayout buttonLayout = new LinearLayout(main_menu.this);
                            buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                                             LinearLayout.LayoutParams.MATCH_PARENT,
                                                             LinearLayout.LayoutParams.WRAP_CONTENT)); // set layout height to wrap content
                            buttonLayout.setOrientation(LinearLayout.VERTICAL);
                            levelselector.addView(buttonLayout); // add the LinearLayout to the ScrollView

                            // Generate level buttons
                            Field[] drawables = R.drawable.class.getFields();
                            for (final Field drawable : drawables) {
                                if (drawable.getName().startsWith("lvl")) {
                                    Button button = new Button(main_menu.this);
                                    button.setText("Level " + drawable.getName().substring(3));
                                    button.setBackgroundResource(R.drawable.level_button);
                                    // Create a StateListAnimator object
                                    StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(main_menu.this, R.anim.button_anim);
                                    
                                    
// Set the StateListAnimator object to the button
                                    button.setStateListAnimator(stateListAnimator);
                                    

                                    final int levelNumber = Integer.parseInt(drawable.getName().substring(3)); // extract level number from drawable name
                                    button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) 
                                            {
                                                if(levelNumber != 0)
                                                {
                                                    level = levelNumber; // set level variable to the selected level number
                                                    // do something with the selected level drawable
                                                }
                                                else
                                                {
                                                    level = levelNumber+1;
                                                }
                                                
                                                
                                                SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                                                editor.putInt(level_KEY, level);
                                                editor.apply();
                                                
                                                Intent intent = new Intent(main_menu.this, MainActivity.class);
                                                startActivity(intent);

                                                finish();
                                                
                                            }
                                        });
                                    LinearLayout.LayoutParams buttonLayoutParams = new LinearLayout.LayoutParams(
                                        LinearLayout.LayoutParams.MATCH_PARENT,
                                        LinearLayout.LayoutParams.WRAP_CONTENT);
                                    button.setLayoutParams(buttonLayoutParams);
                                    buttonLayout.addView(button); // add the button to the LinearLayout
                                    
                                    
                                    
                                }
                            }
                        } else {
                            levelselector.setVisibility(View.INVISIBLE);
                        }
                        // Scroll to the top of the scroll view
                        levelselector.scrollTo(0, 0);
                    } catch (Exception e) {
                        test.log_error(e, main_menu.this);
                        e.printStackTrace();
                    }
                }
            });
            
            
            
        

        
        final Button boat2 = (Button) findViewById(R.id.boat2);

        boat2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    money = Integer.parseInt(money_count.getText().toString());
                    
                    TextView pricetag2 = findViewById(R.id.boat2_price); 
                    
                    TextView pricetag3 = findViewById(R.id.boat3_price); 
                    
                    
                    
                    if(skin != 2 && hasskin2)
                    {
                    
                    skin = 2;
                    
                    
                        pricetag2.setText(null);
                        pricetag2.setBackgroundResource(R.drawable.checkbox_checked);
                        
                    }
                    else if(hasskin2)
                    {
                        skin = 1;
                        
                        pricetag2.setText(null);
                        pricetag2.setBackgroundResource(R.drawable.checkbox);
                        
                        
                    }
                    
                    if(skin == 3 && hasskin3)
                    {


                        pricetag3.setText(null);
                        pricetag3.setBackgroundResource(R.drawable.checkbox_checked);


                    }
                    else if(skin != 3 && hasskin3)
                    {
                        pricetag3.setText(null);
                        pricetag3.setBackgroundResource(R.drawable.checkbox);

                    }
                    
                    if(money >= 50 && !hasskin2)
                    {
                        skin = 2;
                        hasskin2 = true;
                    money -= 50;
                    money_count.setText(Integer.toString(money));
                        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putInt(MONEY_KEY, money);
                        editor.apply();
                        
                        editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putInt(SKIN_KEY, skin);
                        editor.apply();
                        
                        
                        editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putBoolean(HASSKIN2_KEY, hasskin2);
                        editor.apply();
                        
                        pricetag2.setText(null);
                        pricetag2.setBackgroundResource(R.drawable.checkbox_checked);
                        
                        
                    }
                }
			});
        
            
        final Button boat3 = (Button) findViewById(R.id.boat3);

        boat3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    TextView pricetag3 = findViewById(R.id.boat3_price); 
                    
                    TextView pricetag2 = findViewById(R.id.boat2_price);
                    
                    money = Integer.parseInt(money_count.getText().toString());

                    if(skin != 3 && hasskin3)
                    {

                        skin = 3;

                        pricetag3.setText(null);
                        pricetag3.setBackgroundResource(R.drawable.checkbox_checked);

                    }
                    else if(hasskin3)
                    {
                        skin = 1;
                        
                        
                        pricetag3.setText(null);
                        pricetag3.setBackgroundResource(R.drawable.checkbox);
                        
                    }
                    
                    if(skin == 2 && hasskin2)
                    {


                        pricetag2.setText(null);
                        pricetag2.setBackgroundResource(R.drawable.checkbox_checked);


                    }
                    else if(skin != 2 && hasskin2)
                    {
                        pricetag2.setText(null);
                        pricetag2.setBackgroundResource(R.drawable.checkbox);

                    }
                    

                    if(money >= 50 && !hasskin3)
                    {
                        
                        skin = 3;
                        hasskin3 = true;
                        money -= 50;
                        money_count.setText(Integer.toString(money));
                        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putInt(MONEY_KEY, money);
                        editor.apply();

                        editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putInt(SKIN_KEY, skin);
                        editor.apply();

                        pricetag3.setText(null);

                        pricetag3.setBackgroundResource(R.drawable.checkbox_checked);
                        

                        editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putBoolean(HASSKIN3_KEY, hasskin3);
                        editor.apply();
                        
                        
                        
                        
                        
                        
                        
                        
                    }
                }
			});
        
        
            
        final Button Quit = (Button) findViewById(R.id.quit);

        Quit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    finish();

                }
			});
            
            
        final Button money_maker = (Button) findViewById(R.id.money);

        money_maker.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    
                    
                    

                    money++;
                    money_count.setText(Integer.toString(money));
                }
			});
            
        final Button shop = (Button) findViewById(R.id.shop);
        
        back.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    levelselector.setVisibility(View.INVISIBLE);
                    back.setVisibility(View.INVISIBLE);
                    shoplayout.setVisibility(View.INVISIBLE);
                    money_count.setVisibility(View.INVISIBLE);
                    settingslayout.setVisibility(View.INVISIBLE);
                    Quit.setEnabled(true);
                    level_menu.setEnabled(true);
                    paintlevel.setEnabled(true);
                    play.setEnabled(true);
                    settings_button.setEnabled(true);
                    shop.setEnabled(true);
                    
                }
            });
        
            
            
        
        shop.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    money_count.setVisibility(View.VISIBLE);
                    shoplayout.setVisibility(View.VISIBLE);
                    back.setVisibility(View.VISIBLE);
                    money_maker.setY(13);
                    Quit.setEnabled(false);
                    level_menu.setEnabled(false);
                    paintlevel.setEnabled(false);
                    play.setEnabled(false);
                    settings_button.setEnabled(false);

                }
            });
            
            
        

        settings_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Quit.setEnabled(false);
                    level_menu.setEnabled(false);
                    paintlevel.setEnabled(false);
                    play.setEnabled(false);
                    shop.setEnabled(false);

                    back.setVisibility(View.VISIBLE);
                    settingslayout.setVisibility(View.VISIBLE);
                    
                    
                }
			});
            
            
    }
    
}
