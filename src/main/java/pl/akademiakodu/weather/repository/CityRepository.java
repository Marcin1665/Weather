package pl.akademiakodu.weather.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.akademiakodu.weather.model.City;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

// generuje nam SQL podstawowe do bazy danych
/*
    HSQL
 */
public interface CityRepository extends CrudRepository<City,Integer> {

    // SPOSÓB 1, AUTOMAGIA, TA METODA MUSI TAK NAZYWAC
    Optional<City> findByName(String name);

    // CZYSTY SQL ?1 parametr podstaw z metody
    @Query(value = "SELECT  * FROM city c WHERE c.name=?1 LIMIT 1",nativeQuery = true)
    City findByCityName(String name);

    // Hibernate SQL ?1 parametr podstaw z metody
    @Query("SELECT c FROM City c WHERE c.name=?1")
    City findByCity(String name);

}
