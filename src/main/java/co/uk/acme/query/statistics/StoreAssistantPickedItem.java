package co.uk.acme.query.statistics;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author psamatt
 */
@Document(indexName="astore-assistant-picked-item-index", type="astore-assistant-picked-item-type")
final public class StoreAssistantPickedItem {

    @Id
    private final String id;
    private final String sku;
    private final String storeAssistantId;
    private final String storeAssistantName;

    public StoreAssistantPickedItem(@JsonProperty("id") String id, @JsonProperty("sku") String sku, @JsonProperty("storeAssistantId") String storeAssistantId, @JsonProperty("storeAssistantName") String storeAssistantName) {
        this.id = id;
        this.sku = sku;
        this.storeAssistantId = storeAssistantId;
        this.storeAssistantName = storeAssistantName;
    }

    public String getId() {
        return id;
    }

    public String getSku() {
        return sku;
    }

    public String getStoreAssistantId() {
        return storeAssistantId;
    }

    public String getStoreAssistantName() {
        return storeAssistantName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        StoreAssistantPickedItem that = (StoreAssistantPickedItem) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sku != null ? !sku.equals(that.sku) : that.sku != null) return false;
        if (storeAssistantId != null ? !storeAssistantId.equals(that.storeAssistantId) : that.storeAssistantId != null)
            return false;
        return !(storeAssistantName != null ? !storeAssistantName.equals(that.storeAssistantName) : that.storeAssistantName != null);

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (sku != null ? sku.hashCode() : 0);
        result = 31 * result + (storeAssistantId != null ? storeAssistantId.hashCode() : 0);
        result = 31 * result + (storeAssistantName != null ? storeAssistantName.hashCode() : 0);
        return result;
    }
}
