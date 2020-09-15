<template>
  <div class="study">
    <div :class="{flashcard: isFront, back: !isFront}">
      <span class="text">
      {{ getCurrentFlashcard().front }}
      </span>
    </div>
    <div :class="{back: !isFront}" v-if="!isFront">
      <span class="text">
      {{ getCurrentFlashcard().back }}
      </span>
    </div>
    <button @click="prev">Prev</button>
    <button @click="isFront = !isFront">Show</button>
    <button @click="next">Next</button>
    <br>
    <button @click="setStrength('again')">Again</button>
    <button @click="setStrength('hard')">Hard</button>
    <button @click="setStrength('ok')">Ok</button>
    <button @click="setStrength('easy')">Easy</button>
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
      flashcards: [{front: 'front', back: 'back'}],
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
    commit: function (cards, removedIds, editedIndexes) {
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
      // console.log(this.flashcards, this.prevFlashcards)
      console.log('study', cards)
      var intervals = cards.map(function (card) {return {id: card.id, strength: card.strength}})
      .filter(function (value) {
        var str = value.strength
        return (str === 'easy' || str === 'ok' || str === 'hard' || str === 'again')
      })

      console.log('study', intervals)
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
      this.isFront = true
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
    },
    addStrengthField: function () {
      this.flashcards = this.flashcards.map(function (card) {return {...card, strength: ''}})
    },
    setStrength: function (strength) {
      this.getCurrentFlashcard().strength = strength
      this.next()
    }
  },
  created: function () {
    getFlashcards(this, 0, this.bufferSize)
  },
  beforeUnmount: function() {
    this.commit(this.flashcards, this.removedIds, this.editedIndexes)
  }
}
</script>

<style scoped>
.flashcard {
  width: 80%;
  height: 80vh;
  margin: auto;
  text-align: center;
  line-height: 80vh;
}

.back {
  width: 80%;
  height: 40vh;
  margin: auto;
  text-align: center;
  line-height: 40vh;
}

.text {
  font-size: 40px;
}
button {
  width: 200px;
  height: 50px;
}
</style>
