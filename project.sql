-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Dec 21, 2013 at 03:52 PM
-- Server version: 5.5.27
-- PHP Version: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `project`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin`
--

CREATE TABLE IF NOT EXISTS `admin` (
  `admin_id` int(5) NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(25) NOT NULL,
  `admin_pass` varchar(25) NOT NULL,
  PRIMARY KEY (`admin_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `admin`
--

INSERT INTO `admin` (`admin_id`, `admin_name`, `admin_pass`) VALUES
(1, 'test', 'test');

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE IF NOT EXISTS `department` (
  `dep_id` int(5) NOT NULL AUTO_INCREMENT,
  `department` varchar(25) NOT NULL,
  `loc_id` int(5) NOT NULL,
  PRIMARY KEY (`dep_id`),
  KEY `loc_id` (`loc_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`dep_id`, `department`, `loc_id`) VALUES
(1, 'csit', 1),
(2, 'physics', 1),
(3, 'biology', 1),
(4, 'chemistry', 1),
(5, 'arts', 2),
(6, 'humanities', 3);

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE IF NOT EXISTS `employee` (
  `emp_id` int(5) NOT NULL AUTO_INCREMENT,
  `employee` varchar(25) DEFAULT NULL,
  `email` varchar(25) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `salary` int(10) DEFAULT NULL,
  `dep_id` int(5) NOT NULL,
  `loc_id` int(5) NOT NULL,
  PRIMARY KEY (`emp_id`),
  KEY `loc_id` (`loc_id`),
  KEY `dep_id` (`dep_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=11 ;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`emp_id`, `employee`, `email`, `phone`, `salary`, `dep_id`, `loc_id`) VALUES
(1, 'suman', 'dralmostright@gmail.com', '9841173867', 5000, 1, 1),
(2, 'anup', 'themightysapien@gmail.com', '9849132455', 10000, 1, 1),
(3, 'sajjan', 'szncerma@gmail.com', '9849234567', 15000, 2, 2),
(4, 'ravi', 'ravichhetri@gmail.com', '9841723255', 20000, 3, 3),
(5, 'pratap', 'pratap@gmail.com', '9851023454', 25000, 4, 1),
(6, 'shishir', 'chire@gmail.com', '9849553344', 22000, 4, 2),
(7, 'arun', 'arun@gmail.com', '9803776690', 20000, 4, 2),
(8, 'uddhav', 'uddhav@gmail.com', '9843982534', 20000, 1, 3),
(9, 'gokul', 'gokul@gmail.com', '9849234567', 21000, 5, 3),
(10, 'anuj', 'anuj@gmail.com', '9863234567', 30000, 6, 3);

-- --------------------------------------------------------

--
-- Table structure for table `funcop`
--

CREATE TABLE IF NOT EXISTS `funcop` (
  `fid` int(5) NOT NULL AUTO_INCREMENT,
  `fsynonym` varchar(25) NOT NULL,
  `operator` varchar(25) NOT NULL,
  `type` tinyint(2) NOT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `funcop`
--

INSERT INTO `funcop` (`fid`, `fsynonym`, `operator`, `type`) VALUES
(1, 'highest', 'max', 11),
(2, 'lowest', 'min', 11),
(3, 'average', 'avg', 11),
(4, 'total', 'sum', 11),
(5, 'lowest', 'min', 11),
(6, 'number', 'count', 11),
(8, 'greater', '>', 22),
(9, 'less', '<', 22),
(10, 'lower', '<', 22),
(11, 'higher', '>', 22);

-- --------------------------------------------------------

--
-- Table structure for table `location`
--

CREATE TABLE IF NOT EXISTS `location` (
  `loc_id` int(5) NOT NULL AUTO_INCREMENT,
  `street` varchar(25) NOT NULL,
  `city` varchar(25) NOT NULL,
  PRIMARY KEY (`loc_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Dumping data for table `location`
--

INSERT INTO `location` (`loc_id`, `street`, `city`) VALUES
(1, 'tokharoad', 'Kathmandu'),
(2, 'pulchowk', 'lalitpur'),
(3, 'sallarighari', 'bhaktapur');

-- --------------------------------------------------------

--
-- Table structure for table `synonym`
--

CREATE TABLE IF NOT EXISTS `synonym` (
  `sid` int(5) NOT NULL AUTO_INCREMENT,
  `syname` varchar(25) NOT NULL,
  `scolumn` varchar(25) NOT NULL,
  `stable` varchar(25) NOT NULL,
  PRIMARY KEY (`sid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=12 ;

--
-- Dumping data for table `synonym`
--

INSERT INTO `synonym` (`sid`, `syname`, `scolumn`, `stable`) VALUES
(1, 'name', 'employee', 'employee'),
(2, 'names', 'employee', 'employee'),
(3, 'employees', 'employee', 'employee'),
(6, 'go', 'gone', 'went'),
(7, 'located', 'city', 'location'),
(8, 'works', 'department', 'department'),
(9, 'work', 'department', 'department'),
(10, 'location', 'city', 'location'),
(11, 'working', 'department', 'department');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `department_ibfk_1` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`);

--
-- Constraints for table `employee`
--
ALTER TABLE `employee`
  ADD CONSTRAINT `employee_ibfk_1` FOREIGN KEY (`loc_id`) REFERENCES `location` (`loc_id`),
  ADD CONSTRAINT `employee_ibfk_2` FOREIGN KEY (`dep_id`) REFERENCES `department` (`dep_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
