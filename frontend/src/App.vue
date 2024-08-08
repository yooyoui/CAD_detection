<template>
  <div style="width: 100vw;height: auto;overflow: hidden;display: flex">
    <div class="left-side">
      <CallPyComponent />
      <br>
      <UpdateDataComponent />
      <br>
      <DeleteDataComponent />
    </div>
    <div class="right-side">
      <AlgorithmResultsComponent />
      <br>
      <DatabaseResultsComponent />
    </div>
  </div>
</template>

<script setup>
import CallPyComponent from './components/CallPyComponent.vue';
import UpdateDataComponent from './components/UpdateDataComponent.vue';
import DeleteDataComponent from './components/DeleteDataComponent.vue';
import AlgorithmResultsComponent from './components/AlgorithmResultsComponent.vue';
import DatabaseResultsComponent from './components/DatabaseResultsComponent.vue';
</script>

<style scoped>

/* App.vue 样式 */
.left-side {
  flex: 1; /* 左侧容器的宽度是右侧容器的一半 */
  max-width: 300px; /* 设置左侧容器的最大宽度，根据需要调整 */
  border-right: 5px solid #ccc; /* 在左侧容器的右侧添加一条线 */
  padding-right: 50px; /* 可选：在内容和边框之间添加一些空间 */
  margin-right: 100px; /* 在两个容器之间添加一些空间 */
}

.right-side {
}


</style>
