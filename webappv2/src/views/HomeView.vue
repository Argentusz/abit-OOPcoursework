<template>
  <div class="home__container">
    <div class="home">
        <p class="welcomeTitle" v-html="$t('welcome')"/>
      <div class="welcomeSubtitle">
        {{$t('welcomeSubtitle')}}
      </div>
    </div>

    <div class="home" id="2">

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