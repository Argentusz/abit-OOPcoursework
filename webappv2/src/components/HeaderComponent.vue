<template>
  <b-navbar type="dark" variant="dark" fixed="top" class="header">
<!--Left side of header-->
    <b-navbar-brand>
      <button style="border:none;background: none" @click="$router.push('/welcome')">
      <img width="55px" :src="require('@/assets/logo-yellow.png')" alt="МаксУслуги" />
      </button>
    </b-navbar-brand>
<!--Right side of header-->
    <b-navbar-nav class="ml-auto">
      <b-nav-item-dropdown :text="$t('language')" right>
        <b-dropdown-item @click="setLocale('ru')">Русский</b-dropdown-item>
        <b-dropdown-item @click="setLocale('ing')">Ingirmanlandin</b-dropdown-item>
        <b-dropdown-item @click="setLocale('en')">English</b-dropdown-item>
        <b-dropdown-item @click="setLocale('pl')">Polski</b-dropdown-item>
      </b-nav-item-dropdown>
      <b-button class="bButton" variant="warning" v-if="isWelcomePage || $route.path === '/404'" @click="$router.push('/signin')">{{$t('signIn')}}</b-button>
      <b-button class="bButton" variant="warning" v-if="isWelcomePage || $route.path === '/404'" @click="$router.push('/signup')">{{$t('signUp')}}</b-button>
    </b-navbar-nav>

  </b-navbar>
</template>

<script>
export default {
  name: "HeaderComponent",
  props: {
    isWelcomePage: Boolean,
  },
  methods: {
    setLocale(locale) {
      this.$i18n.locale = locale
      localStorage.setItem('lang', locale)
      if (!this.isWelcomePage && this.$route.path !== '/404') {
        this.$bvToast.toast(this.$t('pageReloadToastBody'),
            {
              title: this.$t('pageReloadToast'),
              variant: 'warning',
              solid: true,
              toaster: 'b-toaster-bottom-right'
            }
        )
      }
    }
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