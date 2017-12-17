--
-- PostgreSQL database dump
--

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

--
-- Name: plpgsql; Type: EXTENSION; Schema: -; Owner: 
--

CREATE EXTENSION IF NOT EXISTS plpgsql WITH SCHEMA pg_catalog;


--
-- Name: EXTENSION plpgsql; Type: COMMENT; Schema: -; Owner: 
--

COMMENT ON EXTENSION plpgsql IS 'PL/pgSQL procedural language';


SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: city_list; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE city_list (
    id integer NOT NULL,
    city text NOT NULL
);


ALTER TABLE public.city_list OWNER TO postgres;

--
-- Name: city_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE city_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.city_list_id_seq OWNER TO postgres;

--
-- Name: city_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE city_list_id_seq OWNED BY city_list.id;


--
-- Name: country_list; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE country_list (
    id integer NOT NULL,
    country text NOT NULL
);


ALTER TABLE public.country_list OWNER TO postgres;

--
-- Name: country_list_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE country_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.country_list_id_seq OWNER TO postgres;

--
-- Name: country_list_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE country_list_id_seq OWNED BY country_list.id;


--
-- Name: data; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE data (
    id integer NOT NULL,
    title text NOT NULL,
    data text NOT NULL,
    building_date text,
    location_id integer,
    wallpaper_image text,
    images_folder text,
    latitude double precision DEFAULT 0,
    longitude double precision DEFAULT 0
);


ALTER TABLE public.data OWNER TO postgres;

--
-- Name: data_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE data_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.data_id_seq OWNER TO postgres;

--
-- Name: data_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE data_id_seq OWNED BY data.id;


--
-- Name: location; Type: TABLE; Schema: public; Owner: postgres; Tablespace: 
--

CREATE TABLE location (
    id integer NOT NULL,
    country_id integer,
    city_id integer
);


ALTER TABLE public.location OWNER TO postgres;

--
-- Name: location_id_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE location_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.location_id_seq OWNER TO postgres;

