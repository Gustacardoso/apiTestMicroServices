#  cenário de teste

    cenário: Possuir Cpf com restrição
        dado que tenha parametro de cpf
        quando tenha cpf com restrição
        entao tenho statusCode 200

    cenário: Possuir Cpf Sem restrição
        dado que tenha parametro de cpf
        quando tenha cpf sem restrição
        entao  tenho statusCode 204
        
    cenário: Cadastro com sucesso
        dado que tenha dados validos
        entao temos statusCode 201
        
    cenário: Cpf ja existente
        dado que temos cpf cadastrado
        entao temos statusCode 400
        E mensagem CPF duplicado
        
    cenário: Atualização de dados
        dado que tenha cpf registrado
        entao temos statusCode 200
        
    cenário: atulização Cpf não registrado
        dado que nao registro do cpf
        entao temos statusCode 404
        
    cenário: Lista dados cadastrados por cpf
        dado que temos cpf registrados
        quando fizemos uma chamada get com parametro cpf
        entao temos dados do cpf
        
    cenário: Listar todos dados cadastrados
        dado que temos registros cadastrados 
        quando fizemos requisição via get
        entao temos statusCode 200
        
    cenário: Lista vazia cadastro
        dado que temos retorno vazio de cadastro
        quando fizemos requisição via get
        entao temos statusCode 204
        
    cenário: cpf não cadastrado
        dado que temos cpf não cadastro
        quando fizemos requisição via get
        entao temos statusCode 404
        
    cenário: delete por id cadastrado
        dado que temos que remover pelo id
        quando fizemos requisição via delete
        entao temos statusCode 200 

