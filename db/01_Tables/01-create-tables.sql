/*
* Saul Echeverri
* 25-02-2026
* Ejercicio práctico
* Script de creación de tablas: orders y order_items
* Relaciones entre customers, orders, products y order_items
* Manual de Uso:
* 1. Abrir un espacio de comentarios entre corchetes
* 2. Definir el Usuario que realiza la modificación
* 3. Fecha en la cual se realiza modificación
* 4. Detalle o definición de la modificación
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

/*
* Saul Echeverri
* 25-02-2026
* Script de creación de tabla: orders
*/
-- Tabla orders
CREATE TABLE IF NOT EXISTS public.orders (
    order_id SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    status VARCHAR(50) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    customer_id INTEGER NOT NULL REFERENCES public.customers(id_customer)
);
COMMENT ON TABLE orders IS 'Tabla que registra las órdenes de compra de los clientes';
COMMENT ON COLUMN orders.order_id IS 'Identificador único de la orden';
COMMENT ON COLUMN orders.created_at IS 'Fecha de creación de la orden de compra';
COMMENT ON COLUMN orders.status IS 'Estado actual de la orden (ejemplo: pendiente, completado, cancelado)';
COMMENT ON COLUMN orders.updated_at IS 'Fecha de actualización de la orden';
COMMENT ON COLUMN orders.customer_id IS 'Identificador del cliente que realiza la orden de compra (relación con customers)';


/*
* Saul Echeverri
* 25-02-2026
* Script de creación de tabla: order_items
*/
-- Tabla order_items
CREATE TABLE IF NOT EXISTS public.order_items (
    id_order_item SERIAL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL DEFAULT NOW(),
    quantity INTEGER NOT NULL,
    subtotal NUMERIC(10,2) NOT NULL,
    unit_price NUMERIC(10,2) NOT NULL,
    updated_at TIMESTAMP DEFAULT NULL,
    order_id INTEGER NOT NULL REFERENCES public.orders(order_id),
    product_id INTEGER NOT NULL REFERENCES public.products(id_product)
);
COMMENT ON TABLE order_items IS 'Tabla que registra los ítems incluidos en cada orden de compra';
COMMENT ON COLUMN order_items.id_order_item IS 'Identificador único del ítem de la orden';
COMMENT ON COLUMN order_items.created_at IS 'Fecha de creación del ítem de la orden de compra';
COMMENT ON COLUMN order_items.quantity IS 'Cantidad de producto solicitada en el ítem';
COMMENT ON COLUMN order_items.subtotal IS 'Subtotal del ítem (quantity * unit_price)';
COMMENT ON COLUMN order_items.unit_price IS 'Precio unitario del producto en la orden';
COMMENT ON COLUMN order_items.updated_at IS 'Fecha de actualización del ítem de la orden';
COMMENT ON COLUMN order_items.order_id IS 'Identificador de la orden a la que pertenece el ítem (relación con orders)';
COMMENT ON COLUMN order_items.product_id IS 'Identificador del producto comprado (relación con products)';