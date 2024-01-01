package com.rmm.worker.dto;

import java.util.List;

public class HostIdParams {

    private List<String> output;

    private Filter filter;

    public List<String> getOutput() {
        return output;
    }

    public void setOutput(List<String> output) {
        this.output = output;
    }

    public Filter getFilter() {
        return filter;
    }

    public void setFilter(Filter filter) {
        this.filter = filter;
    }
}
