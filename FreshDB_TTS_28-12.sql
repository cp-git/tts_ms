PGDMP                         {            tts    14.8    14.8 �    �           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            �           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            �           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            �           1262    181522    tts    DATABASE     g   CREATE DATABASE tts WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE tts;
                postgres    false            �            1255    181523 �   employeepassword(integer, integer, character varying, character varying, date, character varying, character varying, character varying) 	   PROCEDURE     �  CREATE PROCEDURE public.employeepassword(IN p_countryid integer, IN p_companyid integer, IN p_firstname character varying, IN p_lastname character varying, IN p_dob date, IN p_email character varying, IN p_username character varying, IN p_password character varying)
    LANGUAGE plpgsql
    AS $$
DECLARE
  v_empid integer;
BEGIN
  -- Insert into employee table and get the empid
  INSERT INTO employee (countryid, companyid, firstname, lastname, dob, email)
  VALUES (p_countryid, p_companyid, p_firstname, p_lastname, p_dob, p_email)
  RETURNING id INTO v_empid;

  -- Insert into password table with the retrieved empid
  INSERT INTO password (empid, username, password)
  VALUES (v_empid, p_username, p_password);
END;
$$;
 
  DROP PROCEDURE public.employeepassword(IN p_countryid integer, IN p_companyid integer, IN p_firstname character varying, IN p_lastname character varying, IN p_dob date, IN p_email character varying, IN p_username character varying, IN p_password character varying);
       public          postgres    false            �            1259    181524    company    TABLE     (  CREATE TABLE public.company (
    id integer NOT NULL,
    address character varying(255),
    code character varying(255),
    contactemail character varying(255),
    contactphone character varying(255),
    countryid integer,
    name character varying(255),
    zip character varying(255)
);
    DROP TABLE public.company;
       public         heap    postgres    false            �            1259    181529    company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.company_id_seq;
       public          postgres    false    209            �           0    0    company_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.company_id_seq OWNED BY public.company.id;
          public          postgres    false    210            �            1259    181530    companyphotos    TABLE     {   CREATE TABLE public.companyphotos (
    id integer NOT NULL,
    companyid integer,
    filename character varying(255)
);
 !   DROP TABLE public.companyphotos;
       public         heap    postgres    false            �            1259    181533    companyphotos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.companyphotos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.companyphotos_id_seq;
       public          postgres    false    211            �           0    0    companyphotos_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.companyphotos_id_seq OWNED BY public.companyphotos.id;
          public          postgres    false    212            �            1259    181534    country    TABLE     l   CREATE TABLE public.country (
    id integer NOT NULL,
    code integer,
    name character varying(255)
);
    DROP TABLE public.country;
       public         heap    postgres    false            �            1259    181537    country_id_seq    SEQUENCE     �   CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    213            �           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    214            �            1259    181538    employee    TABLE     @  CREATE TABLE public.employee (
    id integer NOT NULL,
    isadmin boolean,
    dob timestamp without time zone,
    companyid integer,
    countryid integer,
    email character varying(255),
    firstname character varying(255),
    lastname character varying(255),
    showalltasks boolean,
    isonbench boolean
);
    DROP TABLE public.employee;
       public         heap    postgres    false            �            1259    181543    employee_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.employee_id_seq;
       public          postgres    false    215            �           0    0    employee_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;
          public          postgres    false    216            �            1259    181544    employeephotos    TABLE     }   CREATE TABLE public.employeephotos (
    id integer NOT NULL,
    employeeid integer,
    filename character varying(255)
);
 "   DROP TABLE public.employeephotos;
       public         heap    postgres    false            �            1259    181547    employeephotos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employeephotos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.employeephotos_id_seq;
       public          postgres    false    217            �           0    0    employeephotos_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.employeephotos_id_seq OWNED BY public.employeephotos.id;
          public          postgres    false    218            �            1259    181548    externaltask    TABLE     ,  CREATE TABLE public.externaltask (
    externalid integer NOT NULL,
    candidatecompany character varying,
    companyaddress character varying,
    hrname character varying,
    hremail character varying,
    hrphone character varying,
    taskid bigint,
    candidatename character varying,
    hiringcompanyname character varying,
    jobtitle character varying,
    experiencerequired bigint,
    joblocationid bigint,
    taxtypeid bigint,
    rate bigint,
    recruitername character varying,
    recruiteremail character varying,
    jobsubmissionportalid bigint,
    dateposted timestamp without time zone,
    joblink character varying,
    visaid bigint,
    jobportalid integer,
    jobreferencenumber character varying,
    recruiterphone character varying,
    portalname character varying(255)
);
     DROP TABLE public.externaltask;
       public         heap    postgres    false            �            1259    181553    externaltask_externalid_seq    SEQUENCE     �   CREATE SEQUENCE public.externaltask_externalid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.externaltask_externalid_seq;
       public          postgres    false    219            �           0    0    externaltask_externalid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.externaltask_externalid_seq OWNED BY public.externaltask.externalid;
          public          postgres    false    220            �            1259    181554    internaltask    TABLE     }  CREATE TABLE public.internaltask (
    internalid integer NOT NULL,
    hiringcompanyname character varying,
    jobtitle character varying,
    experiencerequired bigint,
    joblocationid bigint,
    taxtypeid bigint,
    rate bigint,
    recruitername character varying,
    recruiteremail character varying,
    jobsubmissionportalid bigint,
    dateposted timestamp without time zone,
    joblink character varying,
    taskid bigint,
    visaid bigint,
    jobreferencenumber character varying,
    jobportalid integer,
    recruiterphone character varying,
    internalcandidateid bigint,
    portalname character varying(255)
);
     DROP TABLE public.internaltask;
       public         heap    postgres    false            �            1259    181559    internaltask_internalid_seq    SEQUENCE     �   CREATE SEQUENCE public.internaltask_internalid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 2   DROP SEQUENCE public.internaltask_internalid_seq;
       public          postgres    false    221            �           0    0    internaltask_internalid_seq    SEQUENCE OWNED BY     [   ALTER SEQUENCE public.internaltask_internalid_seq OWNED BY public.internaltask.internalid;
          public          postgres    false    222            �            1259    181560    joblocation    TABLE     �   CREATE TABLE public.joblocation (
    locationid integer NOT NULL,
    locationtype character varying,
    locationdescription character varying,
    companyid integer,
    isforbench boolean,
    isforsourcing boolean
);
    DROP TABLE public.joblocation;
       public         heap    postgres    false            �            1259    181565    joblocation_locationid_seq    SEQUENCE     �   CREATE SEQUENCE public.joblocation_locationid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 1   DROP SEQUENCE public.joblocation_locationid_seq;
       public          postgres    false    223            �           0    0    joblocation_locationid_seq    SEQUENCE OWNED BY     Y   ALTER SEQUENCE public.joblocation_locationid_seq OWNED BY public.joblocation.locationid;
          public          postgres    false    224            �            1259    181566 	   jobportal    TABLE     �   CREATE TABLE public.jobportal (
    portalid integer NOT NULL,
    portalname character varying,
    portaldescription character varying,
    companyid integer,
    isforbench boolean,
    isforsourcing boolean
);
    DROP TABLE public.jobportal;
       public         heap    postgres    false            �            1259    181571    jobportal_portalid_seq    SEQUENCE     �   CREATE SEQUENCE public.jobportal_portalid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 -   DROP SEQUENCE public.jobportal_portalid_seq;
       public          postgres    false    225            �           0    0    jobportal_portalid_seq    SEQUENCE OWNED BY     Q   ALTER SEQUENCE public.jobportal_portalid_seq OWNED BY public.jobportal.portalid;
          public          postgres    false    226            �            1259    181572    password    TABLE     �   CREATE TABLE public.password (
    id integer NOT NULL,
    empid integer,
    password character varying(255),
    username character varying(255)
);
    DROP TABLE public.password;
       public         heap    postgres    false            �            1259    181577    password_id_seq    SEQUENCE     �   CREATE SEQUENCE public.password_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.password_id_seq;
       public          postgres    false    227            �           0    0    password_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.password_id_seq OWNED BY public.password.id;
          public          postgres    false    228            �            1259    181578 	   placement    TABLE     �   CREATE TABLE public.placement (
    placementid bigint NOT NULL,
    placementtype character varying,
    placementdescription character varying
);
    DROP TABLE public.placement;
       public         heap    postgres    false            �            1259    181583    reason    TABLE     �   CREATE TABLE public.reason (
    id integer NOT NULL,
    assignedto integer,
    chgdatetime timestamp without time zone,
    employeeid integer,
    reason character varying(255),
    statusid integer,
    taskid integer
);
    DROP TABLE public.reason;
       public         heap    postgres    false            �            1259    181586    reason_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reason_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.reason_id_seq;
       public          postgres    false    230            �           0    0    reason_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.reason_id_seq OWNED BY public.reason.id;
          public          postgres    false    231            �            1259    181587    status    TABLE     A  CREATE TABLE public.status (
    id integer NOT NULL,
    actualenddate boolean NOT NULL,
    actualstartdate boolean NOT NULL,
    companyid integer NOT NULL,
    finalstatus boolean NOT NULL,
    code character varying(255) NOT NULL,
    description character varying(255) NOT NULL,
    statusorder integer NOT NULL
);
    DROP TABLE public.status;
       public         heap    postgres    false            �            1259    181592    status_id_seq    SEQUENCE     �   CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.status_id_seq;
       public          postgres    false    232            �           0    0    status_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;
          public          postgres    false    233            �            1259    181593    task    TABLE       CREATE TABLE public.task (
    id integer NOT NULL,
    companyid integer NOT NULL,
    havingchild boolean,
    actualenddate timestamp without time zone,
    actualstartdate timestamp without time zone,
    assignedto integer NOT NULL,
    createdby integer NOT NULL,
    description character varying(255) NOT NULL,
    enddate timestamp without time zone NOT NULL,
    name character varying(255) NOT NULL,
    parent integer,
    startdate timestamp without time zone NOT NULL,
    status integer NOT NULL,
    placementid integer
);
    DROP TABLE public.task;
       public         heap    postgres    false            �            1259    181598    task_id_seq    SEQUENCE     �   CREATE SEQUENCE public.task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.task_id_seq;
       public          postgres    false    234            �           0    0    task_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.task_id_seq OWNED BY public.task.id;
          public          postgres    false    235            �            1259    181599    taskattachment    TABLE     �   CREATE TABLE public.taskattachment (
    id integer NOT NULL,
    attachedby integer,
    filename character varying(255),
    taskid integer
);
 "   DROP TABLE public.taskattachment;
       public         heap    postgres    false            �            1259    181602    taskattachment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.taskattachment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.taskattachment_id_seq;
       public          postgres    false    236            �           0    0    taskattachment_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.taskattachment_id_seq OWNED BY public.taskattachment.id;
          public          postgres    false    237            �            1259    181603    taxtype    TABLE     �   CREATE TABLE public.taxtype (
    taxtypeid integer NOT NULL,
    taxtypename character varying,
    taxtypedescription character varying,
    companyid integer,
    isforbench boolean,
    isforsourcing boolean
);
    DROP TABLE public.taxtype;
       public         heap    postgres    false            �            1259    181608    taxtype_taxtypeid_seq    SEQUENCE     �   CREATE SEQUENCE public.taxtype_taxtypeid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.taxtype_taxtypeid_seq;
       public          postgres    false    238            �           0    0    taxtype_taxtypeid_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.taxtype_taxtypeid_seq OWNED BY public.taxtype.taxtypeid;
          public          postgres    false    239            �            1259    181609    visatype    TABLE     �   CREATE TABLE public.visatype (
    visaid integer NOT NULL,
    visatype character varying,
    visadescription character varying,
    companyid integer,
    isforbench boolean,
    isforsourcing boolean
);
    DROP TABLE public.visatype;
       public         heap    postgres    false            �            1259    181614    visatype_visaid_seq    SEQUENCE     �   CREATE SEQUENCE public.visatype_visaid_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 *   DROP SEQUENCE public.visatype_visaid_seq;
       public          postgres    false    240            �           0    0    visatype_visaid_seq    SEQUENCE OWNED BY     K   ALTER SEQUENCE public.visatype_visaid_seq OWNED BY public.visatype.visaid;
          public          postgres    false    241            �           2604    181774 
   company id    DEFAULT     h   ALTER TABLE ONLY public.company ALTER COLUMN id SET DEFAULT nextval('public.company_id_seq'::regclass);
 9   ALTER TABLE public.company ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �           2604    181775    companyphotos id    DEFAULT     t   ALTER TABLE ONLY public.companyphotos ALTER COLUMN id SET DEFAULT nextval('public.companyphotos_id_seq'::regclass);
 ?   ALTER TABLE public.companyphotos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �           2604    181776 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �           2604    181777    employee id    DEFAULT     j   ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);
 :   ALTER TABLE public.employee ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �           2604    181778    employeephotos id    DEFAULT     v   ALTER TABLE ONLY public.employeephotos ALTER COLUMN id SET DEFAULT nextval('public.employeephotos_id_seq'::regclass);
 @   ALTER TABLE public.employeephotos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �           2604    181779    externaltask externalid    DEFAULT     �   ALTER TABLE ONLY public.externaltask ALTER COLUMN externalid SET DEFAULT nextval('public.externaltask_externalid_seq'::regclass);
 F   ALTER TABLE public.externaltask ALTER COLUMN externalid DROP DEFAULT;
       public          postgres    false    220    219            �           2604    181780    internaltask internalid    DEFAULT     �   ALTER TABLE ONLY public.internaltask ALTER COLUMN internalid SET DEFAULT nextval('public.internaltask_internalid_seq'::regclass);
 F   ALTER TABLE public.internaltask ALTER COLUMN internalid DROP DEFAULT;
       public          postgres    false    222    221            �           2604    181781    joblocation locationid    DEFAULT     �   ALTER TABLE ONLY public.joblocation ALTER COLUMN locationid SET DEFAULT nextval('public.joblocation_locationid_seq'::regclass);
 E   ALTER TABLE public.joblocation ALTER COLUMN locationid DROP DEFAULT;
       public          postgres    false    224    223            �           2604    181782    jobportal portalid    DEFAULT     x   ALTER TABLE ONLY public.jobportal ALTER COLUMN portalid SET DEFAULT nextval('public.jobportal_portalid_seq'::regclass);
 A   ALTER TABLE public.jobportal ALTER COLUMN portalid DROP DEFAULT;
       public          postgres    false    226    225            �           2604    181783    password id    DEFAULT     j   ALTER TABLE ONLY public.password ALTER COLUMN id SET DEFAULT nextval('public.password_id_seq'::regclass);
 :   ALTER TABLE public.password ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            �           2604    181784 	   reason id    DEFAULT     f   ALTER TABLE ONLY public.reason ALTER COLUMN id SET DEFAULT nextval('public.reason_id_seq'::regclass);
 8   ALTER TABLE public.reason ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    231    230            �           2604    181785 	   status id    DEFAULT     f   ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);
 8   ALTER TABLE public.status ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    233    232            �           2604    181786    task id    DEFAULT     b   ALTER TABLE ONLY public.task ALTER COLUMN id SET DEFAULT nextval('public.task_id_seq'::regclass);
 6   ALTER TABLE public.task ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    235    234            �           2604    181787    taskattachment id    DEFAULT     v   ALTER TABLE ONLY public.taskattachment ALTER COLUMN id SET DEFAULT nextval('public.taskattachment_id_seq'::regclass);
 @   ALTER TABLE public.taskattachment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    237    236            �           2604    181788    taxtype taxtypeid    DEFAULT     v   ALTER TABLE ONLY public.taxtype ALTER COLUMN taxtypeid SET DEFAULT nextval('public.taxtype_taxtypeid_seq'::regclass);
 @   ALTER TABLE public.taxtype ALTER COLUMN taxtypeid DROP DEFAULT;
       public          postgres    false    239    238            �           2604    181789    visatype visaid    DEFAULT     r   ALTER TABLE ONLY public.visatype ALTER COLUMN visaid SET DEFAULT nextval('public.visatype_visaid_seq'::regclass);
 >   ALTER TABLE public.visatype ALTER COLUMN visaid DROP DEFAULT;
       public          postgres    false    241    240            �          0    181524    company 
   TABLE DATA           f   COPY public.company (id, address, code, contactemail, contactphone, countryid, name, zip) FROM stdin;
    public          postgres    false    209   ��       �          0    181530    companyphotos 
   TABLE DATA           @   COPY public.companyphotos (id, companyid, filename) FROM stdin;
    public          postgres    false    211   7�       �          0    181534    country 
   TABLE DATA           1   COPY public.country (id, code, name) FROM stdin;
    public          postgres    false    213   T�       �          0    181538    employee 
   TABLE DATA              COPY public.employee (id, isadmin, dob, companyid, countryid, email, firstname, lastname, showalltasks, isonbench) FROM stdin;
    public          postgres    false    215   {�       �          0    181544    employeephotos 
   TABLE DATA           B   COPY public.employeephotos (id, employeeid, filename) FROM stdin;
    public          postgres    false    217   ͼ       �          0    181548    externaltask 
   TABLE DATA           f  COPY public.externaltask (externalid, candidatecompany, companyaddress, hrname, hremail, hrphone, taskid, candidatename, hiringcompanyname, jobtitle, experiencerequired, joblocationid, taxtypeid, rate, recruitername, recruiteremail, jobsubmissionportalid, dateposted, joblink, visaid, jobportalid, jobreferencenumber, recruiterphone, portalname) FROM stdin;
    public          postgres    false    219   �       �          0    181554    internaltask 
   TABLE DATA           0  COPY public.internaltask (internalid, hiringcompanyname, jobtitle, experiencerequired, joblocationid, taxtypeid, rate, recruitername, recruiteremail, jobsubmissionportalid, dateposted, joblink, taskid, visaid, jobreferencenumber, jobportalid, recruiterphone, internalcandidateid, portalname) FROM stdin;
    public          postgres    false    221   �       �          0    181560    joblocation 
   TABLE DATA           z   COPY public.joblocation (locationid, locationtype, locationdescription, companyid, isforbench, isforsourcing) FROM stdin;
    public          postgres    false    223   $�       �          0    181566 	   jobportal 
   TABLE DATA           r   COPY public.jobportal (portalid, portalname, portaldescription, companyid, isforbench, isforsourcing) FROM stdin;
    public          postgres    false    225   ��       �          0    181572    password 
   TABLE DATA           A   COPY public.password (id, empid, password, username) FROM stdin;
    public          postgres    false    227   	�       �          0    181578 	   placement 
   TABLE DATA           U   COPY public.placement (placementid, placementtype, placementdescription) FROM stdin;
    public          postgres    false    229   :�       �          0    181583    reason 
   TABLE DATA           c   COPY public.reason (id, assignedto, chgdatetime, employeeid, reason, statusid, taskid) FROM stdin;
    public          postgres    false    230   w�       �          0    181587    status 
   TABLE DATA           |   COPY public.status (id, actualenddate, actualstartdate, companyid, finalstatus, code, description, statusorder) FROM stdin;
    public          postgres    false    232   ��       �          0    181593    task 
   TABLE DATA           �   COPY public.task (id, companyid, havingchild, actualenddate, actualstartdate, assignedto, createdby, description, enddate, name, parent, startdate, status, placementid) FROM stdin;
    public          postgres    false    234   ��       �          0    181599    taskattachment 
   TABLE DATA           J   COPY public.taskattachment (id, attachedby, filename, taskid) FROM stdin;
    public          postgres    false    236   ξ       �          0    181603    taxtype 
   TABLE DATA           s   COPY public.taxtype (taxtypeid, taxtypename, taxtypedescription, companyid, isforbench, isforsourcing) FROM stdin;
    public          postgres    false    238   �       �          0    181609    visatype 
   TABLE DATA           k   COPY public.visatype (visaid, visatype, visadescription, companyid, isforbench, isforsourcing) FROM stdin;
    public          postgres    false    240   )�       �           0    0    company_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.company_id_seq', 2, true);
          public          postgres    false    210            �           0    0    companyphotos_id_seq    SEQUENCE SET     B   SELECT pg_catalog.setval('public.companyphotos_id_seq', 2, true);
          public          postgres    false    212            �           0    0    country_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.country_id_seq', 2, true);
          public          postgres    false    214            �           0    0    employee_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.employee_id_seq', 2, true);
          public          postgres    false    216            �           0    0    employeephotos_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.employeephotos_id_seq', 2, true);
          public          postgres    false    218            �           0    0    externaltask_externalid_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.externaltask_externalid_seq', 1, true);
          public          postgres    false    220            �           0    0    internaltask_internalid_seq    SEQUENCE SET     I   SELECT pg_catalog.setval('public.internaltask_internalid_seq', 1, true);
          public          postgres    false    222            �           0    0    joblocation_locationid_seq    SEQUENCE SET     H   SELECT pg_catalog.setval('public.joblocation_locationid_seq', 4, true);
          public          postgres    false    224            �           0    0    jobportal_portalid_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.jobportal_portalid_seq', 7, true);
          public          postgres    false    226            �           0    0    password_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.password_id_seq', 2, true);
          public          postgres    false    228            �           0    0    reason_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.reason_id_seq', 1, true);
          public          postgres    false    231            �           0    0    status_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.status_id_seq', 1, true);
          public          postgres    false    233            �           0    0    task_id_seq    SEQUENCE SET     9   SELECT pg_catalog.setval('public.task_id_seq', 1, true);
          public          postgres    false    235            �           0    0    taskattachment_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.taskattachment_id_seq', 1, true);
          public          postgres    false    237            �           0    0    taxtype_taxtypeid_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.taxtype_taxtypeid_seq', 4, true);
          public          postgres    false    239            �           0    0    visatype_visaid_seq    SEQUENCE SET     A   SELECT pg_catalog.setval('public.visatype_visaid_seq', 5, true);
          public          postgres    false    241            �           2606    181632    company Company_code_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_code_key" UNIQUE (code);
 D   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_code_key";
       public            postgres    false    209            �           2606    181634     company Company_contactemail_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_contactemail_key" UNIQUE (contactemail);
 L   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_contactemail_key";
       public            postgres    false    209            �           2606    181636    country Country_code_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.country
    ADD CONSTRAINT "Country_code_key" UNIQUE (code);
 D   ALTER TABLE ONLY public.country DROP CONSTRAINT "Country_code_key";
       public            postgres    false    213            �           2606    181638    employee Employee_email_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_email_key" UNIQUE (email);
 G   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_email_key";
       public            postgres    false    215            �           2606    181640    password Password_username_key 
   CONSTRAINT     _   ALTER TABLE ONLY public.password
    ADD CONSTRAINT "Password_username_key" UNIQUE (username);
 J   ALTER TABLE ONLY public.password DROP CONSTRAINT "Password_username_key";
       public            postgres    false    227            �           2606    181642     company company_contactphone_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_contactphone_key UNIQUE (contactphone);
 J   ALTER TABLE ONLY public.company DROP CONSTRAINT company_contactphone_key;
       public            postgres    false    209            �           2606    181644    company company_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.company DROP CONSTRAINT company_name_key;
       public            postgres    false    209            �           2606    181646    company company_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.company DROP CONSTRAINT company_pkey;
       public            postgres    false    209            �           2606    181648     companyphotos companyphotos_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.companyphotos
    ADD CONSTRAINT companyphotos_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.companyphotos DROP CONSTRAINT companyphotos_pkey;
       public            postgres    false    211            �           2606    181650    country country_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.country DROP CONSTRAINT country_name_key;
       public            postgres    false    213            �           2606    181652    country country_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_pkey PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.country DROP CONSTRAINT country_pkey;
       public            postgres    false    213            �           2606    181654    employee employee_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT employee_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.employee DROP CONSTRAINT employee_pkey;
       public            postgres    false    215            �           2606    181656 ,   employeephotos employeephotos_employeeid_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_employeeid_key UNIQUE (employeeid);
 V   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_employeeid_key;
       public            postgres    false    217            �           2606    181658 "   employeephotos employeephotos_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_pkey;
       public            postgres    false    217            �           2606    181660    externaltask externaltask_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.externaltask
    ADD CONSTRAINT externaltask_pkey PRIMARY KEY (externalid);
 H   ALTER TABLE ONLY public.externaltask DROP CONSTRAINT externaltask_pkey;
       public            postgres    false    219            �           2606    181662    internaltask internaltask_pkey 
   CONSTRAINT     d   ALTER TABLE ONLY public.internaltask
    ADD CONSTRAINT internaltask_pkey PRIMARY KEY (internalid);
 H   ALTER TABLE ONLY public.internaltask DROP CONSTRAINT internaltask_pkey;
       public            postgres    false    221            �           2606    181664    joblocation joblocation_pkey 
   CONSTRAINT     b   ALTER TABLE ONLY public.joblocation
    ADD CONSTRAINT joblocation_pkey PRIMARY KEY (locationid);
 F   ALTER TABLE ONLY public.joblocation DROP CONSTRAINT joblocation_pkey;
       public            postgres    false    223            �           2606    181666    jobportal jobportal_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.jobportal
    ADD CONSTRAINT jobportal_pkey PRIMARY KEY (portalid);
 B   ALTER TABLE ONLY public.jobportal DROP CONSTRAINT jobportal_pkey;
       public            postgres    false    225            �           2606    181668    taxtype jobtype_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.taxtype
    ADD CONSTRAINT jobtype_pkey PRIMARY KEY (taxtypeid);
 >   ALTER TABLE ONLY public.taxtype DROP CONSTRAINT jobtype_pkey;
       public            postgres    false    238            �           2606    181670    password password_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.password
    ADD CONSTRAINT password_pkey PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.password DROP CONSTRAINT password_pkey;
       public            postgres    false    227            �           2606    181672    placement placement_pkey 
   CONSTRAINT     _   ALTER TABLE ONLY public.placement
    ADD CONSTRAINT placement_pkey PRIMARY KEY (placementid);
 B   ALTER TABLE ONLY public.placement DROP CONSTRAINT placement_pkey;
       public            postgres    false    229            �           2606    181674    reason reason_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_pkey;
       public            postgres    false    230            �           2606    181676    status status_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.status
    ADD CONSTRAINT status_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.status DROP CONSTRAINT status_pkey;
       public            postgres    false    232            �           2606    181678    task task_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.task
    ADD CONSTRAINT task_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.task DROP CONSTRAINT task_pkey;
       public            postgres    false    234            �           2606    181680 "   taskattachment taskattachment_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_pkey;
       public            postgres    false    236            �           2606    181682    visatype visatype_pkey 
   CONSTRAINT     X   ALTER TABLE ONLY public.visatype
    ADD CONSTRAINT visatype_pkey PRIMARY KEY (visaid);
 @   ALTER TABLE ONLY public.visatype DROP CONSTRAINT visatype_pkey;
       public            postgres    false    240            �           2606    181683    company Company_countryid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_countryid_fkey" FOREIGN KEY (countryid) REFERENCES public.country(id) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_countryid_fkey";
       public          postgres    false    3277    209    213            �           2606    181688     employee Employee_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_companyid_fkey" FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_companyid_fkey";
       public          postgres    false    215    3269    209            �           2606    181693     employee Employee_countryid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_countryid_fkey" FOREIGN KEY (countryid) REFERENCES public.country(id) ON UPDATE CASCADE ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_countryid_fkey";
       public          postgres    false    215    213    3277            �           2606    181698    password Password_empid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.password
    ADD CONSTRAINT "Password_empid_fkey" FOREIGN KEY (empid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.password DROP CONSTRAINT "Password_empid_fkey";
       public          postgres    false    3281    227    215            �           2606    181703    task Task_assignedto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_assignedto_fkey" FOREIGN KEY (assignedto) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_assignedto_fkey";
       public          postgres    false    3281    234    215            �           2606    181708    task Task_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_companyid_fkey" FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_companyid_fkey";
       public          postgres    false    234    209    3269            �           2606    181713    task Task_createdby_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_createdby_fkey" FOREIGN KEY (createdby) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_createdby_fkey";
       public          postgres    false    234    3281    215            �           2606    181718    task Task_status_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_status_fkey" FOREIGN KEY (status) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_status_fkey";
       public          postgres    false    234    232    3303            �           2606    181723 *   companyphotos companyphotos_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.companyphotos
    ADD CONSTRAINT companyphotos_companyid_fkey FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.companyphotos DROP CONSTRAINT companyphotos_companyid_fkey;
       public          postgres    false    211    209    3269            �           2606    181728 -   employeephotos employeephotos_employeeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_employeeid_fkey;
       public          postgres    false    215    3281    217            �           2606    181733 %   externaltask externaltask_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.externaltask
    ADD CONSTRAINT externaltask_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;
 O   ALTER TABLE ONLY public.externaltask DROP CONSTRAINT externaltask_taskid_fkey;
       public          postgres    false    234    3305    219            �           2606    181738 %   internaltask internaltask_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.internaltask
    ADD CONSTRAINT internaltask_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE DEFERRABLE INITIALLY DEFERRED;
 O   ALTER TABLE ONLY public.internaltask DROP CONSTRAINT internaltask_taskid_fkey;
       public          postgres    false    221    234    3305            �           2606    181743    reason reason_assignedto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_assignedto_fkey FOREIGN KEY (assignedto) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_assignedto_fkey;
       public          postgres    false    215    3281    230            �           2606    181748    reason reason_employeeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_employeeid_fkey;
       public          postgres    false    230    3281    215            �           2606    181753    reason reason_statusid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_statusid_fkey FOREIGN KEY (statusid) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_statusid_fkey;
       public          postgres    false    3303    230    232            �           2606    181758    reason reason_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_taskid_fkey;
       public          postgres    false    230    234    3305                        2606    181763 -   taskattachment taskattachment_attachedby_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_attachedby_fkey FOREIGN KEY (attachedby) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_attachedby_fkey;
       public          postgres    false    215    3281    236                       2606    181768 )   taskattachment taskattachment_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE;
 S   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_taskid_fkey;
       public          postgres    false    234    3305    236            �   �   x��A
