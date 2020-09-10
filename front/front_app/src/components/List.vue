<template>
  <div class="list">
    <component v-bind:is="component" 
      v-for="(entry, index) in list"
      v-bind:key="index"
      v-bind:index="index"
      v-bind:entry="entry"
      @edit="function (key) {$emit('edit', key)}"
      @delete="function (key) {$emit('delete', key)}">
    </component>
  </div>
</template>

<script>
export default {
  name: 'List',
  props: ['list', 'component'],
}
</script>

<style scoped>
</style>
