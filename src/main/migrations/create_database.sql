CREATE DATABASE `aps-dabd` COLLATE 'utf8_general_ci';

CREATE TABLE `tb_emails` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT PRIMARY KEY,
  `address` varchar(255) NOT NULL,
  `subject` varchar(255) NOT NULL DEFAULT 'Sem Assunto',
  `message` longtext NOT NULL,
  `date` datetime NOT NULL
) ENGINE='InnoDB' COLLATE 'utf8_general_ci';