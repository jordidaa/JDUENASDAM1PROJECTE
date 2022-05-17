--
-- PostgreSQL database dump
--

-- Dumped from database version 14.1
-- Dumped by pg_dump version 14.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: clients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.clients (
    dni character varying(9) NOT NULL,
    contrasenya character varying,
    nom character varying,
    correu_electronic character varying,
    telefon integer,
    adreca character(200)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (dni, contrasenya, nom, correu_electronic, telefon, adreca) FROM stdin;
41582948Y	Jordi1234	Jordi Dueñas	Jordidaa@gmail.com	610020865	Penya Bisbalenca 19                                                                                                                                                                                     
\.


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (dni);


--
-- PostgreSQL database dump complete
--

