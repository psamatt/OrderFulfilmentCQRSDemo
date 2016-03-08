package co.uk.acme.query.employees;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author psamatt
 */
interface EmployedStoreAssistantRepository extends ElasticsearchRepository<EmployedStoreAssistant, String> {
}
