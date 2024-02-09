package kapia.dev.controller;

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

    private final CountryService countryService;

    @Autowired
    private CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public ResponseEntity<List<Country>> findAll() {
        return new ResponseEntity<>(countryService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Country> addCountry(@RequestBody Country country) {
        return new ResponseEntity<>(countryService.addCountry(country), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/id/{country_id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable(value = "country_id") Long countryId) {
        return new ResponseEntity<>(countryService.deleteCountry(countryId), HttpStatus.OK);
    }

}
