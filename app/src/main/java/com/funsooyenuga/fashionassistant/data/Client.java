package com.funsooyenuga.fashionassistant.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class Client {

    //Personal details
    private String id;
    private String name;
    private String sex;
    private String phoneNumber;
    private String dueDate;
    private Boolean delivered;

    //Measurements
    //CAP
    private String capBase;

    //TOP OR GOWN
    private String shoulder;
    private String chestOrBust;
    private String cuffOrRoundSleeve;
    private String longOrShortSleeve;
    private String topOrGownLength;
    //female only
    private String halfLength;
    private String highWaist;
    private String kneeLength;

    //TROUSER
    private String waist;
    private String thigh;
    private String trouserLength;
    private String bottom;
    private String hips;

    public Client() {
        this.id = UUID.randomUUID().toString();
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

    public String getCapBase() {
        return capBase;
    }

    public void setCapBase(String capBase) {
        this.capBase = capBase;
    }

    public String getShoulder() {
        return shoulder;
    }

    public void setShoulder(String shoulder) {
        this.shoulder = shoulder;
    }

    public String getChestOrBust() {
        return chestOrBust;
    }

    public void setChestOrBust(String chestOrBust) {
        this.chestOrBust = chestOrBust;
    }

    public String getCuffOrRoundSleeve() {
        return cuffOrRoundSleeve;
    }

    public void setCuffOrRoundSleeve(String cuffOrRoundSleeve) {
        this.cuffOrRoundSleeve = cuffOrRoundSleeve;
    }

    public String getLongOrShortSleeve() {
        return longOrShortSleeve;
    }

    public void setLongOrShortSleeve(String longOrShortSleeve) {
        this.longOrShortSleeve = longOrShortSleeve;
    }

    public String getTopOrGownLength() {
        return topOrGownLength;
    }

    public void setTopOrGownLength(String topOrGownLength) {
        this.topOrGownLength = topOrGownLength;
    }

    public String getHalfLength() {
        return halfLength;
    }

    public void setHalfLength(String halfLength) {
        this.halfLength = halfLength;
    }

    public String getWaist() {
        return waist;
    }

    public void setWaist(String waist) {
        this.waist = waist;
    }

    public String getThigh() {
        return thigh;
    }

    public void setThigh(String thigh) {
        this.thigh = thigh;
    }

    public String getTrouserLength() {
        return trouserLength;
    }

    public void setTrouserLength(String trouserLength) {
        this.trouserLength = trouserLength;
    }

    public String getBottom() {
        return bottom;
    }

    public void setBottom(String bottom) {
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

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getKneeLength() {
        return kneeLength;
    }

    public void setKneeLength(String kneeLength) {
        this.kneeLength = kneeLength;
    }
}
