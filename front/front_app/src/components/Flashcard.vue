<template>
  <div class="flashcard" v-bind:class="{deleted: deleted}">
    <span class="field">{{ entry.id }}</span>
    <span class="field">{{ entry.front }}</span>
    <span class="back" :title="entry.back">{{ backShort }}</span>
    <span class="field">{{ interval }}</span>
    <span class="field">
      <button @click="$emit('edit', index)">{{ editButton }}</button>
      <button @click="remove">{{ deleteButton }}</button>
    </span>
  </div>
</template>

<script>
import { parseInterval } from '@/utils/interval.js'
import { editButton, deleteButton } from '@/consts/messages.js'

export default {
  name: 'Flashcard',
  props: ['entry', 'index'],
  data: function () {
    return {
      deleted: false,
      // messages
      editButton,
      deleteButton
    }
  },
  computed: {
    interval: function () {
      console.log(this.entry.interval)
      return parseInterval(this.entry.interval.current)
    },
    backShort: function () {
      if (this.entry.back.length > 52) {
        return this.entry.back.substr(0, 52) + '...'
      } else {
        return this.entry.back
      }
    }
  },
  methods: {
    remove: function () {
      this.deleted = true
      this.$emit('delete', this.index)
    }
  }
}
</script>

<style scoped>
.flashcard {
  display: flex;
  justify-content: space-between;
}
.deleted {
  background-color: red;
}
.back {
  width: 50%;
}
</style>
