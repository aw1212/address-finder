package addresses.service;

import addresses.dto.AddressDTO;
import addresses.dto.AddressResult;
import addresses.dto.DetailedAddress;
import addresses.dto.DetailedAddressResult;
import addresses.dto.SimpleAddress;
import addresses.repository.AddressFinderRepository;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AddressFinderServiceTest {

    private DetailedAddress detailedAddress;

    @Mock
    private ObjectMapper objectMapper;
    @Mock
    private AddressFinderRepository addressFinderRepository;
    @InjectMocks
    private AddressFinderService addressFinderService = new AddressFinderService();

    @Test
    public void givenSuccessFullDetailedAddressCall_whenGettingAddress_thenDetailedAddressResultReturned() throws IOException {
        List<DetailedAddressResult> detailedAddressResults = new ArrayList<>();
        detailedAddressResults.add(createDetailedAddressResult());
        detailedAddress = new DetailedAddress(10, detailedAddressResults, true);

        when(objectMapper.readValue(any(URL.class), any(Class.class))).thenReturn(detailedAddress);

        AddressDTO addressDTO = addressFinderService.getAddresses("W6 0LG");
        List<AddressResult> addressResults = addressDTO.getResults();

        assertEquals(1, addressResults.size());
        assertEquals("4th Floor", addressResults.get(0).getAddressLine1());
        assertTrue(addressResults.get(0) instanceof DetailedAddressResult);
    }

    @Test
    public void givenUnsuccessfulDetailedAddressCall_whenGettingAddress_thenAddressResultReturned() throws IOException {
        detailedAddress = new DetailedAddress(0, new ArrayList<>(), false);

        List<String> addresses = new ArrayList<>();
        addresses.add("Loveholidayscom, The Triangle, 5-17 Hammersmith Grove, , , London, Greater London");
        SimpleAddress simpleAddress = new SimpleAddress(10.10, 20.20, addresses);

        when(objectMapper.readValue(any(URL.class), any(Class.class)))
                .thenReturn(detailedAddress)
                .thenReturn(simpleAddress);

        AddressDTO addressDTO = addressFinderService.getAddresses("W6 0LG");
        List<AddressResult> addressResults = addressDTO.getResults();

        assertEquals(1, addressResults.size());
        assertEquals("The Triangle", addressResults.get(0).getAddressLine1());
    }

    private DetailedAddressResult createDetailedAddressResult() {
        String addressline1 = "4th Floor";
        String addressline2 = "123 Main Street";
        String buildingname = "The Triangle";
        String subbuildingname = null;
        String county = "Greater London";
        String number = "5-17";
        String organisation = "Loveholidayscom";
        String postcode = "W6 0LG";
        String posttown = "London";
        String premise = "The Triangle, 5-17";
        String street = "Hammersmith Grove";
        String summaryline = "Loveholidayscom, The Triangle, 5-17 Hammersmith Grove, London, Greater London, W6 0LG";
        return new DetailedAddressResult(addressline1, addressline2, buildingname, subbuildingname, county, number,
                organisation, postcode, posttown, premise, street, summaryline);
    }

    @Test
    public void bla() {
        String one = "this, is, a, , , , String";
        String two = "here, is, , , , , another";

        List<String> strings = new ArrayList<>();
        strings.add(one);
        strings.add(two);

        List<String> bla = strings.stream()
                .map(a -> a.split(","))
                .flatMap(s -> Arrays.stream(s))
                .filter(s -> s.trim().length() > 0)
                .collect(Collectors.toList());
        System.out.println(bla.size());

        //List<DetailedAddressResult> detailedAddress = objectMapper.readValue(
        //        new URL("http://ws.postcoder.com/pcw/PCWV4-B8PJN-2Z68D-KPN4J/address/uk/BN14EP?format=json"),
        //        new TypeReference<List<DetailedAddressResult>>(){});
        //for (DetailedAddressResult detailedAddressResult : detailedAddress) {
        //    addressResults.add(detailedAddressResult);
        //}
    }
}
