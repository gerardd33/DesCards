<template>
  <div class="flashcards-container">
    <div class="list">
      {{ deck_name }}<br>
      <add-card @added="added++; showAddForm=false"></add-card>
    </div>
    <div class="sidebar">
      <flashcard-form :flashcard="flashcards[edited_key]" 
        @xd="update_flashcard"
        @hide="showEditForm=false"
        v-if="showEditForm"></flashcard-form>
      <button @click="save">{{ refreshButton }}</button><br>
      <button @click="$router.push('/study')">{{ studyButton }}</button><br>
      <button @click="$router.push('/flashcard')">{{ manageButton }}</button><br>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import FlashcardForm from '@/components/FlashcardForm.vue'
import Flashcard from '@/components/Flashcard.vue'
import AddCard from '@/components/AddCard.vue'
import { getFlashcards, commitChanges } from '@/utils/http.js'
import { nextButton, prevButton, addButton, studyButton, refreshButton, pageName, manageButton } from '@/consts/messages.js'
import axios from 'axios'

export default {
  name: 'Deck',
  data: function() {
    return {
      deck_name: '',
      flashcards: [ // TODO zmienić domyślną wartość
        {id: 1, name: 'History Deck', front:'asdf', back:'fdsa', interval: {current: 213}},
        {id: 2, name: 'Science Deck', front:'dwa', back:'fdsa', interval: {current: 213}},
				{id: 2, name: 'Computer Science Deck', front:'dwa', back:'fdsa', interval: {current: 213}},
      ],
      flashcard_componenet: Flashcard,
      cardsInDeck: 0,
      page: 0,
      perPage: 4,
      edited_key: 0,
      showEditForm: false,
      showAddForm: false,
      removed_indexes: [],
      removed: 0,
      added: 0,
      edited_indexes: [0],
      // messages
      nextButton,
      prevButton,
      addButton,
      studyButton,
      refreshButton,
      manageButton,
      pageName
    }
  },
  computed: {
    lastPage: function () {
      var difference = this.added - this.removed
      var l = Math.floor((this.cardsInDeck - 1 + difference) / this.perPage) 
      if (l < 0) { l = 0 }
      console.log(l)
      return l 
    }
  },
  components: {
    FlashcardForm,
    AddCard
  },
  methods: {
    update_flashcard: function (updated_flashcard) {
      this.flashcards[this.edited_key] = updated_flashcard
      console.log('updated', this.flashcards[this.edited_key])
    },
    onAdded: function () {
      this.added++
      this.showAddForm = false
    },
    edit: function (key) {
      this.edited_key = key
      this.edited_indexes.push(key)
      this.showEditForm = true
    },
    remove: function (key) {
      this.removed_indexes.push(key)
      // console.log(this.removed_indexes)
    },
    applyChanges: function () {
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
      vm.edited_indexes = [0]

      return commitChanges(updated, removed)
    },
    save: function () {
      var vm = this
      this.applyChanges()
      .then(function () {
        getFlashcards(vm, vm.page, vm.perPage)
      })
    },
    next: function () {
      console.log(this.cardsInDeck, this.removed, this.lastPage, this.added)
      var vm = this
      var promise = this.applyChanges()
      if (this.page < this.lastPage) {
        this.page++
      }
      promise.then(function () {
        getFlashcards(vm, vm.page, vm.perPage)
      })
    },
    prev: function () {
      console.log(this.cardsInDeck, this.removed, this.lastPage, this.added)
      var vm = this
      var promise = this.applyChanges()
      if (this.page > 0) {
        this.page--
      }
      promise.then(function () {
        getFlashcards(vm, vm.page, vm.perPage)
      })
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
  },
  beforeUnmount: function () {
    this.applyChanges()
  }
}
</script>

<style scoped>
.flashcards-container {
  display: grid;
  grid-template-columns: 70% auto;
}

.list {
  grid-column-start: 1;
  grid-column-end: 2;
}

.sidebar {
  grid-column-start: 2;
  grid-column-end: 3;
}

a {
  text-decoration: none;
  text-decoration-color: black;
}

button {
  width: 200px;
  height: 50px;
}
</style>
