package com.hencoder.hencoderpracticedraw1.bean;

/**
 * Created by snowson on 17-11-25.
 */

public class VersionModel {
    private String versionName;
    private int versionCount;
    private int color;

    public VersionModel(String versionName, int versionCount, int color) {
        this.versionName = versionName;
        this.versionCount = versionCount;
        this.color = color;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public int getVersionCount() {
        return versionCount;
    }

    public void setVersionCount(int versionCount) {
        this.versionCount = versionCount;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
