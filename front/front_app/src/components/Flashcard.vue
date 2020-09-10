<template>
  <div class="flashcard">
    <span>{{ entry.id }}</span>
    <span>{{ entry.front }}</span>
    <span>{{ entry.back }}</span>
    <span>{{ entry.interval }}</span>
    <button @click="$emit('edit', index)">Edytuj</button>
    <button @click="$emit('delete', index)">Usuń</button>
  </div>
</template>

<script>
export default {
  name: 'Flashcard',
  props: ['entry', 'index'],
  methods: {
  }
}
</script>

<style scoped>
</style>
