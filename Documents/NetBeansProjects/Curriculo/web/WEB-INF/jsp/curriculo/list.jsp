<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Currículos Salvos - Gerador de Currículo</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
        .curriculos-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }
        
        .curriculo-card {
            background: white;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            margin-bottom: 20px;
            padding: 20px;
            border-left: 4px solid #007bff;
        }
        
        .curriculo-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 15px;
        }
        
        .curriculo-nome {
            font-size: 1.4em;
            font-weight: bold;
            color: #333;
            margin: 0;
        }
        
        .curriculo-info {
            color: #666;
            font-size: 0.9em;
            margin-bottom: 10px;
        }
        
        .curriculo-actions {
            display: flex;
            gap: 10px;
        }
        
        .btn {
            padding: 8px 16px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            font-size: 0.9em;
            transition: background-color 0.3s;
        }
        
        .btn-primary {
            background-color: #007bff;
            color: white;
        }
        
        .btn-primary:hover {
            background-color: #0056b3;
        }
        
        .btn-danger {
            background-color: #dc3545;
            color: white;
        }
        
        .btn-danger:hover {
            background-color: #c82333;
        }
        
        .btn-secondary {
            background-color: #6c757d;
            color: white;
        }
        
        .btn-secondary:hover {
            background-color: #545b62;
        }
        
        .empty-state {
            text-align: center;
            padding: 60px 20px;
            color: #666;
        }
        
        .empty-state h3 {
            margin-bottom: 10px;
        }
        
        .error-message {
            background-color: #f8d7da;
            color: #721c24;
            padding: 15px;
            border-radius: 4px;
            margin-bottom: 20px;
            border: 1px solid #f5c6cb;
        }
        
        .page-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 30px;
            padding-bottom: 15px;
            border-bottom: 2px solid #eee;
        }
        
        .page-title {
            font-size: 2em;
            color: #333;
            margin: 0;
        }
    </style>
</head>
<body>
    <div class="curriculos-container">
        <div class="page-header">
            <h1 class="page-title">Currículos Salvos</h1>
            <a href="${pageContext.request.contextPath}/curriculo/form" class="btn btn-primary">
                Criar Novo Currículo
            </a>
        </div>
        
        <c:if test="${not empty error}">
            <div class="error-message">
                ${error}
            </div>
        </c:if>
        
        <c:choose>
            <c:when test="${empty curriculos}">
                <div class="empty-state">
                    <h3>Nenhum currículo salvo</h3>
                    <p>Você ainda não salvou nenhum currículo no banco de dados.</p>
                    <a href="${pageContext.request.contextPath}/curriculo/form" class="btn btn-primary">
                        Criar Primeiro Currículo
                    </a>
                </div>
            </c:when>
            <c:otherwise>
                <c:forEach var="curriculo" items="${curriculos}">
                    <div class="curriculo-card">
                        <div class="curriculo-header">
                            <h2 class="curriculo-nome">${curriculo.nomeCompleto}</h2>
                            <div class="curriculo-actions">
                                <a href="${pageContext.request.contextPath}/curriculo/download/${curriculo.id}" 
                                   class="btn btn-primary" target="_blank">
                                    Baixar PDF
                                </a>
                                <button onclick="deleteCurriculo(${curriculo.id}, '${curriculo.nomeCompleto}')" 
                                        class="btn btn-danger">
                                    Excluir
                                </button>
                            </div>
                        </div>
                        
                        <div class="curriculo-info">
                            <c:if test="${not empty curriculo.email}">
                                <strong>Email:</strong> ${curriculo.email}<br>
                            </c:if>
                            <c:if test="${not empty curriculo.telefone}">
                                <strong>Telefone:</strong> ${curriculo.telefone}<br>
                            </c:if>
                            <c:if test="${not empty curriculo.endereco}">
                                <strong>Endereço:</strong> ${curriculo.endereco}<br>
                            </c:if>
                        </div>
                        
                        <c:if test="${not empty curriculo.objetivo}">
                            <div class="curriculo-info">
                                <strong>Objetivo:</strong> 
                                <c:choose>
                                    <c:when test="${fn:length(curriculo.objetivo) > 100}">
                                        ${fn:substring(curriculo.objetivo, 0, 100)}...
                                    </c:when>
                                    <c:otherwise>
                                        ${curriculo.objetivo}
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </c:if>
                        
                        <div class="curriculo-info">
                            <strong>Experiências:</strong> ${fn:length(curriculo.experiencias)} |
                            <strong>Educação:</strong> ${fn:length(curriculo.educacoes)} |
                            <strong>Habilidades:</strong> ${fn:length(curriculo.habilidades)} |
                            <strong>Idiomas:</strong> ${fn:length(curriculo.idiomas)}
                        </div>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>
    </div>
    
    <script>
        function deleteCurriculo(id, nome) {
            if (confirm('Tem certeza que deseja excluir o currículo de "' + nome + '"? Esta ação não pode ser desfeita.')) {
                fetch('${pageContext.request.contextPath}/curriculo/delete/' + id, {
                    method: 'DELETE'
                })
                .then(response => {
                    if (response.ok) {
                        alert('Currículo excluído com sucesso!');
                        location.reload();
                    } else {
                        alert('Erro ao excluir currículo. Tente novamente.');
                    }
                })
                .catch(error => {
                    console.error('Erro:', error);
                    alert('Erro ao excluir currículo. Tente novamente.');
                });
            }
        }
    </script>
</body>
</html>
