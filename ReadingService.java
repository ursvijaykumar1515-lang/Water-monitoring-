package com.example.waterquality.service;

import com.example.waterquality.entity.WaterReading;
import com.example.waterquality.repository.WaterReadingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReadingService {
    private final WaterReadingRepository repository;

    public ReadingService(WaterReadingRepository repository) {
        this.repository = repository;
    }

    public List<WaterReading> getAll() {
        return repository.findAllByOrderByRecordedAtDesc();
    }

    public WaterReading getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reading not found"));
    }

    public WaterReading create(WaterReading reading) {
        reading.setRecordedAt(LocalDateTime.now());
        boolean alert = isAlert(reading);
        reading.setAlert(alert);
        reading.setQualityStatus(alert ? "WARNING" : "GOOD");
        if (isCritical(reading)) {
            reading.setQualityStatus("CRITICAL");
        }
        return repository.save(reading);
    }

    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reading not found");
        }
        repository.deleteById(id);
    }

    public Map<String, Object> dashboard() {
        List<WaterReading> all = getAll();
        long alerts = repository.countByAlertTrue();
        long good = all.stream().filter(r -> "GOOD".equals(r.getQualityStatus())).count();
        long critical = all.stream().filter(r -> "CRITICAL".equals(r.getQualityStatus())).count();

        Map<String, Object> data = new LinkedHashMap<>();
        data.put("totalReadings", all.size());
        data.put("alerts", alerts);
        data.put("goodReadings", good);
        data.put("criticalReadings", critical);
        data.put("thresholds", Map.of(
                "phMin", 6.5, "phMax", 8.5,
                "turbidityMax", 5.0,
                "temperatureMax", 35.0,
                "tdsMax", 500.0
        ));
        return data;
    }

    private boolean isAlert(WaterReading r) {
        return r.getPh() < 6.5 || r.getPh() > 8.5
                || r.getTurbidity() > 5
                || r.getTemperature() > 35
                || r.getTds() > 500;
    }

    private boolean isCritical(WaterReading r) {
        return r.getPh() < 5.5 || r.getPh() > 9.5
                || r.getTurbidity() > 10
                || r.getTds() > 1000;
    }
}
