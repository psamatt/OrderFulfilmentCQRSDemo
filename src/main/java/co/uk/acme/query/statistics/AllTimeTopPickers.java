package co.uk.acme.query.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author psamatt
 */
@Document(indexName="alltime-top-pickers-index", type="alltime-top-pickers-type")
final public class AllTimeTopPickers {

    @Id
    private String id;
    private int count;

    public AllTimeTopPickers(@JsonProperty("id") String id) {
        this.id = id;
        this.count = 0;
    }

    public String getId() {
        return id;
    }

    public int getCount() {
        return this.count;
    }

    public void increment() {
        count++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AllTimeTopPickers other = (AllTimeTopPickers) o;

        if (count != other.count) return false;
        return !(id != null ? !id.equals(other.id) : other.id != null);
    }

    @Override
    public int hashCode() {
        int result = getId() != null ? getId().hashCode() : 0;
        result = 31 * result + getCount();
        return result;
    }
}
