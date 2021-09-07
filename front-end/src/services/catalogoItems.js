import { http } from './config'

export default {

    listar: () => {
        return http.get('items')
    },

    incluir: (item) => {
        return http.post('items', item);
    },

    atualizar: (item) => {
        return http.put('items/' + item.id, item);
    },

    deletar: (item) => {
        return http.delete('items/' + item.id)
    }
}