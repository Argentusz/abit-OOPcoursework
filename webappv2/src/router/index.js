import Vue from 'vue'
import VueRouter from 'vue-router'
import HomeView from "@/views/HomeView.vue"
import SignInView from "@/views/SignInView.vue";
import SignUpView from "@/views/SignUpView.vue";
import StudentPOView from "@/views/StudentPOView.vue";
import UniversityPOView from "@/views/UniversityPOView.vue";
import FindUniversity from "@/views/FindUniversity.vue";
import FindCourse from "@/views/FindCourse.vue";
import PageNotFoundView from "@/views/PageNotFoundView.vue";
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
  },
  {
    path: '/find/university',
    component: FindUniversity
  },
  {
    path: '/find/course',
    component: FindCourse
  },
  {
    path: '/404',
    component: PageNotFoundView
  },
  {
    path: '/*',
    redirect: '/404'
  }
]

const router = new VueRouter({
  mode: 'history',
  routes
})

export default router
