package com.BotifyYou_bot.BotifyYou_bot.Entity;

public class Weather {
    private String city;
    private String weather;
    private int temperature;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        switch (weather){
            case "clear sky" -> this.weather = "ясное небо";
            case "few clouds" -> this.weather = "малооблачно";
            case "scattered clouds" -> this.weather = "переменная облачность";
            case "broken clouds" -> this.weather = "облачно";
            case "overcast clouds" -> this.weather = "пасмурно";
            case "light rain" -> this.weather = "небольшой дождь";
            case "moderate rain" -> this.weather = "дождь";
            case "heavy intensity rain" -> this.weather = "сильный дождь";
            case "light snow" -> this.weather = "небольшой снег";
            case "snow" -> this.weather = "снежно";
            case "heavy snow" -> this.weather = "сильный снег";
            case "thunderstorm" -> this.weather = "гроза";
            case "mist" -> this.weather = "туман";
        }
    }

    public int getTemperature() {
        return temperature;
    }

    public void setTemperature(int temperature) {

        this.temperature = temperature -= 273.15;
    }
}
