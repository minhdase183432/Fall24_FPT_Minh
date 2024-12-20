CREATE DATABASE GuitarShopDB;
GO
USE GuitarShopDB;
GO


CREATE TABLE Categories (
    CategoryID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryName NVARCHAR(100) NOT NULL UNIQUE
);


-- Products Table
CREATE TABLE Products (
    ProductID INT IDENTITY(1,1) PRIMARY KEY,
    CategoryID INT NOT NULL FOREIGN KEY REFERENCES Categories(CategoryID),
    ProductCode CHAR(10) NOT NULL UNIQUE,
    ProductName NVARCHAR(100) NOT NULL,
    Description NVARCHAR(500) NOT NULL,
    DateAdded DATE NOT NULL
);


-- Customers Table
CREATE TABLE Customers (
    CustomerID INT IDENTITY(1,1) PRIMARY KEY,
    Email NVARCHAR(255) NOT NULL UNIQUE,
    Password NVARCHAR(255) NOT NULL,
    FirstName NVARCHAR(100) NOT NULL,
    LastName NVARCHAR(100) NOT NULL,
    Address NVARCHAR(255),
    IsPasswordChanged BIT DEFAULT 0
);


-- Orders Table
CREATE TABLE Orders (
    OrderID INT IDENTITY(1,1) PRIMARY KEY,
    CustomerID INT NOT NULL FOREIGN KEY REFERENCES Customers(CustomerID),
    OrderDate DATE NOT NULL,
    ShipAddress NVARCHAR(255)
);


-- OrderItems Table (Junction Table for Orders and Products)
CREATE TABLE OrderItems (
    OrderID INT NOT NULL FOREIGN KEY REFERENCES Orders(OrderID),
    ProductID INT NOT NULL FOREIGN KEY REFERENCES Products(ProductID),
    Quantity INT NOT NULL CHECK (Quantity > 0),
    UnitPrice MONEY NOT NULL,
    DiscountPercent DECIMAL(5,2) NOT NULL CHECK (DiscountPercent BETWEEN 0.00 AND 75.00),
    PRIMARY KEY (OrderID, ProductID)
);

INSERT INTO Categories (CategoryName)
VALUES 
('Electric Guitars'),
('Acoustic Guitars'),
('Bass Guitars'),
('Guitar Accessories');

INSERT INTO Products (CategoryID, ProductCode, ProductName, Description, DateAdded)
VALUES 
(1, 'EGTR000001', 'Fender Stratocaster', 'Electric guitar with a solid body', '2023-10-01'),
(1, 'EGTR000002', 'Gibson Les Paul', 'Electric guitar with humbucking pickups', '2023-10-02'),
(1, 'EGTR000003', 'Ibanez RG', 'Electric guitar with locking tremolo', '2023-10-03'),
(2, 'AGTR000001', 'Taylor 814ce', 'Acoustic-electric guitar with Sitka spruce top', '2023-10-04'),
(2, 'AGTR000002', 'Martin D-28', 'Dreadnought acoustic guitar with rich tone', '2023-10-05'),
(2, 'AGTR000003', 'Yamaha FG800', 'Affordable acoustic guitar with quality sound', '2023-10-06'),
(3, 'BGTR000001', 'Fender Precision Bass', 'Iconic bass guitar with split single-coil pickups', '2023-10-07'),
(3, 'BGTR000002', 'Gibson Thunderbird', 'Bass guitar with unique body shape and sound', '2023-10-08'),
(4, 'ACC000001', 'Guitar Picks', 'Pack of 10 medium guitar picks', '2023-10-09'),
(4, 'ACC000002', 'Guitar Strings', 'Set of acoustic guitar strings', '2023-10-10');

INSERT INTO Customers (Email, Password, FirstName, LastName, Address)
VALUES 
('john.doe@example.com', 'password123', 'John', 'Doe', '123 Elm St, Springfield'),
('jane.smith@example.com', 'securepass', 'Jane', 'Smith', '456 Oak St, Springfield'),
('mike.jones@example.com', 'passw0rd!', 'Mike', 'Jones', '789 Maple St, Springfield'),
('lucy.adams@example.com', 'admin1234', 'Lucy', 'Adams', '101 Pine St, Springfield');


INSERT INTO Orders (CustomerID, OrderDate, ShipAddress)
VALUES 
(1, '2023-10-11', '123 Elm St, Springfield'),
(2, '2023-10-12', '456 Oak St, Springfield'),
(3, '2023-10-13', '789 Maple St, Springfield'),
(4, '2023-10-14', '101 Pine St, Springfield'),
(1, '2023-10-15', '123 Elm St, Springfield'),
(2, '2023-10-16', '456 Oak St, Springfield');


INSERT INTO OrderItems (OrderID, ProductID, Quantity, UnitPrice, DiscountPercent)
VALUES 
(1, 1, 1, 1200.00, 10.00),
(1, 4, 1, 3000.00, 5.00),
(2, 2, 1, 2500.00, 15.00),
(2, 7, 1, 600.00, 20.00),
(3, 3, 1, 800.00, 0.00),
(3, 9, 2, 20.00, 0.00),
(4, 5, 1, 1500.00, 5.00),
(4, 6, 1, 200.00, 0.00),
(5, 8, 1, 900.00, 12.00),
(6, 10, 3, 12.00, 10.00);

--Tìm mã sp, tên sp
select p.ProductCode, p.ProductName, p.Description, p.DateAdded
from Products p
where DateAdded <= DATEADD(month, -12, GETDATE())
order by DateAdded ASC;


--update password
UPDATE Customers
SET Password = 'Secret’@1234!', IsPasswordChanged = 1
WHERE Email = 'rick@raven.com' AND IsPasswordChanged = 1;


--gộp tên
SELECT CONCAT(LastName, ', ', FirstName) AS [Full Name]
FROM Customers
WHERE LastName >= 'M' AND LastName < 'ZZZZZZ'
ORDER BY LastName ASC;

--Tìm giá
SELECT p.ProductName, oi.UnitPrice, p.DateAdded
FROM Products p
JOIN OrderItems oi ON p.ProductID = oi.ProductID
WHERE oi.UnitPrice > 500 AND oi.UnitPrice < 2000
ORDER BY p.DateAdded DESC;

-- tính
SELECT c.CustomerID, c.FirstName, c.LastName, c.Email, c.Address, 
       SUM(oi.Quantity * oi.UnitPrice * (1 - oi.DiscountPercent / 100)) AS TotalAmount
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderItems oi ON o.OrderID = oi.OrderID
GROUP BY c.CustomerID, c.FirstName, c.LastName, c.Email, c.Address
ORDER BY TotalAmount DESC;

