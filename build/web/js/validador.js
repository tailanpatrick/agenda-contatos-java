function validar(){
    removerTodosToasts();
    let nome = frmContato.nome.value;
    let fone = frmContato.fone.value;
    
     if(fone === ''){
      mostrarToastErro('Campo Telefone não pode estar vazio.');
      frmContato.fone.focus();
    }
    
    if(nome === ''){
      mostrarToastErro('Campo Nome não pode estar vazio.');
      frmContato.nome.focus();
    }
    
    if(nome === '' || fone === '') return false;
    
    document.forms["frmContato"].submit();
}