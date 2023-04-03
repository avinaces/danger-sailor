package com.enchantedswords.dragger;
 
import android.animation.AnimatorInflater;
import android.animation.ObjectAnimator;
import android.animation.StateListAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import java.io.File;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;















public class MainActivity extends Activity implements View.OnTouchListener {
    private ImageView draggableObject;
	private ImageView rotateobject;
	private ImageView youlose;
	private ImageView youwin;
    private int originalX, originalY;
	private Bitmap backgroundBitmap;
	private Bitmap testinglevel;
	int level = 1;
    private static final String SHARED_PREFS_KEY = "my_shared_prefs";
    private static final String MONEY_KEY = "money";
    private static final String level_KEY = "level";
    private static final String SKIN_KEY = "skin";
    private int skin = 1;
	float oldx = originalX;
	float oldy = originalY;
	boolean show_hitbox = false;
	boolean show_green_hitbox = false;
	private ImageView loading;
	private Bitmap textureBitmap;
	private Bitmap ostatexture;
	boolean canpaint = false;
	private boolean cantelepory = false;
	private MediaPlayer music;
    private ImageView treeview;
    private int money = 0;
    private boolean ismoneycollected = false;
    boolean deletetrees = false;
    int levelcount = 29;
    int fakelevelcount = 28;
    float staroriginaly;
    float staroriginalx;
    
    
    

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		// Load the background image and scale it to fit the screen
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		
		rotateobject = (ImageView) findViewById(R.id.rotation_object);
		
        

		final Context context = this;
        
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE);
        money = prefs.getInt(MONEY_KEY, 0); // Default value is 0 if key not found
        skin = prefs.getInt(SKIN_KEY, 1);
        
        // Update the UI with the current value of money
        TextView money_count = findViewById(R.id.moneyLabel);
        
        money_count.setText(Integer.toString(money));
        

        
        level = prefs.getInt(level_KEY, 0); // Default value is 0 if key not found
        
        isonpause = true;
            
        ImageView star = findViewById(R.id.star);
        
        staroriginalx = star.getX();
        staroriginaly = star.getY();
        
        
            // Do something with the level value here
       
            
            if(skin == 2)
            {
                rotateobject.setBackground(null);
                
                rotateobject.setBackgroundResource(R.drawable.boat2);
            }
            else if(skin == 3)
            {
                rotateobject.setBackground(null);

                rotateobject.setBackgroundResource(R.drawable.boat);
            }
            else
            {
                rotateobject.setBackground(null);
                rotateobject.setBackgroundResource(R.drawable.dragger);
            }
            
            
            
        //Intent intent = new Intent(MainActivity.this, backdoor.class);
        //startActivity(intent);
    
		
		
		
		
		//levels
		Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0);
		
		backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
		
		testinglevel = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
		
        
        final FrameLayout liquid = (FrameLayout) findViewById(R.id.luquid);

        
        final FrameLayout allsettings = (FrameLayout) findViewById(R.id.all_settings);
		
		final FrameLayout background = (FrameLayout) findViewById(R.id.background);
		background.setBackground(new BitmapDrawable(getResources(), backgroundBitmap));
		
        
        
        
        
		
		final LinearLayout settingsLayout = findViewById(R.id.settings_layout);
		
		final  FrameLayout burbulilayout = (FrameLayout) findViewById(R.id.burbulilayout);
        
        final Random random = new Random();
        
		
		final Button next = (Button) findViewById(R.id.next);
		final Handler handler = new Handler();

		next.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Show the loading view
					loading.setVisibility(View.VISIBLE);
					
					// Get a reference to the loading view
					final ImageView loading = (ImageView) findViewById(R.id.loading);

                    
                    
// Create a rotation animation
					ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, -360f);
					rotation.setDuration(1000);
					rotation.setRepeatCount(ObjectAnimator.INFINITE);
					rotation.setInterpolator(new AccelerateDecelerateInterpolator());

