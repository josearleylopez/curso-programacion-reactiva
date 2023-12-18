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
  vendedor_id UUID,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS creditos (
  id UUID default random_uuid(),
  fecha DATE,
  valor FLOAT,
  saldo FLOAT,
  tasa FLOAT,
  cuotas SMALLINT,
  cliente_id UUID,
  PRIMARY KEY (id)
);
CREATE TABLE IF NOT EXISTS cuotas (
  id UUID default random_uuid(),
  fecha DATE,
  valor FLOAT,
  valor_capital FLOAT,
  valor_interes FLOAT,
  credito_id UUID,
  PRIMARY KEY (id)
);