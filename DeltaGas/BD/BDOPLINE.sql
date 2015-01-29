-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Versión del servidor:         5.5.16 - MySQL Community Server (GPL)
-- SO del servidor:              Win32
-- HeidiSQL Versión:             8.1.0.4545
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- Volcando estructura de base de datos para opline
DROP DATABASE IF EXISTS `opline`;
CREATE DATABASE IF NOT EXISTS `opline` /*!40100 DEFAULT CHARACTER SET utf16 COLLATE utf16_spanish_ci */;
USE `opline`;


-- Volcando estructura para tabla opline.asociado
DROP TABLE IF EXISTS `asociado`;
CREATE TABLE IF NOT EXISTS `asociado` (
  `id_asociado` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `id_estado` int(10) unsigned NOT NULL,
  `nombre` varchar(30) COLLATE utf16_spanish_ci NOT NULL,
  `apellido` varchar(20) COLLATE utf16_spanish_ci NOT NULL,
  `fecha_nacimiento` datetime DEFAULT NULL,
  `cedula` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `expedida` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `fecha_afiliacion` datetime NOT NULL,
  `antiguedad_corabastos` varchar(20) COLLATE utf16_spanish_ci DEFAULT NULL,
  `celular` varchar(20) COLLATE utf16_spanish_ci DEFAULT NULL,
  `correo` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_asociado`),
  KEY `asociado_FKIndex1` (`id_estado`),
  CONSTRAINT `asociado_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.asociado: ~10 rows (aproximadamente)
DELETE FROM `asociado`;
/*!40000 ALTER TABLE `asociado` DISABLE KEYS */;
INSERT INTO `asociado` (`id_asociado`, `id_estado`, `nombre`, `apellido`, `fecha_nacimiento`, `cedula`, `expedida`, `fecha_afiliacion`, `antiguedad_corabastos`, `celular`, `correo`) VALUES
	(0000000001, 1, 'Pedro', 'Jimenez', '1985-04-20 00:00:00', '1657032887', 'Bogotá', '2014-03-10 00:00:00', '2 años', '2147483647', 'jimenez@gmai.com'),
	(0000000002, 1, 'Federico', 'Hernandes', '1980-06-19 00:00:00', '1945632784', 'Bogotá', '2014-02-15 00:00:00', '5 años', '2147483647', ''),
	(0000000003, 1, 'Federica', 'Hernandez', '1980-06-19 00:00:00', '1945632784', 'Bogotá', '2014-02-15 00:00:00', '5 años', '2147483647', ''),
	(0000000004, 1, 'Andres', 'Murcia', '1994-03-10 00:00:00', '1203984563', 'Bogotá', '2014-01-10 00:00:00', '3 años', '2147483647', 'murciaa@hotmail.com'),
	(0000000005, 2, 'Federico', 'Hernandes', '1980-06-19 00:00:00', '1945632784', 'Bogotá', '2014-02-15 00:00:00', '5 años', '2147483647', ''),
	(0000000006, 2, 'Carmen', 'Suarez', '1991-12-01 00:00:00', '1578630142', 'Bogotá', '2014-01-30 00:00:00', '1 año', '2147483647', 'csuarez@gmail.com'),
	(0000000007, 1, 'Federico', 'Hernandes', '1980-06-19 00:00:00', '1945632784', 'Bogotá', '2014-02-15 00:00:00', '5 años', '2147483647', ''),
	(0000000008, 2, 'Celeste', 'Cortes', '1989-10-10 00:00:00', '4698210367', 'Bogotá', '2014-03-10 00:00:00', '2 años', '2147483647', 'ccortes@gmail.com'),
	(0000000009, 2, 'Gabriela', 'Contreras', '1990-09-25 00:00:00', '4812365701', 'Bogotá', '2014-07-27 00:00:00', 'no tiene', '2147483647', NULL),
	(0000000010, 1, 'Juliana', 'Rodriguez', '1994-03-20 00:00:00', '4301527896', 'Bogotá', '2014-02-10 00:00:00', 'no tiene', '2147483647', 'jrodri@gmail.com');
/*!40000 ALTER TABLE `asociado` ENABLE KEYS */;


-- Volcando estructura para tabla opline.certificado
DROP TABLE IF EXISTS `certificado`;
CREATE TABLE IF NOT EXISTS `certificado` (
  `id_certificado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_empleado` int(10) unsigned NOT NULL,
  `tabla_referencia` varchar(1) CHARACTER SET utf8 DEFAULT NULL,
  `documento_solicitante` varchar(20) CHARACTER SET utf8 DEFAULT NULL,
  `fecha_solicitud` datetime DEFAULT NULL,
  `fecha_respuesta` datetime DEFAULT NULL,
  `respuesta` varchar(10) CHARACTER SET utf8 DEFAULT NULL,
  `fecha_generado` datetime DEFAULT NULL,
  `id_pago` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_certificado`),
  KEY `Certificados_FKIndex1` (`id_empleado`),
  CONSTRAINT `certificado_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.certificado: ~11 rows (aproximadamente)
DELETE FROM `certificado`;
/*!40000 ALTER TABLE `certificado` DISABLE KEYS */;
INSERT INTO `certificado` (`id_certificado`, `id_empleado`, `tabla_referencia`, `documento_solicitante`, `fecha_solicitud`, `fecha_respuesta`, `respuesta`, `fecha_generado`, `id_pago`) VALUES
	(1, 2, 'A', '1945632784', '2014-10-21 00:02:47', '2014-10-21 00:06:17', 'aprobado', NULL, 1236),
	(2, 2, 'A', '1945632784', '2014-11-07 02:54:56', '2014-11-09 13:17:17', 'aprobado', NULL, NULL),
	(3, 2, 'A', '1657032887', '2014-11-07 04:08:40', NULL, 'rechazado', NULL, NULL),
	(4, 2, 'A', '1203984563', '2014-11-07 04:10:51', '2014-11-09 21:59:20', 'aprobado', NULL, NULL),
	(5, 2, 'A', '1945632784', '2014-11-07 04:18:37', '2014-11-09 21:59:38', 'rechazado', NULL, NULL),
	(6, 2, 'A', '1945632784', '2014-11-07 04:21:34', NULL, 'pendiente', NULL, NULL),
	(7, 2, 'A', '1945632784', '2014-11-08 17:39:20', NULL, 'pendiente', NULL, NULL),
	(8, 2, 'A', '1945632784', '2014-11-09 02:20:17', NULL, 'pendiente', NULL, NULL),
	(9, 2, 'A', '1945632784', '2014-11-09 02:20:40', NULL, 'pendiente', NULL, NULL),
	(10, 2, 'A', '1578630142', '2014-11-09 02:20:49', NULL, 'pendiente', NULL, NULL),
	(11, 2, 'A', '1578630142', '2014-11-09 02:21:42', NULL, 'pendiente', NULL, NULL);
/*!40000 ALTER TABLE `certificado` ENABLE KEYS */;


-- Volcando estructura para tabla opline.cliente
DROP TABLE IF EXISTS `cliente`;
CREATE TABLE IF NOT EXISTS `cliente` (
  `id_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_tipo_cliente` int(10) unsigned NOT NULL,
  `nombre` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `cedula` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `celular` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `telefono` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_cliente`),
  KEY `cliente_FKIndex2` (`id_tipo_cliente`),
  CONSTRAINT `cliente_ibfk_1` FOREIGN KEY (`id_tipo_cliente`) REFERENCES `tipo_cliente` (`id_tipo_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.cliente: ~10 rows (aproximadamente)
DELETE FROM `cliente`;
/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`id_cliente`, `id_tipo_cliente`, `nombre`, `apellido`, `cedula`, `celular`, `telefono`, `correo`) VALUES
	(1, 1, 'Ana', 'dddd', '123444', '(431) 412-3421', '541-2036', 'dvelas@gmail.com'),
	(2, 5, 'Pedro', 'Vique', '123444', '', '158-6347', 'pviq6@gmail.com'),
	(3, 3, 'Maria2', 'Duran', '32333444', '(324) 242-3223', '225-8844', 'mduran@gmail.com'),
	(4, 4, 'Juan', 'Perez', '123444', '', '765-8420', 'jpere@gmail.com'),
	(5, 3, 'Fernanda', 'Barragan', '1412342', '(234) 341-2341', '900-2541', 'fbarr@gmail.com'),
	(10, 5, 'Juan', 'Sarmiento', '57677667', '(454) 555-5555', '123-3333', '2124@sdfasdf.com'),
	(11, 5, 'xcc', 'cxvv', '123444', '(314) 141-3241', '234-1234', '@'),
	(12, 4, 'xddf', 'sdfa', '324121411', '', '', '@'),
	(13, 5, 'Vanessa', 'Vargas', '1234444', '(323) 111-4444', '332-2222', 'vane.vargas@gmail.com'),
	(14, 1, '122', '121', '123444', '', '', '@');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


-- Volcando estructura para tabla opline.conductor
DROP TABLE IF EXISTS `conductor`;
CREATE TABLE IF NOT EXISTS `conductor` (
  `nic` int(10) unsigned NOT NULL,
  `nombre` varchar(20) COLLATE utf16_spanish_ci NOT NULL,
  `apellido` varchar(30) COLLATE utf16_spanish_ci NOT NULL,
  `fecha_nacimiento` datetime NOT NULL,
  `nivel_escolaridad` varchar(25) COLLATE utf16_spanish_ci DEFAULT NULL,
  `direccion` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `correo` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `celular` varchar(20) COLLATE utf16_spanish_ci NOT NULL,
  `telefono` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `barrio` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `arp` varchar(25) COLLATE utf16_spanish_ci DEFAULT NULL,
  `eps` varchar(25) COLLATE utf16_spanish_ci DEFAULT NULL,
  `genero` varchar(10) COLLATE utf16_spanish_ci DEFAULT NULL,
  `estado_civil` varchar(40) COLLATE utf16_spanish_ci DEFAULT NULL,
  `num_hijos` int(10) unsigned DEFAULT NULL,
  `grupo_sangineo` varchar(5) COLLATE utf16_spanish_ci DEFAULT NULL,
  `cedula` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `expedida` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `foto` blob,
  PRIMARY KEY (`nic`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.conductor: ~10 rows (aproximadamente)
DELETE FROM `conductor`;
/*!40000 ALTER TABLE `conductor` DISABLE KEYS */;
INSERT INTO `conductor` (`nic`, `nombre`, `apellido`, `fecha_nacimiento`, `nivel_escolaridad`, `direccion`, `correo`, `celular`, `telefono`, `barrio`, `arp`, `eps`, `genero`, `estado_civil`, `num_hijos`, `grupo_sangineo`, `cedula`, `expedida`, `foto`) VALUES
	(301, 'Francisco', 'Payares', '1990-10-10 00:00:00', 'Bachillerato', 'Carrera 13 no 15 - 17', 'jjvanegas67@misena.edu.co', '2147483647', '4521035', 'suba', 'sura', 'saludtotal', 'Masculino', 'casado', 0, 'O+', '1254786951', 'Bogotá', NULL),
	(302, 'Rodolfo', 'Quevedo', '1989-05-10 00:00:00', 'Bachillerato', 'Carrera 15 no 20 - 21', 'rquevedo@gmail.com', '2147483647', '4102597', 'usme', 'compensar', 'compensar', 'hombre', 'casado', 4, 'O-', '1254203178', 'Bogotá', NULL),
	(303, 'Wilmer', 'Hurtado', '1992-02-28 00:00:00', 'Tecnico', 'Calle 20 no 35 - 39', 'whurta@gmail.com', '2147483647', '4002563', 'bosa', 'colpatria', 'famisanar', 'hombre', 'soltero', 0, 'A+', '1478596840', 'Bogotá', NULL),
	(304, 'Enrique', 'Tamora', '1984-05-20 00:00:00', 'Bachillerato', 'Calle 75   no 20 - 85 ', 'etam@gmail.com', '2147483647', '3685299', 'bosa', 'famisanar', 'famisanar', 'hombre', 'casado', 3, 'O+', '1869520471', 'Bogotá', NULL),
	(305, 'Ignacio', 'Uribe', '1991-06-26 00:00:00', 'Tecnico', 'Calle 20 no 95 - 20', 'iuribe@gmail.com', '2147483647', '2001587', 'santa fe', 'coomeva', 'coomeva', 'hombre', 'soltero', 0, 'O-', '1574863021', 'Bogotá', NULL),
	(306, 'Marcelo', 'Pastrana', '1990-02-27 00:00:00', 'Bachillerato', 'Carrera 25 no 30 - 90', 'mpastra@gmail.com', '2147483647', '3006985', 'santa fe', 'salud total', 'salud total', 'Masculino', 'casado', 2, 'O+', '1041230151', 'Bogotá', NULL),
	(307, 'Uriel', 'Carbajal', '1994-10-20 00:00:00', 'Bachillerato', 'Carrera 95 no 57 - 80', 'ucarba@gmail.com', '2147483647', '3017452', 'fontibon', 'coomeva', 'coomeva', 'Masculino', 'viudo', 1, 'A+', '1205743699', 'Bogotá', NULL),
	(308, 'Julio', 'Flores', '1985-02-02 00:00:00', 'Bachillerato', 'Calle 30 no 45 c - 90', 'jflor@gmail.com', '2147483647', '3002489', 'fontibon', 'sura', 'sura', 'hombre', 'soltero', 0, 'A-', '1985630475', 'Bogotá', NULL),
	(309, 'Dario', 'Hernandes', '1988-02-01 00:00:00', 'Bachillerato', 'Carrea 13 no 52 - 45', 'dhernan@gmail.com', '2147483647', '9856330', 'barrios unidos', 'compensar', 'compensar', 'hombre', 'casado', 0, 'AB', '1478630221', 'Bogotá', NULL),
	(310, 'Seabstian', 'Peralta', '1990-07-27 00:00:00', 'Bachillerato', 'Carrera 15 no 128 - 62', 'speral@gmail.com', '2147483647', '7689500', 'barrios unidos', 'collpatria', 'saludtotal', 'hombre', 'soltero', 1, 'O+', '1635920147', 'Bogotá', NULL);
/*!40000 ALTER TABLE `conductor` ENABLE KEYS */;


-- Volcando estructura para tabla opline.convenio
DROP TABLE IF EXISTS `convenio`;
CREATE TABLE IF NOT EXISTS `convenio` (
  `id_convenio` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_convenio` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf16_spanish_ci DEFAULT NULL,
  `fecha_inicio` date NOT NULL,
  `fecha_fin` date DEFAULT NULL,
  PRIMARY KEY (`id_convenio`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.convenio: ~6 rows (aproximadamente)
DELETE FROM `convenio`;
/*!40000 ALTER TABLE `convenio` DISABLE KEYS */;
INSERT INTO `convenio` (`id_convenio`, `nombre_convenio`, `descripcion`, `fecha_inicio`, `fecha_fin`) VALUES
	(1, 'Cinascar', 'Convenio de autos con la empresa cinascar', '2014-08-04', '2014-08-13'),
	(2, 'Hyunday ', 'Convenio de autos con la empresa hyunday', '2014-08-27', '2014-08-30'),
	(3, 'Gas narutal', 'Convenio de gas con la empresa gas naturalConvenio de gas con la empresa gas naturalConvenio de gas con la empresa gas natural', '2014-08-27', '2014-09-12'),
	(4, 'Chrevrolet', 'Retiro de camionetas', '2014-08-14', NULL),
	(5, 'Nissan', 'Convnio de autos con la empresa nissan', '2014-08-14', NULL),
	(6, 'Cornabis', 'Administración de riesgos laborales y de salud.', '2014-09-05', NULL);
/*!40000 ALTER TABLE `convenio` ENABLE KEYS */;


-- Volcando estructura para tabla opline.detalles_empleado_zona
DROP TABLE IF EXISTS `detalles_empleado_zona`;
CREATE TABLE IF NOT EXISTS `detalles_empleado_zona` (
  `id_empleado` int(10) unsigned NOT NULL,
  `id_zona` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_empleado`,`id_zona`),
  KEY `Empleado_has_Zona_FKIndex1` (`id_empleado`),
  KEY `Empleado_has_Zona_FKIndex2` (`id_zona`),
  CONSTRAINT `detalles_empleado_zona_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `detalles_empleado_zona_ibfk_2` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id_zona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.detalles_empleado_zona: ~11 rows (aproximadamente)
DELETE FROM `detalles_empleado_zona`;
/*!40000 ALTER TABLE `detalles_empleado_zona` DISABLE KEYS */;
INSERT INTO `detalles_empleado_zona` (`id_empleado`, `id_zona`) VALUES
	(1, 1),
	(2, 10),
	(5, 3),
	(6, 6),
	(7, 5),
	(8, 4),
	(9, 8),
	(10, 8),
	(11, 2),
	(12, 7),
	(12, 9);
/*!40000 ALTER TABLE `detalles_empleado_zona` ENABLE KEYS */;


-- Volcando estructura para tabla opline.detalles_encuesta_aplicada
DROP TABLE IF EXISTS `detalles_encuesta_aplicada`;
CREATE TABLE IF NOT EXISTS `detalles_encuesta_aplicada` (
  `id_pregunta` int(10) unsigned NOT NULL,
  `id_encuesta_aplicada` int(10) unsigned NOT NULL,
  `resultado` int(10) unsigned DEFAULT NULL,
  PRIMARY KEY (`id_pregunta`,`id_encuesta_aplicada`),
  KEY `Preguntas_has_EncuestaAplicada_FKIndex1` (`id_pregunta`),
  KEY `Preguntas_has_EncuestaAplicada_FKIndex2` (`id_encuesta_aplicada`),
  CONSTRAINT `detalles_encuesta_aplicada_ibfk_1` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta` (`id_pregunta`),
  CONSTRAINT `detalles_encuesta_aplicada_ibfk_2` FOREIGN KEY (`id_encuesta_aplicada`) REFERENCES `encuesta_aplicada` (`id_encuesta_aplicada`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.detalles_encuesta_aplicada: ~0 rows (aproximadamente)
DELETE FROM `detalles_encuesta_aplicada`;
/*!40000 ALTER TABLE `detalles_encuesta_aplicada` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalles_encuesta_aplicada` ENABLE KEYS */;


-- Volcando estructura para tabla opline.detalles_ruta_zona
DROP TABLE IF EXISTS `detalles_ruta_zona`;
CREATE TABLE IF NOT EXISTS `detalles_ruta_zona` (
  `id_ruta` int(10) unsigned NOT NULL,
  `id_zona` int(10) unsigned NOT NULL,
  `id_detalle_ruta` int(10) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id_detalle_ruta`),
  KEY `id_ruta` (`id_ruta`),
  KEY `id_zona` (`id_zona`),
  CONSTRAINT `detalles_ruta_zona_ibfk_1` FOREIGN KEY (`id_ruta`) REFERENCES `ruta` (`id_ruta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `detalles_ruta_zona_ibfk_2` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id_zona`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.detalles_ruta_zona: ~7 rows (aproximadamente)
DELETE FROM `detalles_ruta_zona`;
/*!40000 ALTER TABLE `detalles_ruta_zona` DISABLE KEYS */;
INSERT INTO `detalles_ruta_zona` (`id_ruta`, `id_zona`, `id_detalle_ruta`) VALUES
	(4, 9, 2),
	(5, 4, 3),
	(5, 6, 4),
	(6, 3, 5),
	(6, 10, 6),
	(7, 2, 7),
	(7, 10, 8);
/*!40000 ALTER TABLE `detalles_ruta_zona` ENABLE KEYS */;


-- Volcando estructura para tabla opline.detalles_viaje
DROP TABLE IF EXISTS `detalles_viaje`;
CREATE TABLE IF NOT EXISTS `detalles_viaje` (
  `id_viaje` int(10) unsigned NOT NULL,
  `id_cliente` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_viaje`,`id_cliente`),
  KEY `viaje_has_cliente_FKIndex1` (`id_viaje`),
  KEY `viaje_has_cliente_FKIndex2` (`id_cliente`),
  CONSTRAINT `detalles_viaje_ibfk_1` FOREIGN KEY (`id_viaje`) REFERENCES `viaje` (`id_viaje`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `detalles_viaje_ibfk_2` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.detalles_viaje: ~12 rows (aproximadamente)
DELETE FROM `detalles_viaje`;
/*!40000 ALTER TABLE `detalles_viaje` DISABLE KEYS */;
INSERT INTO `detalles_viaje` (`id_viaje`, `id_cliente`) VALUES
	(1, 1),
	(1, 2),
	(2, 1),
	(5, 3),
	(6, 1),
	(6, 4),
	(6, 5),
	(7, 2),
	(8, 4),
	(9, 1),
	(11, 2),
	(11, 5);
/*!40000 ALTER TABLE `detalles_viaje` ENABLE KEYS */;


-- Volcando estructura para tabla opline.empleado
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE IF NOT EXISTS `empleado` (
  `id_empleado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_estado` int(10) unsigned NOT NULL,
  `id_rol` int(10) unsigned NOT NULL,
  `nombre` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `apellido` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `cedula` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `sexo` int(11) NOT NULL,
  `expedida` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `celular` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `telefono` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `direccion` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `clave` varchar(255) COLLATE utf16_spanish_ci DEFAULT NULL,
  `correo` varchar(100) COLLATE utf16_spanish_ci DEFAULT NULL,
  `foto` varchar(300) COLLATE utf16_spanish_ci DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `fecha_registro` date DEFAULT NULL,
  PRIMARY KEY (`id_empleado`),
  KEY `Empleado_FKIndex1` (`id_rol`),
  KEY `id_estado` (`id_estado`),
  CONSTRAINT `empleado_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `empleado_ibfk_2` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.empleado: ~63 rows (aproximadamente)
DELETE FROM `empleado`;
/*!40000 ALTER TABLE `empleado` DISABLE KEYS */;
INSERT INTO `empleado` (`id_empleado`, `id_estado`, `id_rol`, `nombre`, `apellido`, `cedula`, `sexo`, `expedida`, `celular`, `telefono`, `direccion`, `clave`, `correo`, `foto`, `fecha_nacimiento`, `fecha_registro`) VALUES
	(1, 1, 1, 'Fernando', 'Suarez', '1248630457', 0, 'Bogotá', '(324)-144-4111', '620-1541', 'Carrera 14 no 68 - 15', '1\r\n', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413918139496iconoSetup.ico', '2014-11-04', '2014-11-05'),
	(2, 1, 2, 'Juan', 'Segura', '1754863201', 0, 'Bogotá', '3004105472', '5786245', 'Calle 28 no 14c 20', '456', 'jsegura@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(5, 1, 2, 'Bruno', 'Lopez', '4422', 1, 'Bogotá', '(300) 257-4120', '652-7460', 'Carrera 20 no 15c - 40', '25f9e794323b453885f5181f1b624d0b', 'bulop@etoc.com', 'imagenesRegistros/fotosEmpleados/1415341593996ValidarDocumento.png', NULL, '2014-11-05'),
	(6, 2, 4, 'Maria', 'Cordoba', '4294967295', 0, 'Bogotá', '3135842010', '2304578', 'Calle 20 no 45 - 85', '101112', 'macosta@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(7, 1, 4, 'Patricia', 'Santana', '4852103974', 0, 'Bogotá', '3132046897', '6521028', 'Carrera 30 no 15 - 15', '131415', 'pasanta@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(8, 2, 5, 'Cesar', 'Marquez', '1457863205', 0, 'Bogotá', '3135478051', '5008930', 'Calle 23 no 80 - 93', '161718', 'cmarque@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(9, 2, 5, 'Vanesa', 'Garces', '4710524863', 0, 'Bogotá', '3008469524', '3007462', 'Carrera 95 no 20c - 40', '192021', 'vgarc@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(10, 1, 3, 'Nanci', 'Morales', '4601257622', 0, 'Bogotá', '3105248785', '6015452', 'Carrera 50 no 54 - 84', '222324', 'namora@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(11, 1, 3, 'Bruno', 'Lopez', '1523698010', 0, 'Bogotá', '3002574120', '6527460', 'Carrera 20 no 15c - 40', '252627', 'bulop@etoc.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(12, 2, 2, 'Sofia', 'Andrade', '4875200145', 0, 'Bogotá', '(300) 595-6330', '845-7201', 'Calle 35 no  20c 60', '1410812061286', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413134114350hd-abstract-wallpaper.jpg', NULL, '2014-11-05'),
	(13, 1, 2, 'Francisco', 'Lopez', '5434678462', 0, 'Bogotá', '(222) 222-2222', '222-2222', 'Calle 30 no  20c 60', '15749a21bae8ff29b1a4ac86a8e14848', '@', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(14, 1, 2, 'Patricia', 'Nariño', '1334642345', 0, 'Bogotá', '(333) 333-3333', '222-2222 x22222', 'Calle 35 no  30c 60', 'e56a2b4d8f03973f1d3fdd2a60faa4f6', '@', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(15, 1, 2, 'John ', 'Vargas', '2341234767', 0, 'Bogotá', '(333) 333-3333', '333-3333 x33333', '', 'eb882e72439e183d67b9cc4302cb7761', 'John@Vagas', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(16, 1, 2, 'Armando', 'Vargas', '1874562387', 0, 'Bogotá', NULL, NULL, NULL, 'pruebas', NULL, 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(19, 1, 1, 'Pedro', 'Trujillo', '1234577654', 0, 'Bogotá', '12345', 'XD', 'Calle 45 sur #7-89', 'Opline-12345', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(20, 1, 2, 'Pancrasio Pancrasio', 'Rodrigues Rodriguez', '1241234879', 0, 'Bogotá', '(321) 239-9999', '323-1411', 'Calle 35 no  20c 61', '6f14d5a81c56ed32d584f0b48a3447cb', 'VargasDX', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(21, 1, 2, 'Nanci', 'Perlta', '4567823478', 0, 'Bogotá', '(232) 342-3423', '234-2342', 'Calle 35 no  20c', 'bcf90da50a235f03152e53acd303d03e', 'FSKDFJSL', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(22, 2, 3, 'Wilmer', 'Gonzales', '12341444', 0, 'Bogotá', '(122) 222-2222', '234-2342', 'Calle 35 no 31', '61b80f94cdd6d632f7bc38fd9ed91d9c', '@wrwerw', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(23, 1, 2, 'Andrés', 'Marulanda', '12345', 0, 'Bogotá', '1234', '243-2423', 'Carrera 16 B #63 D 42 Sur', '12345', 'jcsarmiento04@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(24, 2, 1, 'Fabiola', 'Armero', '4875200145', 0, 'Bogotá', '(300) 595-6330', '845-7201', 'Calle 35 no  20c 60', 'ae38117130ce280a0c01cfbfe42fba1a', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413865975624Captura.png', NULL, '2014-11-05'),
	(25, 1, 2, 'Jorge Enrique', 'Segura', '1024954837', 0, 'Bogotá', '3105717625', '4567822', 'Calle 95 N 7', 'subgerente', 'jorgese2009@hotmail.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(27, 2, 4, 'Sofia', 'Marulanda', '4875200145', 0, 'Bogotá', '(300) 595-6330', '845-7201', 'Calle 35 no  20c 60', '1410805887835', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413865779797Captura.png', NULL, '2014-11-05'),
	(28, 1, 3, 'Alicia', 'Vargas', '123456', 0, 'Bogotá', '(234) 222-3444', '565-6666', 'Carrera 16 B #63 D 42 Sur', '827ccb0eea8a706c4c34a16891f84e7b', 'alis@gmail.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(29, 1, 2, 'Usuario', 'Premium', '12344441', 0, 'Bogotá', '(321) 111-1111', '845-7201', 'Calle 35 no  20c 60', 'c4ca4238a0b923820dcc509a6f75849b', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1414965538751Captura.png', '2014-11-04', '2014-11-05'),
	(37, 1, 1, 'Juan', 'Marulanda', '4875200145', 0, 'Bogotá', '(300) 595-6330', '845-7201', 'Calle 35 no  20c 60', '1410803401078', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413866001790iconoSetup.ico', NULL, '2014-11-05'),
	(38, 1, 2, 'John', 'Vanegas', '1234111', 0, 'Bogotá', '(342) 222-2112', '213-3131', '23421', '219a06295b4bd773e2486015ca578d2f', 'XD@gmail.com', 'imagenesRegistros/fotosEmpleados/1410748500191Captura.png', NULL, '2014-11-05'),
	(39, 1, 1, 'John', 'Vargas', '123411', 0, 'Bogotá', '(322) 322-3222', '234-2322', 'Car 90', '1410796368377', 'jjvanegas67@gmail.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(40, 1, 2, 'XDFULL', 'SDs', 'SDs', 0, 'SDs', '(233) 333-3333', '232-2222', 'jksd', '1410796472431', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(41, 1, 2, 'JJs', 'JJs', '6777888931', 1, 'Bogotá', '(323) 222-2121', '322-3222', '2341', '531d352c684d681afc38be758edc7e8e', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1415482685747cam.png', NULL, '2014-11-05'),
	(42, 1, 1, 'Juan camilo', 'Sarmiento', '24312411', 0, 'Bogotá', '(323) 411-1111', '324-1241', 'sdfsdffs', '1410797583662', 'jcsarmiento04@misena.edu.co ', 'imagenesRegistros/fotosEmpleados/1413908954953Sin tÃ­tulo.png', NULL, '2014-11-05'),
	(43, 1, 2, 'Andrés', 'Marulanda', '123444', 1, 'Bogotá', '(324) 242-3222', '423-4322', 'Bogotá', '1410797633306', 'afmarulanda74@misena.edu.co ', 'imagenesRegistros/fotosEmpleados/1415341687901ValidarDocumento.png', NULL, '2014-11-05'),
	(44, 1, 2, 'Miguel Steven', 'Lopes', '123412', 2, 'Bogotá', '(323) 131-3111', '323-3222', 'Bogotá', '1410797743461', 'mslopez44@misena.edu.co ', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(45, 2, 2, 'sdf', 'sdf', 'sdf', 0, 'sdf', '(222) 222-2222', '222-2222', '2411', '1410797827274', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(46, 1, 4, 'Miguel', 'Vargas', '3242342', 0, 'Bogotá', '(323) 423-2222', '322-3423', 'sdfl', '1410799007505', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(47, 2, 1, 'Alejandra ', 'Suarez', '34232', 0, 'Bogotá', '(322) 423-2322', '324-2322', 'XDd', '1410799431220', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(48, 2, 1, 'xddd', 'dddd', '111111', 0, 'Bogotá', '(323) 411-1121', '234-2422', 'XDDD', '1410799708041', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(49, 2, 1, 'sdf', 'sdf', 'sdf', 0, 'sdf', '(222) 222-2222', '222-2222 x22222', 'sdl', '1410800592071', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(50, 2, 2, 'JosÃ©', 'Vargas', '1', 2, 'BogotÃ¡', '(333)-333-3333', '333-3333 x33333', 'yyyyyy', 'c4ca4238a0b923820dcc509a6f75849b', 'jarvanux@gmail.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(51, 1, 2, 'sdf', 'sdf', '2', 0, 'sdf', '(222) 222-2222', '222-2222 x22222', 'jksdfsf', '1415338554364', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(52, 1, 4, 'dfsdfsdf', 'sdfs', 'sdf', 0, 'sdfsdfsd', '(234) 234-2342', '234-2342 x34223', 'jskdlsdf', '1410812938078', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(53, 2, 3, 'Sofia', 'Andrade', '4875200145', 0, 'Bogotá', '(300) 595-6330', '845-7201', 'Calle 35 no  20c 60', '1410813587684', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1413865924217XD.png', NULL, '2014-11-05'),
	(54, 1, 1, 'John jaider', 'Vanegas', '1110552476', 0, 'Bogotá', '(321) 239-9838', '234-2222', 'Carrera XD', '1413873582787', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(55, 1, 3, 'Fabio', 'Vargas', '133444111', 0, 'Bogotá', '(323) 244-4111', '234-2422', 'Calle 30', '1413914030534', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(56, 2, 1, 'Henrry', 'Garzón', '12341444', 0, 'Bogotá', '(324) 242-2232', '324-2422', 'Carrera 20', '1413921262033', 'henry.garzon@misena.edu.co ', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(57, 1, 2, 'Prueba', 'Prueba', '2123411', 0, 'Bogotá', '(324) 222-3444', '232-4232', 'Carrera 22', '1413922963611', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(58, 2, 3, 'Orfilia', 'Dueñas', '2412411', 0, 'Bogotá', '(324) 242-2222', '234-2342', 'Calle 222', '1414291375355', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1414291370584Captura.png', NULL, '2014-11-05'),
	(60, 1, 1, 'Pepe', 'Prieto', '322111111', 0, 'Bogotá', '(321) 233-4444', '321-2344', 'Calle 22', '1414964186569', 'pprieto@gmail.com', 'imagenesRegistros/fotosEmpleados/1414964099759Captura.png', NULL, '2014-11-05'),
	(61, 1, 2, 'Juan Camila', 'Sarmiento', '3214444', 0, 'Bogotá', '(321) 444-4444', '321-4444', 'yutgyyr57yttytety', '1415073859447', ',j6ujn hulukjuthyrm@', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', NULL, '2014-11-05'),
	(62, 1, 1, 'Juan', 'Valdez', '13455', 1, 'Bogotá', '(234) 141-2412', '314-1241', 'Calle 10', '29d00682d658dcaae1ab172fb06ca04d', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', '2014-11-07', '2014-11-07'),
	(63, 1, 3, 'Ana', 'Vargas', '1244125', 2, 'Bogotá', '(314) 111-1111', '341-2411', 'xd', 'c78b9b1aafea3a9d08e04ca9910178f0', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', '2014-11-07', '2014-11-07'),
	(64, 1, 4, 'Juana', 'Peres', '1234567', 2, 'Bogotá', '(323) 444-4444', '234-4444', 'calle 23', 'c81e728d9d4c2f636f067f89cc14862c', 'juana@gmail.com', 'imagenesRegistros/fotosEmpleados/perfilPredeterminado.png', '1956-11-15', '2014-11-09'),
	(65, 1, 1, 'Juan', 'Vargas', '321', 1, 'Bogotá', '(314)-144-1111', '234-1111', 'Calle 23', 'eccbc87e4b5ce2fe28308fd9f2a7baf3', 'jjvanegas67@misena.edu.co', 'imagenesRegistros/fotosEmpleados/1415581802319ValidarDocumento.png', '1959-11-19', '2014-11-09');
/*!40000 ALTER TABLE `empleado` ENABLE KEYS */;


-- Volcando estructura para tabla opline.empleado_reunion
DROP TABLE IF EXISTS `empleado_reunion`;
CREATE TABLE IF NOT EXISTS `empleado_reunion` (
  `id_empleado` int(10) unsigned NOT NULL,
  `id_reunion` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_empleado`,`id_reunion`),
  KEY `empleado_has_reunion_FKIndex1` (`id_empleado`),
  KEY `empleado_has_reunion_FKIndex2` (`id_reunion`),
  CONSTRAINT `empleado_reunion_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `empleado_reunion_ibfk_2` FOREIGN KEY (`id_reunion`) REFERENCES `reunion` (`id_reunion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.empleado_reunion: ~14 rows (aproximadamente)
DELETE FROM `empleado_reunion`;
/*!40000 ALTER TABLE `empleado_reunion` DISABLE KEYS */;
INSERT INTO `empleado_reunion` (`id_empleado`, `id_reunion`) VALUES
	(1, 1),
	(1, 2),
	(2, 2),
	(5, 4),
	(6, 4),
	(7, 4),
	(8, 1),
	(8, 3),
	(9, 3),
	(10, 5),
	(11, 1),
	(11, 5),
	(12, 3),
	(12, 5);
/*!40000 ALTER TABLE `empleado_reunion` ENABLE KEYS */;


-- Volcando estructura para tabla opline.encuesta
DROP TABLE IF EXISTS `encuesta`;
CREATE TABLE IF NOT EXISTS `encuesta` (
  `id_encuesta` int(10) NOT NULL AUTO_INCREMENT,
  `titulo` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `descripcion` varchar(200) COLLATE utf16_spanish_ci DEFAULT NULL,
  `fecha_creacion` datetime NOT NULL,
  PRIMARY KEY (`id_encuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.encuesta: ~2 rows (aproximadamente)
DELETE FROM `encuesta`;
/*!40000 ALTER TABLE `encuesta` DISABLE KEYS */;
INSERT INTO `encuesta` (`id_encuesta`, `titulo`, `descripcion`, `fecha_creacion`) VALUES
	(1, 'Encuesta1', 'Descripción 1', '2014-09-03 15:47:18'),
	(2, 'sdfff', 'sdfdf', '2014-09-26 16:06:55');
/*!40000 ALTER TABLE `encuesta` ENABLE KEYS */;


-- Volcando estructura para tabla opline.encuesta_aplicada
DROP TABLE IF EXISTS `encuesta_aplicada`;
CREATE TABLE IF NOT EXISTS `encuesta_aplicada` (
  `id_encuesta_aplicada` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_encuesta` int(10) unsigned NOT NULL,
  `id_cliente` int(10) unsigned DEFAULT NULL,
  `nic` int(10) unsigned DEFAULT NULL,
  `fecha_aplicada` datetime NOT NULL,
  `cantidad_aplicada` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_encuesta_aplicada`),
  KEY `EncuestaAplicada_FKIndex1` (`id_encuesta`),
  KEY `encuesta_aplicada_FKIndex2` (`nic`),
  KEY `encuesta_aplicada_FKIndex3` (`id_cliente`),
  CONSTRAINT `encuesta_aplicada` FOREIGN KEY (`id_encuesta`) REFERENCES `detalles_encuesta_aplicada` (`id_encuesta_aplicada`),
  CONSTRAINT `encuesta_aplicada_ibfk_2` FOREIGN KEY (`nic`) REFERENCES `conductor` (`nic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `encuesta_aplicada_ibfk_3` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.encuesta_aplicada: ~0 rows (aproximadamente)
DELETE FROM `encuesta_aplicada`;
/*!40000 ALTER TABLE `encuesta_aplicada` DISABLE KEYS */;
/*!40000 ALTER TABLE `encuesta_aplicada` ENABLE KEYS */;


-- Volcando estructura para tabla opline.estado
DROP TABLE IF EXISTS `estado`;
CREATE TABLE IF NOT EXISTS `estado` (
  `id_estado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(40) COLLATE utf16_spanish_ci NOT NULL,
  PRIMARY KEY (`id_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.estado: ~2 rows (aproximadamente)
DELETE FROM `estado`;
/*!40000 ALTER TABLE `estado` DISABLE KEYS */;
INSERT INTO `estado` (`id_estado`, `descripcion`) VALUES
	(1, 'activo'),
	(2, 'inactivo');
/*!40000 ALTER TABLE `estado` ENABLE KEYS */;


-- Volcando estructura para tabla opline.historial_estado
DROP TABLE IF EXISTS `historial_estado`;
CREATE TABLE IF NOT EXISTS `historial_estado` (
  `idhistorial_estado` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `tabla` varchar(50) COLLATE utf16_spanish_ci NOT NULL,
  `id_registro` int(10) unsigned NOT NULL,
  `fecha` date NOT NULL,
  `id_estado` int(10) unsigned NOT NULL,
  `justificacion` varchar(150) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`idhistorial_estado`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.historial_estado: ~3 rows (aproximadamente)
DELETE FROM `historial_estado`;
/*!40000 ALTER TABLE `historial_estado` DISABLE KEYS */;
INSERT INTO `historial_estado` (`idhistorial_estado`, `tabla`, `id_registro`, `fecha`, `id_estado`, `justificacion`) VALUES
	(1, 'asociado', 1, '2014-02-10', 1, ''),
	(2, 'zona', 1, '2014-05-10', 0, ''),
	(3, 'empleado', 1, '2014-10-01', 0, '');
/*!40000 ALTER TABLE `historial_estado` ENABLE KEYS */;


-- Volcando estructura para tabla opline.informacion_empresa
DROP TABLE IF EXISTS `informacion_empresa`;
CREATE TABLE IF NOT EXISTS `informacion_empresa` (
  `id_empresa` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `gerente` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `celular` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `telefono` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `direccion` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `pagina_facebook` varchar(250) COLLATE utf16_spanish_ci DEFAULT '0',
  `pagina_twitter` varchar(250) COLLATE utf16_spanish_ci DEFAULT '0',
  `pagina_google` varchar(250) COLLATE utf16_spanish_ci DEFAULT '0',
  `pagina_in` varchar(250) COLLATE utf16_spanish_ci DEFAULT '0',
  `correo` varchar(200) COLLATE utf16_spanish_ci DEFAULT NULL,
  `clave_correo` varchar(250) COLLATE utf16_spanish_ci DEFAULT NULL,
  `sitio_web` varchar(250) COLLATE utf16_spanish_ci DEFAULT NULL,
  `ciudad` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  `pais` varchar(20) COLLATE utf16_spanish_ci DEFAULT '0',
  PRIMARY KEY (`id_empresa`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.informacion_empresa: ~1 rows (aproximadamente)
DELETE FROM `informacion_empresa`;
/*!40000 ALTER TABLE `informacion_empresa` DISABLE KEYS */;
INSERT INTO `informacion_empresa` (`id_empresa`, `nombre`, `gerente`, `celular`, `telefono`, `direccion`, `pagina_facebook`, `pagina_twitter`, `pagina_google`, `pagina_in`, `correo`, `clave_correo`, `sitio_web`, `ciudad`, `pais`) VALUES
	(1, 'Empresa ETOC', 'Francisco', '(321) 239-9838', '242-3424', 'Calle 52 No. 13-65', 'https://www.facebook.com/', 'https://twitter.com/', '', 'https://www.linkedin.com/company/tu-red-social.com.ve', 'sistemaopline@gmail.com', 'sistemaopline2014', 'www.etoc.com', 'Bogotá', 'Colombia');
/*!40000 ALTER TABLE `informacion_empresa` ENABLE KEYS */;


-- Volcando estructura para tabla opline.menu
DROP TABLE IF EXISTS `menu`;
CREATE TABLE IF NOT EXISTS `menu` (
  `id_menu` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_rol` int(10) unsigned NOT NULL,
  `nombre` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_menu`),
  KEY `categoria_FKIndex1` (`id_rol`),
  CONSTRAINT `menu_ibfk_1` FOREIGN KEY (`id_rol`) REFERENCES `rol` (`id_rol`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=37 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.menu: ~31 rows (aproximadamente)
DELETE FROM `menu`;
/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`id_menu`, `id_rol`, `nombre`) VALUES
	(1, 1, 'Reportes'),
	(2, 2, 'Empleados'),
	(3, 4, 'Asociados'),
	(4, 4, 'Pagos'),
	(5, 4, 'Certificados'),
	(6, 4, 'Zonas'),
	(7, 4, 'Reuniones'),
	(8, 5, 'Zonas'),
	(11, 4, 'Clientes'),
	(12, 1, 'Informes'),
	(13, 1, 'Empleados'),
	(14, 1, 'Zona'),
	(15, 1, 'Convenios'),
	(16, 1, 'Reuniones'),
	(17, 1, 'Asociado'),
	(18, 1, 'Pagos'),
	(19, 1, 'Certificados'),
	(21, 2, 'Informes'),
	(22, 2, 'Zonas'),
	(23, 2, 'Clientes'),
	(24, 2, 'Convenio'),
	(26, 2, 'Reuniones'),
	(27, 2, 'Asociado'),
	(28, 2, 'Pagos'),
	(29, 3, 'Reuniones'),
	(30, 3, 'Asociado'),
	(31, 3, 'Pagos'),
	(32, 3, 'Zona'),
	(33, 5, 'Clientes'),
	(34, 5, 'Reuniones'),
	(36, 1, 'Información empresa');
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


-- Volcando estructura para tabla opline.notificaciones
DROP TABLE IF EXISTS `notificaciones`;
CREATE TABLE IF NOT EXISTS `notificaciones` (
  `id_notificacion` int(11) NOT NULL AUTO_INCREMENT,
  `tipo_notificacion` varchar(1) COLLATE utf16_spanish_ci NOT NULL DEFAULT '0',
  `fecha_notificacion` datetime NOT NULL,
  `rol_destino` int(11) NOT NULL DEFAULT '0',
  `descripcion` varchar(20) COLLATE utf16_spanish_ci NOT NULL DEFAULT '0',
  `estado` varchar(1) COLLATE utf16_spanish_ci NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_notificacion`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.notificaciones: ~0 rows (aproximadamente)
DELETE FROM `notificaciones`;
/*!40000 ALTER TABLE `notificaciones` DISABLE KEYS */;
/*!40000 ALTER TABLE `notificaciones` ENABLE KEYS */;


-- Volcando estructura para tabla opline.opcion
DROP TABLE IF EXISTS `opcion`;
CREATE TABLE IF NOT EXISTS `opcion` (
  `id_opcion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_menu` int(10) unsigned NOT NULL,
  `nombre` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `url` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_opcion`),
  KEY `opcion_FKIndex1` (`id_menu`),
  CONSTRAINT `opcion_ibfk_1` FOREIGN KEY (`id_menu`) REFERENCES `menu` (`id_menu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.opcion: ~38 rows (aproximadamente)
DELETE FROM `opcion`;
/*!40000 ALTER TABLE `opcion` DISABLE KEYS */;
INSERT INTO `opcion` (`id_opcion`, `id_menu`, `nombre`, `url`) VALUES
	(1, 1, 'Generar', 'reportes.xhtml'),
	(3, 2, 'Ver Registros', 'empleado.xhtml'),
	(5, 4, 'Etoc', 'pago.xhtml'),
	(6, 4, 'Convenio', 'pagoConvenio.xhtml'),
	(9, 6, 'Consultar', 'zonaConsultar.xhtml'),
	(10, 7, 'Listar', 'reunion.xhtml'),
	(11, 3, 'Listar', 'asociado.xhtml'),
	(12, 8, 'Consultar', 'zonaConsultar.xhtml'),
	(13, 33, 'Administrar Clientes', 'cliente.xhtml'),
	(14, 8, 'Gestión de viajes', 'viajes.xhtml'),
	(17, 5, 'Consultar', 'consultarCertificados.xhtml'),
	(19, 11, 'Gestión Clientes', 'cliente.xhtml'),
	(20, 12, 'Generar informes', 'generarInformes.xhtml'),
	(21, 13, 'Consultar', 'consultarEmpleados.xhtml'),
	(22, 1, 'Consultar Zona', 'zonaConsultar.xhtml'),
	(23, 15, 'Gestión convenios', 'convenio.xhtml'),
	(24, 16, 'Consultar Reuniones', 'convenio.xhtml'),
	(25, 17, 'Consultar Asociados', 'consultarAsociados.xhtml'),
	(26, 18, 'Consultar Pagos', 'pagoConsultar.xhtml'),
	(27, 18, 'Consultar Pagos Convenio', 'pagoConvenioConsultar.xhtml'),
	(31, 21, 'Generar informes', 'generarInformes.xhtml'),
	(32, 22, 'Gestión Zonas', 'zona.xhtml'),
	(33, 23, 'Gestión clientes', 'cliente.xhtml'),
	(34, 24, 'Gestión Convenios', 'convenio.xhtml'),
	(36, 26, 'Gestión reuniones', 'reunion.xhtml'),
	(37, 27, 'Gestión Asociado', 'asociado.xhtml'),
	(38, 28, 'Etoc', 'pago.xhtml'),
	(39, 28, 'Convenio', 'pagoConvenio.xhtml'),
	(40, 29, 'Gestión reuniones', 'reunion.xhtml'),
	(41, 30, 'Gestión asociado', 'asociado.xhtml'),
	(42, 30, 'Gestión asociado', 'asociado.xhtml'),
	(43, 31, 'Etoc', 'pago.xhtml'),
	(44, 31, 'Convenio', 'pagoConvenio.xhtml'),
	(45, 32, 'Consultar Zona', 'zonaConsultar.xhtml'),
	(46, 34, 'Consultar', 'reunionConsultar.xhtml'),
	(47, 36, 'Editar', 'informacionEmpresa.xhtml'),
	(49, 5, 'Petición', 'peticionCertificado.xhtml'),
	(50, 19, 'Certificados', 'CalificarCertificados.xhtml');
/*!40000 ALTER TABLE `opcion` ENABLE KEYS */;


-- Volcando estructura para tabla opline.opcion_respuesta
DROP TABLE IF EXISTS `opcion_respuesta`;
CREATE TABLE IF NOT EXISTS `opcion_respuesta` (
  `id_opcions_respuesta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_pregunta` int(10) unsigned NOT NULL,
  `descripcion` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  `veces_seleccion` int(11) DEFAULT NULL,
  `tipo` varchar(1) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_opcions_respuesta`),
  KEY `opcions_respuesta_FKIndex1` (`id_pregunta`),
  CONSTRAINT `FK_pregunta_opcion_respuesta` FOREIGN KEY (`id_pregunta`) REFERENCES `pregunta` (`id_pregunta`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.opcion_respuesta: ~2 rows (aproximadamente)
DELETE FROM `opcion_respuesta`;
/*!40000 ALTER TABLE `opcion_respuesta` DISABLE KEYS */;
INSERT INTO `opcion_respuesta` (`id_opcions_respuesta`, `id_pregunta`, `descripcion`, `veces_seleccion`, `tipo`) VALUES
	(1, 6, 'sfsdfsd', NULL, 'o'),
	(2, 6, 'sdfsdfs', NULL, 'p');
/*!40000 ALTER TABLE `opcion_respuesta` ENABLE KEYS */;


-- Volcando estructura para tabla opline.pago
DROP TABLE IF EXISTS `pago`;
CREATE TABLE IF NOT EXISTS `pago` (
  `numero_recibo` int(10) unsigned NOT NULL,
  `id_asociado` int(10) unsigned NOT NULL,
  `id_tipo_pago` int(10) unsigned NOT NULL,
  `fecha_pago` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `valor_pago` double NOT NULL,
  `observacion` varchar(200) COLLATE utf16_spanish_ci DEFAULT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_recibo`),
  KEY `Pago_FKIndex1` (`id_tipo_pago`),
  KEY `pago_FKIndex2` (`id_asociado`),
  CONSTRAINT `pago_ibfk_1` FOREIGN KEY (`id_tipo_pago`) REFERENCES `tipo_pago` (`id_tipo_pago`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `pago_ibfk_2` FOREIGN KEY (`id_asociado`) REFERENCES `asociado` (`id_asociado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.pago: ~6 rows (aproximadamente)
DELETE FROM `pago`;
/*!40000 ALTER TABLE `pago` DISABLE KEYS */;
INSERT INTO `pago` (`numero_recibo`, `id_asociado`, `id_tipo_pago`, `fecha_pago`, `fecha_inicio`, `fecha_fin`, `valor_pago`, `observacion`, `id_empleado`) VALUES
	(123, 4, 4, '2014-11-02 23:05:16', '2014-11-02 23:05:18', '2014-11-02 23:05:19', 21, '11', NULL),
	(1234, 2, 3, '2014-11-09 13:03:47', NULL, NULL, 2344, NULL, 64),
	(1236, 2, 3, '2014-11-09 13:21:28', NULL, NULL, 23444, NULL, 64),
	(2144, 2, 2, '2014-11-05 01:30:54', '2014-11-05 00:00:00', NULL, 233, '111', NULL),
	(12444, 2, 3, '2014-11-09 12:47:47', NULL, NULL, 0, NULL, 64),
	(1234415, 2, 3, '2014-11-09 13:14:32', NULL, NULL, 2134, NULL, 64);
/*!40000 ALTER TABLE `pago` ENABLE KEYS */;


-- Volcando estructura para tabla opline.pago_convenio
DROP TABLE IF EXISTS `pago_convenio`;
CREATE TABLE IF NOT EXISTS `pago_convenio` (
  `numero_consig` int(10) unsigned NOT NULL,
  `id_convenio` int(10) unsigned NOT NULL,
  `id_vehiculo` int(10) unsigned NOT NULL,
  `fecha_consignacion` datetime NOT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `valor_consignacion` double NOT NULL,
  `observacion` varchar(300) COLLATE utf16_spanish_ci DEFAULT NULL,
  `id_empleado` int(11) DEFAULT NULL,
  PRIMARY KEY (`numero_consig`),
  KEY `PagoConvenio_FKIndex1` (`id_vehiculo`,`id_convenio`),
  KEY `FK_convenio` (`id_convenio`),
  CONSTRAINT `FK_convenio` FOREIGN KEY (`id_convenio`) REFERENCES `convenio` (`id_convenio`),
  CONSTRAINT `FK_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.pago_convenio: ~24 rows (aproximadamente)
DELETE FROM `pago_convenio`;
/*!40000 ALTER TABLE `pago_convenio` DISABLE KEYS */;
INSERT INTO `pago_convenio` (`numero_consig`, `id_convenio`, `id_vehiculo`, `fecha_consignacion`, `fecha_inicio`, `fecha_fin`, `valor_consignacion`, `observacion`, `id_empleado`) VALUES
	(123, 2, 2, '2014-09-01 16:11:30', '2014-09-01 16:11:30', '2014-09-01 16:11:30', 134, NULL, NULL),
	(136, 2, 12, '2014-01-15 00:00:00', NULL, NULL, 250000, NULL, NULL),
	(154, 1, 2, '2014-03-20 00:00:00', NULL, NULL, 250000, NULL, NULL),
	(160, 1, 3, '2014-02-20 00:00:00', NULL, NULL, 250000, NULL, NULL),
	(190, 3, 7, '2014-04-28 00:00:00', NULL, NULL, 180000, NULL, NULL),
	(195, 2, 15, '2014-05-05 00:00:00', NULL, NULL, 250000, NULL, NULL),
	(201, 1, 2, '2014-07-21 00:00:00', NULL, NULL, 300000, NULL, NULL),
	(202, 1, 3, '2014-07-24 00:00:00', NULL, NULL, 300000, NULL, NULL),
	(203, 3, 7, '2014-07-27 00:00:00', NULL, NULL, 220000, NULL, NULL),
	(204, 2, 12, '2014-07-27 00:00:00', NULL, NULL, 300000, NULL, NULL),
	(205, 2, 15, '2014-07-27 00:00:00', NULL, NULL, 300000, NULL, NULL),
	(552, 2, 5, '2014-08-31 22:29:51', '2014-08-31 22:29:51', '2014-08-31 22:29:51', 12341, NULL, NULL),
	(1231, 2, 2, '2014-11-05 02:03:05', '2014-11-05 02:03:05', '2014-11-05 02:03:05', 0, '', NULL),
	(2342, 2, 3, '2014-09-02 07:51:30', '2014-09-02 07:51:30', '2014-09-02 07:51:30', 0, NULL, NULL),
	(21345, 2, 2, '2014-08-31 15:06:46', NULL, NULL, 7889999, NULL, NULL),
	(21346, 1, 2, '2014-08-31 16:26:11', '2014-08-31 16:26:11', '2014-08-31 16:26:11', 242234234, NULL, NULL),
	(21347, 1, 2, '2014-08-31 16:29:39', '2014-08-31 16:29:39', '2014-08-31 16:29:39', 2412341, NULL, NULL),
	(21348, 2, 5, '2014-08-31 16:31:04', '2014-08-31 16:31:04', '2014-08-31 16:31:04', 21341, NULL, NULL),
	(21349, 2, 5, '2014-08-31 16:44:31', '2014-08-31 16:44:31', '2014-08-31 00:00:00', 1234, '', NULL),
	(21350, 2, 2, '2014-08-31 22:23:34', '2014-08-31 22:23:34', '2014-08-31 22:23:34', 12431, NULL, NULL),
	(21351, 2, 3, '2014-08-31 22:24:28', '2014-08-31 22:24:28', '2014-08-31 00:00:00', 1241, 'SDFSDF', NULL),
	(333333, 3, 7, '2014-09-05 15:57:10', '2014-09-05 15:57:10', '2014-09-05 15:57:10', 33333, NULL, NULL),
	(4444444, 1, 2, '2014-09-05 16:18:11', '2014-09-05 16:18:11', '2014-09-05 16:18:11', 677777, NULL, NULL),
	(5555555, 2, 2, '2014-10-01 18:30:02', '2014-10-01 18:30:02', '2014-10-07 00:00:00', 23422, 'xd', NULL);
/*!40000 ALTER TABLE `pago_convenio` ENABLE KEYS */;


-- Volcando estructura para tabla opline.pregunta
DROP TABLE IF EXISTS `pregunta`;
CREATE TABLE IF NOT EXISTS `pregunta` (
  `id_pregunta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `texto_pregunta` varchar(200) COLLATE utf16_spanish_ci NOT NULL,
  `descripcion` varchar(300) COLLATE utf16_spanish_ci DEFAULT NULL,
  `tipo` varchar(1) COLLATE utf16_spanish_ci NOT NULL,
  `id_encuesta` int(10) NOT NULL,
  PRIMARY KEY (`id_pregunta`),
  KEY `id_encuesta` (`id_encuesta`),
  CONSTRAINT `FK_encuesta_pregunta` FOREIGN KEY (`id_encuesta`) REFERENCES `encuesta` (`id_encuesta`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.pregunta: ~2 rows (aproximadamente)
DELETE FROM `pregunta`;
/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
INSERT INTO `pregunta` (`id_pregunta`, `texto_pregunta`, `descripcion`, `tipo`, `id_encuesta`) VALUES
	(4, 'ooo', 'pppp', 'o', 1),
	(6, 'DSF', 'DSFSD', 'O', 1);
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;


-- Volcando estructura para tabla opline.reunion
DROP TABLE IF EXISTS `reunion`;
CREATE TABLE IF NOT EXISTS `reunion` (
  `id_reunion` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `estado_reunion` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `descripcion` varchar(255) COLLATE utf16_spanish_ci DEFAULT NULL,
  `fecha_reunion` datetime DEFAULT NULL,
  `lugar` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  `nombre` varchar(40) COLLATE utf16_spanish_ci DEFAULT NULL,
  `acta` longblob,
  PRIMARY KEY (`id_reunion`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.reunion: ~17 rows (aproximadamente)
DELETE FROM `reunion`;
/*!40000 ALTER TABLE `reunion` DISABLE KEYS */;
INSERT INTO `reunion` (`id_reunion`, `estado_reunion`, `descripcion`, `fecha_reunion`, `lugar`, `nombre`, `acta`) VALUES
	(1, 'finalizado', 'reunión de empleados', '2014-07-27 00:00:00', 'Sena calle 52', 'Sena2', NULL),
	(2, 'activo', 'reunion con los transportadores', '2014-06-10 00:00:00', 'Oficinas de corabastos', 'Transportadores', NULL),
	(3, 'cancelada', 'reunion con los transportadores', '2014-06-15 00:00:00', 'Oficinas de corabastos', 'Transportadores capacitación', NULL),
	(4, 'activo', 'reunion con los coordinadores', '2014-05-20 00:00:00', 'Oficina de Etoc', 'Charla con los coordinadores', NULL),
	(5, 'cancelada', 'capacitar a los nuevos transportadores', '2014-05-21 00:00:00', 'Oficina de corabastos', 'Capacitar transportadores', NULL),
	(7, 'Finalizada', 'reunion finalizada', '2014-08-15 00:00:00', 'ss', 'Ss', NULL),
	(8, 'Activo', 'reunion de empleados', '2014-08-25 00:00:00', 'ui', 'ghf', NULL),
	(9, 'Activo', 'Buscar respuestas de convenios', '2014-09-02 00:00:00', 'Bodega 14', 'Buscar Respuestas', NULL),
	(10, 'Activo', 'Definir soluciones para sueldo de empleados.', '2014-09-01 00:00:00', 'Bodega 13', 'Pagos del mes', NULL),
	(11, 'Activa', 'XD', '2014-09-09 00:00:00', 'xx', 'Reunión', NULL),
	(12, 'Activa', 'sdfff', '2014-10-21 00:00:00', 'sdf', 'XD2', NULL),
	(13, 'Activa', '', NULL, '', 'xd2', NULL),
	(14, 'Activa', '', NULL, '', 'xd3', NULL),
	(15, 'Activa', '', '2014-10-07 00:00:00', 'sddf', 'xddf', NULL),
	(16, 'Activa', '', '2014-10-12 00:00:00', 'Sena calle 52', 'Reunión de algo', NULL),
	(17, 'Activa', '', NULL, '', '', NULL),
	(18, 'Activa', 'Está reunión es creada principalmente para algo.', '2014-11-19 00:00:00', 'Corabastos', 'Reunión 0', NULL);
/*!40000 ALTER TABLE `reunion` ENABLE KEYS */;


-- Volcando estructura para tabla opline.rol
DROP TABLE IF EXISTS `rol`;
CREATE TABLE IF NOT EXISTS `rol` (
  `id_rol` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.rol: ~5 rows (aproximadamente)
DELETE FROM `rol`;
/*!40000 ALTER TABLE `rol` DISABLE KEYS */;
INSERT INTO `rol` (`id_rol`, `nombre`) VALUES
	(1, 'Gerente'),
	(2, 'SubGerente'),
	(3, 'Asistente'),
	(4, 'Secretaria'),
	(5, 'Coordinador');
/*!40000 ALTER TABLE `rol` ENABLE KEYS */;


-- Volcando estructura para tabla opline.ruta
DROP TABLE IF EXISTS `ruta`;
CREATE TABLE IF NOT EXISTS `ruta` (
  `id_ruta` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_ruta` varchar(40) COLLATE utf16_spanish_ci NOT NULL,
  `descripcion` varchar(255) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_ruta`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.ruta: ~6 rows (aproximadamente)
DELETE FROM `ruta`;
/*!40000 ALTER TABLE `ruta` DISABLE KEYS */;
INSERT INTO `ruta` (`id_ruta`, `nombre_ruta`, `descripcion`) VALUES
	(4, 'Ruta 4', 'Frutas para los Colegios'),
	(5, 'Ruta 5', 'Vegetales para los batallones'),
	(6, 'Ruta 6', 'Arroz para la policia'),
	(7, 'Ruta 7 ', 'Mercado para los restaurantes'),
	(8, 'Ruta 8', 'Mercado para los colegios'),
	(9, 'Ruta9', 'Fruvers');
/*!40000 ALTER TABLE `ruta` ENABLE KEYS */;


-- Volcando estructura para tabla opline.sexos
DROP TABLE IF EXISTS `sexos`;
CREATE TABLE IF NOT EXISTS `sexos` (
  `id_sexo` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(10) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_sexo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.sexos: ~3 rows (aproximadamente)
DELETE FROM `sexos`;
/*!40000 ALTER TABLE `sexos` DISABLE KEYS */;
INSERT INTO `sexos` (`id_sexo`, `descripcion`) VALUES
	(1, 'Masculino'),
	(2, 'Femenino'),
	(3, 'Gay');
/*!40000 ALTER TABLE `sexos` ENABLE KEYS */;


-- Volcando estructura para tabla opline.soat
DROP TABLE IF EXISTS `soat`;
CREATE TABLE IF NOT EXISTS `soat` (
  `id_vehiculo` int(10) unsigned NOT NULL,
  `fecha_vencimiento` datetime NOT NULL,
  `fecha_de_inicio` datetime NOT NULL,
  `aseguradora` varchar(30) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo`),
  KEY `soat_FKIndex1` (`id_vehiculo`),
  CONSTRAINT `soat_ibfk_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.soat: ~10 rows (aproximadamente)
DELETE FROM `soat`;
/*!40000 ALTER TABLE `soat` DISABLE KEYS */;
INSERT INTO `soat` (`id_vehiculo`, `fecha_vencimiento`, `fecha_de_inicio`, `aseguradora`) VALUES
	(2, '2014-08-20 00:00:00', '2013-08-20 00:00:00', 'Seguros bolivar'),
	(3, '2014-07-27 00:00:00', '2013-07-27 00:00:00', 'Seguros bolivar'),
	(5, '2015-03-21 00:00:00', '2014-03-21 00:00:00', 'Colpatria'),
	(7, '2015-04-15 00:00:00', '2014-04-15 00:00:00', 'Colpatria'),
	(8, '2015-07-25 00:00:00', '2014-07-25 00:00:00', 'Colseguros'),
	(9, '2015-02-28 00:00:00', '2014-02-28 00:00:00', 'Colseguros'),
	(10, '2015-04-14 00:00:00', '2014-04-14 00:00:00', 'Equidad seguros'),
	(11, '2015-05-05 00:00:00', '2014-05-05 00:00:00', 'Equidad seguros'),
	(12, '2015-06-27 00:00:00', '2014-06-27 00:00:00', 'Equidad seguros'),
	(15, '2015-07-27 00:00:00', '2014-07-27 00:00:00', 'Colpatria');
/*!40000 ALTER TABLE `soat` ENABLE KEYS */;


-- Volcando estructura para tabla opline.tecnomecanica
DROP TABLE IF EXISTS `tecnomecanica`;
CREATE TABLE IF NOT EXISTS `tecnomecanica` (
  `id_vehiculo` int(10) unsigned NOT NULL,
  `fecha_de_inicio` datetime NOT NULL,
  `fecha_de_vencimiento` datetime NOT NULL,
  PRIMARY KEY (`id_vehiculo`),
  KEY `tecnomecanica_FKIndex1` (`id_vehiculo`),
  CONSTRAINT `tecnomecanica_ibfk_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.tecnomecanica: ~10 rows (aproximadamente)
DELETE FROM `tecnomecanica`;
/*!40000 ALTER TABLE `tecnomecanica` DISABLE KEYS */;
INSERT INTO `tecnomecanica` (`id_vehiculo`, `fecha_de_inicio`, `fecha_de_vencimiento`) VALUES
	(2, '2014-07-27 00:00:00', '2015-07-27 00:00:00'),
	(3, '2014-04-20 00:00:00', '2015-04-20 00:00:00'),
	(5, '2014-03-28 00:00:00', '2014-03-28 00:00:00'),
	(7, '2014-05-10 00:00:00', '2015-05-10 00:00:00'),
	(8, '2014-06-20 00:00:00', '2015-06-20 00:00:00'),
	(9, '2014-01-16 00:00:00', '2015-01-16 00:00:00'),
	(10, '2014-02-12 00:00:00', '2015-02-12 00:00:00'),
	(11, '2014-02-27 00:00:00', '2015-02-27 00:00:00'),
	(12, '2014-04-14 00:00:00', '2015-04-14 00:00:00'),
	(15, '2015-07-27 00:00:00', '2015-07-27 00:00:00');
/*!40000 ALTER TABLE `tecnomecanica` ENABLE KEYS */;


-- Volcando estructura para tabla opline.tipo_cliente
DROP TABLE IF EXISTS `tipo_cliente`;
CREATE TABLE IF NOT EXISTS `tipo_cliente` (
  `id_tipo_cliente` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(40) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_tipo_cliente`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.tipo_cliente: ~6 rows (aproximadamente)
DELETE FROM `tipo_cliente`;
/*!40000 ALTER TABLE `tipo_cliente` DISABLE KEYS */;
INSERT INTO `tipo_cliente` (`id_tipo_cliente`, `descripcion`) VALUES
	(1, 'Fruver'),
	(2, 'Militar'),
	(3, 'Publico'),
	(4, 'Policia'),
	(5, 'Restaurante'),
	(9, 'Colegio');
/*!40000 ALTER TABLE `tipo_cliente` ENABLE KEYS */;


-- Volcando estructura para tabla opline.tipo_pago
DROP TABLE IF EXISTS `tipo_pago`;
CREATE TABLE IF NOT EXISTS `tipo_pago` (
  `id_tipo_pago` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nombre_pago` varchar(40) COLLATE utf16_spanish_ci NOT NULL,
  PRIMARY KEY (`id_tipo_pago`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.tipo_pago: ~5 rows (aproximadamente)
DELETE FROM `tipo_pago`;
/*!40000 ALTER TABLE `tipo_pago` DISABLE KEYS */;
INSERT INTO `tipo_pago` (`id_tipo_pago`, `nombre_pago`) VALUES
	(1, 'uniforme'),
	(2, 'etoc'),
	(3, 'certificado'),
	(4, 'afiliacion'),
	(5, 'Otros');
/*!40000 ALTER TABLE `tipo_pago` ENABLE KEYS */;


-- Volcando estructura para tabla opline.vehiculo
DROP TABLE IF EXISTS `vehiculo`;
CREATE TABLE IF NOT EXISTS `vehiculo` (
  `id_vehiculo` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_zona` int(10) unsigned NOT NULL,
  `id_asociado` int(10) unsigned NOT NULL,
  `nic` int(10) unsigned NOT NULL,
  `placa` varchar(10) COLLATE utf16_spanish_ci NOT NULL,
  `numero_motor` varchar(20) COLLATE utf16_spanish_ci NOT NULL,
  `marca` varchar(15) COLLATE utf16_spanish_ci NOT NULL,
  `modelo` int(11) NOT NULL,
  `numero_chasis` varchar(20) COLLATE utf16_spanish_ci NOT NULL,
  `tipo_servicio` varchar(15) COLLATE utf16_spanish_ci NOT NULL,
  `tipo_vehiculo` varchar(25) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_vehiculo`),
  KEY `Vehiculo_FKIndex2` (`id_zona`),
  KEY `Vehiculo_FKIndex3` (`nic`),
  KEY `Vehiculo_FKIndex4` (`id_asociado`),
  CONSTRAINT `vehiculo_ibfk_1` FOREIGN KEY (`id_zona`) REFERENCES `zona` (`id_zona`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vehiculo_ibfk_2` FOREIGN KEY (`nic`) REFERENCES `conductor` (`nic`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `vehiculo_ibfk_3` FOREIGN KEY (`id_asociado`) REFERENCES `asociado` (`id_asociado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.vehiculo: ~10 rows (aproximadamente)
DELETE FROM `vehiculo`;
/*!40000 ALTER TABLE `vehiculo` DISABLE KEYS */;
INSERT INTO `vehiculo` (`id_vehiculo`, `id_zona`, `id_asociado`, `nic`, `placa`, `numero_motor`, `marca`, `modelo`, `numero_chasis`, `tipo_servicio`, `tipo_vehiculo`) VALUES
	(2, 1, 2, 301, 'MFN-790', 'AFGC45F', 'Chevrolet', 1985, '58GCSF5CD', 'publico', 'Estacas'),
	(3, 1, 3, 306, 'EJB-234', 'FLAG20A', 'Foton', 1990, '85GREL5MN', 'publico', 'Furgon'),
	(5, 1, 4, 302, 'EQY-214', 'ERFG55C', 'Hyunday', 2000, '33ASDF2MG', 'publico', 'Camioneta'),
	(7, 3, 5, 303, 'QER-234', 'GLAS40S', 'Chevrolet', 1997, '52GFYU5LT', 'publico', 'Estacas'),
	(8, 4, 6, 304, 'TEU-224', 'ASRE25B', 'Foton', 2000, '20LERF52CV', 'publico', 'Furgon'),
	(9, 12, 7, 305, 'RDO-674', 'HRTY70H', 'Hyunday', 1995, '30VVDF88GH', 'publico', 'Furgon'),
	(10, 3, 10, 307, 'NCB-789', 'POLP22N', 'Foton', 1996, '30KADSS50C', 'publico', 'Camioneta'),
	(11, 7, 9, 308, 'JSG-680', 'GHHD36K', 'Chevrolet', 1998, '98SDKRN20H', 'publico', 'Camioneta'),
	(12, 3, 2, 303, 'SMJ-897', 'DSFE74R', 'Foton', 2000, '55ASDAS36G', 'publico', 'Camioneta'),
	(15, 3, 10, 310, 'KDM-839', 'ASDGV55G', 'Foton', 1995, '20SDFHR22H', 'publico', 'Camioneta');
/*!40000 ALTER TABLE `vehiculo` ENABLE KEYS */;


-- Volcando estructura para tabla opline.viaje
DROP TABLE IF EXISTS `viaje`;
CREATE TABLE IF NOT EXISTS `viaje` (
  `id_viaje` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_vehiculo` int(10) unsigned NOT NULL,
  `fecha_viaje` datetime NOT NULL,
  PRIMARY KEY (`id_viaje`),
  KEY `Viaje_FKIndex1` (`id_vehiculo`),
  CONSTRAINT `viaje_ibfk_1` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.viaje: ~12 rows (aproximadamente)
DELETE FROM `viaje`;
/*!40000 ALTER TABLE `viaje` DISABLE KEYS */;
INSERT INTO `viaje` (`id_viaje`, `id_vehiculo`, `fecha_viaje`) VALUES
	(1, 2, '2014-05-20 00:00:00'),
	(2, 3, '2014-05-01 00:00:00'),
	(3, 5, '2014-07-27 00:00:00'),
	(4, 7, '2014-06-06 00:00:00'),
	(5, 8, '2014-02-10 00:00:00'),
	(6, 9, '2014-01-30 00:00:00'),
	(7, 7, '2014-05-24 00:00:00'),
	(8, 8, '2014-03-30 00:00:00'),
	(9, 9, '2014-01-21 00:00:00'),
	(10, 10, '2014-03-14 00:00:00'),
	(11, 11, '2014-06-20 00:00:00'),
	(12, 12, '2014-07-27 00:00:00');
/*!40000 ALTER TABLE `viaje` ENABLE KEYS */;


-- Volcando estructura para tabla opline.zona
DROP TABLE IF EXISTS `zona`;
CREATE TABLE IF NOT EXISTS `zona` (
  `id_zona` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `id_estado` int(10) unsigned NOT NULL,
  `capacidad_vehiculos` int(10) unsigned NOT NULL,
  `nombre` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `ubicacion` varchar(25) COLLATE utf16_spanish_ci NOT NULL,
  `carga` varchar(100) COLLATE utf16_spanish_ci DEFAULT NULL,
  PRIMARY KEY (`id_zona`),
  KEY `id_estado` (`id_estado`),
  CONSTRAINT `zona_ibfk_1` FOREIGN KEY (`id_estado`) REFERENCES `estado` (`id_estado`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf16 COLLATE=utf16_spanish_ci;

-- Volcando datos para la tabla opline.zona: ~14 rows (aproximadamente)
DELETE FROM `zona`;
/*!40000 ALTER TABLE `zona` DISABLE KEYS */;
INSERT INTO `zona` (`id_zona`, `id_estado`, `capacidad_vehiculos`, `nombre`, `ubicacion`, `carga`) VALUES
	(1, 1, 15, 'Zona 1', 'Bodega 2 bajo el tunel', 'Arroz y Papas'),
	(2, 1, 20, 'Zona 2', 'Bodega 1 y 2', 'Legumbres'),
	(3, 1, 25, 'Zona 3', 'Bodega 3 y 4', 'Fresas2'),
	(4, 1, 10, 'Zona 4', 'Bodega 5 y 6', 'Manzanas'),
	(5, 1, 5, 'Zona 5', 'Bodega 7', 'Lechugas'),
	(6, 1, 5, 'Zona 6', 'Bodega 8', 'Vegetales'),
	(7, 2, 25, 'Zona 7', 'Bodega 12 y 13', 'Arroz'),
	(8, 1, 17, 'Zona 8', 'Bodega 11', 'Espinacas'),
	(9, 2, 15, 'Zona 9', 'Bodega 15', 'Moras'),
	(10, 1, 20, 'Zona 10', 'Bodega 14 y 16', 'Papas y maiz'),
	(11, 1, 222, 'Zona 11', 'Bodega 24', 'Sandias'),
	(12, 1, 25, 'Zona 14', 'Entrada 29', 'Limones'),
	(13, 1, 23, 'Zona 13', 'Bodega 21', 'Peras'),
	(14, 1, 0, '', '', '');
/*!40000 ALTER TABLE `zona` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
