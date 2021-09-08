import { http } from './config'

export default {

    listar: () => {
        return http.get('transportes')
    },

    atualizar: (transporte) => {
        return http.put('transportes/' + transporte.id, transporte);
    },
}