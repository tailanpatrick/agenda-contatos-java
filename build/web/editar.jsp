<%@page import="model.Contato"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    // Obter a lista de contatos da requisição
    Contato contato = (Contato) request.getAttribute("contato");
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Agenda de Contatos - Editar Contato</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="./css/style.css"/>      
        <link rel="icon" href="./img/icon-phone.png"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.css">
    </head>
    <body>
        <div class="conteudo-principal">

            <div class="agenda-e-novo">
                <h1>Editar Contato</h1>

            </div>
            <form class="frmContato" name="frmContato" action="/agenda-contatos-java/edit" method="POST">
                <input type="hidden" name="id" value="<%= contato.getIdcon() %>"/>
                
                <div class="form-control">
                    <label>Nome:</label>
                    <input type="text" name="nome" value="<%= contato.getNome() %>"/>    
                </div>

                <div class="form-control">
                    <label>Telefone:</label>
                    <input type="text" name="fone" id="telefone" maxlength="15" value="<%= contato.getPhone() %>"/>    
                </div>

                <div class="form-control">
                    <label>Email:</label>
                    <input type="text" name="email" value="<%= contato.getEmail() %>"/>    
                </div>

                <div class="form-control botoes">
                    <button type="button" onclick="validar();">Salvar</button>
                </div>
                <div class="form-control">
                    <a href="main" style="display: inline-block;">Voltar</a>
                </div>
            </form>
        </div>

        <script src="./js/mascara-telefone.js"></script>
        <script src="./js/validador.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/toastr.min.js"></script>
        <script src="./js/toast.js"></script>
    </body>
</html>
