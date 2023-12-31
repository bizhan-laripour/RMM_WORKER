package com.rmm.worker.scheduler;


import com.rmm.worker.dto.*;
import com.rmm.worker.entity.ZabbixResponseEntity;
import com.rmm.worker.feign.ZabbixFeignClient;
import com.rmm.worker.service.ZabbixService;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Scheduler {

    private final Environment environment;


    private final ZabbixFeignClient zabbixFeignClient;

    private final ZabbixService zabbixService;

    public Scheduler(Environment environment, ZabbixFeignClient zabbixFeignClient, ZabbixService zabbixService) {
        this.environment = environment;
        this.zabbixFeignClient = zabbixFeignClient;
        this.zabbixService = zabbixService;
    }

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime() {
        HostIdResult host =  getIdByIp();
        ZabbixResponseEntity result = getInformation(host.getResult().get(0).getHostid().toString());
        zabbixService.save(result);
        System.out.println("result saved");
    }


    public ZabbixResponseEntity getInformation(String ip){
        ZabbixRequestDto zabbixRequestDto = new ZabbixRequestDto();
        ZabbixRequestParams zabbixRequestParams = new ZabbixRequestParams();
        List<String> output = new ArrayList<>();
        output.add("itemid");
        output.add("name");
        output.add("lastvalue");
        List<Integer> hostids = new ArrayList<>();
        hostids.add(Integer.parseInt(ip));
        zabbixRequestParams.setHostIds(hostids);
        zabbixRequestParams.setOutput(output);
        zabbixRequestDto.setAuth(environment.getProperty("auth"));
        zabbixRequestDto.setId(1L);
        zabbixRequestDto.setParams(zabbixRequestParams);
        zabbixRequestDto.setMethod("item.get");
        zabbixRequestDto.setJsonrpc(environment.getProperty("jsonrpc"));
       return zabbixFeignClient.getAllInformation(zabbixRequestDto);

    }

    public HostIdResult getIdByIp(){
        HostIdDto hostIdDto = new HostIdDto();
        HostIdParams params = new HostIdParams();
        Filter filter = new Filter();
        filter.setIp(environment.getProperty("agent.ip"));
        params.setFilter(filter);
        List<String> output = new ArrayList<>();
        output.add("hostid");
        params.setOutput(output);
        hostIdDto.setParams(params);
        hostIdDto.setId(1L);
        hostIdDto.setAuth(environment.getProperty("auth"));
        hostIdDto.setJsonrpc(environment.getProperty("jsonrpc"));
        hostIdDto.setMethod("host.get");
        hostIdDto.setParams(params);
        return zabbixFeignClient.getHostId(hostIdDto);
    }
}
