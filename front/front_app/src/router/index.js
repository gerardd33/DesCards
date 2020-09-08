import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Decks from '../views/Decks.vue'
import Deck from '../views/Deck.vue'

const routes = [
  {
    path: '/',
    name: 'Login',
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/decks',
    name: 'Decks',
    component: Decks
  },
  {
    path: '/deck',
    name: 'Deck',
    component: Deck
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
