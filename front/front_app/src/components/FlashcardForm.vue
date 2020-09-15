<template>
  <div class="flashcard-form">
    <label for="front">{{ frontLabel }}</label><br>
    <input id="front" :value="flashcard.front" @input="update('front', $event.target.value)">
    <br>
    <label for="back">{{ backLabel }}</label><br>
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
      this.$emit('update-card', a)
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

input#front, input#back {
	height: 30px;
	width: 175px;
	font-size: 18px;
	margin: 5px;
	padding: 3px 5px;
}

</style>
