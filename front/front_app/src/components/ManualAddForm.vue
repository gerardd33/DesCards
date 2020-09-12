<template>
  <div class="flashcard-form">
    Front <input v-model="front">
    Back <input v-model="back">
    <button @click="add">Dodaj</button>
  </div>
</template>

<script>
export default {
  name: 'FlashcardForm',
  data: function () {
    return {
      front: '',
      back: ''
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
