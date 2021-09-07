<template>
  <div class="container">
    <div class="row">
      <form @submit.prevent="salvar" method="POST" autocomplete="off">
        <div class="row">
          <div class="col-md mb-3">
            <label for="nome">Nome</label>
            <input
              id="nome"
              type="text"
              class="form-control"
              placeholder=""
              v-model="infoLoja.nome"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <label for="cnpj">CNPJ</label>
            <input
              id="cnpj"
              type="text"
              class="form-control"
              placeholder=""
              v-model="infoLoja.cnpj"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <label for="endereco">Endereço</label>
            <input
              id="endereco"
              type="text"
              class="form-control"
              placeholder=""
              v-model="infoLoja.endereco"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <label for="email">E-mail</label>
            <input
              id="email"
              type="text"
              class="form-control"
              placeholder=""
              v-model="infoLoja.email"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <button type="submit" class="btn btn-success btn-block">
              Salvar
            </button>
          </div>
          <div class="col-md mb-3">
            <button class="btn btn-danger btn-block" @click="remover(infoLoja)">
              Excluir
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import InfoLojaService from "../services/infoLoja";
import Commons from "../services/commons";

export default {
  name: "app",
  data() {
    return {
      infoLoja: {
        id: "",
        nome: "",
        cnpj: "",
        endereco: "",
        email: ""
      }
    };
  },
  mounted() {
    this.visualizar();
  },
  methods: {
    visualizar() {
      InfoLojaService.visualizar().then((resposta) => {
        this.infoLoja = resposta.data;
      });
    },
    salvar() {
      if (!this.infoLoja.id) {
        InfoLojaService.incluir(this.infoLoja)
          .then((resposta) => {
            this.infoLoja = {};
            alert("Cadastrado com sucesso!");
            this.visualizar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      } else {
        InfoLojaService.atualizar(this.infoLoja)
          .then((resposta) => {
            this.infoLoja = {};
            alert("Atualizado com sucesso!");
            this.visualizar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      }
    },
    remover(infoLoja) {
      if (confirm("Deseja excluir?")) {
        InfoLojaService.deletar(infoLoja)
          .then((resposta) => {
            this.visualizar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      }
    },
  },
};
</script>

<style>
</style>