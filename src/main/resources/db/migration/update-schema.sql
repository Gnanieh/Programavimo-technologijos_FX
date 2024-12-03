CREATE TABLE Comment
(
    id               INT AUTO_INCREMENT NOT NULL,
    DTYPE            VARCHAR(31) NULL,
    title            VARCHAR(255) NULL,
    body             VARCHAR(255) NULL,
    timestamp        datetime NULL,
    parentComment_id INT NULL,
    publication_id   INT NULL,
    client_id        INT NULL,
    chat_id          INT NULL,
    commentOwner_id  INT NULL,
    CONSTRAINT pk_comment PRIMARY KEY (id)
);

CREATE TABLE PeriodicRecord
(
    id              INT AUTO_INCREMENT NOT NULL,
    user_id         INT NULL,
    publication_id  INT NULL,
    transactionDate date NULL,
    status          SMALLINT NULL,
    CONSTRAINT pk_periodicrecord PRIMARY KEY (id)
);

CREATE TABLE Publication
(
    id                INT AUTO_INCREMENT NOT NULL,
    DTYPE             VARCHAR(31) NULL,
    title             VARCHAR(255) NULL,
    author            VARCHAR(255) NULL,
    publisher         VARCHAR(255) NULL,
    year              date NULL,
    type              VARCHAR(255) NULL,
    owner_id          INT NULL,
    client_id         INT NULL,
    publicationStatus SMALLINT NULL,
    requestDate       date NULL,
    illustrator       VARCHAR(255) NULL,
    originalLanguage  VARCHAR(255) NULL,
    volumeNumber      INT    NOT NULL,
    isColor           BIT(1) NOT NULL,
    isbn              VARCHAR(255) NULL,
    pageCount         INT    NOT NULL,
    summary           VARCHAR(255) NULL,
    CONSTRAINT pk_publication PRIMARY KEY (id)
);

CREATE TABLE User
(
    id          INT AUTO_INCREMENT NOT NULL,
    DTYPE       VARCHAR(31) NULL,
    login       VARCHAR(255) NULL,
    password    VARCHAR(255) NULL,
    name        VARCHAR(255) NULL,
    surname     VARCHAR(255) NULL,
    dateCreated date NULL,
    email       VARCHAR(255) NULL,
    address     VARCHAR(255) NULL,
    birthDate   date NULL,
    phoneNumber VARCHAR(255) NULL,
    CONSTRAINT pk_user PRIMARY KEY (id)
);

ALTER TABLE User
    ADD CONSTRAINT uc_user_login UNIQUE (login);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_CHAT FOREIGN KEY (chat_id) REFERENCES Comment (id);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_CLIENT FOREIGN KEY (client_id) REFERENCES User (id);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_COMMENTOWNER FOREIGN KEY (commentOwner_id) REFERENCES User (id);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_PARENTCOMMENT FOREIGN KEY (parentComment_id) REFERENCES Comment (id);

ALTER TABLE Comment
    ADD CONSTRAINT FK_COMMENT_ON_PUBLICATION FOREIGN KEY (publication_id) REFERENCES Publication (id);

ALTER TABLE PeriodicRecord
    ADD CONSTRAINT FK_PERIODICRECORD_ON_PUBLICATION FOREIGN KEY (publication_id) REFERENCES Publication (id);

ALTER TABLE PeriodicRecord
    ADD CONSTRAINT FK_PERIODICRECORD_ON_USER FOREIGN KEY (user_id) REFERENCES User (id);

ALTER TABLE Publication
    ADD CONSTRAINT FK_PUBLICATION_ON_CLIENT FOREIGN KEY (client_id) REFERENCES User (id);

ALTER TABLE Publication
    ADD CONSTRAINT FK_PUBLICATION_ON_OWNER FOREIGN KEY (owner_id) REFERENCES User (id);