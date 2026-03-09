-- Database Initialization Script
-- Insert Medianos
INSERT INTO medianos (id, name, height, email) VALUES ('f47ac10b-58cc-4372-a567-0e02b2c3d479', 'Juan', 120, 'juan@example.com');
INSERT INTO medianos (id, name, height, email) VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Alberto', 135, 'alberto@example.com');
INSERT INTO medianos (id, name, height, email) VALUES ('6ba7b810-9dad-11d1-80b4-00c04fd430c8', 'Pedro', 150, 'pedro@example.com');

-- Insert Fotos
INSERT INTO fotos (id, path, description, created, mediano_id) VALUES (1, 'http://example.com/foto1.jpg', 'Foto de perfil de Juan', CURRENT_DATE, 'f47ac10b-58cc-4372-a567-0e02b2c3d479');
INSERT INTO fotos (id, path, description, created, mediano_id) VALUES (2, 'http://example.com/foto2.jpg', 'Alberto de vacaciones', CURRENT_DATE, '550e8400-e29b-41d4-a716-446655440000');
INSERT INTO fotos (id, path, description, created, mediano_id) VALUES (3, 'http://example.com/foto3.jpg', 'Pedro en la playa', CURRENT_DATE, '6ba7b810-9dad-11d1-80b4-00c04fd430c8');

-- Ensure auto-increment IDs for Postgres sync up correctly after manual inserts if we inserted exact IDs
-- Not strictly required for H2 during tests but usually good practice.
