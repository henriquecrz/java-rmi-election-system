# Trabalho - Eleições com Java RMI

Este trabalho é baseado na proposta do livro do Coulouris, Dollimore, Kinberg, Blair (2013), pag. 227.

Considere uma interface Election que fornece dois métodos remotos:

- vote(String eleitor, String candidato):
  - String eleitor: código hash MD5 gerado a partir do nome completo do eleitor.
  - String candidato: String de 3 caracteres numéricos que identificam um candidato.
- result(String candidato): este método possui dois parâmetros com os quais o servidor recebe o número de um candidato e retorna para o cliente o número de votos desse candidato.
- Os identificadores de eleitor devem ser gerados a partir de uma função MD5 do nome completo do eleitor.
- O sistema deve carregar a lista de candidatos a partir do arquivo senadores.csv

Desenvolva um sistema para o serviço Election utilizando o Java RMI, que garanta que seus registros permaneçam consistentes quando ele é acessado simultaneamente por vários clientes.

O serviço Election deve garantir que todos os votos sejam armazenados com segurança, mesmo quando o processo servidor falha.

Considerando que o Java RMI possui semântica at-most-once, implemente um mecanismo de recuperação de falha no cliente que consiga obter uma semântica exactly-once para o caso do serviço ser interrompido por um tempo inferior a 30 segundos.

# Instruções
- Instalação do Java no mínimo na versão 8
- Executar a classe Server
- E, em seguida, a classe Client
