package com.rmm.worker.repository;

import com.rmm.worker.entity.ZabbixResponseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ZabbixRepository extends ElasticsearchRepository<ZabbixResponseEntity, String> {

    ZabbixResponseEntity findFirstByIpOrderByDate(String hostId);

}
