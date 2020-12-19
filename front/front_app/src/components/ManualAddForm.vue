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
  margin: 7px 14px 7px 14px;
}

.add {
  width: 250px;
  height: 65px;
  font-size: 25px;
}

input {
  height: 30px;
  width: 625px;
  font-size: 25px;
	margin: 25px;
	padding: 7px 14px;
}

[for="back"], [for="front"] {
  font-size: 20px;
}

</style>
