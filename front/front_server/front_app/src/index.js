import buttonCounter from './button-counter.vue'
import VueRouter from 'vue-router'
import Vue from 'vue/dist/vue.js'

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
