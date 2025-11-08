<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Gerador de Currículo</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-file-alt"></i> Gerador de Currículo</h1>
            <p>Preencha os campos abaixo para gerar seu currículo profissional</p>
        </div>

        <form:form modelAttribute="curriculo" action="${pageContext.request.contextPath}/curriculo/preview" method="post" class="curriculo-form">
            
            <!-- Informações Pessoais -->
            <div class="section">
                <h2><i class="fas fa-user"></i> Informações Pessoais</h2>
                <div class="form-row">
                    <div class="form-group">
                        <label for="nomeCompleto">Nome Completo *</label>
                        <form:input path="nomeCompleto" id="nomeCompleto" required="true" placeholder="Seu nome completo"/>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="email">E-mail *</label>
                        <form:input path="email" type="email" id="email" required="true" placeholder="seu@email.com"/>
                    </div>
                    <div class="form-group">
                        <label for="telefone">Telefone</label>
                        <form:input path="telefone" id="telefone" placeholder="(11) 99999-9999"/>
                    </div>
                </div>
                
                <div class="form-row">
                    <div class="form-group">
                        <label for="endereco">Endereço</label>
                        <form:input path="endereco" id="endereco" placeholder="Cidade, Estado"/>
                    </div>
                    <div class="form-group">
                        <label for="linkedin">LinkedIn</label>
                        <form:input path="linkedin" id="linkedin" placeholder="linkedin.com/in/seuperfil"/>
                    </div>
                </div>
            </div>

            <!-- Objetivo -->
            <div class="section">
                <h2><i class="fas fa-bullseye"></i> Objetivo Profissional</h2>
                <div class="form-group">
                    <form:textarea path="objetivo" rows="3" placeholder="Descreva seu objetivo profissional..."/>
                </div>
            </div>

            <!-- Resumo Profissional -->
            <div class="section">
                <h2><i class="fas fa-user-tie"></i> Resumo Profissional</h2>
                <div class="form-group">
                    <form:textarea path="resumoProfissional" rows="4" placeholder="Descreva brevemente sua experiência e principais qualificações..."/>
                </div>
            </div>

            <!-- Experiências Profissionais -->
            <div class="section">
                <h2><i class="fas fa-briefcase"></i> Experiências Profissionais</h2>
                <div id="experiencias-container">
                    <div class="experiencia-item">
                        <div class="form-row">
                            <div class="form-group">
                                <label>Cargo</label>
                                <input type="text" name="experiencias" placeholder="Ex: Desenvolvedor Java"/>
                            </div>
                            <div class="form-group">
                                <label>Empresa</label>
                                <input type="text" name="experiencias" placeholder="Nome da empresa"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Período</label>
                                <input type="text" name="experiencias" placeholder="Ex: Jan 2020 - Atual"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label>Descrição das atividades</label>
                            <textarea name="experiencias" rows="3" placeholder="Descreva suas principais atividades e conquistas..."></textarea>
                        </div>
                        <button type="button" class="btn-remove" onclick="removeExperiencia(this)">
                            <i class="fas fa-trash"></i> Remover
                        </button>
                    </div>
                </div>
                <button type="button" class="btn-add" onclick="addExperiencia()">
                    <i class="fas fa-plus"></i> Adicionar Experiência
                </button>
            </div>

            <!-- Educação -->
            <div class="section">
                <h2><i class="fas fa-graduation-cap"></i> Educação</h2>
                <div id="educacoes-container">
                    <div class="educacao-item">
                        <div class="form-row">
                            <div class="form-group">
                                <label>Curso</label>
                                <input type="text" name="educacoes" placeholder="Ex: Bacharelado em Ciência da Computação"/>
                            </div>
                            <div class="form-group">
                                <label>Instituição</label>
                                <input type="text" name="educacoes" placeholder="Nome da instituição"/>
                            </div>
                        </div>
                        <div class="form-row">
                            <div class="form-group">
                                <label>Período</label>
                                <input type="text" name="educacoes" placeholder="Ex: 2018 - 2022"/>
                            </div>
                            <div class="form-group">
                                <label>Status</label>
                                <input type="text" name="educacoes" placeholder="Ex: Concluído, Cursando"/>
                            </div>
                        </div>
                        <button type="button" class="btn-remove" onclick="removeEducacao(this)">
                            <i class="fas fa-trash"></i> Remover
                        </button>
                    </div>
                </div>
                <button type="button" class="btn-add" onclick="addEducacao()">
                    <i class="fas fa-plus"></i> Adicionar Educação
                </button>
            </div>

            <!-- Habilidades -->
            <div class="section">
                <h2><i class="fas fa-cogs"></i> Habilidades</h2>
                <div class="form-group">
                    <label for="habilidades">Habilidades (separadas por vírgula)</label>
                    <textarea name="habilidades" id="habilidades" rows="3" placeholder="Java, Spring Boot, MySQL, Git, Docker..."></textarea>
                </div>
            </div>

            <!-- Idiomas -->
            <div class="section">
                <h2><i class="fas fa-language"></i> Idiomas</h2>
                <div class="form-group">
                    <label for="idiomas">Idiomas (separados por vírgula)</label>
                    <textarea name="idiomas" id="idiomas" rows="2" placeholder="Português (Nativo), Inglês (Avançado), Espanhol (Intermediário)..."></textarea>
                </div>
            </div>

            <!-- Botões -->
            <div class="form-actions">
                <button type="submit" class="btn-primary">
                    <i class="fas fa-eye"></i> Visualizar Currículo
                </button>
            </div>
        </form:form>
    </div>

    <script>
        function addExperiencia() {
            const container = document.getElementById('experiencias-container');
            const newItem = document.querySelector('.experiencia-item').cloneNode(true);
            
            // Limpar os valores dos inputs
            newItem.querySelectorAll('input, textarea').forEach(input => input.value = '');
            
            container.appendChild(newItem);
        }

        function removeExperiencia(button) {
            const container = document.getElementById('experiencias-container');
            if (container.children.length > 1) {
                button.parentElement.remove();
            }
        }

        function addEducacao() {
            const container = document.getElementById('educacoes-container');
            const newItem = document.querySelector('.educacao-item').cloneNode(true);
            
            // Limpar os valores dos inputs
            newItem.querySelectorAll('input, textarea').forEach(input => input.value = '');
            
            container.appendChild(newItem);
        }

        function removeEducacao(button) {
            const container = document.getElementById('educacoes-container');
            if (container.children.length > 1) {
                button.parentElement.remove();
            }
        }
    </script>
</body>
</html>
