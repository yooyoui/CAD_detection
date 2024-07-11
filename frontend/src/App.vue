<script setup>
import { ref } from 'vue';
import axios from 'axios';

const imagePath = ref('');
const updateId = ref('');
const updateContent = ref('');
const deleteId = ref('');


const callPy = () => {
  axios.post('http://localhost:8080/CadDet/CallPy', {
    path: `--image_dir=${imagePath.value}`
  }).then(response => {
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
};

const createData = () => {
  axios.post('http://localhost:8080/CadDet/create').then(response => {
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
};

const readData = () => {
  axios.get('http://localhost:8080/CadDet/read').then(response => {
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
};

const updateData = () => {
  axios.put(`http://localhost:8080/CadDet/update?id=${updateId.value}&content=${updateContent.value}`).then(response => {
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
};

const deleteData = () => {
  axios.delete(`http://localhost:8080/CadDet/delete?id=${deleteId.value}`).then(response => {
    console.log(response);
  }).catch(error => {
    console.error(error);
  });
}


</script>

<template>
  <div>
    <input type="text" v-model="imagePath" placeholder="请输入文件路径" /> <br>
    <button @click="callPy">调用Py接口</button> <br>
    <button @click="createData">创建数据</button> <br>
    <button @click="readData">读取数据</button> <br>
    <button @click="updateData">更新数据</button>
    <input v-model="updateId" placeholder="ID" />
    <input v-model="updateContent" placeholder="内容" /> <br>
    <button @click="deleteData">删除数据</button>
    <input v-model="deleteId" placeholder="ID" />
  </div>
</template>

<style scoped>

</style>
