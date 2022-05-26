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
    adreca character varying(200)
);


ALTER TABLE public.clients OWNER TO postgres;

--
-- Name: factura; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.factura (
    num_factura integer NOT NULL,
    data_factura date,
    dni character varying(9),
    nom character varying,
    correu_electronic character varying,
    telefon integer,
    adreca character varying(200)
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
    codi_producte character varying(4)
);


ALTER TABLE public.linia_factura OWNER TO postgres;

--
-- Name: productes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.productes (
    codi character varying(4) NOT NULL,
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
49582915L	Pepe12345	Pepe	pepe@gmail.com	976363938	Carrer jaume 2n girona
41582948Y	Jordi1234	Jordi Dueñas	jordi@gmail.com	610020865	Penya Bisbalenca 19
41582949P	Manolo123	Manolo	manolo@gmail.com	654983217	Placa major la bisbal
41582628T	Guillem1234	Guillem	guillem@gmail.com	784512369	Avinguda montilivi girona
41235689Y	Mias12345	Jordi mias	jmias@gmail.com	123456789	Calonge
41587859T	Joel12345	Joel	joel@gmail.com	456789123	La bisbal
78945612Y	Iu1234567	Iu	iu@gmail.com	456789321	Palamos
78965425Y	Jaume1234	Jaume	jaume@palamos.com	456987451	Calonge
69898989Y	Maria1234	Maria	maria@labisbal.cat	412536789	La bisbal
\.


--
-- Data for Name: factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.factura (num_factura, data_factura, dni, nom, correu_electronic, telefon, adreca) FROM stdin;
1	2022-05-24	41582948Y	Jordi Dueñas	Jordidaa@gmail.com	610020865	Penya Bisbalenca 19
2	2022-05-24	41582948Y	Jordi Dueñas	Jordidaa@gmail.com	610020865	Penya Bisbalenca 19
3	2022-05-24	41582948Y	Jordi Dueñas	jordi@gmail.com	610020865	Penya Bisbalenca 19
4	2022-05-26	41235689Y	Jordi mias	jmias@gmail.com	123456789	Calonge
5	2022-05-26	41582948Y	Jordi Dueñas	jordi@gmail.com	610020865	Penya Bisbalenca 19
6	2022-05-26	41582948Y	Jordi Dueñas	jordi@gmail.com	610020865	Penya Bisbalenca 19
7	2022-05-26	41582948Y	Jordi Dueñas	jordi@gmail.com	610020865	Penya Bisbalenca 19
\.


--
-- Data for Name: linia_factura; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.linia_factura (num_factura, num_linia, preu_producte, iva, quantitat, nom_producte, codi_producte) FROM stdin;
1	1	2	21	5	Red bull	RB01
1	2	0.5	21	6	Cocacola	CO01
2	1	2	21	1	Red bull	RB01
3	1	1.95	20	1	Red bull	RB01
4	1	0.6	21	4	Fanta de llimona	01FA
4	2	2	4	4	Llimonada	LL00
5	1	0.6	4	5	Vichi catalan	VC01
6	1	0.6	4	1	Vichi catalan	VC01
7	1	2	4	2	Llimonada	LL00
\.


--
-- Data for Name: productes; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY public.productes (codi, nom, stock, preu, iva, unitats_venudes) FROM stdin;
02FA	Fanta de tronja	9	0.6	21	0
AG01	Aigua font vella	7	0.4	6	0
CO01	Cocacola	189	0.5	21	21
RB01	Red bull	5	1.95	20	7
CO02	Cocacola zero	25	0.5	16	0
AQ01	aquarius llimona	5	0.6	25	0
ZT01	Suc de tronja	5	1	25	0
01FA	Fanta de llimona	0	0.6	21	4
VC01	Vichi catalan	8	0.6	4	6
LL00	Llimonada	0	2	4	6
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
-- Name: fki_fk_factura; Type: INDEX; Schema: public; Owner: postgres
--

CREATE INDEX fki_fk_factura ON public.linia_factura USING btree (num_factura);


--
-- Name: linia_factura fk_factura; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.linia_factura
    ADD CONSTRAINT fk_factura FOREIGN KEY (num_factura) REFERENCES public.factura(num_factura) NOT VALID;


--
-- PostgreSQL database dump complete
--

