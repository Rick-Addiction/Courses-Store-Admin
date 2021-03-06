DROP TABLE IF EXISTS TB_CUSTOMER;

CREATE TABLE TB_CUSTOMER (
  id_customer VARCHAR(50) PRIMARY KEY,
  first_name VARCHAR(50) NOT NULL,
  last_name VARCHAR(150) NOT NULL,
  doc_type VARCHAR(30) NOT NULL,
  doc_number VARCHAR(30) NOT NULL,
  CONSTRAINT AK_docID UNIQUE(doc_type, doc_number)
);

INSERT INTO TB_CUSTOMER (id_customer,first_name, last_name, doc_type, doc_number) VALUES
  ('5d616c6d-653b-45f0-9e92-165933d237a9','João', 'Carvalho', 'CPF', '025.719.958-63'),
  ('9b064a59-928f-414f-9b5f-e91e99ce2fca','Thiago', 'Borges', 'CPF', '185.323.128-29'),
  ('d6cf648a-cfd1-468d-988c-03d983b356d4','Arnaldo', 'Mendonça', 'CPF', '715.174.388-29'),
  ('350f347b-25cd-4b79-b9a6-a049f1a94ebf','Cristina', 'Freitas', 'CPF', '236.837.328-49'),
  ('3ef36fc9-f053-4777-a7fd-42054553b77a','Fernando', 'Moura', 'RG', '28.226.173-4'),
  ('f44abe7a-eaf1-4bd1-b6f4-272640eccb86','Everson', 'Santos', 'RG', '33.044.586-8'),
  ('55c92ca2-4bf8-475d-a788-b9eb3586456a','Sabrina', 'Machado', 'CPF', '095.494.898-09');