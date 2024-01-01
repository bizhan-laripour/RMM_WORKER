package com.rmm.worker.dto;

import java.util.List;

public class ZabbixRequestParams {

    private List<String> output;

    private List<Integer> hostIds;

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public List<Integer> getHostIds() {
        return hostIds;
    }

    public void setHostIds(List<Integer> hostIds) {
        this.hostIds = hostIds;
    }
}
