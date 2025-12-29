-- Create schema explicitly (Debezium defaults to public)
CREATE SCHEMA IF NOT EXISTS public;

-- Orders table
CREATE TABLE IF NOT EXISTS public.orders (
    id            BIGSERIAL PRIMARY KEY,
    user_id       INT NOT NULL,
    amount        NUMERIC(15, 2) NOT NULL,
    status        VARCHAR(30) NOT NULL,
    created_at    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Helpful index for CDC consumers / queries
CREATE INDEX IF NOT EXISTS idx_orders_created_at
    ON public.orders (created_at);
