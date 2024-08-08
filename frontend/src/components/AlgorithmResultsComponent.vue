<template>
  <div class="result">
    <div style="font-size: 25px" class="label-text">算法结果</div>
    <hr>
    <div class="displayedImageContainer">
      <img id="displayedImage" alt="" src=""/>
    </div>
    <span class="label-text" style="font-size: 14px;color: grey">文本内容</span>
    <hr>
      <el-table :data="detResult"
                style="width: 100%;"
                empty-text="请先执行算法以获取结果"
                border
                show-header
                height="200px"
                max-height="200px">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="position" label="Position"></el-table-column>
      <el-table-column prop="attribute" label="Attribute"></el-table-column>
    </el-table>
    <el-button name="saveButton" @click="createData">保存结果</el-button>
  </div>
</template>

<script setup>
import axios from 'axios';
import { detResult } from '../utils/utils.js';
import { API_URL } from '../config/apiConfig.js';

const createData = () => {
  axios.post(`${API_URL}/create`).then(response => {
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

.el-button {
  width: 100%;
}

</style>