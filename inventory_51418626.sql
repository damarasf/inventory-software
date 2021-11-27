-- phpMyAdmin SQL Dump
-- version 4.9.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2021 at 03:04 AM
-- Server version: 10.4.8-MariaDB
-- PHP Version: 7.3.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `inventory_51418626`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `kd_barang` varchar(20) NOT NULL,
  `nama_barang` varchar(255) NOT NULL,
  `Harga` int(11) NOT NULL,
  `Kualitas` float(13,2) NOT NULL,
  `stok_barang` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`kd_barang`, `nama_barang`, `Harga`, `Kualitas`, `stok_barang`) VALUES
('2345', 'damara', 2000, 1.00, 0),
('222', 'afsasgas', 20, 2.00, 49),
('12334', 'Apa aja boleh', 2000, 5.00, 30);

-- --------------------------------------------------------

--
-- Table structure for table `customer`
--

CREATE TABLE `customer` (
  `id_customer` int(20) NOT NULL,
  `nama_customer` varchar(255) NOT NULL,
  `Alamat` text NOT NULL,
  `Telp` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `customer`
--

INSERT INTO `customer` (`id_customer`, `nama_customer`, `Alamat`, `Telp`) VALUES
(1, 'Damara Syaidil F', 'GPE blok B no 27', '081319916659'),
(8, 'Syaidil', 'Dimana aja bisa', '081319916659');

-- --------------------------------------------------------

--
-- Table structure for table `detail_order`
--

CREATE TABLE `detail_order` (
  `no_order` int(25) NOT NULL,
  `id_customer` int(20) NOT NULL,
  `tanggal_order` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `kd_barang` varchar(20) NOT NULL,
  `jumlah_barang` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `detail_order`
--

INSERT INTO `detail_order` (`no_order`, `id_customer`, `tanggal_order`, `kd_barang`, `jumlah_barang`, `total_harga`) VALUES
(3, 1, '2021-11-12 07:14:00', '123', 2, 6000),
(3, 1, '2021-11-12 07:14:00', '2345', 1, 2000),
(4, 1, '2021-11-12 09:19:03', '123', 2, 6000),
(4, 1, '2021-11-12 09:19:03', '1234', 1, 5000),
(5, 8, '2021-11-12 09:35:12', '123', 2, 6000),
(5, 8, '2021-11-12 09:35:12', '1234', 2, 10000),
(6, 8, '2021-11-12 09:38:40', '123', 1, 3000),
(6, 8, '2021-11-12 09:38:40', '1234', 2, 10000),
(7, 1, '2021-11-12 15:38:04', '123', 2, 6000),
(8, 1, '2021-11-19 08:36:48', '222', 1, 20);

-- --------------------------------------------------------

--
-- Table structure for table `header_order`
--

CREATE TABLE `header_order` (
  `no_order` int(11) NOT NULL,
  `id_customer` int(11) NOT NULL,
  `id_user_sales` int(11) NOT NULL,
  `id_user_gudang` int(11) NOT NULL,
  `total_penjualan` int(11) NOT NULL,
  `tanggal_bayar` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `status_order` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `header_order`
--

INSERT INTO `header_order` (`no_order`, `id_customer`, `id_user_sales`, `id_user_gudang`, `total_penjualan`, `tanggal_bayar`, `status_order`) VALUES
(3, 1, 2, 0, 8000, '2021-11-12 09:36:29', 'Completed'),
(4, 1, 2, 0, 11000, '2021-11-12 09:19:08', 'Invoice'),
(5, 8, 2, 0, 16000, '2021-11-12 09:35:23', 'Invoice'),
(6, 8, 2, 0, 13000, '2021-11-12 15:41:40', 'Completed'),
(7, 1, 2, 0, 6000, '2021-11-26 17:56:44', 'Shipped'),
(8, 1, 2, 0, 20, '2021-11-19 08:37:29', 'Invoice');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id_users` int(10) NOT NULL,
  `nama_users` varchar(200) NOT NULL,
  `Username` varchar(100) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `Status` varchar(20) NOT NULL,
  `last_login` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id_users`, `nama_users`, `Username`, `Password`, `Status`, `last_login`) VALUES
(1, 'admin', 'admin', 'admin', 'gudang', '2018-10-27 09:38:14'),
(2, 'sales', 'sales', 'sales', 'sales', '2018-10-27 09:39:06');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id_customer`);

--
-- Indexes for table `header_order`
--
ALTER TABLE `header_order`
  ADD PRIMARY KEY (`no_order`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id_users`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `customer`
--
ALTER TABLE `customer`
  MODIFY `id_customer` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `header_order`
--
ALTER TABLE `header_order`
  MODIFY `no_order` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id_users` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
