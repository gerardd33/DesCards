<template>
  <div class="add-card">
    <button @click="setManual">Ręcznie</button>
    <button @click="setAuto">Automatycznie</button>
    <br>
    <component v-bind:is="form"
      @add-manual="manual"
      @add-auto="auto">
    </component>
  </div>
</template>

<script>
import Manual from '@/components/ManualAddForm.vue'
import Auto from '@/components/AutoCardForm.vue'

export default {
  name: 'AddCard',
  props: [],
  data: function () {
    return {
      form: Manual,
    }
  },
  methods: {
    manual: function (flashcard) {
      console.log(flashcard)
    },
    auto: function (fields) {
      console.log(fields)
    },
    setManual: function () {
      this.form = Manual
    },
    setAuto: function () {
      this.form = Auto
    }
  },
  computed: {
  },
  created: function () {
    // TODO read last used form from localstorage
  }
}
</script>

<style scoped>
</style>
