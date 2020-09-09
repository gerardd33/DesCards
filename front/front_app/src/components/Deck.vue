<template>
  <div class="deck">
    {{ entry.id }}
    {{ entry.name }}
    <button @click="choose">Wybierz</button>
  </div>
</template>

<script>
export default {
  name: 'Deck',
  props: ['entry'],
  methods: {
    choose: function () {
      window.localStorage.setItem('deck', this.entry.name)
      window.localStorage.setItem('deckId', this.entry.id)
      this.$router.push('/deck')
    }
  }
}
</script>

<style scoped>
</style>
