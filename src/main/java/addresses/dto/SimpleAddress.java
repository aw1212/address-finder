package addresses.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SimpleAddress {
    private final double latitude;
    private final double longitude;
    private final List<String> addresses;

    @JsonCreator
    public SimpleAddress(
            @JsonProperty("Latitude") double latitude,
            @JsonProperty("Longitude") double longitude,
            @JsonProperty("Addresses") List<String> addresses
    ) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.addresses = addresses;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public List<String> getAddresses() {
        return addresses;
    }
}
