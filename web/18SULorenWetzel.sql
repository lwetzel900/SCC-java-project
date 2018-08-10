-- phpMyAdmin SQL Dump
-- version 4.5.1
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Aug 08, 2018 at 09:35 PM
-- Server version: 10.1.10-MariaDB
-- PHP Version: 7.0.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `18SULorenWetzel`
--
Drop database if exists `18SULorenWetzel`;
create database `18SULorenWetzel`;
USE `18SULorenWetzel`;
-- --------------------------------------------------------

--
-- Table structure for table `Persons`
--

CREATE TABLE `Persons` (
  `EmployeeID` int(1) NOT NULL,
  `FirstName` varchar(50) NOT NULL,
  `MiddleName` varchar(20) DEFAULT NULL,
  `LastName` varchar(50) NOT NULL,
  `BirthDate` date NOT NULL,
  `HireDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `Persons`
--

INSERT INTO `Persons` (`EmployeeID`, `FirstName`, `MiddleName`, `LastName`, `BirthDate`, `HireDate`) VALUES
(1, 'Loren', 'R', 'Wetzel', '1981-08-09', '2018-08-07');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `Persons`
--
ALTER TABLE `Persons`
  ADD PRIMARY KEY (`EmployeeID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `Persons`
--
ALTER TABLE `Persons`
  MODIFY `EmployeeID` int(1) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
