-- phpMyAdmin SQL Dump
-- version 3.5.2.2
-- http://www.phpmyadmin.net
--
-- Inang: 127.0.0.1
-- Waktu pembuatan: 27 Agu 2016 pada 11.59
-- Versi Server: 5.5.27
-- Versi PHP: 5.4.7

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Basis data: `crud_mahasiswa`
--

-- --------------------------------------------------------

--
-- Struktur dari tabel `mahasiswa`
--

CREATE TABLE IF NOT EXISTS `mahasiswa` (
  `mahasiswa_id` int(11) NOT NULL AUTO_INCREMENT,
  `nim` varchar(50) DEFAULT NULL,
  `nama` varchar(255) DEFAULT NULL,
  `tgl_lahir` date DEFAULT NULL,
  `prodi_id` int(11) DEFAULT NULL,
  `alamat` text,
  PRIMARY KEY (`mahasiswa_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=10 ;

--
-- Dumping data untuk tabel `mahasiswa`
--

INSERT INTO `mahasiswa` (`mahasiswa_id`, `nim`, `nama`, `tgl_lahir`, `prodi_id`, `alamat`) VALUES
(1, '2016420109', 'Muhammad Irsyad', '1992-09-11', 16, 'Nginden I/29 Sukolilo, Surabaya'),
(7, '2016080556488', 'Muhammad Arifin', '1991-08-31', 8, 'Kedung doro I/789 Surabaya'),
(8, '20165599623', 'Arif Krisna wijaya', '1985-05-08', 6, 'Jombang barat laut');

-- --------------------------------------------------------

--
-- Struktur dari tabel `prodi`
--

CREATE TABLE IF NOT EXISTS `prodi` (
  `prodi_id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`prodi_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=21 ;

--
-- Dumping data untuk tabel `prodi`
--

INSERT INTO `prodi` (`prodi_id`, `nama`) VALUES
(1, 'Biologi'),
(2, 'Ilmu Ekonomi'),
(3, 'Akuntansi'),
(4, 'Manajemen'),
(5, 'Ilmu Farmasi'),
(6, 'Filsafat'),
(7, 'Jurnalistik'),
(8, 'Hubungan Masyarakat'),
(9, 'Teknik Infromatika'),
(10, 'Teknik Sipil'),
(11, 'Sastra Arab'),
(12, 'Sastra Jepang'),
(13, 'Sastra Inggris'),
(14, 'Sosiologi'),
(15, 'Pendidikan Dokter'),
(16, 'Pendidikan Dokter Gigi'),
(17, 'Matematika'),
(18, 'Statistika'),
(19, 'Agronomi'),
(20, 'Ilmu Budaya');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
