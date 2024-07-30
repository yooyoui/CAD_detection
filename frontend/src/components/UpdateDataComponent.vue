<template>
  <div class="update">
    更新数据
    <hr>
    <input name="updateIdInput" v-model="updateId" placeholder="请输入要更新的ID"/>
    <input name="updateContentInput" v-model="updatePosition" placeholder="请输入要更新的内容(位置)"/>
    <input name="updateContentInput" v-model="updateValue" placeholder="请输入要更新的内容(值)"/>
    <button name="updateButton" @click="updateData">更新</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const updateId = ref('');
const updatePosition = ref('');
const updateValue = ref('');

const updateData = () => {
  axios.put(`http://localhost:8080/CadDet/update?id=${updateId.value}&position=${updatePosition.value}&value=${updateValue.value}`)
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

.update {
  margin-top: 50px; /* 在上方添加一些空间 */
}

</style>