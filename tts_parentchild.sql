PGDMP                      
    {            tts    14.8    14.8 e    m           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            n           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            o           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            p           1262    123893    tts    DATABASE     g   CREATE DATABASE tts WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE tts;
                postgres    false            �            1255    123894 �   employeepassword(integer, integer, character varying, character varying, date, character varying, character varying, character varying) 	   PROCEDURE     �  CREATE PROCEDURE public.employeepassword(IN p_countryid integer, IN p_companyid integer, IN p_firstname character varying, IN p_lastname character varying, IN p_dob date, IN p_email character varying, IN p_username character varying, IN p_password character varying)
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
       public          postgres    false            �            1259    123895    company    TABLE     b  CREATE TABLE public.company (
    id integer NOT NULL,
    code character varying(25) NOT NULL,
    name character varying(50) NOT NULL,
    contactemail character varying(50) NOT NULL,
    contactphone character varying(50) NOT NULL,
    address character varying(255) NOT NULL,
    zip character varying(10) NOT NULL,
    countryid integer NOT NULL
);
    DROP TABLE public.company;
       public         heap    postgres    false            �            1259    123898    company_id_seq    SEQUENCE     �   CREATE SEQUENCE public.company_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.company_id_seq;
       public          postgres    false    209            q           0    0    company_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.company_id_seq OWNED BY public.company.id;
          public          postgres    false    210            �            1259    123899    companyphotos    TABLE     �   CREATE TABLE public.companyphotos (
    id integer NOT NULL,
    companyid integer NOT NULL,
    filename character varying(50) NOT NULL
);
 !   DROP TABLE public.companyphotos;
       public         heap    postgres    false            �            1259    123902    companyphotos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.companyphotos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 +   DROP SEQUENCE public.companyphotos_id_seq;
       public          postgres    false    211            r           0    0    companyphotos_id_seq    SEQUENCE OWNED BY     M   ALTER SEQUENCE public.companyphotos_id_seq OWNED BY public.companyphotos.id;
          public          postgres    false    212            �            1259    123903    country    TABLE     }   CREATE TABLE public.country (
    id integer NOT NULL,
    code integer NOT NULL,
    name character varying(50) NOT NULL
);
    DROP TABLE public.country;
       public         heap    postgres    false            �            1259    123906    country_id_seq    SEQUENCE     �   CREATE SEQUENCE public.country_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 %   DROP SEQUENCE public.country_id_seq;
       public          postgres    false    213            s           0    0    country_id_seq    SEQUENCE OWNED BY     A   ALTER SEQUENCE public.country_id_seq OWNED BY public.country.id;
          public          postgres    false    214            �            1259    123907    employee    TABLE     B  CREATE TABLE public.employee (
    id integer NOT NULL,
    countryid integer NOT NULL,
    companyid integer NOT NULL,
    firstname character varying(50) NOT NULL,
    lastname character varying(50) NOT NULL,
    dob timestamp without time zone NOT NULL,
    email character varying(50) NOT NULL,
    isadmin boolean
);
    DROP TABLE public.employee;
       public         heap    postgres    false            �            1259    123910    employee_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employee_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.employee_id_seq;
       public          postgres    false    215            t           0    0    employee_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.employee_id_seq OWNED BY public.employee.id;
          public          postgres    false    216            �            1259    123911    employeephotos    TABLE     �   CREATE TABLE public.employeephotos (
    id integer NOT NULL,
    employeeid integer NOT NULL,
    filename character varying(50) NOT NULL
);
 "   DROP TABLE public.employeephotos;
       public         heap    postgres    false            �            1259    123914    employeephotos_id_seq    SEQUENCE     �   CREATE SEQUENCE public.employeephotos_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.employeephotos_id_seq;
       public          postgres    false    217            u           0    0    employeephotos_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.employeephotos_id_seq OWNED BY public.employeephotos.id;
          public          postgres    false    218            �            1259    123915    password    TABLE     �   CREATE TABLE public.password (
    id integer NOT NULL,
    empid integer NOT NULL,
    username character varying(50) NOT NULL,
    password character varying(50) NOT NULL
);
    DROP TABLE public.password;
       public         heap    postgres    false            �            1259    123918    password_id_seq    SEQUENCE     �   CREATE SEQUENCE public.password_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 &   DROP SEQUENCE public.password_id_seq;
       public          postgres    false    219            v           0    0    password_id_seq    SEQUENCE OWNED BY     C   ALTER SEQUENCE public.password_id_seq OWNED BY public.password.id;
          public          postgres    false    220            �            1259    123919    reason    TABLE       CREATE TABLE public.reason (
    id integer NOT NULL,
    taskid integer NOT NULL,
    employeeid integer NOT NULL,
    chgdatetime timestamp with time zone NOT NULL,
    reason character varying(250) NOT NULL,
    statusid integer NOT NULL,
    assignedto integer NOT NULL
);
    DROP TABLE public.reason;
       public         heap    postgres    false            �            1259    123922    reason_id_seq    SEQUENCE     �   CREATE SEQUENCE public.reason_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.reason_id_seq;
       public          postgres    false    221            w           0    0    reason_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.reason_id_seq OWNED BY public.reason.id;
          public          postgres    false    222            �            1259    123923    status    TABLE     �   CREATE TABLE public.status (
    id integer NOT NULL,
    code character varying(25) NOT NULL,
    description character varying(255) NOT NULL,
    statusorder integer NOT NULL
);
    DROP TABLE public.status;
       public         heap    postgres    false            �            1259    123926    status_id_seq    SEQUENCE     �   CREATE SEQUENCE public.status_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.status_id_seq;
       public          postgres    false    223            x           0    0    status_id_seq    SEQUENCE OWNED BY     ?   ALTER SEQUENCE public.status_id_seq OWNED BY public.status.id;
          public          postgres    false    224            �            1259    123927    task    TABLE     �  CREATE TABLE public.task (
    id integer NOT NULL,
    name character varying(100) NOT NULL,
    description character varying(255) NOT NULL,
    createdby integer NOT NULL,
    assignedto integer NOT NULL,
    status integer NOT NULL,
    startdate timestamp with time zone NOT NULL,
    enddate timestamp with time zone NOT NULL,
    actualstartdate timestamp with time zone,
    actualenddate timestamp with time zone,
    parent integer,
    companyid integer NOT NULL,
    havingchild boolean
);
    DROP TABLE public.task;
       public         heap    postgres    false            �            1259    123930    task_id_seq    SEQUENCE     �   CREATE SEQUENCE public.task_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.task_id_seq;
       public          postgres    false    225            y           0    0    task_id_seq    SEQUENCE OWNED BY     ;   ALTER SEQUENCE public.task_id_seq OWNED BY public.task.id;
          public          postgres    false    226            �            1259    123931    taskattachment    TABLE     �   CREATE TABLE public.taskattachment (
    id integer NOT NULL,
    taskid integer NOT NULL,
    attachedby integer NOT NULL,
    filename character varying(250) NOT NULL
);
 "   DROP TABLE public.taskattachment;
       public         heap    postgres    false            �            1259    123934    taskattachment_id_seq    SEQUENCE     �   CREATE SEQUENCE public.taskattachment_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 ,   DROP SEQUENCE public.taskattachment_id_seq;
       public          postgres    false    227            z           0    0    taskattachment_id_seq    SEQUENCE OWNED BY     O   ALTER SEQUENCE public.taskattachment_id_seq OWNED BY public.taskattachment.id;
          public          postgres    false    228            �           2604    123935 
   company id    DEFAULT     h   ALTER TABLE ONLY public.company ALTER COLUMN id SET DEFAULT nextval('public.company_id_seq'::regclass);
 9   ALTER TABLE public.company ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    210    209            �           2604    123936    companyphotos id    DEFAULT     t   ALTER TABLE ONLY public.companyphotos ALTER COLUMN id SET DEFAULT nextval('public.companyphotos_id_seq'::regclass);
 ?   ALTER TABLE public.companyphotos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    212    211            �           2604    123937 
   country id    DEFAULT     h   ALTER TABLE ONLY public.country ALTER COLUMN id SET DEFAULT nextval('public.country_id_seq'::regclass);
 9   ALTER TABLE public.country ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    214    213            �           2604    123938    employee id    DEFAULT     j   ALTER TABLE ONLY public.employee ALTER COLUMN id SET DEFAULT nextval('public.employee_id_seq'::regclass);
 :   ALTER TABLE public.employee ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    216    215            �           2604    123939    employeephotos id    DEFAULT     v   ALTER TABLE ONLY public.employeephotos ALTER COLUMN id SET DEFAULT nextval('public.employeephotos_id_seq'::regclass);
 @   ALTER TABLE public.employeephotos ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    218    217            �           2604    123940    password id    DEFAULT     j   ALTER TABLE ONLY public.password ALTER COLUMN id SET DEFAULT nextval('public.password_id_seq'::regclass);
 :   ALTER TABLE public.password ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    220    219            �           2604    123941 	   reason id    DEFAULT     f   ALTER TABLE ONLY public.reason ALTER COLUMN id SET DEFAULT nextval('public.reason_id_seq'::regclass);
 8   ALTER TABLE public.reason ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    222    221            �           2604    123942 	   status id    DEFAULT     f   ALTER TABLE ONLY public.status ALTER COLUMN id SET DEFAULT nextval('public.status_id_seq'::regclass);
 8   ALTER TABLE public.status ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    224    223            �           2604    123943    task id    DEFAULT     b   ALTER TABLE ONLY public.task ALTER COLUMN id SET DEFAULT nextval('public.task_id_seq'::regclass);
 6   ALTER TABLE public.task ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    226    225            �           2604    123944    taskattachment id    DEFAULT     v   ALTER TABLE ONLY public.taskattachment ALTER COLUMN id SET DEFAULT nextval('public.taskattachment_id_seq'::regclass);
 @   ALTER TABLE public.taskattachment ALTER COLUMN id DROP DEFAULT;
       public          postgres    false    228    227            W          0    123895    company 
   TABLE DATA           f   COPY public.company (id, code, name, contactemail, contactphone, address, zip, countryid) FROM stdin;
    public          postgres    false    209   8~       Y          0    123899    companyphotos 
   TABLE DATA           @   COPY public.companyphotos (id, companyid, filename) FROM stdin;
    public          postgres    false    211   @       [          0    123903    country 
   TABLE DATA           1   COPY public.country (id, code, name) FROM stdin;
    public          postgres    false    213   �       ]          0    123907    employee 
   TABLE DATA           f   COPY public.employee (id, countryid, companyid, firstname, lastname, dob, email, isadmin) FROM stdin;
    public          postgres    false    215   �       _          0    123911    employeephotos 
   TABLE DATA           B   COPY public.employeephotos (id, employeeid, filename) FROM stdin;
    public          postgres    false    217   2�       a          0    123915    password 
   TABLE DATA           A   COPY public.password (id, empid, username, password) FROM stdin;
    public          postgres    false    219   �       c          0    123919    reason 
   TABLE DATA           c   COPY public.reason (id, taskid, employeeid, chgdatetime, reason, statusid, assignedto) FROM stdin;
    public          postgres    false    221   ��       e          0    123923    status 
   TABLE DATA           D   COPY public.status (id, code, description, statusorder) FROM stdin;
    public          postgres    false    223   ߅       g          0    123927    task 
   TABLE DATA           �   COPY public.task (id, name, description, createdby, assignedto, status, startdate, enddate, actualstartdate, actualenddate, parent, companyid, havingchild) FROM stdin;
    public          postgres    false    225   G�       i          0    123931    taskattachment 
   TABLE DATA           J   COPY public.taskattachment (id, taskid, attachedby, filename) FROM stdin;
    public          postgres    false    227   ��       {           0    0    company_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.company_id_seq', 84, true);
          public          postgres    false    210            |           0    0    companyphotos_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.companyphotos_id_seq', 58, true);
          public          postgres    false    212            }           0    0    country_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.country_id_seq', 85, true);
          public          postgres    false    214            ~           0    0    employee_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.employee_id_seq', 303, true);
          public          postgres    false    216                       0    0    employeephotos_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.employeephotos_id_seq', 82, true);
          public          postgres    false    218            �           0    0    password_id_seq    SEQUENCE SET     ?   SELECT pg_catalog.setval('public.password_id_seq', 137, true);
          public          postgres    false    220            �           0    0    reason_id_seq    SEQUENCE SET     =   SELECT pg_catalog.setval('public.reason_id_seq', 358, true);
          public          postgres    false    222            �           0    0    status_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.status_id_seq', 18, true);
          public          postgres    false    224            �           0    0    task_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.task_id_seq', 185, true);
          public          postgres    false    226            �           0    0    taskattachment_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.taskattachment_id_seq', 43, true);
          public          postgres    false    228            �           2606    123946    company Company_code_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_code_key" UNIQUE (code);
 D   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_code_key";
       public            postgres    false    209            �           2606    123948     company Company_contactemail_key 
   CONSTRAINT     e   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_contactemail_key" UNIQUE (contactemail);
 L   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_contactemail_key";
       public            postgres    false    209            �           2606    123950    company Company_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_pkey";
       public            postgres    false    209            �           2606    123952    country Country_code_key 
   CONSTRAINT     U   ALTER TABLE ONLY public.country
    ADD CONSTRAINT "Country_code_key" UNIQUE (code);
 D   ALTER TABLE ONLY public.country DROP CONSTRAINT "Country_code_key";
       public            postgres    false    213            �           2606    123954    country Country_pkey 
   CONSTRAINT     T   ALTER TABLE ONLY public.country
    ADD CONSTRAINT "Country_pkey" PRIMARY KEY (id);
 @   ALTER TABLE ONLY public.country DROP CONSTRAINT "Country_pkey";
       public            postgres    false    213            �           2606    123956    employee Employee_email_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_email_key" UNIQUE (email);
 G   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_email_key";
       public            postgres    false    215            �           2606    123958    employee Employee_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_pkey";
       public            postgres    false    215            �           2606    123960    password Password_pkey 
   CONSTRAINT     V   ALTER TABLE ONLY public.password
    ADD CONSTRAINT "Password_pkey" PRIMARY KEY (id);
 B   ALTER TABLE ONLY public.password DROP CONSTRAINT "Password_pkey";
       public            postgres    false    219            �           2606    123962    password Password_username_key 
   CONSTRAINT     _   ALTER TABLE ONLY public.password
    ADD CONSTRAINT "Password_username_key" UNIQUE (username);
 J   ALTER TABLE ONLY public.password DROP CONSTRAINT "Password_username_key";
       public            postgres    false    219            �           2606    123964    status Status_code_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.status
    ADD CONSTRAINT "Status_code_key" UNIQUE (code);
 B   ALTER TABLE ONLY public.status DROP CONSTRAINT "Status_code_key";
       public            postgres    false    223            �           2606    123966    status Status_pkey 
   CONSTRAINT     R   ALTER TABLE ONLY public.status
    ADD CONSTRAINT "Status_pkey" PRIMARY KEY (id);
 >   ALTER TABLE ONLY public.status DROP CONSTRAINT "Status_pkey";
       public            postgres    false    223            �           2606    123968    task Task_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_pkey" PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_pkey";
       public            postgres    false    225            �           2606    123970     company company_contactphone_key 
   CONSTRAINT     c   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_contactphone_key UNIQUE (contactphone);
 J   ALTER TABLE ONLY public.company DROP CONSTRAINT company_contactphone_key;
       public            postgres    false    209            �           2606    123972    company company_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.company
    ADD CONSTRAINT company_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.company DROP CONSTRAINT company_name_key;
       public            postgres    false    209            �           2606    123974     companyphotos companyphotos_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.companyphotos
    ADD CONSTRAINT companyphotos_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.companyphotos DROP CONSTRAINT companyphotos_pkey;
       public            postgres    false    211            �           2606    123976    country country_name_key 
   CONSTRAINT     S   ALTER TABLE ONLY public.country
    ADD CONSTRAINT country_name_key UNIQUE (name);
 B   ALTER TABLE ONLY public.country DROP CONSTRAINT country_name_key;
       public            postgres    false    213            �           2606    123978 ,   employeephotos employeephotos_employeeid_key 
   CONSTRAINT     m   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_employeeid_key UNIQUE (employeeid);
 V   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_employeeid_key;
       public            postgres    false    217            �           2606    123980 "   employeephotos employeephotos_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_pkey;
       public            postgres    false    217            �           2606    123982    reason reason_pkey 
   CONSTRAINT     P   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_pkey PRIMARY KEY (id);
 <   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_pkey;
       public            postgres    false    221            �           2606    123984 "   taskattachment taskattachment_pkey 
   CONSTRAINT     `   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_pkey PRIMARY KEY (id);
 L   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_pkey;
       public            postgres    false    227            �           2606    123985    company Company_countryid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.company
    ADD CONSTRAINT "Company_countryid_fkey" FOREIGN KEY (countryid) REFERENCES public.country(id) ON UPDATE CASCADE ON DELETE CASCADE;
 J   ALTER TABLE ONLY public.company DROP CONSTRAINT "Company_countryid_fkey";
       public          postgres    false    3235    213    209            �           2606    123990     employee Employee_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_companyid_fkey" FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_companyid_fkey";
       public          postgres    false    209    3225    215            �           2606    123995     employee Employee_countryid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employee
    ADD CONSTRAINT "Employee_countryid_fkey" FOREIGN KEY (countryid) REFERENCES public.country(id) ON UPDATE CASCADE ON DELETE CASCADE;
 L   ALTER TABLE ONLY public.employee DROP CONSTRAINT "Employee_countryid_fkey";
       public          postgres    false    215    3235    213            �           2606    124000    password Password_empid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.password
    ADD CONSTRAINT "Password_empid_fkey" FOREIGN KEY (empid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 H   ALTER TABLE ONLY public.password DROP CONSTRAINT "Password_empid_fkey";
       public          postgres    false    215    3241    219            �           2606    124005    task Task_assignedto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_assignedto_fkey" FOREIGN KEY (assignedto) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_assignedto_fkey";
       public          postgres    false    215    3241    225            �           2606    124010    task Task_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_companyid_fkey" FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_companyid_fkey";
       public          postgres    false    225    3225    209            �           2606    124015    task Task_createdby_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_createdby_fkey" FOREIGN KEY (createdby) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 D   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_createdby_fkey";
       public          postgres    false    225    215    3241            �           2606    124020    task Task_status_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.task
    ADD CONSTRAINT "Task_status_fkey" FOREIGN KEY (status) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE;
 A   ALTER TABLE ONLY public.task DROP CONSTRAINT "Task_status_fkey";
       public          postgres    false    225    223    3255            �           2606    124025 *   companyphotos companyphotos_companyid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.companyphotos
    ADD CONSTRAINT companyphotos_companyid_fkey FOREIGN KEY (companyid) REFERENCES public.company(id) ON UPDATE CASCADE ON DELETE CASCADE;
 T   ALTER TABLE ONLY public.companyphotos DROP CONSTRAINT companyphotos_companyid_fkey;
       public          postgres    false    211    3225    209            �           2606    124030 -   employeephotos employeephotos_employeeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.employeephotos
    ADD CONSTRAINT employeephotos_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.employeephotos DROP CONSTRAINT employeephotos_employeeid_fkey;
       public          postgres    false    3241    215    217            �           2606    124035    reason reason_assignedto_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_assignedto_fkey FOREIGN KEY (assignedto) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_assignedto_fkey;
       public          postgres    false    215    221    3241            �           2606    124040    reason reason_employeeid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_employeeid_fkey FOREIGN KEY (employeeid) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 G   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_employeeid_fkey;
       public          postgres    false    3241    221    215            �           2606    124045    reason reason_statusid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_statusid_fkey FOREIGN KEY (statusid) REFERENCES public.status(id) ON UPDATE CASCADE ON DELETE CASCADE;
 E   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_statusid_fkey;
       public          postgres    false    223    221    3255            �           2606    124050    reason reason_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.reason
    ADD CONSTRAINT reason_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE;
 C   ALTER TABLE ONLY public.reason DROP CONSTRAINT reason_taskid_fkey;
       public          postgres    false    225    3257    221            �           2606    124055 -   taskattachment taskattachment_attachedby_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_attachedby_fkey FOREIGN KEY (attachedby) REFERENCES public.employee(id) ON UPDATE CASCADE ON DELETE CASCADE;
 W   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_attachedby_fkey;
       public          postgres    false    215    3241    227            �           2606    124060 )   taskattachment taskattachment_taskid_fkey    FK CONSTRAINT     �   ALTER TABLE ONLY public.taskattachment
    ADD CONSTRAINT taskattachment_taskid_fkey FOREIGN KEY (taskid) REFERENCES public.task(id) ON UPDATE CASCADE ON DELETE CASCADE;
 S   ALTER TABLE ONLY public.taskattachment DROP CONSTRAINT taskattachment_taskid_fkey;
       public          postgres    false    227    3257    225            W   �   x�MMMk�0=��b�WY2I���u�CKw��-�^�I׀�Z�o�-E��yY�jV�τeo�m���7Iݔ]�z�S�Ox&�Q�g�u�b���1�.v6x�%���vh���O�O$Tu	U������?g�/W4�1��v�3��"�Y��w߼�+3���xG|8���n01>�*D,���^�J�v� �I�h/ �i�ťW��N�L0�r(�F
=.X���5˷��qA$x�?������.��_�ie      Y   Y   x�3�4�t��/MQ���+Qp�M-�LN�7�+�K�2��47�tI,I,�,�˙��%M�9-�9��s�-�!B�&`!�x�P� "`      [   !   x�3�4���K�L�0�4�v����� EK�      ]   H  x�}�]k�0������cNӫvW���nN��A�!*��|8Z-����s����΃.��J6�
A0��l������Z��M���k��OĲ̻�]���sg�d��I�ܻ����wo�»�t%z+�RU� )�Sv@�Oe�R2�(��t�NMP�9}`xAl�`w��!׭lzP��W��HpO;�Е�B�PW��	L�l�*(���Kȶ4��p�']��E�WX	��&�Y/X��:�8��"��	r��eD�"#�=u�+ݤ~�T.��#�/1��;#���������7l�W{[�w􂙗��a��	0��{/����(�� Q���      _   �   x�U�M�0�s�cF���D'xJt���Y:wؿ7Z<&O^���B7�!��wfTi�ǋ����-sP[z���:����%Bki�j5L��V�b�2�Ў�E���9�2ZƖÚ�I�t�c1g,�ӁzMߝXD,��s��T2U��鴨��S0lU������1�\�fk`̃��>/6lޜc��K	{-�Qn��!�:f_<      a   �   x�M�K
�0���0ş|�t��P��X�"���5��1�4�:�Y�J�bVƎ�>�2q�4�:���Y�!��1�^p Gi��1�X�_�C�'0-���ͨ��+Ίy���ݜX��~��8���E�}v�!g���� ��E      c   "  x���Oo�@��ɧ�u�3���nU�	�����9����u�&n�6�*����73
a��%��Re��L '���$%�󺨊|_f�w�殪���WE��5�þ�����R!�j���f���]����)�vW��T�d�z��V���ۂ?�1͘����`�=�6��q��9_�:�޹����6�3���e�DNc8���c|KKv����ƶ� �1�z�3f'0�@Z�OXq�i�7����@�.y���2sgEN����٭OC�����P:���f�Ř���P|���(&���5����=T4iF��8���rD�̀�����^5�������83��{�e��z�=��m��b�c��A#s��s����C�&^����#/�.�պ.�l�˶���T�t%ͱ�*�=��i�B��V7o��_}J�h��'7�nW�G�,�����7)�ch�c  ��X3'��y��M����N��Xy�As.�����l��� ��5c�:T� V�¼��S��Z;��"Ϋ���cv�ۑ��%����u�/�Ǫ�ݑ��Z�������x8_��$a''$ �"��ˎhnT� �Κ�j��&S��jv?y9%7I��K���h7M^l��&qb�x�/K٭(ʝ�ɣ�G֩i9��3g���㋽(���qşD} Ξ��H�i�qS'�w\HvxUmݫޖ�>Jݎ�-��*�v��j~�����q�Q��rW�q�w�}������@SW�iŐ�C����Â����b�\�wt&T      e   X   x�3���+(�O/J-.�I,�V���wr�4�2�t��K�H�Y�\���E��%�)Q�Ѐ˄�91/95'.�rr��qqq �p      g   �  x��WM��0=;��{�1�ߢڮ��j�+U�		!�&U [��׆8l�A!@{��O@�>�eE�V����xX�<X��l����ٷ!3��#@�h
x
Q������]}s��}��\�]M@�΂���!6oiTD$����q"c��]���
��X������lA�*���OU�wM0�yv���p⵰ �"qF���27FۗM�ت�b����sxf'�M�yrA�㪬�"��%��c#�E�9a���xW�, �(�D��1���OKw��EP��`���͓�����c���#�b\F#^2��2�aL�  `���e��۠X��г��@��Q�܆ţ�[�����z��h['W]}��a�Kp��ӊ�҆z0G�z��V@��%�S����%fo�k}�,�ąCN�%hc7�$�#�e���|͜w�����������C��8�0v�P��zC����9��w{}�=͂��ٓ7ţ��j� B�yk3��}�i��NK�]ڸ˗��k����m�z�ӮZ^����]��t;�kA��!�Y*�Z�f��ɪhΎ��T��a�� ��qq����x��L�j�3��L���[�iTW�z�n6{�u&vw���)'����V�/��V�.$�B�y�P���jП��d���Q      i   �   x��ѻ�0��9y��q�.,m���uD��ۃ���_�tf�d������;���gf��\��%o�����,Ӳ/���Y�q���B�"�
*�W��R�RI��F����(r�~��y�����־ �X��     