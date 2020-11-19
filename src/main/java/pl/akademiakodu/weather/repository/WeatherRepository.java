package pl.akademiakodu.weather.repository;

import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.weather.model.Weather;

public interface WeatherRepository extends CrudRepository<Weather,Integer> {



}
