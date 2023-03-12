import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import {i18n} from './plugins/i18n'
import { BootstrapVue, IconsPlugin  } from 'bootstrap-vue'
import VueGoodTablePlugin from 'vue-good-table';

// import the styles
import 'vue-good-table/dist/vue-good-table.css'
// Import Bootstrap and BootstrapVue CSS files (order is important)
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// Make BootstrapVue available throughout your project
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)
Vue.use(VueGoodTablePlugin);


Vue.config.productionTip = false
Vue.config.silent = true
new Vue({
  i18n,
  router,
  store,
  render: h => h(App)
}).$mount('#app')

