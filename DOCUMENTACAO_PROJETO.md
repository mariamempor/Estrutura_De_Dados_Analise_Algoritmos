# Documento de Implementação - Simulador de Suporte Técnico

## 1. Visão geral
O sistema simula uma central de suporte técnico de informática via console e foi desenvolvido com Java padrão, sem frameworks e sem banco de dados.

## 2. Estruturas utilizadas e onde foram implementadas

### 2.1 Fila (Queue)
- **Classe:** `src/estruturas/FilaChamados.java`
- **Objetivo:** manter chamados aguardando atendimento.
- **Implementação:** três filas internas (`filaAlta`, `filaMedia`, `filaBaixa`) com `LinkedList` para priorizar atendimento.
- **Regra:** chamados de prioridade **ALTA** são atendidos antes de **MEDIA**, e estes antes de **BAIXA**.

### 2.2 Pilha (Stack)
- **Classe:** `src/estruturas/PilhaHistorico.java`
- **Objetivo:** registrar histórico de chamados atendidos.
- **Implementação:** `Stack<Chamado>`.
- **Comportamento:** último chamado finalizado aparece primeiro no relatório de histórico (LIFO).

### 2.3 Lista Ligada (LinkedList)
- **Classe:** `src/estruturas/ListaUsuarios.java`
- **Objetivo:** armazenar usuários cadastrados no sistema.
- **Implementação:** `LinkedList<Usuario>` com operações de cadastro, busca por ID e listagem.

### 2.4 Árvore Binária de Busca (BST)
- **Classe:** `src/estruturas/ArvoreChamados.java`
- **Objetivo:** indexar chamados para busca rápida por ID.
- **Implementação manual:** classe interna `No` com referências `esquerdo` e `direito`.
- **Operações:** inserção recursiva, busca recursiva e travessia em ordem.

## 3. Regras de negócio implementadas
- Chamado contém ID, descrição, prioridade, status, usuário, técnico e tempo estimado.
- Chamado aberto entra na fila e também na árvore.
- Chamado atendido é removido da fila, recebe técnico, é finalizado e registrado na pilha.
- Relatórios exibem total em espera, total atendido e tempo médio de atendimento simulado.

## 4. Organização em classes

### Modelos
- `Chamado`
- `Usuario`
- `Tecnico`
- `Prioridade` (enum)
- `StatusChamado` (enum)

### Estruturas
- `FilaChamados`
- `PilhaHistorico`
- `ListaUsuarios`
- `ArvoreChamados`

### Sistema
- `SistemaSuporte` (regra de negócio e integração das estruturas)

### Interface
- `Menu` (interação com usuário via console)

### Execução
- `Main`

## 5. Fluxo funcional demonstrável
1. Cadastrar usuário.
2. Abrir chamado.
3. Atender próximo chamado (respeitando prioridade).
4. Ver fila de espera.
5. Ver histórico (pilha).
6. Buscar chamado por ID (árvore).
7. Exibir relatórios.

## 6. Dados automáticos para simulação
Na inicialização (`SistemaSuporte`), o sistema cria:
- 3 técnicos
- 3 usuários
- 3 chamados com prioridades diferentes

Isso permite iniciar a demonstração sem depender de cadastro manual completo.

## 7. Como executar
```bash
javac -d out $(find src -name "*.java")
java -cp out Main
```

## 8. Pontos para apresentação (5 a 10 min)
- Mostrar onde cada estrutura está no código.
- Explicar diferença entre fila de prioridade e pilha de histórico.
- Demonstrar busca por ID na árvore.
- Executar fluxo completo no menu.
