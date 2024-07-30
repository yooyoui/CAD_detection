<template>
  <div class="result">
    <h1>算法运行结果</h1>
    <hr>
    <div class="displayedImageContainer">
      <img id="displayedImage" alt="" src=""/>
    </div>
    <span class="label-text">文本内容</span>
    <el-table :data="detResult" style="width: 100%" max-height="200px">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="position" label="Position"></el-table-column>
      <el-table-column prop="attribute" label="Attribute"></el-table-column>
    </el-table>
    <button name="saveButton" @click="createData">保存结果</button>
  </div>
</template>

<script setup>
import axios from 'axios';
import { detResult } from '../utils/utils.js';

const createData = () => {
  axios.post('http://localhost:8080/CadDet/create').then(response => {
    console.log(response);
    alert(response.data);
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};
</script>

<style scoped>

.result {
  margin-top: 70px;
  width: 1000px;
}

button {
  width: 100%;
}

</style>