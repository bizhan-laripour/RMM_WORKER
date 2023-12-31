package com.rmm.worker.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.List;
@Document(indexName = "zabbixresponse")
public class ZabbixResponseEntity {

    @Id
    private String id;
    private String jsonrpc;

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
}
