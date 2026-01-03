# Train Management System 🚂

A comprehensive train company management system built in Java that contains two main modules: one for managing trains and their components, and another for ticket sales.

## ✨ Features

- 🔹 **Train Management**: Complete CRUD operations for commercial and cargo trains
- 🔹 **Wagon Management**: Handle both commercial and cargo wagons with detailed specifications
- 🔹 **Route Management**: Create and manage train routes with origin and destination
- 🔹 **Engineer Management**: Manage train engineers and their assignments
- 🔹 **Ticket Sales System**: Automated ticket purchasing system for passengers
- 🔹 **User Management**: Handle user accounts and authentication
- 🔹 **JSON Persistence**: Data storage using JSON files for easy management

## 🛠️ Technologies Used

| Technology | Description |
|------------|-------------|
| Java | Main programming language |
| JSON | Data persistence format |
| Maven | Dependency management and build tool |
| json-20240303.jar | JSON library for Java |

## 📁 Project Structure
```
SistemaGestionTrenes/
│
├── src/main/java/org/example/
│   ├── models/              # Entity classes
│   ├── services/            # Business logic
│   ├── controllers/         # Application controllers
│   └── utils/               # Utility classes
│
├── maquinistas.json         # Engineers data
├── rutas.json               # Routes data
├── trenesComerciales.json   # Commercial trains data
├── trenesDeCarga.json       # Cargo trains data
├── usuarios.json            # Users data
├── vagonesComerciales.json  # Commercial wagons data
├── vagonesDeCarga.json      # Cargo wagons data
└── pom.xml                  # Maven configuration
```

## 🚀 Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Maven 3.6 or higher

### Installation

1. Clone the repository:
```bash
git clone https://github.com/rivatoDev/SistemaGestionTrenes.git
cd SistemaGestionTrenes
```

2. Build the project with Maven:
```bash
mvn clean install
```

3. Run the application:
```bash
mvn exec:java
```

## 📊 System Modules

### 1. Train Management Module
- Create, read, update, and delete commercial trains
- Create, read, update, and delete cargo trains
- Assign wagons to trains
- Manage train specifications and capacities

### 2. Ticket Sales Module
- User registration and authentication
- Route browsing and selection
- Ticket purchasing system
- Purchase history tracking

### 3. Route Management
- Define routes with origin and destination
- Assign trains to specific routes
- Manage route schedules

### 4. Engineer Management
- Register train engineers
- Assign engineers to trains
- Track engineer information and availability

## 💾 Data Persistence

The system uses JSON files for data persistence:

- **maquinistas.json**: Stores engineer information
- **rutas.json**: Contains all available routes
- **trenesComerciales.json**: Commercial train data
- **trenesDeCarga.json**: Cargo train data
- **usuarios.json**: User accounts and credentials
- **vagonesComerciales.json**: Commercial wagon specifications
- **vagonesDeCarga.json**: Cargo wagon specifications

## 👥 Authors

- [@rivatoDev](https://github.com/rivatoDev)
- [@JoaquinOrtegaxyz](https://github.com/JoaquinOrtegaxyz)
- [@ianwesen](https://github.com/ianwesen)

## 📝 License

This project is available for educational purposes.

---

Questions or suggestions? Feel free to open an issue or submit a pull request!
