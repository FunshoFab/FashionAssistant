package com.funsooyenuga.fashionassistant;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class Client {
    //Personal details
    String name;
    char sex;
    String phoneNumber;

    //Measurements
    //CAP
    float cap_base_m;

    //TOP OR GOWN
    float shoulder;
    float chest_or_bust;
    float sleeve;
    float round_sleeve;
    float long_sleeve;
    float top_or_gown_length;
    float half_length_f;

    //TROUSER
    float waist_or_hips;
    float thigh;
    float length;
    float bottom;

    //GETTER AND SETTER METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSex() {
        return sex;
    }

    public void setSex(char sex) {
        this.sex = sex;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public float getCap_base_m() {
        return cap_base_m;
    }

    public void setCap_base_m(float cap_base_m) {
        this.cap_base_m = cap_base_m;
    }

    public float getShoulder() {
        return shoulder;
    }

    public void setShoulder(float shoulder) {
        this.shoulder = shoulder;
    }

    public float getChest_or_bust() {
        return chest_or_bust;
    }

    public void setChest_or_bust(float chest_or_bust) {
        this.chest_or_bust = chest_or_bust;
    }

    public float getSleeve() {
        return sleeve;
    }

    public void setSleeve(float sleeve) {
        this.sleeve = sleeve;
    }

    public float getRound_sleeve() {
        return round_sleeve;
    }

    public void setRound_sleeve(float round_sleeve) {
        this.round_sleeve = round_sleeve;
    }

    public float getLong_sleeve() {
        return long_sleeve;
    }

    public void setLong_sleeve(float long_sleeve) {
        this.long_sleeve = long_sleeve;
    }

    public float getTop_or_gown_length() {
        return top_or_gown_length;
    }

    public void setTop_or_gown_length(float top_or_gown_length) {
        this.top_or_gown_length = top_or_gown_length;
    }

    public float getHalf_length_f() {
        return half_length_f;
    }

    public void setHalf_length_f(float half_length_f) {
        this.half_length_f = half_length_f;
    }

    public float getWaist_or_hips() {
        return waist_or_hips;
    }

    public void setWaist_or_hips(float waist_or_hips) {
        this.waist_or_hips = waist_or_hips;
    }

    public float getThigh() {
        return thigh;
    }

    public void setThigh(float thigh) {
        this.thigh = thigh;
    }

    public float getLength() {
        return length;
    }

    public void setLength(float length) {
        this.length = length;
    }

    public float getBottom() {
        return bottom;
    }

    public void setBottom(float bottom) {
        this.bottom = bottom;
    }
}
