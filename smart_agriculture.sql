drop database if exists smart_agriculture;
create database smart_agriculture;
use smart_agriculture;
drop table if exists crop_category;
create table crop_category
(
    id          int primary key auto_increment comment '主键',
    name        varchar(32) not null comment '分类名称',
    create_time datetime    null comment '创建时间',
    update_time datetime    null comment '修改时间',
    create_user bigint      null comment '创建人',
    update_user bigint      null comment '修改人',
    constraint idx_type_name
        unique (name)
) comment '农作物分类';
insert into crop_category(name)
VALUES ('水稻'),
       ('小麦'),
       ('玉米'),
       ('大豆'),
       ('油菜'),
       ('花生'),
       ('棉花'),
       ('马铃薯'),
       ('大白菜'),
       ('结球甘蓝');
drop table if exists growth_cycle;
create table growth_cycle
(
    id            int primary key auto_increment comment '主键',
    category_id   int comment '种类id',
    name          varchar(32) comment '周期名字',
    cycle_percent varchar(32) comment '在整个周期的占比:如1-2',
    soil_moisture varchar(32) comment '这个周期需要的土壤湿度:如22.33'
);
insert into growth_cycle(category_id, name, cycle_percent, soil_moisture)
VALUES (1, '发芽期', '0-10', '80-100'),
       (1, '分蘖期', '10-25', '70-80'),
       (1, '生长初期', '25-50', '70-80'),
       (1, '拔节期', '50-75', '60-70'),
       (1, '抽穗期', '75-90', '55-65'),
       (1, '平秧期', '90-95', '50-60'),
       (1, '成熟期', '95-100', '40-50');
drop table if exists crop;
create table crop
(
    id                     int primary key auto_increment comment '主键',
    name                   varchar(32)  null comment '作物名称',
    growth_cycle           varchar(255) null comment '生长周期',
    yield                  varchar(255) null comment '产量',
    characteristics        varchar(255) null comment '特点',
    cultivation_techniques text         null comment '栽培技术要点',
    promotion              text         null comment '推广情况',
    crop_category_id       int          null comment '类别id',
    create_time            datetime     null comment '创建时间',
    update_time            datetime     null comment '修改时间',
    create_user            bigint       null comment '创建人',
    update_user            bigint       null comment '修改人'
) comment '作物';
INSERT INTO crop (name, characteristics, growth_cycle, yield, cultivation_techniques, promotion,
                  crop_category_id)
VALUES ('龙粳 31',
        '高产稳产，整精米率高，抗倒性较强。',
        '130',
        '576.8',
        '在黑龙江省第三积温带插秧栽培，适合4月至5月播种和插秧。科学管理肥水，注意氮、磷、钾肥配合施用，及时预防和控制病、虫、草害的发生。',
        '1.36 亿亩（2011—2022 年）。。',
        1),
       ('南粳 9108',
        '食味佳、香味浓郁，丰产性好。',
        '153',
        '652.1',
        '在江苏省苏中及宁镇扬丘陵地区作单季稻种植。1. 精量播
        种。机插秧塑盘育秧每盘播干种子 100.0 ～ 120.0 克。2. 插足基本苗。亩基本苗 7.0
        万～ 8.0 万。3. 科学肥水管理。一般亩施纯氮 16 ～ 18 千克，为保持其优良食味品
        质，宜少施氮肥，多施有机肥，特别是后期尽量不施氮肥。基肥、分蘖肥、穗肥比
        例以 4 ∶ 4 ∶ 2 为宜。常规水分管理，收获前 7 天断水。4. 病虫草害防治。播种前
        用药剂浸种，特别要注意穗颈稻瘟病、纹枯病和稻曲病的防治。',
        '3500 万亩（2014—2021 年',
        1),
       ('黄华占',
        '高产稳产，抗倒性较强，米质优、出米率高、食味好。',
        '118',
        '540.0',
        '湖北（长江中下游）一季晚稻种植。1. 适时播种。一般 5 月下
旬—6 月上旬播种，秧田播种量 10.0 ～ 20.0 千克，播种前用强氯精浸种消毒。2. 插
足基本苗。秧龄期控制在 25 天内，株行距 13.3 厘米 ×20.0 厘米，每穴插 2 ～ 3 粒
谷苗，亩插足基本苗 15.0 万。3. 科学管理肥水。一般亩施纯氮 12 千克、五氧化二
磷 6 千克、氧化钾 9 千克。浅水勤灌，亩苗数达 30.0 万时排水晒天，后期干干湿湿，
忌断水过早。',
        '3283 万亩（2017—2021 年）。',
        1)
