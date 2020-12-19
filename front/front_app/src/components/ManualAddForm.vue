<template>
  <div class="flashcard-form" v-on:keyup.enter="add">
    <table>
      <tr>
        <td><label for="front">{{ frontLabel }}</label></td>
        <td><input id="front" v-model="front"></td>
      </tr>
      <tr>
        <td><label for="back">{{ backLabel }}</label></td>
        <td><input id="back" class="back" v-model="back"></td>
      </tr>
    </table>
    <br>
    <button class="add" v-on:click="add">{{ addButton }}</button>
  </div>
</template>

<script>
import { frontLabel, backLabel, addButton } from '@/consts/messages.js'

export default {
  name: 'FlashcardForm',
  data: function () {
    return {
      front: '',
      back: '',
      // messages
      frontLabel,
      backLabel,
      addButton
    }
  },
  methods: {
    add: function () {
      var deckId = window.localStorage.getItem('deckId')
      this.$emit('add-manual', {front: this.front, back: this.back, deckId})
			this.resetInput()
    },
		resetInput: function() {
			this.front = ''
			this.back = ''
		}
  },
}
</script>

<style scoped>
table {
  display: inline;
}

td > input {
  margin: 6px 12px 6px 12px;
}

.add {
  width: 200px;
  height: 50px;
  font-size: 20px;
}

input {
  height: 25px;
  width: 500px;
  font-size: 20px;
	margin: 20px;
	padding: 5px 10px;
}
</style>
