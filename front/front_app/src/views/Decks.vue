<template>
  <div class="decks-container">
    <list :list="decks" :component="deck_component"></list>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import Deck from '@/components/Deck.vue'
// import axios from 'axios'

export default {
  name: 'Decks',
  data: function() {
    return {
      decks: [
        {id: 1, name: 'Talia 1'},
        {id: 2, name: 'Talia 2'},
        {id: 3, name: 'Talin 3'}
      ],
      deck_component: Deck
    }
  },
  components: {
    List
  },
  methods: {
  }
}
</script>
