package pl.akademiakodu.weather.service;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class WeatherModel {
    @JsonProperty("main")
    private TemperatureModel temperatureModel;

    public TemperatureModel getTemperatureModel() {
        return temperatureModel;
    }

    public void setTemperatureModel(TemperatureModel temperatureModel) {
        this.temperatureModel = temperatureModel;
    }
}
