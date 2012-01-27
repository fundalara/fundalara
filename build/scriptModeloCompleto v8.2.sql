CREATE TABLE public.funcionalidad (
                codigo_funconalidad INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                Parent_codigo_funconalidad INTEGER NOT NULL,
                CONSTRAINT funcionalidad_pk PRIMARY KEY (codigo_funconalidad)
);


CREATE TABLE public.grupo (
                codigo_grupo INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT grupo_pk PRIMARY KEY (codigo_grupo)
);


CREATE TABLE public.funcionalidad_grupo (
                codigo_funconalidad INTEGER NOT NULL,
                codigo_grupo INTEGER NOT NULL,
                CONSTRAINT funcionalidad_grupo_pk PRIMARY KEY (codigo_funconalidad, codigo_grupo)
);


CREATE SEQUENCE public.constante_codigo_constante_seq_1;

CREATE TABLE public.constante (
                codigo_constante INTEGER NOT NULL DEFAULT nextval('public.constante_codigo_constante_seq_1'),
                abreviatura VARCHAR NOT NULL,
                nombre VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT constante_pk PRIMARY KEY (codigo_constante)
);


ALTER SEQUENCE public.constante_codigo_constante_seq_1 OWNED BY public.constante.codigo_constante;

CREATE TABLE public.egreso (
                codigo_egreso INTEGER NOT NULL,
                numero_documento VARCHAR,
                fecha_pago DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT egreso_pk PRIMARY KEY (codigo_egreso)
);


CREATE SEQUENCE public.tipo_dato_codigo_tipo_dato_seq_1;

CREATE TABLE public.tipo_dato (
                codigo_tipo_dato INTEGER NOT NULL DEFAULT nextval('public.tipo_dato_codigo_tipo_dato_seq_1'),
                nombre VARCHAR NOT NULL,
                descripcion VARCHAR,
                estatus CHAR NOT NULL,
                tipo BOOLEAN NOT NULL,
                Parent_codigo_tipo_dato INTEGER,
                CONSTRAINT tipo_dato_pk PRIMARY KEY (codigo_tipo_dato)
);


ALTER SEQUENCE public.tipo_dato_codigo_tipo_dato_seq_1 OWNED BY public.tipo_dato.codigo_tipo_dato;

CREATE SEQUENCE public.dato_basico_codigo_dato_basico_seq_2_5;

CREATE TABLE public.dato_basico (
                codigo_dato_basico INTEGER NOT NULL DEFAULT nextval('public.dato_basico_codigo_dato_basico_seq_2_5'),
                codigo_tipo_dato INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                descripcion VARCHAR,
                estatus CHAR NOT NULL,
                Parent_codigo_dato_basico INTEGER,
                CONSTRAINT dato_basico_pk PRIMARY KEY (codigo_dato_basico)
);


ALTER SEQUENCE public.dato_basico_codigo_dato_basico_seq_2_5 OWNED BY public.dato_basico.codigo_dato_basico;

CREATE TABLE public.clasificacion_competencia (
                codigo_clasificacion_competencia INTEGER NOT NULL,
                tipo_competencia INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                descripcion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT clasificacion_competencia PRIMARY KEY (codigo_clasificacion_competencia)
);


CREATE TABLE public.condicion_competencia (
                codigo_condicion INTEGER NOT NULL,
                codigo_clasificacion_competencia INTEGER NOT NULL,
                CONSTRAINT condicion_competencia_pk PRIMARY KEY (codigo_condicion, codigo_clasificacion_competencia)
);


CREATE SEQUENCE public.tipo_ingreso_codigo_tipo_ingreso_seq_1;

CREATE TABLE public.tipo_ingreso (
                codigo_tipo_ingreso INTEGER NOT NULL DEFAULT nextval('public.tipo_ingreso_codigo_tipo_ingreso_seq_1'),
                codigo_periodicidad INTEGER,
                descripcion VARCHAR NOT NULL,
                monto DOUBLE PRECISION,
                estatus CHAR NOT NULL,
                frecuencia INTEGER,
                CONSTRAINT tipo_ingreso_pk PRIMARY KEY (codigo_tipo_ingreso)
);


ALTER SEQUENCE public.tipo_ingreso_codigo_tipo_ingreso_seq_1 OWNED BY public.tipo_ingreso.codigo_tipo_ingreso;

CREATE SEQUENCE public.personal_foraneo_codigo_personal_foraneo_seq;

CREATE TABLE public.personal_foraneo (
                codigo_personal_foraneo INTEGER NOT NULL DEFAULT nextval('public.personal_foraneo_codigo_personal_foraneo_seq'),
                codigo_tipo_personal_foraneo INTEGER NOT NULL,
                nombre VARCHAR,
                CONSTRAINT personal_foraneo_pk PRIMARY KEY (codigo_personal_foraneo)
);


ALTER SEQUENCE public.personal_foraneo_codigo_personal_foraneo_seq OWNED BY public.personal_foraneo.codigo_personal_foraneo;

CREATE SEQUENCE public.lapso_deportivo_codigo_lapso_deportivo_seq;

CREATE TABLE public.lapso_deportivo (
                codigo_lapso_deportivo INTEGER NOT NULL DEFAULT nextval('public.lapso_deportivo_codigo_lapso_deportivo_seq'),
                tipo_lapso INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT lapso_deportivo_pk PRIMARY KEY (codigo_lapso_deportivo)
);


ALTER SEQUENCE public.lapso_deportivo_codigo_lapso_deportivo_seq OWNED BY public.lapso_deportivo.codigo_lapso_deportivo;

CREATE SEQUENCE public.escala_medicion_codigo_escala_seq_1;

CREATE TABLE public.escala_medicion (
                codigo_escala INTEGER NOT NULL DEFAULT nextval('public.escala_medicion_codigo_escala_seq_1'),
                tipo_escala INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                descripcion VARCHAR NOT NULL,
                CONSTRAINT escala_medicion_pk PRIMARY KEY (codigo_escala)
);


ALTER SEQUENCE public.escala_medicion_codigo_escala_seq_1 OWNED BY public.escala_medicion.codigo_escala;

CREATE SEQUENCE public.valor_escala_codigo_valor_escala_seq;

CREATE TABLE public.valor_escala (
                codigo_valor_escala INTEGER NOT NULL DEFAULT nextval('public.valor_escala_codigo_valor_escala_seq'),
                codigo_escala INTEGER NOT NULL,
                valor VARCHAR NOT NULL,
                descripcion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT valor_escala_pk PRIMARY KEY (codigo_valor_escala)
);


ALTER SEQUENCE public.valor_escala_codigo_valor_escala_seq OWNED BY public.valor_escala.codigo_valor_escala;

CREATE TABLE public.egreso_forma_pago (
                codigo_egreso_forma_pago INTEGER NOT NULL,
                codigo_egreso INTEGER NOT NULL,
                codigo_forma_pago INTEGER,
                codigo_banco INTEGER,
                codigo_tarjeta INTEGER,
                monto DOUBLE PRECISION NOT NULL,
                estatus CHAR NOT NULL,
                numero_docuemnto_pago VARCHAR,
                CONSTRAINT egreso_forma_pago_pk PRIMARY KEY (codigo_egreso_forma_pago, codigo_egreso)
);


CREATE TABLE public.ingreso (
                numero_documento VARCHAR NOT NULL,
                fecha_pago DATE NOT NULL,
                estatus CHAR NOT NULL,
                codigo_tipo_documento INTEGER,
                CONSTRAINT ingreso_pk PRIMARY KEY (numero_documento)
);


CREATE TABLE public.ingreso_forma_pago (
                codigo_ingreso_forma_pago INTEGER NOT NULL,
                numero_documento VARCHAR,
                codigo_forma_pago INTEGER,
                codigo_tarjeta INTEGER,
                codigo_banco INTEGER,
                monto DOUBLE PRECISION NOT NULL,
                estatus CHAR NOT NULL,
                numero_documento_pago VARCHAR,
                CONSTRAINT ingreso_forma_pago_pk PRIMARY KEY (codigo_ingreso_forma_pago)
);


CREATE SEQUENCE public.horario_codigo_horario_seq;

CREATE TABLE public.horario (
                codigo_horario INTEGER NOT NULL DEFAULT nextval('public.horario_codigo_horario_seq'),
                dia INTEGER NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_fin TIME NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT horario_pk PRIMARY KEY (codigo_horario)
);


ALTER SEQUENCE public.horario_codigo_horario_seq OWNED BY public.horario.codigo_horario;

CREATE SEQUENCE public.recaudo_por_proceso_codigo_recaudo_por_proceso_seq_1;

CREATE TABLE public.recaudo_por_proceso (
                codigo_recaudo_por_proceso INTEGER NOT NULL DEFAULT nextval('public.recaudo_por_proceso_codigo_recaudo_por_proceso_seq_1'),
                codigo_importancia INTEGER NOT NULL,
                codigo_proceso INTEGER NOT NULL,
                codigo_documento INTEGER NOT NULL,
                cantidad INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT recaudo_por_proceso_pk PRIMARY KEY (codigo_recaudo_por_proceso)
);


ALTER SEQUENCE public.recaudo_por_proceso_codigo_recaudo_por_proceso_seq_1 OWNED BY public.recaudo_por_proceso.codigo_recaudo_por_proceso;

CREATE SEQUENCE public.documentos_entregados_codigo_documento_seq;

CREATE TABLE public.documento_entregado (
                codigo_documento_entregado INTEGER NOT NULL DEFAULT nextval('public.documentos_entregados_codigo_documento_seq'),
                codigo_recaudo_por_proceso INTEGER NOT NULL,
                documento BYTEA,
                cantidad INTEGER,
                fecha DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_entregado_pk PRIMARY KEY (codigo_documento_entregado)
);


ALTER SEQUENCE public.documentos_entregados_codigo_documento_seq OWNED BY public.documento_entregado.codigo_documento_entregado;

CREATE SEQUENCE public.talla_por_indumentaria_codigo_talla_indumentaria_seq1;

CREATE TABLE public.talla_por_indumentaria (
                codigo_talla_indumentaria INTEGER NOT NULL DEFAULT nextval('public.talla_por_indumentaria_codigo_talla_indumentaria_seq1'),
                codigo_tipo_uniforme INTEGER NOT NULL,
                codigo_talla INTEGER NOT NULL,
                precio REAL NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT talla_por_indumentaria_pk PRIMARY KEY (codigo_talla_indumentaria)
);


ALTER SEQUENCE public.talla_por_indumentaria_codigo_talla_indumentaria_seq1 OWNED BY public.talla_por_indumentaria.codigo_talla_indumentaria;

CREATE TABLE public.persona (
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_persona INTEGER NOT NULL,
                codigo_parroquia INTEGER,
                telefono_habitacion VARCHAR,
                fecha_ingreso DATE NOT NULL,
                correo_electronico VARCHAR,
                twitter VARCHAR,
                direccion VARCHAR,
                fecha_egreso DATE,
                estatus CHAR NOT NULL,
                CONSTRAINT persona_pk PRIMARY KEY (cedula_rif)
);


CREATE SEQUENCE public.documento_acreedor_codigo_documento_acreedor_seq_3_1;

CREATE TABLE public.documento_acreedor (
                codigo_documento_acreedor INTEGER NOT NULL DEFAULT nextval('public.documento_acreedor_codigo_documento_acreedor_seq_3_1'),
                codigo_tipo_ingreso INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                cedula_atleta VARCHAR,
                fecha_emision DATE NOT NULL,
                fecha_vencimiento DATE NOT NULL,
                monto DOUBLE PRECISION NOT NULL,
                concepto VARCHAR,
                estado CHAR NOT NULL,
                estatus CHAR NOT NULL,
                saldo DOUBLE PRECISION NOT NULL,
                CONSTRAINT documento_acreedor_pk PRIMARY KEY (codigo_documento_acreedor)
);


ALTER SEQUENCE public.documento_acreedor_codigo_documento_acreedor_seq_3_1 OWNED BY public.documento_acreedor.codigo_documento_acreedor;

CREATE TABLE public.ingreso_documento_acreedor (
                numero_documento VARCHAR NOT NULL,
                codigo_documento_acreedor INTEGER NOT NULL,
                monto_abonado DOUBLE PRECISION NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT ingreso_documento_acreedor_pk PRIMARY KEY (numero_documento, codigo_documento_acreedor)
);


CREATE TABLE public.documento_indumentaria (
                codigo_documento_acreedor INTEGER NOT NULL,
                codigo_talla_indumentaria INTEGER NOT NULL,
                monto DOUBLE PRECISION NOT NULL,
                cantidad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_indumentaria_pk PRIMARY KEY (codigo_documento_acreedor, codigo_talla_indumentaria)
);


CREATE TABLE public.cuenta_pagar (
                origen VARCHAR NOT NULL,
                codigo_tipo_documento INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                fecha_emision DATE NOT NULL,
                monto_total DOUBLE PRECISION NOT NULL,
                codigo_tipo_egreso INTEGER NOT NULL,
                fecha_vencimiento DATE NOT NULL,
                concepto VARCHAR,
                estado CHAR NOT NULL,
                estatus CHAR NOT NULL,
                Subtotal DOUBLE PRECISION,
                CONSTRAINT cuenta_pagar_pk PRIMARY KEY (origen)
);


CREATE SEQUENCE public.nota_entrega_codigo_nota_entrega_seq_1_1;

CREATE TABLE public.nota_entrega (
                codigo_nota_entrega INTEGER NOT NULL DEFAULT nextval('public.nota_entrega_codigo_nota_entrega_seq_1_1'),
                origen VARCHAR,
                codigo_documento_acreedor INTEGER,
                fecha_recepcion DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT nota_entrega_pk PRIMARY KEY (codigo_nota_entrega)
);


ALTER SEQUENCE public.nota_entrega_codigo_nota_entrega_seq_1_1 OWNED BY public.nota_entrega.codigo_nota_entrega;

CREATE TABLE public.egreso_cuenta_pagar (
                origen VARCHAR NOT NULL,
                codigo_egreso INTEGER NOT NULL,
                monto_abonado DOUBLE PRECISION NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT egreso_cuenta_pagar_pk PRIMARY KEY (origen, codigo_egreso)
);


