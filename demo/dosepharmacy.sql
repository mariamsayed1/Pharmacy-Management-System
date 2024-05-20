-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 19, 2024 at 03:56 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dosepharmacy`
--

-- --------------------------------------------------------

--
-- Table structure for table `category`
--

CREATE TABLE `category` (
  `id` int(11) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `category`
--

INSERT INTO `category` (`id`, `image`, `name`) VALUES
(1, '425783.jpg', 'Medicines'),
(2, 'bet.jpg', 'vitamines'),
(3, 'polyfresh1.jpg', 'Personal care'),
(4, 'bbshower.jpg', 'Baby and mother'),
(5, 'voltaren.jpeg', 'Medicines'),
(9, 'laroche.jpg', 'Skin Care'),
(11, 'bet.jpg', 'vitamines'),
(12, 'blog freeze.jpg', 'Medicines');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist`
--

CREATE TABLE `pharmacist` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pharmacist`
--

INSERT INTO `pharmacist` (`id`, `password`, `username`, `usertype`) VALUES
(1, '123', 'pharm1', 'pharmacist'),
(2, '1223', 'jana', 'pharmacist');

-- --------------------------------------------------------

--
-- Table structure for table `pharmacist_seq`
--

CREATE TABLE `pharmacist_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `pharmacist_seq`
--

INSERT INTO `pharmacist_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `active_ingredient` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int(11) NOT NULL,
  `side_effect` varchar(255) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `exp_date` varchar(255) DEFAULT NULL,
  `prod_date` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `active_ingredient`, `image`, `name`, `price`, `quantity`, `side_effect`, `category_id`, `description`, `exp_date`, `prod_date`) VALUES
(1, 'mm', 'panadol.jpg', 'panadol', 50, 17, 'mm', 1, NULL, NULL, NULL),
(2, 'hh', 'Cataflam.jpg', 'cataflam', 20, 30, 'hh', 1, NULL, NULL, NULL),
(17, 'kk', 'omega3.jpg', 'omega3', 40, 70, 'kk', 1, NULL, NULL, NULL),
(19, 'mm', 'panadol.jpg', 'panadol', 50, 50, 'hh', 1, NULL, NULL, NULL),
(26, 'jj', 'vaseline.jpeg', 'vasline', 200, 90, 'hh', 3, NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session`
--

