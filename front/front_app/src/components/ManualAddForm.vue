<template>
  <div class="flashcard-form">
    <label for="front">{{ frontLabel }}</label>
    <input v-model="front">
    <br>
    <label for="back">{{ backLabel }}</label>
    <input v-model="back">
    <br>
    <button @click="add">{{ addButton }}</button>
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
</style>
