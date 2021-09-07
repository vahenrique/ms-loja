import { http } from './config'

export default	{

    listar:()=>{
		return http.get('categorias')
    },
    
    incluir:(categoria)=>{
        return http.post('categorias',categoria);
    },

    atualizar:(categoria)=>{
        return http.put('categorias/' + categoria.id ,categoria);
    },

	deletar:(categoria)=>{
		return http.delete('categorias/' + categoria.id)
	}
}