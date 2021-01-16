create table TB_COMMENT (
	id serial,
	work_order_id bigint not null,	
	description text not null,
	send_date timestamp not null,
	
	primary key (id)
);

alter table TB_COMMENT add constraint FK_COMMENT_WORK_ORDER
foreign key (work_order_id) references TB_WORK_ORDER (id);