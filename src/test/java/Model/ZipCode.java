package Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class ZipCode {
    private String country;
    private String countryabbreviation;
    private String postcode;
    private ArrayList<Place> places;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountryabbreviation() {
        return countryabbreviation;
    }

    @JsonProperty("country abbreviation") //class nesnesine dönüştürürken aradaki boşluk olmadığından aşağıdaki alanı dolduramıyor. eğer jsondaki veri "country abbreviation" bu ise gel bu alanı kullan demiş oluyoruz.
    public void setCountryabbreviation(String countryabbreviation) {
        this.countryabbreviation = countryabbreviation;
    }

    public String getPostcode() {
        return postcode;
    }

    @JsonProperty("post code")
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public ArrayList<Place> getPlaces() {
        return places;
    }

    public void setPlaces(ArrayList<Place> places) {
        this.places = places;
    }

    @Override
    public String toString() {
        return "ZipCode{" +
                "country='" + country + '\'' +
                ", countryabbreviation='" + countryabbreviation + '\'' +
                ", postcode='" + postcode + '\'' +
                ", places=" + places +
                '}';
    }
}