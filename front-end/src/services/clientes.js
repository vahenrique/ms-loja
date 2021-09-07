import { http } from './config'

export default {

    listar: () => {
        return http.get('clientes')
    },

    incluir: (cliente) => {
        return http.post('clientes', cliente);
    },

    atualizar: (cliente) => {
        return http.put('clientes/' + cliente.id, cliente);
    },

    deletar: (cliente) => {
        return http.delete('clientes/' + cliente.id)
    }
}