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
              @click="carrinho()"
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
        <div class="col-md-12" v-if="etapa === 'finalizar'">
          <div class="row justify-content-end">
            <div class="col-sm-2">
              <button
                class="btn btn-primary"
                title="Voltar ao Catálogo"
                @click="voltarCatalogo()"
              >
                Voltar ao Catálogo
                <i class="fas fa-book-open"></i>
              </button>
            </div>
            <div class="col-sm-2">
              <button
                class="btn btn-success"
                title="Check-out"
                @click="checkout()"
              >
                Check-out
                <i class="fas fa-save"></i>
              </button>
            </div>
          </div>
          <div class="row">
            <h4 class="mb-3 float-left">Itens do Pedido</h4>
            <div class="table-responsive">
              <table class="table table-sm">
                <thead>
                  <tr>
                    <th scope="col">Nome</th>
                    <th scope="col">Valor Unitário</th>
                    <th scope="col">Quantidade</th>
                    <th scope="col">Valor Total</th>
                    <th scope="col" class="text-center"></th>
                  </tr>
                </thead>
                <tbody>
                  <tr v-for="item of pedido.pedidoItems" :key="item.id">
                    <td>{{ item.catalogoItemNome }}</td>
                    <td>{{ item.valorUnitario }}</td>
                    <td>
                      <button
                        class="btn btn-danger"
                        title="Subtrair"
                        style="margin-right: 1em"
                        @click="subtrairQuantidade(item)"
                      >
                        <i class="fas fa-minus-circle"></i>
                      </button>
                      {{ item.quantidade }}
                      <button
                        class="btn btn-success"
                        title="Adicionar"
                        style="margin-left: 1em"
                        @click="adicionarQuantidade(item)"
                      >
                        <i class="fas fa-plus-circle"></i>
                      </button>
                    </td>
                    <td>{{ item.valorTotal }}</td>
                    <td class="text-center">
                      <button
                        @click="remover(item)"
                        class="btn btn-danger"
                        title="Remover"
                      >
                        <i class="fas fa-trash"></i>
                      </button>
                    </td>
                  </tr>
                </tbody>
              </table>
            </div>
          </div>
        </div>
        <div class="col-md-12" v-if="etapa === 'checkout'">
          <div class="container">
            <div class="row">
              <form
                @submit.prevent="finalizarPedido"
                method="POST"
                autocomplete="off"
              >
                <div class="row">
                  <div class="col-md mb-3">
                    <label for="valorFrete">Valor do Frete</label>
                    <input
                      id="valorFrete"
                      type="number"
                      class="form-control"
                      placeholder=""
                      v-model="pedido.valorFrete"
                    />
                  </div>
                </div>

                <div class="row">
                  <div class="col-md mb-3">
                    <label for="cliente">Cliente</label>
                    <select
                      class="form-control"
                      id="cliente"
                      v-model="pedido.clienteId"
                    >
                      <option
                        v-for="cliente of clientes"
                        :key="cliente.id"
                        :value="cliente.id"
                      >
                        {{ cliente.nome }}
                      </option>
                    </select>
                  </div>
                </div>

                <div class="row">
                  <div class="col-md mb-3">
                    <button type="submit" class="btn btn-success btn-block">
                      <i class="fas fa-save"></i>
                      Finalizar
                    </button>
                  </div>
                  <div class="col-md mb-3">
                    <button
                      class="btn btn-danger btn-block"
                      @click="descartarPedido()"
                    >
                      <i class="fas fa-trash"></i>
                      Descartar
                    </button>
                  </div>
                </div>
              </form>
            </div>
          </div>
        </div>
      </div>
      <div class="col" v-else>
        <div class="alert alert-warning" role="alert">
          Atenção! <br />
          Não existem Informações da Loja cadastradas. <br />
          Necessário cadastrar no menu "Cadastros da Loja > Informações da Loja"
          antes de iniciar um pedido.
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import InfoLojaService from "../services/infoLoja";
import ClientesService from "../services/clientes";
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
      clientes: [],
    };
  },
  mounted() {
    if (this.etapa === "novo") {
      this.verificarInfoLoja();
    }
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
    carrinho() {
      PedidosService.visualizar(this.pedido).then((resposta) => {
        this.pedido = resposta.data;
        this.etapa = "finalizar";
        if (this.pedido.pedidoItems.length > 0) {
          for (let i = 0; i < this.pedido.pedidoItems.length; i++) {
            let pedidoItem = this.pedido.pedidoItems[i];
            CatalogoService.visualizar(pedidoItem).then((resposta) => {
              pedidoItem.catalogoItemNome = resposta.data.nome;
              pedidoItem.valorTotal =
                pedidoItem.quantidade * pedidoItem.valorUnitario;
            });
          }
        }
      });
    },
    subtrairQuantidade(item) {
      let itemAlt = Object.assign({}, item);
      itemAlt.quantidade = item.quantidade - 1;
      this.alterarQuantidade(itemAlt);
    },
    adicionarQuantidade(item) {
      let itemAlt = Object.assign({}, item);
      itemAlt.quantidade = item.quantidade + 1;
      this.alterarQuantidade(itemAlt);
    },
    alterarQuantidade(item) {
      PedidoItemsService.alterarQuantidade(item)
        .then((resposta) => {
          this.carrinho();
          console.log(resposta);
        })
        .catch((e) => {
          alert(Commons.formatarErro(e.response.data));
        });
    },
    remover(item) {
      if (confirm("Deseja excluir o item?")) {
        PedidoItemsService.deletar(item).then((resposta) => {
          this.carrinho();
          console.log(resposta);
        });
      }
    },
    voltarCatalogo() {
      this.etapa = "item";
    },
    checkout() {
      this.etapa = "checkout";
      ClientesService.listar().then((resposta) => {
        this.clientes = resposta.data;
      });
    },
    finalizarPedido() {
      PedidosService.finalizar(this.pedido)
        .then((resposta) => {
          this.reset();
          console.log(resposta);
        })
        .catch((e) => {
          alert(Commons.formatarErro(e.response.data));
        });
    },
    descartarPedido() {
      if (confirm("Deseja excluir o pedido?")) {
        PedidosService.deletar(this.pedido).then((resposta) => {
          this.reset();
          console.log(resposta);
        });
      }
    },
    reset() {
      this.pedido = {};
      this.clientes = [];
      this.etapa = "novo";
    },
  },
};
</script>

<style>
</style>