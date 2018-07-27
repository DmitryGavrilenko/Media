
CREATE EXTENSION pgcrypto;

CREATE TABLE IF NOT EXISTS n_user(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  password VARCHAR(200),
  name VARCHAR(200),
  email VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS img(
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  path VARCHAR(200) NOT NULL,
  user_id UUID references  n_user
);