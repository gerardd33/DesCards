<template>
  <div class="flashcards-container">
    {{ deck_name }}<br>
    {{ page }}
    <list :list="flashcards" :component="flashcard_componenet"></list>
    <button @click="prev">Poprzedni</button>
    <button @click="next">Następny</button>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import Deck from '@/components/Deck.vue'
import  { getCookie } from '@/utils/cookies.js'
import axios from 'axios'

export default {
  name: 'Deck',
  data: function() {
    return {
      deck_name: '',
      flashcards: [ // TODO zmienić domyślną wartość
        {id: 1, name: 'Talia 1'},
        {id: 2, name: 'Talia 2'},
        {id: 3, name: 'Talin 3'}
      ],
      flashcard_componenet: Deck,
      page: 0,
      limit: 20,
      last_page: false,
    }
  },
  computed: {
    offset: function () {
      return this.page * this.limit
    }
  },
  components: {
    List
  },
  methods: {
    getFlashcards: function () {
      var vm = this
      var deckId = 1 // TODO read from local storage
      axios.get('/api/deck', {params: {
        deckId,
        offset: vm.offset,
        limit: vm.limit + 1, // Fetch 1 more to check if we're on last page
        sortBy: 'interval', // TODO let user choose
        direction: 'asc'
      }})
      .then(function (response) {
        if (response.status === 200) {
          vm.flashcards = response.data
          vm.last_page = vm.flashcards.length < (vm.limit + 1)
          if (!vm.last_page) {
            vm.flashcards.pop()
          }
        }
      })
    },
    next: function () {
      // TODO check last
      if (!this.last_page) {
          this.page++
          this.getFlashcards()
      }
    },
    prev: function () {
      if (this.offset > 0) {
        this.page--
        this.getFlashcards()
      }
    }
  },
  created: function () {
    this.getFlashcards()
    this.deck_name = getCookie('deck')
  }
}
</script>
