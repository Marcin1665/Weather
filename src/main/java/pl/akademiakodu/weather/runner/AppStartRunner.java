package pl.akademiakodu.weather.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import pl.akademiakodu.weather.model.City;
import pl.akademiakodu.weather.model.Weather;
import pl.akademiakodu.weather.repository.CityRepository;
import pl.akademiakodu.weather.repository.WeatherRepository;

import java.time.LocalDate;

@Component
public class AppStartRunner implements ApplicationRunner {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private WeatherRepository weatherRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if ( cityRepository.count()==0){
           cityRepository.save(new City("Warszawa"));
           cityRepository.save(new City("Lipno"));
           cityRepository.save(new City("Katowice"));
        }
        // dostaje city o numerze 1
        City city = cityRepository.findById(1).get();


        System.out.println(
                cityRepository.findByCityName("Warszawa").getName());
        System.out.println(
                cityRepository.findByCity("Warszawa").getName());
        System.out.println(
                cityRepository.findByName("Warszawa").get().getName());




    }
}
