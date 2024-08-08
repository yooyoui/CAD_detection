<template>
  <div class="delete">
    删除数据
    <hr>
    <el-input name="deleteIdInput" v-model="deleteId" placeholder="请输入要删除内容的ID"/>
    <el-button name="deleteDataButton" @click="deleteData">删除</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {API_URL} from "@/config/apiConfig.js";

const deleteId = ref('');

const deleteData = () => {
  axios.delete(`${API_URL}/delete?id=${deleteId.value}`).then(response => {
    console.log(response);
    alert(response.data);
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};
</script>

<style scoped>

.el-input {
  width: 80%; /* 输入框的宽度为容器的100% */
  margin-bottom: 10px; /* 在下方添加一些空间 */
}

.el-button {
  width: 40%; /* 按钮的宽度为容器的100% */
  margin-top: 5px; /* 在上方添加一些空间 */
}

.delete {
  margin-top: 15px; /* 在上方添加一些空间 */
}

</style>