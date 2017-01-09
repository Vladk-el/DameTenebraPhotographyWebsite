-- phpMyAdmin SQL Dump
-- version 4.4.15.7
-- http://www.phpmyadmin.net
--
-- Client :  mysql51-106.perso
-- Généré le :  Lun 09 Janvier 2017 à 11:42
-- Version du serveur :  5.5.52-0+deb7u1-log
-- Version de PHP :  5.4.45-0+deb7u6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";

--
-- Base de données :  `yourdbname`
--
CREATE DATABASE IF NOT EXISTS `yourdbname` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
USE `yourdbname`;

-- --------------------------------------------------------

--
-- Structure de la table `category`
--

DROP TABLE IF EXISTS `category`;
CREATE TABLE IF NOT EXISTS `category` (
  `category_id` int(255) NOT NULL,
  `category_name` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `category_description` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_date` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `link`
--

DROP TABLE IF EXISTS `link`;
CREATE TABLE IF NOT EXISTS `link` (
  `link_id` int(255) NOT NULL,
  `link_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `link_link` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `link_owner_id` int(255) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `message`
--

DROP TABLE IF EXISTS `message`;
CREATE TABLE IF NOT EXISTS `message` (
  `id` int(11) NOT NULL,
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `website` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `email` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `subject` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `message` varchar(1000) COLLATE utf8_unicode_ci NOT NULL,
  `date` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `owner`
--

DROP TABLE IF EXISTS `owner`;
CREATE TABLE IF NOT EXISTS `owner` (
  `owner_id` int(255) NOT NULL,
  `owner_name` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `owner_description` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- --------------------------------------------------------

--
-- Structure de la table `photo`
--

DROP TABLE IF EXISTS `photo`;
CREATE TABLE IF NOT EXISTS `photo` (
  `photo_id` int(255) NOT NULL,
  `photo_name` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_description` varchar(1000) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_date` varchar(10) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_link` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `photo_mini_link` varchar(300) COLLATE utf8_unicode_ci DEFAULT NULL,
  `appareil` varchar(200) COLLATE utf8_unicode_ci DEFAULT NULL,
  `obturation` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `ouverture` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `longueur_focale` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `vitesse_ISO` varchar(100) COLLATE utf8_unicode_ci DEFAULT NULL,
  `category_photo` int(255) NOT NULL DEFAULT '0',
  `active` tinyint(1) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Index pour les tables exportées
--

--
-- Index pour la table `category`
--
ALTER TABLE `category`
  ADD PRIMARY KEY (`category_id`);

--
-- Index pour la table `link`
--
ALTER TABLE `link`
  ADD PRIMARY KEY (`link_id`),
  ADD UNIQUE KEY `link_id` (`link_id`),
  ADD KEY `link_owner_id` (`link_owner_id`) USING BTREE;

--
-- Index pour la table `message`
--
ALTER TABLE `message`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `owner`
--
ALTER TABLE `owner`
  ADD PRIMARY KEY (`owner_id`);

--
-- Index pour la table `photo`
--
ALTER TABLE `photo`
  ADD PRIMARY KEY (`photo_id`,`category_photo`),
  ADD UNIQUE KEY `photo_id` (`photo_id`),
  ADD KEY `category_photo` (`category_photo`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `category`
--
ALTER TABLE `category`
  MODIFY `category_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `link`
--
ALTER TABLE `link`
  MODIFY `link_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `message`
--
ALTER TABLE `message`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `owner`
--
ALTER TABLE `owner`
  MODIFY `owner_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT pour la table `photo`
--
ALTER TABLE `photo`
  MODIFY `photo_id` int(255) NOT NULL AUTO_INCREMENT;
--
-- Contraintes pour les tables exportées
--

--
-- Contraintes pour la table `link`
--
ALTER TABLE `link`
  ADD CONSTRAINT `LINK_OWNER_ID` FOREIGN KEY (`link_owner_id`) REFERENCES `owner` (`owner_id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `photo`
--
ALTER TABLE `photo`
  ADD CONSTRAINT `photo_ibfk_2` FOREIGN KEY (`category_photo`) REFERENCES `category` (`category_id`) ON DELETE CASCADE ON UPDATE CASCADE;
