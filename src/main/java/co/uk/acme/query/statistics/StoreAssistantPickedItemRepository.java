package co.uk.acme.query.statistics;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author psamatt
 */
interface StoreAssistantPickedItemRepository extends ElasticsearchRepository<StoreAssistantPickedItem, String> {
}