;
drop table if exists crop_province;
create table crop_province
(
    id          int primary key auto_increment comment '主键',
    crop_id     int comment '作物id',
    province_id int comment '省份id'
);
insert into crop_province(crop_id, province_id)
VALUES (1, 8),
       (2, 10),
       (3, 10),
       (3, 11),
       (3, 12),
       (3, 14),
       (3, 16),
       (3, 17),
       (3, 18),
       (3, 19),
       (3, 20),
       (3, 21),
       (3, 22),
       (3, 27);
drop table if exists province;
create table province
(
    id   int primary key auto_increment comment '主键',
    name varchar(32) not null comment '省份名字'
) comment '省份';
INSERT INTO province (name)
VALUES ('北京市'),
       ('天津市'),
       ('河北省'),
       ('山西省'),
       ('内蒙古自治区'),
       ('辽宁省'),
       ('吉林省'),
       ('黑龙江省'),
       ('上海市'),
       ('江苏省'),
       ('浙江省'),
       ('安徽省'),
       ('福建省'),
       ('江西省'),
       ('山东省'),
       ('河南省'),
       ('湖北省'),
       ('湖南省'),
       ('广东省'),
       ('广西壮族自治区'),
       ('海南省'),
       ('重庆市'),
       ('四川省'),
       ('贵州省'),
       ('云南省'),
       ('西藏自治区'),
       ('陕西省'),
       ('甘肃省'),
       ('青海省'),
       ('宁夏回族自治区'),
       ('新疆维吾尔自治区'),
       ('台湾省'),
       ('香港特别行政区'),
       ('澳门特别行政区');

drop table if exists user;
create table user
(
    id          int primary key auto_increment comment '主键',
    name        varchar(32)  null comment '姓名',
    username    varchar(32)  not null comment '用户名',
    phone       varchar(11)  null comment '手机号',
    password    varchar(255) not null comment '密码',
    create_time datetime     null comment '创建时间',
    update_time datetime     null comment '更新时间',
    create_user bigint       null comment '创建人',
    update_user bigint       null comment '修改人',
    constraint idx_username
        unique (username)
) comment '员工信息' collate = utf8mb3_bin;
insert into user(username, password)
values ('user', 'e10adc3949ba59abbe56e057f20f883e');
drop table if exists admin;
create table admin
(
    id       int primary key auto_increment comment '主键',
    username varchar(32)  not null comment '用户名',
    name     varchar(32)  null comment '姓名',
    password varchar(255) not null comment '密码'
);
insert into admin(username, password)
values ('admin', 'e10adc3949ba59abbe56e057f20f883e');
drop table if exists field;
create table field
(
    id            int primary key auto_increment comment '主键',
    user_id       int comment '用户id',
    name          varchar(32) comment '田地名字',
    status        int comment '田地状态: 1.已耕种 2.未耕种',
    soil_moisture varchar(32) comment '土壤湿度',
    planting_time datetime comment '种植时间',
    crop_id       int comment '种植农作物的id',
    province_id   int comment '省份id',
    create_time   datetime null comment '创建时间',
    update_time   datetime null comment '更新时间',
    create_user   bigint   null comment '创建人',
    update_user   bigint   null comment '修改人'
);
create table news
(
    id          int primary key auto_increment comment '主键',
    title       varchar(255) comment '标题',
    url         varchar(255) comment '链接',
    create_time datetime comment '创建时间'
) comment '新闻';