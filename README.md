# Instruções para Executar o Projeto

Estas são as instruções para configurar e executar o projeto em sua máquina local. Certifique-se de seguir todos os passos na ordem indicada.

## Pré-requisitos

Antes de começar, certifique-se de que você tenha os seguintes pré-requisitos instalados:

- **Java Development Kit (JDK):** Certifique-se de ter o JDK instalado em sua máquina. Você pode baixá-lo em [OpenJDK](https://adoptopenjdk.net/).
- **Maven:** Certifique-se de que o Maven esteja instalado em sua máquina. Você pode baixá-lo em [Maven](https://maven.apache.org/download.cgi).
- **Git:** O Git é necessário para clonar o repositório. Você pode baixá-lo em [Git](https://git-scm.com/downloads).
- **MySQL:** Você precisa ter um servidor MySQL instalado ou acessível para criar o banco de dados.



## Execução do Projeto

Siga estas etapas para executar o projeto:

1. **Navegue até a pasta de projetos e clone o repositório do GitHub**

   ```bash
   git clone https://github.com/rodreeg0/projedata_teste.git


2. **Navegue até o diretório do projeto:**

   ```bash
   cd ./projedata_teste


3. **Compile o projeto usando o Maven:**

   ```bash
   mvn clean install

4. **Execute o projeto:**

   #### 4.1. Crie um banco de dados MySQL com o nome `projedata_teste`.

   #### 4.2. Abra o arquivo `hibernate.cfg.xml` localizado na pasta `src/main/resources`.

   #### 4.3. Atualize as configurações do banco de dados, incluindo o URL de conexão, nome de usuário e senha no arquivo `hibernate.cfg.xml`:

      ```xml
      <property name="hibernate.connection.url">jdbc:mysql://localhost:3306/projedata_teste</property>
      <property name="hibernate.connection.username">seu_usuario</property>
      <property name="hibernate.connection.password">sua_senha</property>
      ```
   #### 4.4. Salve o arquivo `hibernate.cfg.xml`.

   #### 4.5. Execute a classe principal.

      ```bash
      mvn exec:java@run-principal
      ```
      ou caso não queira rodar com o banco de dados:
      ```bash
      mvn exec:java@run-principalSimples
      ```


