package co.uk.acme.query.employees;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author psamatt
 */
@Document(indexName="employed-store-assistant-index", type="employed-store-assistant-type")
final public class EmployedStoreAssistant {

    @Id
    private String id;
    private String name;

    public EmployedStoreAssistant(@JsonProperty("id") String id, @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EmployedStoreAssistant that = (EmployedStoreAssistant) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(name != null ? !name.equals(that.name) : that.name != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
