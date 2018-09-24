# BookStoreSystem

### Purpose

This project implemented a book store system written by **Java Swing**. This is a side project to practice how to use `JFrame`, `JButton`, `JPanel`, `JTextField`, `JTextArea` & `JRadioButton` to implement a GUI program.

### Application architecture

This project implement a simple shopping model for clients and managers.

- For customers, `Client` and `Cart` classes were implemented for shopping and collecting items. 
  `ClientService` class handles the user's shopping actions and communicates with `ManagerService` class.

- For managers,  `Manager` and `Stock` classes were implemented for updating, adding, deleting, checking information of products.
  `ManagerService` class implemented the business logic for a shopping system. In addition, a login mechanism was established to avoid   from non-authorized manipulation.
