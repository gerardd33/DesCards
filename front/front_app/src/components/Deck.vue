<template>
  <div class="deck">
    <span>{{ entry.id }}</span>
    <span>{{ entry.name }}</span>
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
.deck {
  display: flex;
  justify-content: space-between;
}
</style>
