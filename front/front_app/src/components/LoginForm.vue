<template>
  <div class="login">
    <h3>Zaloguj się</h3><br>
    <input type="text"><br>
    <input type="password"><br>
    <input type="submit" v-on:click="$emit('login')">
  </div>
</template>

<script>
export default {
  name: 'LoginForm',
}
</script>

<style scoped>
</style>
