CREATE TABLE IF NOT EXISTS vendedores (
  id UUID default random_uuid(),
  dni VARCHAR2(16),
  nombres VARCHAR2(120),
  apellidos VARCHAR2(120),
  correo_electronico VARCHAR2(120),
  telefono_celular VARCHAR2(16),
  activo BOOLEAN default TRUE,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS clientes (
  id UUID default random_uuid(),
  dni VARCHAR2(16),
  nombres VARCHAR2(120),
  apellidos VARCHAR2(120),
  correo_electronico VARCHAR2(120),
  telefono_celular VARCHAR2(16),
  PRIMARY KEY (id)
);
