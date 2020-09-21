<template>
  <div class="login-container">
    <login-form v-on:login="login"
      v-bind:message="message"
      v-bind:title="title"/>
    <router-link to="/register">{{ registerPrompt }}</router-link>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginForm from '@/components/LoginForm.vue'
import { loginTitle, registerPrompt, wrongCredentials, loginError } from '@/consts/messages.js'
import axios from 'axios'

export default {
  name: 'Home',
  data: function() {
    return {
      test: 0,
      message: '',
      title: loginTitle,
			registerPrompt: registerPrompt
    }
  },
  components: {
    'login-form': LoginForm
  },
  methods: {
    login: function(username, password) {
      var vm = this
      // get access token from server
      axios.post('/api/login', 
                {username,
                 password})
      .then(function (response) {
          if (response.status === 200) {
            // Successful authentication
            vm.$router.push('/decks')
            window.localStorage.setItem('login', 'true')
          } else {
            vm.message = loginError
          }
      })
      .catch(function (error){
        if (error.response) {
          if (error.response.status === 403) {
            vm.message = wrongCredentials
          } else {
            vm.message = loginError
          }
        } else {
          vm.message = loginError
        }
      })
    }
  }
}
</script>
