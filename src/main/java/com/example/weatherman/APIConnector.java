package com.example.weatherman;

import org.json.*;

import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class APIConnector {
    private final String urlString;
    
    public APIConnector(String urlString) throws MalformedURLException{
        this.urlString=urlString;
    }
    
//    public JSONArray getJSONArray(String query){
//        try{
//            URL url = new URL(urlString + query);
//
//            HttpURLConnection conn= (HttpURLConnection) url.openConnection();
//            conn.setRequestMethod("GET");
//            conn.connect();
//
//            int responseCode =  conn.getResponseCode();
//
//            if (responseCode != 200) {
//                throw new RuntimeException("HttpResponseCode: " + responseCode);
//            } else {
//                System.out.println("connection good for array");
//                StringBuilder informationString = new StringBuilder();
//                Scanner scanner = new Scanner(url.openStream());
//                while (scanner.hasNext()) {
//                    informationString.append(scanner.nextLine());
//                }
//                scanner.close();
//                JSONObject jsnObjectINFO = new JSONObject(informationString.toString());
//
//                System.out.println(jsnObjectINFO);
//                System.out.println(jsnObjectINFO.getJSONObject("current"));
//                JSONArray jsonArray = new JSONArray(jsnObjectINFO.get("current").toString());
//
//                return jsonArray;
//            }
//        } catch(Exception e){
//            e.printStackTrace();
//        }
//        return null;
//    }

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

                JSONObject jsnObjectINFO = new JSONObject(informationString.toString());
               return  jsnObjectINFO.getJSONObject("current");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
