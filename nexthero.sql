/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.26 : Database - nexthero
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`nexthero` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `nexthero`;

/*Data for the table `movie_carousel` */

insert  into `movie_carousel`(`id`,`image`,`is_show`,`movie_id`,`sort`) values (1,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZHiASu_7AAfGb_KNy8Y861.png',1,'marvel-1011',1),(2,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZSiAaDteAAdT5PsVWXs099.png',1,'xman-1004',2),(3,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1ZgSAdG-rAAfcNtoF_n8219.png',1,'dc-1008',6),(4,'http://122.152.205.72:88/group1/M00/00/03/CpoxxFw1Z_aASu2VAAf-RnvbnhY123.png',1,'marvel-1022',7);

/*Data for the table `movie_info` */

insert  into `movie_info`(`id`,`basic_info`,`cover`,`name`,`original_name`,`plot_desc`,`poster`,`prised_counts`,`release_date`,`score`,`total_time`,`trailer`) values ('dc-1004','2018 / 美国 / 科幻 / 超级英雄','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/cover.jpg','蝙蝠侠：黑暗骑士崛起','原名：The Dark Knight Rises','在哥谭镇的检察官哈维·丹特死亡之后，蝙蝠侠承担起了杀死丹特的罪责，并在暗中接手了丹特的工作。但是，作为替罪羊的蝙蝠侠，随即就被哥谭镇的警方列为了最高通缉对象。走投无路的蝙蝠侠只有离开哥谭镇，远走高飞。\n八年之后，蝙蝠侠回到了哥谭镇，见到了如迷似雾的瑟琳娜·凯尔和另一个令人头疼的超级反派贝恩。而目前处在形单影只情况下的蝙蝠侠能阻止贝恩毁灭城市的计划么？','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/poster.jpg',7561,'上映时间：2012-08-27（中国大陆）',91,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/DC/BatManTheDarkKnightRises/trailer.mp4'),('marvel-1000','2019 / 美国 / 科幻 / 超级英雄','http://122.152.205.72:88/superhero/xman/Logan/cover.jpg','金刚狼：殊死一战','原名：xman: Logan','一天，一个陌生女子找到了罗根，希望他能够保护一个名为劳拉的小女孩，带她去到“伊甸园”，可是现如今的暮狼，只想过上安稳的日子，他不愿挺身作英雄，不愿面对逃亡生活。','http://122.152.205.72:88/superhero/xman/Logan/poster.jpg',9999,'上映时间：2017-11-3（中国大陆）',92,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/xman/Logan/trailer.mp4'),('marvel-1026','2018 / 美国 / 科幻 / 超级英雄','http://img5.mtime.cn/mg/2018/07/31/221920.12746227.jpg','毒液：致命守护者','原名：Venom','蜘蛛侠最强劲敌“毒液”强势来袭！汤姆·哈迪在本片中饰演“毒液”的宿主–埃迪·布洛克。身为记者的埃迪在调查生命基金会老板卡尔顿·德雷克（里兹·阿迈德饰）的过程中，事业遭受重创，与未婚妻安妮·韦英（米歇尔·威廉姆斯饰）的关系岌岌可危，并意外被外星共生体入侵，历经挣扎对抗，最终成为拥有强大超能力，无人可挡的“毒液”。影片极具震撼的视效制作，颠覆想象的展示了“毒液”的变身过程，并将其酷炫的外形和强大的技能呈现的淋漓尽致。','http://122.152.205.72:88/superhero/MARVEL/Venom/poster.jpg',7892,'上映时间：2012-08-27（中国大陆）',89,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/MARVEL/Venom/trailer.mp4'),('marvel-1029','2018 / 美国 / 科幻 / 超级英雄','http://122.152.205.72:88/superhero/MARVEL/Thor3/cover.jpg','雷神3：诸神黄昏','原名：Thor: Ragnarok','洛基（汤姆·希德勒斯顿饰）一直假扮着他们失踪的父亲奥丁，但洛基治国相当懈怠，导致本被关押的海拉(凯特·布兰切特饰)再度现身，并计划了一场浩劫，意图毁灭天宫。初次和海拉对抗，雷神索尔（克里斯·海姆斯沃斯饰）失去了他强大的锤子，被卷到了萨卡星，一颗野蛮星球，被迷人但邪恶的宗师(杰夫·高布伦饰)所统治。隐居在萨卡星的瓦尔基里(泰莎·汤普森饰)是个酗酒成性的强硬战士，把雷神带到了宗师那里。','http://122.152.205.72:88/superhero/MARVEL/Thor3/poster.jpg',9800,'上映时间：2017-05-05（中国大陆）',78,'影片时长：165分钟（中国大陆）','http://122.152.205.72:88/superhero/MARVEL/Thor3/trailer.mp4');

/*Data for the table `native` */

insert  into `native`(`next_val`) values (5);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
