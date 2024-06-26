package com.rmm.worker.service;

import com.rmm.worker.entity.ZabbixResponseEntity;
import com.rmm.worker.repository.ZabbixRepository;
import org.springframework.data.domain.Limit;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ZabbixService {

    private final ZabbixRepository zabbixRepository;

    public ZabbixService(ZabbixRepository zabbixRepository) {
        this.zabbixRepository = zabbixRepository;
    }


    public ZabbixResponseEntity save(ZabbixResponseEntity zabbixResponseEntity){
        return zabbixRepository.save(zabbixResponseEntity);
    }

    public ZabbixResponseEntity findById(String id){
        return zabbixRepository.findById(id).get();
    }

    public List<ZabbixResponseEntity> findAll(){
        List<ZabbixResponseEntity> zabbixResponseEntities = new ArrayList<>();
        zabbixRepository.findAll().forEach(zabbixResponseEntities::add);
        return zabbixResponseEntities;
    }

    public void delete(ZabbixResponseEntity zabbixResponseEntity){
        zabbixRepository.delete(zabbixResponseEntity);
    }

    public ZabbixResponseEntity findFirstByHostIdOrderByDate(String ip){
        return zabbixRepository.findFirstByIpOrderByDateDesc(ip);
    }

    public List<ZabbixResponseEntity> findAllByIpOrderByDateDesc(String ip){
        return zabbixRepository.findAllByIpOrderByDateDesc(ip , Limit.of(25));
    }
}