// Start the animation
					rotation.start();
					

					next.setVisibility(View.INVISIBLE);
					
					

                   
                    
                    if (level >= levelcount) {

                        int starlevel = level - fakelevelcount;



                        float stary = test.readFromDownloadsDirectory(context,"y_cord" + (starlevel == 1 ? "" : starlevel));

                        ImageView star = findViewById(R.id.star);

                        if(stary != 12345.0f) {
                            star.setY(stary-(star.getHeight()/2));
                            star.setVisibility(View.VISIBLE);
                            ismoneycollected=false;
                        } else {

                        }

                        float starx = test.readFromDownloadsDirectory(context, "x_cord" + (starlevel == 1 ? "" : starlevel));

                        if(starx != 12345.0f) {
                            star.setX(starx-(star.getWidth()/2));
                            star.setVisibility(View.VISIBLE);
                            ismoneycollected=false;
                        } else {

                        }

                    }

                else
                {
                    ImageView star = findViewById(R.id.star);
                    
                    if(level == 2)
                    {
                        star.setY(screenHeight/2);
                        star.setX(((screenWidth/2) + screenWidth/4));
                    }else
                    {
                        star.setY((screenHeight/2)-(star.getHeight()/2));
                        star.setX((screenWidth/2)-(star.getWidth()/2));
                        
                    }
                    
                    if(level == 6)
                    {
                        star.setY((screenHeight/2)-(star.getHeight()/2));
                        star.setX((screenWidth/2)-(star.getHeight()/4.5f));
                        
                    }
                    
                    star.setVisibility(View.VISIBLE);
                    ismoneycollected=false;
                    
                }
                    
                    
                    
                    Bitmap originalBitmap;
                    if (level <= fakelevelcount) {
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
                    } else {
                        // Get the external files directory
                        File externalFilesDir = getExternalFilesDir(null);

// Create a directory reference to the "levels" directory inside the external files directory
                        File levelsDirectory = new File(externalFilesDir, "levels");

                        if (!levelsDirectory.exists()) {
                            Log.e("Error", "The 'levels' directory does not exist");
                            youwin.setVisibility(View.INVISIBLE);
                            loading.setVisibility(View.INVISIBLE);
							isonpause = false;
                            return;
                        }

// Get a list of all files in the levelsDirectory
                        File[] levelFiles = levelsDirectory.listFiles();

// Sort the files in ascending order based on the last four digits of their names
                        Arrays.sort(levelFiles, new Comparator<File>() {
                                @Override
                                public int compare(File file1, File file2) {
                                    String fileName1 = file1.getName();
                                    String fileName2 = file2.getName();

                                    // Extract the last four digits of each file name
                                    int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                                    int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                                    // Compare the last four digits of each file name
                                    return Integer.compare(fileNumber1, fileNumber2);
                                }
                            });
                        

                        if (levelFiles == null || levelFiles.length == 0 || level - levelcount >= levelFiles.length) {
                            Log.e("Error", "No level file found for level " + level);
                            youwin.setVisibility(View.INVISIBLE);
                            loading.setVisibility(View.INVISIBLE);
							isonpause = false;
                            return;
                        }

                        originalBitmap = BitmapFactory.decodeFile(levelFiles[level - levelcount].getPath());
                    }
                    
                    
                    
                    
                    
					
					
					// Process the bitmap on a separate thread
					new AsyncTask<Bitmap, Void, Void>() {
						@Override
						
						
						protected Void doInBackground(Bitmap... bitmaps) {
							Bitmap originalBitmap = bitmaps[0];
							backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
							testinglevel = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
							backgroundtexturechanger();
                            
							return null;
						}

						@Override
						protected void onPostExecute(Void aVoid) {
							super.onPostExecute(aVoid);

							// Set the position of the draggable object to its original position
							draggableObject.setX(originalX);
							draggableObject.setY(originalY);
							rotateobject.setX(originalX);
							rotateobject.setY(originalY);
							rotateobject.setRotation(0);

                            
                            burbulilayout.removeAllViews();
                            
                            int minDistance = screenHeight / 15;
                            List<Pair<Integer, Integer>> spawnedTreePositions = new ArrayList<>();

                            for (int i = 0; i < 100; i++) {
                                int randomYNumber = random.nextInt(screenHeight);
                                int randomXNumber = random.nextInt(screenWidth);
                                boolean canSpawn = true;

                                for (Pair<Integer, Integer> position : spawnedTreePositions) {
                                    int xDistance = Math.abs(randomXNumber - position.first);
                                    int yDistance = Math.abs(randomYNumber - position.second);

                                    if (xDistance < minDistance && yDistance < minDistance) {
                                        canSpawn = false;
                                        break;
                                    }
                                }

                                if (canSpawn) {
                                    int pixelColor = testinglevel.getPixel(randomXNumber, randomYNumber);
                                    if (Color.alpha(pixelColor) == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
                                        treeview = new ImageView(context);
                                         

                                        if (level >= 20) { // spawn swamp trees
                                            if (liquid.getBackground().getConstantState() != getResources().getDrawable(R.drawable.swamp_water).getConstantState()) {
                                                liquid.setBackgroundResource(R.drawable.swamp_water);
                                            }

                                            int randomtree = random.nextInt(8);
                                            int treesize = screenHeight / 8;

                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));

                                            // Set the image resource and randomly mirror it
                                            treeview.setImageResource(R.drawable.swamp_pine1 + randomtree);
                                            if (random.nextBoolean()) {
                                                treeview.setScaleX(-1);
                                            }

                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        }
                                        else if (level >= 10) { // spawn pine trees
                                            int randomtree = random.nextInt(10);
                                            int treesize = screenHeight / 4;
                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                            treeview.setImageResource(R.drawable.pine1 + randomtree);
                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        } else { // spawn normal trees
                                            int randomtree = random.nextInt(8);
                                            int treesize = screenHeight / 8;
                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                            treeview.setImageResource(R.drawable.tree1 + randomtree);
                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        }

                                        treeview.setZ(randomYNumber);
                                        burbulilayout.addView(treeview);
                                        spawnedTreePositions.add(new Pair<>(randomXNumber, randomYNumber));
                                    }
                                }
                                
                            }
                            
                            
                            
                            
							level++;
							
                            
                            
                            
                            
							// Hide the "you win" image and the "next" button
							youwin.setVisibility(View.INVISIBLE);
							
							isonpause = false;

							// Check if the user has completed all levels
							
							
							// Hide the loading view after a short delay
							handler.postDelayed(new Runnable() {
									@Override
									public void run() {
										loading.setVisibility(View.INVISIBLE);
									}
								}, 10); // 1000 milliseconds = 1 second
						}
					}.execute(originalBitmap);
				}
			});
		
        
            
            
        final Button skipthislevel = (Button) findViewById(R.id.skipthislevel);
        
        
        
       music = MediaPlayer.create(getApplicationContext(), R.raw.music);
        
       
       
        music.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    
                }
            });

// Start the music playback
         
        music.start();
        
       
        
        final Handler skipper = new Handler();

        skipthislevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Show the loading view
                    loading.setVisibility(View.VISIBLE);

                    // Get a reference to the loading view
                    final ImageView loading = (ImageView) findViewById(R.id.loading);



// Create a rotation animation
                    ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, -360f);
                    rotation.setDuration(1000);
                    rotation.setRepeatCount(ObjectAnimator.INFINITE);
                    rotation.setInterpolator(new AccelerateDecelerateInterpolator());

