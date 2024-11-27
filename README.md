CONNECT WITH DataBase MYSQL

CREATE TABLE Person (
    IdPerson INT AUTO_INCREMENT PRIMARY KEY,
    Name VARCHAR(50) NOT NULL,
    Surname VARCHAR(50) NOT NULL
);

CREATE TABLE Card (
    IdCard INT AUTO_INCREMENT PRIMARY KEY,
    Money DECIMAL(10, 2) NOT NULL,
    Password VARCHAR(255) NOT NULL,
    IdPerson INT,
    FOREIGN KEY (IdPerson) REFERENCES Person(IdPerson) ON DELETE CASCADE
)

INSERT INTO Person (Name, Surname)
VALUES
('Jan', 'Kowalski'),
('Anna', 'Nowak'),
('Piotr', 'Wiśniewski'),
('Maria', 'Kaczmarek'),
('Tomasz', 'Wójcik'),
('Katarzyna', 'Mazur'),
('Paweł', 'Nowicki'),
('Agnieszka', 'Zielińska'),
('Michał', 'Szymczak'),
('Monika', 'Kamińska');

INSERT INTO Card (Money, Password, IdPerson)
VALUES
(100.00, 'password1', 1),
(200.00, 'password2', 2),
(300.00, 'password3', 3),
(400.00, 'password4', 4),
(500.00, 'password5', 5),
(150.00, 'password6', 6),
(250.00, 'password7', 7),
(350.00, 'password8', 8),
(450.00, 'password9', 9),
(550.00, 'password10', 10),
(600.00, 'password11', 1),
(700.00, 'password12', 2),
(800.00, 'password13', 3),
(900.00, 'password14', 4),
(1000.00, 'password15', 5);
