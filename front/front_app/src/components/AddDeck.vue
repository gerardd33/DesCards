<template>
  <div class="add-deck" v-on:keyup.enter="add">
    <label for="front">{{ deckNameLabel }}</label><br>
    <input id="front" v-model="deckName">
    <br>
    <button v-on:click="add">{{ saveButton }}</button>
  </div>
</template>

<script>
import { deckNameLabel, saveButton } from '@/consts/messages.js'
import axios from 'axios'

export default {
  name: 'AddDeck',
  data: function () {
    return {
      deckName: '',
      // messages
      saveButton,
      deckNameLabel
    }
  },
  methods: {
    add: function () {
      axios.post('/api/add_deck', {name: this.deckName})
      window.location.reload()
    }
  }
}
</script>

<style scoped>

input#front {
	height: 30px;
	width: 175px;
	font-size: 18px;
	margin: 5px;
	padding: 3px 5px;
}

</style>
