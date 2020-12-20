<template>
  <div class="auto-card-create-form" v-on:keyup.enter="add">
    <div class="query">
      <label for="query">{{ frontLabel }}</label>
      <input v-model="query" id="query"><br>
    </div>
    <div class="category-box">
    <select class="category-selection" v-model="selectedId">
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
    </div>
    <br>
    <button class="add" v-on:click="add">{{ addButton }}</button>
  </div>
</template>

<script>
import axios from 'axios'
import { addButton, frontLabel } from '@/consts/messages.js'

export default {
  name: 'AutoCardForm',
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
					field.checked = false
        }
      }
      var deckId = window.localStorage.getItem('deckId')
      this.$emit('add-auto', {query: this.query, specialFields, deckId})
			this.resetInput()
    },
		resetInput: function() {
			this.query = ''
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
  margin: 15px;
}

.query > input {
  width: 700px;
  height: 50px;
	font-size: 27px;
	padding: 7px 14px;
}

.add {
	width: 250px;
	height: 60px;
	margin: 25px;
	font-size: 25px;
}

.category-selection {
	font-size: 20px;
	padding: 7px;
	margin-bottom: 7px;
}

.special-fields {
  font-size: 20px;
  padding-left: 10px;
}

.category-box {
  display: inline-block;
  text-align: left;
}

.query {
  font-size: 20px;
}

</style>
