# 📝 Análise léxica
Repositório para o desenvolvimento da avaliação 1, referente a disciplina de Compiladores

Trabalho apresentado como requisito parcial de avaliação.
Utilização do conhecimentos de Linguagem de Programação, sendo Java a escolhida.


## 🚶 Passos a serem realizadas:
- [✔️] - Fazer a leitura do arquivo `.pix`;
- [✔️] - Criar a primeira lista de tokens válidos; -> Arquivos gerados: tokens.json e Token.java
- [✔️] - Ler todos os símbolos do código de entrada;
- [✔️] - Reunir todos os lexemas menos espaços; -> Função LeitorTxt.separeteText(.
- [✔️] - Melhoria na função LeitorTxt.separeteText(...) para levar em conta fatores importantes
além dos espaços;
- [✔️] - Atualizar lista de tokens com base no código de entrada;
- [✔️] - Gerar um arquivo no formato csv. O conteúdo do arquivo é um
texto no formato CSV contendo a tabela de símbolos;
- [✔️] - Subistituir os lexemas por ID, inserir o ID nos Tokens; Retorno de Token.getTokenFromJson() contém a lista de tokens com ID atualizados
- [✔️] - Gerar um arquivo no formato `.pixobj`. O conteúdo do arquivo é
um texto contendo o código tokenizado; Classe EscritaTxt.java
- [✔️] - Caso haja algum erro durante o processo da análise léxica, será gerado um arquivo
log contendo o erro que foi gerado. Criar método na mesma classe que irá gerar `.pixobj`



> ✔️ - Feito

> 🔘 - Em andamento

> ❌ - Não iniciado


## 📦 Disposição do programa
     .
     ├── analise_lexica
     │   └── src
     │       ├── main
     │       │    └── java
     │       │         └── com.faria
     │       │              ├── Main.java
     │       │              ├── LeitorTxt.java
     │       │              ├── EscritaTxt.java
     │       │              ├── Token.java
     │       │              │
     │       │              └── enums
     │       │                   ├── Tipagem.java
     │       │                   └── Constantes.java
     │       │
     │       └── test.java.br.com.faria
     │           ├── TestLeitorTxt.java
     │           └── TestTokenss.java
     │
     ├── target
     ├── untracked
     ├── README.md
     ├── pom.xml
     └── analisador_lexico.jar
     .

## 👷 Desenvolvimento

1.  ### 📚 Principais Classes
Descreve o funcionamento das principais classes do projeto.

IMPORTANTE: Como geraremos o token para o conteúdo dentro de '' ou ""? De qual tipo será?

-> Classe Token irá representar nosso token, seus atributos recebem os parâmetros necessários que compõe ele. Para representação de varios token utilize uma lista de Token, Exemplo: List`<Token>` ou ArrayList`<Token>`.

-> Classe LeitorTxt sevirá para fazer o intermédio entre o arquivo de texto e o código que fará a tratativa dos dados contidos nele. Utilizando primeiro o método bufferTxt para converter em String, ainda com espaçamentos, para depois utilizar o método separeteText para quebrar e dividir o texto em um String[] (array de String) delimitados por quais quer caracteres em branco, entretanto dando prioridade para o agrupamento de caracteres delimitados por "" ou ''.

-> Enum Tipagem deverá conter os tipos possiveis de Tokens que podemos armazenar.

-> Classes de teste em geral servem para verificação das funções criadas em determinada classe. Siga o padrão "Test + nome da classe.java"

Criador de diagramas: https://app.diagrams.net/?splash=0#G1MNDvqLwhnGeQRzdopsxT-lrRcbgqdqLt#%7B%22pageId%22%3A%22JrWfula45WOMTxcmjdZU%22%7D

2. ###  ♻️ Fluxo de execução da classe Main.java
Pix Script

O sistema opera através de um fluxo de processamento de strings baseado em estados e padrões definidos.
	
Leitura e Buffering (LeitorTxt.java): O processo inicia com a função bufferTxt, que utiliza FileReader e BufferedReader para converter o arquivo .pix em uma String manipulável. O bloco finally garante a saída do sistema de leitura independentemente de erros capturados.

Desmembramento de Símbolos Agrupados: O código conta com uma lógica específica para separar operadores e delimitadores que estejam "colados". Utilizando Pattern e Matcher, o sistema identifica símbolos como (, !, $>, != e {, aplicando regras de lookahead e lookbehind para separá-los sem perder a integridade dos operadores compostos.

Tokenização por Regex(token.json): A separação de lexemas no método separateText utiliza uma expressão regular robusta: \"[^\"]*\"|'[^']*'|\\S+. Essa lógica prioriza o agrupamento de caracteres dentro de aspas (simples ou duplas), impedindo que textos literais com espaços sejam fragmentados.


### Atualização de Tokens e tabela de símbolos

Configuração via JSON (tokens.json): As regras da linguagem são divididas em duas categorias:
Padrao: Contém tokens fixos da gramática, como palavras-chave (LEDGER, IF, LET) e delimitadores.

Outros: Armazena tokens dinâmicos identificados durante a execução, como IDENTIFICADOR, NUMBER e TEXTO_LITERAL.
Representação de Tipagem (Tipagem.java): Um enum é utilizado para categorizar os tokens (ex: KW_BP para base do programa, KW_OA para atribuição), permitindo comparações rápidas e seguras no código.

### Geração de saída e Objto direto(EscritaTxt.java)

Arquivo Objeto .pixobj : A classe EscritaTxt gera este arquivo substituindo os lexemas originais por suas representações em token.
Lógica de Substituição: O método substituirToken verifica se o lexema possui um ID na tabela de símbolos. Se for um TEXTO_LITERAL, o token é removido da lista após o uso para evitar duplicidade de IDs em objetos de conteúdo igual, mas alocações diferentes.
Formatação: O arquivo .pixobj é formatado com quebras de linha a cada 3 iterações para facilitar a leitura humana e o processamento posterior.
Validação

Cultura de Testes (JUnit 5): O arquivo pom.xml define a dependência do junit-jupiter. Os testes em TestLeitorTxt.java e TestTokenss.java validam desde a leitura de arquivos inexistentes até a criação correta de tokens complexos como operadores relacionais (>=, !=) e lógicos (&&, ||).
Gestão de Logs de Erro: Se um caractere inválido ou não mapeado é encontrado (como o símbolo Ψ registrado nos logs), o sistema utiliza o método log da classe EscritaTxt para gravar o erro com timestamp e o ponto exato da falha, sem interromper a execução do programa.


## 🔧 Como executar?
📁 A pasta "untracked" dispõe de arquivos de exemplo para validação dos testes do projeto. Por tanto é de suma importância mante-lo ativo até a finalização.



Pontos importantes a descrever:
- Como executar o código: 
- Cuidados a serem tomados;


## ✒️ Autores: 
| [<img src="https://avatars.githubusercontent.com/u/99749672?v=4" width=115><br><sub>Gabriel Alexandre</sub>](https://https://github.com/aieFaria) |  [<img src="https://avatars.githubusercontent.com/u/147110604?v=4" width=115><br><sub>Henzo Henrique</sub>](https://github.com/HenzoHS) |  [<img src="https://avatars.githubusercontent.com/u/160502160?v=4" width=115><br><sub>Railson Bernardo</sub>](https://github.com/Railson-Bernardo) |
| :---: | :---: | :---: |



