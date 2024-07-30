<template>
  <div class="delete">
    删除数据
    <hr>
    <input name="deleteIdInput" v-model="deleteId" placeholder="请输入要删除内容的ID"/>
    <button name="deleteDataButton" @click="deleteData">删除</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const deleteId = ref('');

const deleteData = () => {
  axios.delete(`http://localhost:8080/CadDet/delete?id=${deleteId.value}`).then(response => {
    console.log(response);
    alert(response.data);
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};
</script>

<style scoped>

input {
  width: 80%; /* 输入框的宽度为容器的100% */
  padding: 10px; /* 输入框的内边距 */
  margin-bottom: 10px; /* 在下方添加一些空间 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}

button {
  width: 40%; /* 按钮的宽度为容器的100% */
  margin-top: 5px; /* 在上方添加一些空间 */
}

.delete {
  margin-top: 50px; /* 在上方添加一些空间 */
}

</style>