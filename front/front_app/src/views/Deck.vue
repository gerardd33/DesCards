<template>
  <div class="flashcards-container">
    <div class="list">
			<span class="deck-name">{{ deck_name }}</span>
			<br>
      <add-card @added="added++; showAddForm=false"></add-card>
    </div>
    <div class="sidebar">
      <flashcard-form :flashcard="flashcards[edited_key]" 
        v-on:update-card="update_flashcard"
        v-on:hide="showEditForm=false"
        v-if="showEditForm"></flashcard-form>
      <button v-on:click="$router.push('/study')">{{ studyButton }}</button><br>
      <button v-on:click="$router.push('/flashcards')">{{ manageButton }}</button><br>
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
      flashcards: [],
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
  width: 225px;
  height: 65px;
  font-size: 22px;
}

.deck-name {
	display: inline-block;
	font-size: 40px;
	font-weight: bold;
	margin: 15px;
}

</style>
