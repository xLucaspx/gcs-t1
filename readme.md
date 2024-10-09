# Sistema de gerenciamento de itens de jogadores

Projeto desenvolvido durante a cadeira de Gerenciamento de Configuração de _Software_, visando a prática de Git em
times. Foi solicitada a criação de um repositório para o desenvolvimento de um sistema de gerenciamento de itens entre
jogadores; o sistema foi implementado em Java, utilizando o _console_ para interação com o usuário e sem persistência de
dados. Para conveniência, o sistema inicia com dados já preenchidos; estes dados são mantidos em um arquivo chamado
[_seeder.txt_](./resources/seeder.txt) no diretório [_resources_](./resources), sendo lidos, interpretados e armazenados
na memória no início de cada execução. Mais informações sobre o preenchimento dos dados podem ser encontradas abaixo, na
seção [Preenchimento de dados](#preenchimento-de-dados).

[Trello - Tarefas](https://trello.com/b/olub9SuD).

## Índice

- [Como clonar o repositório](#como-clonar-o-repositório);
- [Processo de _branching_](#processo-de-_branching_);
- [Boas práticas](#boas-práticas);
- [Preenchimento de dados](#preenchimento-de-dados);
- [Utilização e funcionalidades](#utilização-e-funcionalidades).

## Como clonar o repositório

Na página inicial do repositório há um botão em destaque com o texto **<> Code**; ao clicar neste botão, devemos
escolher entre duas opções: _HTTPS_ ou _SSH_.

Caso já possua uma chave _SSH_ configurada no Git da sua máquina, utilize a segunda opção e copie a chave _SSH_
do repositório.

Se você não tem uma chave _SSH_ configurada — ou não sabe se tem — utilize a opção _HTTPS_ e copie o endereço
do repositório.

No terminal _Git Bash_ da sua máquina, navegue até a pasta na qual você deseja clonar o projeto e digite o comando
`git clone`, colando o endereço ou chave copiadas do repositório.

E.g.: a sequência de comandos abaixo, executada no _Git Bash_, irá clonar o projeto na área de trabalho (_Desktop_)
do usuário, dentro de uma pasta com o mesmo nome do repositório (_gcs-t1_), utilizando o protocolo _HTTPS_.

```bash
cd ~/Desktop
git clone https://github.com/xLucaspx/gcs-t1.git
```

Dentro da pasta clonada, o repositório já estará configurado, bastando apenas criar uma nova _branch_ a partir da
_branch_ `dev` e iniciar o desenvolvimento da sua _feature_. Isso pode ser realizado com os comandos `git switch dev`
e `git switch -C feature/nova-branch`, onde `nova-branch` será o nome da feature que você irá desenvolver. Tente criar
nomes curtos, mas descritivos; atenção para a flag `-C` (com a letra "C" maiúscula), ela será a responsável por criar
a _branch_, enquanto o comando `switch` irá transferir a `head` para a _branch_ desejada.

**Importante**: crie sua _branch_ a partir da _branch_ `dev`.

## Processo de _branching_

Sempre utilizar prefixos nos nomes das _branches_ para identificar rapidamente seu propósito, conforme segue:

- **_Feature_**: para o desenvolvimento de novas _features_/funcionalidades será utilizado o prefixo `feature/`;
- **Documentação**: para atividades relacionadas à documentação será utilizado o prefixo `docs/`;
- **_Bugfix_**: para a correção de _bugs_ e defeitos no código será utilizado o prefixo `bugfix/`;
- **_Hotfix_**: para correções críticas, diretamente na _branch_ principal, será utilizado o prefixo `hotfix/`;
- **_Release_**: para cada versão da aplicação, antes de fazer o _merge_ com a _branch_ principal será utilizada uma
- _branch_ com o prefixo `release/`.

Priorizar nomes descritivos e concisos, que representem o trabalho realizado na _branch_.

## Boas Práticas

1. **Sempre documentar todos os métodos**; também é boa prática documentar as classes (não se esqueça do atributo
	 `@author`) e os atributos mais complexos;
2. Atualizar o diagrama de classes com as modificações realizadas;
3. **RAII** (_Resource Acquisition is Initialization_): Inicializar os atributos da classe no construtor;
4. Não criar _getters_ e _setters_ desnecessários (isso fere o encapsulamento e a imutabilidade, devemos criar apenas
	 os métodos que realmente vamos utilizar);
5. Quando um método retorna uma lista, devemos torná-la imutável com `Collections.unmodifiableList()`, passando a lista
	 em questão por parâmetro;
6. Quando utilizar a classe `Scanner`, utilize **apenas** o método `nextLine()`; se necessário ler um número, utilize
	 os métodos de conversão das classes _wrappers_ (e.g. `Integer.parseInt()`, `Double.parseDouble` etc.);
7. **Não _commitar_ na _branch_ `main`**;
8. **Não _commitar_ código que não funciona**!

Após terminar o desenvolvimento na sua _branch_ e _commitar_ localmente, faça o _push_ para o repositório remoto
(`git push origin prefixo/nome-da-branch`) e abra um **_pull request_**`. Se necessitar de auxílio, não hesite em entrar
em contato.

## Preenchimento de dados

Um dos requisitos de sistema é que o mesmo seja inicializado com dados já inseridos. A maneira escolhida para alcançar
este objetivo foi a criação de um arquivo que contém, linha a linha, os dados dos objetos que devem ser criados e
inseridos no sistema. Um método da classe de aplicação deverá ler este arquivo, instânciar os objetos lá dispostos e
armazená-los corretamente nas classes _handler_.

O arquivo padrão será armazenado em [_resources/seeder.txt_](./resources/seeder.txt); dependendo das configurações de
execução e do diretório no qual o código será executado (_working directory_), o sistema pode não encontrar o arquivo no
caminho especificado. Neste caso, é possível alterar a constante `CAMINHO_ARQUIVO_SEEDER` na classe `App` para o caminho
correto. Outra possibilidade é passar o caminho do arquivo _seeder_ como argumento ao executar o programa, não sendo
necessário modificar o código; e.g.: `Java Main ../resources/seeder.txt`, se o seu _working directory_ for a pasta _src_
ao invés da pasta raíz do projeto.

O arquivo _seeder_ terá a seguinte estrutura:

- Número `x` de jogadores que serão cadastrados;
- `x` linhas contendo as informações de cada jogador no formato `nome,email,pin`;
- Número `y` de itens que serão cadastrados;
- `y` linhas contendo as informações de cada item no formato `id,nome,descricao,categoria,preco,email-jogador`;
- Número `z` de propostas que serão cadastradas;
- `z` linhas contendo as informações de cada proposta no formato
	`email-solicitante,id-item-solicitante,email-solicitado,id-item-solicitado,data,status`.

Excepcionalmente na inserção manual de dados, o _ID_ dos itens será informado; isso se dá visando garantir a
consistência dos dados inseridos. De mesmo modo, as propostas de inserção manual devem receber a data e o _status_ no
construtor. (No arquivo, toma-se como garantido, no caso das propostas com _status_ `CONFIRMADA`, que os itens
envolvidos já estão na posse dos donos corretos, i.e. ao confirmar a troca o item oferecido pelo solicitante foi para o
solicitado e vice-versa).

## Utilização e funcionalidades

O sistema possui uma tela inicial com opções de cadastro e _login_; conforme solicitado no documento do trabalho, também
há uma forma de se autenticar no sistema apenas selecionando o jogador desejado. Na tela inicial, aparecem as
informações do jogador (nome e _e-mail_) e notificações de troca, que informam quantas propostas recebidas e quantas
propostas realizadas estão em aberto.

É possível listar os itens em posse do jogador autenticado, cadastrar um novo item ou excluir um item. Também é possível
listar os itens disponíveis, i.e., os itens em posse de outros jogadores, e buscar itens por _ID_, nome, descrição,
categoria ou preço.

Para abrir uma nova proposta de troca informa-se o _ID_ do item oferecido e o _ID_ do item solicitado; após, é
apresentada a proposta e o jogador confirma se deseja efetivamente realizá-la. É possível listar as propostas recebidas,
propostas recebidas em aberto, propostas realizadas e propostas realizadas em aberto. O jogador pode, ainda, aceitar ou
recusar propostas recebidas em aberto e cancelar propostas realizadas em aberto.

Jogadores autenticados podem, também, visualizar informações do sistema, como a quantidade de usuários cadastrados, a
quantidade de itens cadastrados, valor total e preço médio de itens, quantidade de propostas no sistema etc. Por fim,
é possível realizar _logout_ e se autenticar novamente ou encerrar o sistema; nesta última opção, os dados armazenados e
modificados durante a execução são perdidos (não há persistência de dados).

É realizado um tratamento básico de exceções para impedir que a aplicação quebre com alguma entrada inválida. Para
inserir valores numéricos não-inteiros utilize sempre o ponto decimal. 
