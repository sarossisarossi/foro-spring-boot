-- Insertar datos en tabla usuario
INSERT INTO usuario (nombre, correo_electronico, contrasena) VALUES
  ('Ana Gómez', 'ana.gomez@example.com', '1234hashed'),
  ('Luis Pérez', 'luis.perez@example.com', 'abcdhashed'),
  ('María Torres', 'maria.torres@example.com', 'xyz123hashed'),
  ('Carlos Soto', 'carlos.soto@example.com', 'pass123hashed'),
  ('Javiera Díaz', 'javiera.diaz@example.com', 'securehashed');

-- Insertar datos en tabla curso (agrupados por categoría)
INSERT INTO curso (nombre, categoria) VALUES
  ('Introducción a Java', 'Programación'),
  ('API REST con Spring', 'Programación'),
  ('Spring Boot desde cero', 'Frameworks'),
  ('Fundamentos de HTML y CSS', 'Web'),
  ('Diseño Web Responsivo', 'Web');