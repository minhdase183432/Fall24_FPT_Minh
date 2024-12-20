create database SellManager;
use SellManager;

create table tbCustomer
(	CustID int identity (1,1) primary key,
	CustName nvarchar(100),
    CustCity nvarchar(100)
);

create table tbOrders
(	OrID int identity (1,1)) primary key,
	
