<template>
  <div class="flashcard-form" v-on:keyup.enter="$emit('hide')">
    <label for="front">{{ frontLabel }}</label><br>
    <input id="front" :value="flashcard.front" v-on:input="update('front', $event.target.value)">
    <br>
    <label for="back">{{ backLabel }}</label><br>
    <input id="back" :value="flashcard.back" v-on:input="update('back', $event.target.value)">
    <br>
    <button v-on:click="$emit('hide')">Save</button>
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
      this.$emit('update-card', a)
    }
  },
  computed: {
    local() {
      return this.flashcard ? this.flashcard : {}
    },
  }
}
</script>

<style scoped>

input#front, input#back {
	height: 40px;
	width: 350px;
	font-size: 20px;
	margin: 5px;
	padding: 3px 5px;
}

button {
  margin-top: 10px;
  height: 40px;
  width: 90px;
  font-size: 20px;
  padding: 5px;
}

[for="back"], [for="front"] {
  font-size: 20px;
}

</style>
