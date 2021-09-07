import { http } from './config'

export default {

    visualizar: () => {
        return http.get('infoloja')
    },

    incluir: (infoloja) => {
        return http.post('infoloja', infoloja);
    },

    atualizar: (infoloja) => {
        return http.put('infoloja', infoloja);
    },

    deletar: () => {
        return http.delete('infoloja')
    }
}