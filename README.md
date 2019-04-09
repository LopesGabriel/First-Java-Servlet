# First-Java-Servlet
First attempt to create a Java JDBC + Servlets application.

Postgres Database Create Script
{

CREATE DATABASE firstservlet
  WITH OWNER = postgres
       ENCODING = 'UTF8'
       TABLESPACE = pg_default
       LC_COLLATE = 'Portuguese_Brazil.1252'
       LC_CTYPE = 'Portuguese_Brazil.1252'
       CONNECTION LIMIT = -1;


}

Produto Table Create Script
{

CREATE TABLE public.produtos
(
  produtoid integer NOT NULL DEFAULT nextval('produtos_produtoid_seq'::regclass),
  nome character varying(50) NOT NULL,
  quantidade integer,
  valor numeric,
  CONSTRAINT produto_produtoid_key PRIMARY KEY (produtoid)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.produtos
  OWNER TO postgres;
  
  }
  
  Usuario Table Create Script
  {
  
  CREATE TABLE public.usuario
(
  id integer NOT NULL DEFAULT nextval('usuario_id_seq'::regclass),
  login character varying,
  senha character varying,
  email character varying,
  cep character varying(9),
  rua character varying(150),
  bairro character varying(50),
  cidade character varying(50),
  uf character varying(2),
  file text,
  filetype text,
  CONSTRAINT usuario_id_key PRIMARY KEY (id)
)
WITH (
  OIDS=FALSE
);
ALTER TABLE public.usuario
  OWNER TO postgres;
  
  }
