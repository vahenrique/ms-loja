<template>
  <nav
    class="navbar sticky-top navbar-expand-lg navbar-light navbar-header"
    style="background-color: #6db33f; margin-bottom: 2em"
  >
    <div class="container">
      <div class="collapse navbar-collapse" id="div-menu">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <button
              class="btn"
              style="background-color: transparent"
              title="Home"
              @click="ativar('home')"
            >
              <i class="fas fa-home"></i>
            </button>
          </li>
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
              Cadastros do Catálogo
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
              Cadastros da Loja
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownLoja">
              <a class="dropdown-item" href="#" @click="ativar('loja-infoloja')"
                >Informações da Loja</a
              >
              <a class="dropdown-item" href="#" @click="ativar('loja-cliente')"
                >Clientes</a
              >
            </div>
          </li>
          <li class="nav-item dropdown">
            <a
              class="nav-link dropdown-toggle"
              href="#"
              id="navbarDropdownPedido"
              role="button"
              data-toggle="dropdown"
              aria-haspopup="true"
              aria-expanded="false"
            >
              Pedidos
            </a>
            <div class="dropdown-menu" aria-labelledby="navbarDropdownPedido">
              <a
                class="dropdown-item"
                href="#"
                @click="ativar('pedido-digitacao')"
                >Digitação de Pedidos</a
              >
              <a
                class="dropdown-item"
                href="#"
                @click="ativar('pedido-acompanhamento')"
                >Acompanhamento de Pedidos</a
              >
            </div>
          </li>
        </ul>
      </div>
    </div>
  </nav>
  <Home v-if="tela === 'home'" />
  <Categorias v-if="tela === 'catalogo-categoria'" />
  <CatalogoItems v-if="tela === 'catalogo-item'" />
  <Clientes v-if="tela === 'loja-cliente'" />
  <InfoLoja v-if="tela === 'loja-infoloja'" />
  <DigitacaoPedidos v-if="tela === 'pedido-digitacao'" />
  <AcompanhamentoPedidos v-if="tela === 'pedido-acompanhamento'" />
</template>

<script>
import Home from "./components/Home.vue";
import Categorias from "./components/Categorias.vue";
import CatalogoItems from "./components/CatalogoItems.vue";
import Clientes from "./components/Clientes.vue";
import InfoLoja from "./components/InfoLoja.vue";
import DigitacaoPedidos from "./components/DigitacaoPedidos.vue";
import AcompanhamentoPedidos from "./components/AcompanhamentoPedido.vue";

export default {
  name: "App",
  components: {
    Home,
    Categorias,
    CatalogoItems,
    Clientes,
    InfoLoja,
    DigitacaoPedidos,
    AcompanhamentoPedidos,
  },
  data() {
    return {
      tela: "",
    };
  },
  mounted() {
    this.tela = "home";
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