--
-- Name: location_id_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE location_id_seq OWNED BY location.id;


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY city_list ALTER COLUMN id SET DEFAULT nextval('city_list_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY country_list ALTER COLUMN id SET DEFAULT nextval('country_list_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY data ALTER COLUMN id SET DEFAULT nextval('data_id_seq'::regclass);


--
-- Name: id; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY location ALTER COLUMN id SET DEFAULT nextval('location_id_seq'::regclass);


--
-- Data for Name: city_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY city_list (id, city) FROM stdin;
1	Vanadzor
2	Erevan
3	Gyumri
4	Spitak
\.


--
-- Name: city_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('city_list_id_seq', 4, true);


--
-- Data for Name: country_list; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY country_list (id, country) FROM stdin;
1	Armenia
2	Russia
3	USA
\.


--
-- Name: country_list_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('country_list_id_seq', 3, true);


--
-- Data for Name: data; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY data (id, title, data, building_date, location_id, wallpaper_image, images_folder, latitude, longitude) FROM stdin;
1	Title	some text	1963-09-01	1	\N	\N	\N	\N
2	Title_1	some text 1	1963-09-01	2	\N	\N	\N	\N
3	Title_2	some text 2	1999-09-01	1	wallpaper.jpg	/images	\N	\N
4	Title_3	some text 3	2000-09-01	1	wallpaper.jpg	/images	\N	\N
5	Title_5	some text 5	2000-09-01	1	wallpaper.jpeg	/images	\N	\N
6	Մայր Հայաստան	Մայր Հայաստան հուշահամալիր, հուշահամալիր է Երևանում, կառուցվել է ի նշանավորումն ԽՍՀՄ-ի՝ Հայրենական մեծ պատերազմում տարած հաղթանակի։ Բացվել է 1950 թվականի նոյեմբերի 29-ին Հաղթանակ զբոսայգում։ Հեղինակը ԽՍՀՄ ժողովրդական ճարտարապետ Ռաֆայել Իսրայելյանն է։ Պատվանդանի վրա տեղադրվել էր ԽՍՀՄ ժողովրդական նկարիչ Սերգեյ Մերկուրովի հեղինակած Իոսիֆ Ստալինի 17 մետր բարձրության պղնձե կոփածո արձանը։ 1951 թվականին հեղինակներն արժանացել են ԽՍՀՄ պետական մրցանակի։ 1962 թվականին հանվել է Իոսիֆ Ստալինի արձանը։ 1967 թվականին տեղադրվել է Հայաստանի ժողովրդական նկարիչ Արա Հարությունյանի հեղինակած 22 մետր բարձրության պղնձե կոփածո «Մայր Հայաստան» արձանը։	1950-11-29	1	mother_armenia_wallpaper.jpg	/images	\N	\N
9	New Test_1	Body	1950-11-29	1	wallpaper.jpeg	/images	\N	\N
8	New Test	Body	1950-11-29	1		/images	40.1951859999999996	44.5247030000000024
7	Մայր Հայաստան 2	Մայր Հայաստան հուշահամալիր, հուշահամալիր է Երևանում, կառուցվել է ի նշանավորումն ԽՍՀՄ-ի՝ Հայրենական մեծ պատերազմում տարած հաղթանակի։ Բացվել է 1950 թվականի նոյեմբերի 29-ին Հաղթանակ զբոսայգում։ Հեղինակը ԽՍՀՄ ժողովրդական ճարտարապետ Ռաֆայել Իսրայելյանն է։ Պատվանդանի վրա տեղադրվել էր ԽՍՀՄ ժողովրդական նկարիչ Սերգեյ Մերկուրովի հեղինակած Իոսիֆ Ստալինի 17 մետր բարձրության պղնձե կոփածո արձանը։ 1951 թվականին հեղինակներն արժանացել են ԽՍՀՄ պետական մրցանակի։ 1962 թվականին հանվել է Իոսիֆ Ստալինի արձանը։ 1967 թվականին տեղադրվել է Հայաստանի ժողովրդական նկարիչ Արա Հարությունյանի հեղինակած 22 մետր բարձրության պղնձե կոփածո «Մայր Հայաստան» արձանը։	1950-11-29	1	mother_armenia_wallpaper.jpg	/images	40.1951859999999996	44.5247030000000024
17	Սուրբ Մարիամ Աստվածածին եկեղեցի	Եկեղեցին հիմնադրվել է 1831 թվականին, մեծահամբավ հարյուրապետ Փիրուման Յուզբաշ Թայիրովի կողմից։Հստակ տարեթիվ հայտնի չէ, թե երբ է կառուցվել հին եկեղեցին (մինչ այս եկեղեցու կառուցվելը եղել է այլ եկեղեցի), սակայն կան տեղեկություններ, որ այն եղել է եկեղեցու բակում զետեղված խաչքարերի տեղանքում։ 1826 թվականին Ղարաքիլիսայում մեծ երկրաշարժ է տեղի ունեցել, որի ժամանակ եկեղեցին ավերվել է։ Սակայն Փիրուման Թայիրովը հին եկեղեցու քարերը գործածել է նորի կառուցման համար։ Փիրուման Թայիրովը ծնունդով էրզրումցի էր, եղել է Ռուսական կայսրության զինվորական, որը մեծ հարգանք է վայելել Ղարաքիլիսայում։ 	1831	1	MariamAstvacacin/image.jpeg	MariamAstvacacin	40.8148809999999997	44.4957800000000034
13	Սուրբ Գրիգոր Նարեկացի եկեղեցի	Վանաձորի Առաջնորդանիստ Սուրբ Գրիգոր Նարեկացի Մայր Եկեղեցի, 2005 թվականին կառուցվել է Գարեգին Բ Հայրապետի նախաձեռնությամբ և բարերարությամբ ԱՄՆ-ի Նյու Ջերսիից Սարգիս և Ռուդ Բեդևյան ընտանիքի, ճարտարապետ Ռուբեն Ազատյան: 2007 թվականին եկեղեցու Սուրբ Խորանը զարդարվում է հայազգի սրբերի որմնանկարներով, եկեղեցին պատկերազարդվում է սրբերի հիշատակը հավերժացնող ու հայ եկեղեցու պատմությունն անմահացնող արվեստի գործերով` հովանավորությամբ Վանաձորի քաղաքապետ Սամվել Դարբինյանի, գեներալ Լյովա Երանոսյանի, «Ա. Հակոբյան» ՍՊԸ տնօրեն Արթուր Հակոբյանի, ԱԺ պատգամավոր Կարեն Սարիբեկյանի, «Սեզամ-գազ» ՍՊԸ տնօրեն Արմեն Մխոյանի, «Լոռի» ռեստորանի տնօրեն Արտակ Ղազարյանի։Արմեն Մխոյանի բարերարությամբ եկեղեցում տեղադրվել է թեմակալ առաջնորդ Սեպուհ Սրբազանի գահակալության 10-ամյակին նվիրված գահավորակ։2008 թվականին շարունակվել է եկեղեցու պատկերազարդումը և նոր կտակարանյան պատումների պատկերագրումը համալրվում է ևս երեք մեծադիր նոր կտավներով, որոնք զարդարում են եկեղեցու վերնատան ճակատային մասը։ Կատարվում է նաև եկեղեցու ավանդատան մկրտության ավազանի տեղադրումն ու օծումը։2012 թվականից եկեղեցու ներսում կառուցվել է Սուրբ Գրիգոր Նարեկացու մասունքի Մատուռ-պահարան։ 	2005	1	GrigorNarekaci/image.jpg	GrigorNarekaci	40.8053030000000021	44.4903270000000006
18	Հովհաննես Աբելյանի հուշարձան	ՀԽՍՀ ժողովրդական արտիստ Հովհաննես Աբելյանի հուշարձանը տեղադրվել է 1965 թվականին Հովհաննես Աբելյանի անվան դրամատիկական թատրոնի շենքի հարակից պուրակում։ 2010 թվականին արձանը տեղափոխվել է թատրոնի շենքի առջև։ Ընդգրկված է Վանաձորի պատմության և մշակույթի անշարժ հուշարձանների ցանկում։ 	1965	1	HovhannesAbelyan/image.jpeg	 HovhannesAbelyan	40.8097939999999966	44.494101999999998
\.


--
-- Name: data_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('data_id_seq', 18, true);


--
-- Data for Name: location; Type: TABLE DATA; Schema: public; Owner: postgres
--

COPY location (id, country_id, city_id) FROM stdin;
1	1	1
2	1	2
3	1	3
4	1	4
\.


--
-- Name: location_id_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('location_id_seq', 4, true);


--
-- Name: city_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY city_list
    ADD CONSTRAINT city_list_pkey PRIMARY KEY (id);


--
-- Name: country_list_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY country_list
    ADD CONSTRAINT country_list_pkey PRIMARY KEY (id);


--
-- Name: data_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY data
    ADD CONSTRAINT data_pkey PRIMARY KEY (id);


--
-- Name: location_pkey; Type: CONSTRAINT; Schema: public; Owner: postgres; Tablespace: 
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_pkey PRIMARY KEY (id);


--
-- Name: data_location_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY data
    ADD CONSTRAINT data_location_id_fkey FOREIGN KEY (location_id) REFERENCES location(id);


--
-- Name: location_city_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_city_id_fkey FOREIGN KEY (city_id) REFERENCES city_list(id);


--
-- Name: location_country_id_fkey; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY location
    ADD CONSTRAINT location_country_id_fkey FOREIGN KEY (country_id) REFERENCES country_list(id);


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

