<template>
  <div class="callPy">
    <img id="displayedImage" alt="" src=""/>
    <hr class="img">
    调用算法
    <hr>
    <div class="input-with-button">
      <el-input clearable
            name="pathInput"
            type="text"
            v-model="imagePath"
            placeholder="请输入文件路径"/>
      <input type="file" id="fileImage" name="fileImage" @change="selectImg"/>
    </div>
    <el-button name="callPyButton" @click="callPy">调用</el-button>
  </div>
</template>

<script setup>
import {ref} from 'vue';
import axios from 'axios';
import {getDataForTable} from '../utils/utils.js';
import {API_URL} from "@/config/apiConfig.js";

const imagePath = ref('');
const pyResponse = ref('');

const showImg = () => {
  axios.post(`${API_URL}/getResultImg`)
      .then(response => {
        console.log(response);
        document.getElementById('displayedImage').src = response.data;
      })
      .catch(error => {
        console.error(error);
        alert('Failed to load image.');
      });
};

const callPy = () => {
  axios.post(`${API_URL}/CallPy`, {
    path: `${imagePath.value}`
  }).then(response => {
    console.log(response);
    pyResponse.value = JSON.stringify(response.data);
    getDataForTable();
    showImg();
  }).catch(error => {
    console.error(error.response.data);
    alert(error.response.data);
  });
};

const selectImg = () => {
  const file = document.getElementById("fileImage").files[0];
  console.log(file);
  if (window.FileReader) {
    const reader = new FileReader();
    reader.readAsDataURL(file);
    reader.onloadend = (e) => {
      imagePath.value = e.target.result;
      document.getElementById("displayedImage").setAttribute("src", e.target.result);
      console.log("***" + e.target.result);
    };
  }
};

</script>

<style scoped>

img {
  max-width: 100%; /* 调整图片的最大宽度，确保它不会超出其容器的宽度 */
  height: auto; /* 保持图片的原始宽高比 */
  display: block; /* 确保图片不会有额外的空间 */
  margin: 50px auto; /* 图片居中显示 */
}

input {
  width: 80%; /* 输入框的宽度为容器的100% */
  padding: 10px; /* 输入框的内边距 */
  margin: 5px; /* 增加边缘距离 */
  border: 1px solid #d0d0d0; /* 设置文本框边框的颜色为更浅的灰色 */
}

.el-button {
  width: 40%; /* 按钮的宽度为容器的100% */
  margin: 5px; /* 在上方添加一些空间 */
}

.img {
  margin-top: 50px; /* 在顶部添加一些空间 */
}

.el-input {
  width: 80%; /* 输入框的宽度为容器的100% */
  margin-bottom: 10px;
}

</style>