package com.rmm.worker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "zabbixitems")
public class ZabbixResultItemEntity {

    @Id
    private String id;
    private String itemid;

    private String name;

    private String lastValue;

    public String getItemid() {
        return itemid;
    }

    public void setItemid(String itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastValue() {
        return lastValue;
    }

    public void setLastValue(String lastValue) {
        this.lastValue = lastValue;
    }
}
