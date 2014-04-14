package com.transpirationRateSimulator.plot;

/**
 * @author: Levi
 * @version: 2/16/14
 */
public class Pair {
    private double xValue;
    private double yValue;

    public Pair (double indVariable, double depVariable) {
        this.xValue = indVariable;
        this.yValue = depVariable;
    }

    //Getters

    public double getXValue() {
        return xValue;
    }

    public double getYValue() {
        return yValue;
    }
}
