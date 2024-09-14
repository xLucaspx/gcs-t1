[Trello - Tarefas](https://trello.com/b/olub9SuD).

## Como clonar o repositório

Na página inicial do repositório há um botão em destaque com o texto **<> Code**; ao clicar neste botão, devemos escolher entre duas opções: _HTTPS_ ou _SSH_.

Caso já possua uma chave _SSH_ configurada no Git da sua máquina, utilize a segunda opção e copie a chave _SSH_ do repositório.

Se você não tem uma chave _SSH_ configurada -- ou não sabe se tem -- utilize a opção _HTTPS_ e copie o endereço do repositório.

No terminal _Git Bash_ da sua máquina, navegue até a pasta na qual você deseja clonar o projeto e digite o comando `git clone`, colando o endereço ou chave copiadas do repositório.

E.g.: a sequência de comandos abaixo, executada no _Git Bash_, irá clonar o projeto na área de trabalho (_Desktop_) do usuário, dentro de uma pasta com o mesmo nome do repositório (_gcs-t1_), utilizando o protocolo _HTTPS_.

```bash
cd ~/Desktop
git clone https://github.com/xLucaspx/gcs-t1.git
```

Dentro da pasta clonada, o repositório já estará configurado, bastando apenas criar uma nova _branch_ e iniciar o desenvolvimento da sua _feature_. Isso pode ser realizado com o comando `git switch -C feature/nova-branch`,
onde `nova-branch` será o nome da feature que você irá desenvolver. Tente criar nomes curtos mas descritivos; atenção para a flag `-C` (com a letra "C" maíuscula), ela será a responsável por criar a _branch_, enquanto o comando
`switch` irá transferir a `head` para a _branch_ criada.

## Boas Práticas

1. **Sempre documentar todos os métodos**; também é boa prática documentar as classes (não se esqueça do atributo `@author`) e os atributos mais complexos;
2. Atualizar o diagrama de classes com as modificações realizadas;
3. **RAII** (_Resource Acaquisition is Inicialization_): Iniciar os atributos da classe no construtor;
4. Não criar _getters_ e _setters_ desnecessários (isso fere o encapsulamento e a imutabilidade, devemos criar apenas os métodos que realmente vamos utilizar);
5. Quando um método retorna uma lista, devemos sempre torná-la imutável com `Collections.unmodifiableList()`, passando a lista em questão por parâmetro;
6. **Não commitar na _branch_ main**;
7. **Não commitar código que não funciona**!

Após terminar o desenvolvimento da sua _feature_ e _commitar_ localmente, faça o _push_ para o repositório remoto (`git push origin feature/nome-da-branch`) e abra um `pull request`. Se necessitar de auxílio, não hesite em
entrar em contato.
