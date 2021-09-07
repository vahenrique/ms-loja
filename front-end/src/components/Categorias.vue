<template>
  <div class="container">
    <div class="row">
      <form @submit.prevent="salvar" method="POST" autocomplete="off">
        <div class="row">
          <div class="col-md mb-3">
            <label for="nome">Nome</label>
            <input
              type="text"
              class="form-control"
              placeholder=""
              v-model="categoria.nome"
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
        <h4 class="mb-3 float-left">Categorias Cadastrados</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">Nome</th>
                <th scope="col" class="text-center">Editar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="categoria of categorias" :key="categoria.id">
                <td>{{ categoria.nome }}</td>
                <td class="text-center">
                  <button
                    @click="editar(categoria)"
                    class="btn btn-primary"
                    title="Editar Dados"
                  >
                    <i class="fas fa-edit"></i>
                  </button>
                  <button
                    @click="remover(categoria)"
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
import CategoriasService from "./../services/categorias";
import Commons from "./../services/commons";

export default {
  name: "app",
  data() {
    return {
      categoria: {
        id: "",
        nome: "",
      },
      categorias: [],
    };
  },
  mounted() {
    this.listar();
  },
  methods: {
    listar() {
      CategoriasService.listar()
        .then((resposta) => {
          this.categorias = resposta.data;
        });
    },
    salvar() {
      if (!this.categoria.id) {
        CategoriasService.incluir(this.categoria)
          .then((resposta) => {
            this.categoria = {};
            alert("Cadastrada com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      } else {
        CategoriasService.atualizar(this.categoria)
          .then((resposta) => {
            this.categoria = {};
            alert("Atualizada com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      }
    },
    editar(categoria) {
      this.categoria = Object.assign({}, categoria);
    },
    remover(categoria) {
      if (confirm("Deseja excluir a categoria?")) {
        CategoriasService.deletar(categoria)
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