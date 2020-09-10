<template>
  <div class="flashcard" v-bind:class="{deleted: deleted}">
    <span>{{ entry.id }}</span>
    <span>{{ entry.front }}</span>
    <span>{{ entry.back }}</span>
    <span>{{ entry.interval }}</span>
    <button @click="$emit('edit', index)">Edytuj</button>
    <button @click="remove">Usuń</button>
  </div>
</template>

<script>
export default {
  name: 'Flashcard',
  props: ['entry', 'index'],
  data: function () {
    return {
      deleted: false
    }
  },
  methods: {
    remove: function () {
      this.deleted = true
      this.$emit('delete', this.index)
    }
  }
}
</script>

<style scoped>
.deleted {
    background-color: red;
}
</style>
