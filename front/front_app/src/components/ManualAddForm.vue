<template>
  <div class="flashcard-form">
    <table>
      <tr>
        <td><label for="front">{{ frontLabel }}</label></td>
        <td><input v-model="front"></td>
      </tr>
      <tr>
        <td><label for="back">{{ backLabel }}</label></td>
        <td><input class="back" v-model="back"></td>
      </tr>
    </table>
    <br>
    <button class="add" @click="add">{{ addButton }}</button>
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
    }
  },
}
</script>

<style scoped>
table {
  display: inline;
}

td > input {
  margin: 0px 12px 0px 12px;
  
}

.add {
  width: 200px;
  height: 50px;
  margin: 20px;
  font-size: 20px;
}

input {
  height: 20px;
  width: 500px;
  font-size: 20px;
}
</style>
