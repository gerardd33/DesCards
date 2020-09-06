<template>
  <div class="login-container">
    <login-form v-on:login="login()" v-bind:message="message"/>
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
    login: function() {
      var vm = this
      // get access token from server
      axios.post('/api/login', 
                {username: 'admin',
                 password: 'admin'})
      .then(function (response) {
        if (response.status === 200) {
          // redirect to main page
        } else {
          vm.message = 'Niepoprawny login lub hasło'
        }
      })
      .catch(function (error){
        console.log(error)
      })
    }
  }
}
</script>
