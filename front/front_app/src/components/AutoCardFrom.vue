<template>
  <div class="auto-card-create-form">
    <select v-model="selected">
      <option v-for="(category, index) in categories"
        :key="index"
        :value="category">
        {{ category.name }}
      </option>
    </select>
    <input type="checkbox"
      v-for="(field, index) in selected.specialFields"
      :key="index">
  </div>
</template>

<script>
export default {
  name: 'AutoCardForm',
//  props: ['entry', 'index'],
  data: function () {
    return {
      categories: []
    }
  },
  methods: {
    remove: function () {
      this.deleted = true
      this.$emit('delete', this.index)
    }
  },
  created: function () {
    // get categories
    axios.get('/api/categories')
    .then(function (response) {
      if (response.status === 200) {
        vm.categories = response.data
      }
    })
  }
}
</script>

<style scoped>
</style>
