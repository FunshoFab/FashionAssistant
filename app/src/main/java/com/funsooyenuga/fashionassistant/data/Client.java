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
    private float cuff_or_round_sleeve;
    private float long_or_short_sleeve;
    private float topOrGownLength;
    private float halfLength_f;

    //TROUSER
    private float waist;
    private float thigh;
    private float length;
    private float bottom;
    private String highWaist;
    private String hips;

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

    public float getCuff_or_round_sleeve() {
        return cuff_or_round_sleeve;
    }

    public void setCuff_or_round_sleeve(float cuff_or_round_sleeve) {
        this.cuff_or_round_sleeve = cuff_or_round_sleeve;
    }

    public float getLong_or_short_sleeve() {
        return long_or_short_sleeve;
    }

    public void setLong_or_short_sleeve(float long_or_short_sleeve) {
        this.long_or_short_sleeve = long_or_short_sleeve;
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

    public float getWaist() {
        return waist;
    }

    public void setWaist(float waist) {
        this.waist = waist;
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


    public String getHighWaist() {
        return highWaist;
    }

    public void setHighWaist(String highWaist) {
        this.highWaist = highWaist;
    }

    public String getHips() {
        return hips;
    }

    public void setHips(String hips) {
        this.hips = hips;
    }
}
