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
              v-model="item.nome"
            />
          </div>
        </div>

         <div class="row">
          <div class="col-md mb-3">
            <label for="valor">Valor</label>
            <input
              id="valor"
              type="number"
              class="form-control"
              placeholder=""
              v-model="item.valor"
            />
          </div>
        </div>

        <div class="row">
          <div class="col-md mb-3">
            <label for="categoria">Categoria</label>
            <select
              class="form-control"
              id="categoria"
              v-model="item.categoriaId"
            >
              <option
                v-for="categoria of categorias"
                :key="categoria.id"
                :value="categoria.id"
              >
                {{ categoria.nome }}
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
        </div>
      </form>
    </div>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-12">
        <h4 class="mb-3 float-left">Itens Cadastrados</h4>
        <div class="table-responsive">
          <table class="table table-sm">
            <thead>
              <tr>
                <th scope="col">Nome</th>
                <th scope="col">Valor</th>
                <th scope="col">Categoria</th>
                <th scope="col" class="text-center">Editar</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item of items" :key="item.id">
                <td>{{ item.nome }}</td>
                <td>{{ item.valor }}</td>
                <td>{{ item.categoriaNome }}</td>
                <td class="text-center">
                  <button
                    @click="editar(item)"
                    class="btn btn-primary"
                    title="Editar Dados"
                  >
                    <i class="fas fa-edit"></i>
                  </button>
                  <button
                    @click="remover(item)"
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
import CatalogoItemService from "../services/catalogoItems";
import CategoriasService from "../services/categorias";
import Commons from "../services/commons";

export default {
  name: "app",
  data() {
    return {
      item: {
        id: "",
        nome: "",
        valor: "",
        categoriaId: "",
      },
      items: [],
      categorias: [],
    };
  },
  mounted() {
    this.listar();
    CategoriasService.listar().then((resposta) => {
      this.categorias = resposta.data;
    });
  },
  methods: {
    listar() {
      CatalogoItemService.listar().then((resposta) => {
        this.items = resposta.data;
      });
    },
    salvar() {
      if (!this.item.id) {
        console.log(this.item);
        CatalogoItemService.incluir(this.item)
          .then((resposta) => {
            this.item = {};
            alert("Cadastrado com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      } else {
        CatalogoItemService.atualizar(this.item)
          .then((resposta) => {
            this.item = {};
            alert("Atualizado com sucesso!");
            this.listar();
            console.log(resposta);
          })
          .catch((e) => {
            alert(Commons.formatarErro(e.response.data));
          });
      }
    },
    editar(item) {
      this.item = Object.assign({}, item);
    },
    remover(item) {
      if (confirm("Deseja excluir o item?")) {
        CatalogoItemService.deletar(item)
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