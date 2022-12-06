import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/homeView.vue'
import AboutView from '../views/aboutView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
   component: HomeView
  },
  {
    path: '/about',
    name: 'about',
    component: AboutView
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/* webpackChunkName: "about" */ '../views/aboutView.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
