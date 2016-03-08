package co.uk.acme.query.statistics;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author psamatt
 */
public interface AllTimeTopPickersRepository extends ElasticsearchRepository<AllTimeTopPickers, String> {
}
