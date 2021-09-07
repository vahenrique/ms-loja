import { http } from './config'

export default {

    novo: () => {
        return http.post('pedidos')
    },

    finalizar: (pedido) => {
        return http.put('pedidos', pedido);
    },
}