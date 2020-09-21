<template>
  <div class="auto-card-create-form">
    <div class="query">
      <label for="query">{{ frontLabel }}</label>
      <input v-model="query" id="query"><br>
    </div>
    <select v-model="selectedId">
      <option v-for="(category, index) in categories"
        :key="index"
        :value="index">
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
    <button class="add" @click="add">{{ addButton }}</button>
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
      categories: [{id:1, name:"kategoria"}],
      query: '',
      // messages
      selectedId: (window.localStorage.getItem('last-category')===null)?null:parseInt(window.localStorage.getItem('last-category')),
      addButton,
      frontLabel
    }
  },
  computed: {
    selected: function () {
      var id = this.selectedId
      if (id === null || this.categories.length <= id)
        return {}
      return this.categories[this.selectedId]
    }
  },
  methods: {
    add: function () {
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
  },
  watch: {
    selectedId: function (to) {
      window.localStorage.setItem('last-category', to.toString())
    }
  }
}
</script>

<style scoped>
.query > * {
  margin: 12px;
}

.query > input { 
  width: 500px;
  height: 20px;
}

.add {
  width: 200px;
  height: 50px;
  margin: 20px;
  font-size: 20px;
}
</style>
