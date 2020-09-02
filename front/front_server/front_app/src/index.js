import buttonCounter from './button-counter.vue'

// eslint-disable-next-line
var app = new Vue({
  el: '#app',
  data: {
    message: 'Hello Vue!'
  },
  delimiters: ['[[', ']]'],
  components: {
    'button-counter': buttonCounter
  }
})
