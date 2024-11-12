
function mascaraTelefone(input) {
    input.value = input.value
        .replace(/\D/g, '')
        .replace(/^(\d{2})(\d)/g, '($1) $2') 
        .replace(/(\d{5})(\d)/, '$1-$2'); 
}

function aplicarMascara() {
    const telefoneInput = document.getElementById('telefone');
    if (telefoneInput) {
        telefoneInput.addEventListener('input', function() {
            mascaraTelefone(this);
        });
    }
}

// Chama a função para aplicar a máscara quando o DOM estiver carregado
document.addEventListener('DOMContentLoaded', aplicarMascara);
