# address-finder

This is a simple spring boot application that finds addresses given a postcode.

# running the application

Simply start the app (main method is in the addresses.AddressFinderApplication class) and visit 
http://localhost:8080/addresses?postcode=[postcode] You must enter the desired postcode as a path variable.

# results

The application will print details of the addresses for the postcode you provide. The first 20 or so calls will return more
detailed results as it uses a demo endpoint of a premium API service. After those calls have been used up, the app will 
fall back to a free API that returns less detailed results, and is rate limited at 20 requests every 24 hours.
