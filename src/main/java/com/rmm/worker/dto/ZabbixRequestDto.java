package com.rmm.worker.dto;

public class ZabbixRequestDto {

    private String jsonrpc;

    private String method;

    private String auth;

    private Long id;

    private ZabbixRequestParams params;

    public String getJsonrpc() {
        return jsonrpc;
    }

    public void setJsonrpc(String jsonrpc) {
        this.jsonrpc = jsonrpc;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ZabbixRequestParams getParams() {
        return params;
    }

    public void setParams(ZabbixRequestParams params) {
        this.params = params;
    }
}
