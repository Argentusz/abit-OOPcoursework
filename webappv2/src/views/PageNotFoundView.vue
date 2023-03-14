<template>
<div class="err404">
  <div class="mars-pic">
    <img
        :src="require('@/assets/404-upper-layer.png')"
        alt=""
        class="mars-pic-img"
    />
  </div>
  <div class="err-text">
    <b class="err-title">
      {{$t('error')}} 404<br/>
      {{$t('pageNotFound')}}<br/>
    </b>
  </div>
  <div class="err-subtext">
    {{$t('pageNotFoundDesc')}}
  </div>
</div>
</template>

<script>
export default {
  name: "PageNotFoundView"
}
</script>

<style scoped>
.err404 {
  height: calc(100vh - 60px);
  width: 100vw;
  background-image: url("@/assets/404.png");
}
.mars-pic {
  position: absolute;
  bottom: 0;
  left: 0;
  z-index: 2;
}
.mars-pic-img {

}
.err-text {
  font-size: 64px;
  position: absolute;
  z-index:1;
  left: 950px;
  top: 325px;
  text-align: center;
}
.err-subtext {
  font-size: 55px;
  position: absolute;
  z-index: 3;
  left: 720px;
  top: 500px;
  text-align: center;
  text-shadow: 2px 2px #343a40;
}
</style>