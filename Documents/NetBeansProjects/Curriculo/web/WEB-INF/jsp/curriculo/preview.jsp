<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Preview do Currículo</title>
    <link href="<c:url value='/css/style.css'/>" rel="stylesheet" type="text/css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
</head>
<body>
    <div class="container">
        <div class="header">
            <h1><i class="fas fa-eye"></i> Preview do Currículo</h1>
            <p>Confira como ficará seu currículo antes de fazer o download</p>
        </div>

        <!-- Preview do Currículo -->
        <div class="curriculo-preview">
            <!-- Cabeçalho -->
            <div class="preview-header">
                <h1 class="nome">${curriculo.nomeCompleto}</h1>
                <div class="contato">
                    <c:if test="${not empty curriculo.email}">
                        <span><i class="fas fa-envelope"></i> ${curriculo.email}</span>
                    </c:if>
                    <c:if test="${not empty curriculo.telefone}">
                        <span><i class="fas fa-phone"></i> ${curriculo.telefone}</span>
                    </c:if>
                    <c:if test="${not empty curriculo.endereco}">
                        <span><i class="fas fa-map-marker-alt"></i> ${curriculo.endereco}</span>
                    </c:if>
                    <c:if test="${not empty curriculo.linkedin}">
                        <span><i class="fab fa-linkedin"></i> ${curriculo.linkedin}</span>
                    </c:if>
                </div>
            </div>

            <!-- Objetivo -->
            <c:if test="${not empty curriculo.objetivo}">
                <div class="preview-section">
                    <h2>OBJETIVO</h2>
                    <p>${curriculo.objetivo}</p>
                </div>
            </c:if>

            <!-- Resumo Profissional -->
            <c:if test="${not empty curriculo.resumoProfissional}">
                <div class="preview-section">
                    <h2>RESUMO PROFISSIONAL</h2>
                    <p>${curriculo.resumoProfissional}</p>
                </div>
            </c:if>

            <!-- Experiências -->
            <c:if test="${not empty curriculo.experiencias}">
                <div class="preview-section">
                    <h2>EXPERIÊNCIA PROFISSIONAL</h2>
                    <c:forEach var="exp" items="${curriculo.experiencias}">
                        <div class="preview-item">
                            <h3>${exp.cargo} - ${exp.empresa}</h3>
                            <p class="periodo">${exp.periodo}</p>
                            <c:if test="${not empty exp.descricao}">
                                <p class="descricao">${exp.descricao}</p>
                            </c:if>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <!-- Educação -->
            <c:if test="${not empty curriculo.educacoes}">
                <div class="preview-section">
                    <h2>EDUCAÇÃO</h2>
                    <c:forEach var="edu" items="${curriculo.educacoes}">
                        <div class="preview-item">
                            <h3>${edu.curso} - ${edu.instituicao}</h3>
                            <p class="periodo">${edu.periodo} - ${edu.status}</p>
                        </div>
                    </c:forEach>
                </div>
            </c:if>

            <!-- Habilidades -->
            <c:if test="${not empty curriculo.habilidades}">
                <div class="preview-section">
                    <h2>HABILIDADES</h2>
                    <div class="habilidades-list">
                        <c:forEach var="habilidade" items="${curriculo.habilidades}" varStatus="status">
                            ${habilidade.trim()}<c:if test="${!status.last}"> • </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>

            <!-- Idiomas -->
            <c:if test="${not empty curriculo.idiomas}">
                <div class="preview-section">
                    <h2>IDIOMAS</h2>
                    <div class="idiomas-list">
                        <c:forEach var="idioma" items="${curriculo.idiomas}" varStatus="status">
                            ${idioma.trim()}<c:if test="${!status.last}"> • </c:if>
                        </c:forEach>
                    </div>
                </div>
            </c:if>
        </div>

        <!-- Formulário para download -->
        <form action="${pageContext.request.contextPath}/curriculo/download" method="post" style="display: none;" id="downloadForm">
            <input type="hidden" name="nomeCompleto" value="${curriculo.nomeCompleto}"/>
            <input type="hidden" name="email" value="${curriculo.email}"/>
            <input type="hidden" name="telefone" value="${curriculo.telefone}"/>
            <input type="hidden" name="endereco" value="${curriculo.endereco}"/>
            <input type="hidden" name="linkedin" value="${curriculo.linkedin}"/>
            <input type="hidden" name="objetivo" value="${curriculo.objetivo}"/>
            <input type="hidden" name="resumoProfissional" value="${curriculo.resumoProfissional}"/>
            
            <!-- Experiências -->
            <c:forEach var="exp" items="${curriculo.experiencias}">
                <input type="hidden" name="experiencias" value="${exp.cargo}"/>
                <input type="hidden" name="experiencias" value="${exp.empresa}"/>
                <input type="hidden" name="experiencias" value="${exp.periodo}"/>
                <input type="hidden" name="experiencias" value="${exp.descricao}"/>
            </c:forEach>
            
            <!-- Educações -->
            <c:forEach var="edu" items="${curriculo.educacoes}">
                <input type="hidden" name="educacoes" value="${edu.curso}"/>
                <input type="hidden" name="educacoes" value="${edu.instituicao}"/>
                <input type="hidden" name="educacoes" value="${edu.periodo}"/>
                <input type="hidden" name="educacoes" value="${edu.status}"/>
            </c:forEach>
            
            <!-- Habilidades -->
            <c:if test="${not empty curriculo.habilidades}">
                <input type="hidden" name="habilidades" value="<c:forEach var='h' items='${curriculo.habilidades}' varStatus='s'>${h}<c:if test='${!s.last}'>,</c:if></c:forEach>"/>
            </c:if>
            
            <!-- Idiomas -->
            <c:if test="${not empty curriculo.idiomas}">
                <input type="hidden" name="idiomas" value="<c:forEach var='i' items='${curriculo.idiomas}' varStatus='s'>${i}<c:if test='${!s.last}'>,</c:if></c:forEach>"/>
            </c:if>
        </form>

        <!-- Botões de ação -->
        <div class="form-actions">
            <a href="${pageContext.request.contextPath}/curriculo/form" class="btn-secondary">
                <i class="fas fa-arrow-left"></i> Voltar ao Formulário
            </a>
            <button type="button" class="btn-primary" onclick="downloadPdf()">
                <i class="fas fa-download"></i> Baixar PDF
            </button>
        </div>
    </div>

    <script>
        function downloadPdf() {
            document.getElementById('downloadForm').submit();
        }
    </script>
</body>
</html>
