package com.example.weatherman;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;;
import org.json.simple.parser.JSONParser;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APIConnector {
    private final String urlString;
    
    public APIConnector(String urlString) throws MalformedURLException{
        this.urlString=urlString;
    }
    
    public JSONArray getJSONArray(String query){
        try{
            URL url = new URL(urlString + query);

            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            
            int responseCode =  conn.getResponseCode();
            
            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                System.out.println("connection good for array");
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());
                String tempInfo =scanner.nextLine();
                while (!tempInfo.equals("") ||tempInfo!=null ){
                    informationString.append(tempInfo);
                    tempInfo =scanner.nextLine();
                }
                scanner.close();
                
                JSONParser parser = new JSONParser();
                return (JSONArray) parser.parse(String.valueOf(informationString));
            } 
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public JSONObject getJSONObject(String query) {
        try {
            URL url = new URL(urlString + query);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //Check if connect is made
            int responseCode = conn.getResponseCode();

            if (responseCode != 200) {
                throw new RuntimeException("HttpResponseCode: " + responseCode);
            } else {
                System.out.println("connection good for objects");
                StringBuilder informationString = new StringBuilder();
                Scanner scanner = new Scanner(url.openStream());

                while (scanner.hasNext()) {
                    informationString.append(scanner.nextLine());
                }
                scanner.close();

                JSONParser parse = new JSONParser();

                return (JSONObject) parse.parse(String.valueOf(informationString));
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
