package kapia.dev.controller;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import kapia.dev.model.Country;
import kapia.dev.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Hidden
    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @Hidden
    @PostMapping(consumes = "application/json")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a country")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Country deleted"),
            @ApiResponse(responseCode = "404", description = "Country not found")})
    @DeleteMapping(value = "/id/{country_id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable(value = "country_id") Long countryId) {
        return new ResponseEntity<>(countryService.deleteCountry(countryId), HttpStatus.OK);
    }

}
