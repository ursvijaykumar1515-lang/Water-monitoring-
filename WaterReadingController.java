package com.example.waterquality.controller;

import com.example.waterquality.entity.WaterReading;
import com.example.waterquality.service.ReadingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/readings")
@CrossOrigin(origins = "http://localhost:5173")
public class WaterReadingController {
    private final ReadingService service;

    public WaterReadingController(ReadingService service) {
        this.service = service;
    }

    @GetMapping
    public List<WaterReading> all() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public WaterReading one(@PathVariable Long id) {
        return service.getById(id);
    }

    @PostMapping
    public ResponseEntity<WaterReading> create(@Valid @RequestBody WaterReading reading) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(reading));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok(Map.of("message", "Reading deleted successfully"));
    }

    @GetMapping("/dashboard")
    public Map<String, Object> dashboard() {
        return service.dashboard();
    }
}
