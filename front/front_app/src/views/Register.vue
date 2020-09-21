<template>
  <div class="login-container">
    <login-form v-on:login="register"
      v-bind:message="message"
      v-bind:title="title"/>
  </div>
</template>

<script>
// @ is an alias to /src
import LoginForm from '@/components/LoginForm.vue'
import { registerPageTitle, accountCreated, registerError, usernameAlreadyTaken } from '@/consts/messages.js'
import axios from 'axios'

export default {
  name: 'Home',
  data: function() {
    return {
      test: 0,
      message: '',
      title: registerPageTitle
    }
  },
  components: {
    'login-form': LoginForm
  },
  methods: {
    register: function(username, password) {
      var vm = this
      axios.post('/api/register', 
                {username,
                 password})
      .then(function (response) {
          if (response.status === 200) {
            vm.message = accountCreated
          } else {
            vm.message = registerError
          }
      })
      .catch(function (error) {
        if (error.response.status === 409) {
          vm.message = usernameAlreadyTaken
        } else {
          vm.message = registerError
        }
      })
    }
  }
}
</script>
