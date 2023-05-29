package com.ortin.models;

public class DevelopCard {
    private String name;
    private String description;
    private int icon;
    private String gitHub;
    private String contact;

    public DevelopCard(String name, String description, int icon, String gitHub, String contact) {
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.gitHub = gitHub;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getIcon() {
        return icon;
    }

    public String getGitHub() {
        return gitHub;
    }

    public String getContact() {
        return contact;
    }
}
