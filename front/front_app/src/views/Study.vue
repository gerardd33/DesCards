<template>
  <div class="study">
    {{ flashcardText }}
  </div>
</template>

<script>
// @ is an alias to /src
import axios from 'axios'
import { getFlashcards, commitChanges } from '@/utils/http.js'

export default {
  name: 'Study',
  data: function() {
    return {
      flashcards: [{}],
      prevFlashcards: [],
      removedIds: [],
      editedIndexes: [],
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
    commitPrev: function () {
      var mapForUpdate = function (index) {
        return {
          front: this.prevFlashcards[index].front,
          back: this.prevFlashcards[index].back,
          deckId: this.prevFlashcards[index].deckId
        }
      }
      var updated = this.editedIndexes.map(mapForUpdate)
      this.editedIndexes = []
      
      // TODO update intervals

      commitChanges(updated, this.removedIds, interval)
    },
    getFlashcards: function () {
      this.commitPrev()

      this.prevFlashcards = this.flashcards

      getFlashcards(this, 0, this.bufferSize)
//    var vm = this
//    var deckId = window.localStorage.getItem('deckId')
//    axios.get('/api/deck', {params: {
//      deckId,
//      offset: 0,
//      limit: 20,
//      sortBy: 'interval',
//      direction: 'asc'
//    }})
//    .then(function (response) {
//      if (response.status === 200) {
//        vm.flashcards = response.data
//      }
//    })
    },
    getCurrentFlashcard: function () {
      var id = this.currentFlashcardId
      if (id >= 0 && id < this.flashcards.length) {
        return this.flashcards[id]
      } else if (id < 0 && -id <= this.prevFlashcards.length) {
        return this.prevFlashcards[this.prevFlashcards.length - id]
      }
    }
  },
  created: function () {
  }
}
</script>
