<template>
  <div class="deck">
    <span>{{}}</span>
    <span class="deck-name">{{ entry.name }}</span>
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

span, button {
	display: inline-block;
	margin: 10px;
	font-size: 16px;
}

.deck {
	border: 2px solid mediumspringgreen;
	border-radius: 30px;
	padding: 10px 30px;
	margin: 10px;
}

.deck-name {
	display: inline-block;
	font-size: 25px;
	font-weight: bold;
}

</style>
