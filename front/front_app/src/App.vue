<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Login</router-link> |
      <router-link to="/decks">Home</router-link> |
      <router-link to="/">
        <span v-on:click="logout">Logout</span>
      </router-link> 
    </div>
    <router-view/>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  methods: {
    logout: function () {
      axios.post('/api/logout')
      window.localStorage.removeItem('login')
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
  font-size: 22px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

button, input {
  margin: 12px;
}

button {
	background-color: white;
	border: 2px solid mediumseagreen;
	border-radius: 30px;
	padding: 5px 10px;
	font-size: 16px;
}

</style>
