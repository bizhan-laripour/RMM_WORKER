package com.rmm.worker.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ImperativeElasticsearchConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder()
                .connectedTo("127.0.0.1:9200")
                .build();
    }
}
