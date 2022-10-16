DROP TABLE IF EXISTS LOCATIONS cascade;
DROP TABLE IF EXISTS REQUESTS cascade;
DROP TABLE IF EXISTS EVENTS cascade;
DROP TABLE IF EXISTS COMPILATIONS cascade;
DROP TABLE IF EXISTS CATEGORIES cascade;
DROP TABLE IF EXISTS USERS;
DROP TABLE IF EXISTS COMPILATIONS_EVENTS;

CREATE TABLE IF NOT EXISTS COMPILATIONS_EVENTS
(
    id             BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    compilation_id INTEGER,
    events_id      INTEGER
);


CREATE TABLE IF NOT EXISTS USERS
(
    id    BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name  VARCHAR(255)                            NOT NULL,
    email VARCHAR(512)                            NOT NULL,
    UNIQUE (email),
    CONSTRAINT owner_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS CATEGORIES
(
    id   BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    name VARCHAR(255)                            NOT NULL,
    UNIQUE (name),

    CONSTRAINT category_id PRIMARY KEY (id)
);


CREATE TABLE IF NOT EXISTS LOCATIONS
(
    id  BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    lat double,
    lon double,

    CONSTRAINT location_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS EVENTS
(
    id                 BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    annotation         VARCHAR(2000)                           NOT NULL,
    description        VARCHAR(7000)                           NOT NULL,
    confirmed_requests INTEGER,
    created_on         TIMESTAMP,
    event_date         TIMESTAMP,
    paid               BOOLEAN,
    participant_limit  INTEGER,
    published_on       TIMESTAMP,
    request_moderation BOOLEAN,
    title              VARCHAR(2000)                           NOT NULL,
    state              VARCHAR(15) ,
    views              INTEGER,

    CONSTRAINT event_id PRIMARY KEY (id),
    category_id        INTEGER REFERENCES CATEGORIES (id),
    location_id        INTEGER REFERENCES LOCATIONS (id),
    initiator_id       INTEGER REFERENCES USERS (id)

);

CREATE TABLE IF NOT EXISTS COMPILATIONS
(
    id     BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    pinned BOOLEAN,
    title  VARCHAR(255)                            NOT NULL

);



CREATE TABLE IF NOT EXISTS REQUESTS
(
    id       BIGINT GENERATED BY DEFAULT AS IDENTITY NOT NULL,
    created  TIMESTAMP,
    status   VARCHAR(15)                             NOT NULL,

    event_id INTEGER REFERENCES EVENTS (id),
    owner_id INTEGER REFERENCES USERS (id),
    UNIQUE (owner_id)
);




