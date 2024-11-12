// Configurações opcionais para o Toastr 
toastr.options = {
    "closeButton": true, 
    "progressBar": true, 
    "positionClass": "toast-top-right",
    "timeOut": "4000",
    "extendedTimeOut": "1000",
};

function mostrarToastSucesso() {
    toastr.success('Operação realizada com sucesso!', 'Sucesso');
}

function mostrarToastErro(msg) {
    toastr.error(msg, 'Erro');
}

function mostrarToastAlerta() {
    toastr.warning('Atenção! Algo pode estar errado.', 'Alerta');
}

function removerTodosToasts() {
    toastr.clear(); // Limpa todos os toasts
}