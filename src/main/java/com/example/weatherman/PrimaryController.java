package com.example.weatherman;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.net.MalformedURLException;

public class PrimaryController {
    @FXML
    private TextArea weatherText;

    @FXML
    private TextField cityInput;

    @FXML
    private Button btn;

    private final String cityAPI="http://api.weatherapi.com/v1/current.json?key=<f284aa318b7940d096d74443232007>&q=London";
    private final String weatherAPI="http://api.weatherapi.com/v1/";

    @FXML
    void getWeatherData(ActionEvent e)throws MalformedURLException{
        JSONObject todayWeather = GeTodaysWeatherInfomation(getwoeid());

        weatherText.setText(
                "Min temp: "+todayWeather.get("min_temp")+
                        "\nCurrent temp: "+todayWeather.get("the_temp")+
                        "\nMax temp: "+ todayWeather.get("max_temp")

        );
    }

    public String getwoeid() throws MalformedURLException{
        APIConnector apiConnectorCity = new APIConnector(cityAPI);
        JSONObject weatherJSONObject = new JSONObject();
        int count=0;
        for(Object jsonObject:apiConnectorCity.getJSONArray(cityInput.getText())) {
            weatherJSONObject.put(count++,jsonObject);
            System.out.println(jsonObject);
        }

        return weatherJSONObject.get("woeid").toString();
    }
    
    public JSONObject GeTodaysWeatherInfomation(String woeid) throws MalformedURLException{
        
        APIConnector apiConnectorWeather= new APIConnector(weatherAPI);

        JSONObject weatherJSONObject = apiConnectorWeather.getJSONObject(woeid+"/");

        JSONArray weatherArray = (JSONArray) weatherJSONObject.get("consolidate_weather");
        System.out.println(weatherArray);

        return (JSONObject) weatherArray.get(0);
    }

}
