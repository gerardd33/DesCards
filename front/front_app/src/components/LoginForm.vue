<template>
  <div class="login">
    <h3>Zaloguj się</h3><br>
    <input type="text"><br>
    <input type="password"><br>
    <input type="submit" v-on:click="$emit('login')"><br>
    <span class="message">{{ message }}</span>
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
  props: ['message']
}
</script>

<style scoped>
</style>
