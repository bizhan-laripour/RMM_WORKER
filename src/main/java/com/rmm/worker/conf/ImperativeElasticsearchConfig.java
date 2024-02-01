package com.rmm.worker.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;

@Configuration
public class ImperativeElasticsearchConfig extends ElasticsearchConfiguration {

    private final Environment environment;

    public ImperativeElasticsearchConfig(Environment environment) {
        this.environment = environment;
    }

    @Override
    public ClientConfiguration clientConfiguration() {

        return ClientConfiguration.builder()
                .connectedTo(environment.getProperty("elastic.server.port"))
                .build();
    }
}
