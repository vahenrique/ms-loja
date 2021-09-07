<template>
  <div class="container">
    <div class="row">
      <div class="col" v-if="existeInfoLoja">
        <button
          class="btn btn-primary"
          title="Novo Pedido"
          v-if="etapa === 'novo'"
          @click="novo()"
        >
          Novo Pedido
          <i class="fas fa-plus-circle"></i>
        </button>

        <div class="col-md-12" v-if="etapa === 'item'">
          <div class="row justify-content-end">
            <button
              class="btn btn-primary"
              title="Visualizar Itens do Pedido"
              @click="novo()"
            >
              Visualizar Itens do Pedido
              <i class="fas fa-shopping-cart"></i>
            </button>
          </div>
          <div class="row">
            <h4 class="mb-3 float-left">Itens do Catalogo</h4>
            <div class="table-responsive">
              <table class="table table-sm">
                <thead>
                  <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Valor</th>
                    <th scope="col">Categoria</th>
                    <th scope="col" class="text-center"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item of catalogoItems" :key="item.id">
                    <td>{{ item.nome }}</td>
                    <td>{{ item.valor }}</td>
                    <td>{{ item.categoriaNome }}</td>
                    <td class="text-center">
                      <button
                        @click="adicionar(item)"
                        class="btn btn-success"
                        title="Adicionar ao Pedido"
                      >
                        <i class="fas fa-plus-circle"></i>
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
      </div>
      <div class="col" v-else>
        <div class="alert alert-warning" role="alert">
          Atenção! <br />
          Não existe Informações da Loja cadastrada. <br />
          Necessário cadastrar no menu "Cadastros da Loja > Informações da Loja"
          antes de iniciar um pedido.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InfoLojaService from "../services/infoLoja";
import PedidosService from "../services/pedidos";
import PedidoItemsService from "../services/pedidoItems";
import CatalogoService from "../services/catalogo";
import Commons from "../services/commons";

export default {
  name: "app",
  data() {
    return {
      existeInfoLoja: false,
      etapa: "novo",
      pedido: {
        id: "",
        dtGeracao: "",
        status: "",
        valorFrete: "",
        clienteId: "",
        pedidoItems: [],
        transporteId: "",
      },
      catalogoItems: [],
    };
  },
  mounted() {
    this.verificarInfoLoja();
    this.etapa = "novo";
  },
  methods: {
    verificarInfoLoja() {
      InfoLojaService.visualizar().then((resposta) => {
        let infoLoja = resposta.data;
        this.existeInfoLoja = Boolean(infoLoja.id);
      });
    },
    novo() {
      PedidosService.novo().then((resposta) => {
        this.pedido = resposta.data;
        this.etapa = "item";
      });
      CatalogoService.listar().then((resposta) => {
        this.catalogoItems = resposta.data;
      });
    },
    adicionar(item) {
      let pedidoItem = {};
      pedidoItem.pedidoId = this.pedido.id;
      pedidoItem.catalogoItemId = item.id;
      pedidoItem.quantidade = 1;
      pedidoItem.valorUnitario = item.valor;
      PedidoItemsService.incluir(pedidoItem)
        .then((resposta) => {
          alert("Adicionado com sucesso!");
          console.log(resposta);
        })
        .catch((e) => {
          alert(Commons.formatarErro(e.response.data));
        });
    },
  },
};
</script>

<style>
</style>