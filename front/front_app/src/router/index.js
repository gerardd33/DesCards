import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Decks from '../views/Decks.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/decks',
    name: 'Decks',
    component: Decks
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
