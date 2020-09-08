<template>
  <div class="deck">
    {{ entry.id }}
    {{ entry.name }}
    <button>Wybierz</button>
  </div>
</template>

<script>
export default {
  name: 'Deck',
  props: ['entry']
}
</script>

<style scoped>
</style>