// Start the animation
                    rotation.start();

                    if (level >= 29) {

                        int starlevel = level - 28;



                        float stary = test.readFromDownloadsDirectory(context,"y_cord" + (starlevel == 1 ? "" : starlevel));

                        ImageView image2 = findViewById(R.id.star);

                        if(stary != 12345.0f) {
                            image2.setY(stary-(image2.getHeight()/2));
                            image2.setVisibility(View.VISIBLE);
                            ismoneycollected=false;
                        } else {

                        }

                        float starx = test.readFromDownloadsDirectory(context, "x_cord" + (starlevel == 1 ? "" : starlevel));

                        if(starx != 12345.0f) {
                            image2.setX(starx-(image2.getWidth()/2));
                            image2.setVisibility(View.VISIBLE);
                            ismoneycollected=false;
                        } else {

                        }

                    }

                    

                    next.setVisibility(View.INVISIBLE);



                    // Load the level 2 image (this may take some time)


                    Bitmap originalBitmap;
                    if (level <= 28) {
                        originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
                    } else {
                        // Get the external files directory
                        File externalFilesDir = getExternalFilesDir(null);

// Create a directory reference to the "levels" directory inside the external files directory
                        File levelsDirectory = new File(externalFilesDir, "levels");

                        if (!levelsDirectory.exists()) {
                            Log.e("Error", "The 'levels' directory does not exist");
                            return;
                        }

// Get a list of all files in the levelsDirectory
                        File[] levelFiles = levelsDirectory.listFiles();

// Sort the files in ascending order based on the last four digits of their names
                        Arrays.sort(levelFiles, new Comparator<File>() {
                                @Override
                                public int compare(File file1, File file2) {
                                    String fileName1 = file1.getName();
                                    String fileName2 = file2.getName();

                                    // Extract the last four digits of each file name
                                    int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                                    int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                                    // Compare the last four digits of each file name
                                    return Integer.compare(fileNumber1, fileNumber2);
                                }
                            });


                        if (levelFiles == null || levelFiles.length == 0 || level - 29 >= levelFiles.length) {
                            Log.e("Error", "No level file found for level " + level);
                            return;
                        }

                        originalBitmap = BitmapFactory.decodeFile(levelFiles[level - 29].getPath());
                    }
                    
                    
                    
                    




                    // Process the bitmap on a separate thread
                    new AsyncTask<Bitmap, Void, Void>() {
                        @Override


                        protected Void doInBackground(Bitmap... bitmaps) {
                            Bitmap originalBitmap = bitmaps[0];
                            backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                            testinglevel = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                            backgroundtexturechanger();
                            //treemaker();
                            return null;
                        }

                        @Override
                        protected void onPostExecute(Void aVoid) {
                            super.onPostExecute(aVoid);

                            // Set the position of the draggable object to its original position
                            draggableObject.setX(originalX);
                            draggableObject.setY(originalY);
                            rotateobject.setX(originalX);
                            rotateobject.setY(originalY);
                            rotateobject.setRotation(0);


                            burbulilayout.removeAllViews();

                            int minDistance = screenHeight / 15;
                            List<Pair<Integer, Integer>> spawnedTreePositions = new ArrayList<>();

                            for (int i = 0; i < 100; i++) {
                                int randomYNumber = random.nextInt(screenHeight);
                                int randomXNumber = random.nextInt(screenWidth);
                                boolean canSpawn = true;

                                for (Pair<Integer, Integer> position : spawnedTreePositions) {
                                    int xDistance = Math.abs(randomXNumber - position.first);
                                    int yDistance = Math.abs(randomYNumber - position.second);

                                    if (xDistance < minDistance && yDistance < minDistance) {
                                        canSpawn = false;
                                        break;
                                    }
                                }

                                if (canSpawn) {
                                    int pixelColor = testinglevel.getPixel(randomXNumber, randomYNumber);
                                    if (Color.alpha(pixelColor) == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
                                        treeview = new ImageView(context);


                                        if (level >= 20) { // spawn swamp trees

                                            if (liquid.getBackground().getConstantState() != getResources().getDrawable(R.drawable.swamp_water).getConstantState()) {
                                                liquid.setBackgroundResource(R.drawable.swamp_water);
                                            }


                                            int randomtree = random.nextInt(8);
                                            int treesize = screenHeight / 8;

                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                            treeview.setImageResource(R.drawable.swamp_pine1 + randomtree);
                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        } else if (level >= 10) { // spawn pine trees
                                            int randomtree = random.nextInt(10);
                                            int treesize = screenHeight / 4;
                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                            treeview.setImageResource(R.drawable.pine1 + randomtree);
                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        } else { // spawn normal trees
                                            int randomtree = random.nextInt(8);
                                            int treesize = screenHeight / 8;
                                            treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                            treeview.setImageResource(R.drawable.tree1 + randomtree);
                                            treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                            treeview.setX(randomXNumber - (treesize / 2));
                                        }

                                        treeview.setZ(randomYNumber);
                                        burbulilayout.addView(treeview);
                                        spawnedTreePositions.add(new Pair<>(randomXNumber, randomYNumber));
                                    }
                                }

                            }




                            level++;





                            // Hide the "you win" image and the "next" button
                            youwin.setVisibility(View.INVISIBLE);

                            isonpause = false;

                            // Check if the user has completed all levels


                            // Hide the loading view after a short delay
                            skipper.postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        loading.setVisibility(View.INVISIBLE);
                                    }
                                }, 10); // 1000 milliseconds = 1 second
                        }
                    }.execute(originalBitmap);
				}
            });
		
            
            
		final Switch canteleportbutton = (Switch) findViewById(R.id.teleport);

		/*canteleportbutton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					if(cantelepory)
					{
						cantelepory = false;
						canteleportbutton.setText("can teleport | off");
					}
					else
					{
						cantelepory = true;
						canteleportbutton.setText("can teleport | on");
					}
					

				}
			});*/
            
        canteleportbutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    // do something, the isChecked will be
                    
                    if(!isChecked)
                    {
                        cantelepory = false;
                        canteleportbutton.setText("can teleport | off");
                    }
                    else
                    {
                        cantelepory = true;
                        canteleportbutton.setText("can teleport | on");
                    }
					
                    
                    // true if the switch is in the On position
                }
            });
            
		final Button paintlevel = (Button) findViewById(R.id.paint_level);
		
		paintlevel.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {

					if(canpaint)
					{
						canpaint = false;
					}
					else
					{
						Intent intent = new Intent(MainActivity.this, main_menu.class);
                        startActivity(intent);
						canpaint = true;
						finish();
					}



				}
			});
		final Button Settings = (Button) findViewById(R.id.settings_button);
	    Settings.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Button swich = (Button) findViewById(R.id.swich_test);
					Button green_hitbox = (Button) findViewById(R.id.green_hitbox);
					// Do something when the button is clicked
                    ScrollView levelselector = findViewById(R.id.levelselector);
                    levelselector.setVisibility(View.INVISIBLE);
					if(swich.getVisibility() == View.INVISIBLE)
					{
					isonpause = true;
					swich.setVisibility(View.VISIBLE);
					green_hitbox.setVisibility(View.VISIBLE);
					allsettings.setBackground(new ColorDrawable(Color.parseColor("#DD111111")));
					paintlevel.setVisibility(View.VISIBLE);
                    allsettings.setVisibility(View.VISIBLE);
					}
					else
					{
						isonpause = false;
						swich.setVisibility(View.INVISIBLE);
						green_hitbox.setVisibility(View.INVISIBLE);
						paintlevel.setVisibility(View.INVISIBLE);
						allsettings.setBackground(new ColorDrawable(Color.parseColor("#00111111")));
                        allsettings.setVisibility(View.INVISIBLE);
					}
				}
			});
			
			

		final Button swich = (Button) findViewById(R.id.swich_test);
	    swich.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Do something when the button is clicked
                    if(show_hitbox)
					{
						show_hitbox = false;
						swich.setText("show hitbox|off");
						Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level - 1);
						backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
						FrameLayout background = (FrameLayout) findViewById(R.id.background);
						background.setBackground(new BitmapDrawable(getResources(), backgroundBitmap));
						
					}
					
					else
					{
						show_hitbox = true;
						swich.setText("show hitbox|on");
					}
					
				}
			});
			
			
			
			
		final Button green_hitbox = (Button) findViewById(R.id.green_hitbox);
	    green_hitbox.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Do something when the button is clicked
                    if(show_green_hitbox)
					{
						show_green_hitbox = false;
						green_hitbox.setText("show real object|off");
						draggableObject.setBackground(new ColorDrawable(Color.parseColor("#0000FF00")));
					}

					else
					{
						show_green_hitbox = true;
						green_hitbox.setText("show real object|on");
						draggableObject.setBackground(new ColorDrawable(Color.parseColor("#9900FF00")));
					}

				}
			});
			
		
		
		final Button myButton = (Button) findViewById(R.id.my_button);
	    myButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					// Do something when the button is clicked
					draggableObject.setX(originalX);
					draggableObject.setY(originalY);
					rotateobject.setX(originalX);
					rotateobject.setY(originalY);
					rotateobject.setRotation(0);
					youlose.setVisibility(View.INVISIBLE);
					myButton.setVisibility(View.INVISIBLE);
					isonpause = false;
				}
			});
			
			
		
		
			
            
    
        final Button skipalllevels = findViewById(R.id.skiplevels);
        final ScrollView levelselector = findViewById(R.id.levelselector);

        skipalllevels.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        
                        allsettings.setVisibility(View.INVISIBLE);
                        // Set the visibility of the scroll view to visible
                        if (levelselector.getVisibility() == View.INVISIBLE) {
                            levelselector.setVisibility(View.VISIBLE);

                            // Create a new LinearLayout to hold the buttons
                            LinearLayout buttonLayout = new LinearLayout(MainActivity.this);
                            buttonLayout.setLayoutParams(new LinearLayout.LayoutParams(
                                                             LinearLayout.LayoutParams.MATCH_PARENT,
                                                             LinearLayout.LayoutParams.WRAP_CONTENT)); // set layout height to wrap content
                            buttonLayout.setOrientation(LinearLayout.VERTICAL);
                            levelselector.addView(buttonLayout); // add the LinearLayout to the ScrollView

                            // Generate level buttons
                            Field[] drawables = R.drawable.class.getFields();
                            for (final Field drawable : drawables) {
                                if (drawable.getName().startsWith("lvl")) {
                                    Button button = new Button(MainActivity.this);
                                    button.setText("Level " + drawable.getName().substring(3));
                                    button.setBackgroundResource(R.drawable.level_button);
                                    // Create a StateListAnimator object
                                    StateListAnimator stateListAnimator = AnimatorInflater.loadStateListAnimator(MainActivity.this, R.anim.button_anim);


// Set the StateListAnimator object to the button
                                    button.setStateListAnimator(stateListAnimator);
                                    final int levelNumber = Integer.parseInt(drawable.getName().substring(3)); // extract level number from drawable name
                                    button.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {

                                                if(levelNumber != 0)
                                                {
                                                    level = levelNumber-1; // set level variable to the selected level number
                                                    // do something with the selected level drawable
                                                }
                                                else
                                                {
                                                    level = levelNumber;
                                                }
                                                loading.setVisibility(View.VISIBLE);

                                                // Get a reference to the loading view
                                                final ImageView loading = (ImageView) findViewById(R.id.loading);



// Create a rotation animation
                                                ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, -360f);
                                                rotation.setDuration(1000);
                                                rotation.setRepeatCount(ObjectAnimator.INFINITE);
                                                rotation.setInterpolator(new AccelerateDecelerateInterpolator());

// Start the animation
                                                rotation.start();


                                                next.setVisibility(View.INVISIBLE);





                                                if (level >= 29) {

                                                    int starlevel = level - 28;



                                                    float stary = test.readFromDownloadsDirectory(context,"y_cord" + (starlevel == 1 ? "" : starlevel));

                                                    ImageView image2 = findViewById(R.id.star);

                                                    if(stary != 12345.0f) {
                                                        image2.setY(stary-(image2.getHeight()/2));
                                                        image2.setVisibility(View.VISIBLE);
                                                        ismoneycollected=false;
                                                    } else {

                                                    }

                                                    float starx = test.readFromDownloadsDirectory(context, "x_cord" + (starlevel == 1 ? "" : starlevel));

                                                    if(starx != 12345.0f) {
                                                        image2.setX(starx-(image2.getWidth()/2));
                                                        image2.setVisibility(View.VISIBLE);
                                                        ismoneycollected=false;
                                                    } else {

                                                    }

                                                }





                                                Bitmap originalBitmap;
                                                if (level <= 28) {
                                                    originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
                                                } else {
                                                    // Get the external files directory
                                                    File externalFilesDir = getExternalFilesDir(null);

// Create a directory reference to the "levels" directory inside the external files directory
                                                    File levelsDirectory = new File(externalFilesDir, "levels");

                                                    if (!levelsDirectory.exists()) {
                                                        Log.e("Error", "The 'levels' directory does not exist");
                                                        return;
                                                    }

// Get a list of all files in the levelsDirectory
                                                    File[] levelFiles = levelsDirectory.listFiles();

// Sort the files in ascending order based on the last four digits of their names
                                                    Arrays.sort(levelFiles, new Comparator<File>() {
                                                            @Override
                                                            public int compare(File file1, File file2) {
                                                                String fileName1 = file1.getName();
                                                                String fileName2 = file2.getName();

                                                                // Extract the last four digits of each file name
                                                                int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                                                                int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                                                                // Compare the last four digits of each file name
                                                                return Integer.compare(fileNumber1, fileNumber2);
                                                            }
                                                        });


                                                    if (levelFiles == null || levelFiles.length == 0 || level - 29 >= levelFiles.length) {
                                                        Log.e("Error", "No level file found for level " + level);
                                                        return;
                                                    }

                                                    originalBitmap = BitmapFactory.decodeFile(levelFiles[level - 29].getPath());
                                                }







                                                // Process the bitmap on a separate thread
                                                new AsyncTask<Bitmap, Void, Void>() {
                                                    @Override


                                                    protected Void doInBackground(Bitmap... bitmaps) {
                                                        Bitmap originalBitmap = bitmaps[0];
                                                        backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                                                        testinglevel = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                                                        backgroundtexturechanger();

                                                        return null;
                                                    }

                                                    @Override
                                                    protected void onPostExecute(Void aVoid) {
                                                        super.onPostExecute(aVoid);

                                                        // Set the position of the draggable object to its original position
                                                        draggableObject.setX(originalX);
                                                        draggableObject.setY(originalY);
                                                        rotateobject.setX(originalX);
                                                        rotateobject.setY(originalY);
                                                        rotateobject.setRotation(0);


                                                        burbulilayout.removeAllViews();

                                                        int minDistance = screenHeight / 15;
                                                        List<Pair<Integer, Integer>> spawnedTreePositions = new ArrayList<>();

                                                        for (int i = 0; i < 100; i++) {
                                                            int randomYNumber = random.nextInt(screenHeight);
                                                            int randomXNumber = random.nextInt(screenWidth);
                                                            boolean canSpawn = true;

                                                            for (Pair<Integer, Integer> position : spawnedTreePositions) {
                                                                int xDistance = Math.abs(randomXNumber - position.first);
                                                                int yDistance = Math.abs(randomYNumber - position.second);

                                                                if (xDistance < minDistance && yDistance < minDistance) {
                                                                    canSpawn = false;
                                                                    break;
                                                                }
                                                            }

                                                            if (canSpawn) {
                                                                int pixelColor = testinglevel.getPixel(randomXNumber, randomYNumber);
                                                                if (Color.alpha(pixelColor) == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
                                                                    treeview = new ImageView(context);


                                                                    if (level >= 20) { // spawn swamp trees
                                                                        if (liquid.getBackground().getConstantState() != getResources().getDrawable(R.drawable.swamp_water).getConstantState()) {
                                                                            liquid.setBackgroundResource(R.drawable.swamp_water);
                                                                        }

                                                                        int randomtree = random.nextInt(8);
                                                                        int treesize = screenHeight / 8;

                                                                        treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));

                                                                        // Set the image resource and randomly mirror it
                                                                        treeview.setImageResource(R.drawable.swamp_pine1 + randomtree);
                                                                        if (random.nextBoolean()) {
                                                                            treeview.setScaleX(-1);
                                                                        }

                                                                        treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                                                        treeview.setX(randomXNumber - (treesize / 2));
                                                                    }
                                                                    else if (level >= 10) { // spawn pine trees
                                                                        int randomtree = random.nextInt(10);
                                                                        int treesize = screenHeight / 4;
                                                                        treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                                                        treeview.setImageResource(R.drawable.pine1 + randomtree);
                                                                        treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                                                        treeview.setX(randomXNumber - (treesize / 2));
                                                                    } else { // spawn normal trees
                                                                        int randomtree = random.nextInt(8);
                                                                        int treesize = screenHeight / 8;
                                                                        treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                                                        treeview.setImageResource(R.drawable.tree1 + randomtree);
                                                                        treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                                                        treeview.setX(randomXNumber - (treesize / 2));
                                                                    }

                                                                    treeview.setZ(randomYNumber);
                                                                    burbulilayout.addView(treeview);
                                                                    spawnedTreePositions.add(new Pair<>(randomXNumber, randomYNumber));
                                                                }
                                                            }

                                                        }




                                                        level++;





                                                        // Hide the "you win" image and the "next" button
                                                        youwin.setVisibility(View.INVISIBLE);

                                                        isonpause = false;

                                                        // Check if the user has completed all levels


                                                        // Hide the loading view after a short delay
                                                        handler.postDelayed(new Runnable() {
                                                                @Override
                                                                public void run() {
                                                                    loading.setVisibility(View.INVISIBLE);
                                                                }
                                                            }, 10); // 1000 milliseconds = 1 second
                                                    }
                                                }.execute(originalBitmap);

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
                        test.log_error(e, context);
                        e.printStackTrace();
                    }
                }
            });
        
        


        
        
        
        
            
            
        final Button realoadthislevel = findViewById(R.id.reaload);
        realoadthislevel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) 
                
                {
                    reloadlevel(context);
                }
                
			});
		
        
		
		draggableObject = (ImageView) findViewById(R.id.draggable_object);
		
		youlose = (ImageView) findViewById(R.id.you_lose);
		youwin = (ImageView) findViewById(R.id.you_win);
		loading = (ImageView) findViewById(R.id.loading);
		
		draggableObject.setOnTouchListener(this);

		int[] coordinates = new int[2];
		draggableObject.getLocationInWindow(coordinates);
		int x = coordinates[0] + draggableObject.getWidth();
		int y = coordinates[1] + draggableObject.getHeight();
		originalX = x + screenWidth / 2 - 100;
		originalY = y + screenHeight - (screenHeight / 5);
        
        reloadlevel(context);
        
        isonpause=false;
        
	}
    
    
    
    
    private void reloadlevel(final Context context)
    {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
        
        final Handler handler = new Handler();
        
        final Random random = new Random();
        
		final  FrameLayout burbulilayout = (FrameLayout) findViewById(R.id.burbulilayout);
        
        final FrameLayout liquid = (FrameLayout) findViewById(R.id.luquid);
        // Show the loading view

        level--;

        loading.setVisibility(View.VISIBLE);

        // Get a reference to the loading view
        final ImageView loading = (ImageView) findViewById(R.id.loading);



// Create a rotation animation
        ObjectAnimator rotation = ObjectAnimator.ofFloat(loading, "rotation", 0f, -360f);
        rotation.setDuration(1000);
        rotation.setRepeatCount(ObjectAnimator.INFINITE);
        rotation.setInterpolator(new AccelerateDecelerateInterpolator());

// Start the animation
        rotation.start();


        



        if (level >= 29) {

            int starlevel = level - 28;



            float stary = test.readFromDownloadsDirectory(context,"y_cord" + (starlevel == 1 ? "" : starlevel));

            ImageView image2 = findViewById(R.id.star);

            if(stary != 12345.0f) {
                image2.setY(stary-(image2.getHeight()/2));
                image2.setVisibility(View.VISIBLE);
                ismoneycollected=false;
            } else {

            }

            float starx = test.readFromDownloadsDirectory(context, "x_cord" + (starlevel == 1 ? "" : starlevel));

            if(starx != 12345.0f) {
                image2.setX(starx-(image2.getWidth()/2));
                image2.setVisibility(View.VISIBLE);
                ismoneycollected=false;
            } else {

            }

        }




        Bitmap originalBitmap;
        if (level <= 28) {
            originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
        } else {
            // Get the external files directory
            File externalFilesDir = getExternalFilesDir(null);

// Create a directory reference to the "levels" directory inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "levels");

            if (!levelsDirectory.exists()) {
                Log.e("Error", "The 'levels' directory does not exist");
                return;
            }

// Get a list of all files in the levelsDirectory
            File[] levelFiles = levelsDirectory.listFiles();

// Sort the files in ascending order based on the last four digits of their names
            Arrays.sort(levelFiles, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        String fileName1 = file1.getName();
                        String fileName2 = file2.getName();

                        // Extract the last four digits of each file name
                        int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                        int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                        // Compare the last four digits of each file name
                        return Integer.compare(fileNumber1, fileNumber2);
                    }
                });


            if (levelFiles == null || levelFiles.length == 0 || level - 29 >= levelFiles.length) {
                Log.e("Error", "No level file found for level " + level);
                return;
            }

            originalBitmap = BitmapFactory.decodeFile(levelFiles[level - 29].getPath());
        }










        // Process the bitmap on a separate thread
        new AsyncTask<Bitmap, Void, Void>() {
            @Override


            protected Void doInBackground(Bitmap... bitmaps) {
                Bitmap originalBitmap = bitmaps[0];
                backgroundBitmap = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                testinglevel = Bitmap.createScaledBitmap(originalBitmap, screenWidth, screenHeight, true);
                backgroundtexturechanger();

                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                super.onPostExecute(aVoid);

                // Set the position of the draggable object to its original position
                draggableObject.setX(originalX);
                draggableObject.setY(originalY);
                rotateobject.setX(originalX);
                rotateobject.setY(originalY);
                rotateobject.setRotation(0);


                burbulilayout.removeAllViews();

                int minDistance = screenHeight / 15;
                List<Pair<Integer, Integer>> spawnedTreePositions = new ArrayList<>();

                for (int i = 0; i < 100; i++) {
                    int randomYNumber = random.nextInt(screenHeight);
                    int randomXNumber = random.nextInt(screenWidth);
                    boolean canSpawn = true;

                    for (Pair<Integer, Integer> position : spawnedTreePositions) {
                        int xDistance = Math.abs(randomXNumber - position.first);
                        int yDistance = Math.abs(randomYNumber - position.second);

                        if (xDistance < minDistance && yDistance < minDistance) {
                            canSpawn = false;
                            break;
                        }
                    }

                    if (canSpawn) {
                        int pixelColor = testinglevel.getPixel(randomXNumber, randomYNumber);
                        if (Color.alpha(pixelColor) == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
                            treeview = new ImageView(context);


                            if (level >= 20) { // spawn swamp trees
                                if (liquid.getBackground().getConstantState() != getResources().getDrawable(R.drawable.swamp_water).getConstantState()) {
                                    liquid.setBackgroundResource(R.drawable.swamp_water);
                                }

                                int randomtree = random.nextInt(8);
                                int treesize = screenHeight / 8;

                                treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));

                                // Set the image resource and randomly mirror it
                                treeview.setImageResource(R.drawable.swamp_pine1 + randomtree);
                                if (random.nextBoolean()) {
                                    treeview.setScaleX(-1);
                                }

                                treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                treeview.setX(randomXNumber - (treesize / 2));
                            }
                            else if (level >= 10) { // spawn pine trees
                                int randomtree = random.nextInt(10);
                                int treesize = screenHeight / 4;
                                treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                treeview.setImageResource(R.drawable.pine1 + randomtree);
                                treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15));
                                treeview.setX(randomXNumber - (treesize / 2));
                            } 


                            else { // spawn normal trees
                                int randomtree = random.nextInt(8);
                                int treesize = screenHeight / 8;
                                treeview.setLayoutParams(new LinearLayout.LayoutParams(treesize, treesize));
                                treeview.setImageResource(R.drawable.tree1 + randomtree);
                                treeview.setY(((randomYNumber - (treesize)) - randomYNumber / 15) + (treesize / 8));
                                treeview.setX(randomXNumber - (treesize / 2));
                            }

                            treeview.setZ(randomYNumber);
                            burbulilayout.addView(treeview);
                            spawnedTreePositions.add(new Pair<>(randomXNumber, randomYNumber));
                        }
                    }

                }




                level++;





                // Hide the "you win" image and the "next" button
                youwin.setVisibility(View.INVISIBLE);

                isonpause = false;

                // Check if the user has completed all levels


                // Hide the loading view after a short delay
                handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            loading.setVisibility(View.INVISIBLE);
                        }
                    }, 10); // 1000 milliseconds = 1 second
            }
        }.execute(originalBitmap);
        
    }
    
    
    @Override
    protected void onPause() {
        super.onPause();
        music.stop();
        music.release();
        
    }

  
    
   
	
	private boolean isOnGreenPart() {
		 int[] objectCoordinates = new int[2];
		draggableObject.getLocationInWindow(objectCoordinates);
		
		

		 double left = objectCoordinates[0];
		 double top = objectCoordinates[1] - (draggableObject.getHeight() * 1.1);
		 double right = left + draggableObject.getWidth();
	     double bottom = top + draggableObject.getHeight();

		// Check the color of all pixels that the draggable object covers
		for (double x = left; x < right; x++) {
			for (double y = top; y < bottom; y++) {
				int pixelColor = testinglevel.getPixel((int)x,(int) y);
				
				if (Color.red(pixelColor) == 0 && Color.green(pixelColor) == 255 && Color.blue(pixelColor) == 0) {
					// The pixel is green, so the object is on the black part of the background image
					return true;
				}
			}
		}

		// The object is not on the green part of the background image
		return false;
	}
	
    
    private void backgroundtexturechanger() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int screenWidth = displayMetrics.widthPixels;
        final int screenHeight = displayMetrics.heightPixels;
        FrameLayout background = (FrameLayout) findViewById(R.id.background);

        Bitmap levelBitmap;
        if (level <= 28) {
            levelBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
        } else {
            // Get the external files directory
            File externalFilesDir = getExternalFilesDir(null);

            // Create a directory reference to the "levels" directory inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "levels");

            if (!levelsDirectory.exists()) {
                Log.e("Error", "The 'levels' directory does not exist");
                return;
            }

            // Get a list of all files in the levelsDirectory
            File[] levelFiles = levelsDirectory.listFiles();

            // Sort the files in ascending order based on the last four digits of their names
            Arrays.sort(levelFiles, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        String fileName1 = file1.getName();
                        String fileName2 = file2.getName();

                        // Extract the last four digits of each file name
                        int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                        int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                        // Compare the last four digits of each file name
                        return Integer.compare(fileNumber1, fileNumber2);
                    }
                });

            if (levelFiles == null || levelFiles.length == 0 || level - 29 >= levelFiles.length) {
                Log.e("Error", "No level file found for level " + level);
                return;
            }

            levelBitmap = BitmapFactory.decodeFile(levelFiles[level - 29].getPath());
        }

        // Reduce the size of the level bitmap
        levelBitmap = Bitmap.createScaledBitmap(levelBitmap, screenWidth, screenHeight, true);

        // Load the texture bitmap
        Bitmap textureBitmap;
        if (level >= 20) {
            textureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture3);
        } else if (level >= 10) {
            textureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture2);
        } else {
            textureBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture);
        }
        textureBitmap = Bitmap.createScaledBitmap(textureBitmap, screenWidth, screenHeight, true);

        // Load the ostatexture bitmap
        Bitmap ostatexture = BitmapFactory.decodeResource(getResources(), R.drawable.osta);
        ostatexture = Bitmap.createScaledBitmap(ostatexture, screenWidth, screenHeight, true);

        // Create a new bitmap with the same dimensions as the level bitmap
        Bitmap newBitmap = Bitmap.createBitmap(screenWidth, screenHeight, Bitmap.Config.ARGB_8888);

        // Draw the level bitmap on the new bitmap
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawBitmap(levelBitmap, 0, 0, null);

        // Replace black pixels with the corresponding pixels in the texture bitmap
       // Paint paint = new Paint();

        // Create arrays to hold the pixel data
        int[] levelPixels = new int[screenWidth * screenHeight];
        int[] texturePixels = new int[screenWidth * screenHeight];
        int[] ostatexturePixels = new int[screenWidth * screenHeight];
        int[] newPixels = new int[screenWidth * screenHeight];

