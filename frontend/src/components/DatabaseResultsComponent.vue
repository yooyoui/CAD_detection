<template>
  <div class="ShowDb">
    <span class="label-text">读取数据库结果</span>
    <hr>
    <el-table :data="DBData" style="width: 100%" max-height="350px">
      <el-table-column prop="id" label="ID"></el-table-column>
      <el-table-column prop="fileId" label="File ID"></el-table-column>
      <el-table-column prop="position" label="Position"></el-table-column>
      <el-table-column prop="value" label="Value"></el-table-column>
    </el-table>
    <button name="readButton" @click="readData">读取数据</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';

const DBData = ref([]);

const readData = () => {
  axios.get('http://localhost:8080/CadDet/read').then(response => {
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

button {
  width: 100%;
}

</style>