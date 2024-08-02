package org.Simdev.object;

public class Project {
    private String name, date, location;
    public Project(String name, String date, String location) {
        this.name = name;
        this.date = date;
        this.location = location;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getLocation() { return location; }
}
