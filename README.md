# SkyRescue - Emergency Rescue Center System

## Descripcion del Proyecto
Sistema de gestion y coordinacion de emergencias con drones y operadores desarrollado en Java 21 utilizando Maven. El proyecto implementa desarrollo guiado por pruebas (TDD), medicion de cobertura de codigo con JaCoCo y analisis estatico de codigo con SonarQube.

---

## Prerrequisitos
- **Java Development Kit (JDK):** Version 21 o superior
- **Apache Maven:** Version 3.8+
- **Git:** Control de versiones
- **Docker / SonarQube:** Para analisis estatico de codigo

---

## Instrucciones de Compilacion y Pruebas

### Compilar el proyecto
```bash
mvn clean compile
```

### Ejecutar pruebas unitarias
```bash
mvn test
```

---

## Cobertura de Codigo con JaCoCo

El reporte HTML debe quedar disponible en:
`target/site/jacoco/index.html`

### Meta de cobertura
El proyecto debe alcanzar como minimo:
- **85% de cobertura de lineas**

---

## Evidencia de cobertura

### Primera ejecucion
![Cobertura inicial](docs/evidence/coverage-first.png)

### Cobertura final
![Cobertura final](docs/evidence/coverage-final.png)
