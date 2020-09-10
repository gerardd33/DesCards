import axios from 'axios'

export var getFlashcards = async function (vm, page, perPage, sortBy='interval', direction='asc') {
  var deckId = window.localStorage.getItem('deckId')
  axios.get('/api/deck', {params: {
    deckId,
    page,
    perPage,
    sortBy,
    direction
  }})
  .then(function (response) {
    vm.flashcards = response.data
  })
}

export var commitChanges = async function (editedCards, removedIds=null, intervals=null) {
  var deckId = window.localStorage.getItem('deckId')

  axios.post('/api/update_cards', {
      deckId,
      editedCards
  })
  .then(function () {
    if (intervals !== null) {
      return axios.post()
    } else {
      return null
    }
  })
  .then(function () {
    if (removedIds !== null) {
      return axios.post('/api/remove_cards', {
        deckId, 
        removedIds
      })
    } else {
      return null
    }
  })
  .then(function () {console.log('zapisano zmiany')})
  .catch(function () {
    // some error message,
    console.log('nie udało się zapisać zmian')
  })
}