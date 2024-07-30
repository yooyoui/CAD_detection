<template>
  <div class="flex-container">
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
.flex-container {
  display: flex; /* 使用Flexbox布局 */
  align-items: flex-start; /* 使子元素在交叉轴的开始位置对齐 */
  font-family: '黑体', sans-serif; /* 设置字体 */
  font-weight: bold;
}

.left-side {
  flex: 1; /* 左侧容器的宽度是右侧容器的一半 */
  max-width: 300px; /* 设置左侧容器的最大宽度，根据需要调整 */
  border-right: 1px solid #ccc; /* 在左侧容器的右侧添加一条线 */
  padding-right: 20px; /* 可选：在内容和边框之间添加一些空间 */
  margin-right: 250px; /* 在两个容器之间添加一些空间 */
}

.right-side {
  white-space: pre-wrap;
  overflow-wrap: break-word;
  margin-bottom: 5px; /* 在文本区域下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}


</style>
