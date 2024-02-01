package com.rmm.worker.scheduler;


import com.rmm.worker.dto.*;
import com.rmm.worker.entity.ZabbixResponseEntity;
import com.rmm.worker.feign.ZabbixFeignClient;
import com.rmm.worker.service.ZabbixService;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;
import java.util.*;

@Component
public class Scheduler {

    private final Environment environment;


    private final ZabbixFeignClient zabbixFeignClient;

    private final ZabbixService zabbixService;

    public static Map<String, String> ipTable = new HashMap<>();

    public Scheduler(Environment environment, ZabbixFeignClient zabbixFeignClient, ZabbixService zabbixService) {
        this.environment = environment;
        this.zabbixFeignClient = zabbixFeignClient;
        this.zabbixService = zabbixService;

    }

    @Scheduled(fixedRate = 60000)
    public void getDeviceInformation() throws UnknownHostException {
        if(ipTable.isEmpty()){
            generate(environment.getProperty("agent.ip.from") , environment.getProperty("agent.ip.to"));
        }
        for (Map.Entry<String, String> entry : ipTable.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            ZabbixResponseEntity result = getInformation(entry.getValue());
            result.setHostId(entry.getValue());
            result.setIp(entry.getKey());
            result.setDate(new Date());
            zabbixService.save(result);
            System.out.println("result saved");
        }
    }


    public ZabbixResponseEntity getInformation(String id) {
        ZabbixRequestDto zabbixRequestDto = new ZabbixRequestDto();
        ZabbixRequestParams zabbixRequestParams = new ZabbixRequestParams();
        List<String> output = new ArrayList<>();
        output.add("itemid");
        output.add("name");
        output.add("lastvalue");
        List<Integer> hostids = new ArrayList<>();
        hostids.add(Integer.parseInt(id));
        zabbixRequestParams.setHostIds(hostids);
        zabbixRequestParams.setOutput(output);
        zabbixRequestDto.setAuth(environment.getProperty("auth"));
        zabbixRequestDto.setId(1L);
        zabbixRequestDto.setParams(zabbixRequestParams);
        zabbixRequestDto.setMethod("item.get");
        zabbixRequestDto.setJsonrpc(environment.getProperty("jsonrpc"));
        return zabbixFeignClient.getAllInformation(zabbixRequestDto);

    }

    public HostIdResult getIdByIp(String ip) {
        HostIdDto hostIdDto = new HostIdDto();
        HostIdParams params = new HostIdParams();
        Filter filter = new Filter();
        filter.setIp(ip);
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

    private int convertToInt(String address) throws UnknownHostException {
        InetAddress inetAddress = Inet4Address.getByName(address);
        return ByteBuffer.allocate(4).put(inetAddress.getAddress()).getInt(0);
    }

    // Convert an integer to an IP string
    private String convertToIp(int address) throws UnknownHostException {
        return Inet4Address.getByAddress(ByteBuffer.allocate(4).putInt(address).array()).getHostAddress();
    }

    public void generate(String address1, String address2) throws UnknownHostException {
        int numeric1 = convertToInt(address1);
        int numeric2 = convertToInt(address2);
        for (int i = Math.min(numeric1, numeric2); i <= Math.max(numeric1, numeric2); i++) {
            String ip = convertToIp(i);
            HostIdResult hostIdResult = getIdByIp(ip);
            if (hostIdResult != null && !hostIdResult.getResult().isEmpty()) {
                ipTable.put(ip, hostIdResult.getResult().get(0).getHostid());
            }
        }
    }
}
