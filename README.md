# Daily Water Quality Monitoring System

Full-stack PBL project based on the requirements shown in the provided document.

## Stack
- Backend: Java 17, Spring Boot, Spring Web, Spring Data JPA, MySQL
- Frontend: React + Vite
- REST API between React and Spring Boot
- Responsive dashboard with validation, loading, success/error states
- Threshold-based alerts for pH, turbidity, temperature and TDS

## Features
- Add water-quality readings
- View all readings
- Dashboard summary
- Automatic quality status: GOOD / WARNING / CRITICAL
- Alerts when configured thresholds are exceeded
- Delete readings
- REST validation and meaningful HTTP responses
- MySQL persistence through JPA/Hibernate

## Database setup
Create a database:
```sql
CREATE DATABASE water_quality_db;
```

Edit `backend/src/main/resources/application.properties` if your MySQL username/password differs.

## Run backend
```bash
cd backend
mvn spring-boot:run
```
API: `http://localhost:8080/api/readings`

## Run frontend
```bash
cd frontend
npm install
npm run dev
```
Open the URL shown by Vite, normally `http://localhost:5173`.

## API endpoints
- `GET /api/readings`
- `GET /api/readings/{id}`
- `POST /api/readings`
- `DELETE /api/readings/{id}`
- `GET /api/readings/dashboard`

Example POST:
```json
{
  "location": "Itugullapadu",
  "ph": 7.2,
  "turbidity": 2.4,
  "temperature": 26.5,
  "tds": 320,
  "notes": "Morning sample"
}
```

## Thresholds
- pH safe range: 6.5–8.5
- Turbidity warning above: 5 NTU
- Temperature warning above: 35 °C
- TDS warning above: 500 mg/L

These values are kept in `ReadingService` and can be changed easily.

## Suggested project flow
React form/dashboard → Spring Boot Controller → Service/Business Logic → JPA Repository → MySQL → JSON → React UI.
