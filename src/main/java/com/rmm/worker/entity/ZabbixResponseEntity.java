package com.rmm.worker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;
import java.util.List;
@Document(indexName = "zabbixresponse")
public class ZabbixResponseEntity {

    @Id
    private String id;

    private String hostId;
    private String jsonrpc;

    private Date date;

    private String ip;

    private List<ZabbixResultItemEntity> result;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public List<ZabbixResultItemEntity> getResult() {
        return result;
    }

    public void setResult(List<ZabbixResultItemEntity> result) {
        this.result = result;
    }

    public String getHostId() {
        return hostId;
    }

    public void setHostId(String hostId) {
        this.hostId = hostId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}
