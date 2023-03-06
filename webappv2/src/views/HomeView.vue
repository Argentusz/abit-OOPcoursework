<template>
  <div class="home">
    <div class="welcomeTitle">
      Добро пожаловать на<br/>
      <b>МаксУслуги</b>
    </div>
    <div class="welcomeSubtitle">
      Новейшая платформа межпланетной республики Ингирманландия
      для ВУЗов и студентов, которая облегчит процесс поступления
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
}
</script>

<style scoped>
.home {
  background: url("@/assets/hellopagebg.png") center/cover;
  height: calc(100vh - 64px);
  padding-top: 24vh;
}
.welcomeTitle {
    font-size: calc(3vw + 20px);
  margin-left: 5vw;
}
.welcomeSubtitle {
  font-size:calc(2vw + 10px);
  margin-left: 5vw;
  width: 50vw;
}
</style>