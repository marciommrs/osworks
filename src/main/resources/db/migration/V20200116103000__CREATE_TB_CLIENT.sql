create table TB_CLIENT (
	id serial,
	name varchar(60) not null,
	email varchar(255) not null,
	tel varchar(20) not null,
	
	primary key (id)
);