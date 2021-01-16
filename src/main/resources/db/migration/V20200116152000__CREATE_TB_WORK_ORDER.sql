create table TB_WORK_ORDER (
	id serial,
	client_id bigint not null,
	description text not null,
	price numeric(10,2) not null,
	status varchar(20) not null,
	open_date timestamp not null,
	end_date timestamp,
	
	primary key (id)
);

alter table TB_WORK_ORDER add constraint FK_ORDER_CLIENT
foreign key (client_id) references TB_CLIENT (id);