CREATE TABLE public.persona_natural (
                cedula_rif VARCHAR NOT NULL,
                codigo_genero INTEGER,
                celular VARCHAR,
                primer_nombre VARCHAR NOT NULL,
                segundo_nombre VARCHAR,
                primer_apellido VARCHAR NOT NULL,
                segundo_apellido VARCHAR,
                fecha_nacimiento DATE,
                foto BYTEA,
                estatus CHAR NOT NULL,
                CONSTRAINT persona_natural_pk PRIMARY KEY (cedula_rif)
);


CREATE TABLE public.personal (
                cedula_rif VARCHAR NOT NULL,
                codigo_estado_civil INTEGER NOT NULL,
                cantidad_hijos INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                tipo_sangre VARCHAR,
                CONSTRAINT personal_pk PRIMARY KEY (cedula_rif)
);


CREATE SEQUENCE public.personal_contrato_codigo_personal_contrato_seq;

CREATE TABLE public.personal_contrato (
                codigo_personal_contrato INTEGER NOT NULL DEFAULT nextval('public.personal_contrato_codigo_personal_contrato_seq'),
                cedula_rif VARCHAR NOT NULL,
                codigo_modalidad INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE,
                codigo_horario INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_contrato_pk PRIMARY KEY (codigo_personal_contrato)
);


ALTER SEQUENCE public.personal_contrato_codigo_personal_contrato_seq OWNED BY public.personal_contrato.codigo_personal_contrato;

CREATE TABLE public.usuario (
                cedula_rif VARCHAR NOT NULL,
                nick VARCHAR NOT NULL,
                password VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT usuario_pk PRIMARY KEY (cedula_rif)
);


CREATE TABLE public.perfil (
                codigo_perfil INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT perfil_pk PRIMARY KEY (codigo_perfil)
);


CREATE TABLE public.perfil_grupo (
                codigo_perfil INTEGER NOT NULL,
                codigo_grupo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT perfil_grupo_pk PRIMARY KEY (codigo_perfil, codigo_grupo)
);


CREATE SEQUENCE public.seguridad_funcional_codigo_seguridad_seq1;

