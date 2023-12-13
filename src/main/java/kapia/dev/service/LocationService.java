package kapia.dev.service;

import kapia.dev.model.Location;
import kapia.dev.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public List<Location> findAll() {
        return locationRepository.findAll();
    }

    public Location findById(Long locationId) {
        return locationRepository.findById(locationId).orElseThrow();
    }
}
