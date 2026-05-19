# Ignorando mudanças locais com Git

Existem pastas de projeto (out, .idea, etc.) que são criadas pelas IDEs e que devem ser adicionadas ao *.gitignore* e esse arquivo deve subir para o repositório e ficar disponível para todo o time.

Entretanto, podem existir também pastas e arquivos locais que você utilize para fazer logs e rascunhos do código, o que deve ser feito, anotações, guardar valores de chaves de acesso locais ou coisa assim, que não são interessantes ou necessários deixar visível para o time, sendo preciso um '.gitignore local'.

## Criando o arquivo .gitignore_local

Primeiro passo é criar um arquivo chamado, por exemplo, *.gitignore_local* que contenha, assim como o .gitignore do projeto, as pastas e arquivos que deseja ocultar do tracking do git.

Pode fazer isso utilizando o comando *echo*, *printf* ou *cat* para criar pelo terminal:

```bash
cat <<EOF >> .gitignore_local
.idea/
out/
*.iml
/outrapasta
EOF


## OU


echo ".idea/
out/" >> .gitignore_local

## OU


printf ".idea/\nout/\n*.ml" >> .gitignore_local
```

## Adicionando o poder da IGNORÂNCIA ao .gitignore_local

Para que o arquivo funcione exatamente como o *.gitignore* precisamos dizer ao Git para fazê-lo, utilizando o git config:



> git config core.excludesFile .gitignore_local



## Evitar que o arquivo suba para o remoto por engano

Apesar do arquivo já funcionar como o *.gitignore*, não é ainda o que queremos. Pois assim como o *.gitignore*, o Git vai rastrear o arquivo e podemos acabar adicionando ele na stage, fazendo o commit e depois o push, sem querer, e aí ele vai acabar ficando disponível no servidor remoto e acessível para toda a equipe.

Para evitar que isso aconteça, devemos dizer para o Git não rastrear esse arquivo também. Para isso vamos usar um "arquivo secreto" que o Git possui e que funciona exatamente como o `.gitignore`, mas ele é **estritamente local**. Ele não é rastreado pelo Git e nunca será enviado para o GitHub.

- **Onde fica:** Vá na pasta raiz do seu projeto e entre na pasta oculta `.git`. Lá dentro, procure por `info` e depois pelo arquivo `exclude`.

- **O que fazer:** Abra o arquivo `.git/info/exclude` em um editor de texto e adicione o nome do arquivo local *.gitignore_local*:
  
  ```bash
  # na pasta do projeto:
  echo ".gitignore_local" >> .git/info/exclude
  ```

**Resultado:** O Git passará a ignorar o `.gitignore_local`, mas essa regra só existe no seu computador. Quando algum colega baixar o projeto, o arquivo `exclude` dele estará vazio e ele nem saberá que você ignora essa pasta. 

Agora sim, temos um arquivo para ignorar nossos arquivos pessoais do projeto e sem risco de subir isso para o repositório remoto! :)
