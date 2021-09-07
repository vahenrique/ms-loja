import { http } from './config'

export default {

    listar: () => {
        return http.get('pedidoitems')
    },

    incluir: (pedidoItem) => {
        return http.post('pedidoitems', pedidoItem);
    },

    alterarQuantidade: (pedidoItem) => {
        return http.put('pedidoitems/' + pedidoItem.id, pedidoItem);
    },

    deletar: (pedidoItem) => {
        return http.delete('pedidoitems/' + pedidoItem.id)
    }
}