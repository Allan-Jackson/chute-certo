# Como nomear bem meu commit?

Dar um bom nome ao commit é uma arte que salva a vida do seu "eu do futuro" e dos seus colegas de equipe. Um commit bem nomeado conta a história da evolução do código sem que ninguém precise ler linha por linha.

A regra de ouro no mercado é o padrão **Conventional Commits** , inspirado nas diretrizes de contribuição do projeto Angular, se tornou o padrão absoluto no mercado de desenvolvimento.

---

## 1. A Estrutura Ideal

Um bom commit segue este formato: `<tipo>: <descrição curta em letras minúsculas>`

Os tipos mais comuns seriam:

- **`refactor`**: Quando você muda o código para deixá-lo melhor, mas não adiciona uma funcionalidade nova.

- **`feat`**: Quando você adiciona uma funcionalidade (ex: a nova lógica de entrada).

- **`style`**: Mudanças que não afetam o código (espaços, formatação, organização de comentários).

- **`fix`**: Usado quando você corrige um erro (bug) no código.

- **`docs`**: Alterações apenas na documentação (como o seu arquivo README, as notas na pasta `docs/` ou comentários Javadoc).

- **`test`**: Quando você adiciona ou modifica testes (embora você ainda não tenha chegado nessa parte, é vital em projetos maiores).

- **`chore`**: Mudanças em arquivos de configuração que não afetam o código de produção (ex: atualizar o `.gitignore`, configurar o Maven/Gradle ou o seu Kanban).

- **`perf`**: Uma mudança de código focada especificamente em melhorar o desempenho (performance).

- **`ci`**: Mudanças nos seus arquivos de configuração de Integração Contínua (GitHub Actions, Travis, etc.).

- **`build`**: Alterações que afetam o sistema de construção ou dependências externas.

---

## 2. Dicas de Ouro para Commits

- **Seja Atômico:** Tente não fazer 10 coisas diferentes e dar um commit só. Se você criou a função de escrita e depois mudou o menu, o ideal seriam dois commits. 

- **Use o Imperativo:** Uma boa dica é completar a frase: *"Se eu aplicar este commit, ele vai..."*.
  
  - (Ele vai...) `separar funcoes de interface` (Certo)
  
  - (Ele vai...) `separando funcoes` ou `separei funcoes` (Evite)

- **O "Porquê", não o "Como":** Não precisa dizer "criei um switch case". Diga "organizei a lógica de escolha do menu".
