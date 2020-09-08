<template>
  <div class="list">
    <component v-bind:is="component" 
      v-for="entry in list"
      v-bind:key="entry.id"
      v-bind:entry="entry">
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
