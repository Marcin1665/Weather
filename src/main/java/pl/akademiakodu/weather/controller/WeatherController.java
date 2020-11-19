package pl.akademiakodu.weather.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import pl.akademiakodu.weather.model.City;
import pl.akademiakodu.weather.model.Weather;
import pl.akademiakodu.weather.repository.CityRepository;
import pl.akademiakodu.weather.repository.WeatherRepository;
import pl.akademiakodu.weather.service.WeatherModel;

import java.time.LocalDate;

@Controller
public class WeatherController {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherRepository weatherRepository;
    @GetMapping("/")
    public String addCity(){
        return "add";
    }

    @GetMapping("/weather")
    public String index(@RequestParam String city, ModelMap map){
        WeatherModel weatherModel = new RestTemplate().getForObject(
                        "http://api.openweathermap.org/data/2.5/weather?q="
                                +city+"&appid=ef2028e98b54649bf1f4c4582631f350&units=metric",
                WeatherModel.class);
        createWeatherFromWeatherModel(weatherModel,city);
        map.put("city",city);
        map.put("weather",weatherModel);
        return "result";
    }
    //cities/warszawa

    private void createWeatherFromWeatherModel(WeatherModel weatherModel,String city){
        City myCity = cityRepository.findByName(city).
                orElse(new City(city));
        // save albo zapisuje do bazy danych albo robi update
        cityRepository.save(myCity);
        Weather weather = new Weather();
        weather.setCity(myCity);
        weather.setPressure((double)weatherModel.getTemperatureModel().getPressure());
        weather.setTemperature(weatherModel.getTemperatureModel().getTemperature());
        weather.setDate(LocalDate.now());
        weatherRepository.save(weather);
    }







}
