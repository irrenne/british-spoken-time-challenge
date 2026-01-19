# British Spoken Time Challenge

This project converts a given time (HH:MM) into its British English spoken form.

## Usage Examples

- Handles standard time expressions:
    - `00:00` -> "midnight"
    - `12:00` -> "noon"
    - `HH:00` -> "[hour] o'clock"
    - `HH:15` -> "quarter past [hour]"
    - `HH:30` -> "half past [hour]"
    - `HH:45` -> "quarter to [next hour]"
    - `HH:01-HH:29` -> "[minutes] past [hour]" (for multiples of 5)
    - `HH:31-HH:59` -> "[minutes] to [next hour]" (for multiples of 5)
    - Other times -> "[hour] [minutes]" or "[hour] oh [minutes]" for single digits

## Requirements

- Java 21+
- Maven

## Building and Running

### Build the project
```bash
mvn clean package
```

### Run tests
```bash
mvn test
```

### Run the application

```bash
mvn exec:java -Dexec.mainClass="org.challenge.Main"
```

## Run with Docker

```bash
docker build -t british-spoken-time .
docker run -it --rm british-spoken-time
```
