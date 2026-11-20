DROP DATABASE IF EXISTS operadora_movil;
CREATE DATABASE operadora_movil CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE operadora_movil;
CREATE TABLE cliente (
                         idcliente INT PRIMARY KEY AUTO_INCREMENT,
                         nombre    VARCHAR(100) NOT NULL,
                         dni       CHAR(8) NOT NULL
);
CREATE TABLE plan (
                      idplan         INT PRIMARY KEY,
                      nombre         VARCHAR(50) NOT NULL,
                      precio_mensual DECIMAL(6,2) NOT NULL
);
CREATE TABLE linea (
                       idlinea          INT PRIMARY KEY AUTO_INCREMENT,
                       numero           CHAR(9) NOT NULL UNIQUE,
                       fecha_activacion DATE NOT NULL,
                       idcliente        INT NOT NULL,
                       idplan           INT NOT NULL,
                       FOREIGN KEY (idcliente) REFERENCES cliente(idcliente),
                       FOREIGN KEY (idplan)    REFERENCES plan(idplan)
);
INSERT INTO cliente (nombre, dni) VALUES
                                      ('Ana Torres','45678912'),('Luis Ramírez','71234567'),
                                      ('Rosa Quispe','40987654'),('Jorge Huamán','46781234');
INSERT INTO plan VALUES
                     (1,'Básico',29.90),(2,'Ilimitado',59.90),(3,'Empresarial',89.90),(4,'Prepago Plus',19.90);
INSERT INTO linea (numero, fecha_activacion, idcliente, idplan) VALUES
                                                                    ('987654321','2026-08-01',1,2),
                                                                    ('912345678','2026-09-15',2,1),

                                                                    ('998877665','2026-10-03',1,3),
                                                                    ('945612378','2026-07-20',3,2),
                                                                    ('956781234','2026-06-11',4,2),
                                                                    ('923456789','2026-10-28',3,1);