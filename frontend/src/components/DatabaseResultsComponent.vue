<template>
  <div class="ShowDb">
    <span class="label-text" style="font-size: 14px;color: grey">读取数据库结果</span>
    <hr>
    <el-table :data="DBData"
              style="width: 100%;"
              empty-text="点击读取数据以获取查看数据库"
              border
              show-header
              height="200px"
              max-height="200px">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="fileId" label="File ID"></el-table-column>
      <el-table-column prop="position" label="Position"></el-table-column>
      <el-table-column prop="value" label="Value"></el-table-column>
    </el-table>
    <el-button name="readButton" @click="readData">读取数据</el-button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import {API_URL} from "@/config/apiConfig.js";

const DBData = ref([]);

const readData = () => {
  axios.get(`${API_URL}/read`).then(response => {
    console.log(response.data);
    DBData.value = response.data.map(item => ({
      id: item.id,
      fileId: item.fileId,
      position: item.position,
      value: item.value,
    }));
  }).catch(error => {
    console.error(error);
    alert(error.response.data);
  });
};
</script>

<style scoped>

.ShowDb {
  margin-top: 70px;
  width: 1000px;
}

.el-button {
  width: 100%;
}

</style>