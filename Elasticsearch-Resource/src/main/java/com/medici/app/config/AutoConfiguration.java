package com.medici.app.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@Configuration
@EnableAutoConfiguration
@EnableElasticsearchRepositories(basePackages = "com.medici.app.repository")
@ComponentScan(basePackages = { "com.medici.app" })
public class AutoConfiguration {

//	@Value("${elasticsearch.host}")
//	private String esHost;
//
//	@Value("${elasticsearch.port}")
//	private int esPort;
//
//	@Value("${elasticsearch.cluster.name:elasticsearch}")
//	private String clusterName;
//
//	@Bean
//	public RestHighLevelClient client() throws Exception {
//		RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(new HttpHost(esHost, esPort)));
//		return client;
//	}

}
