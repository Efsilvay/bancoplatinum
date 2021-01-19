-- --------------------------------------------------------
-- Host:                         localhost
-- Versión del servidor:         10.5.5-MariaDB - mariadb.org binary distribution
-- SO del servidor:              Win64
-- HeidiSQL Versión:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Volcando estructura de base de datos para bancoplatinum
CREATE DATABASE IF NOT EXISTS `bancoplatinum` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `bancoplatinum`;

-- Volcando estructura para tabla bancoplatinum.ctacorrient
CREATE TABLE IF NOT EXISTS `ctacorrient` (
  `idCuenta` int(11) NOT NULL AUTO_INCREMENT,
  `monto` int(11) NOT NULL,
  `rutCliente` int(11) DEFAULT NULL,
  `Ejecutivo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCuenta`),
  KEY `FK_ctacorrient_persona` (`rutCliente`),
  KEY `FK_ctacorrient_ejecutivo` (`Ejecutivo`),
  CONSTRAINT `FK_ctacorrient_ejecutivo` FOREIGN KEY (`Ejecutivo`) REFERENCES `ejecutivo` (`rutEjecutivo`),
  CONSTRAINT `FK_ctacorrient_persona` FOREIGN KEY (`rutCliente`) REFERENCES `persona` (`rut`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bancoplatinum.ejecutivo
CREATE TABLE IF NOT EXISTS `ejecutivo` (
  `rutEjecutivo` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `departamento` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`rutEjecutivo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bancoplatinum.persona
CREATE TABLE IF NOT EXISTS `persona` (
  `rut` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL DEFAULT '',
  `apellido` varchar(50) NOT NULL DEFAULT '',
  `direccion` varchar(50) NOT NULL DEFAULT '',
  `correo` varchar(50) NOT NULL DEFAULT '',
  `telefono` int(11) NOT NULL DEFAULT 0,
  `usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`rut`),
  KEY `FK_persona_usuario` (`usuario`),
  CONSTRAINT `FK_persona_usuario` FOREIGN KEY (`usuario`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bancoplatinum.transacción
CREATE TABLE IF NOT EXISTS `transacción` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rutCliente` int(11) DEFAULT NULL,
  `idCuenta` int(11) DEFAULT NULL,
  `CuentaTransferencia` int(11) DEFAULT NULL,
  `TipoCuenta` varchar(50) DEFAULT NULL,
  `MontoTranferencia` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_transacción_persona` (`rutCliente`),
  KEY `FK_transacción_usuario` (`idCuenta`),
  CONSTRAINT `FK_transacción_persona` FOREIGN KEY (`rutCliente`) REFERENCES `persona` (`rut`),
  CONSTRAINT `FK_transacción_usuario` FOREIGN KEY (`idCuenta`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

-- Volcando estructura para tabla bancoplatinum.usuario
CREATE TABLE IF NOT EXISTS `usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombreUsuario` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- La exportación de datos fue deseleccionada.

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
