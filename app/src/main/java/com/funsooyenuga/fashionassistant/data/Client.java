package com.funsooyenuga.fashionassistant.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class Client {
    //Personal details
    private final UUID id;
    private String name;
    private String sex;
    private String phoneNumber;
    private String dueDate;
    private Boolean delivered;

    //Measurements
    //CAP
    private float cap_base_m;

    //TOP OR GOWN
    private float shoulder;
    private float chestOrBust;
    private float shortSleeve;
    private float roundSleeve;
    private float longSleeve;
    private float topOrGownLength;
    private float halfLength_f;

    //TROUSER
    private float waist_or_hips;
    private float thigh;
    private float length;
    private float bottom;

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
        this.dueDate = dateFormat.format(dueDate);
    }

    public UUID getId() {
        return id;
    }
}
