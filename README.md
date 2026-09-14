# SkyRescue - Emergency Rescue Center System

## Descripción del Proyecto
Sistema de gestión y coordinación de emergencias con drones y operadores desarrollado en Java 21 utilizando Maven. El proyecto implementa desarrollo guiado por pruebas (TDD), medición de cobertura de código con JaCoCo y análisis estático de código con SonarQube.

---

## Prerrequisitos
- **Java Development Kit (JDK):** Versión 21 o superior
- **Apache Maven:** Versión 3.8+
- **Git:** Control de versiones
- **Docker / SonarQube:** Para análisis estático de código

---

## Instrucciones de Compilación y Pruebas

### Compilar el proyecto
```bash
mvn clean compile

### Ejecutar pruebas unitarias
```bash
mvn test
``a

---

## Cobertura de Cédigo con JaCoCo

El reporte HTML debe quedar disponible en:
`target/site/jacoco/index.html`

### Meta de cobertura
El proyecto debe alcanzar como ménimo:
- *85% de cobertura de léneas*

---

## Evidencia de cobertura

### Primera ejecucién
!%lCobertura inicial](docs/evidence/coverage-first.png)

### Cobertura final
![Cobertura final](docs/evidence/coverage-final.png)
