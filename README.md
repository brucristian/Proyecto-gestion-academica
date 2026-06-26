# UniScheduler — Sistema de Gestión de Matrículas Académicas

> Aplicación de escritorio en Java para la automatización del proceso de matriculas de materias en la Universidad de Cartagena.

---

## Tabla de Contenidos

- [Descripción General](#descripción-general)
- [Problema que Resuelve](#problema-que-resuelve)
- [Funcionalidades Principales](#funcionalidades-principales)
- [Arquitectura del Proyecto](#arquitectura-del-proyecto)
- [Modelo de Dominio](#modelo-de-dominio)
- [Tecnologías Utilizadas](#tecnologías-utilizadas)
- [Estructura del Proyecto](#estructura-del-proyecto)
- [Instalación y Ejecución](#instalación-y-ejecución)
- [Roles de Usuario](#roles-de-usuario)
- [Equipo de Desarrollo](#equipo-de-desarrollo)

---

## Descripción General

**UniScheduler** es una aplicación informática desarrollada en Java que automatiza el núcleo crítico del proceso de inscripción de matrículas académicas en la Universidad de Cartagena. Permite a los estudiantes seleccionar, validar y registrar sus asignaturas de manera eficiente, respaldado por un motor de validaciones automáticas que verifica cruces de horario y prerrequisitos en tiempo real.

---

## Problema que Resuelve

El proceso tradicional de inscripción de matrículas presentaba múltiples ineficiencias derivadas de la validación manual:

- Cada departamento publica sus horarios de manera aislada, obligando a los estudiantes a cruzar información visualmente.
- Los estudiantes debían verificar manualmente si cumplían con los prerrequisitos de cada materia.
- La detección de cruces de horario entre grupos seleccionados era completamente manual.

Esto generaba errores frecuentes, reprocesos administrativos y pérdida de cupos por demoras en la corrección de horarios rechazados. **UniScheduler** soluciona la ausencia de una plataforma integrada que valide estas reglas de negocio en tiempo real.

---

## Funcionalidades Principales

### Para Estudiantes
- Inicio de sesión y gestión de perfil
- Consulta del catálogo de cursos y grupos disponibles
- Inscripción de materias con validación automática en tiempo real:
  - Verificación de **prerrequisitos** cumplidos
  - Detección de **cruces de horario** entre grupos seleccionados
  - Control del **límite de créditos** por período
- Visualización del horario semanal inscrito
- Consulta del historial académico

### Para Administradores
- Gestión del catálogo académico (cursos, períodos, programas, prerrequisitos)
- Programación académica (grupos, horarios, docentes)
- Gestión de estudiantes y matrículas
- Gestión de historial académico
- Administración de plantillas de semestre

---

## Arquitectura del Proyecto

El proyecto sigue una arquitectura **Hexagonal (Ports & Adapters)** combinada con principios de **Domain-Driven Design (DDD)**, separando claramente las responsabilidades en tres capas:

```
┌─────────────────────────────────────────────────┐
│                    UI (JavaFX)                  │
│         Controllers · ViewModels · FXML         │
└────────────────────┬────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────┐
│              Application Layer                  │
│    Use Cases · Commands · Services · DTOs       │
└────────────────────┬────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────┐
│                Domain Layer                     │
│       Entities · Value Objects · Ports          │
└────────────────────┬────────────────────────────┘
                     │
┌────────────────────▼────────────────────────────┐
│            Infrastructure Layer                 │
│   Excel Repositories · Mappers · Adapters       │
└─────────────────────────────────────────────────┘
```

### Módulos del Sistema

| Módulo | Responsabilidad |
|--------|----------------|
| `auth` | Autenticación, registro y gestión de usuarios |
| `academic_catalog` | Cursos, períodos académicos, programas y prerrequisitos |
| `academic_programming` | Grupos, horarios de grupo, docentes y plantillas de semestre |
| `enrollment` | Matrículas, detalles de inscripción y gestión de estudiantes |
| `academic_history` | Historial académico y estado de cursos completados |

---

## Modelo de Dominio

El sistema modela las siguientes entidades principales y sus relaciones:

- **User** → autenticación con `Email` (Value Object) y `EncodedPassword`
- **Student** → extiende el modelo de usuario con información académica y programa
- **Enrollment** → agrupa `EnrollmentDetail` vinculando al estudiante con grupos específicos
- **Course** → pertenece a un `AcademicProgram` y puede tener `Prerequisite`s
- **Group** → instancia de un curso en un período, con `GroupSchedule` (horarios por día) y `Teacher`
- **AcademicPeriod** → período académico con estado (`ACTIVE`, etc.)
- **AcademicProgram** → programa académico al que pertenecen los cursos
- **SemesterTemplate** → plantilla reutilizable de grupos por programa académico
- **AcademicHistory** → registro histórico de cursos cursados por el estudiante

### Enumeraciones del Dominio

| Enum | Valores |
|------|---------|
| `Role` | `ADMIN`, `STUDENT` |
| `DocumentType` | Tipos de documento de identidad |
| `Gender` | Géneros |
| `Status` | Estado del usuario |
| `EnrollmentStatus` | Estados de la matrícula |
| `AcademicPeriodStatus` | Estados del período académico |
| `AcademicHistoryCourseStatus` | Estado de un curso en el historial |
| `WeekDays` | Días de la semana para horarios |

---

## Tecnologías Utilizadas

| Tecnología | Uso |
|-----------|-----|
| Java | Lenguaje principal |
| JavaFX + FXML | Interfaz gráfica de usuario |
| Apache POI (Excel) | Capa de persistencia (archivos `.xlsx`) |
| Maven | Gestión de dependencias y construcción |
| CSS | Estilos de la interfaz gráfica |

> La persistencia se implementa sobre archivos Excel (`.xlsx`), sin base de datos relacional, lo que simplifica el despliegue de la aplicación.

---

## Estructura del Proyecto

```
src/main/java/org/unischeduler/
├── backend/
│   ├── application/service/     # Casos de uso y servicios de aplicación
│   ├── domain/                  # Entidades, VOs, enums y puertos (interfaces)
│   └── infrastructure/          # Repositorios Excel, mappers y adaptadores
└── ui/
    ├── app/                     # Bootstrap de la aplicación JavaFX
    ├── components/              # Navbar, sidebar, eventos de horario
    ├── layouts/                 # Layout principal
    ├── pages/                   # Controladores de cada vista
    ├── service/                 # Servicios de UI
    └── viewmodel/               # ViewModels para binding con la interfaz

src/main/resources/
├── css/                         # Hojas de estilos globales y por módulo
└── ui/fxml/                     # Archivos FXML de vistas y componentes
```

---

## Instalación y Ejecución

### Prerrequisitos

- Java 17 o superior
- Maven 3.8+

### Pasos

```bash
# 1. Clonar el repositorio
git clone <url-del-repositorio>
cd Proyecto-de-POO---Aplicacion-de-Gestion-Academica

# 2. Compilar el proyecto
mvn clean install

# 3. Ejecutar la aplicación
mvn javafx:run
```

> Los datos iniciales se cargan automáticamente desde los seeders (`CourseSeeder`, `StudentSeeder`, `GroupSeeder`, etc.) al primer arranque.

---

## Roles de Usuario

### Administrador
Accede a todas las funciones de gestión del sistema: períodos académicos, cursos, grupos, docentes, estudiantes y matrículas.

### Estudiante
Accede a la consulta de su horario, inscripción de materias con validaciones automáticas, y consulta de su historial académico.

---

## Motor de Validaciones

El sistema implementa las siguientes validaciones automáticas durante el proceso de inscripción:

- **`ValidatePrerequisiteService`** — verifica que el estudiante haya aprobado los prerrequisitos de cada curso a inscribir.
- **`ValidateScheduleConflictsService`** — detecta cruces de horario entre los grupos seleccionados.
- **`ValidateCreditLimitService`** — controla que el número de créditos inscritos no supere el máximo permitido por período.

---

---

## Equipo de Desarrollo

Proyecto desarrollado como parte del curso de **Programación Orientada a Objetos** — Universidad de Cartagena, 2026.

| Nombre | Rol |
|--------|-----|
| 👨‍💻 Cristian Bru Cano | Desarrollador |
| 👨‍💻 Joan Vergara | Desarrollador |
| 👨‍💻 Armando Escobar | Desarrollador |
| 👨‍💻 Miguel González | Desarrollador |

---

<div align="center">
  <sub>Hecho con ☕ y mucho Java · Universidad de Cartagena · 2026</sub>
</div>
