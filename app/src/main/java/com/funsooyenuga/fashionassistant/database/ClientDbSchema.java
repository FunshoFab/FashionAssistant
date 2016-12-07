package com.funsooyenuga.fashionassistant.database;

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
        public static final String DUE_DATE = "due_date";
        public static final String DELIVERED = "delivered";
        public static final String CLIENT_ID = "client_id";

    }

    public static final class MeasurementTable {
        public static final String TABLE_NAME = "measurement";

        //COLUMN NAMES
        public static final String MEASUREMENT_ID  = "measurement_id";

        //Cap
        public static final String CAP_BASE = "cap_base";

        //Top or gown
        public static final String SHOULDER = "shoulder";
        public static final String CHEST_OR_BUST = "chest_or_bust";
        public static final String SHORT_SLEEVE = "short_sleeve";
        public static final String LONG_SLEEVE = "long_sleeve";
        public static final String ROUND_SLEEVE = "round_sleeve";
        public static final String TOP_OR_GOWN_LENGTH = "length";
        public static final String HALF_LENGTH = "half_length";

        //Trouser
        public static final String WAIST_OR_HIPS = "waist_or_hips";
        public static final String THIGH = "thigh";
        public static final String LENGTH = "length";
        public static final String BOTTOM = "bottom";
    }

}
