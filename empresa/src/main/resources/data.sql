INSERT INTO setores( nome) VALUES ('Recursos Humanos');
INSERT INTO setores( nome) VALUES ('Operações');
INSERT INTO setores( nome) VALUES ('Endomarketing');


INSERT INTO cargos(nome , setor_id ) VALUES ('Diretor de Recursos Humanos', 1);
INSERT INTO cargos(nome , setor_id  ) VALUES ('Gerente de Recursos Humanos', 1);
INSERT INTO cargos(nome , setor_id  ) VALUES ('Coordenador de Recursos Humanos', 1);
INSERT INTO cargos(nome , setor_id  ) VALUES ('Auxiliar de RH', 1 );
INSERT INTO cargos(nome , setor_id  ) VALUES ('Analista de Recursos Humanos', 1);

INSERT INTO trabalhadores (nome , cpf, sexo, cargo_id , setor_id ) VALUES ('Maria Amélia Souza','23612074008', 'FEMININO', 1 , 1 );
INSERT INTO trabalhadores (nome , cpf, sexo, cargo_id , setor_id ) VALUES ('Renato Gadelha','38035500090', 'MASCULINO', 2, 1 );
INSERT INTO trabalhadores (nome , cpf, sexo, cargo_id , setor_id ) VALUES ('Carol Castro da Silva','56371397095', 'FEMININO',  3, 1 );
INSERT INTO trabalhadores (nome , cpf, sexo, cargo_id , setor_id ) VALUES ('Regina Tavares Moraes','01239266022',  'FEMININO',  4 , 1 );
INSERT INTO trabalhadores (nome , cpf, sexo, cargo_id , setor_id ) VALUES ('Regis Valentin Gyenge','53666198074', 'MASCULINO',  5 , 1 );