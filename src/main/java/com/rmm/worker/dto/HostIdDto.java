package com.rmm.worker.dto;

public class HostIdDto {

    private String jsonrpc;

    private String method;

    private HostIdParams params;

    private String auth;

    private Long id;

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

    public HostIdParams getParams() {
        return params;
    }

    public void setParams(HostIdParams params) {
        this.params = params;
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
}
