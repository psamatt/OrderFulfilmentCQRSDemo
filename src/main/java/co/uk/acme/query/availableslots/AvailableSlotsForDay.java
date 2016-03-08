package co.uk.acme.query.availableslots;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author psamatt
 */
@Document(indexName="available-slots-for-day-index", type="available-slots-for-day-type")
final public class AvailableSlotsForDay {

    @Id
    private final String id;
    private final String day;
    private int capacity;

    public AvailableSlotsForDay(@JsonProperty("id") String id, @JsonProperty("day") String day, @JsonProperty("capacity") int capacity) {
        this.id = id;
        this.day = day;
        this.capacity = capacity;
    }

    public void decrementCapacity() {
        capacity--;
    }

    public void incrementCapacity() {
        capacity++;
    }

    public String getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public int getCapacity() {
        return capacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AvailableSlotsForDay that = (AvailableSlotsForDay) o;

        if (capacity != that.capacity) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return !(day != null ? !day.equals(that.day) : that.day != null);
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (day != null ? day.hashCode() : 0);
        result = 31 * result + capacity;
        return result;
    }
}
