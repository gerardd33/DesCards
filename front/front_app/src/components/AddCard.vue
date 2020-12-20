<template>
  <div class="add-card">
    <button v-on:click="setManual">Manual</button>
    <button v-on:click="setAuto">Automatic</button>
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
      var vm = this
      axios.post('/api/add_manual', flashcard)
      .then(function () {
        vm.$emit('added')
      })
    },
    auto: function (request) {
      var vm = this
      axios.post('/api/add_auto', request)
      .then(function () {
        vm.$emit('added')
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
  width: 200px;
  height: 50px;
  margin: 10px 15px;
  font-size: 22px;
}
</style>
