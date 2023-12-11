package kapia.dev.controller;

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
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping("/all")
    public ResponseEntity<List<Region>> findAll() {
        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }


}
