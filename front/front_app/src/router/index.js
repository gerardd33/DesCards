import { createRouter, createWebHistory } from 'vue-router'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import Decks from '../views/Decks.vue'
import Deck from '../views/Deck.vue'
import Study from '../views/Study.vue'
import Flashcards from '../views/Flashcards.vue'

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
  },
  {
    path: '/study',
    name: 'Study',
    component: Study
  },
  {
    path: '/flashcards',
    name: 'Flashcards',
    component: Flashcards
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

router.beforeEach(function (to, from, next) {
  if (to.path !== '/' && to.path !== '/register' && window.localStorage.getItem('login') !== 'true') {
    next('/')
  } else {
    next()
  }
})

export default router
