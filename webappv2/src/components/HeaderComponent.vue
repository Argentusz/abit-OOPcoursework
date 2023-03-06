<template>
  <b-navbar type="dark" variant="dark" fixed="top" class="header">
<!--Left side of header-->
    <b-navbar-brand>
      <img width="55px" :src="require('@/assets/logo-yellow.png')" alt="МаксУслуги"/>
    </b-navbar-brand>
<!--Right side of header-->
    <b-navbar-nav class="ml-auto" v-if="isWelcomePage">
      <b-button class="bButton" variant="warning" v-if="isWelcomePage">Войти</b-button>
      <b-button class="bButton" variant="warning" v-if="isWelcomePage">Зарегистрироваться</b-button>
    </b-navbar-nav>

  </b-navbar>
</template>

<script>
export default {
  name: "HeaderComponent",
  props: {
    isWelcomePage: Boolean,
    isRegistration: Boolean,
    isLoggedIn: Boolean,
  }
}
</script>

<style scoped>
.header {
  max-height: 64px;
}
.bButton {
  cursor: pointer;
  margin-right: 5px;
}
</style>