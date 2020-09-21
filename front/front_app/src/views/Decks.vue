<template>
  <div class="decks-container">
    <list class="list" :list="decks" :component="deck_component"></list>
    <div class="sidebar">
      <button @click="showAddForm=!showAddForm">{{ addButton }}</button>
      <add-deck v-if="showAddForm"></add-deck>    
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import Deck from '@/components/Deck.vue'
import AddDeck from '@/components/AddDeck.vue'
import { addButton } from '@/consts/messages.js'
import axios from 'axios'

export default {
  name: 'Decks',
  data: function() {
    return {
      decks: [],
      deck_component: Deck,
      addButton,
      showAddForm: false
    }
  },
  components: {
    List,
    AddDeck,
  },
  created: function () {
    var vm = this
    axios.get('/api/user_decks')
    .then(function (response) {
      if (response.status === 200) {
        vm.decks = response.data
      }
    })
  }
}
</script>

<style scoped>
.decks-container {
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

button {
  width: 200px;
  height: 50px;
}
</style>
