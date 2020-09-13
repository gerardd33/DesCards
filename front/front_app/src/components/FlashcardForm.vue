<template>
  <div class="flashcard-form">
    Front <input :value="flashcard.front" @input="update('front', $event.target.value)">
    Back <input :value="flashcard.back" @input="update('back', $event.target.value)">
    <button @click="$emit('hide')">Zapisz</button>
  </div>
</template>

<script>
export default {
  name: 'FlashcardForm',
  props: ['flashcard'],
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
