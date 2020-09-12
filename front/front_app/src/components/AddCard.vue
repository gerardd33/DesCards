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
import axios from 'axios'

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
      axios.post('/api/add_manual', flashcard)
      .then(function () {
        console.log('Dodano fiszkę')
      })
      .catch(function () {
        console.log('Dodanie nie powiodło się')
      })
    },
    auto: function (request) {
      console.log(request)
      axios.post('/api/add_auto', request)
      .then(function () {
        console.log('Dodano fiszkę')
      })
      .catch(function () {
        console.log('Dodanie nie powiodło się')
      })
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
