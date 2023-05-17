<template>
  <div id="app">
    <HeaderComponent :is-welcome-page="($route.path === '/welcome')" :is-logged-in="false"></HeaderComponent>
    <router-view  class="routerView"/>
    <FooterComponent/>
  </div>
</template>

<script setup>
import HeaderComponent from "@/components/HeaderComponent.vue";
import FooterComponent from "@/components/FooterComponent.vue";

</script>

<style scoped>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #fff;
  font-size:12px;
}
.routerView {
  margin-top: 45px;
}
</style>

<style lang="scss">
@import "~ag-grid-community/styles/ag-grid.css";
@import "~ag-grid-community/styles/ag-theme-balham.css";
</style>
