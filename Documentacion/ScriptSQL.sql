-- ------------------------------------------
-- Creacion de la base de datos
-- ------------------------------------------

create database if not exists base_de_datos_web;
use base_de_datos_web;

-- ------------------------------------------
-- Tabla roles
-- ------------------------------------------

create table if not exists roles(

id 				int auto_increment,
role_name 		varchar(45) not null unique,

constraint pk_role
	primary key (id)

) Engine = InnoDB ;

-- ------------------------------------------
-- Tabla Usuarios
-- ------------------------------------------

create table if not exists users(

id 			INT NOT NULL AUTO_INCREMENT,
user_name 	VARCHAR(255) NOT NULL UNIQUE,
mail 		VARCHAR(45) NOT NULL UNIQUE,
password 	VARCHAR(255) NOT NULL,
roles_id 	INT NOT NULL,
created_at 	TIMESTAMP NOT NULL,
updated_at	TIMESTAMP NULL,

CONSTRAINT pk_users 
	primary key (id),
    
constraint fk_user_roles1 
	foreign key (roles_id)
    references roles(id)
    on delete cascade
    on update cascade

)Engine = InnoDB ;

-- ------------------------------------------
-- Tabla Personas
-- ------------------------------------------

create table if not exists persons(

id 			INT NOT NULL AUTO_INCREMENT,
users_id 	INT NOT NULL,
name 		VARCHAR(45) NOT NULL,
surname 	VARCHAR(45) NOT NULL,
DNI 		VARCHAR(45) NOT NULL UNIQUE,
image 		VARCHAR(45) NULL,
address 	VARCHAR(45) NOT NULL,
phone 		VARCHAR(45) NOT NULL,

constraint pk_persons primary key (id),

constraint fk_persons_users
	foreign key (users_id)
    references users(id)
    on delete cascade
    on update cascade
    
)Engine = InnoDB;

-- --------------------------------
-- Tabla Categorias
-- --------------------------------

create table if not exists categories(

id				int not null auto_increment,
category_name	VARCHAR(45) NOT NULL UNIQUE,
description		TEXT NOT NULL,

constraint pk_categories
	primary key (id)

)Engine = InnoDB;

-- --------------------------------
-- Tabla Productos
-- --------------------------------
CREATE TABLE IF NOT EXISTS products (

id				INT NOT NULL AUTO_INCREMENT,
name			VARCHAR(45) NOT NULL,
code			VARCHAR(255) NOT NULL UNIQUE,
price			DOUBLE NOT NULL,
description		TEXT NOT NULL,
stock			INT NOT NULL,
image			VARCHAR(45) NOT NULL,

CONSTRAINT pk_products
	primary key (id)

)Engine = InnoDB;

-- --------------------------------
-- Tabla Categoria de productos
-- --------------------------------

CREATE TABLE IF NOT EXISTS products_categories (
id					INT NOT NULL,
categories_id		INT NOT NULL,
products_id 		INT NOT NULL,

constraint pk_productos_categories
	primary key (id),
    
CONSTRAINT fk_products_categories_1
    FOREIGN KEY (categories_id)
    REFERENCES categories(id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_products_categories_products_1
    FOREIGN KEY (products_id)
    REFERENCES products (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    
)ENGINE = InnoDB;

-- -----------------------------------------------------
-- Tabla Pedidos
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS orders (

id				INT NOT NULL,
persons_id 		INT NOT NULL,
ammount			VARCHAR(45) NOT NULL,
order_date 		TIMESTAMP NOT NULL,
order_status	VARCHAR(45) NOT NULL,
  
constraint pk_orders
	primary key (id),
    
  CONSTRAINT fk_orders_persons_1
    FOREIGN KEY (persons_id)
    REFERENCES persons (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
) ENGINE = InnoDB;


-- -----------------------------------------------------
-- Tabla Detalle de Pedidos
-- -----------------------------------------------------

CREATE TABLE IF NOT EXISTS order_details (
id			INT NOT NULL,
orders_id	INT NOT NULL,
products_id INT NOT NULL,
quantity	INT NOT NULL,
total		DOUBLE NOT NULL,

constraint pk_order_datails
	primary key (id),
    
  CONSTRAINT fk_order_details_orders_1
    FOREIGN KEY (orders_id)
    REFERENCES orders (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    
  CONSTRAINT fk_order_details_products_1
    FOREIGN KEY (products_id)
    REFERENCES products (id)
    ON DELETE CASCADE
    ON UPDATE CASCADE
    
)ENGINE = InnoDB;