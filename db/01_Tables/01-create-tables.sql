/*
* Saul Echeverri
* 25-02-2026
* Ejercicio práctico
* Version Inicial - Script de creación de tablas: customers y products
* Manual de Uso:
* 1. Abrir un espacio de comentarios entre corchetes
* 2. Definir el Usuario que realiza la modificación
* 3. Fecha en la cual se realiza modificación
* 4. Detalle o definicion de la modificación
* 5. Anexar script para ser ejecutado
* 6. Generar un Pull Request siguiendo el proceso establecido para actualización de ramas
*/

/*
* Saul Echeverri
* 24-02-2026
* Script de creación de tabla: customers
*/
-- Tabla customers
CREATE TABLE IF NOT EXISTS public.customers (
    id_customer SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    email VARCHAR(100) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL
);
COMMENT ON TABLE customers IS 'Tabla que registra la información de los clientes';
COMMENT ON COLUMN customers.id_customer IS 'Identificador único del cliente';
COMMENT ON COLUMN customers.created_at IS 'Fecha de creación del registro';
COMMENT ON COLUMN customers.email IS 'Correo electrónico del cliente, único';
COMMENT ON COLUMN customers.full_name IS 'Nombre completo del cliente';
COMMENT ON COLUMN customers.updated_at IS 'Fecha de actualización del registro';


/*
* Saul Echeverri
* 24-02-2026
* Script de creación de tabla: products
*/
-- Tabla products
CREATE TABLE IF NOT EXISTS public.products (
    id_product SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    description VARCHAR(255) DEFAULT NULL,
    name VARCHAR(100) NOT NULL,
    price NUMERIC(10,2) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL
);
COMMENT ON TABLE products IS 'Tabla que registra la información de los productos';
COMMENT ON COLUMN products.id_product IS 'Identificador único de producto';
COMMENT ON COLUMN products.created_at IS 'Fecha de creación del registro';
COMMENT ON COLUMN products.description IS 'Descripción del producto';
COMMENT ON COLUMN products.name IS 'Nombre del producto';
COMMENT ON COLUMN products.price IS 'Precio del producto';
COMMENT ON COLUMN products.updated_at IS 'Fecha de actualización del registro';