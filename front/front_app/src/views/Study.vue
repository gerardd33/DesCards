<template>
  <div class="study">
    {{ flashcardText }}
    <button @click="prev">Prev</button>
    <button @click="next">Next</button>
  </div>
</template>

<script>
// @ is an alias to /src
// import axios from 'axios'
import { getFlashcards, commitChanges } from '@/utils/http.js'

export default {
  name: 'Study',
  data: function() {
    return {
      flashcards: [{}],
      prevFlashcards: [],
      removedIds: [],
      prevRemovedIds: [],
      editedIndexes: [],
      prevEditedIndexes: [],
      currentFlashcardId: 0,
      isFront: true,
      bufferSize: 20
    }
  },
  computed: {
    flashcardText: function () {
      if (this.isFront) {
        return this.getCurrentFlashcard().front
      } else {
        return this.getCurrentFlashcard().back
      }
    }
  },
  components: {
  },
  methods: {
    commit: function (cards, removedIds, editedIndexes, intervals=null) {
      var mapForUpdate = function (index) {
        return {
          front: cards[index].front,
          back: cards[index].back,
          deckId: cards[index].deckId
        }
      }
      var updated = editedIndexes.map(mapForUpdate)
      this.editedIndexes = []
      
      // TODO update intervals

      commitChanges(updated, removedIds, intervals)
    },
    getCurrentFlashcard: function () {
      var id = this.currentFlashcardId
      console.log(id)
      if (id >= 0 && id < this.flashcards.length) {
        return this.flashcards[id]
      } else if (id < 0 && -id <= this.prevFlashcards.length) {
        return this.prevFlashcards[this.prevFlashcards.length + id]
      }
    },
    next: function () {
      console.log(this.currentFlashcardId, this.prevFlashcards.length, this.flashcards.length)
      if (this.currentFlashcardId < this.flashcards.length - 1) {
        this.currentFlashcardId++
      } else {
        this.commit(this.prevFlashcards, this.prevRemovedIds, this.prevEditedIndexes)
  
        this.prevFlashcards = this.flashcards
        this.prevRemovedIds = this.removedIds
        this.removedIds = []
        this.prevEditedIndexes = this.editedIndexes
        this.editedIndexes = []
  
        getFlashcards(this, 0, this.bufferSize)
        // TODO how errors work

        this.currentFlashcardId = 0
      }
    },
    prev: function () {
      console.log(this.currentFlashcardId, this.prevFlashcards.length, this.flashcards.length)
      if (-this.currentFlashcardId < this.prevFlashcards.length) {
        this.currentFlashcardId--
      }
    }
  },
  created: function () {
    getFlashcards(this, 0, this.bufferSize)
  }
}
</script>
