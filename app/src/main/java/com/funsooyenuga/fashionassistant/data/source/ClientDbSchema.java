package com.funsooyenuga.fashionassistant.data.source;

/**
 * Created by FAB THE GREAT on 30/11/2016.
 */

public class ClientDbSchema {
    public static final class ClientInfoTable {
        public static final String TABLE_NAME = "client_info";

        //COLUMN NAMES
        //Client info
        public static final String CLIENT_NAME = "client_name";
        public static final String CLIENT_PHONE_NUMBER = "client_phone_number";
        public static final String CLIENT_SEX = "client_sex";
        public static final String DELIVERY_DATE = "delivery_date";
        public static final String RECEIVED_DATE = "received_date";
        public static final String DELIVERED = "delivered";
        public static final String CLIENT_ID = "client_id";
        public static final String ADD_INFO = "add_info";

    }

    public static final class MeasurementTable {
        public static final String TABLE_NAME = "measurement";

        //COLUMN NAMES
        //Cap
        public static final String CAP_BASE = "capBase";

        //Top or gown
        public static final String SHOULDER = "shoulder";
        public static final String CHEST_OR_BUST = "chestOrBust";
        public static final String LONG_SLEEVE = "longSleeve";
        public static final String CUFF = "cuff";
        public static final String SHORT_SLEEVE = "shortSleeve";
        public static final String ROUND_SLEEVE = "roundSleeve";
        public static final String TOP_OR_GOWN_LENGTH = "topLength";
        public static final String HALF_LENGTH = "halfLength";
        public static final String KNEE_LENGTH = "kneeLength";
        public static final String HIGH_WAIST = "highWaist";

        //Trouser
        public static final String WAIST = "waist";
        public static final String THIGH = "thigh";
        public static final String TROUSER_LENGTH = "length";
        public static final String BOTTOM = "bottom";
        public static final String HIPS = "hips";
    }

}
