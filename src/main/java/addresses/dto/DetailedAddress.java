package addresses.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DetailedAddress {
    private final int remaining;
    private final List<DetailedAddressResult> result;
    private final boolean success;

    @JsonCreator
    public DetailedAddress(
        @JsonProperty("remaining") int remaining,
        @JsonProperty("result") List<DetailedAddressResult> result,
        @JsonProperty("success") boolean success
    ) {
        this.remaining = remaining;
        this.result = result;
        this.success = success;
    }

    public int getRemaining() {
        return remaining;
    }

    public List<DetailedAddressResult> getResult() {
        return result;
    }

    public boolean isSuccess() {
        return success;
    }
}
