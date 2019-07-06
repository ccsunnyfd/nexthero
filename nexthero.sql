/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.25-log : Database - nexthero
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nexthero` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci */;

USE `nexthero`;

/*Table structure for table `movie_carousel` */

DROP TABLE IF EXISTS `movie_carousel`;

CREATE TABLE `movie_carousel` (
  `id` bigint(20) NOT NULL,
  `image` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `is_show` int(11) DEFAULT '1',
  `movie_id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `sort` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `movie_carousel` */

insert  into `movie_carousel`(`id`,`image`,`is_show`,`movie_id`,`sort`) values (1,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZHiASu_7AAfGb_KNy8Y861.png',1,'marvel-1011',1),(2,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZSiAaDteAAdT5PsVWXs099.png',1,'xman-1004',2),(3,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZgSAdG-rAAfcNtoF_n8219.png',1,'dc-1008',6),(4,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1Z_aASu2VAAf-RnvbnhY123.png',1,'marvel-1022',7);

/*Table structure for table `movie_info` */

DROP TABLE IF EXISTS `movie_info`;

CREATE TABLE `movie_info` (
  `id` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `basic_info` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `cover` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `name` varchar(40) COLLATE utf8_unicode_ci NOT NULL,
  `original_name` varchar(30) COLLATE utf8_unicode_ci DEFAULT NULL,
  `plot_desc` varchar(255) COLLATE utf8_unicode_ci DEFAULT NULL,
  `poster` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  `prised_counts` int(11) DEFAULT NULL,
  `release_date` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `score` int(11) NOT NULL,
  `total_time` varchar(40) COLLATE utf8_unicode_ci DEFAULT NULL,
  `trailer` varchar(200) COLLATE utf8_unicode_ci NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `movie_info` */

insert  into `movie_info`(`id`,`basic_info`,`cover`,`name`,`original_name`,`plot_desc`,`poster`,`prised_counts`,`release_date`,`score`,`total_time`,`trailer`) values ('dc-1004','2018 / 美国 / 科幻 / 超级英雄','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/cover.jpg','蝙蝠侠：黑暗骑士崛起','原名：The Dark Knight Rises','在哥谭镇的检察官哈维·丹特死亡之后，蝙蝠侠承担起了杀死丹特的罪责，并在暗中接手了丹特的工作。但是，作为替罪羊的蝙蝠侠，随即就被哥谭镇的警方列为了最高通缉对象。走投无路的蝙蝠侠只有离开哥谭镇，远走高飞。\n八年之后，蝙蝠侠回到了哥谭镇，见到了如迷似雾的瑟琳娜·凯尔和另一个令人头疼的超级反派贝恩。而目前处在形单影只情况下的蝙蝠侠能阻止贝恩毁灭城市的计划么？','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/poster.jpg',7561,'上映时间：2012-08-27（中国大陆）',91,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/trailer.mp4'),('dc-1910','2017 / 美国 / 科幻 / 超级英雄','http://img5.mtime.cn/mt/2017/04/01/164328.75628704_1280X720X2.jpg','银河护卫队2','原名：Guardians of the Galaxy 2','银河护卫队在本集中穿越宇宙，继续外太空的史诗冒险之旅。他们必须共同作战，守护彼此；同时要解开“星爵”彼得·奎尔的身世之谜。旧日敌人变为盟友，漫画中深受喜爱的角色也会现身，对护卫队出手援助。','http://img5.mtime.cn/pi/2016/12/20/161428.84261659_1280X720X2',9800,'上映时间：2017-05-05（中国大陆）',78,'影片时长：165分钟（中国大陆）','http://vfx.mtime.cn/Video/2017/03/01/mp4/170301133120672165_480.mp4'),('marvel-1000','2019 / 美国 / 科幻 / 超级英雄','http://img5.mtime.cn/mt/2019/03/29/095608.66203322_1280X720X2.jpg','复仇者联盟4：终局之战','原名：Avengers: Endgame','《复仇者联盟3：无限战争》的毁灭性事件过后，宇宙由于疯狂泰坦灭霸的行动而变得满目疮痍。无论前方将遭遇怎样的后果，复仇者联盟都必须在剩余盟友的帮助下再一次集结，以逆转灭霸的所作所为，彻底恢复宇宙的秩序。','http://img5.mtime.cn/pi/2019/03/29/095719.81293973_1280X720X2.jpg',7800,'上映时间：2019-04-24（中国大陆）',92,'影片时长：165分钟（中国大陆）','http://vfx.mtime.cn/Video/2019/03/14/mp4/190314223540373995.mp4'),('marvel-1026','2018 / 美国 / 科幻 / 超级英雄','http://122.152.205.72:88/superhero/MARVEL/Venom/cover.jpg','毒液：致命守护者','原名：Venom','蜘蛛侠最强劲敌“毒液”强势来袭！汤姆·哈迪在本片中饰演“毒液”的宿主–埃迪·布洛克。身为记者的埃迪在调查生命基金会老板卡尔顿·德雷克（里兹·阿迈德饰）的过程中，事业遭受重创，与未婚妻安妮·韦英（米歇尔·威廉姆斯饰）的关系岌岌可危，并意外被外星共生体入侵，历经挣扎对抗，最终成为拥有强大超能力，无人可挡的“毒液”。影片极具震撼的视效制作，颠覆想象的展示了“毒液”的变身过程，并将其酷炫的外形和强大的技能呈现的淋漓尽致。','http://122.152.205.72:88/superhero/MARVEL/Venom/poster.jpg',7892,'上映时间：2012-08-27（中国大陆）',89,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/MARVEL/Venom/trailer.mp4');

/*Table structure for table `native` */

DROP TABLE IF EXISTS `native`;

CREATE TABLE `native` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

/*Data for the table `native` */

insert  into `native`(`next_val`) values (5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