CREATE TABLE public.seguridad_funcional (
                codigo_seguridad INTEGER NOT NULL DEFAULT nextval('public.seguridad_funcional_codigo_seguridad_seq1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_dato INTEGER NOT NULL,
                fecha_registro DATE NOT NULL,
                codigo_registro INTEGER NOT NULL,
                agregar BOOLEAN,
                modificar BOOLEAN,
                eliminar BOOLEAN,
                CONSTRAINT seguridad_funcional_pk PRIMARY KEY (codigo_seguridad)
);


ALTER SEQUENCE public.seguridad_funcional_codigo_seguridad_seq1 OWNED BY public.seguridad_funcional.codigo_seguridad;

CREATE TABLE public.detalle_seguridad_funcional (
                codigo_seguridad INTEGER NOT NULL,
                codigo_tipo_dato INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                fecha_registro DATE NOT NULL,
                codigo_registro VARCHAR NOT NULL,
                agregar BOOLEAN NOT NULL,
                modificar BOOLEAN NOT NULL,
                eliminar BOOLEAN NOT NULL,
                CONSTRAINT detalle_seguridad_funcional_pk PRIMARY KEY (codigo_seguridad, codigo_tipo_dato)
);


CREATE SEQUENCE public.requisicion_codigo_requisicion_seq;

CREATE TABLE public.requisicion (
                codigo_requisicion INTEGER NOT NULL DEFAULT nextval('public.requisicion_codigo_requisicion_seq'),
                codigo_estado_requisicion INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                fecha_emision DATE NOT NULL,
                fecha_entrega DATE NOT NULL,
                motivo_requisicion VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT id PRIMARY KEY (codigo_requisicion)
);


ALTER SEQUENCE public.requisicion_codigo_requisicion_seq OWNED BY public.requisicion.codigo_requisicion;

CREATE TABLE public.persona_juridica (
                cedula_rif VARCHAR NOT NULL,
                fax VARCHAR,
                razon_social VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT persona_juridica_pk PRIMARY KEY (cedula_rif)
);


CREATE TABLE public.proveedor_banco (
                codigo_cuenta_banco VARCHAR NOT NULL,
                codigo_tipo_cuenta INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                titular VARCHAR,
                codigo_banco INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT proveedor_banco_pk PRIMARY KEY (codigo_cuenta_banco)
);


CREATE TABLE public.nomina (
                codigo_nomina VARCHAR NOT NULL,
                codigo_tipo_nomina INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE,
                estatus CHAR NOT NULL,
                estado CHAR NOT NULL,
                CONSTRAINT nomina_pk PRIMARY KEY (codigo_nomina)
);


CREATE SEQUENCE public.indicador_codigo_indicador_seq;

CREATE TABLE public.indicador (
                codigo_indicador INTEGER NOT NULL DEFAULT nextval('public.indicador_codigo_indicador_seq'),
                codigo_medicion INTEGER NOT NULL,
                codigo_modalidad INTEGER NOT NULL,
                codigo_tipo_indicador INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                abreviatura VARCHAR NOT NULL,
                formula VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT indicador_pk PRIMARY KEY (codigo_indicador)
);


ALTER SEQUENCE public.indicador_codigo_indicador_seq OWNED BY public.indicador.codigo_indicador;

CREATE SEQUENCE public.estadio_codigo_estadio_seq_1;

CREATE TABLE public.estadio (
                codigo_estadio INTEGER NOT NULL DEFAULT nextval('public.estadio_codigo_estadio_seq_1'),
                codigo_parroquia INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                direccion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT estadio_pk PRIMARY KEY (codigo_estadio)
);


ALTER SEQUENCE public.estadio_codigo_estadio_seq_1 OWNED BY public.estadio.codigo_estadio;

CREATE TABLE public.afeccion_personal (
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_afeccion INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT afeccion_personal_pk PRIMARY KEY (cedula_rif, codigo_tipo_afeccion)
);


CREATE TABLE public.medico (
                numero_colegio VARCHAR NOT NULL,
                codigo_especialidad INTEGER,
                matricula VARCHAR,
                cedula_medico VARCHAR,
                nombre VARCHAR NOT NULL,
                apellido VARCHAR NOT NULL,
                telefono_oficina VARCHAR,
                telefono_celular VARCHAR,
                fecha_ingreso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT medico_pk PRIMARY KEY (numero_colegio)
);


CREATE SEQUENCE public.institucion_codigo_institucion_seq_1_1;

CREATE TABLE public.institucion (
                codigo_institucion INTEGER NOT NULL DEFAULT nextval('public.institucion_codigo_institucion_seq_1_1'),
                codigo_parroquia INTEGER,
                codigo_tipo_institucion INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                direccion VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT institucion_pk PRIMARY KEY (codigo_institucion)
);


ALTER SEQUENCE public.institucion_codigo_institucion_seq_1_1 OWNED BY public.institucion.codigo_institucion;

CREATE SEQUENCE public.personal_tipo_nomina_codigo_personal_tipo_nomina_seq;

CREATE TABLE public.personal_tipo_nomina (
                codigo_personal_tipo_nomina INTEGER NOT NULL DEFAULT nextval('public.personal_tipo_nomina_codigo_personal_tipo_nomina_seq'),
                codigo_tipo_nomina INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_tipo_nomina_pk PRIMARY KEY (codigo_personal_tipo_nomina)
);


ALTER SEQUENCE public.personal_tipo_nomina_codigo_personal_tipo_nomina_seq OWNED BY public.personal_tipo_nomina.codigo_personal_tipo_nomina;

CREATE SEQUENCE public.personal_cargo_codigo_personal_cargo_seq;

CREATE TABLE public.personal_cargo (
                codigo_personal_cargo INTEGER NOT NULL DEFAULT nextval('public.personal_cargo_codigo_personal_cargo_seq'),
                codigo_cargo INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                fecha_fin DATE,
                fecha_inicio DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_cargo_pk PRIMARY KEY (codigo_personal_cargo)
);


ALTER SEQUENCE public.personal_cargo_codigo_personal_cargo_seq OWNED BY public.personal_cargo.codigo_personal_cargo;

CREATE SEQUENCE public.dato_academico_personal_codigo_dato_academico_seq;

CREATE TABLE public.dato_academico_personal (
                codigo_dato_academico INTEGER NOT NULL DEFAULT nextval('public.dato_academico_personal_codigo_dato_academico_seq'),
                cedula_rif VARCHAR NOT NULL,
                titulo VARCHAR NOT NULL,
                instituto VARCHAR NOT NULL,
                fecha_egreso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_academico_personal_pk PRIMARY KEY (codigo_dato_academico)
);


ALTER SEQUENCE public.dato_academico_personal_codigo_dato_academico_seq OWNED BY public.dato_academico_personal.codigo_dato_academico;

CREATE SEQUENCE public.concepto_nomina_codigo_concepto_nomina_seq;

CREATE TABLE public.concepto_nomina (
                codigo_concepto_nomina INTEGER NOT NULL DEFAULT nextval('public.concepto_nomina_codigo_concepto_nomina_seq'),
                aplicable_sueldo BOOLEAN NOT NULL,
                fecha_creacion DATE NOT NULL,
                estatus CHAR NOT NULL,
                descripcion VARCHAR NOT NULL,
                codigo_tipo_concepto INTEGER NOT NULL,
                fijo BOOLEAN NOT NULL,
                CONSTRAINT concepto_nomina_pk PRIMARY KEY (codigo_concepto_nomina)
);


ALTER SEQUENCE public.concepto_nomina_codigo_concepto_nomina_seq OWNED BY public.concepto_nomina.codigo_concepto_nomina;

CREATE SEQUENCE public.movimiento_codigo_movimiento_seq;

CREATE TABLE public.movimiento (
                codigo_movimiento INTEGER NOT NULL DEFAULT nextval('public.movimiento_codigo_movimiento_seq'),
                codigo_nomina VARCHAR NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                codigo_concepto_nomina INTEGER NOT NULL,
                monto DOUBLE PRECISION NOT NULL,
                fecha DATE NOT NULL,
                justificacion_inasistencia BYTEA,
                estatus CHAR NOT NULL,
                cantidad_hora_dia INTEGER,
                observacion VARCHAR,
                CONSTRAINT movimiento_pk PRIMARY KEY (codigo_movimiento)
);


ALTER SEQUENCE public.movimiento_codigo_movimiento_seq OWNED BY public.movimiento.codigo_movimiento;

CREATE SEQUENCE public.personal_concepto_nomina_codigo_personal_concepto_nomina_seq;

CREATE TABLE public.personal_concepto_nomina (
                codigo_personal_concepto_nomina INTEGER NOT NULL DEFAULT nextval('public.personal_concepto_nomina_codigo_personal_concepto_nomina_seq'),
                cedula_rif VARCHAR NOT NULL,
                codigo_concepto_nomina INTEGER NOT NULL,
                monto REAL NOT NULL,
                fecha_fin DATE,
                estatus CHAR NOT NULL,
                fecha_activacion DATE NOT NULL,
                CONSTRAINT personal_concepto_nomina_pk PRIMARY KEY (codigo_personal_concepto_nomina)
);


ALTER SEQUENCE public.personal_concepto_nomina_codigo_personal_concepto_nomina_seq OWNED BY public.personal_concepto_nomina.codigo_personal_concepto_nomina;

CREATE SEQUENCE public.competencia_codigo_competencia_seq_1;

CREATE TABLE public.competencia (
                codigo_competencia INTEGER NOT NULL DEFAULT nextval('public.competencia_codigo_competencia_seq_1'),
                codigo_clasificacion_competencia INTEGER NOT NULL,
                codigo_lapso_deportivo INTEGER NOT NULL,
                codigo_estado INTEGER NOT NULL,
                codigo_organizacion INTEGER NOT NULL,
                codigo_estado_competencia INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE NOT NULL,
                cantidad_fase INTEGER NOT NULL,
                cantidad_jugador INTEGER NOT NULL,
                monto_inscripcion REAL NOT NULL,
                condiciones_generales VARCHAR,
                desempate VARCHAR,
                extrainning VARCHAR,
                documento BYTEA,
                estatus CHAR NOT NULL,
                CONSTRAINT competencia_pk PRIMARY KEY (codigo_competencia)
);


ALTER SEQUENCE public.competencia_codigo_competencia_seq_1 OWNED BY public.competencia.codigo_competencia;

CREATE SEQUENCE public.juego_codigo_juego_seq_1_1_1;

CREATE TABLE public.juego (
                codigo_juego INTEGER NOT NULL DEFAULT nextval('public.juego_codigo_juego_seq_1_1_1'),
                codigo_estadio INTEGER NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                codigo_estado INTEGER NOT NULL,
                hora_inicio TIME NOT NULL,
                fecha DATE NOT NULL,
                observaciones VARCHAR,
                cantidad_inning INTEGER NOT NULL,
                hora_fin TIME NOT NULL,
                CONSTRAINT juego_pk PRIMARY KEY (codigo_juego)
);


ALTER SEQUENCE public.juego_codigo_juego_seq_1_1_1 OWNED BY public.juego.codigo_juego;

CREATE TABLE public.personal_foraneo_juego (
                codigo_juego INTEGER NOT NULL,
                codigo_personal_foraneo INTEGER NOT NULL,
                codigo_tipo INTEGER,
                CONSTRAINT personal_foraneo_juego_pk PRIMARY KEY (codigo_juego, codigo_personal_foraneo)
);


CREATE TABLE public.fase_competencia (
                codigo_competencia INTEGER NOT NULL,
                numero_fase INTEGER NOT NULL,
                equipo_ingresan INTEGER NOT NULL,
                equipo_clasifican INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT fase_competencia_pk PRIMARY KEY (codigo_competencia, numero_fase)
);


CREATE SEQUENCE public.divisa_codigo_divisa_seq_2;

CREATE TABLE public.divisa (
                codigo_divisa INTEGER NOT NULL DEFAULT nextval('public.divisa_codigo_divisa_seq_2'),
                codigo_parroquia INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                direccion VARCHAR NOT NULL,
                telefono VARCHAR,
                correo_electronico VARCHAR,
                persona_contacto VARCHAR,
                logo BYTEA,
                estatus CHAR NOT NULL,
                CONSTRAINT divisa_pk PRIMARY KEY (codigo_divisa)
);


ALTER SEQUENCE public.divisa_codigo_divisa_seq_2 OWNED BY public.divisa.codigo_divisa;

CREATE SEQUENCE public.liga_codigo_liga_seq;

CREATE TABLE public.liga (
                codigo_liga INTEGER NOT NULL DEFAULT nextval('public.liga_codigo_liga_seq'),
                nombre VARCHAR NOT NULL,
                localidad VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT liga_pk PRIMARY KEY (codigo_liga)
);


ALTER SEQUENCE public.liga_codigo_liga_seq OWNED BY public.liga.codigo_liga;

CREATE TABLE public.liga_competencia (
                codigo_liga INTEGER NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                CONSTRAINT liga_competencia_pk PRIMARY KEY (codigo_liga, codigo_competencia)
);


CREATE SEQUENCE public.instalacion_codigo_instalacion_seq;

CREATE TABLE public.instalacion (
                codigo_instalacion INTEGER NOT NULL DEFAULT nextval('public.instalacion_codigo_instalacion_seq'),
                codigo_tipo_instalacion INTEGER NOT NULL,
                descripcion VARCHAR,
                capacidad INTEGER,
                estatus CHAR NOT NULL,
                tamano NUMERIC NOT NULL,
                ubicacion VARCHAR,
                CONSTRAINT instalacion_pk PRIMARY KEY (codigo_instalacion)
);


ALTER SEQUENCE public.instalacion_codigo_instalacion_seq OWNED BY public.instalacion.codigo_instalacion;

CREATE SEQUENCE public.almacen_codigo_almacen_seq_1;

CREATE TABLE public.almacen (
                codigo_almacen INTEGER NOT NULL DEFAULT nextval('public.almacen_codigo_almacen_seq_1'),
                codigo_instalacion INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                descripcion VARCHAR,
                capacidad NUMERIC NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT almacen_pk PRIMARY KEY (codigo_almacen)
);


ALTER SEQUENCE public.almacen_codigo_almacen_seq_1 OWNED BY public.almacen.codigo_almacen;

CREATE SEQUENCE public.material_codigo_material_seq;

CREATE TABLE public.material (
                codigo_material INTEGER NOT NULL DEFAULT nextval('public.material_codigo_material_seq'),
                codigo_almacen INTEGER NOT NULL,
                codigo_unidad_medida INTEGER,
                codigo_tipo_material INTEGER NOT NULL,
                descripcion VARCHAR NOT NULL,
                cantidad_deteriorada INTEGER,
                cantidad_existencia INTEGER NOT NULL,
                cantidad_presentacion INTEGER,
                cantidad_disponible INTEGER NOT NULL,
                reutilizable BOOLEAN NOT NULL,
                stock_minimo INTEGER,
                stock_maximo INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT material_pk PRIMARY KEY (codigo_material)
);


ALTER SEQUENCE public.material_codigo_material_seq OWNED BY public.material.codigo_material;

CREATE TABLE public.recepcion_material (
                codigo_material INTEGER NOT NULL,
                codigo_nota_entrega INTEGER NOT NULL,
                cantidad_recibida INTEGER NOT NULL,
                observaciones VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT recepcion_material_pk PRIMARY KEY (codigo_material, codigo_nota_entrega)
);


CREATE TABLE public.documento_acreedor_material (
                codigo_material INTEGER NOT NULL,
                codigo_documento_acreedor INTEGER NOT NULL,
                monto_estimado DOUBLE PRECISION,
                estatus CHAR NOT NULL,
                cantidad INTEGER NOT NULL,
                CONSTRAINT documento_acreedor_material_pk PRIMARY KEY (codigo_material, codigo_documento_acreedor)
);


CREATE TABLE public.cuenta_pagar_material (
                codigo_material INTEGER NOT NULL,
                origen VARCHAR NOT NULL,
                cantidad INTEGER NOT NULL,
                precio_unitario DOUBLE PRECISION NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT cuenta_pagar_material_pk PRIMARY KEY (codigo_material, origen)
);


CREATE TABLE public.detalle_requisicion (
                codigo_material INTEGER NOT NULL,
                codigo_requisicion INTEGER NOT NULL,
                cantidad_solicitada INTEGER NOT NULL,
                cantidad_entregada INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT detalle_requisicion_pk PRIMARY KEY (codigo_material, codigo_requisicion)
);


CREATE SEQUENCE public.categoria_codigo_categoria_seq_1;

CREATE TABLE public.categoria (
                codigo_categoria INTEGER NOT NULL DEFAULT nextval('public.categoria_codigo_categoria_seq_1'),
                nombre VARCHAR NOT NULL,
                edad_inferior INTEGER NOT NULL,
                edad_superior INTEGER NOT NULL,
                cantidad_equipo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT categoria_pk PRIMARY KEY (codigo_categoria)
);


ALTER SEQUENCE public.categoria_codigo_categoria_seq_1 OWNED BY public.categoria.codigo_categoria;

CREATE SEQUENCE public.constante_categoria_codigo_constante_categoria_seq;

CREATE TABLE public.constante_categoria (
                codigo_constante_categoria INTEGER NOT NULL DEFAULT nextval('public.constante_categoria_codigo_constante_categoria_seq'),
                codigo_constante INTEGER NOT NULL,
                codigo_categoria_1 INTEGER NOT NULL,
                valor INTEGER NOT NULL,
                CONSTRAINT constante_categoria_pk PRIMARY KEY (codigo_constante_categoria)
);


ALTER SEQUENCE public.constante_categoria_codigo_constante_categoria_seq OWNED BY public.constante_categoria.codigo_constante_categoria;

CREATE SEQUENCE public.indicador_categoria_competencia_codigo_indicador_categoria_c81;

CREATE TABLE public.indicador_categoria_competencia (
                codigo_indicador_categoria_competencia INTEGER NOT NULL DEFAULT nextval('public.indicador_categoria_competencia_codigo_indicador_categoria_c81'),
                codigo_categoria INTEGER NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                codigo_indicador INTEGER NOT NULL,
                CONSTRAINT indicador_categoria_competencia_pk PRIMARY KEY (codigo_indicador_categoria_competencia)
);


ALTER SEQUENCE public.indicador_categoria_competencia_codigo_indicador_categoria_c81 OWNED BY public.indicador_categoria_competencia.codigo_indicador_categoria_competencia;

CREATE SEQUENCE public.plan_temporada_codigo_plan_temporada_seq;

CREATE TABLE public.plan_temporada (
                codigo_plan_temporada INTEGER NOT NULL DEFAULT nextval('public.plan_temporada_codigo_plan_temporada_seq'),
                codigo_lapso_deportivo INTEGER NOT NULL,
                codigo_categoria INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT plan_temporada_pk PRIMARY KEY (codigo_plan_temporada)
);


ALTER SEQUENCE public.plan_temporada_codigo_plan_temporada_seq OWNED BY public.plan_temporada.codigo_plan_temporada;

CREATE SEQUENCE public.plan_entrenamiento_codigo_plan_entrenamiento_seq;

CREATE TABLE public.plan_entrenamiento (
                codigo_plan_entrenamiento INTEGER NOT NULL DEFAULT nextval('public.plan_entrenamiento_codigo_plan_entrenamiento_seq'),
                codigo_plan_temporada INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT plan_entrenamiento_pk PRIMARY KEY (codigo_plan_entrenamiento)
);


ALTER SEQUENCE public.plan_entrenamiento_codigo_plan_entrenamiento_seq OWNED BY public.plan_entrenamiento.codigo_plan_entrenamiento;

CREATE SEQUENCE public.actividad_entrenamiento_cod_actividad_entrenamiento_seq;

CREATE TABLE public.actividad_entrenamiento (
                cod_actividad_entrenamiento INTEGER NOT NULL DEFAULT nextval('public.actividad_entrenamiento_cod_actividad_entrenamiento_seq'),
                fase INTEGER NOT NULL,
                codigo_categoria INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT actividad_entrenamiento_pk PRIMARY KEY (cod_actividad_entrenamiento)
);


ALTER SEQUENCE public.actividad_entrenamiento_cod_actividad_entrenamiento_seq OWNED BY public.actividad_entrenamiento.cod_actividad_entrenamiento;

CREATE SEQUENCE public.indicador_actividad_escala_codigo_indicador_actividad_escala21;

CREATE TABLE public.indicador_actividad_escala (
                codigo_indicador_actividad_escala INTEGER NOT NULL DEFAULT nextval('public.indicador_actividad_escala_codigo_indicador_actividad_escala21'),
                indicador INTEGER NOT NULL,
                codigo_escala INTEGER NOT NULL,
                cod_actividad_entrenamiento INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT indicador_actividad_escala_pk PRIMARY KEY (codigo_indicador_actividad_escala)
);


ALTER SEQUENCE public.indicador_actividad_escala_codigo_indicador_actividad_escala21 OWNED BY public.indicador_actividad_escala.codigo_indicador_actividad_escala;

CREATE TABLE public.categoria_competencia (
                codigo_competencia INTEGER NOT NULL,
                codigo_categoria INTEGER NOT NULL,
                duracion_hora TIME,
                duracion_inning INTEGER,
                CONSTRAINT categoria_competencia_pk PRIMARY KEY (codigo_competencia, codigo_categoria)
);


CREATE TABLE public.categoria_liga (
                codigo_liga INTEGER NOT NULL,
                codigo_categoria INTEGER NOT NULL,
                CONSTRAINT categoria_liga_pk PRIMARY KEY (codigo_liga, codigo_categoria)
);


CREATE SEQUENCE public.equipo_codigo_equipo_seq_1;

CREATE TABLE public.equipo (
                codigo_equipo INTEGER NOT NULL DEFAULT nextval('public.equipo_codigo_equipo_seq_1'),
                codigo_tipo_lapso INTEGER NOT NULL,
                codigo_clasificacion INTEGER,
                codigo_categoria INTEGER NOT NULL,
                codigo_divisa INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                minimo_jugador INTEGER NOT NULL,
                maximo_jugador INTEGER NOT NULL,
                CONSTRAINT equipo_pk PRIMARY KEY (codigo_equipo)
);


ALTER SEQUENCE public.equipo_codigo_equipo_seq_1 OWNED BY public.equipo.codigo_equipo;

CREATE TABLE public.jugador_foraneo (
                cedula VARCHAR NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT jugador_foraneo_pk PRIMARY KEY (cedula)
);


CREATE TABLE public.horario_plan_temporada (
                codigo_horario_plan INTEGER NOT NULL,
                codigo_horario INTEGER NOT NULL,
                codigo_plan_temporada INTEGER NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT horario_plan_temporada_pk PRIMARY KEY (codigo_horario_plan)
);


CREATE SEQUENCE public.test_evaluativo_codigo_test_seq;

CREATE TABLE public.test_evaluativo (
                codigo_test INTEGER NOT NULL DEFAULT nextval('public.test_evaluativo_codigo_test_seq'),
                codigo_equipo INTEGER NOT NULL,
                fecha DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT test_evaluativo_pk PRIMARY KEY (codigo_test)
);


ALTER SEQUENCE public.test_evaluativo_codigo_test_seq OWNED BY public.test_evaluativo.codigo_test;

CREATE SEQUENCE public.indicador_test_codigo_indicador_test_seq;

CREATE TABLE public.indicador_test (
                codigo_indicador_test INTEGER NOT NULL DEFAULT nextval('public.indicador_test_codigo_indicador_test_seq'),
                codigo_indicador_actividad_escala INTEGER NOT NULL,
                codigo_test INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT indicador_test_pk PRIMARY KEY (codigo_indicador_test)
);


ALTER SEQUENCE public.indicador_test_codigo_indicador_test_seq OWNED BY public.indicador_test.codigo_indicador_test;

CREATE SEQUENCE public.sesion_codigo_sesion_seq_1_1;

CREATE SEQUENCE public.sesion_dia_semana_seq;

CREATE TABLE public.sesion (
                codigo_sesion INTEGER NOT NULL DEFAULT nextval('public.sesion_codigo_sesion_seq_1_1'),
                dia_semana INTEGER NOT NULL DEFAULT nextval('public.sesion_dia_semana_seq'),
                codigo_plan_entrenamiento INTEGER NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT sesion_pk PRIMARY KEY (codigo_sesion)
);


ALTER SEQUENCE public.sesion_codigo_sesion_seq_1_1 OWNED BY public.sesion.codigo_sesion;

ALTER SEQUENCE public.sesion_dia_semana_seq OWNED BY public.sesion.dia_semana;

CREATE TABLE public.instalacion_utilizada (
                codigo_instalacion_utilizada INTEGER NOT NULL,
                codigo_sesion INTEGER,
                codigo_instalacion INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE NOT NULL,
                estatus CHAR NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_fin TIME NOT NULL,
                CONSTRAINT instalacion_utilizada_pk PRIMARY KEY (codigo_instalacion_utilizada)
);


CREATE SEQUENCE public.planificacion_actividad_codigo_planificacion_actividad_seq;

CREATE TABLE public.planificacion_actividad (
                codigo_planificacion_actividad INTEGER NOT NULL DEFAULT nextval('public.planificacion_actividad_codigo_planificacion_actividad_seq'),
                codigo_instalacion_utilizada INTEGER NOT NULL,
                codigo_tipo_actividad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                actividad_periodico BOOLEAN NOT NULL,
                actividad_plantilla BOOLEAN NOT NULL,
                descripcion VARCHAR NOT NULL,
                CONSTRAINT planificacion_actividad_pk PRIMARY KEY (codigo_planificacion_actividad)
);


ALTER SEQUENCE public.planificacion_actividad_codigo_planificacion_actividad_seq OWNED BY public.planificacion_actividad.codigo_planificacion_actividad;

CREATE SEQUENCE public.actividad_codigo_actividad_seq_6_1_1;

CREATE TABLE public.actividad (
                codigo_actividad INTEGER NOT NULL DEFAULT nextval('public.actividad_codigo_actividad_seq_6_1_1'),
                codigo_instalacion_utilizada INTEGER NOT NULL,
                codigo_planificacion_actividad INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_culminacion DATE NOT NULL,
                estatus CHAR NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_fin TIME NOT NULL,
                CONSTRAINT actividad_pk PRIMARY KEY (codigo_actividad)
);


ALTER SEQUENCE public.actividad_codigo_actividad_seq_6_1_1 OWNED BY public.actividad.codigo_actividad;

CREATE TABLE public.personal_actividad (
                cedula_rif VARCHAR NOT NULL,
                codigo_actividad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_actividad_pk PRIMARY KEY (cedula_rif, codigo_actividad)
);


CREATE TABLE public.comision_actividad (
                codigo_comision INTEGER NOT NULL,
                codigo_actividad INTEGER NOT NULL,
                CONSTRAINT comision_actividad_pk PRIMARY KEY (codigo_comision, codigo_actividad)
);


CREATE SEQUENCE public.tarea_actividad_codigo_tarea_actividad_seq;

CREATE TABLE public.tarea_actividad (
                codigo_tarea_actividad INTEGER NOT NULL DEFAULT nextval('public.tarea_actividad_codigo_tarea_actividad_seq'),
                codigo_actividad INTEGER NOT NULL,
                codigo_tarea INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT tarea_actividad_pk PRIMARY KEY (codigo_tarea_actividad)
);


ALTER SEQUENCE public.tarea_actividad_codigo_tarea_actividad_seq OWNED BY public.tarea_actividad.codigo_tarea_actividad;

CREATE TABLE public.estado_actividad (
                codigo_estado INTEGER NOT NULL,
                codigo_actividad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT estado_actividad_pk PRIMARY KEY (codigo_estado, codigo_actividad)
);


CREATE TABLE public.resultado_actividad (
                codigo_resultado INTEGER NOT NULL,
                codigo_actividad INTEGER NOT NULL,
                observaciones VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT resultado_actividad_pk PRIMARY KEY (codigo_resultado, codigo_actividad)
);


CREATE SEQUENCE public.solicitud_mantenimiento_codigo_solicitud_seq_1;

CREATE TABLE public.solicitud_mantenimiento (
                codigo_solicitud INTEGER NOT NULL DEFAULT nextval('public.solicitud_mantenimiento_codigo_solicitud_seq_1'),
                codigo_actividad INTEGER NOT NULL,
                codigo_prioridad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT solicitud_mantenimiento_pk PRIMARY KEY (codigo_solicitud, codigo_actividad)
);


ALTER SEQUENCE public.solicitud_mantenimiento_codigo_solicitud_seq_1 OWNED BY public.solicitud_mantenimiento.codigo_solicitud;

CREATE TABLE public.personal_actividad_planificada (
                cedula_rif VARCHAR NOT NULL,
                codigo_planificacion_actividad INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_actividad_planificada_pk PRIMARY KEY (cedula_rif, codigo_planificacion_actividad)
);


CREATE SEQUENCE public.tarea_actividad_planificada_codigo_personal_actividad_planif18;

CREATE TABLE public.tarea_actividad_planificada (
                codigo_personal_actividad_planificada INTEGER NOT NULL DEFAULT nextval('public.tarea_actividad_planificada_codigo_personal_actividad_planif18'),
                codigo_planificacion_actividad INTEGER NOT NULL,
                codigo_tarea INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT tarea_actividad_planificada_pk PRIMARY KEY (codigo_personal_actividad_planificada)
);


ALTER SEQUENCE public.tarea_actividad_planificada_codigo_personal_actividad_planif18 OWNED BY public.tarea_actividad_planificada.codigo_personal_actividad_planificada;

CREATE SEQUENCE public.periodicidad_codigo_periodicidad_seq;

CREATE TABLE public.periodicidad (
                codigo_periodicidad INTEGER NOT NULL DEFAULT nextval('public.periodicidad_codigo_periodicidad_seq'),
                codigo_planificacion_actividad INTEGER NOT NULL,
                frecuencia_periodicidad VARCHAR NOT NULL,
                lapso_repeticion INTEGER NOT NULL,
                periodicidad_semanal VARCHAR NOT NULL,
                dia_repeticion INTEGER NOT NULL,
                mes_repeticion INTEGER NOT NULL,
                numero_repeticiones_periodicidad INTEGER NOT NULL,
                fecha_finalizacion_periodicidad DATE NOT NULL,
                estatus VARCHAR NOT NULL,
                hora_inicio TIME NOT NULL,
                hora_culminacion TIME NOT NULL,
                estatus_1 CHAR NOT NULL,
                CONSTRAINT periodicidad_pk PRIMARY KEY (codigo_periodicidad)
);


ALTER SEQUENCE public.periodicidad_codigo_periodicidad_seq OWNED BY public.periodicidad.codigo_periodicidad;

CREATE SEQUENCE public.sesion_ejecutada_codigo_sesion_ejecutada_seq_1;

CREATE TABLE public.sesion_ejecutada (
                codigo_sesion_ejecutada INTEGER NOT NULL DEFAULT nextval('public.sesion_ejecutada_codigo_sesion_ejecutada_seq_1'),
                codigo_sesion INTEGER NOT NULL,
                eventualidad_sesion INTEGER NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                fecha DATE NOT NULL,
                hora_fin TIME NOT NULL,
                estatus CHAR NOT NULL,
                hora_inicio TIME NOT NULL,
                observacion_sesion VARCHAR NOT NULL,
                CONSTRAINT sesion_ejecutada_pk PRIMARY KEY (codigo_sesion_ejecutada)
);


ALTER SEQUENCE public.sesion_ejecutada_codigo_sesion_ejecutada_seq_1 OWNED BY public.sesion_ejecutada.codigo_sesion_ejecutada;

CREATE TABLE public.instalacion_ejecutada (
                codigo_sesion_ejecutada INTEGER NOT NULL,
                codigo_instalacion_utilizada INTEGER NOT NULL,
                eventualidad_instalacion INTEGER NOT NULL,
                observacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT instalacion_ejecutada_pk PRIMARY KEY (codigo_sesion_ejecutada, codigo_instalacion_utilizada)
);


CREATE TABLE public.personal_suplente (
                cedula_rif VARCHAR NOT NULL,
                codigo_sesion_ejecutada INTEGER NOT NULL,
                eventualidad_entrenador INTEGER NOT NULL,
                observacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_suplente_pk PRIMARY KEY (cedula_rif, codigo_sesion_ejecutada)
);


CREATE SEQUENCE public.material_actividad_codigo_material_actividad_seq;

CREATE TABLE public.material_actividad (
                codigo_material_actividad INTEGER NOT NULL DEFAULT nextval('public.material_actividad_codigo_material_actividad_seq'),
                codigo_juego INTEGER,
                codigo_sesion_ejecutada INTEGER,
                codigo_actividad INTEGER,
                codigo_material INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                cantidad_entregada INTEGER NOT NULL,
                cantidad_devuelta INTEGER,
                fecha_devolucion DATE,
                observacion VARCHAR,
                CONSTRAINT material_actividad_pk PRIMARY KEY (codigo_material_actividad)
);


ALTER SEQUENCE public.material_actividad_codigo_material_actividad_seq OWNED BY public.material_actividad.codigo_material_actividad;

CREATE SEQUENCE public.actividades_ejecutadas_codigo_actividad_ejecutada_seq;

CREATE TABLE public.actividades_ejecutadas (
                codigo_actividad_ejecutada INTEGER NOT NULL DEFAULT nextval('public.actividades_ejecutadas_codigo_actividad_ejecutada_seq'),
                codigo_indicador_actividad_escala INTEGER NOT NULL,
                codigo_sesion_ejecutada INTEGER NOT NULL,
                tiempo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT actividades_ejecutadas_pk PRIMARY KEY (codigo_actividad_ejecutada)
);


ALTER SEQUENCE public.actividades_ejecutadas_codigo_actividad_ejecutada_seq OWNED BY public.actividades_ejecutadas.codigo_actividad_ejecutada;

CREATE SEQUENCE public.material_actividad_planificada_codigo_material_actividad_pla191;

CREATE TABLE public.material_actividad_planificada (
                codigo_material_actividad_planificada INTEGER NOT NULL DEFAULT nextval('public.material_actividad_planificada_codigo_material_actividad_pla191'),
                codigo_juego INTEGER,
                codigo_planificacion_actividad INTEGER,
                codigo_sesion INTEGER,
                codigo_material INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                cantidad_requerida INTEGER NOT NULL,
                CONSTRAINT material_actividad_planificada_pk PRIMARY KEY (codigo_material_actividad_planificada)
);


ALTER SEQUENCE public.material_actividad_planificada_codigo_material_actividad_pla191 OWNED BY public.material_actividad_planificada.codigo_material_actividad_planificada;

CREATE SEQUENCE public.actividad_calendario_codigo_actividad_calendario_seq;

CREATE TABLE public.actividad_calendario (
                codigo_actividad_calendario INTEGER NOT NULL DEFAULT nextval('public.actividad_calendario_codigo_actividad_calendario_seq'),
                codigo_sesion INTEGER,
                codigo_juego INTEGER,
                codigo_competencia INTEGER,
                codigo_tipo_actividad INTEGER NOT NULL,
                codigo_actividad INTEGER,
                fecha_inicio DATE NOT NULL,
                fecha_culminacion DATE NOT NULL,
                descripcion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                color VARCHAR,
                hora_inicio TIME NOT NULL,
                hora_fin TIME NOT NULL,
                CONSTRAINT actividad_calendario_pk PRIMARY KEY (codigo_actividad_calendario)
);


ALTER SEQUENCE public.actividad_calendario_codigo_actividad_calendario_seq OWNED BY public.actividad_calendario.codigo_actividad_calendario;

CREATE TABLE public.actividad_planificada (
                codigo_indicador_actividad_escala INTEGER NOT NULL,
                codigo_sesion INTEGER NOT NULL,
                tiempo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT actividad_planificada_pk PRIMARY KEY (codigo_indicador_actividad_escala, codigo_sesion)
);


CREATE SEQUENCE public.personal_equipo_codigo_personal_equipo_seq;

CREATE TABLE public.personal_equipo (
                codigo_personal_equipo INTEGER NOT NULL DEFAULT nextval('public.personal_equipo_codigo_personal_equipo_seq'),
                codigo_equipo INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                codigo_plan_temporada INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_finalizacion DATE NOT NULL,
                eventualidad VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT personal_equipo_pk PRIMARY KEY (codigo_personal_equipo)
);


ALTER SEQUENCE public.personal_equipo_codigo_personal_equipo_seq OWNED BY public.personal_equipo.codigo_personal_equipo;

CREATE TABLE public.asistencia_personal_entrenamiento (
                codigo_sesion_ejecutada INTEGER NOT NULL,
                codigo_personal_equipo INTEGER NOT NULL,
                eventualidad_entrenador INTEGER NOT NULL,
                asistencia BOOLEAN NOT NULL,
                observacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT asistencia_personal_entrenamiento_pk PRIMARY KEY (codigo_sesion_ejecutada, codigo_personal_equipo)
);


CREATE SEQUENCE public.equipo_competencia_codigo_equipo_competencia_seq;

CREATE TABLE public.equipo_competencia (
                codigo_equipo_competencia INTEGER NOT NULL DEFAULT nextval('public.equipo_competencia_codigo_equipo_competencia_seq'),
                cedula_delegado VARCHAR NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT equipo_competencia_pk PRIMARY KEY (codigo_equipo_competencia)
);


ALTER SEQUENCE public.equipo_competencia_codigo_equipo_competencia_seq OWNED BY public.equipo_competencia.codigo_equipo_competencia;

CREATE SEQUENCE public.equipo_juego_codigo_equipo_juego_seq;

CREATE TABLE public.equipo_juego (
                codigo_equipo_juego INTEGER NOT NULL DEFAULT nextval('public.equipo_juego_codigo_equipo_juego_seq'),
                codigo_equipo_competencia INTEGER NOT NULL,
                home_club BOOLEAN NOT NULL,
                codigo_juego INTEGER NOT NULL,
                CONSTRAINT equipo_juego_pk PRIMARY KEY (codigo_equipo_juego)
);


ALTER SEQUENCE public.equipo_juego_codigo_equipo_juego_seq OWNED BY public.equipo_juego.codigo_equipo_juego;

CREATE TABLE public.personal_equipo_juego (
                codigo_equipo_juego INTEGER NOT NULL,
                codigo_personal_equipo INTEGER NOT NULL,
                CONSTRAINT personal_equipo_juego_pk PRIMARY KEY (codigo_equipo_juego, codigo_personal_equipo)
);


CREATE TABLE public.desempenno_colectivo (
                codigo_equipo_juego INTEGER NOT NULL,
                codigo_indicador_categoria_competencia INTEGER NOT NULL,
                valor REAL NOT NULL,
                inning INTEGER NOT NULL,
                CONSTRAINT desempenno_colectivo_pk PRIMARY KEY (codigo_equipo_juego, codigo_indicador_categoria_competencia)
);


CREATE SEQUENCE public.comision_equipo_codigo_comision_equipo_seq_1_1;

CREATE TABLE public.comision_equipo (
                codigo_comision_equipo INTEGER NOT NULL DEFAULT nextval('public.comision_equipo_codigo_comision_equipo_seq_1_1'),
                codigo_comision INTEGER NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                maximo_comision INTEGER NOT NULL,
                cantidad_comision INTEGER NOT NULL,
                estatus_1 CHAR NOT NULL,
                CONSTRAINT comision_equipo_pk PRIMARY KEY (codigo_comision_equipo)
);


ALTER SEQUENCE public.comision_equipo_codigo_comision_equipo_seq_1_1 OWNED BY public.comision_equipo.codigo_comision_equipo;

CREATE TABLE public.familiar (
                cedula_rif VARCHAR NOT NULL,
                codigo_profesion INTEGER,
                estatus CHAR NOT NULL,
                CONSTRAINT familiar_pk PRIMARY KEY (cedula_rif)
);


CREATE TABLE public.representante_plan (
                cedula_familiar VARCHAR NOT NULL,
                nombre VARCHAR NOT NULL,
                apellido VARCHAR NOT NULL,
                telefono_habitacion VARCHAR,
                telefono_celular VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT representante_plan_pk PRIMARY KEY (cedula_familiar)
);


CREATE TABLE public.jugador (
                cedula_rif VARCHAR NOT NULL,
                codigo_pais INTEGER,
                codigo_parroquia_nacimiento INTEGER,
                numero INTEGER,
                tipo_de_sangre VARCHAR,
                peso DOUBLE PRECISION,
                altura DOUBLE PRECISION,
                brazo_lanzar VARCHAR,
                posicion_bateo VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT jugador_pk PRIMARY KEY (cedula_rif)
);


CREATE TABLE public.jugador_plan (
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_jugador INTEGER NOT NULL,
                codigo_talla_indumentaria INTEGER NOT NULL,
                apellido VARCHAR NOT NULL,
                nombre VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                fecha_nacimiento DATE NOT NULL,
                CONSTRAINT jugador_plan_pk PRIMARY KEY (cedula_rif)
);


CREATE SEQUENCE public.roster_plan_codigo_roster_plan_seq_1_1;

CREATE TABLE public.roster_plan (
                codigo_roster_plan INTEGER NOT NULL DEFAULT nextval('public.roster_plan_codigo_roster_plan_seq_1_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT roster_plan_pk PRIMARY KEY (codigo_roster_plan)
);


ALTER SEQUENCE public.roster_plan_codigo_roster_plan_seq_1_1 OWNED BY public.roster_plan.codigo_roster_plan;

CREATE TABLE public.representante_jugador_plan (
                cedula_rif VARCHAR NOT NULL,
                cedula_familiar VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT representante_jugador_plan_pk PRIMARY KEY (cedula_rif, cedula_familiar)
);


CREATE SEQUENCE public.familiar_jugador_codigo_familiar_jugador_seq_1;

CREATE TABLE public.familiar_jugador (
                codigo_familiar_jugador INTEGER NOT NULL DEFAULT nextval('public.familiar_jugador_codigo_familiar_jugador_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                cedula_familiar VARCHAR NOT NULL,
                codigo_parentesco INTEGER NOT NULL,
                representante_actual BOOLEAN NOT NULL,
                fecha_ingreso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT familiar_jugador_pk PRIMARY KEY (codigo_familiar_jugador)
);


ALTER SEQUENCE public.familiar_jugador_codigo_familiar_jugador_seq_1 OWNED BY public.familiar_jugador.codigo_familiar_jugador;

CREATE TABLE public.representante (
                codigo_familiar_jugador INTEGER NOT NULL,
                fecha_asignacion DATE NOT NULL,
                fecha_retiro DATE NOT NULL,
                CONSTRAINT representante_pk PRIMARY KEY (codigo_familiar_jugador)
);


CREATE SEQUENCE public.hospedaje_codigo_hospedaje_seq_1;

CREATE TABLE public.hospedaje (
                codigo_hospedaje INTEGER NOT NULL DEFAULT nextval('public.hospedaje_codigo_hospedaje_seq_1'),
                codigo_familiar_jugador INTEGER NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT hospedaje_pk PRIMARY KEY (codigo_hospedaje)
);


ALTER SEQUENCE public.hospedaje_codigo_hospedaje_seq_1 OWNED BY public.hospedaje.codigo_hospedaje;

CREATE TABLE public.familiar_comision_equipo (
                codigo_comision_equipo INTEGER NOT NULL,
                codigo_familiar_jugador INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT familiar_comision_equipo_pk PRIMARY KEY (codigo_comision_equipo, codigo_familiar_jugador)
);


CREATE SEQUENCE public.dato_deportivo_codigo_dato_deportivo_seq_1;

CREATE TABLE public.dato_deportivo (
                codigo_dato_deportivo INTEGER NOT NULL DEFAULT nextval('public.dato_deportivo_codigo_dato_deportivo_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_deportivo_pk PRIMARY KEY (codigo_dato_deportivo)
);


ALTER SEQUENCE public.dato_deportivo_codigo_dato_deportivo_seq_1 OWNED BY public.dato_deportivo.codigo_dato_deportivo;

CREATE TABLE public.logro_por_jugador (
                codigo_logro INTEGER NOT NULL,
                codigo_dato_deportivo INTEGER NOT NULL,
                observacion VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT logro_por_jugador_pk PRIMARY KEY (codigo_logro, codigo_dato_deportivo)
);


CREATE SEQUENCE public.dato_academico_codigo_academico_seq_1;

CREATE TABLE public.dato_academico (
                codigo_academico INTEGER NOT NULL DEFAULT nextval('public.dato_academico_codigo_academico_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_anno_escolar INTEGER NOT NULL,
                codigo_curso INTEGER NOT NULL,
                codigo_institucion INTEGER NOT NULL,
                fecha_ingreso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_academico_pk PRIMARY KEY (codigo_academico)
);


ALTER SEQUENCE public.dato_academico_codigo_academico_seq_1 OWNED BY public.dato_academico.codigo_academico;

CREATE TABLE public.documento_academico (
                codigo_documento_entregado INTEGER NOT NULL,
                codigo_academico INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_academico_pk PRIMARY KEY (codigo_documento_entregado, codigo_academico)
);


CREATE SEQUENCE public.dato_medico_codigo_dato_medico_seq_1;

CREATE TABLE public.dato_medico (
                codigo_dato_medico INTEGER NOT NULL DEFAULT nextval('public.dato_medico_codigo_dato_medico_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                numero_colegio VARCHAR NOT NULL,
                fecha_informe DATE,
                fecha_reincorporacion DATE,
                observacion VARCHAR,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_medico_pk PRIMARY KEY (codigo_dato_medico)
);


ALTER SEQUENCE public.dato_medico_codigo_dato_medico_seq_1 OWNED BY public.dato_medico.codigo_dato_medico;

CREATE TABLE public.documento_medico (
                codigo_documento_entregado INTEGER NOT NULL,
                codigo_dato_medico INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_medico_pk PRIMARY KEY (codigo_documento_entregado, codigo_dato_medico)
);


CREATE TABLE public.afeccion_jugador (
                codigo_afeccion INTEGER NOT NULL,
                codigo_dato_medico INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT afeccion_jugador_pk PRIMARY KEY (codigo_afeccion, codigo_dato_medico)
);


CREATE SEQUENCE public.retiro_traslado_codigo_retiro_traslado_seq_1;

CREATE TABLE public.retiro_traslado (
                codigo_retiro_traslado INTEGER NOT NULL DEFAULT nextval('public.retiro_traslado_codigo_retiro_traslado_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_operacion INTEGER NOT NULL,
                codigo_motivo_retiro INTEGER NOT NULL,
                fecha_retiro DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT retiro_traslado_pk PRIMARY KEY (codigo_retiro_traslado)
);


ALTER SEQUENCE public.retiro_traslado_codigo_retiro_traslado_seq_1 OWNED BY public.retiro_traslado.codigo_retiro_traslado;

CREATE SEQUENCE public.dato_conducta_codigo_dato_conducta_seq_1;

CREATE TABLE public.dato_conducta (
                codigo_dato_conducta INTEGER NOT NULL DEFAULT nextval('public.dato_conducta_codigo_dato_conducta_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_tipo_suspension INTEGER NOT NULL,
                fecha_inicio DATE NOT NULL,
                fecha_fin DATE,
                observacion VARCHAR,
                cantidad INTEGER NOT NULL,
                fecha_ocurrencia DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_conducta_pk PRIMARY KEY (codigo_dato_conducta)
);


ALTER SEQUENCE public.dato_conducta_codigo_dato_conducta_seq_1 OWNED BY public.dato_conducta.codigo_dato_conducta;

CREATE TABLE public.documento_conducta (
                codigo_documento_entregado INTEGER NOT NULL,
                codigo_dato_conducta INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_conducta_pk PRIMARY KEY (codigo_documento_entregado, codigo_dato_conducta)
);


CREATE TABLE public.motivo_sancion (
                codigo_motivo INTEGER NOT NULL,
                codigo_dato_conducta INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT motivo_sancion_pk PRIMARY KEY (codigo_motivo, codigo_dato_conducta)
);


CREATE SEQUENCE public.roster_codigo_roster_seq1;

CREATE TABLE public.roster (
                codigo_roster INTEGER NOT NULL DEFAULT nextval('public.roster_codigo_roster_seq1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_equipo INTEGER NOT NULL,
                fecha_ingreso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT roster_pk PRIMARY KEY (codigo_roster)
);


ALTER SEQUENCE public.roster_codigo_roster_seq1 OWNED BY public.roster.codigo_roster;

CREATE SEQUENCE public.roster_competencia_codigo_roster_competencia_seq_1;

CREATE TABLE public.roster_competencia (
                codigo_roster_competencia INTEGER NOT NULL DEFAULT nextval('public.roster_competencia_codigo_roster_competencia_seq_1'),
                codigo_roster INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                codigo_competencia INTEGER NOT NULL,
                CONSTRAINT roster_competencia_pk PRIMARY KEY (codigo_roster_competencia)
);


ALTER SEQUENCE public.roster_competencia_codigo_roster_competencia_seq_1 OWNED BY public.roster_competencia.codigo_roster_competencia;

CREATE TABLE public.jugador_foraneo_roster_competencia (
                cedula VARCHAR NOT NULL,
                codigo_roster_competencia INTEGER NOT NULL,
                CONSTRAINT jugador_foraneo_roster_competencia_pk PRIMARY KEY (cedula, codigo_roster_competencia)
);


CREATE SEQUENCE public.line_up_codigo_line_up_seq;

CREATE TABLE public.line_up (
                codigo_line_up INTEGER NOT NULL DEFAULT nextval('public.line_up_codigo_line_up_seq'),
                codigo_estado_line_up INTEGER NOT NULL,
                codigo_roster_competencia INTEGER NOT NULL,
                codigo_tipo_mencion INTEGER,
                codigo_posicion INTEGER,
                codigo_juego INTEGER NOT NULL,
                orden_bate INTEGER,
                CONSTRAINT line_up_pk PRIMARY KEY (codigo_line_up)
);


ALTER SEQUENCE public.line_up_codigo_line_up_seq OWNED BY public.line_up.codigo_line_up;

CREATE TABLE public.desempenno_individual (
                codigo_indicador_categoria_competencia INTEGER NOT NULL,
                codigo_line_up INTEGER NOT NULL,
                valor REAL NOT NULL,
                CONSTRAINT desempenno_individual_pk PRIMARY KEY (codigo_indicador_categoria_competencia, codigo_line_up)
);


CREATE SEQUENCE public.asistencia_jugador_codigo_asistencia_seq_1;

CREATE TABLE public.asistencia_jugador (
                codigo_asistencia INTEGER NOT NULL DEFAULT nextval('public.asistencia_jugador_codigo_asistencia_seq_1'),
                codigo_roster_plan INTEGER,
                codigo_roster INTEGER,
                codigo_sesion_ejecutada INTEGER NOT NULL,
                eventualidad INTEGER NOT NULL,
                asistencia BOOLEAN NOT NULL,
                observacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT asistencia_jugador_pk PRIMARY KEY (codigo_asistencia)
);


ALTER SEQUENCE public.asistencia_jugador_codigo_asistencia_seq_1 OWNED BY public.asistencia_jugador.codigo_asistencia;

CREATE TABLE public.desempenno_jugador (
                codigo_asistencia INTEGER NOT NULL,
                codigo_actividad_ejecutada INTEGER NOT NULL,
                puntuacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT desempenno_jugador_pk PRIMARY KEY (codigo_asistencia, codigo_actividad_ejecutada)
);


CREATE TABLE public.test_jugador (
                codigo_test_jugador INTEGER NOT NULL,
                codigo_roster_plan INTEGER,
                codigo_roster INTEGER,
                codigo_indicador_test INTEGER NOT NULL,
                puntuacion VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT test_jugador_pk PRIMARY KEY (codigo_test_jugador)
);


CREATE SEQUENCE public.ascenso_codigo_ascenso_seq1;

CREATE TABLE public.ascenso (
                codigo_ascenso INTEGER NOT NULL DEFAULT nextval('public.ascenso_codigo_ascenso_seq1'),
                codigo_roster INTEGER NOT NULL,
                tipo_ascenso VARCHAR NOT NULL,
                fecha_ascenso DATE NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT ascenso_pk PRIMARY KEY (codigo_ascenso)
);


ALTER SEQUENCE public.ascenso_codigo_ascenso_seq1 OWNED BY public.ascenso.codigo_ascenso;

CREATE TABLE public.documento_ascenso (
                codigo_documento_entregado INTEGER NOT NULL,
                codigo_ascenso INTEGER NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_ascenso_pk PRIMARY KEY (codigo_documento_entregado, codigo_ascenso)
);


CREATE TABLE public.talla_por_jugador (
                codigo_talla_indumentaria INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT talla_por_jugador_pk PRIMARY KEY (codigo_talla_indumentaria, cedula_rif)
);


CREATE SEQUENCE public.dato_social_codigo_dato_social_seq_1;

CREATE TABLE public.dato_social (
                codigo_dato_social INTEGER NOT NULL DEFAULT nextval('public.dato_social_codigo_dato_social_seq_1'),
                cedula_rif VARCHAR NOT NULL,
                codigo_actividad_social INTEGER NOT NULL,
                codigo_institucion INTEGER NOT NULL,
                horas_dedicadas INTEGER,
                fecha_inicio DATE,
                estatus CHAR NOT NULL,
                CONSTRAINT dato_social_pk PRIMARY KEY (codigo_dato_social)
);


ALTER SEQUENCE public.dato_social_codigo_dato_social_seq_1 OWNED BY public.dato_social.codigo_dato_social;

CREATE TABLE public.documento_personal (
                codigo_documento_entregado INTEGER NOT NULL,
                cedula_rif VARCHAR NOT NULL,
                estatus CHAR NOT NULL,
                CONSTRAINT documento_personal_pk PRIMARY KEY (codigo_documento_entregado, cedula_rif)
);


ALTER TABLE public.funcionalidad ADD CONSTRAINT funcionalidad_funcionalidad_fk
FOREIGN KEY (Parent_codigo_funconalidad)
REFERENCES public.funcionalidad (codigo_funconalidad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.funcionalidad_grupo ADD CONSTRAINT funcionalidad_funcionalidad_grupo_fk
FOREIGN KEY (codigo_funconalidad)
REFERENCES public.funcionalidad (codigo_funconalidad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.perfil_grupo ADD CONSTRAINT grupo_perfil_grupo_fk
FOREIGN KEY (codigo_grupo)
REFERENCES public.grupo (codigo_grupo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.funcionalidad_grupo ADD CONSTRAINT grupo_funcionalidad_grupo_fk
FOREIGN KEY (codigo_grupo)
REFERENCES public.grupo (codigo_grupo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.constante_categoria ADD CONSTRAINT constante_constante_categoria_fk
FOREIGN KEY (codigo_constante)
REFERENCES public.constante (codigo_constante)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_cuenta_pagar ADD CONSTRAINT egreso_egreso_cuenta_pagar_fk
FOREIGN KEY (codigo_egreso)
REFERENCES public.egreso (codigo_egreso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_forma_pago ADD CONSTRAINT egreso_egreso_forma_pago_fk
FOREIGN KEY (codigo_egreso)
REFERENCES public.egreso (codigo_egreso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_basico ADD CONSTRAINT tipo_dato_dato_basico_fk
FOREIGN KEY (codigo_tipo_dato)
REFERENCES public.tipo_dato (codigo_tipo_dato)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tipo_dato ADD CONSTRAINT tipo_dato_tipo_dato_fk
FOREIGN KEY (Parent_codigo_tipo_dato)
REFERENCES public.tipo_dato (codigo_tipo_dato)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.seguridad_funcional ADD CONSTRAINT tipo_dato_seguridad_funcional_fk
FOREIGN KEY (codigo_tipo_dato)
REFERENCES public.tipo_dato (codigo_tipo_dato)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.detalle_seguridad_funcional ADD CONSTRAINT tipo_dato_detalle_seguridad_funcional_fk
FOREIGN KEY (codigo_tipo_dato)
REFERENCES public.tipo_dato (codigo_tipo_dato)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.planificacion_actividad ADD CONSTRAINT dato_basico_planificacion_mantenimiento_fk
FOREIGN KEY (codigo_tipo_actividad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad_planificada ADD CONSTRAINT dato_basico_tarea_mantenimiento_planificado_fk
FOREIGN KEY (codigo_tarea)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad ADD CONSTRAINT dato_basico_tarea_mantenimiento_fk
FOREIGN KEY (codigo_tarea)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion ADD CONSTRAINT dato_basico_instalacion_fk
FOREIGN KEY (codigo_tipo_instalacion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.estado_actividad ADD CONSTRAINT dato_basico_estado_mantenimiento_fk
FOREIGN KEY (codigo_estado)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.resultado_actividad ADD CONSTRAINT dato_basico_resultado_mantenimiento_fk
FOREIGN KEY (codigo_resultado)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comision_equipo ADD CONSTRAINT dato_basico_comision_equipo_fk
FOREIGN KEY (codigo_comision)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comision_actividad ADD CONSTRAINT dato_basico_comision_actividad_fk
FOREIGN KEY (codigo_comision)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar ADD CONSTRAINT dato_basico_familiar_fk
FOREIGN KEY (codigo_profesion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_social ADD CONSTRAINT dato_basico_dato_social_fk
FOREIGN KEY (codigo_actividad_social)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar_jugador ADD CONSTRAINT dato_basico_familiar_jugador_fk
FOREIGN KEY (codigo_parentesco)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo ADD CONSTRAINT dato_basico_equipo_fk
FOREIGN KEY (codigo_clasificacion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.afeccion_jugador ADD CONSTRAINT dato_basico_afeccion_jugador_fk
FOREIGN KEY (codigo_afeccion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_cargo ADD CONSTRAINT dato_basico_personal_cargo_fk
FOREIGN KEY (codigo_cargo)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.afeccion_personal ADD CONSTRAINT dato_basico_afeccion_personal_fk
FOREIGN KEY (codigo_tipo_afeccion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.competencia ADD CONSTRAINT dato_basico_competencia_fk
FOREIGN KEY (codigo_estado_competencia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.juego ADD CONSTRAINT dato_basico_juego_fk
FOREIGN KEY (codigo_estado)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.estadio ADD CONSTRAINT dato_basico_estadio_fk
FOREIGN KEY (codigo_parroquia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador ADD CONSTRAINT dato_basico_indicador_fk
FOREIGN KEY (codigo_tipo_indicador)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_tipo_nomina ADD CONSTRAINT dato_basico_personal_tipo_nomina_fk
FOREIGN KEY (codigo_tipo_nomina)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nomina ADD CONSTRAINT dato_basico_nomina_fk
FOREIGN KEY (codigo_tipo_nomina)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.persona ADD CONSTRAINT dato_basico_persona_fk
FOREIGN KEY (codigo_parroquia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.logro_por_jugador ADD CONSTRAINT dato_basico_logro_por_jugador_fk
FOREIGN KEY (codigo_logro)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador ADD CONSTRAINT dato_basico_jugador_fk
FOREIGN KEY (codigo_parroquia_nacimiento)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.talla_por_indumentaria ADD CONSTRAINT dato_basico_talla_por_indumentaria_fk
FOREIGN KEY (codigo_talla)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_academico ADD CONSTRAINT dato_basico_dato_academico_fk
FOREIGN KEY (codigo_anno_escolar)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_academico ADD CONSTRAINT dato_basico_dato_academico_fk1
FOREIGN KEY (codigo_curso)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.motivo_sancion ADD CONSTRAINT dato_basico_motivo_sancion_fk
FOREIGN KEY (codigo_motivo)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_conducta ADD CONSTRAINT dato_basico_dato_conducta_fk
FOREIGN KEY (codigo_tipo_suspension)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recaudo_por_proceso ADD CONSTRAINT dato_basico_documento_fk1
FOREIGN KEY (codigo_documento)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recaudo_por_proceso ADD CONSTRAINT dato_basico_documento_fk2
FOREIGN KEY (codigo_proceso)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recaudo_por_proceso ADD CONSTRAINT dato_basico_recaudo_por_proceso_fk
FOREIGN KEY (codigo_importancia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.horario ADD CONSTRAINT dato_basico_horario_fk
FOREIGN KEY (dia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_entrenamiento ADD CONSTRAINT dato_basico_actividad_entrenamiento_fk
FOREIGN KEY (fase)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion ADD CONSTRAINT dato_basico_sesion_fk
FOREIGN KEY (dia_semana)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion_ejecutada ADD CONSTRAINT dato_basico_sesion_ejecutada_fk
FOREIGN KEY (eventualidad_sesion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar ADD CONSTRAINT dato_basico_cuenta_pagar_fk2
FOREIGN KEY (codigo_tipo_documento)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar ADD CONSTRAINT dato_basico_cuenta_pagar_fk
FOREIGN KEY (codigo_tipo_egreso)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.concepto_nomina ADD CONSTRAINT dato_basico_concepto_nomina_fk
FOREIGN KEY (codigo_tipo_concepto)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_contrato ADD CONSTRAINT dato_basico_personal_contrato_fk
FOREIGN KEY (codigo_modalidad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_contrato ADD CONSTRAINT dato_basico_personal_contrato_fk1
FOREIGN KEY (codigo_horario)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.medico ADD CONSTRAINT dato_basico_medico_fk
FOREIGN KEY (codigo_especialidad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material ADD CONSTRAINT dato_basico_material_fk
FOREIGN KEY (codigo_tipo_material)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material ADD CONSTRAINT dato_basico_material_fk1
FOREIGN KEY (codigo_unidad_medida)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_foraneo_juego ADD CONSTRAINT dato_basico_umpire_juego_fk
FOREIGN KEY (codigo_tipo)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.line_up ADD CONSTRAINT dato_basico_line_up_fk
FOREIGN KEY (codigo_posicion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.line_up ADD CONSTRAINT dato_basico_line_up_fk1
FOREIGN KEY (codigo_tipo_mencion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador ADD CONSTRAINT dato_basico_indicador_fk1
FOREIGN KEY (codigo_modalidad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.solicitud_mantenimiento ADD CONSTRAINT dato_basico_solicitud_mantenimiento_fk
FOREIGN KEY (codigo_prioridad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_basico ADD CONSTRAINT dato_basico_dato_basico_fk
FOREIGN KEY (Parent_codigo_dato_basico)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.competencia ADD CONSTRAINT dato_basico_competencia_fk2
FOREIGN KEY (codigo_organizacion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_forma_pago ADD CONSTRAINT dato_basico_ingreso_forma_pago_fk
FOREIGN KEY (codigo_forma_pago)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso ADD CONSTRAINT dato_basico_ingreso_fk
FOREIGN KEY (codigo_tipo_documento)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.proveedor_banco ADD CONSTRAINT dato_basico_proveedor_banco_fk
FOREIGN KEY (codigo_banco)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_forma_pago ADD CONSTRAINT dato_basico_ingreso_forma_pago_fk1
FOREIGN KEY (codigo_tarjeta)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_forma_pago ADD CONSTRAINT dato_basico_ingreso_forma_pago_fk2
FOREIGN KEY (codigo_banco)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_forma_pago ADD CONSTRAINT dato_basico_egreso_forma_pago_fk1
FOREIGN KEY (codigo_banco)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_forma_pago ADD CONSTRAINT dato_basico_egreso_forma_pago_fk
FOREIGN KEY (codigo_tarjeta)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.competencia ADD CONSTRAINT dato_basico_competencia_fk1
FOREIGN KEY (codigo_estado)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.divisa ADD CONSTRAINT dato_basico_divisa_fk
FOREIGN KEY (codigo_parroquia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.escala_medicion ADD CONSTRAINT dato_basico_escala_medicion_fk
FOREIGN KEY (tipo_escala)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_actividad_escala ADD CONSTRAINT dato_basico_indicador_actividad_escala_fk
FOREIGN KEY (indicador)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador ADD CONSTRAINT dato_basico_jugador_fk1
FOREIGN KEY (codigo_pais)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.retiro_traslado ADD CONSTRAINT dato_basico_retiro_traslado_fk
FOREIGN KEY (codigo_motivo_retiro)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.lapso_deportivo ADD CONSTRAINT dato_basico_lapso_deportivo_fk
FOREIGN KEY (tipo_lapso)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_jugador ADD CONSTRAINT dato_basico_asistencia_jugador_fk
FOREIGN KEY (eventualidad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_foraneo ADD CONSTRAINT dato_basico_personal_juego_fk1
FOREIGN KEY (codigo_tipo_personal_foraneo)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.institucion ADD CONSTRAINT dato_basico_institucion_fk
FOREIGN KEY (codigo_tipo_institucion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_calendario ADD CONSTRAINT dato_basico_actividad_calendario_fk
FOREIGN KEY (codigo_tipo_actividad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.persona ADD CONSTRAINT dato_basico_persona_fk1
FOREIGN KEY (codigo_tipo_persona)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.institucion ADD CONSTRAINT dato_basico_institucion_fk1
FOREIGN KEY (codigo_parroquia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_forma_pago ADD CONSTRAINT dato_basico_egreso_forma_pago_fk2
FOREIGN KEY (codigo_forma_pago)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.proveedor_banco ADD CONSTRAINT dato_basico_proveedor_banco_fk1
FOREIGN KEY (codigo_tipo_cuenta)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal ADD CONSTRAINT dato_basico_personal_fk
FOREIGN KEY (codigo_estado_civil)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.persona_natural ADD CONSTRAINT dato_basico_persona_natural_fk
FOREIGN KEY (codigo_genero)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador ADD CONSTRAINT dato_basico_indicador_fk2
FOREIGN KEY (codigo_medicion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.retiro_traslado ADD CONSTRAINT dato_basico_retiro_traslado_fk1
FOREIGN KEY (codigo_tipo_operacion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.talla_por_indumentaria ADD CONSTRAINT dato_basico_talla_por_indumentaria_fk1
FOREIGN KEY (codigo_tipo_uniforme)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo ADD CONSTRAINT dato_basico_equipo_fk1
FOREIGN KEY (codigo_tipo_lapso)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_plan ADD CONSTRAINT dato_basico_jugador_plan_fk
FOREIGN KEY (codigo_tipo_jugador)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.line_up ADD CONSTRAINT dato_basico_line_up_fk2
FOREIGN KEY (codigo_estado_line_up)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_personal_entrenamiento ADD CONSTRAINT dato_basico_asistencia_personal_entrenamiento_fk
FOREIGN KEY (eventualidad_entrenador)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_suplente ADD CONSTRAINT dato_basico_personal_suplente_fk
FOREIGN KEY (eventualidad_entrenador)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion_ejecutada ADD CONSTRAINT dato_basico_instalacion_ejecutada_fk
FOREIGN KEY (eventualidad_instalacion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.requisicion ADD CONSTRAINT dato_basico_requisicion_fk
FOREIGN KEY (codigo_estado_requisicion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tipo_ingreso ADD CONSTRAINT dato_basico_tipo_ingreso_fk
FOREIGN KEY (codigo_periodicidad)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.condicion_competencia ADD CONSTRAINT dato_basico_condicion_competencia_fk
FOREIGN KEY (codigo_condicion)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.clasificacion_competencia ADD CONSTRAINT dato_basico_clasificacion_competencia_fk
FOREIGN KEY (tipo_competencia)
REFERENCES public.dato_basico (codigo_dato_basico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.condicion_competencia ADD CONSTRAINT clasificacion_competencia_condicion_competencia_fk
FOREIGN KEY (codigo_clasificacion_competencia)
REFERENCES public.clasificacion_competencia (codigo_clasificacion_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.competencia ADD CONSTRAINT clasificacion_competencia_competencia_fk
FOREIGN KEY (codigo_clasificacion_competencia)
REFERENCES public.clasificacion_competencia (codigo_clasificacion_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_acreedor ADD CONSTRAINT tipo_ingreso_documento_acreedor_fk
FOREIGN KEY (codigo_tipo_ingreso)
REFERENCES public.tipo_ingreso (codigo_tipo_ingreso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_foraneo_juego ADD CONSTRAINT umpire_umpire_juego_fk
FOREIGN KEY (codigo_personal_foraneo)
REFERENCES public.personal_foraneo (codigo_personal_foraneo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.plan_temporada ADD CONSTRAINT temporada_plan_temporada_fk
FOREIGN KEY (codigo_lapso_deportivo)
REFERENCES public.lapso_deportivo (codigo_lapso_deportivo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.competencia ADD CONSTRAINT temporada_competencia_fk
FOREIGN KEY (codigo_lapso_deportivo)
REFERENCES public.lapso_deportivo (codigo_lapso_deportivo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.valor_escala ADD CONSTRAINT escala_medicion_valor_escala_fk
FOREIGN KEY (codigo_escala)
REFERENCES public.escala_medicion (codigo_escala)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_actividad_escala ADD CONSTRAINT escala_medicion_indicador_actividad_escala_fk
FOREIGN KEY (codigo_escala)
REFERENCES public.escala_medicion (codigo_escala)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_documento_acreedor ADD CONSTRAINT ingreso_ingreso_documento_acreedor_fk
FOREIGN KEY (numero_documento)
REFERENCES public.ingreso (numero_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_forma_pago ADD CONSTRAINT ingreso_ingreso_forma_pago_fk1
FOREIGN KEY (numero_documento)
REFERENCES public.ingreso (numero_documento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.horario_plan_temporada ADD CONSTRAINT horario_horario_plan_temporada_fk
FOREIGN KEY (codigo_horario)
REFERENCES public.horario (codigo_horario)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_entregado ADD CONSTRAINT recaudo_por_proceso_documento_entregado_fk
FOREIGN KEY (codigo_recaudo_por_proceso)
REFERENCES public.recaudo_por_proceso (codigo_recaudo_por_proceso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_academico ADD CONSTRAINT documento_entregado_documento_academico_fk
FOREIGN KEY (codigo_documento_entregado)
REFERENCES public.documento_entregado (codigo_documento_entregado)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_medico ADD CONSTRAINT documento_entregado_documento_medico_fk
FOREIGN KEY (codigo_documento_entregado)
REFERENCES public.documento_entregado (codigo_documento_entregado)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_conducta ADD CONSTRAINT documento_entregado_documento_conducta_fk
FOREIGN KEY (codigo_documento_entregado)
REFERENCES public.documento_entregado (codigo_documento_entregado)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_ascenso ADD CONSTRAINT documento_entregado_documento_ascenso_fk
FOREIGN KEY (codigo_documento_entregado)
REFERENCES public.documento_entregado (codigo_documento_entregado)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_personal ADD CONSTRAINT documento_entregado_documento_personal_fk
FOREIGN KEY (codigo_documento_entregado)
REFERENCES public.documento_entregado (codigo_documento_entregado)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.talla_por_jugador ADD CONSTRAINT talla_por_indumentaria_talla_por_jugador_fk
FOREIGN KEY (codigo_talla_indumentaria)
REFERENCES public.talla_por_indumentaria (codigo_talla_indumentaria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_indumentaria ADD CONSTRAINT talla_por_indumentaria_documento_indumentaria_fk
FOREIGN KEY (codigo_talla_indumentaria)
REFERENCES public.talla_por_indumentaria (codigo_talla_indumentaria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_plan ADD CONSTRAINT talla_por_indumentaria_jugador_plan_fk
FOREIGN KEY (codigo_talla_indumentaria)
REFERENCES public.talla_por_indumentaria (codigo_talla_indumentaria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.persona_juridica ADD CONSTRAINT persona_persona_juridica_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.persona_natural ADD CONSTRAINT persona_persona_natural_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar ADD CONSTRAINT persona_cuenta_pagar_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_acreedor ADD CONSTRAINT persona_documento_acreedor_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_acreedor ADD CONSTRAINT persona_documento_acreedor_fk1
FOREIGN KEY (cedula_atleta)
REFERENCES public.persona (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_indumentaria ADD CONSTRAINT documento_acreedor_documento_indumentaria_fk
FOREIGN KEY (codigo_documento_acreedor)
REFERENCES public.documento_acreedor (codigo_documento_acreedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ingreso_documento_acreedor ADD CONSTRAINT documento_acreedor_ingreso_documento_acreedor_fk
FOREIGN KEY (codigo_documento_acreedor)
REFERENCES public.documento_acreedor (codigo_documento_acreedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nota_entrega ADD CONSTRAINT documento_acreedor_nota_entrega_fk
FOREIGN KEY (codigo_documento_acreedor)
REFERENCES public.documento_acreedor (codigo_documento_acreedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_acreedor_material ADD CONSTRAINT documento_acreedor_donacion_material_fk
FOREIGN KEY (codigo_documento_acreedor)
REFERENCES public.documento_acreedor (codigo_documento_acreedor)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.egreso_cuenta_pagar ADD CONSTRAINT cuenta_pagar_egreso_cuenta_pagar_fk
FOREIGN KEY (origen)
REFERENCES public.cuenta_pagar (origen)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar_material ADD CONSTRAINT cuenta_pagar_cuenta_pagar_material_fk
FOREIGN KEY (origen)
REFERENCES public.cuenta_pagar (origen)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.nota_entrega ADD CONSTRAINT cuenta_pagar_nota_entrega_fk
FOREIGN KEY (origen)
REFERENCES public.cuenta_pagar (origen)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recepcion_material ADD CONSTRAINT nota_entrega_recepcion_material_fk
FOREIGN KEY (codigo_nota_entrega)
REFERENCES public.nota_entrega (codigo_nota_entrega)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal ADD CONSTRAINT persona_natural_personal_fk1
FOREIGN KEY (cedula_rif)
REFERENCES public.persona_natural (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador ADD CONSTRAINT persona_natural_jugador_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona_natural (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar ADD CONSTRAINT persona_natural_familiar_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona_natural (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo_competencia ADD CONSTRAINT persona_natural_equipo_competencia_fk
FOREIGN KEY (cedula_delegado)
REFERENCES public.persona_natural (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_concepto_nomina ADD CONSTRAINT personal_personal_concepto_nomina_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_tipo_nomina ADD CONSTRAINT personal_personal_tipo_nomina_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_cargo ADD CONSTRAINT personal_personal_cargo_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.afeccion_personal ADD CONSTRAINT personal_afeccion_personal_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.movimiento ADD CONSTRAINT personal_movimiento_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_actividad_planificada ADD CONSTRAINT personal_empleado_actividad_planificada_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_equipo ADD CONSTRAINT personal_personal_equipo_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.requisicion ADD CONSTRAINT personal_requisicion_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.usuario ADD CONSTRAINT personal_usuario_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_contrato ADD CONSTRAINT personal_personal_contrato_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_academico_personal ADD CONSTRAINT personal_dato_academico_personal_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_actividad ADD CONSTRAINT personal_personal_actividad_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_suplente ADD CONSTRAINT personal_personal_suplente_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.personal (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.seguridad_funcional ADD CONSTRAINT usuario_seguridad_funcional_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.usuario (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.perfil ADD CONSTRAINT usuario_perfil_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.usuario (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.detalle_seguridad_funcional ADD CONSTRAINT usuario_detalle_seguridad_funcional_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.usuario (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.perfil_grupo ADD CONSTRAINT perfil_perfil_grupo_fk
FOREIGN KEY (codigo_perfil)
REFERENCES public.perfil (codigo_perfil)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.detalle_seguridad_funcional ADD CONSTRAINT seguridad_funcional_detalle_seguridad_funcional_fk
FOREIGN KEY (codigo_seguridad)
REFERENCES public.seguridad_funcional (codigo_seguridad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.detalle_requisicion ADD CONSTRAINT requisicion_detalle_requisicion_fk
FOREIGN KEY (codigo_requisicion)
REFERENCES public.requisicion (codigo_requisicion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.proveedor_banco ADD CONSTRAINT persona_juridica_proveedor_banco_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.persona_juridica (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.movimiento ADD CONSTRAINT nomina_movimiento_fk
FOREIGN KEY (codigo_nomina)
REFERENCES public.nomina (codigo_nomina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_categoria_competencia ADD CONSTRAINT indicador_indicador_competencia_fk
FOREIGN KEY (codigo_indicador)
REFERENCES public.indicador (codigo_indicador)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.juego ADD CONSTRAINT estadio_juego_fk
FOREIGN KEY (codigo_estadio)
REFERENCES public.estadio (codigo_estadio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_medico ADD CONSTRAINT medico_dato_medico_fk
FOREIGN KEY (numero_colegio)
REFERENCES public.medico (numero_colegio)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_social ADD CONSTRAINT institucion_dato_social_fk
FOREIGN KEY (codigo_institucion)
REFERENCES public.institucion (codigo_institucion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_academico ADD CONSTRAINT institucion_dato_academico_fk
FOREIGN KEY (codigo_institucion)
REFERENCES public.institucion (codigo_institucion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_concepto_nomina ADD CONSTRAINT concepto_nomina_personal_concepto_nomina_fk
FOREIGN KEY (codigo_concepto_nomina)
REFERENCES public.concepto_nomina (codigo_concepto_nomina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.movimiento ADD CONSTRAINT concepto_nomina_movimiento_fk
FOREIGN KEY (codigo_concepto_nomina)
REFERENCES public.concepto_nomina (codigo_concepto_nomina)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.categoria_competencia ADD CONSTRAINT competencia_categoria_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.fase_competencia ADD CONSTRAINT competencia_fase_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_deportivo ADD CONSTRAINT competencia_dato_deportivo_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.hospedaje ADD CONSTRAINT competencia_hospedaje_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo_competencia ADD CONSTRAINT competencia_equipo_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.juego ADD CONSTRAINT competencia_juego_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_categoria_competencia ADD CONSTRAINT competencia_indicador_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster_competencia ADD CONSTRAINT competencia_roster_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.liga_competencia ADD CONSTRAINT competencia_liga_competencia_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_calendario ADD CONSTRAINT competencia_actividad_calendario_fk
FOREIGN KEY (codigo_competencia)
REFERENCES public.competencia (codigo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_foraneo_juego ADD CONSTRAINT juego_umpire_juego_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo_juego ADD CONSTRAINT juego_equipo_juego_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.line_up ADD CONSTRAINT juego_line_up_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_calendario ADD CONSTRAINT juego_actividad_calendario_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad ADD CONSTRAINT juego_material_actividad_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad_planificada ADD CONSTRAINT juego_material_actividad_planificada_fk
FOREIGN KEY (codigo_juego)
REFERENCES public.juego (codigo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo ADD CONSTRAINT divisa_equipo_fk
FOREIGN KEY (codigo_divisa)
REFERENCES public.divisa (codigo_divisa)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.categoria_liga ADD CONSTRAINT liga_liga_categoria_fk
FOREIGN KEY (codigo_liga)
REFERENCES public.liga (codigo_liga)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.liga_competencia ADD CONSTRAINT liga_liga_competencia_fk
FOREIGN KEY (codigo_liga)
REFERENCES public.liga (codigo_liga)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.almacen ADD CONSTRAINT instalacion_almacen_fk
FOREIGN KEY (codigo_instalacion)
REFERENCES public.instalacion (codigo_instalacion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion_utilizada ADD CONSTRAINT instalacion_instalacion_utilizada_fk
FOREIGN KEY (codigo_instalacion)
REFERENCES public.instalacion (codigo_instalacion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material ADD CONSTRAINT almacen_material_fk
FOREIGN KEY (codigo_almacen)
REFERENCES public.almacen (codigo_almacen)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad ADD CONSTRAINT material_material_mantenimiento_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad_planificada ADD CONSTRAINT material_material_mantenimiento_planificado_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.detalle_requisicion ADD CONSTRAINT material_detalle_requisicion_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar_material ADD CONSTRAINT material_cuenta_pagar_material_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.cuenta_pagar_material ADD CONSTRAINT material_cuenta_pagar_material_fk1
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_acreedor_material ADD CONSTRAINT material_donacion_material_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.recepcion_material ADD CONSTRAINT material_recepcion_material_fk
FOREIGN KEY (codigo_material)
REFERENCES public.material (codigo_material)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.categoria_liga ADD CONSTRAINT categoria_liga_categoria_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.categoria_competencia ADD CONSTRAINT categoria_categoria_competencia_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo ADD CONSTRAINT categoria_equipo_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_entrenamiento ADD CONSTRAINT categoria_actividad_entrenamiento_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.plan_temporada ADD CONSTRAINT categoria_temporada_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_categoria_competencia ADD CONSTRAINT categoria_indicador_competencia_fk
FOREIGN KEY (codigo_categoria)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.constante_categoria ADD CONSTRAINT categoria_constante_categoria_fk
FOREIGN KEY (codigo_categoria_1)
REFERENCES public.categoria (codigo_categoria)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_colectivo ADD CONSTRAINT indicador_competencia_desempenno_colectivo_fk
FOREIGN KEY (codigo_indicador_categoria_competencia)
REFERENCES public.indicador_categoria_competencia (codigo_indicador_categoria_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_individual ADD CONSTRAINT indicador_competencia_desempenno_individual_fk
FOREIGN KEY (codigo_indicador_categoria_competencia)
REFERENCES public.indicador_categoria_competencia (codigo_indicador_categoria_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_equipo ADD CONSTRAINT temporada_personal_equipo_fk
FOREIGN KEY (codigo_plan_temporada)
REFERENCES public.plan_temporada (codigo_plan_temporada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.plan_entrenamiento ADD CONSTRAINT temporada_plan_entrenamiento_fk
FOREIGN KEY (codigo_plan_temporada)
REFERENCES public.plan_temporada (codigo_plan_temporada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.horario_plan_temporada ADD CONSTRAINT plan_temporada_horario_plan_temporada_fk
FOREIGN KEY (codigo_plan_temporada)
REFERENCES public.plan_temporada (codigo_plan_temporada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion ADD CONSTRAINT plan_entrenamiento_sesion_fk
FOREIGN KEY (codigo_plan_entrenamiento)
REFERENCES public.plan_entrenamiento (codigo_plan_entrenamiento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_actividad_escala ADD CONSTRAINT actividad_entrenamiento_indicador_actividad_escala_fk
FOREIGN KEY (cod_actividad_entrenamiento)
REFERENCES public.actividad_entrenamiento (cod_actividad_entrenamiento)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_planificada ADD CONSTRAINT indicador_actividad_escala_actividad_planificada_fk
FOREIGN KEY (codigo_indicador_actividad_escala)
REFERENCES public.indicador_actividad_escala (codigo_indicador_actividad_escala)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividades_ejecutadas ADD CONSTRAINT indicador_actividad_escala_actividades_ejecutadas_fk
FOREIGN KEY (codigo_indicador_actividad_escala)
REFERENCES public.indicador_actividad_escala (codigo_indicador_actividad_escala)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_test ADD CONSTRAINT indicador_actividad_escala_indicador_test_fk
FOREIGN KEY (codigo_indicador_actividad_escala)
REFERENCES public.indicador_actividad_escala (codigo_indicador_actividad_escala)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comision_equipo ADD CONSTRAINT equipo_comision_equipo_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster ADD CONSTRAINT equipo_roster_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo_competencia ADD CONSTRAINT equipo_equipo_competencia_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_equipo ADD CONSTRAINT equipo_personal_equipo_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion ADD CONSTRAINT equipo_sesion_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion_ejecutada ADD CONSTRAINT equipo_sesion_ejecutada_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.test_evaluativo ADD CONSTRAINT equipo_test_evaluativo_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster_plan ADD CONSTRAINT equipo_roster_plan_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.horario_plan_temporada ADD CONSTRAINT equipo_horario_plan_temporada_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_foraneo ADD CONSTRAINT equipo_jugador_foraneo_fk
FOREIGN KEY (codigo_equipo)
REFERENCES public.equipo (codigo_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_foraneo_roster_competencia ADD CONSTRAINT jugador_refuerzo_jugador_refuerzo_roster_competencia_fk
FOREIGN KEY (cedula)
REFERENCES public.jugador_foraneo (cedula)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.indicador_test ADD CONSTRAINT test_evaluativo_indicador_test_fk
FOREIGN KEY (codigo_test)
REFERENCES public.test_evaluativo (codigo_test)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.test_jugador ADD CONSTRAINT indicador_test_test_jugador_fk
FOREIGN KEY (codigo_indicador_test)
REFERENCES public.indicador_test (codigo_indicador_test)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_planificada ADD CONSTRAINT sesion_actividad_planificada_fk
FOREIGN KEY (codigo_sesion)
REFERENCES public.sesion (codigo_sesion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_calendario ADD CONSTRAINT sesion_actividad_calendario_fk
FOREIGN KEY (codigo_sesion)
REFERENCES public.sesion (codigo_sesion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad_planificada ADD CONSTRAINT sesion_material_actividad_planificada_fk
FOREIGN KEY (codigo_sesion)
REFERENCES public.sesion (codigo_sesion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.sesion_ejecutada ADD CONSTRAINT sesion_sesion_ejecutada_fk
FOREIGN KEY (codigo_sesion)
REFERENCES public.sesion (codigo_sesion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion_utilizada ADD CONSTRAINT sesion_instalacion_utilizada_fk
FOREIGN KEY (codigo_sesion)
REFERENCES public.sesion (codigo_sesion)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.planificacion_actividad ADD CONSTRAINT instalacion_utilizada_planificacion_actividad_fk
FOREIGN KEY (codigo_instalacion_utilizada)
REFERENCES public.instalacion_utilizada (codigo_instalacion_utilizada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion_ejecutada ADD CONSTRAINT instalacion_utilizada_instalacion_ejecutada_fk
FOREIGN KEY (codigo_instalacion_utilizada)
REFERENCES public.instalacion_utilizada (codigo_instalacion_utilizada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad ADD CONSTRAINT instalacion_utilizada_actividad_fk
FOREIGN KEY (codigo_instalacion_utilizada)
REFERENCES public.instalacion_utilizada (codigo_instalacion_utilizada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.periodicidad ADD CONSTRAINT planificacion_mantenimiento_periodicidad_fk
FOREIGN KEY (codigo_planificacion_actividad)
REFERENCES public.planificacion_actividad (codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad_planificada ADD CONSTRAINT planificacion_mantenimiento_tarea_mantenimiento_planificada_fk
FOREIGN KEY (codigo_planificacion_actividad)
REFERENCES public.planificacion_actividad (codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad_planificada ADD CONSTRAINT planificacion_mantenimiento_material_mantenimiento_planifica583
FOREIGN KEY (codigo_planificacion_actividad)
REFERENCES public.planificacion_actividad (codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_actividad_planificada ADD CONSTRAINT planificacion_mantenimiento_empleado_mantenimiento_planifica195
FOREIGN KEY (codigo_planificacion_actividad)
REFERENCES public.planificacion_actividad (codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad ADD CONSTRAINT planificacion_actividad_actividad_fk
FOREIGN KEY (codigo_planificacion_actividad)
REFERENCES public.planificacion_actividad (codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.solicitud_mantenimiento ADD CONSTRAINT actividad_solicitud_mantenimiento_fk1
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad ADD CONSTRAINT actividad_material_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.resultado_actividad ADD CONSTRAINT actividad_resultado_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.estado_actividad ADD CONSTRAINT actividad_estado_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad ADD CONSTRAINT actividad_tarea_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.comision_actividad ADD CONSTRAINT actividad_comision_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividad_calendario ADD CONSTRAINT actividad_actividad_calendario_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_actividad ADD CONSTRAINT actividad_personal_actividad_fk
FOREIGN KEY (codigo_actividad)
REFERENCES public.actividad (codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad ADD CONSTRAINT personal_actividad_tarea_actividad_fk
FOREIGN KEY (cedula_rif, codigo_actividad)
REFERENCES public.personal_actividad (cedula_rif, codigo_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.tarea_actividad_planificada ADD CONSTRAINT personal_actividad_planificada_tarea_actividad_planificada_fk
FOREIGN KEY (cedula_rif, codigo_planificacion_actividad)
REFERENCES public.personal_actividad_planificada (cedula_rif, codigo_planificacion_actividad)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.actividades_ejecutadas ADD CONSTRAINT sesion_ejecutada_actividades_ejecutadas_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_personal_entrenamiento ADD CONSTRAINT sesion_ejecutada_asistencia_personal_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_jugador ADD CONSTRAINT sesion_ejecutada_asistencia_jugador_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.material_actividad ADD CONSTRAINT sesion_ejecutada_material_actividad_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_suplente ADD CONSTRAINT sesion_ejecutada_personal_suplente_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.instalacion_ejecutada ADD CONSTRAINT sesion_ejecutada_instalacion_ejecutada_fk
FOREIGN KEY (codigo_sesion_ejecutada)
REFERENCES public.sesion_ejecutada (codigo_sesion_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_jugador ADD CONSTRAINT actividades_ejecutadas_desempenno_jugador_fk
FOREIGN KEY (codigo_actividad_ejecutada)
REFERENCES public.actividades_ejecutadas (codigo_actividad_ejecutada)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_personal_entrenamiento ADD CONSTRAINT personal_equipo_asistencia_personal_entrenamiento_fk
FOREIGN KEY (codigo_personal_equipo)
REFERENCES public.personal_equipo (codigo_personal_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_equipo_juego ADD CONSTRAINT personal_equipo_personal_equipo_juego_fk
FOREIGN KEY (codigo_personal_equipo)
REFERENCES public.personal_equipo (codigo_personal_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.equipo_juego ADD CONSTRAINT equipo_competencia_equipo_juego_fk
FOREIGN KEY (codigo_equipo_competencia)
REFERENCES public.equipo_competencia (codigo_equipo_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_colectivo ADD CONSTRAINT equipo_juego_desempenno_colectivo_fk
FOREIGN KEY (codigo_equipo_juego)
REFERENCES public.equipo_juego (codigo_equipo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.personal_equipo_juego ADD CONSTRAINT equipo_juego_personal_equipo_juego_fk
FOREIGN KEY (codigo_equipo_juego)
REFERENCES public.equipo_juego (codigo_equipo_juego)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar_comision_equipo ADD CONSTRAINT comision_equipo_familiar_comision_equipo_fk
FOREIGN KEY (codigo_comision_equipo)
REFERENCES public.comision_equipo (codigo_comision_equipo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar_jugador ADD CONSTRAINT familiar_familiar_jugador_fk
FOREIGN KEY (cedula_familiar)
REFERENCES public.familiar (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.representante_plan ADD CONSTRAINT familiar_representante_plan_fk
FOREIGN KEY (cedula_familiar)
REFERENCES public.familiar (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.representante_jugador_plan ADD CONSTRAINT representante_plan_representante_jugador_plan_fk
FOREIGN KEY (cedula_familiar)
REFERENCES public.representante_plan (cedula_familiar)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_personal ADD CONSTRAINT jugador_documento_personal_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_social ADD CONSTRAINT jugador_dato_social_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.talla_por_jugador ADD CONSTRAINT jugador_talla_por_jugador_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster ADD CONSTRAINT jugador_roster_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_conducta ADD CONSTRAINT jugador_dato_conducta_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.retiro_traslado ADD CONSTRAINT jugador_retiro_traslado_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_medico ADD CONSTRAINT jugador_dato_medico_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_academico ADD CONSTRAINT jugador_dato_academico_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.dato_deportivo ADD CONSTRAINT jugador_dato_deportivo_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar_jugador ADD CONSTRAINT jugador_familiar_jugador_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_plan ADD CONSTRAINT jugador_jugador_plan_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.representante_jugador_plan ADD CONSTRAINT jugador_plan_representante_jugador_plan_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador_plan (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster_plan ADD CONSTRAINT jugador_plan_roster_plan_fk
FOREIGN KEY (cedula_rif)
REFERENCES public.jugador_plan (cedula_rif)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.test_jugador ADD CONSTRAINT roster_plan_test_jugador_fk
FOREIGN KEY (codigo_roster_plan)
REFERENCES public.roster_plan (codigo_roster_plan)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_jugador ADD CONSTRAINT roster_plan_asistencia_jugador_fk
FOREIGN KEY (codigo_roster_plan)
REFERENCES public.roster_plan (codigo_roster_plan)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.familiar_comision_equipo ADD CONSTRAINT familiar_jugador_familiar_comision_equipo_fk
FOREIGN KEY (codigo_familiar_jugador)
REFERENCES public.familiar_jugador (codigo_familiar_jugador)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.hospedaje ADD CONSTRAINT familiar_jugador_hospedaje_fk
FOREIGN KEY (codigo_familiar_jugador)
REFERENCES public.familiar_jugador (codigo_familiar_jugador)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.representante ADD CONSTRAINT familiar_jugador_representante_fk
FOREIGN KEY (codigo_familiar_jugador)
REFERENCES public.familiar_jugador (codigo_familiar_jugador)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.logro_por_jugador ADD CONSTRAINT dato_deportivo_logro_por_jugador_fk
FOREIGN KEY (codigo_dato_deportivo)
REFERENCES public.dato_deportivo (codigo_dato_deportivo)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_academico ADD CONSTRAINT dato_academico_documento_academico_fk
FOREIGN KEY (codigo_academico)
REFERENCES public.dato_academico (codigo_academico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.afeccion_jugador ADD CONSTRAINT dato_medico_afeccion_jugador_fk
FOREIGN KEY (codigo_dato_medico)
REFERENCES public.dato_medico (codigo_dato_medico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_medico ADD CONSTRAINT dato_medico_documento_medico_fk
FOREIGN KEY (codigo_dato_medico)
REFERENCES public.dato_medico (codigo_dato_medico)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.motivo_sancion ADD CONSTRAINT dato_conducta_motivo_sancion_fk
FOREIGN KEY (codigo_dato_conducta)
REFERENCES public.dato_conducta (codigo_dato_conducta)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_conducta ADD CONSTRAINT dato_conducta_documento_conducta_fk
FOREIGN KEY (codigo_dato_conducta)
REFERENCES public.dato_conducta (codigo_dato_conducta)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.ascenso ADD CONSTRAINT roster_ascenso_fk
FOREIGN KEY (codigo_roster)
REFERENCES public.roster (codigo_roster)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.test_jugador ADD CONSTRAINT roster_test_jugador_fk
FOREIGN KEY (codigo_roster)
REFERENCES public.roster (codigo_roster)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.asistencia_jugador ADD CONSTRAINT roster_asistencia_jugador_fk
FOREIGN KEY (codigo_roster)
REFERENCES public.roster (codigo_roster)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.roster_competencia ADD CONSTRAINT roster_roster_competencia_fk
FOREIGN KEY (codigo_roster)
REFERENCES public.roster (codigo_roster)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.line_up ADD CONSTRAINT roster_competencia_line_up_fk
FOREIGN KEY (codigo_roster_competencia)
REFERENCES public.roster_competencia (codigo_roster_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.jugador_foraneo_roster_competencia ADD CONSTRAINT roster_competencia_jugador_refuerzo_roster_competencia_fk
FOREIGN KEY (codigo_roster_competencia)
REFERENCES public.roster_competencia (codigo_roster_competencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_individual ADD CONSTRAINT line_up_desempenno_individual_fk
FOREIGN KEY (codigo_line_up)
REFERENCES public.line_up (codigo_line_up)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.desempenno_jugador ADD CONSTRAINT asistencia_jugador_desempeo_jugador_fk
FOREIGN KEY (codigo_asistencia)
REFERENCES public.asistencia_jugador (codigo_asistencia)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;

ALTER TABLE public.documento_ascenso ADD CONSTRAINT ascenso_documento_ascenso_fk
FOREIGN KEY (codigo_ascenso)
REFERENCES public.ascenso (codigo_ascenso)
ON DELETE NO ACTION
ON UPDATE NO ACTION
NOT DEFERRABLE;