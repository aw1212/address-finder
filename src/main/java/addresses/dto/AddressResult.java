package addresses.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResult {

    private final String organization;
    private final String addressLine1;
    private final String addressLine2;
    private final String city;
    private final String county;

    @JsonCreator
    public AddressResult(
            @JsonProperty("organization") String organization,
            @JsonProperty("addressLine1") String addressLine1,
            @JsonProperty("addressLine2") String addressLine2,
            @JsonProperty("city") String city,
            @JsonProperty("county") String county
    ) {
        this.organization = organization;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.county = county;
    }

    public String getOrganization() {
        return organization;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public String getCity() {
        return city;
    }

    public String getCounty() {
        return county;
    }
}
