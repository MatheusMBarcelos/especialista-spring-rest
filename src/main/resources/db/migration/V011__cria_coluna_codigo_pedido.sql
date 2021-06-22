alter table pedido add codigo varchar(36) not null after id;
update pedido set codigo = UUID();
alter table pedido add constraint uk_codigo_pedido unique (codigo);