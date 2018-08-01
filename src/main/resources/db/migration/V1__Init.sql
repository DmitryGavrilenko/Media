
CREATE EXTENSION if not exists pgcrypto;

CREATE TABLE IF NOT EXISTS n_user(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  created_time TIMESTAMP WITHOUT TIME ZONE NOT NULL ,
  updated_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  password VARCHAR(200),
  name VARCHAR(200),
  email VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS img(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  created_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  updated_time TIMESTAMP WITHOUT TIME ZONE NOT NULL,
  path VARCHAR(200) NOT NULL,
  user_id UUID references  n_user
);