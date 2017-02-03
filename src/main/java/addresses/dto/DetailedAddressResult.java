package addresses.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class DetailedAddressResult extends AddressResult {

    private final String buildingname;
    private final String subbuildingname;
    private final String number;
    private final String postcode;
    private final String premise;
    private final String street;
    private final String summaryline;

    @JsonCreator
    public DetailedAddressResult(
            @JsonProperty("addressline1") String addressline1,
            @JsonProperty("addressline2") String addressline2,
            @JsonProperty("buildingname") String buildingname,
            @JsonProperty("subbuildingname") String subbuildingname,
            @JsonProperty("county") String county,
            @JsonProperty("number") String number,
            @JsonProperty("organisation") String organisation,
            @JsonProperty("postcode") String postcode,
            @JsonProperty("posttown") String posttown,
            @JsonProperty("premise") String premise,
            @JsonProperty("street") String street,
            @JsonProperty("summaryline") String summaryline
    ) {
        super(organisation, addressline1, addressline2, posttown, county);
        this.buildingname = buildingname;
        this.subbuildingname = subbuildingname;
        this.number = number;
        this.postcode = postcode;
        this.premise = premise;
        this.street = street;
        this.summaryline = summaryline;
    }

    public String getBuildingname() {
        return buildingname;
    }

    public String getSubbuildingname() {
        return subbuildingname;
    }

    public String getNumber() {
        return number;
    }

    public String getPostcode() {
        return postcode;
    }

    public String getPremise() {
        return premise;
    }

    public String getStreet() {
        return street;
    }

    public String getSummaryline() {
        return summaryline;
    }

}
