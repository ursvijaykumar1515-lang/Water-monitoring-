package com.example.waterquality.repository;

import com.example.waterquality.entity.WaterReading;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface WaterReadingRepository extends JpaRepository<WaterReading, Long> {
    List<WaterReading> findAllByOrderByRecordedAtDesc();
    long countByAlertTrue();
}
