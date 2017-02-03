package addresses.service;

import addresses.dto.AddressDTO;
import addresses.dto.AddressResult;
import addresses.dto.DetailedAddress;
import addresses.dto.SimpleAddress;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AddressFinderService {

    private static final String DETAILED_ADDRESS_URL_PREFIX = "https://www.alliescomputing.com/web_services/postcoder-web-v3.php?searchterm=";
    private static final String SIMPLE_ADDRESS_URL_PREFIX = "https://api.getaddress.io/v2/uk/";
    private static final String SIMPLE_ADDRESS_URL_AUTH = "?api-key=ymYHugGpakCmq_vIv2JFIQ7311";

    @Autowired
    private ObjectMapper objectMapper;

    public AddressDTO getAddresses(String postcode) throws IOException {
        List<AddressResult> addressResults;
        String encodedPostcode = postcode.replace(" ", "");
        String detailedAddressUrl = DETAILED_ADDRESS_URL_PREFIX + encodedPostcode;
        DetailedAddress detailedAddress = objectMapper.readValue(new URL(detailedAddressUrl), DetailedAddress.class);

        if (detailedAddress.isSuccess()) {
            addressResults = getDetailedAddressResults(detailedAddress);
        } else {
            String simpleAddressUrl = SIMPLE_ADDRESS_URL_PREFIX + encodedPostcode + SIMPLE_ADDRESS_URL_AUTH;
            SimpleAddress simpleAddress = objectMapper.readValue(new URL(simpleAddressUrl), SimpleAddress.class);
            addressResults = getSimpleAddressResults(simpleAddress);
        }

        return new AddressDTO(addressResults);
    }

    private List<AddressResult> getDetailedAddressResults(DetailedAddress detailedAddress) {
        return detailedAddress.getResult().stream()
                .collect(Collectors.toList());
    }

    private List<AddressResult> getSimpleAddressResults(SimpleAddress simpleAddress) {
        List<AddressResult> addressResults = new ArrayList<>();
        List<String> addresses = simpleAddress.getAddresses();
        for (String address : addresses) {
            List<String> addressSegments = getAddressSegments(address);
            if (addressSegments.size() == 4) {
                addressResults.add(getShorterAddressResult(addressSegments));
            } else if (addressSegments.size() == 5) {
                addressResults.add(getLongerAddressResult(addressSegments));
            }
        }
        return addressResults;
    }

    private List<String> getAddressSegments(String address) {
        List<String> segments = Arrays.asList(address.split(","));
        return segments.stream()
                .map(String::trim)
                .filter(s -> s.length() > 0)
                .collect(Collectors.toList());
    }

    private AddressResult getShorterAddressResult(List<String> addressSegments) {
        String addressline1 = addressSegments.get(0);
        String addressline2 = addressSegments.get(1);
        String posttown = addressSegments.get(2);
        String county = addressSegments.get(3);
        return new AddressResult(null, addressline1, addressline2, posttown, county);
    }

    private AddressResult getLongerAddressResult(List<String> addressSegments) {
        String organisation = addressSegments.get(0);
        String addressline1 = addressSegments.get(1);
        String addressline2 = addressSegments.get(2);
        String posttown = addressSegments.get(3);
        String county = addressSegments.get(4);
        return new AddressResult(organisation, addressline1, addressline2, posttown, county);
    }

}
