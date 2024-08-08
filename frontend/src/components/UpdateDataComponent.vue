<template>
  <div class="update">
    更新数据
    <hr>
    <el-input name="updateIdInput" v-model="updateId" placeholder="请输入要更新的ID"/>
    <el-input name="updateContentInput" v-model="updatePosition" placeholder="请输入要更新的内容(位置)"/>
    <el-input name="updateContentInput" v-model="updateValue" placeholder="请输入要更新的内容(值)"/>
    <el-button name="updateButton" @click="updateData">更新</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {API_URL} from "@/config/apiConfig.js";

const updateId = ref('');
const updatePosition = ref('');
const updateValue = ref('');

const updateData = () => {
  axios.put(`${API_URL}/update?id=${updateId.value}&position=${updatePosition.value}&value=${updateValue.value}`)
    .then(response => {
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
  margin-top: 10px; /* 在上方添加一些空间 */
}

.update {
  margin-top: 15px; /* 在上方添加一些空间 */
}

</style>