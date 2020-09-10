<template>
  <div class="auto-card-create-form">
    <select v-model="selected">
      <option v-for="(category, index) in categories"
        :key="index"
        :value="category">
        {{ category.name }}
      </option>
    </select>
    <div class="special-fields"
      v-for="(field, index) in selected.specialFields"
      :key="index">
      <input type="checkbox"
        :id="index"
        v-model="field.checked">
      <label :for="index">{{ field.name }}</label>
      <br>
    </div>
    <button @click="generate">generuj</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'AutoCardForm',
//  props: ['entry', 'index'],
  data: function () {
    return {
      categories: [],
      selected: {}
    }
  },
  methods: {
    remove: function () {
      this.deleted = true
      this.$emit('delete', this.index)
    },
    generate: function () {
      console.log(this.selected)
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
        }
      }
    })
  }
}
</script>

<style scoped>
</style>
