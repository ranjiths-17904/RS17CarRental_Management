
# 💻 Java Main Console Project

![Java](https://img.shields.io/badge/Language-Java-orange?style=for-the-badge&logo=java)
![VS Code](https://img.shields.io/badge/IDE-VS%20Code-blue?style=for-the-badge&logo=visualstudiocode)
![Status](https://img.shields.io/badge/Status-In%20Development-yellow?style=for-the-badge)
![License](https://img.shields.io/badge/License-MIT-green?style=for-the-badge)

---

## 📌 Overview

Welcome to the **Java Main Console Project** — a fully modular console-based application developed using the **MVC (Model-View-Controller)** design pattern. It demonstrates proper layering of code, clean separation of logic, and interaction with XML and database files.

---

## 📁 Project Structure


### 📁 Project Structure

```text
Java Main Console Project/
├── lib/                  # External libraries (MySQL Connector)
├── MainSrc/
│   ├── Controller/       # Controllers: Handle user actions
│   ├── Dao/              # Data access objects for DB communication
│   ├── Model/            # Data models
│   ├── View/             # User interface/console views
│   ├── Main.java         # Project entry point
│   └── Main.class        # Compiled output
├── resources/            # XML files and configs
├── pom.xml               # Project object model (for Maven, optional)
└── README.md             # Project documentation
```

---

## ⚙️ Setup Instructions

### 📥 Requirements

- Java JDK 8 or above  
- VS Code (or any Java-compatible IDE)  
- MySQL JDBC Driver (Place it in `lib/`)  
- XML file for configuration (placed in `resources/`)

---

### 🛠️ Compile the Project

In your VS Code terminal:


        >>> javac MainSrc/**/*.java


If only compiling the main file:

        >>> javac MainSrc/Main.java


---

### Query for Database and Table Creation:

```
CREATE DATABASE car_rental;

USE car_rental;

CREATE TABLE cars (
    id INT PRIMARY KEY,
    model VARCHAR(100),
    color VARCHAR(100),
    price_per_day DECIMAL(10, 2), -- ✅ added price per day
    available BOOLEAN DEFAULT true
);

-- Table for storing customer information
CREATE TABLE customers (
    customer_id VARCHAR(20) PRIMARY KEY,
    name VARCHAR(100),
    contact VARCHAR(15)
);

-- Table for tracking rental bookings
CREATE TABLE rentals (
    rental_id VARCHAR(20) PRIMARY KEY,
    car_id INT,
    customer_id VARCHAR(20),
    rental_days INT,
    total_amount DECIMAL(10, 2),
    rental_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (customer_id) REFERENCES customers(customer_id)
);

-- Table for tracking payment transactions
CREATE TABLE payments (
    payment_id VARCHAR(20) PRIMARY KEY,
    rental_id VARCHAR(20),
    amount DECIMAL(10, 2),
    method VARCHAR(50),
    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

-- Table for recording car returns
CREATE TABLE returns (
    return_id SERIAL PRIMARY KEY,
    rental_id VARCHAR(20),
    return_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    late_fee DECIMAL(10, 2),
    FOREIGN KEY (rental_id) REFERENCES rentals(rental_id)
);

```

---

### ▶️ Run the Project

Use the following command to run your project with the MySQL JDBC driver:


        >>> java -cp "MainSrc;lib/mysql-connector-java-x.x.xx.jar" Main


> ⚠️ Make sure to replace `x.x.xx` with the actual version of the connector `.jar` file.

---

## 📂 Where to Place XML Files?

All XML configuration or data files should be placed in the `resources/` folder.

**Example Path:**


resources/config.xml


In Java, load it like:


        File file = new File("resources/config.xml");


---

## 🔑 Key Features

- 📦 MVC Design Pattern  
- 🛢️ DAO Layer for Database Operations  
- 🧾 XML-based Configurable Data  
- 🧠 Clear Separation of Logic, Data, and View  
- 🎯 Easy to Maintain and Extend  

---

## 🧰 Technologies Used

| Technology        | Description                         |
|------------------|-------------------------------------|
| Java             | Core programming language           |
| MySQL Connector  | JDBC driver for database connection |
| XML              | Configuration and structured data   |
| VS Code          | Code editor used                    |

---

## 💬 Tips

- Always check class names and paths in your run command.  
- Don’t forget to add the `.jar` file to your classpath correctly.  
- Use `System.out.println()` for debugging logic flow in console apps.

---

## ✨ Example Output


===== Welcome to Project Console =====
1. Add Record
2. View Record
3. Update Record
4. Exit
Enter your choice:


---

## 📬 Contact

- **Developer:** Ranjith  
- **Status:** Active development  
- 📫 Feel free to fork or reach out for contributions!

---

## 📄 License

This project is licensed under the [MIT License](https://opensource.org/licenses/MIT).

---

⭐ *Thank you for checking out this project!*
