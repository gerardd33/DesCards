<template>
  <div class="flashcard" v-bind:class="{deleted: deleted}">
		<div class="field front"><span>{{ frontShort }}</span></div>
		<div class="field back"><span :title="entry.back">{{ backShort }}</span></div>
		<div class="field"><span>{{ interval }}</span></div>
    <div class="field">
      <button v-on:click="edit">{{ editButton }}</button>
      <button v-on:click="remove">{{ deleteButton }}</button>
    </div>
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
      return parseInterval(this.entry.interval.current)
    },
    backShort: function () {
      if (this.entry.back.length > 40) {
        return this.entry.back.substr(0, 40) + '...'
      } else {
        return this.entry.back
      }
    },
		frontShort: function () {
			if (this.entry.front.length > 35) {
				return this.entry.front.substr(0, 35) + '...'
			} else {
				return this.entry.front
			}
		}
  },
  methods: {
    remove: function () {
      this.deleted = true
      this.$emit('delete', this.index)
    },
    edit: function () {
      this.$emit('edit', this.index)
    }
  }
}
</script>

<style scoped>

.flashcard {
  display: flex;
  justify-content: space-between;
	width: 1200px;
  height: 80px;
}

.deleted {
  background-color: darksalmon;
	opacity: 0.75;
}

.front {
	width: 15%;
}

.front span {
	display: inline-block;
	vertical-align: middle;
	margin: 0 0 15px 10px;
}

.back {
  width: 50%;
}

.flashcard {
	border: 2px solid mediumspringgreen;
	border-radius: 30px;
	padding: 10px 15px;
	margin: 10px 5px 10px 5px;
  font-size: 20px;
  text-align: left;
}

.field {
	margin: 5px 0;
}

.field span {
	display: inline-block;
	vertical-align: middle;
	text-align: left;
	margin-top: 18px;
}

.field button {
  font-size: 20px;
  padding: 7px 14px 7px 14px;
}

@media (min-width: 1500px) and (max-width: 1700px) {
  .flashcard {
    width: 1100px;
  }
}

@media (max-width: 1500px) {
  .flashcard {
    width: 800px;
  }
}

</style>
