<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
      <router-link to="/">
        <span @click="logout">Logout</span>
      </router-link> |
    </div>
    <router-view/>
  </div>
</template>

<script>
// import axios from 'axios'

export default {
  name: 'App',
  methods: {
    logout: function () {
      console.log('xD')
//      axios.post('/api/logout')
//      .then(function (response) {
//        if (response.status === 200) {
//          // pomyślnie wylogowano
//        } else {
//          // nie udało się wylogować
//        }
//      })
//      .catch(function () {
//        // nie udało się wylogować
//      })
    }
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>
