-- *************************************************************
-- DELETANDO TODAS AS TABELAS
-- *************************************************************

DROP TABLE IF EXISTS area_voluntario CASCADE;
DROP TABLE IF EXISTS disponibilidade CASCADE;
DROP TABLE IF EXISTS endereco CASCADE;
DROP TABLE IF EXISTS contato CASCADE;
DROP TABLE IF EXISTS voluntario CASCADE;
DROP TABLE IF EXISTS area_atuacao CASCADE;

-- *************************************************************
-- RECRIANDO AS TABELAS
-- *************************************************************

CREATE TABLE area_atuacao (

    id BIGSERIAL PRIMARY KEY,

    nome_area VARCHAR(100) NOT NULL UNIQUE

);

CREATE TABLE voluntario (

    id BIGSERIAL PRIMARY KEY,

    nome VARCHAR(150) NOT NULL,

    profissao VARCHAR(100) NOT NULL,

    cpf VARCHAR(11) NOT NULL UNIQUE,

    registro_conselho VARCHAR(50),

    horas_semanais_disponiveis INTEGER NOT NULL
        CHECK (horas_semanais_disponiveis > 0),

    data_cadastro DATE NOT NULL DEFAULT CURRENT_DATE,

    data_criacao TIMESTAMP,

    data_atualizacao TIMESTAMP,

    status VARCHAR(20) NOT NULL
        CHECK (
            status IN (
                'ATIVO',
                'DESATIVADO',
                'PENDENTE'
            )
        )

);

CREATE TABLE contato (

    id BIGSERIAL PRIMARY KEY,

    voluntario_id BIGINT NOT NULL,

    tipo VARCHAR(20) NOT NULL
        CHECK (
            tipo IN (
                'EMAIL',
                'TELEFONE',
                'OUTRO'
            )
        ),

    contato VARCHAR(150) NOT NULL,

    descricao VARCHAR(255),

    CONSTRAINT fk_contato_voluntario
        FOREIGN KEY (voluntario_id)
        REFERENCES voluntario(id)
        ON DELETE CASCADE

);

CREATE TABLE endereco (

    id BIGSERIAL PRIMARY KEY,

    voluntario_id BIGINT NOT NULL,

    cep VARCHAR(8) NOT NULL,

    logradouro VARCHAR(200) NOT NULL,

    numero VARCHAR(10) NOT NULL,

    complemento VARCHAR(100),

    bairro VARCHAR(100) NOT NULL,

    cidade VARCHAR(100) NOT NULL,

    CONSTRAINT fk_endereco_voluntario
        FOREIGN KEY (voluntario_id)
        REFERENCES voluntario(id)
        ON DELETE CASCADE

);

CREATE TABLE disponibilidade (

    id BIGSERIAL PRIMARY KEY,

    voluntario_id BIGINT NOT NULL,

    dia_semana VARCHAR(20) NOT NULL
        CHECK (
            dia_semana IN (
                'SEGUNDA',
                'TERCA',
                'QUARTA',
                'QUINTA',
                'SEXTA'
            )
        ),

    horario TIME NOT NULL,

    CONSTRAINT fk_disponibilidade_voluntario
        FOREIGN KEY (voluntario_id)
        REFERENCES voluntario(id)
        ON DELETE CASCADE

);

