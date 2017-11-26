package com.hencoder.hencoderpracticedraw1.bean;

import android.graphics.Point;

/**
 * Created by snowson on 17-11-26.
 */

public class LabelLineModule {
    public enum LineType {
        TOPLEFT, TOPRIGHT, BOTTOMLEFT, BOTTOMRIGHT, CENTERLEFT, CENTERRIGHT
    }

    private LineType lineType;
    private Point point;

    public LineType getLineType() {
        return lineType;
    }

    public void setLineType(LineType lineType) {
        this.lineType = lineType;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point poine) {
        this.point = poine;
    }
}
