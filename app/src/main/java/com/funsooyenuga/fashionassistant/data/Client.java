package com.funsooyenuga.fashionassistant.data;

import java.util.Calendar;
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
    private Date deliveryDate;
    private Date receivedDate;
    private String addInfo;

    private Boolean delivered;

    //Measurements
    //CAP
    private double capBase;
    //TOP OR GOWN
    private double shoulder;
    private double chestOrBust;
    private double cuffOrRoundSleeve;
    private double longOrShortSleeve;
    private double topOrGownLength;
    //female only
    private double halfLength;
    private double highWaist;
    private double kneeLength;

    //TROUSER
    private double waist;
    private double thigh;
    private double trouserLength;
    private double bottom;
    private double hips;

    public Client() {
        this.id = UUID.randomUUID().toString();
        receivedDate = Calendar.getInstance().getTime();
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

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Date getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(Date receivedDate) {
        this.receivedDate = receivedDate;
    }

    public double getCapBase() {
        return capBase;
    }

    public void setCapBase(double capBase) {
        this.capBase = capBase;
    }

    public double getShoulder() {
        return shoulder;
    }

    public void setShoulder(double shoulder) {
        this.shoulder = shoulder;
    }

    public double getChestOrBust() {
        return chestOrBust;
    }

    public void setChestOrBust(double chestOrBust) {
        this.chestOrBust = chestOrBust;
    }

    public double getCuffOrRoundSleeve() {
        return cuffOrRoundSleeve;
    }

    public void setCuffOrRoundSleeve(double cuffOrRoundSleeve) {
        this.cuffOrRoundSleeve = cuffOrRoundSleeve;
    }

    public double getLongOrShortSleeve() {
        return longOrShortSleeve;
    }

    public void setLongOrShortSleeve(double longOrShortSleeve) {
        this.longOrShortSleeve = longOrShortSleeve;
    }

    public double getTopOrGownLength() {
        return topOrGownLength;
    }

    public void setTopOrGownLength(double topOrGownLength) {
        this.topOrGownLength = topOrGownLength;
    }

    public double getHalfLength() {
        return halfLength;
    }

    public void setHalfLength(double halfLength) {
        this.halfLength = halfLength;
    }

    public double getWaist() {
        return waist;
    }

    public void setWaist(double waist) {
        this.waist = waist;
    }

    public double getThigh() {
        return thigh;
    }

    public void setThigh(double thigh) {
        this.thigh = thigh;
    }

    public double getTrouserLength() {
        return trouserLength;
    }

    public void setTrouserLength(double trouserLength) {
        this.trouserLength = trouserLength;
    }

    public double getBottom() {
        return bottom;
    }

    public void setBottom(double bottom) {
        this.bottom = bottom;
    }

    public Boolean getDelivered() {
        return delivered;
    }

    public void setDelivered(Boolean delivered) {
        this.delivered = delivered;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getHighWaist() {
        return highWaist;
    }

    public void setHighWaist(double highWaist) {
        this.highWaist = highWaist;
    }

    public double getHips() {
        return hips;
    }

    public void setHips(double hips) {
        this.hips = hips;
    }

    public double getKneeLength() {
        return kneeLength;
    }

    public void setKneeLength(double kneeLength) {
        this.kneeLength = kneeLength;
    }

    public String getAddInfo() {
        return addInfo;
    }

    public void setAddInfo(String addInfo) {
        this.addInfo = addInfo;
    }
}
