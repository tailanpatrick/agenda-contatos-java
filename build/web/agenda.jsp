<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Contato"%>
<%@page import="java.util.ArrayList"%>

<%
    // Obter a lista de contatos da requisição
    ArrayList<Contato> contatos = (ArrayList<Contato>) request.getAttribute("contatos");
%>
<!DOCTYPE html>
<html lang="pt-BR">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Agenda de Contatos - Contatos</title>
        <link rel="icon" href="./img/icon-phone.png"/>
        <link rel="stylesheet" href="./css/style.css"/>
    </head>
    <body>
        <div class="conteudo-principal">

            <div class="agenda-e-novo">
                <h1 class="titulo-agenda">Agenda de Contatos</h1>
                <a href="novo.html">
                    <img src="./img/add.png" alt="novo contato" title="Novo Contato"/>
                </a>
            </div>

            <div class="lista-contatos">

                <% 
                    // Verifica se a lista de contatos é nula ou está vazia
                    if (contatos == null || contatos.size() == 0) { 
                %>
                <p style="text-align: center; margin-top: 150px;"><b>*</b> Não há contatos para exibir no momento.</p>
                <% 
                    } else {
                        for (Contato c : contatos) { 
                %>

                <div class="contato">
                    <img src="https://img.icons8.com/fluency/48/000000/user-male-circle.png" alt="Foto de contato" class="foto-contato"/>
                    <div class="info-contato">
                        <strong><%= c.getNome() %></strong>
                        <p><%= c.getPhone() %></p>
                        <p><%= c.getEmail() %></p>
                    </div>
                    <div class="acoes-contato">
                        <a href="select?idcon=<%= c.getIdcon() %>">
                            <img src="https://img.icons8.com/fluency/48/0000FF/edit.png" alt="Editar" title="Editar Contato"/>
                        </a>
                        <a href="delete?idcon=<%= c.getIdcon() %>" onclick="deletarContato()">
                            <img src="https://img.icons8.com/color/48/FF0000/delete.png" alt="Deletar" title="Deletar Contato"/>
                        </a>
                    </div>
                </div>

                <% 
                        }
                    } 
                %>
            </div>
        </div>
    </body>
</html>
