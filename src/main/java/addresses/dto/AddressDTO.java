package addresses.dto;

import java.util.List;

public class AddressDTO {

    private final List<AddressResult> results;

    public AddressDTO(
            List<AddressResult> results
    ) {
        this.results = results;
    }

    public List<AddressResult> getResults() {
        return results;
    }
}
