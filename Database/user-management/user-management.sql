-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1:3306
-- Généré le : ven. 18 déc. 2020 à 15:21
-- Version du serveur :  5.7.31
-- Version de PHP : 7.3.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `user-management`
--

-- --------------------------------------------------------

--
-- Structure de la table `survey`
--

DROP TABLE IF EXISTS `survey`;
CREATE TABLE IF NOT EXISTS `survey` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `added_by` int(11) DEFAULT NULL,
  `city` varchar(50) DEFAULT NULL,
  `country` varchar(50) DEFAULT NULL,
  `date_added` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `is_deleted` bit(1) DEFAULT NULL,
  `state` varchar(50) DEFAULT NULL,
  `survey_date` varchar(50) DEFAULT NULL,
  `title` varchar(50) DEFAULT NULL,
  `zip` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `survey`
--

INSERT INTO `survey` (`id`, `added_by`, `city`, `country`, `date_added`, `date_modified`, `description`, `is_deleted`, `state`, `survey_date`, `title`, `zip`) VALUES
(15, 12, 'Paris', 'France', '2020-12-07 20:49:38', '2020-12-07 20:49:38', 'Software Engineer Survey', b'0', 'île-de-france', '25-12-2020', 'Software Engineer Survey', '75010'),
(17, 12, 'Issou', 'France', '2020-12-07 20:53:24', '2020-12-07 20:57:36', 'Software Engineer Survey 2', b'1', 'Yvelines', '27-12-2020', 'Software Engineer Survey 2', '78440');

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date_added` datetime DEFAULT NULL,
  `date_modified` datetime DEFAULT NULL,
  `email_id` varchar(50) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) DEFAULT '1',
  `last_name` varchar(50) DEFAULT NULL,
  `mobile` varchar(50) DEFAULT NULL,
  `password` varchar(300) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_r9kvst217faqa7vgeyy51oos0` (`email_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`id`, `date_added`, `date_modified`, `email_id`, `first_name`, `is_deleted`, `last_name`, `mobile`, `password`) VALUES
(14, '2020-12-07 21:04:33', '2020-12-07 21:04:33', 'alexandre.berard@esiea.fr', 'Alexandre', 0, 'Berard', '0243594624', '$2a$10$SVjJCSaGJXNiZWRGtF3sW.lZY9BRZPfR/wt0yyXfldG5WlYUKMhPC');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
