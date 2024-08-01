package org.Simdev.object;

public class Project {
    private String name, date, directory;
    public Project(String name, String date, String directory) {
        this.name = name;
        this.date = date;
        this.directory = directory;
    }

    public String getName() { return name; }
    public String getDate() { return date; }
    public String getDirectory() { return directory; }
}
