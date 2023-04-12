package com.example.demo;

public class StepCounter {


    private int _count = 0;

    public int get_count() {
        return this._count;
    }

    public void step() {
        this._count++;
    }

    public void step(int value) {
        this._count += value;
    }

}