CREATE TABLE area_voluntario (

    id_voluntario BIGINT NOT NULL,

    id_area BIGINT NOT NULL,

    PRIMARY KEY (id_voluntario, id_area),

    CONSTRAINT fk_area_voluntario_voluntario
        FOREIGN KEY (id_voluntario)
        REFERENCES voluntario(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_area_voluntario_area
        FOREIGN KEY (id_area)
        REFERENCES area_atuacao(id)

);

-- *************************************************************
-- ÍNDICES
-- *************************************************************

CREATE INDEX idx_voluntario_cpf
    ON voluntario(cpf);

CREATE INDEX idx_contato_voluntario
    ON contato(voluntario_id);

CREATE INDEX idx_endereco_voluntario
    ON endereco(voluntario_id);

CREATE INDEX idx_disponibilidade_voluntario
    ON disponibilidade(voluntario_id);

CREATE INDEX idx_area_voluntario
    ON area_voluntario(id_voluntario);

-- *************************************************************
-- DADOS INICIAIS
-- *************************************************************

INSERT INTO area_atuacao (nome_area) VALUES
('Psicologia'),
('Odontologia'),
('Psicopedagogia'),
('Neuropsicopedagogia'),
('Educação Física'),
('Fisioterapia'),
('Medicina - Pneumologia'),
('Nutrição'),
('Assistência Social'),
('Fonoaudiologia'),
('Outras / Apoio');

-- *************************************************************
-- VIEW 01: Voluntários com contagem de contatos e áreas
-- *************************************************************

CREATE OR REPLACE VIEW vw_voluntarios_resumo AS
SELECT
    v.id,
    v.nome,
    v.profissao,
    v.cpf,
    v.status,
    v.data_cadastro,
    COUNT(DISTINCT c.id)  AS total_contatos,
    COUNT(DISTINCT av.id_area) AS total_areas
FROM voluntario v
LEFT JOIN contato c ON c.voluntario_id = v.id
LEFT JOIN area_voluntario av ON av.id_voluntario = v.id
GROUP BY v.id, v.nome, v.profissao, v.cpf, v.status, v.data_cadastro;

-- *************************************************************
-- VIEW 02: Voluntários ativos com suas áreas de atuação
-- *************************************************************

CREATE OR REPLACE VIEW vw_voluntarios_ativos AS
SELECT
    v.id,
    v.nome,
    v.profissao,
    v.status,
    STRING_AGG(a.nome_area, ', ') AS areas_atuacao
FROM voluntario v
LEFT JOIN area_voluntario av ON av.id_voluntario = v.id
LEFT JOIN area_atuacao a ON a.id = av.id_area
WHERE v.status = 'ATIVO'
GROUP BY v.id, v.nome, v.profissao, v.status;

-- *************************************************************
-- VIEW 03: Painel de disponibilidade semanal
-- *************************************************************
CREATE OR REPLACE VIEW vw_disponibilidade_semanal AS
SELECT
    v.nome,
    v.profissao,
    d.dia_semana,
    d.horario
FROM disponibilidade d
JOIN voluntario v ON v.id = d.voluntario_id
WHERE v.status = 'ATIVO'
ORDER BY d.dia_semana, d.horario;

-- *************************************************************
-- PROCEDURES 01: Atualizar status de um voluntário
-- *************************************************************

CREATE OR REPLACE PROCEDURE sp_atualizar_status(
    p_id BIGINT,
    p_novo_status VARCHAR(20)
)
LANGUAGE plpgsql AS $$
BEGIN
    IF p_novo_status NOT IN ('ATIVO', 'PENDENTE', 'DESATIVADO') THEN
        RAISE EXCEPTION 'Status invalido: %', p_novo_status;
    END IF;

    UPDATE voluntario
    SET status = p_novo_status,
        data_atualizacao = NOW()
    WHERE id = p_id;

    IF NOT FOUND THEN
        RAISE EXCEPTION 'Voluntario com id % nao encontrado', p_id;
    END IF;
END;
$$;

-- *************************************************************
-- PROCEDURES 02: Cadastrar voluntário com contato inicial
-- *************************************************************
CREATE OR REPLACE PROCEDURE sp_cadastrar_voluntario(
    p_nome           VARCHAR,
    p_profissao      VARCHAR,
    p_cpf            VARCHAR,
    p_horas          INTEGER,
    p_tipo_contato   VARCHAR,
    p_valor_contato  VARCHAR
)
LANGUAGE plpgsql AS $$
DECLARE
    v_id BIGINT;
BEGIN
    INSERT INTO voluntario (nome, profissao, cpf, horas_semanais_disponiveis, status, data_criacao)
    VALUES (p_nome, p_profissao, p_cpf, p_horas, 'PENDENTE', NOW())
    RETURNING id INTO v_id;

    INSERT INTO contato (voluntario_id, tipo, contato)
    VALUES (v_id, p_tipo_contato, p_valor_contato);

    RAISE NOTICE 'Voluntario cadastrado com id %', v_id;
END;
$$;

-- *************************************************************
-- TRIGGER 01: Registrar log de operações (INSERT, UPDATE, DELETE)
-- *************************************************************

CREATE TABLE IF NOT EXISTS log_voluntario (
    id          BIGSERIAL PRIMARY KEY,
    voluntario_id BIGINT,
    operacao    VARCHAR(10) NOT NULL,  -- INSERT, UPDATE, DELETE
    status_anterior VARCHAR(20),
    status_novo     VARCHAR(20),
    data_evento TIMESTAMP NOT NULL DEFAULT NOW(),
    usuario     TEXT DEFAULT current_user
);

CREATE OR REPLACE FUNCTION fn_log_voluntario()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        INSERT INTO log_voluntario (voluntario_id, operacao, status_novo)
        VALUES (NEW.id, 'INSERT', NEW.status);

    ELSIF TG_OP = 'UPDATE' THEN
        INSERT INTO log_voluntario (voluntario_id, operacao, status_anterior, status_novo)
        VALUES (NEW.id, 'UPDATE', OLD.status, NEW.status);

    ELSIF TG_OP = 'DELETE' THEN
        INSERT INTO log_voluntario (voluntario_id, operacao, status_anterior)
        VALUES (OLD.id, 'DELETE', OLD.status);
    END IF;

    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_log_voluntario
AFTER INSERT OR UPDATE OR DELETE ON voluntario
FOR EACH ROW EXECUTE FUNCTION fn_log_voluntario();

-- *************************************************************
-- TRIGGER 02: Preencher datas de auditoria automaticamente
-- *************************************************************
CREATE OR REPLACE FUNCTION fn_auditoria_voluntario()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    IF TG_OP = 'INSERT' THEN
        NEW.data_criacao     := NOW();
        NEW.data_atualizacao := NOW();
    ELSIF TG_OP = 'UPDATE' THEN
        NEW.data_atualizacao := NOW();
    END IF;
    RETURN NEW;
END;
$$;

CREATE TRIGGER trg_auditoria_voluntario
BEFORE INSERT OR UPDATE ON voluntario
FOR EACH ROW EXECUTE FUNCTION fn_auditoria_voluntario();

-- *************************************************************
-- TRIGGER 03: Bloquear exclusão de voluntário com status ATIVO
-- *************************************************************
CREATE OR REPLACE FUNCTION fn_bloquear_exclusao_ativo()
RETURNS TRIGGER LANGUAGE plpgsql AS $$
BEGIN
    IF OLD.status = 'ATIVO' THEN
        RAISE EXCEPTION 'Nao e possivel excluir um voluntario ATIVO. Desative-o primeiro.';
    END IF;
    RETURN OLD;
END;
$$;

CREATE TRIGGER trg_bloquear_exclusao_ativo
BEFORE DELETE ON voluntario
FOR EACH ROW EXECUTE FUNCTION fn_bloquear_exclusao_ativo();
