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
              v-model="cliente.nome"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <label for="cpf">CPF</label>
            <input
              id="cpf"
              type="text"
              class="form-control"
              placeholder=""
              v-model="cliente.cpf"
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
              v-model="cliente.endereco"
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
              v-model="cliente.email"
            />
          </div>
        </div>
        <div class="row">
          <div class="col-md mb-3">
            <button type="submit" class="btn btn-success btn-block">
              Salvar
            </button>
          </div>
        </div>
      </form>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h4 class="mb-3 float-left">Clientes Cadastrados</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">Nome</th>
                <th scope="col">CPF</th>
                <th scope="col" class="text-center">Editar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="cliente of clientes" :key="cliente.id">
                <td>{{ cliente.nome }}</td>
                <td>{{ cliente.cpf }}</td>
                <td class="text-center">
                  <button
                    @click="editar(cliente)"
                    class="btn btn-primary"
                    title="Editar Dados"
                  >
                    <i class="fas fa-edit"></i>
                  </button>
                  <button
                    @click="remover(cliente)"
                    class="btn btn-danger"
                    title="Editar Dados"
                  >
                    <i class="fas fa-times"></i>
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
import ClientesService from "../services/clientes";
import Commons from "../services/commons";

export default {
  name: "app",
  data() {
    return {
      cliente: {
        id: "",
        nome: "",
        cpf: "",
        endereco: "",
        email: "",
      },
      clientes: [],
    };
  },
  mounted() {
    this.listar();
  },
  methods: {
    listar() {
      ClientesService.listar().then((resposta) => {
        this.clientes = resposta.data;
      });
    },
    salvar() {
      if (!this.cliente.id) {
        ClientesService.incluir(this.cliente)
          .then((resposta) => {
            this.cliente = {};
            alert("Cadastrado com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      } else {
        ClientesService.atualizar(this.cliente)
          .then((resposta) => {
            this.cliente = {};
            alert("Atualizado com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      }
    },
    editar(cliente) {
      this.cliente = Object.assign({}, cliente);
    },
    remover(cliente) {
      if (confirm("Deseja excluir o cliente?")) {
        ClientesService.deletar(cliente)
          .then((resposta) => {
            this.listar();
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