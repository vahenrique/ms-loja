<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12" v-if="visualizandoItem">
        <div class="row justify-content-end">
          <div class="col-sm-2">
            <button class="btn btn-primary" title="Voltar" @click="voltar()">
              Voltar
              <i class="fas fa-long-arrow-alt-left"></i>
            </button>
          </div>
        </div>
        <h4 class="mb-3 float-left">Itens do Pedido</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">Nome</th>
                <th scope="col">Valor Unitário</th>
                <th scope="col">Quantidade</th>
                <th scope="col">Valor Total</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item of pedidoSelecionado.pedidoItems" :key="item.id">
                <td>{{ item.catalogoItemNome }}</td>
                <td>{{ item.valorUnitario }}</td>
                <td>{{ item.quantidade }}</td>
                <td>{{ item.valorTotal }}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
      <div class="col-md-12" v-else>
        <h4 class="mb-3 float-left">Pedidos Registrados</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">ID</th>
                <th scope="col">Data</th>
                <th scope="col">Status</th>
                <th scope="col">Cliente</th>
                <th scope="col" class="text-center">Ver Itens</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="pedido of pedidos" :key="pedido.id">
                <td>{{ pedido.id }}</td>
                <td>{{ pedido.dtGeracao }}</td>
                <td>{{ pedido.status }}</td>
                <td>{{ pedido.clienteNome }}</td>
                <td class="text-center">
                  <button
                    @click="selecionar(pedido)"
                    class="btn btn-primary"
                    title="Ver Itens"
                  >
                    <i class="fas fa-search"></i>
                  </button>
                </td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import PedidosService from "../services/pedidos";
import CatalogoService from "../services/catalogo";

export default {
  name: "app",
  data() {
    return {
      pedidos: [],
      pedidoSelecionado: {},
      visualizandoItem: false,
    };
  },
  mounted() {
    this.listar();
    this.visualizandoItem = false;
  },
  methods: {
    listar() {
      PedidosService.listar().then((resposta) => {
        this.pedidos = resposta.data;
      });
    },
    selecionar(pedido) {
      this.pedidoSelecionado = Object.assign({}, pedido);
      this.visualizandoItem = true;
      if (this.pedidoSelecionado.pedidoItems.length > 0) {
        for (let i = 0; i < this.pedidoSelecionado.pedidoItems.length; i++) {
          let pedidoItem = this.pedidoSelecionado.pedidoItems[i];
          CatalogoService.visualizar(pedidoItem).then((resposta) => {
            pedidoItem.catalogoItemNome = resposta.data.nome;
            pedidoItem.valorTotal =
              pedidoItem.quantidade * pedidoItem.valorUnitario;
          });
        }
      }
    },
    voltar() {
      this.pedidoSelecionado = {};
      this.visualizandoItem = false;
      this.listar();
    },
  },
};
</script>

<style>
</style>