// Get the pixel data for the level, texture, and ostatexture bitmaps
        levelBitmap.getPixels(levelPixels, 0, screenWidth, 0, 0, screenWidth, screenHeight);
        textureBitmap.getPixels(texturePixels, 0, screenWidth, 0, 0, screenWidth, screenHeight);
        ostatexture.getPixels(ostatexturePixels, 0, screenWidth, 0, 0, screenWidth, screenHeight);

// Iterate through the pixels and apply the texture
        for (int i = 0; i < levelPixels.length; i++) {
            int levelPixel = levelPixels[i];

            if (levelPixel == Color.BLACK) {
                int texturePixel = texturePixels[i];
                newPixels[i] = texturePixel;
            } else if (levelPixel == Color.GREEN) {
                int ostatexturePixel = ostatexturePixels[i];
                newPixels[i] = ostatexturePixel;
            } else {
                newPixels[i] = levelPixel;
            }
        }

// Set the new pixel data on the new bitmap
        newBitmap.setPixels(newPixels, 0, screenWidth, 0, 0, screenWidth, screenHeight);
        
        
        
        
// Add the ostatexture to the new bitmap, only on the green part of the bitmap
      /*  for (int x = 0; x < screenWidth; x++) {
            for (int y = 0; y < screenHeight; y++) {
                // Get the color of the pixel in the new bitmap
                int pixelColor = newBitmap.getPixel(x, y);
                // Check if the pixel color is green
                if (pixelColor == Color.GREEN) {
                    // Draw the ostatexture on the new bitmap at the same position
                    canvas.drawBitmap(ostatexture, x, y, null);
                }
            }
        }*/

