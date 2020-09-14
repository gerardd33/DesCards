<template>
  <div class="login">
    <h3>{{ title }}</h3><br>
    <input type="text" v-model="username"><br>
    <input type="password" v-model="password"><br>
    <input type="submit" v-on:click="$emit('login', username, password)"><br>
    <span class="message">{{ message }}</span>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  props: ['message', 'title'],
  data: function() {
    return {
      username: '',
      password: ''
    }
  }
}
</script>

<style scoped>
</style>
