<template>
<div class="footer">
  <p class="sub-sign" v-html="$t('footerWarning')">
  </p>
  <div class="flag">
    <img class="flagImg" :src="require('@/assets/ingFlag.png')" alt="Флаг межпланетной Ингирманландской республики"/>
    {{$t('departmentOfEducation')}}
  </div>
</div>
</template>

<script>
export default {
  name: "FooterComponent"
}
</script>

<style scoped>
.footer {
  position: relative;
  background-color: #343a40;
  height: 220px;
}
.sub-sign {
  margin-left: 30px;
  position: absolute;
  bottom: 10px;
}
.flag {
  position: absolute;
  right: 30px;
  bottom: 10px;
  display: flex;
  flex-direction: column;
  width: 300px;
}
.flagImg {
  width: 200px;
}
</style>