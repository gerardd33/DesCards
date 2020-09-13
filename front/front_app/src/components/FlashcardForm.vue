<template>
  <div class="flashcard-form">
    <label for="front">{{ frontLabel }}</label>
    <input id="front" :value="flashcard.front" @input="update('front', $event.target.value)">
    <br>
    <label for="back">{{ backLabel }}</label>
    <input id="back" :value="flashcard.back" @input="update('back', $event.target.value)">
    <br>
    <button @click="$emit('hide')">Zapisz</button>
  </div>
</template>

<script>
import { frontLabel, backLabel, saveButton } from '@/consts/messages.js'
export default {
  name: 'FlashcardForm',
  props: ['flashcard'],
  data: function () {
    return {
      // messages
      frontLabel,
      backLabel,
      saveButton
    }
  },
  methods: {
    update: function (key, value) {
      var a = { ...this.flashcard, [key]: value }
      console.log(a)
      this.$emit('xd', a) 
    }
  },
  computed: {
    local() {
      return this.flashcard ? this.flashcard : {}
    },
  },
  created: function () {
    console.log(this.flashcard)
  }
}
</script>

<style scoped>
</style>
