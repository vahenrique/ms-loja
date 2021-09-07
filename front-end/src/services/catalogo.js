import { http } from './config'

export default {

    listar: () => {
        return http.get('catalogo')
    },
    visualizar: (pedidoItem) => {
        return http.get('catalogo/' + pedidoItem.catalogoItemId);
    },
}