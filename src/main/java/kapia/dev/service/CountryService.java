package kapia.dev.service;

import kapia.dev.model.Country;
import kapia.dev.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    private CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAll() {
        return countryRepository.findAll();
    }

    public Country addCountry(Country country) {
        return countryRepository.save(country);
    }

    public Void deleteCountry(Long countryId) {
        Country country = countryRepository.findById(countryId).orElseThrow(() -> new RuntimeException("Country not found with id: " + countryId));
        countryRepository.delete(country);
        return null;
    }
}
