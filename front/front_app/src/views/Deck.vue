<template>
  <div class="flashcards-container">
    {{ deck_name }}
    <list :list="flashcards" :component="flashcard_componenet"></list>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import Deck from '@/components/Deck.vue'
import  { getCookie } from '@/utils/cookies.js'
// import axios from 'axios'

export default {
  name: 'Deck',
  data: function() {
    return {
      deck_name: '',
      flashcards: [
        {id: 1, name: 'Talia 1'},
        {id: 2, name: 'Talia 2'},
        {id: 3, name: 'Talin 3'}
      ],
      flashcard_componenet: Deck
    }
  },
  components: {
    List
  },
  methods: {
  },
  created: function () {
    this.deck_name = getCookie('deck')
  }
}
</script>
