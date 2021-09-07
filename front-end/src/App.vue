<template>
  <nav
    class="navbar sticky-top navbar-expand-lg navbar-light navbar-header"
    style="background-color: #6db33f; margin-bottom: 2em"
  >
    <div class="container">
      <div class="collapse navbar-collapse" id="div-menu">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownCatalogo"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Catálogo
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownCatalogo">
              <a
                class="dropdown-item"
                href="#"
                @click="ativar('catalogo-categoria')"
                >Categorias</a
              >
              <a class="dropdown-item" href="#" @click="ativar('catalogo-item')"
                >Itens</a
              >
            </div>
          </li>
        </ul>
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownLoja"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Loja
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownLoja">
              <a class="dropdown-item" href="#" @click="ativar('loja-cliente')"
                >Clientes</a
              >
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <Categorias v-if="tela === 'catalogo-categoria'" />
  <CatalogoItems v-if="tela === 'catalogo-item'" />
  <Clientes v-if="tela === 'loja-cliente'" />
</template>

<script>
import Categorias from "./components/Categorias.vue";
import CatalogoItems from "./components/CatalogoItems.vue";
import Clientes from "./components/Clientes.vue";

export default {
  name: "App",
  components: {
    Categorias,
    CatalogoItems,
    Clientes,
  },
  data() {
    return {
      tela: "",
    };
  },
  methods: {
    ativar(telaAtiva) {
      this.tela = telaAtiva;
    },
  },
};
</script>

<style>
</style>
