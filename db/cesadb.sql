SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

DROP DATABASE IF EXISTS `cesadb`;
CREATE DATABASE IF NOT EXISTS `cesadb` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `cesadb`;

DROP TABLE IF EXISTS `fact_prov`;
CREATE TABLE IF NOT EXISTS `fact_prov` (
  `CIF_PROVEEDOR` varchar(9) NOT NULL,
  `RAZ_PROVEEDOR` varchar(50) NOT NULL,
  `NUM_FACTURA` int(11) NOT NULL AUTO_INCREMENT,
  `DES_FACTURA` varchar(100) NOT NULL,
  `BAS_IMPONIBLE` float NOT NULL,
  `IVA_IMPORTE` int(11) NOT NULL,
  `TOT_IMPORTE` float NOT NULL,
  `FEC_FACTURA` date NOT NULL,
  `FEC_VENCIMIENTO` date NOT NULL,
  PRIMARY KEY (`NUM_FACTURA`),
  KEY `CIF_PROVEEDOR` (`CIF_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

DROP TABLE IF EXISTS `prov_comp`;
CREATE TABLE IF NOT EXISTS `prov_comp` (
  `CIF_PROVEEDOR` varchar(9) NOT NULL,
  `RAZ_PROVEEDOR` varchar(50) NOT NULL,
  `REG_NOTARIAL` int(11) NOT NULL,
  `SEG_RESPONSABILIDAD` varchar(20) NOT NULL,
  `SEG_IMPORTE` float NOT NULL,
  `FEC_HOMOLOGACION` date NOT NULL,
  PRIMARY KEY (`CIF_PROVEEDOR`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


ALTER TABLE `fact_prov`
  ADD CONSTRAINT `fact_prov_ibfk_1` FOREIGN KEY (`CIF_PROVEEDOR`) REFERENCES `prov_comp` (`CIF_PROVEEDOR`) ON DELETE NO ACTION ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;