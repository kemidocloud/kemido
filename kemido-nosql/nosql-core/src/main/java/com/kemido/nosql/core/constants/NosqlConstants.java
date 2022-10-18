package com.kemido.nosql.core.constants;

import com.kemido.assistant.core.constants.BaseConstants;

/**
 * <p>Description: Nosql 模块通用常量 </p>
 */
public interface NosqlConstants extends BaseConstants {

    String PROPERTY_PREFIX_NOSQL = PROPERTY_PREFIX_HERODOTUS + ".nosql";

    String PROPERTY_NOSQL_COUCHDB = PROPERTY_PREFIX_NOSQL + ".couchdb";
    String PROPERTY_NOSQL_INFLUXDB = PROPERTY_PREFIX_NOSQL + ".influxdb";

    String ITEM_COUCHDB_ENDPOINT = PROPERTY_NOSQL_COUCHDB + ".endpoint";
    String ITEM_COUCHDB_USERNAME = PROPERTY_NOSQL_COUCHDB + ".username";
    String ITEM_COUCHDB_PASSWORD = PROPERTY_NOSQL_COUCHDB + ".password";

    String ITEM_INFLUXDB_ENDPOINT = PROPERTY_NOSQL_INFLUXDB + ".endpoint";
    String ITEM_INFLUXDB_DATABASE = PROPERTY_NOSQL_INFLUXDB + ".database";
    String ITEM_INFLUXDB_USERNAME = PROPERTY_NOSQL_INFLUXDB + ".username";
    String ITEM_INFLUXDB_PASSWORD = PROPERTY_NOSQL_INFLUXDB + ".password";
}
