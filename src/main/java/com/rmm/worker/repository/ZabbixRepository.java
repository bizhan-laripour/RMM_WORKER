package com.rmm.worker.repository;

import com.rmm.worker.entity.ZabbixResponseEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;
import java.util.List;


@Repository
public interface ZabbixRepository extends ElasticsearchRepository<ZabbixResponseEntity, String> {

    ZabbixResponseEntity findFirstByIpOrderByDateDesc(String hostId);


}
