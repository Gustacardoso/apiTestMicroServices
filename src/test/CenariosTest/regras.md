#  Scenario de test


Scenario: Possuir Cpf com restrição
dado que tenha parametro de cpf
quando tenha cpf com restrição
entao  tenho statusCode 200

Scenario: Possuir Cpf Sem restrição
dado que tenha parametro de cpf
quando tenha cpf sem restrição
entao  tenho statusCode 204

Scenario: Cadastro com sucesso
dado que tenha dados validos
entao temos statusCode 201

Scenario: Cpf ja existente
dado que temos cpf cadastrado
entao temos statusCode 400
E mensagem CPF duplicado

Scenario: Problema no cadastro

Scenario: Atualização de dados
dado que tenha cpf registrado
entao temos statusCode 200

Scenario: atulização Cpf não registrado
dado que nao registro do cpf
entao temos statusCode 404

Scenario: Lista dados cadastrados por cpf
dado que temos cpf registrados
quando fizemos uma chamada get com parametro cpf
entao temos dados do cpf

Scenario: Listar todos dados cadastrados
dado que temos registros cadastrados 
quando fizemos requisição via get
entao temos statusCode 200

Scenario: Lista vazia cadastro
dado que temos retorno vazio de cadastro
quando fizemos requisição via get
entao temos statusCode 204

Scenario: cpf não cadastrado
dado que temos cpf não cadastro
quando fizemos requisição via get
entao temos statusCode 404

Scenario: delete por id cadastrado
dado que temos que remover pelo id
quando fizemos requisição via delete
entao temos statusCode 200 

