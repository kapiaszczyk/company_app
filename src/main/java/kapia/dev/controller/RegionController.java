package kapia.dev.controller;

import io.swagger.v3.oas.annotations.Hidden;
import kapia.dev.model.Region;
import kapia.dev.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/regions")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    private RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @GetMapping
    @Hidden
    public ResponseEntity<List<Region>> findAll() {
        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }


}