CREATE TABLE `spring_session` (
  `PRIMARY_ID` char(36) NOT NULL,
  `SESSION_ID` char(36) NOT NULL,
  `CREATION_TIME` bigint(20) NOT NULL,
  `LAST_ACCESS_TIME` bigint(20) NOT NULL,
  `MAX_INACTIVE_INTERVAL` int(11) NOT NULL,
  `EXPIRY_TIME` bigint(20) NOT NULL,
  `PRINCIPAL_NAME` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

--
-- Dumping data for table `spring_session`
--

INSERT INTO `spring_session` (`PRIMARY_ID`, `SESSION_ID`, `CREATION_TIME`, `LAST_ACCESS_TIME`, `MAX_INACTIVE_INTERVAL`, `EXPIRY_TIME`, `PRINCIPAL_NAME`) VALUES
('72aad8fd-c193-4707-b424-18b222f06dff', '05aa5a8b-4c2f-4ff9-9053-af7745d0645d', 1716081076697, 1716081565079, 1800, 1716083365079, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `spring_session_attributes`
--

CREATE TABLE `spring_session_attributes` (
  `SESSION_PRIMARY_ID` char(36) NOT NULL,
  `ATTRIBUTE_NAME` varchar(200) NOT NULL,
  `ATTRIBUTE_BYTES` blob NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `confirmpassword` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `usertype` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `confirmpassword`, `email`, `fullname`, `password`, `phonenumber`, `username`, `usertype`) VALUES
(1, '12345678', 'mariamsayed777@gmail.com', 'mariam mahmoud', '$2a$12$6yrK5ft.ab4Y/TtSX7NyfeJZ16xCiblwNWIyZwzva/yfv6kQwjPBy', '05677', 'mariammahmoud', 'client'),
(2, '12345678', 'nowdemy@sample.com', 'sara mostafa', '$2a$12$lZ47fG.L9pJswy9iZEciU.3oPGaSn8eYnmUPqjjc4819lHfRb1Efu', '05677', 'saraa', 'client'),
(52, '12345678', 'coco@gmail.com', 'mariam', '$2a$12$fBT/6xBIrBwEs8vxe7C5iu7kdOsXs5K.CBDFFyvkOz3UZ7De7lMAm', '05677', 'mariam1', 'client'),
(102, '12345678', 'nowdemy@sample.com', 'mmm', '$2a$12$3dXcv.N4o/nxQl06Mn.TZefZg6FIIzT.jD3JdFgBC0dDwCPMJHP9e', '05677', 'mmmmmm', 'client'),
(152, '12345678', 'coco@gmail.com', 'sssss', '$2a$12$ttUE3G2OeSij7YopabkX/uUVfPgIhPPFtgc0Y1oKNFKrPIHB7mTZC', '05677', 'sss', 'client'),
(202, '$2a$12$hopbW1TtqVwXt3fkXEyobuwIN6Ud0vQi/fWp5T6VsSdN9rnSRWrrW', 'mariamsayed777@gmail.com', 'mariam', '$2a$12$r/c7VVZYJAy9dIHzk0XPAuiy1YLldmdeLy/MLoVL6UUPFmQiOECx2', '01147977179', 'mariam10', 'client'),
(252, '$2a$12$0yGh0UUXc4ZyLSCSmqV.TO0zxtKqjxCUBpnrpyPceDqVbKUS1820.', 'dose@gmail.com', 'Dose Pharmasy', '$2a$12$OqDri9U3M5nQEW6WJ7rQ5eDCGqw9j5m9vbXDEg09A0pI6lNZ9PMCK', '01123456789', 'admin', 'admin'),
(302, '$2a$12$a7qp3QjUwRCwtiJF2gV9We.d0FlzYRelgw5hQCy1FLcp5bVmdcnVO', 'coco@gmail.com', 'mmmmm', '$2a$12$e8TzmvjHcY2ZLOpehzRD.efzjPoUWcsxj/Lgpba7G4sM8Vf.4zlyO', '01147977179', 'mm', 'client'),
(352, '$2a$12$oA9heTCTQR1AEwJ1FFMH8uHDTquexw71ccIyXccpyGpzsSYxToxW.', 'nowdemy@sample.com', 'lllll', '$2a$12$rzzS.AW.QFqKQFpjjijVjuqB.9wsNuaPLCGWoibl3TfGNWqdlUevi', '01147977179', 'llllll', 'client'),
(402, '$2a$12$RDW1yklHB75.UjnMgpS6reIUIrvceyWlW80CYEf8jWX3CQahx7AoC', 'pharmacy@gmail.com', 'shshshhs', '$2a$12$H9a2MwFJLOdwhXxf1r4JluFsUuAF6G4s5lxzOtFl.IXwz.xifsWjm', '01147977179', 'ppppppp', 'client');

-- --------------------------------------------------------

--
-- Table structure for table `user_seq`
--

CREATE TABLE `user_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user_seq`
--

INSERT INTO `user_seq` (`next_val`) VALUES
(501);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pharmacist`
--
ALTER TABLE `pharmacist`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1mtsbur82frn64de7balymq9s` (`category_id`);

--
-- Indexes for table `spring_session`
--
ALTER TABLE `spring_session`
  ADD PRIMARY KEY (`PRIMARY_ID`),
  ADD UNIQUE KEY `SPRING_SESSION_IX1` (`SESSION_ID`),
  ADD KEY `SPRING_SESSION_IX2` (`EXPIRY_TIME`),
  ADD KEY `SPRING_SESSION_IX3` (`PRINCIPAL_NAME`);

--
-- Indexes for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD PRIMARY KEY (`SESSION_PRIMARY_ID`,`ATTRIBUTE_NAME`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `category`
--
ALTER TABLE `category`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `product`
--
ALTER TABLE `product`
  ADD CONSTRAINT `FK1mtsbur82frn64de7balymq9s` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`);

--
-- Constraints for table `spring_session_attributes`
--
ALTER TABLE `spring_session_attributes`
  ADD CONSTRAINT `SPRING_SESSION_ATTRIBUTES_FK` FOREIGN KEY (`SESSION_PRIMARY_ID`) REFERENCES `spring_session` (`PRIMARY_ID`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
