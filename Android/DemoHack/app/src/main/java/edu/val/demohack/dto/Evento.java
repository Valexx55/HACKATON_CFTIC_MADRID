package edu.val.demohack.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Evento {

    private String title;
    private Location location;


    @JsonProperty("@id")
    private String id;

    public Evento ()
    {

    }

    public Evento(String title, Location location, String id) {
        this.title = title;
        this.location = location;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
