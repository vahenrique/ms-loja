import { http } from './config'

export default {

    listar: () => {
        return http.get('pedidos');
    },
    visualizar: (pedido) => {
        return http.get('pedidos/' + pedido.id);
    },
    novo: () => {
        return http.post('pedidos')
    },

    finalizar: (pedido) => {
        return http.put('pedidos', pedido);
    },
}