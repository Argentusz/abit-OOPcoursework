<template>
  <div class="home__container">
    <div class="home">
        <p class="welcomeTitle" v-html="$t('welcome')"/>
      <div class="welcomeSubtitle">
        {{$t('welcomeSubtitle')}}
      </div>
    </div>

    <div class="home2" >
      <p class="home2welcomeTitle" v-animate-onscroll="{down: 'animated fadeInRight'}"><b>{{$t('howToEnterUniversity')}}</b></p>
      <p v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep1')}}</p>
      <p v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep2')}}</p>
      <p v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep3')}}</p>
      <p v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep4')}}</p>
      <p v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep5')}}</p>
      <p style="color: #ffc107" v-animate-onscroll="{down: 'animated fadeInRight'}">{{$t('howToStep6')}}</p>
    </div>
  </div>
</template>

<script>
export default {
  name: 'HomeView',
}
</script>

<style scoped>
.home__container {
  background: url("@/assets/hellopagebg.png") repeat-x fixed left top;
  background-size: 1600px 900px;
  width: 100vw;
  top: 0;
  margin:0;

}
.home {
  height: calc(100vh);
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
.home2 {
  font-size: calc(2vw + 9px);
  margin-left: 5vw;
  height: 100vh;
  margin-top: 200px;
}
.home2welcomeTitle {
  font-size: calc(3vw + 20px);
}
</style>