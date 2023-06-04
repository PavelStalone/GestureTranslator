package com.ortin.gesturetranslator.models;

public class DeveloperCard {
    private final String name;
    private final String description;
    private final int icon;
    private final String gitHub;
    private final String contact;

    public DeveloperCard(String name, String description, int icon, String gitHub, String contact) {
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
