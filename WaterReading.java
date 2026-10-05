package com.example.waterquality.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "water_readings")
public class WaterReading {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Location is required")
    @Size(max = 120, message = "Location must be at most 120 characters")
    private String location;

    @NotNull(message = "pH is required")
    @DecimalMin(value = "0.0", message = "pH cannot be negative")
    @DecimalMax(value = "14.0", message = "pH cannot exceed 14")
    private Double ph;

    @NotNull(message = "Turbidity is required")
    @DecimalMin(value = "0.0", message = "Turbidity cannot be negative")
    private Double turbidity;

    @NotNull(message = "Temperature is required")
    @DecimalMin(value = "-50.0")
    @DecimalMax(value = "100.0")
    private Double temperature;

    @NotNull(message = "TDS is required")
    @DecimalMin(value = "0.0", message = "TDS cannot be negative")
    private Double tds;

    @Size(max = 500, message = "Notes must be at most 500 characters")
    private String notes;

    @Column(nullable = false)
    private String qualityStatus;

    @Column(nullable = false)
    private Boolean alert;

    @Column(nullable = false)
    private LocalDateTime recordedAt;

    public WaterReading() {}

    public Long getId() { return id; }
    public String getLocation() { return location; }
    public Double getPh() { return ph; }
    public Double getTurbidity() { return turbidity; }
    public Double getTemperature() { return temperature; }
    public Double getTds() { return tds; }
    public String getNotes() { return notes; }
    public String getQualityStatus() { return qualityStatus; }
    public Boolean getAlert() { return alert; }
    public LocalDateTime getRecordedAt() { return recordedAt; }

    public void setId(Long id) { this.id = id; }
    public void setLocation(String location) { this.location = location; }
    public void setPh(Double ph) { this.ph = ph; }
    public void setTurbidity(Double turbidity) { this.turbidity = turbidity; }
    public void setTemperature(Double temperature) { this.temperature = temperature; }
    public void setTds(Double tds) { this.tds = tds; }
    public void setNotes(String notes) { this.notes = notes; }
    public void setQualityStatus(String qualityStatus) { this.qualityStatus = qualityStatus; }
    public void setAlert(Boolean alert) { this.alert = alert; }
    public void setRecordedAt(LocalDateTime recordedAt) { this.recordedAt = recordedAt; }
}
