package com.rmm.worker.feign;

import com.rmm.worker.dto.HostIdDto;
import com.rmm.worker.dto.HostIdResult;
import com.rmm.worker.dto.ZabbixRequestDto;
import com.rmm.worker.entity.ZabbixResponseEntity;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(value = "all-informations", url = "https://zabbix.featmate.com")
public interface ZabbixFeignClient {

    @PostMapping(value = "/api_jsonrpc.php")
    ZabbixResponseEntity getAllInformation(@RequestBody ZabbixRequestDto zabbixRequestDto);

    @PostMapping(value = "/api_jsonrpc.php")
    HostIdResult getHostId(@RequestBody HostIdDto hostIdDto);
}
