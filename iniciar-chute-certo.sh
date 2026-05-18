#!/bin/bash

# Garante que o script mude o terminal para a pasta onde ele próprio está salvo
cd "$(dirname "$0")" || exit

# Abre uma nova janela de terminal para rodar o jogo e mantém aberta no fim
gnome-terminal -- bash -c "java -jar ChuteCerto.jar; echo; read -p 'Jogo encerrado. Pressione ENTER para sair...'"