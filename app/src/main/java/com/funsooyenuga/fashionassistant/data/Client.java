package com.funsooyenuga.fashionassistant.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class Client {
    //Personal details
    UUID id;
    String name;
    String sex;
    String phoneNumber;
    String dueDate;
    Boolean delivered;

    //Measurements
    //CAP
    float cap_base_m;

    //TOP OR GOWN
    float shoulder;
    float chestOrBust;
    float shortSleeve;
    float roundSleeve;
    float longSleeve;
    float topOrGownLength;
    float halfLength_f;

    //TROUSER
    float waist_or_hips;
    float thigh;
    float length;
    float bottom;

    public Client() {
        this.id = UUID.randomUUID();
    }

    //GETTER AND SETTER METHODS
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
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

    public float getChestOrBust() {
        return chestOrBust;
    }

    public void setChestOrBust(float chestOrBust) {
        this.chestOrBust = chestOrBust;
    }

    public float getShortSleeve() {
        return shortSleeve;
    }

    public void setShortSleeve(float shortSleeve) {
        this.shortSleeve = shortSleeve;
    }

    public float getRoundSleeve() {
        return roundSleeve;
    }

    public void setRoundSleeve(float roundSleeve) {
        this.roundSleeve = roundSleeve;
    }

    public float getLongSleeve() {
        return longSleeve;
    }

    public void setLongSleeve(float longSleeve) {
        this.longSleeve = longSleeve;
    }

    public float getTopOrGownLength() {
        return topOrGownLength;
    }

    public void setTopOrGownLength(float topOrGownLength) {
        this.topOrGownLength = topOrGownLength;
    }

    public float getHalfLength_f() {
        return halfLength_f;
    }

    public void setHalfLength_f(float halfLength_f) {
        this.halfLength_f = halfLength_f;
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

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        SimpleDateFormat dateFormat  = new SimpleDateFormat("EEE, d MMM yyyy");
        String formattedDate = dateFormat.format(dueDate);
        this.dueDate = formattedDate;
    }

    public UUID getId() {
        return id;
    }
}
