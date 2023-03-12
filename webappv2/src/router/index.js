import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import SignInView from "@/views/SignInView.vue";
import SignUpView from "@/views/SignUpView.vue";
import StudentPOView from "@/views/StudentPOView.vue";
import UniversityPOView from "@/views/UniversityPOView.vue";

Vue.use(VueRouter)

const routes = [
  {
    path: '/welcome',
    component: HomeView
  },
  {
    path: '/signin',
    component: SignInView
  },
  {
    path: '/signup',
    component: SignUpView
  },
  {
    path: '/',
    component: StudentPOView
  },
  {
    path: '/u',
    component: UniversityPOView
  }

]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
