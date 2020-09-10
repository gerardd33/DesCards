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
    <button>Dodaj</button>
    <router-link to="/study">Nauka</router-link>
    <flashcard-form :flashcard="flashcards[edited_key]" @xd="update_flashcard"></flashcard-form>
    <auto-card-form></auto-card-form>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
// import Deck from '@/components/Deck.vue'
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
      page: 0,
      perPage: 20,
      last_page: false,
      edited_key: 0,
      removed_indexes: [],
      edited_indexes: [],
    }
  },
  computed: {
    offset: function () {
      return this.page * this.limit
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
      // - next/prev
      // - unmount/destroy?
      // - apply changes

      var deckId = window.localStorage.getItem('deckId')
      var removed = this.removed_indexes.map((index) => this.flashcards[index].id)
      this.removed_indexes = []

      var mapForUpdate = function (index) {
        return {
          front: this.flashcards[index].front,
          back: this.flashcards[index].back,
          deckId: this.flashcards[index].deckId
        }
      }
      var updated = this.edited_indexes.map(mapForUpdate)
      this.edited_indexes = []

      commitChanges(updated, removed)

//    axios.post('/api/remove_cards', {
//      deckId, 
//      removed
//    })
//    .then(function () {
//      return axios('/api/update_cards', {
//        deckId,
//        updated
//      })}
//    )
//    .then(function () {console.log('zapisano zmiany')})
//    .catch(function () {
//      // some error message,
//      console.log('nie udało się zapisać zmian')
//    })
      
    },
    next: function () {
      this.applyChanges()
      if (!this.last_page) {
          this.page++
          getFlashcards(this, this.page, this.perPage)
      }
    },
    prev: function () {
      this.applyChanges()
      if (this.offset > 0) {
        this.page--
        getFlashcards(this, this.page, this.perPage)
      }
    }
  },
  created: function () {
    getFlashcards(this, this.page, this.perPage)
    this.deck_name = window.localStorage.getItem('deck')
  }
  // TODO commit changes on exit
}
</script>
