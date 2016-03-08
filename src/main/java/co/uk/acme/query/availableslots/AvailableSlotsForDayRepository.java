package co.uk.acme.query.availableslots;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author psamatt
 */
public interface AvailableSlotsForDayRepository extends ElasticsearchRepository<AvailableSlotsForDay, String> {
}
