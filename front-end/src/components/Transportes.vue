<template>
  <div class="container">
    <div class="row">
      <div class="col-md-12" v-if="visualizandoTransporte">
        <h4 class="mb-3 float-left">Atualizar Transporte</h4>
        <div class="container">
          <div class="row">
            <form
              @submit.prevent="atualizar"
              method="POST"
              autocomplete="off"
            >
              <div class="row">
                <div class="col-md mb-3">
                  <label for="codigoRastreio">Código de Rastreio</label>
                  <input
                    id="codigoRastreio"
                    type="text"
                    class="form-control"
                    placeholder=""
                    v-model="transporteSelecionado.codigoRastreio"
                  />
                </div>
              </div>

              <div class="row">
                <div class="col-md mb-3">
                  <label for="status">Status</label>
                  <select
                    class="form-control"
                    id="status"
                    v-model="transporteSelecionado.status"
                  >
                    <option v-for="st of status" :key="st" :value="st">
                      {{ st }}
                    </option>
                  </select>
                </div>
              </div>

              <div class="row">
                <div class="col-md mb-3">
                  <button type="submit" class="btn btn-success btn-block">
                    Salvar
                  </button>
                </div>
                <div class="col-md mb-3">
                  <button class="btn btn-danger btn-block" @click="voltar()">
                    Cancelar
                  </button>
                </div>
              </div>
            </form>
          </div>
        </div>
      </div>
      <div class="col-md-12" v-else>
        <h4 class="mb-3 float-left">Transportes Registrados</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">ID do Pedido</th>
                <th scope="col">Endereço Destinatário</th>
                <th scope="col">Código de Rastreio</th>
                <th scope="col">Status</th>
                <th scope="col" class="text-center">Atualizar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="transporte of transportes" :key="transporte.id">
                <td>{{ transporte.pedidoId }}</td>
                <td>{{ transporte.enderecoDestinatario }}</td>
                <td>{{ transporte.codigoRastreio }}</td>
                <td>{{ transporte.status }}</td>
                <td class="text-center">
                  <button
                    @click="selecionar(transporte)"
                    class="btn btn-primary"
                    title="Atualizar"
                  >
                    <i class="fas fa-truck"></i>
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
import TransportesService from "../services/transportes";
import Commons from "../services/commons";

export default {
  name: "app",
  data() {
    return {
      transportes: [],
      transporteSelecionado: {},
      visualizandoTransporte: false,
      status: ["ENVIADO", "ENTREGUE"],
    };
  },
  mounted() {
    this.listar();
    this.visualizandoTransporte = false;
  },
  methods: {
    listar() {
      TransportesService.listar().then((resposta) => {
        this.transportes = resposta.data;
      });
    },
    selecionar(transporte) {
      this.transporteSelecionado = Object.assign({}, transporte);
      this.visualizandoTransporte = true;
    },
    voltar() {
      this.transporteSelecionado = {};
      this.visualizandoTransporte = false;
      this.listar();
    },
    atualizar() {
      TransportesService.atualizar(this.transporteSelecionado)
        .then((resposta) => {
          this.voltar();
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