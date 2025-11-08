# Gerador de Currículo - Spring MVC

Um sistema web para geração de currículos profissionais em PDF, desenvolvido com arquitetura MVC usando Spring Framework.

## 🚀 Funcionalidades

### ✅ Funcionalidades Originais
- ✅ Formulário web intuitivo para entrada de dados
- ✅ Preview do currículo antes do download
- ✅ Geração automática de PDF profissional
- ✅ Interface responsiva e moderna
- ✅ Arquitetura MVC bem estruturada

### 🆕 Novas Funcionalidades SQLite
- ✅ **Persistência completa em banco SQLite**
- ✅ **Salvar currículo e gerar PDF simultaneamente**
- ✅ **Listar todos os currículos salvos**
- ✅ **Baixar PDF de currículos salvos**
- ✅ **Excluir currículos do banco de dados**
- ✅ **Inicialização automática do banco**

## 🛠️ Tecnologias Utilizadas

- **Backend**: Java, Spring MVC
- **Banco de Dados**: SQLite + JDBC
- **Frontend**: JSP, HTML5, CSS3, JavaScript
- **PDF**: iText Library
- **Servidor**: GlassFish/Tomcat
- **IDE**: NetBeans

## 📋 Pré-requisitos

- Java 8 ou superior
- NetBeans IDE
- GlassFish Server ou Tomcat
- Bibliotecas necessárias (ver seção de instalação)

## 🔧 Instalação e Configuração

### 1. Dependências Necessárias

Baixe e adicione as seguintes bibliotecas ao projeto:

#### iText PDF (Obrigatório)
```
itextpdf-5.5.13.2.jar
```
Download: https://github.com/itext/itextpdf/releases

#### JSTL (Obrigatório)
```
jstl-1.2.jar
```
Download: https://mvnrepository.com/artifact/javax.servlet/jstl/1.2

### 2. Adicionando Dependências no NetBeans

1. Clique com o botão direito no projeto
2. Selecione **Properties**
3. Vá para **Libraries**
4. Clique em **Add JAR/Folder**
5. Adicione os arquivos JAR baixados

**Alternativa**: Copie os JARs para `web/WEB-INF/lib/`

### 3. Configuração do Servidor

1. Configure o GlassFish ou Tomcat no NetBeans
2. Deploy do projeto no servidor
3. Acesse: `http://localhost:8080/Curriculo/curriculo/`

## 📁 Estrutura do Projeto

```
Curriculo/
├── src/java/com/curriculo/
│   ├── model/
│   │   ├── Curriculo.java
│   │   ├── Experiencia.java
│   │   └── Educacao.java
│   ├── controller/
│   │   └── CurriculoController.java
│   └── service/
│       └── PdfService.java
├── web/
│   ├── WEB-INF/
│   │   ├── jsp/curriculo/
│   │   │   ├── form.jsp
│   │   │   └── preview.jsp
│   │   ├── dispatcher-servlet.xml
│   │   └── web.xml
│   └── css/
│       └── style.css
└── README.md
```

## 🎯 Como Usar

### 1. Acessar o Formulário
- Navegue para: `http://localhost:8080/Curriculo/curriculo/`
- Preencha os dados pessoais obrigatórios

### 2. Adicionar Experiências
- Clique em "Adicionar Experiência" para incluir mais cargos
- Preencha cargo, empresa, período e descrição
- Use o botão "Remover" para excluir experiências

### 3. Adicionar Educação
- Clique em "Adicionar Educação" para incluir cursos
- Preencha curso, instituição, período e status

### 4. Habilidades e Idiomas
- Liste habilidades separadas por vírgula
- Liste idiomas com nível de proficiência

### 5. Preview e Download
- Clique em "Visualizar Currículo" para ver o preview
- Revise as informações
- Clique em "Baixar PDF" para gerar o arquivo

## 🎨 Personalização

### Modificar Layout do PDF
Edite o arquivo `PdfService.java` para alterar:
- Fontes e tamanhos
- Cores e espaçamentos
- Estrutura das seções

### Modificar Interface Web
Edite os arquivos:
- `form.jsp` - Formulário de entrada
- `preview.jsp` - Página de preview
- `style.css` - Estilos visuais

## 🔍 Rotas da Aplicação

| Rota | Método | Descrição |
|------|--------|-----------|
| `/curriculo/` | GET | Página inicial com formulário |
| `/curriculo/form` | GET | Formulário de entrada |
| `/curriculo/preview` | POST | Preview do currículo |
| `/curriculo/download` | POST | Download do PDF |

## 🐛 Solução de Problemas

### Erro: "Cannot resolve iText imports"
- Verifique se o JAR do iText foi adicionado corretamente
- Confirme a versão compatível (5.5.13.2)

### Erro: "JSTL tags not working"
- Adicione o JAR do JSTL às bibliotecas
- Verifique as declarações taglib nos JSPs

### Erro 404 ao acessar páginas
- Confirme se o component-scan está configurado
- Verifique se o servidor está rodando
- Confirme a URL base do projeto

## 📝 Arquitetura MVC

### Model
- `Curriculo.java` - Entidade principal
- `Experiencia.java` - Dados de experiência profissional  
- `Educacao.java` - Dados educacionais

### View
- `form.jsp` - Interface de entrada de dados
- `preview.jsp` - Visualização do currículo
- `style.css` - Estilos da aplicação

### Controller
- `CurriculoController.java` - Gerencia requisições e respostas
- `PdfService.java` - Serviço de geração de PDF

## 🤝 Contribuição

1. Faça um fork do projeto
2. Crie uma branch para sua feature
3. Commit suas mudanças
4. Push para a branch
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo LICENSE para detalhes.

## 🆘 Suporte

Para dúvidas ou problemas:
1. Verifique a seção de solução de problemas
2. Consulte a documentação do Spring MVC
3. Abra uma issue no repositório

---

**Desenvolvido com ❤️ usando Spring MVC**
