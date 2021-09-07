export default	{

formatarErro(erro){
    let msg = erro.titulo;
    for(let i = 0; i < erro.campos.length; i++){
      let campo = erro.campos[i];
      msg = msg +'\n'+ campo.campo + ': ' + campo.mensagem;
    }
    return msg;
  }
}