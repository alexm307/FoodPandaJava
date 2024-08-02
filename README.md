# FoodPanda

This project is a desktop application for managing orders, menu items, and generating reports for a catering company. The application uses serialization for storing products, client accounts, and orders. The project also includes a graphical user interface and employs various design patterns and Java features to enhance functionality and maintainability.

## Features

- **Administrator**:
  - Perform CRUD operations on menu items
  - Generate reports on previous orders
  - Receive notifications when a new order is created (using the Observer design pattern)

- **Regular User**:
  - View and search menu items based on categories such as price, calories, protein, etc.
  - Place orders by selecting desired items
  - Generate a .txt bill file for each order, containing order details and timestamp

## Secondary Objectives

- **JavaDoc Comments**:
  - Document classes with JavaDoc comments
  - Generate corresponding JavaDoc files
  - Implement Design by Contract pattern with preconditions, postconditions, and assert statements

- **Graphical User Interface (GUI)**:
  - Provide a user-friendly interface for menu browsing and order placement
  - Enable administrators to manage menu items and generate reports

- **Design Patterns**:
  - **Observer Design Pattern**: Notify employees of new orders
  - **Composite Design Pattern**: Implement `MenuItem`, `BaseProduct`, and `CompositeProduct` classes
  - **Streams and Lambda Expressions**: Generate administrator reports and filter menu items

- **Serialization**:
  - Store application data using serialization and deserialization in the `DeliveryService` class

## Technologies Used

- Java SE
- JavaFX (for GUI)
- Serialization
- Design Patterns (Observer, Composite)
- Java Streams and Lambda Expressions

## Installation

To set up the project locally, follow these steps:

### Prerequisites

- Java JDK 8 or higher
- A Java IDE (e.g., IntelliJ IDEA, Eclipse)
