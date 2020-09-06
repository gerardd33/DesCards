<template>
  <div class="login-container">
    <login-form v-on:login="login" v-bind:message="message"/>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginForm from '@/components/LoginForm.vue'
import axios from 'axios'

export default {
  name: 'Home',
  data: function() {
    return {
      test: 0,
      message: ''
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
            vm.$router.push('/decks')
          } else {
            vm.message = 'Logowanie nie powiodło się'
          }
      })
      .catch(function (error){
        if (error.response) {
          if (error.response.status === 403) {
            vm.message = 'Niepoprawny login lub hasło'
          } else {
            vm.message = 'Logowanie nie powiodło się'
          }
        } else {
          vm.message = 'Logowanie nie powiodło się'
        }
      })
    }
  }
}
</script>
