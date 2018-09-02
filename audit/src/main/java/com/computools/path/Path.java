package com.computools.path;

public enum Path {
    PATH("/home/dmitry/IdeaProjects/Media/images/");

    private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
