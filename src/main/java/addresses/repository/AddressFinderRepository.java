package addresses.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import addresses.dto.AddressResult;

@Repository
public class AddressFinderRepository {

    @Autowired
    private NamedParameterJdbcTemplate njt;

    private static final String INSERT_ADDRESS_SQL = "INSERT INTO addresses " +
            "(organization, address_line_1, address_line_2, city, county) " +
            "VALUES (:organization, :addressLine1, :addressLine2, :city, :county)";

    public void saveAddress(AddressResult addressResult) {
        SqlParameterSource parameters = new MapSqlParameterSource()
                .addValue("organization", addressResult.getOrganization())
                .addValue("addressLine1", addressResult.getAddressLine1())
                .addValue("addressLine2", addressResult.getAddressLine2())
                .addValue("city", addressResult.getCity())
                .addValue("county", addressResult.getCounty());
        njt.update(INSERT_ADDRESS_SQL, parameters);
    }

}
