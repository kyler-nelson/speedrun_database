create database if not exists nelson_kyler_db;
use nelson_kyler_db;


create table publishers(
    name        char(35)   not null,
    constraint publishersPK primary key(name)
);

create table developers(
    name char(35)        null,
    constraint developersPK primary key(name)
);

create table players(
    playerId int       not null AUTO_INCREMENT,
    name  char(35)     not null,
    age   int          null,
    gender char(35)    null,
    constraint playersPK primary key(playerId)
);

create table games(
    gameId int        not null AUTO_INCREMENT,
    title  char(35)   not null,
    genre  char(35)   null,
    publisher  char(35)   null,
    developer  char(35)   null,
    constraint gamesPK primary key(gameId)
);

create table platforms(
    name        char(35)    not null,
    type        char(35)    null,
    constraint platformsPK primary key(name)
);

create table videos(
    videoId int        not null AUTO_INCREMENT,
    duration int       not null,
    gameTime int       null,
    format   char(3)   not null,
    constraint videosPK primary key(videoId)
);

create table categories(
    releaseId    int             not null,
    category     char(35)        not null default 'any%',
    timingMethod char(35)        null default 'FILI',
    constraint categoriesPK primary key(releaseId, category)
);


create table regions(
    name     char(35) not null,
    standard char(35) not null,
    constraint regionsPK primary key(name)
);

create table runs(
    releaseId       int      not null,
    category     char(35)    not null,
    playerId       int       not null,
	completionDate date      null,
    publishDate    date      null,
    runDuration    int       not null, 
    isEmulated     char(35)  not null default 'false',
    videoId        int       null,
    constraint runsPK primary key(playerId, releaseId, category, completionDate)
);

create table releases(
    releaseId      int       not null AUTO_INCREMENT,
    gameId         int       not null,
    platform       char(35)  not null,
    region         char(35)  not null,
    releaseDate    date      null,
    constraint releasesPK primary key(releaseId),
    CONSTRAINT uc_releaseId UNIQUE (gameId, platform, region) #ReleaseId is surrogate key
);

ALTER TABLE runs
ADD FOREIGN KEY (playerId)
REFERENCES players(playerId);

ALTER TABLE runs
ADD FOREIGN KEY ( releaseId, category )
REFERENCES categories( releaseId, category );

ALTER TABLE releases
ADD FOREIGN KEY (gameId)
REFERENCES games(gameId);

ALTER TABLE releases
ADD FOREIGN KEY (platform)
REFERENCES platforms(name);

ALTER TABLE releases
ADD FOREIGN KEY (region)
REFERENCES regions(name);

ALTER TABLE categories
ADD FOREIGN KEY (releaseId)
REFERENCES releases(releaseId);

ALTER TABLE games
ADD FOREIGN KEY (developer)
REFERENCES developers(name);

ALTER TABLE games
ADD FOREIGN KEY (publisher)
REFERENCES publishers(name);

#ALTER TABLE runs
#ADD CHECK( select publicationDate, runDuration from runs )

INSERT INTO players (name, age, gender)
VALUES ('runnerguy', 18, "male"),
       ('cosmowright', 23, "male"),
       ('skater82297', 22, "male"),
       ('doktor_m', 17, "male")
;


INSERT INTO videos (duration, gameTime, format)
VALUES ( 1337, 1420, 'mp4' ),
       ( 1381, 1450, 'mp4'),
       ( 1455, 1582, 'mp4' ),
       ( 1786, 1892, 'mp4' ),
       ( 2205, 2311, 'flv' )
;

INSERT INTO developers ( name )
VALUES ( 'nintendo' ),
       ( 'Nintendo EAD' ),
	   ( 'electronic arts' ),
       ( 'HAL Laboratory' ),
       ( 'Naughty Dog' )
;

INSERT INTO publishers ( name )
VALUES ( 'nintendo' ),
       ( 'electronic arts' ),
       ( 'ubisoft' ),
       ( 'bethesda' ),
       ( 'Sony Computer Entertainment' ),
       ( 'microsoft' )
;

INSERT INTO platforms ( name, type )
VALUES ( 'nintendo 64', 'console' ),
       ( 'gamecube', 'console' ),
       ( 'xbox', 'console' ),
       ( 'xbox one', 'console' ),
       ( 'playstation', 'console' ),
       ( 'playstation 2', 'console' ),
       ( 'playstation 3', 'console' ),
       ( 'playstation 4', 'console' ),
       ( 'dos', 'pc' ),
       ( 'windows', 'pc' ),
       ( 'apple', 'pc' ),
       ( 'linux', 'pc' )
;

INSERT INTO regions (name, standard)
VALUES ('USA', 'NTSC'),
       ('EUR', 'PAL'),
       ('CHN', 'IQ'),
       ('JAP', 'NTSC')
;

INSERT INTO games (title, genre, developer, publisher)
VALUES ('Ocarina of Time',   'action/adventure', 'Nintendo EAD',    'nintendo'),
       ('The Wind Waker',    'action/adventure', 'Nintendo EAD',    'nintendo'),
       ('Super Smash Bros.', 'fighting',         'HAL Laboratory',  'nintendo'),
	   ('Uncharted',         'adventure',        'Naughty Dog',     'Sony Computer Entertainment'),
       ('Madden NFL 2015',   'sports',           'electronic arts', 'electronic arts')
;

INSERT INTO releases (gameId, platform, region, releaseDate)
VALUES ( 1, 'nintendo 64',   'USA', '1998-02-11'),
       ( 1, 'nintendo 64',   'JAP', '1998-03-13'),
       ( 2, 'nintendo 64',   'USA', '2003-01-14'),
       ( 2, 'gamecube',      'USA', '2006-07-17'),
	   ( 3, 'nintendo 64',   'USA', '1997-01-10'),
	   ( 4, 'playstation 2', 'USA', '1997-01-10')
;

INSERT INTO categories ( releaseId, category, timingMethod)
VALUES ( '1', 'any%', 'FILI'),
       ( '1', '100%', 'FILI'),
	   ( '2', 'any%', 'FILI'),
	   ( '2', '100%', 'FILI'),
       ( '3', 'any%', 'FILI'),
	   ( '3', '100%', 'FILI'),
	   ( '4', 'any%', 'IGT'),
       ( '4', '100%', 'IGT'),
	   ( '5', 'any%', 'IGT'),
	   ( '5', '100%', 'IGT')
;

INSERT INTO runs ( playerId, releaseId, category, completionDate, publishDate, runDuration, isEmulated, videoId )
VALUES ( 1, 1, 'any%', '2014-03-07', '2015-03-04', 1310, 'false', 1 ),
       ( 2, 1, 'any%', '2014-03-07', '2014-11-07', 1381, 'false', 2 ),
       ( 3, 1, '100%', '2012-03-07', '2015-02-04', 1455, 'false', 3 ),
       ( 4, 1, 'any%', '2009-03-07', '2013-03-01', 1786, 'true', 4 ),
       ( 3, 2, 'any%', '2008-03-07', '2012-02-09', 2205, 'false', 5 ),
	   ( 3, 3, '100%', '2013-04-02', '2014-10-07', 8541, 'false', null ),
	   ( 4, 4, 'any%', '2012-02-02', '2013-10-07', 6541, 'false', null ),
	   ( 3, 5, 'any%', '2015-02-02', '2015-10-03', 6541, 'false', null ),
	   ( 3, 5, 'any%', '2014-02-02', '2014-10-07', 6745, 'false', null ),
	   ( 3, 5, 'any%', '2013-02-02', '2013-11-03', 6952, 'false', null )
;