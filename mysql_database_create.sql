create table album (id bigint not null auto_increment, title varchar(255), year_of_release integer, band_id bigint, genre_id bigint, label_id bigint, primary key (id)) ENGINE=InnoDB;
create table album_member (album_id bigint not null, member_id bigint not null, primary key (album_id, member_id)) ENGINE=InnoDB;
create table album_studio (album_id bigint not null, studio_id bigint not null, primary key (album_id, studio_id)) ENGINE=InnoDB;
create table band (id bigint not null auto_increment, name varchar(255), year_disbanded integer, year_founded integer, genre_id bigint, primary key (id)) ENGINE=InnoDB;
create table band_event (band_id bigint not null, event_id bigint not null, primary key (band_id, event_id)) ENGINE=InnoDB;
create table band_member (band_id bigint not null, member_id bigint not null, primary key (band_id, member_id)) ENGINE=InnoDB;
create table event (id bigint not null auto_increment, event_date tinyblob, type varchar(255), promoter_id bigint, venue_id bigint, primary key (id)) ENGINE=InnoDB;
create table genre (id bigint not null auto_increment, name varchar(255), primary key (id)) ENGINE=InnoDB;
create table label (id bigint not null auto_increment, name varchar(255) not null, year_founded integer, primary key (id)) ENGINE=InnoDB;
create table location (id bigint not null auto_increment, place varchar(255), primary key (id)) ENGINE=InnoDB;
create table member (id bigint not null auto_increment, name varchar(255), primary key (id)) ENGINE=InnoDB;
create table promoter (id bigint not null auto_increment, name varchar(255) not null, year_founded integer, primary key (id)) ENGINE=InnoDB;
create table studio (id bigint not null auto_increment, name varchar(255), location_id bigint, primary key (id)) ENGINE=InnoDB;
create table venue (id bigint not null auto_increment, name varchar(255), location_id bigint, primary key (id)) ENGINE=InnoDB;
alter table album add constraint FK84w6mugh985e3ouf2qcqn9v5o foreign key (band_id) references band (id);
alter table album add constraint FKmhihrmrx2f1mhqtrbagwru45g foreign key (genre_id) references genre (id);
alter table album add constraint FK9v7wrblbhdunry09r94vamtuj foreign key (label_id) references label (id);
alter table album_member add constraint FKmm4ee0bmusecug67v0iew5qwm foreign key (member_id) references member (id);
alter table album_member add constraint FKye8nm324jqnuceqyna1ds9fm foreign key (album_id) references album (id);
alter table album_studio add constraint FKlwir5q3ygdbq226to5q3acr4d foreign key (studio_id) references studio (id);
alter table album_studio add constraint FKayqyqqluc2ib0844srkhm9b7p foreign key (album_id) references album (id);
alter table band add constraint FKskvcr1csl2cg98qgcus4g9r55 foreign key (genre_id) references genre (id);
alter table band_event add constraint FK2o39ijm2hktdgfimtbdjbmrir foreign key (event_id) references band (id);
alter table band_event add constraint FKilauofkevnuyu8js3o84l7rc foreign key (band_id) references event (id);
alter table band_member add constraint FKtdge8j6t2m9i5h8rcjk3ju0vq foreign key (member_id) references member (id);
alter table band_member add constraint FK260yqm4dj85eh8d8xsy0pteas foreign key (band_id) references band (id);
alter table event add constraint FKdr9fyhncfw4q8y8c1roa2owte foreign key (promoter_id) references promoter (id);
alter table event add constraint FKthgbmd6s6hp4l47kx1sh4cf9n foreign key (venue_id) references venue (id);
alter table studio add constraint FKs69hwiawanlmld53jju1hwyop foreign key (location_id) references location (id);
alter table venue add constraint FKd2ntchpg6g1dhvj349p7e0qyw foreign key (location_id) references location (id);