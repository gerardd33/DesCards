<template>
  <div class="flashcards-container">
    {{ deck_name }}<br>
    {{ page }}
    <list :list="flashcards"
      :component="flashcard_componenet"
      @edit="edit"
      @delete="remove">
    </list>
    <button @click="prev">Poprzedni</button>
    <button @click="next">Następny</button>
    <button @click="applyChanges">Zapisz zmiany</button>
    <button>Dodaj</button>
    <router-link to="/study">Nauka</router-link>
    <flashcard-form :flashcard="flashcards[edited_key]" @xd="update_flashcard"></flashcard-form>
    <auto-card-form></auto-card-form>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import FlashcardForm from '@/components/FlashcardForm.vue'
import Flashcard from '@/components/Flashcard.vue'
import AutoCardForm from '@/components/AutoCardForm.vue'
import  { getFlashcards, commitChanges } from '@/utils/http.js'
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
      cardsInDeck: 0,
      page: 0,
      perPage: 4,
      edited_key: 0,
      removed_indexes: [],
      removed: 0,
      edited_indexes: [],
    }
  },
  computed: {
    lastPage: function () {
      console.log(this.cardsInDeck, this.removed, this.perPage)
      var l = Math.floor((this.cardsInDeck - 1 - this.removed) / this.perPage) 
      if (l < 0) { l = 0 }
      console.log(l)
      return l 
    }
  },
  components: {
    List,
    FlashcardForm,
    AutoCardForm
  },
  methods: {
    update_flashcard: function (updated_flashcard) {
      this.flashcards[this.edited_key] = updated_flashcard
      console.log('updated', this.flashcards[this.edited_key])
    },
    edit: function (key) {
      this.edited_key = key
      this.edited_indexes.push(key)
    },
    remove: function (key) {
      this.removed_indexes.push(key)
      // console.log(this.removed_indexes)
    },
//  getFlashcards: function () {
//    var vm = this
//    var deckId = window.localStorage.getItem('deckId')
//    axios.get('/api/deck', {params: {
//      deckId,
//      offset: vm.offset,
//      limit: vm.limit + 1, // Fetch 1 more to check if we're on last page
//      sortBy: 'interval', // TODO let user choose
//      direction: 'asc'
//    }})
//    .then(function (response) {
//      if (response.status === 200) {
//        vm.flashcards = response.data
//        vm.last_page = vm.flashcards.length < (vm.limit + 1)
//        if (!vm.last_page) {
//          vm.flashcards.pop()
//        }
//      }
//    })
//  },
    applyChanges: function () {
      // powinno być wykonane przy:
      // - next/prev OK
      // - unmount/destroy?
      // - apply changes
      var vm = this

      var removed = vm.removed_indexes.map((index) => vm.flashcards[index].id)
      vm.removed += removed.length
      vm.removed_indexes = []

      var mapForUpdate = function (index) {
        return {
          id: vm.flashcards[index].id,
          front: vm.flashcards[index].front,
          back: vm.flashcards[index].back,
          deckId: vm.flashcards[index].deckId
        }
      }
      console.log(vm.edited_indexes)
      var updated = vm.edited_indexes.map(mapForUpdate)
      vm.edited_indexes = []

      commitChanges(updated, removed)
    },
    next: function () {
      this.applyChanges()
      if (this.page < this.lastPage) {
          this.page++
          getFlashcards(this, this.page, this.perPage)
      }
    },
    prev: function () {
      this.applyChanges()
      if (this.page > 0) {
        this.page--
        getFlashcards(this, this.page, this.perPage)
      }
    }
  },
  created: function () {
    getFlashcards(this, this.page, this.perPage)
    var deckId = window.localStorage.getItem('deckId')
    var vm = this
    axios.get('/api/deck_info', {params: {deckId}})
    .then(function (response) {
      vm.cardsInDeck = response.data.totalCards
      console.log(vm.cardsInDeck, response)
    })
    this.deck_name = window.localStorage.getItem('deck')
  }
  // TODO commit changes on exit
}
</script>
