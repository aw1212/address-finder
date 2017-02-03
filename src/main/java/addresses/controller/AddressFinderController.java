package addresses.controller;

import java.io.IOException;

import addresses.dto.AddressDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import addresses.service.AddressFinderService;

@Controller
@RequestMapping("/addresses")
public class AddressFinderController {

    @Autowired
    private AddressFinderService addressFinderService;

    @RequestMapping(method= RequestMethod.GET)
    public @ResponseBody
    AddressDTO getAddresses(@RequestParam(value = "postcode") String postcode) throws IOException {
        return addressFinderService.getAddresses(postcode);
    }
}
