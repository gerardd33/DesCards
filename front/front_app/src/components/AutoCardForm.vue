<template>
  <div class="auto-card-create-form">
    <label for="query">{{ frontLabel }}</label>
    <input v-model="query" id="query"><br>
    <select v-model="selected">
      <option v-for="(category, index) in categories"
        :key="index"
        :value="category">
        {{ category.name }}
      </option>
    </select>
    <br>
    <div class="special-fields"
      v-for="(field, index) in selected.specialFields"
      :key="index">
      <input type="checkbox"
        :id="index"
        v-model="field.checked">
      <label :for="index">{{ field.name }}</label>
      <br>
    </div>
    <br>
    <button @click="add">{{ addButton }}</button>
  </div>
</template>

<script>
import axios from 'axios'
import { addButton, frontLabel } from '@/consts/messages.js'

export default {
  name: 'AutoCardForm',
//  props: ['entry', 'index'],
  data: function () {
    return {
      categories: [],
      selected: {},
      query: '',
      // messages
      addButton,
      frontLabel
    }
  },
  methods: {
    add: function () {
      console.log(this.selected)
      var specialFields = []
      for (var field of this.selected.specialFields) {
        if (field.checked) {
          specialFields.push(field.name)
        }
      }
      var deckId = window.localStorage.getItem('deckId')
      this.$emit('add-auto', {query: this.query, specialFields, deckId})
    }
  },
  created: function () {
    // get categories
    var vm = this
    axios.get('/api/categories')
    .then(function (response) {
      if (response.status === 200) {
        vm.categories = response.data.map((value) => { 
          return {...value, specialFields: value.specialFields.map((name) => {return {name, checked: false}})} 

        })
        if (vm.categories.length > 0) {
          vm.selected = vm.categories[0]
          // TODO choose last category
        }
      }
    })
  }
}
</script>

<style scoped>
</style>