// Set the new bitmap as the background of the FrameLayout
        background.setBackground(new BitmapDrawable(getResources(), newBitmap));
    }
    
	
	private void backgroundtexturechanger1() {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
        FrameLayout background = (FrameLayout) findViewById(R.id.background);
        
        
        
        
        Bitmap originalBitmapBTC;
        if (level <= 28) {
            originalBitmapBTC = BitmapFactory.decodeResource(getResources(), R.drawable.lvl0 + level);
        } else {
            // Get the external files directory
            File externalFilesDir = getExternalFilesDir(null);

// Create a directory reference to the "levels" directory inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "levels");

            if (!levelsDirectory.exists()) {
                Log.e("Error", "The 'levels' directory does not exist");
                return;
            }

// Get a list of all files in the levelsDirectory
            File[] levelFiles = levelsDirectory.listFiles();

// Sort the files in ascending order based on the last four digits of their names
            Arrays.sort(levelFiles, new Comparator<File>() {
                    @Override
                    public int compare(File file1, File file2) {
                        String fileName1 = file1.getName();
                        String fileName2 = file2.getName();

                        // Extract the last four digits of each file name
                        int fileNumber1 = Integer.parseInt(fileName1.substring(fileName1.length() - 8, fileName1.length() - 4));
                        int fileNumber2 = Integer.parseInt(fileName2.substring(fileName2.length() - 8, fileName2.length() - 4));

                        // Compare the last four digits of each file name
                        return Integer.compare(fileNumber1, fileNumber2);
                    }
                });


            if (levelFiles == null || levelFiles.length == 0 || level - 29 >= levelFiles.length) {
                Log.e("Error", "No level file found for level " + level);
                return;
            }

            originalBitmapBTC = BitmapFactory.decodeFile(levelFiles[level - 29].getPath());
        }

		// Reduce the size of the level bitmap
		Bitmap levelBitmap = Bitmap.createScaledBitmap(originalBitmapBTC, screenWidth, screenHeight, true);

		// Load the texture bitmap
		if (textureBitmap == null | ostatexture == null | level >= 0) {
            
            // Declare a Map to store the loaded bitmaps
            Map<Integer, Bitmap> loadedBitmaps = new HashMap<>();

            if (level >= 20) {
                if (!loadedBitmaps.containsKey(R.drawable.mtexture3)) {
                    Bitmap textureBitmapBTC = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture3);
                    Bitmap textureBitmap = Bitmap.createScaledBitmap(textureBitmapBTC, screenWidth, screenHeight, true);
                    loadedBitmaps.put(R.drawable.mtexture3, textureBitmap);
                }
                textureBitmap = loadedBitmaps.get(R.drawable.mtexture3);
            } else if (level >= 10) {
                if (!loadedBitmaps.containsKey(R.drawable.mtexture2)) {
                    Bitmap textureBitmapBTC = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture2);
                    Bitmap textureBitmap = Bitmap.createScaledBitmap(textureBitmapBTC, screenWidth, screenHeight, true);
                    loadedBitmaps.put(R.drawable.mtexture2, textureBitmap);
                }
                textureBitmap = loadedBitmaps.get(R.drawable.mtexture2);
            } else {
                if (!loadedBitmaps.containsKey(R.drawable.mtexture)) {
                    Bitmap textureBitmapBTC = BitmapFactory.decodeResource(getResources(), R.drawable.mtexture);
                    Bitmap textureBitmap = Bitmap.createScaledBitmap(textureBitmapBTC, screenWidth, screenHeight, true);
                    loadedBitmaps.put(R.drawable.mtexture, textureBitmap);
                }
                textureBitmap = loadedBitmaps.get(R.drawable.mtexture);
            }

            if (!loadedBitmaps.containsKey(R.drawable.osta)) {
                Bitmap ostatextureBTC = BitmapFactory.decodeResource(getResources(), R.drawable.osta);
                Bitmap ostatexture = Bitmap.createScaledBitmap(ostatextureBTC, screenWidth, screenHeight, true);
                loadedBitmaps.put(R.drawable.osta, ostatexture);
            }
            ostatexture = loadedBitmaps.get(R.drawable.osta);
            }

		// Copy the level bitmap so we can modify the copy without affecting the original
	Bitmap newBitmap = levelBitmap.copy(levelBitmap.getConfig(), true);

		// Process each pixel in the copy
		 // keep track of current x position

        for (int x = 0; x < newBitmap.getWidth(); x++) {
            for (int y = 0; y < newBitmap.getHeight(); y++) {
                int pixelColor = newBitmap.getPixel(x, y);
                if (Color.alpha(pixelColor) == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
                    int replacePixel = textureBitmap.getPixel(x, y);
                    newBitmap.setPixel(x, y, replacePixel);

                 }
                int greenpixelColor = newBitmap.getPixel(x, y);
                if (Color.red(greenpixelColor) == 0 && Color.green(greenpixelColor) == 255 && Color.blue(greenpixelColor) == 0) {
                    int replaceostaPixel = ostatexture.getPixel(x, y);
                    newBitmap.setPixel(x, y, replaceostaPixel);
                }
            }
            //background.setBackground(new BitmapDrawable(getResources(), newBitmap));
        }
        
        
		
		
		
		 
		// Set the modified bitmap as the background image
		
		background.setBackground(new BitmapDrawable(getResources(), newBitmap));
        
      
	}
	
	
	

    private boolean isOnBlackPart() {
		int[] objectCoordinates = new int[2];
		draggableObject.getLocationInWindow(objectCoordinates);

	    
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		final int screenWidth = displayMetrics.widthPixels;
		final int screenHeight = displayMetrics.heightPixels;
		FrameLayout background = (FrameLayout) findViewById(R.id.background);
		
		int Halfscreenh = screenHeight / 2;
// Create a new canvas and pass in the bitmap
		Bitmap mutableBitmap = ((BitmapDrawable) background.getBackground()).getBitmap().copy(Bitmap.Config.ARGB_8888, true); // Create a mutable copy of the bitmap
        Canvas canvas = new Canvas(mutableBitmap); // Use the mutable bitmap in the Canvas constructor
        

// Create a paint object with the desired properties
		Paint paint = new Paint();
		paint.setColor(Color.argb(60, 255, 0, 0));
		paint.setStyle(Paint.Style.FILL);
		double top = objectCoordinates[1] - (draggableObject.getHeight() * 0.9);
// Draw a rectangle on the canvas
		
		double left = objectCoordinates[0] + (draggableObject.getWidth() / 4);
		
		if(objectCoordinates[1] < Halfscreenh)
		{
			 top = top - ((Halfscreenh-objectCoordinates[1])/11);
		}
		else
		{
			top = top - ((Halfscreenh-objectCoordinates[1])/14);
		}
		
		

		// Divide width and height of draggable object by 2
		double width = draggableObject.getWidth() / 2;
		double height = draggableObject.getHeight() / 2;

		double right = left + width;
		double bottom = top + height;
		
		

		// Check the color of all pixels that the draggable object covers
		for (double x = left; x < right; x++) {
			for (double y = top; y < bottom; y++) {
				try {
					int pixelColor = testinglevel.getPixel((int) x, (int) y);

					if (show_hitbox) {
						canvas.drawPoint((int) x, (int) y, paint);
					}

					if (Color.alpha(pixelColor)  == 255 && Color.red(pixelColor) == 0 && Color.green(pixelColor) == 0 && Color.blue(pixelColor) == 0) {
						// The pixel is black, so the object is on the black part of the background image
						return true;
					}
				} catch (NullPointerException e) {
					e.printStackTrace();
					// Handle the exception here, e.g. return false or throw a custom exception
				}
			}
		}
		

		// The object is not on the black part of the background image
		return false;
	}
	
	
	
	boolean isonpause = true;

	@Override
	public boolean onTouch(View view, MotionEvent motionEvent) {
		DisplayMetrics displayMetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
		int getWidth = displayMetrics.widthPixels;
		int getHeight = displayMetrics.heightPixels;
		final Button myButton = (Button) findViewById(R.id.my_button);
	    final Button next = (Button) findViewById(R.id.next);
        final  FrameLayout burbulilayout = (FrameLayout) findViewById(R.id.burbulilayout);
		TextView money_count = findViewById(R.id.moneyLabel);
		Context context = this;
        
        
        
        
 
		
		float newX = motionEvent.getRawX();
		float newY = motionEvent.getRawY();
		float rotate = 0;
		
		
		
		switch (motionEvent.getAction()) {
			
				
			
			case MotionEvent.ACTION_DOWN:
				// When the user first touches the draggable object
				break;
			case MotionEvent.ACTION_MOVE:
				if (isOnBlackPart()) {
					draggableObject.setX(originalX);
                    draggableObject.setY(originalY);
                    rotateobject.setX(originalX);
                    rotateobject.setY(originalY);
					youlose.setVisibility(View.VISIBLE);
					myButton.setVisibility(view.VISIBLE);
					isonpause = true;
					rotateobject.setRotation(0);
					return true;
				}
				if (isOnGreenPart()) {
					draggableObject.setX(originalX);
                    draggableObject.setY(originalY);
                    rotateobject.setX(originalX);
                    rotateobject.setY(originalY);
					youwin.setVisibility(View.VISIBLE);
					next.setVisibility(view.VISIBLE);
					isonpause = true;
					rotateobject.setRotation(0);
					return true;
				}
                
                
                
                
                
                
                
                
				// When the user is dragging the object around the screen
				int x = (int) motionEvent.getRawX();
				int y = (int) motionEvent.getRawY();
  			    int width = draggableObject.getWidth();
				int height = draggableObject.getHeight();
				

				// Check if the object is going off the left or right edge of the screen
				if (x - width / 2 < 0) {
					x = width / 2;
				} else if (x + width / 2 > getWidth) {
					x = getWidth - width / 2;
				}

				// Check if the object is going off the top or bottom edge of the screen
				if (y - height*2 < 0 | y - height*2 == 0) {
					y = height*2 ;
				} else if (y + height / 8 > getHeight) {
					y = getHeight - height / 8;
				}

                
                
				
				if(!isonpause)
				{
					
					
					
					
					newX = motionEvent.getRawX();
					newY = motionEvent.getRawY();
					
					
					 if (newY != oldy && newY - oldy != 0) {
						 
						rotate = (float)Math.toDegrees(Math.atan(-(newX - oldx) / (newY - oldy)));
						
						if (newX > oldx && newY > oldy)
							{
								rotate += 180;
							}
						
						if( newX <= oldx && newY > oldy)
							{
								rotate += 180;
							}
						
						rotateobject.setRotation(rotate);
					 }
					
				

              
					 
					 if(newY - draggableObject.getY() >= 30 && newX - draggableObject.getX() >= -60 || cantelepory){
                         if(newY - draggableObject.getY() >= -30 && newX - draggableObject.getX() <= 160 || cantelepory){
                         
					   draggableObject.setX(x - width / 2);
					   draggableObject.setY(y - height * 2);
					   rotateobject.setX(x - width / 2);
					   rotateobject.setY(y - height * 2);
                       }
					 }
					
                     
                     
                     if(!ismoneycollected)
                     {
					ImageView image2 = findViewById(R.id.star);

                    Rect rect1 = new Rect();
                    draggableObject.getHitRect(rect1);

                    
                    
                    Rect rect2 = new Rect();
                    image2.getHitRect(rect2);

                    if (Rect.intersects(rect1, rect2)) {
                        // Do something when the ImageViews collide
                    
                        
                        money++;
                        money_count.setText(Integer.toString(money));
                        image2.setVisibility(View.INVISIBLE);
                        ismoneycollected = true;

                        // Save the updated value of money to the shared preferences
                        SharedPreferences.Editor editor = getSharedPreferences(SHARED_PREFS_KEY, MODE_PRIVATE).edit();
                        editor.putInt(MONEY_KEY, money);
                        editor.apply();
                       
                    }

					}
					/*final ImageView burbuliview = new ImageView(this);
					
					burbuliview.setLayoutParams(new LinearLayout.LayoutParams(75, 75));
					burbuliview.setImageResource(R.drawable.tree1);
					
					burbuliview.setY(draggableObject.getY());
					burbuliview.setX(draggableObject.getX());
					
					burbulilayout.addView(burbuliview);
					
					
					
					float initialScale = 1f;
					float finalScale = 0.5f;
					long duration = 255; // in milliseconds
					Animation scaleAnimation = new ScaleAnimation(initialScale, finalScale, initialScale, finalScale, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
					scaleAnimation.setDuration(duration);
					
					

// Set the animation listener to remove the ImageView when the animation ends
					scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
							@Override
							public void onAnimationStart(Animation animation) {}

							@Override
							public void onAnimationEnd(Animation animation) {
								burbulilayout.removeView(burbuliview);
							}

							@Override
							public void onAnimationRepeat(Animation animation) {}
						});

// Start the animation on the ImageView
					burbuliview.startAnimation(scaleAnimation);*/
					
					
					oldx = motionEvent.getRawX();
					oldy = motionEvent.getRawY();
				}
			//	draggableObject.setX(x - width / 2);
			//	draggableObject.setY(y - height * 2);

				break;
			case MotionEvent.ACTION_UP:
				
				// When the user lifts their finger off the object
				
				if (isOnGreenPart()) {
					draggableObject.setX(originalX);
                    draggableObject.setY(originalY);
                    rotateobject.setX(originalX);
                    rotateobject.setY(originalY);
					youwin.setVisibility(View.VISIBLE);
					next.setVisibility(view.VISIBLE);
					isonpause = true;
					rotateobject.setRotation(0);

				}
				
				if (isOnBlackPart()) {
					draggableObject.setX(originalX);
                    draggableObject.setY(originalY);
                    rotateobject.setX(originalX);
                    rotateobject.setY(originalY);
					youlose.setVisibility(View.VISIBLE);
					myButton.setVisibility(View.VISIBLE);
					isonpause = true;
					rotateobject.setRotation(0);
					
				}
				break;
			default:
				return false;
		}
		return true;
	}
	
	
	
	
}

