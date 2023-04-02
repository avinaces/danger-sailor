package com.enchantedswords.dragger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import android.os.Environment;
import org.json.JSONObject;
import org.json.JSONException;

import java.io.FileWriter;
import android.content.Context;
import java.io.BufferedReader;
import java.io.FileReader;
import org.json.JSONArray;
import java.io.FileInputStream;







public class test {




    public static void writeToDownloadsDirectory(Context context, float x, float y) {
        try {
            // Get the external files directory
            File externalFilesDir = context.getExternalFilesDir(null);

            // Create a directory called "levels" inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "level data");
            if (!levelsDirectory.exists()) {
                levelsDirectory.mkdirs();
            }

            // Create a file reference to the json file with the filename "level.json"
            File file = new File(levelsDirectory, "level.json");

            // Read the existing file content if any

// Read the existing file content if any
            String fileContent = "";
            if(file.exists()){
                FileInputStream fis = new FileInputStream(file);
                byte[] data = new byte[(int) file.length()];
                fis.read(data);
                fis.close();
                fileContent = new String(data, "UTF-8");

                // Remove any extra opening braces from the existing content
                while (fileContent.length() > 0 && fileContent.charAt(0) == '{') {
                    fileContent = fileContent.substring(1);
                }

                // Remove the last character (closing brace) from the existing content
                if (fileContent.length() > 0 && fileContent.charAt(fileContent.length() - 1) == '}') {
                    fileContent = fileContent.substring(0, fileContent.length() - 1);
                }

                // Add a comma separator if there is existing content
                if (!fileContent.isEmpty()) {
                    fileContent += ",";
                }
            }
            


// Create a JSONObject with both values
            JSONObject jsonObject = new JSONObject();

            String xCordKey="x_cord";
            int i=2;
            while(fileContent.contains(xCordKey)){
                xCordKey="x_cord"+i;
                i++;
            }

            String yCordKey="y_cord";

            i=2;
            while(fileContent.contains(yCordKey)){
                yCordKey="y_cord"+i;
                i++;
            }

            jsonObject.put(xCordKey, x);
            jsonObject.put(yCordKey, y);

            // Append the JSONObject to the existing file content
            String jsonObjectString = jsonObject.toString();

            // Remove the first and last characters (opening and closing braces) from the new content
            if (jsonObjectString.length() > 0 && jsonObjectString.charAt(0) == '{') {
                jsonObjectString = jsonObjectString.substring(1);
            }

            if (jsonObjectString.length() > 0 && jsonObjectString.charAt(jsonObjectString.length() - 1) == '}') {
                jsonObjectString = jsonObjectString.substring(0, jsonObjectString.length() - 1);
            }

            String newFileContent = "{" + fileContent + jsonObjectString + "}";

            FileWriter fileWriter = new FileWriter(file);
            fileWriter.write(newFileContent);
            fileWriter.flush();
            fileWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    


    


    
    
    
    

    
    public static float readFromDownloadsDirectory(Context context, String key) {
        try {
            // Get the external files directory
            File externalFilesDir = context.getExternalFilesDir(null);

            // Create a directory called "levels" inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "level data");
            if (!levelsDirectory.exists()) {
                return 12345.0f; // Return a default value if the directory doesn't exist
            }

            // Create a file reference to the json file with the filename "level.json"
            File file = new File(levelsDirectory, "level.json");

            // Read the contents of the file into a string
            StringBuilder fileContents = new StringBuilder();
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileContents.append(line);
            }
            bufferedReader.close();

            // Parse the string into a JSONObject
            JSONObject jsonObject = new JSONObject(fileContents.toString());

            // Get the value associated with the key and convert it to a float
            return Float.parseFloat(jsonObject.getString(key));

        } catch (JSONException | IOException e) {
            e.printStackTrace();

            log_error(e,context);

            return 12345.0f; // Return a default value if an exception occurs
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    public static void log_error(Exception e,Context context)
    {
        try {
            // Get the external files directory
            File externalFilesDir = context.getExternalFilesDir(null);

            // Create a directory called "levels" inside the external files directory
            File levelsDirectory = new File(externalFilesDir, "level data");
            if (!levelsDirectory.exists()) {
                levelsDirectory.mkdirs();
            }

            // Create a file reference to the json file with the filename "level.json"
            File file = new File(levelsDirectory, "error.log");

            
            // Create a file writer and write the JSON object to the file
            FileWriter fileWriter = new FileWriter(file, true);
            fileWriter.write("Exception: " + e.toString() + "\n\n");
            fileWriter.flush();
            fileWriter.close();
        } catch ( IOException D) {
            D.printStackTrace();
        }
        
    }
    
}


