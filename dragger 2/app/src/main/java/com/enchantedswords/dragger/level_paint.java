package com.enchantedswords.dragger;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.os.Handler;
import android.widget.Button;
import android.util.DisplayMetrics;
import android.graphics.drawable.BitmapDrawable;
import android.animation.ObjectAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Switch;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.widget.SeekBar;

import android.os.Environment;
import android.content.Context;
import android.graphics.Bitmap;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.FileInputStream;
import android.content.Intent;
import java.util.ArrayList;
import java.util.List;
import android.graphics.RectF;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.time.Year;
import java.io.FilenameFilter;
import android.widget.ScrollView;
import android.widget.TextView;
import java.nio.ByteBuffer;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import android.view.Window;


public class  level_paint extends Activity {

    private Bitmap tranparent;
	boolean isonpause = true;
	private Bitmap backgroundBitmap;
	private boolean isblack = true;
	private int squareSize = 10;
    String fileName = "bitmap1.png";
	private boolean isEraser = false;
	private float y;
    private float x;
    private float stary;
    private float starx;
	private boolean canmovestar = false;
	private int levelnames = 0;
    private ImageView star;
    
    
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.paint_level);
		
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		
		final Context context = this;
        
        
        
        
        
        
		
		Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tr);

        tranparent = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
	    backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
		
		final LinearLayout toolbar = findViewById(R.id.toolbar);
        final Button opentoolbar = findViewById(R.id.opentoolbar);

        opentoolbar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(toolbar.getVisibility() == View.VISIBLE) {
                        // Animate the rotation from 180 degrees to 0 degrees
                        ObjectAnimator rotation = ObjectAnimator.ofFloat(opentoolbar, "rotation", 180f, 0f);
                        rotation.setDuration(300);
                        rotation.start();

                        // Animate the visibility from visible to invisible
                        toolbar.animate().alpha(0f).setDuration(300).withEndAction(new Runnable() {
                                @Override
                                public void run() {
                                    toolbar.setVisibility(View.INVISIBLE);
                                }
                            }).start();
                    } else {
                        // Animate the rotation from 0 degrees to 180 degrees
                        ObjectAnimator rotation = ObjectAnimator.ofFloat(opentoolbar, "rotation", 0f, 180f);
                        rotation.setDuration(300);
                        rotation.start();

                        // Animate the visibility from invisible to visible
                        toolbar.setVisibility(View.VISIBLE);
                        toolbar.setAlpha(0f);
                        toolbar.animate().alpha(1f).setDuration(300).start();
                    }
                }
            });
        
        
		final Button paintlevel = (Button) findViewById(R.id.paint_level);
		paintlevel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
						
                    Intent intent = new Intent(level_paint.this, main_menu.class);
						startActivity(intent);
						finish();
						
				}
			});
            
            
            
            
            
			
		final Button green = (Button) findViewById(R.id.green);
		green.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    canmovestar=false;
					isblack = false;
                    isEraser = false;

				}
			});
			
		final Button black = (Button) findViewById(R.id.black);
		black.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
                    canmovestar=false;
					isblack = true;
                    isEraser = false;

				}
			});
			
			
	    
			
		final Button undo = (Button) findViewById(R.id.undo);
		undo.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					canmovestar=false;
					isEraser = true;
			
				}
			});
		
        final Button star_button = (Button) findViewById(R.id.pick_star);
        star_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    canmovestar=true;

                }
			});
			
		
		SeekBar seekbar = (SeekBar)findViewById(R.id.slider); 

		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { 

				@Override 
				public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) { 
					// Do something with the progress value 
					squareSize = progress;
				} 

				@Override 
				public void onStartTrackingTouch(SeekBar seekBar) { 
					// This method will automatically called when the user touches the SeekBar 
				} 

				@Override 
				public void onStopTrackingTouch(SeekBar seekBar) { 
					// This method will automatically called when the user stops touching the SeekBar 
				} 

			});
            
            
            
        final Button download = (Button) findViewById(R.id.download);
        download.setOnClickListener(new View.OnClickListener() {
        @Override
                public void onClick(View v) {
                // Increment the levelnames variable
            try {
                FileInputStream fis = openFileInput("levelnames.txt");
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                String value = reader.readLine();
                levelnames = Integer.parseInt(value);
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
                
                
                    levelnames++;

            try {
                FileOutputStream fos = openFileOutput("levelnames.txt", MODE_PRIVATE);
                fos.write(Integer.toString(levelnames).getBytes());
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            
                    
                    
                    // Get the external files directory
                    File externalFilesDir = getExternalFilesDir(null);

                    // Create a directory called "levels" inside the external files directory
                    File levelsDirectory = new File(externalFilesDir, "levels");
                    if (!levelsDirectory.exists()) {
                    levelsDirectory.mkdirs();
                        }

                 
                          
                          
                          
                          
                       
                   
                    // Create a file reference to the bitmap file with the current levelname as the filename
                    String filename = String.format("level_%04d.png", levelnames);
            
                    File bitmapFile = new File(levelsDirectory, filename);

                    try {
                    // Create a file output stream and compress the bitmap image to PNG format
                        FileOutputStream fos = new FileOutputStream(bitmapFile);
                        backgroundBitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        fos.close();
                        } catch (IOException e) {
                    e.printStackTrace();
                        }

                    test.writeToDownloadsDirectory(context,starx, stary);
                    }
                });
            
			
		final LinearLayout settingsLayout = findViewById(R.id.settings_layout);
		final Button Settings = (Button) findViewById(R.id.settings_button);
	    Settings.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Button load = (Button) findViewById(R.id.load);
					Button save = (Button) findViewById(R.id.save);
					Button download = (Button) findViewById(R.id.download);
					// Do something when the button is clicked
					if(load.getVisibility() == View.INVISIBLE)
					{
						isonpause = true;
						load.setVisibility(View.VISIBLE);
						save.setVisibility(View.INVISIBLE);
						download.setVisibility(View.VISIBLE);
						settingsLayout.setBackground(new ColorDrawable(Color.parseColor("#DD111111")));
						paintlevel.setVisibility(View.VISIBLE);
					}
					else
					{
						isonpause = false;
						load.setVisibility(View.INVISIBLE);
						save.setVisibility(View.INVISIBLE);
						download.setVisibility(View.INVISIBLE);
						paintlevel.setVisibility(View.INVISIBLE);
						settingsLayout.setBackground(new ColorDrawable(Color.parseColor("#00111111")));
					}
				}
			});
            
        
        Button loadButton = findViewById(R.id.load);
        loadButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    
                    
                }
            });
        
            
        
    }
	boolean firsttime = true;
	float oldX = 0, oldY = 0;
	
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		
		Paint eraser = new Paint();
		eraser.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		eraser.setAlpha(0);
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		star = findViewById(R.id.star);
        
        
		
		switch (event.getAction()) {
			case MotionEvent.ACTION_DOWN:
			case MotionEvent.ACTION_MOVE:
				// Get the x and y coordinates of the touch event
			//	float exp = -(((float) ((screenHeight*0.2) * Math.exp(event.getY()/screenHeight) + (screenHeight*3)))/50);
			    x = event.getX();
				float strap = (event.getY() -screenHeight/(float)1.7);
                y = (event.getY()-(screenHeight/30)); //((screenHeight - event.getY())/5))-((strap*strap*strap)/(screenHeight*2303));
                
                
                
                
                
               
				if (firsttime) {
					oldX = x;
					oldY = y;
					firsttime = false;
				}

                int colorargb = 0xff000000; // black with full opacity
                
                
				// Create a new Paint object for drawing
				Paint paint = new Paint();
				paint.setColor(isblack ? colorargb : Color.GREEN);

				// Create a new Canvas object for drawing on the background bitmap
				Canvas canvas = new Canvas(backgroundBitmap);

				// Draw the wave image if the eraser is active
                
                if(canmovestar)
                {
                    star.setVisibility(View.VISIBLE);
                    
                    star.setX(x-(star.getWidth()/2));
                    star.setY(y-(star.getHeight()/2));
                    
                    starx=x;
                    stary=y;
                    
                }
                
				else if (isEraser) {
					
                    
					canvas.drawBitmap(tranparent, null, new RectF(x - squareSize, y - squareSize, x + squareSize, y + squareSize), eraser);
				} else {
					// Calculate the dimensions of the circle
					int radius = squareSize;

					// Draw the circle on the canvas
					canvas.drawCircle(x, y, radius, paint);

					// Draw a line between the old and new positions
					paint.setStrokeWidth(radius * 2);
					canvas.drawLine(oldX, oldY, x, y, paint);
				}

				// Update the old position
				oldX = x;
				oldY = y;

				// Update the background view
				FrameLayout background = (FrameLayout) findViewById(R.id.paintin_board);
				background.setBackground(new BitmapDrawable(getResources(), backgroundBitmap));

				break;
			case MotionEvent.ACTION_UP:
				// Do something when the user lifts their finger
				firsttime = true;
				break;
		}
		
		return true;
	}
	
}

