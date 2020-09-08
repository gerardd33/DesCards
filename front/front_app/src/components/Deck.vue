<template>
  <div class="deck">
    {{ entry.id }}
    {{ entry.name }}
    <button @click="choose">Wybierz</button>
  </div>
</template>

<script>
import { setCookie } from '@/utils/cookies.js'

export default {
  name: 'Deck',
  props: ['entry'],
  methods: {
    choose: function () {
      console.log('set cookie')
      setCookie('deck', this.entry.name)
      this.$router.push('/deck')
    }
  }
}
</script>

<style scoped>
</style>
