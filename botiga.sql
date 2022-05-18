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
-- Name: factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura (
    num_factura integer NOT NULL,
    client character varying(9),
    data_factura date
);


ALTER TABLE public.factura OWNER TO postgres;

--
-- Name: linia_factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.linia_factura (
    num_factura integer NOT NULL,
    num_linia integer NOT NULL,
    preu_producte numeric,
    iva integer,
    quantitat integer,
    nom_producte character varying,
    producte character varying
);


ALTER TABLE public.linia_factura OWNER TO postgres;

--
-- Name: productes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productes (
    codi character varying NOT NULL,
    nom character varying,
    stock integer,
    preu numeric,
    iva integer,
    unitats_venudes integer
);


ALTER TABLE public.productes OWNER TO postgres;

--
-- Data for Name: clients; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.clients (dni, contrasenya, nom, correu_electronic, telefon, adreca) FROM stdin;
41582948Y	Jordi1234	Jordi Dueñas	Jordidaa@gmail.com	610020865	Penya Bisbalenca 19                                                                                                                                                                                     
\.


--
-- Data for Name: factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura (num_factura, client, data_factura) FROM stdin;
\.


--
-- Data for Name: linia_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.linia_factura (num_factura, num_linia, preu_producte, iva, quantitat, nom_producte, producte) FROM stdin;
\.


--
-- Data for Name: productes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productes (codi, nom, stock, preu, iva, unitats_venudes) FROM stdin;
\.


--
-- Name: clients clients_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.clients
    ADD CONSTRAINT clients_pkey PRIMARY KEY (dni);


--
-- Name: factura factura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT factura_pkey PRIMARY KEY (num_factura);


--
-- Name: linia_factura linia_factura_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linia_factura
    ADD CONSTRAINT linia_factura_pkey PRIMARY KEY (num_linia, num_factura);


--
-- Name: productes productes_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.productes
    ADD CONSTRAINT productes_pkey PRIMARY KEY (codi);


--
-- Name: fki_fk_dni; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_dni ON public.factura USING btree (client);


--
-- Name: fki_fk_factura; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_factura ON public.linia_factura USING btree (num_factura);


--
-- Name: fki_fk_producte; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_producte ON public.linia_factura USING btree (producte);


--
-- Name: factura fk_dni; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.factura
    ADD CONSTRAINT fk_dni FOREIGN KEY (client) REFERENCES public.clients(dni) NOT VALID;


--
-- Name: linia_factura fk_factura; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linia_factura
    ADD CONSTRAINT fk_factura FOREIGN KEY (num_factura) REFERENCES public.factura(num_factura) NOT VALID;


--
-- Name: linia_factura fk_producte; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linia_factura
    ADD CONSTRAINT fk_producte FOREIGN KEY (producte) REFERENCES public.productes(codi) NOT VALID;


--
-- PostgreSQL database dump complete
--

