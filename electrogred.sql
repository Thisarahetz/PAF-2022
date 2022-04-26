-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 26, 2022 at 06:10 PM
-- Server version: 10.4.18-MariaDB
-- PHP Version: 7.4.16

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `electrogred`
--

-- --------------------------------------------------------

--
-- Table structure for table `bill`
--

CREATE TABLE `bill` (
  `billId` varchar(20) NOT NULL,
  `accountId` varchar(20) DEFAULT NULL,
  `joinDate` varchar(14) DEFAULT NULL,
  `meterReadingBeforeDate` varchar(40) DEFAULT NULL,
  `meterReadingBefore` varchar(40) DEFAULT NULL,
  `meterReadingNowDate` varchar(40) DEFAULT NULL,
  `meterReadingNow` varchar(40) DEFAULT NULL,
  `noOfUntitsConsumed` varchar(40) DEFAULT NULL,
  `chargeforelectricityConsume` varchar(40) DEFAULT NULL,
  `adjustments` varchar(40) DEFAULT NULL,
  `totalAmountDue` double(6,2) DEFAULT NULL,
  `billDate` varchar(40) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `bill`
--

INSERT INTO `bill` (`billId`, `accountId`, `joinDate`, `meterReadingBeforeDate`, `meterReadingBefore`, `meterReadingNowDate`, `meterReadingNow`, `noOfUntitsConsumed`, `chargeforelectricityConsume`, `adjustments`, `totalAmountDue`, `billDate`) VALUES
('BIAN657567100000', 'AN159756', '2022/02/01', '159756769', '2300', '2530', '5454354', 'gfdfg', 'cgfdg', 'ddfgv', 5667.00, '432sdfs');

-- --------------------------------------------------------

--
-- Table structure for table `notification`
--

CREATE TABLE `notification` (
  `notificationId` varchar(20) NOT NULL,
  `accountId` varchar(20) DEFAULT NULL,
  `billId` varchar(20) DEFAULT NULL,
  `amountToBePaid` double(6,2) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `mobileNumber` varchar(50) DEFAULT NULL,
  `subject` varchar(80) DEFAULT NULL,
  `massage` varchar(200) DEFAULT NULL,
  `dateNotify` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `notification`
--

INSERT INTO `notification` (`notificationId`, `accountId`, `billId`, `amountToBePaid`, `email`, `mobileNumber`, `subject`, `massage`, `dateNotify`) VALUES
('NIBIAN657567100000', 'AN159756', 'BIAN657567100000', 545.00, 'Damon.Howell2@gmail.com', '847-536-5925', 'dgdfgdfggd', 'bhfgbhfgbn', '1650833671');

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `paymentId` varchar(20) NOT NULL,
  `accountId` varchar(20) DEFAULT NULL,
  `billId` varchar(20) DEFAULT NULL,
  `payeeName` varchar(20) DEFAULT NULL,
  `paymentType` varchar(20) DEFAULT NULL,
  `email` varchar(40) DEFAULT NULL,
  `totalAmountBill` double(6,2) DEFAULT NULL,
  `amountToBePaid` double(6,2) DEFAULT NULL,
  `datePayment` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `payment`
--

INSERT INTO `payment` (`paymentId`, `accountId`, `billId`, `payeeName`, `paymentType`, `email`, `totalAmountBill`, `amountToBePaid`, `datePayment`) VALUES
('PI220425044847447', 'AN159756', 'BIAN657567100000', 'dinetsh', 'cash', 'dineth@gmail.com', 3900.00, 3000.00, '2022/26/4'),
('PI220425045520420', 'AN159756', 'BIAN657567100000', 'Shashi', 'online payment', 'sashi@gmail.com', 3900.00, 3000.00, '2022/3/4'),
('PI220425045522422', 'AN159756', 'BIAN657567100000', 'Rachitha', 'cash', 'rachitha@gmail.com', 1999.00, 1000.00, '2022/8/9'),
('PI220426071647447', 'AN159756', 'BIAN657567100000', 'dineth', 'cash', 'dineth@gmail.com', 3900.00, 3000.00, '2022/26/4');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `accountId` varchar(20) NOT NULL,
  `isAdmin` tinyint(1) DEFAULT NULL,
  `firstName` varchar(50) DEFAULT NULL,
  `lastName` varchar(50) DEFAULT NULL,
  `nic` varchar(50) DEFAULT NULL,
  `permanantAddress` varchar(50) DEFAULT NULL,
  `mobileNumber` varchar(50) DEFAULT NULL,
  `landNumber` varchar(50) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `userPassword` varchar(20) DEFAULT NULL,
  `areaoffice` varchar(20) DEFAULT NULL,
  `joinDate` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`accountId`, `isAdmin`, `firstName`, `lastName`, `nic`, `permanantAddress`, `mobileNumber`, `landNumber`, `email`, `userPassword`, `areaoffice`, `joinDate`) VALUES
('AN159756', 1, 'Thisara', 'yashoda', '19929290', 'ganemulla', '43242', '5454354', 't', 'cgfdg', 'ddfgv', '5667867'),
('AN434354', 0, 'Asanka', 'Gamage', '3121221', 'mathale', '43242', '5454354', 'gfdfg', 'cgfdg', 'ddfgv', '5667867');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `bill`
--
ALTER TABLE `bill`
  ADD PRIMARY KEY (`billId`),
  ADD KEY `accountId` (`accountId`);

--
-- Indexes for table `notification`
--
ALTER TABLE `notification`
  ADD PRIMARY KEY (`notificationId`),
  ADD KEY `accountId` (`accountId`),
  ADD KEY `billId` (`billId`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`paymentId`),
  ADD KEY `accountId` (`accountId`),
  ADD KEY `billId` (`billId`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`accountId`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bill`
--
ALTER TABLE `bill`
  ADD CONSTRAINT `bill_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `users` (`accountId`) ON DELETE CASCADE;

--
-- Constraints for table `notification`
--
ALTER TABLE `notification`
  ADD CONSTRAINT `notification_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `users` (`accountId`) ON DELETE CASCADE,
  ADD CONSTRAINT `notification_ibfk_2` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`) ON DELETE CASCADE;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `payment_ibfk_1` FOREIGN KEY (`accountId`) REFERENCES `users` (`accountId`) ON DELETE CASCADE,
  ADD CONSTRAINT `payment_ibfk_2` FOREIGN KEY (`billId`) REFERENCES `bill` (`billId`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
