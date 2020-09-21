import axios from 'axios'

export var getFlashcards = async function (vm, page, perPage, sortBy='created', direction='desc') {
  var deckId = window.localStorage.getItem('deckId')
  return axios.get('/api/deck', {params: {
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

export var commitChanges = async function (flashcards, removedIds=null, intervals=null) {
  var deckId = window.localStorage.getItem('deckId')

  return axios.post('/api/update_cards', {
      deckId,
      flashcards
  })
  .then(function () {
    if (intervals !== null) {
      return axios.post('/api/update_intervals', {
          deckId,
          intervals
      })
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
}
