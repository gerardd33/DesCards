<template>
  <div class="login">
    <h3>{{ title }}</h3><br>
    <input type="text" v-model="username"><br>
    <input type="password" v-model="password" v-on:keyup.enter="submitLogin"><br>
    <input type="submit" v-on:click="submitLogin"><br>
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
	font-size: 25px;
	margin-bottom: 0;
}

input[type="text"], input[type="password"] {
	height: 30px;
	width: 250px;
	font-size: 20px;
	margin: 10px;
	padding: 3px 5px;
}

input[type="submit"] {
	background-color: white;
	border: 2px solid mediumseagreen;
	border-radius: 30px;
	padding: 5px 10px;
	font-size: 16px;
}

.message {
	display: block;
	margin-top: 15px;
}

</style>
