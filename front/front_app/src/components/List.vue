<template>
  <div class="list">
    <component v-bind:is="component" 
      v-for="(entry, index) in list"
      v-bind:key="entry.id"
      v-bind:index="index"
      v-bind:entry="entry"
      v-on:edit="function (key) {$emit('edit', key)}"
      v-on:delete="function (key) {$emit('delete', key)}">
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
.list {
  width: 90%;
  margin: auto;
}
</style>
