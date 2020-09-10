<template>
  <div class="flashcards-container">
    {{ deck_name }}<br>
    {{ page }}
    <list :list="flashcards"
      :component="flashcard_componenet"
      @edit="edit"
      >
    </list>
    <button @click="prev">Poprzedni</button>
    <button @click="next">Następny</button>
    <button>Dodaj</button>
    <router-link to="/study">Nauka</router-link>
    <flashcard-form :flashcard="flashcards[edited_key]" @xd="update_flashcard"></flashcard-form>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
// import Deck from '@/components/Deck.vue'
import FlashcardForm from '@/components/FlashcardForm.vue'
import Flashcard from '@/components/Flashcard.vue'
//import  { getCookie } from '@/utils/cookies.js'
import axios from 'axios'

export default {
  name: 'Deck',
  data: function() {
    return {
      deck_name: '',
      flashcards: [ // TODO zmienić domyślną wartość
        {id: 1, name: 'Talia 1', front:'asdf', back:'fdsa'},
        {id: 2, name: 'Talia 2', front:'dwa', back:'fdsa'},
        {id: 3, name: 'Talin 3', front:'trzy', back:'fdsa'}
      ],
      flashcard_componenet: Flashcard,
      page: 0,
      limit: 20,
      last_page: false,
      edited_key: 0
    }
  },
  computed: {
    offset: function () {
      return this.page * this.limit
    }
  },
  components: {
    List,
    FlashcardForm
  },
  methods: {
    update_flashcard: function (updated_flashcard) {
      this.flashcards[this.edited_key] = updated_flashcard
      console.log('updated', this.flashcards[this.edited_key])
    },
    edit: function (key) {
      this.edited_key = key
    },
    getFlashcards: function () {
      var vm = this
      var deckId = window.localStorage.getItem('deckId')
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
    this.deck_name = window.localStorage.getItem('deck')
  }
}
</script>
