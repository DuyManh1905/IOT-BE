package com.example.demo.restcontroller;

import com.example.demo.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/data")
@CrossOrigin(origins = "*")
public class DataRestController {
    private final DataService dataService;

    @Autowired
    public DataRestController(DataService dataService)
    {
        this.dataService = dataService;
    }
    
    @GetMapping("")
    public ResponseEntity<Object> get7LastestResult()
    {
        return ResponseEntity.ok(this.dataService.get7LastestResult());
    }
    
    @GetMapping("/all")
    public ResponseEntity<Object> getAlltResult()
    {
        return ResponseEntity.ok(this.dataService.getAllResult());
    }
    
    
}
