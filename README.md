# Calculadora Aritmética em Java

INTEGRANTES: 
""" DIOGO LOURENCO ANDRADE / RA 23513 """

Este projeto implementa uma calculadora aritmética capaz de processar expressões matemáticas em notação infixa e calcular seus resultados. Utilizando conceitos de estruturas de dados, como filas e pilhas circulares, o programa converte expressões para notação pós-fixa (RPN - Reverse Polish Notation) e realiza cálculos de forma eficiente.

---

## **📜 Funcionalidades**

- **Entrada de Expressão Matemática:**
  - Aceita expressões infixas com operadores como `+`, `-`, `*`, `/`, `^` e parênteses para controle de precedência.
  - Exemplo de expressão válida: `3 + 5 * (2 - 8)`

- **Conversão para Notação Pós-Fixa (RPN):**
  - Realiza a conversão de expressões infixas para notação pós-fixa, facilitando a avaliação matemática.

- **Cálculo do Resultado:**
  - Processa a expressão em notação pós-fixa utilizando uma pilha circular para armazenar valores intermediários.

- **Tratamento de Erros:**
  - Verifica entradas inválidas, como divisões por zero, operadores inesperados ou falta de argumentos.

---

## **🛠️ Estruturas de Dados Utilizadas**

- **CircularQueue:**  
  Fila circular usada para armazenar tokens durante a conversão para RPN.

- **CircularStack:**  
  Pilha circular utilizada para armazenar operadores e realizar cálculos.

- **ExpressionConverter:**  
  Responsável por analisar e converter a expressão infixa para RPN.

- **RPNCalculator:**  
  Avalia a expressão em notação pós-fixa e calcula o resultado.

---

## **🚀 Como Usar**

### **Opção 1: Usando Argumentos de Linha de Comando**
1. Compile o projeto:  
   ```bash
   javac *.java
   ```
2. Execute o programa passando a expressão como argumento:  
   ```bash
   java Main "3 + 5 * (2 - 8)"
   ```
3. O resultado será exibido no terminal:  
   ```bash
   Resultado: -13.0
   ```

### **Opção 2: Entrada via Terminal**
Se preferir digitar a expressão durante a execução, modifique o método `main` para usar o `Scanner` (detalhado na seção anterior). Execute o programa e siga o prompt para inserir a expressão.

---

## **📂 Estrutura do Projeto**

```plaintext
├── Main.java                // Classe principal
├── CircularQueue.java       // Implementação da fila circular
├── CircularStack.java       // Implementação da pilha circular
├── ExpressionConverter.java // Conversão de expressões infixas para RPN
├── RPNCalculator.java       // Avaliação e cálculo de expressões em RPN
```

---

## **📚 Requisitos**

- **Java JDK 11+**  
  Certifique-se de ter uma versão compatível do JDK instalada em sua máquina.

---

## **💡 Exemplos de Uso**

### Entrada Válida:
```bash
java Main "10 + 2 * (6 / 3)"
```
Saída:
```plaintext
Resultado: 14.0
```

### Entrada Inválida:
```bash
java Main "10 + * 2"
```
Saída:
```plaintext
Erro: Operador inesperado: *
```

---

## **🔧 Melhorias Futuras**

- Suporte a funções matemáticas como seno (`sin`), cosseno (`cos`), logaritmo (`log`).
- Interface gráfica simples para interação mais amigável.
- Integração com arquivos de entrada e saída.

---

## **👨‍💻 Autor**
Desenvolvido como parte de um projeto de estudo em Java, com foco em algoritmos, estruturas de dados e manipulação de expressões matemáticas.

**Vamos calcular?** 💻✨
