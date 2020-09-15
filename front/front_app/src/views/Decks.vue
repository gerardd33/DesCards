<template>
  <div class="decks-container">
    <list :list="decks" :component="deck_component"></list>
  </div>
</template>

<script>
// @ is an alias to /src
import List from '@/components/List.vue'
import Deck from '@/components/Deck.vue'
import axios from 'axios'

export default {
  name: 'Decks',
  data: function() {
    return {
      decks: [
        {id: 1, name: 'History Deck'},
        {id: 2, name: 'Science Deck'},
				{id: 3, name: 'Computer Science Deck'}
      ],
      deck_component: Deck
    }
  },
  components: {
    List
  },
  created: function () {
    var vm = this
    axios.get('/api/user_decks')
    .then(function (response) {
      if (response.status === 200) {
        console.log(response)
        vm.decks = response.data
      }
    })
  }
}
</script>
