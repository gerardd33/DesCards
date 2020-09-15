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
  data: function () {
    return {
      form: (window.localStorage.getItem('last-form') === 'manual')?Manual:Auto
    }
  },
  methods: {
    manual: function (flashcard) {
      console.log(flashcard)
      var vm = this
      axios.post('/api/add_manual', flashcard)
      .then(function () {
        console.log('Dodano fiszkę')
        vm.$emit('added')
      })
      .catch(function (error) {
        console.log('Dodanie nie powiodło się', error)
      })
    },
    auto: function (request) {
      console.log(request)
      var vm = this
      axios.post('/api/add_auto', request)
      .then(function () {
        console.log('Dodano fiszkę')
        vm.$emit('added')
      })
      .catch(function () {
        console.log('Dodanie nie powiodło się')
      })
    },
    setManual: function () {
      this.form = Manual
      window.localStorage.setItem('last-form', 'manual')
    },
    setAuto: function () {
      this.form = Auto
      window.localStorage.setItem('last-form', 'auto')
    }
  },
}
</script>

<style scoped>
button {
  width: 150px;
  height: 30px;
  margin: 12px;
}
</style>
