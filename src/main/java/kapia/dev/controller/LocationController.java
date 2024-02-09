package kapia.dev.controller;

import kapia.dev.model.Location;
import kapia.dev.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    private LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public ResponseEntity<List<Location>> findAll() {
        return new ResponseEntity<>(locationService.findAll(), HttpStatus.OK);
    }

    @PostMapping(consumes = "application/json")
    public ResponseEntity<Location> addLocation(@RequestBody Location location) {
        return new ResponseEntity<>(locationService.addLocation(location), HttpStatus.CREATED);
    }
}