�0 �����Ro�� �փ ��%Yh�MBL��9��W̋�g&��s(��R��4�53�;n�>ӿx|lUU~璡;�ȭ>��7���V��Rk�
r'��7�P��	Z)����B�1:(^      �      x������ � �      �      x�3�4�tL�������� #      �   B   x�3�L�4202�54�5�P00�26�20�4�Ĕ��<��������\��҂�"�(PWW� �k      �      x������ � �      �      x������ � �      �      x������ � �      �   T   x�3�J��/I�,S
��Eٙy���%�%\F��IE�)�F
)���
�ii����9��t�3���&�$��U�P���� ��$5      �   q   x�3��K,�N,��̃��E%�9���%�%\Ɯ�y)��)��`
U҄�='��8%?��3�BUb�霟[��W� �qP��q�d�e��d�q�@��r��d�AI�`� �i<�      �   !   x�3�4�,H,.�,.-H-JL�������� `�      �   -   x�3�L�(I-�Ḱ3��R2SKR�93�0�l� ��>      �      x������ � �      �      x������ � �      �      x������ � �      �      x������ � �      �   .   x�3�7!K��.#NCKKI�s:9�1T F��� _	�      �   �   x�u��!E��+�4Y����F;���!0	 ����UZ�朓�����`�-�b�3J�Ѳ��a�41Ǚ4�j��Ô$�WN�ͧY!{]sIo><iC���E,�PSV���᧖�o�
���/�΅O� 7>     