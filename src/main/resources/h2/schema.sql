CREATE TABLE IF NOT EXISTS project_config (
  `id` int(11) IDENTITY(1,1) NOT NULL primary key,
  `projectName` varchar(100) DEFAULT '',
  `targetRuntime` varchar(30) DEFAULT '',
  `suppressAllComments` bit(1) DEFAULT '',
  `driverClass` varchar(100) DEFAULT '',
  `connectionURL` varchar(300) DEFAULT '',
  `userId` varchar(50) DEFAULT '',
  `password` varchar(50) DEFAULT '',
  `targetProject` varchar(200) DEFAULT '',
  `targetModelPackage` varchar(100) DEFAULT '',
  `targetMapperPackage` varchar(100) DEFAULT '',
  `targetMapperInterfacePackage` varchar(100) DEFAULT '',
  `isOverwrite` bit(1) DEFAULT 1,
  `removePrefix` varchar(100) DEFAULT ''
);