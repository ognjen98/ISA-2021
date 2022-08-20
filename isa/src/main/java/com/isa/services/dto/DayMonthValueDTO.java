package com.isa.services.dto;

public class DayMonthValueDTO {

    private float value;
    private int dayMonth;
    private float count;
    private float avg;

    public DayMonthValueDTO(){}

    public DayMonthValueDTO(float value, int dayMonth, float count, float avg) {
        this.value = value;
        this.dayMonth = dayMonth;
        this.count = count;
        this.avg = avg;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getDayMonth() {
        return dayMonth;
    }

    public void setDayMonth(int dayMonth) {
        this.dayMonth = dayMonth;
    }

    public float getCount() {
        return count;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public float getAvg() {
        return avg;
    }

    public void setAvg(float avg) {
        this.avg = avg;
    }
}
