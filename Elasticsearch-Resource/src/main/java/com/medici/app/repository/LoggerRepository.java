package com.medici.app.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import com.medici.app.model.KafkaProducerLog;

@RepositoryRestResource(collectionResourceRel = "logs", path = "logs")
public interface LoggerRepository extends ElasticsearchRepository<KafkaProducerLog, String> {

}
