ALTER TABLE restaurante add aberto tinyint(1) not null;
UPDATE restaurante set aberto = true;