<template>
  <div class="login">
    <h3>{{ title }}</h3><br>
    <input type="text" v-model="username"><br>
    <input type="password" v-model="password" v-on:keyup.enter="submitLogin"><br>
    <input type="submit" :value="submitButton" v-on:click="submitLogin"><br>
    <span class="message">{{ message }}</span>
  </div>
</template>

<script>
import { submitButton } from '@/consts/messages.js'

export default {
  name: 'LoginForm',
  props: ['message', 'title'],
  data: function() {
    return {
      username: '',
      password: '',
      submitButton
    }
  },
  methods: {
    submitLogin: function() {
      this.$emit('login', this.username, this.password)
    }
  }
}
</script>

<style scoped>

h3 {
	font-size: 35px;
	margin-bottom: 0;
}

input[type="text"], input[type="password"] {
	height: 40px;
	width: 350px;
	font-size: 27px;
	margin: 10px;
	padding: 5px 8px;
}

input[type="submit"] {
	background-color: white;
	border: 2px solid mediumseagreen;
	border-radius: 30px;
	padding: 10px 15px;
	font-size: 22px;
}

.message {
	display: block;
	margin-top: 15px;
}

</style>
