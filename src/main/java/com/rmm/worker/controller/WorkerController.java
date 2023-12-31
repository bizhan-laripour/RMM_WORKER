package com.rmm.worker.controller;

import com.rmm.worker.entity.ZabbixResponseEntity;
import com.rmm.worker.service.ZabbixService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class WorkerController {

    private final ZabbixService zabbixService;

    public WorkerController(ZabbixService zabbixService) {
        this.zabbixService = zabbixService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<?> save(@RequestBody ZabbixResponseEntity zabbixResponseEntity){
        return new ResponseEntity<>(zabbixService.save(zabbixResponseEntity) , HttpStatus.OK);
    }

    @PostMapping(value = "find-by-id")
    public ResponseEntity<?> findById(String id){
        return new ResponseEntity<>(zabbixService.findById(id) , HttpStatus.OK);
    }

    @PostMapping(value = "delete")
    public void delete(ZabbixResponseEntity zabbixResponseEntity){
        zabbixService.delete(zabbixResponseEntity);
    }

    @GetMapping(value = "find-all")
    public ResponseEntity<?> findAll(){
        return new ResponseEntity<>(zabbixService.findAll() , HttpStatus.OK);
    }
}
