<template>
  <div class="login-container">
    <login-form v-on:login='login()'/>
    {{ test }}
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
      test: 0
    }
  },
  components: {
    'login-form': LoginForm
  },
  methods: {
    login: function() {
      // var vm = this
      // get access token from server
      axios.post('/api/login', 
                {username: 'admin',
                 password: 'admin'})
      .then(function (response) {
        console.log(response.data.test)
      })
      .catch(function (error){
        console.log(error)
      })
    }
  }
}
</script>
